package com.eoptech.shopdoda.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.eoptech.shopdoda.dto.search.ProductSearch;
import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.entities.ProductsImages;
import com.eoptech.shopdoda.repositories.ProductRepo;
import com.eoptech.shopdoda.taglibs.PaginationTaglib;
import com.eoptech.shopdoda.utils.PathConstant;
import com.eoptech.shopdoda.utils.Utilities;

@Service
public class ProductService {

	@Autowired
	private ProductRepo productRepo;

	@PersistenceContext
	EntityManager entityManager;

	// tim kiem product
	// https://stackoverflow.com/questions/871578/how-to-compare-strings-in-sql-ignoring-case/871583
	public List<Product> search(ProductSearch productSearch) {
		String jpql = "SELECT p FROM Product p where 1=1";

		jpql = jpql + " AND p.status = true";

		if (!StringUtils.isEmpty(productSearch.getSeo())) {
			jpql = jpql + " AND p.seo = '" + productSearch.getSeo() + "'";
		}

		if (!StringUtils.isEmpty(productSearch.getCategorySeo())) {
			jpql = jpql + " AND p.categories.seo = '" + productSearch.getCategorySeo() + "'";
		}
		if (!StringUtils.isEmpty(productSearch.getSearchText())) {
			String st = "'%" + productSearch.getSearchText().toLowerCase() + "%'";
			jpql = jpql + " AND ( LOWER(p.title) LIKE " + st 
						+ " OR LOWER(p.shortDescription) LIKE " + st 
						+ " OR LOWER(p.detailDescription) LIKE " + st +" ) ";
		}
		jpql = jpql + " ORDER BY p.createdDate DESC";
		
		Query query = entityManager.createQuery(jpql, Product.class);

		// paging
		if (productSearch.getOffset() != null) {
			productSearch.setCount(query.getResultList().size());

			query.setFirstResult(productSearch.getOffset());
			query.setMaxResults(PaginationTaglib.MAX);

		}
		return query.getResultList();
	}

	/**
	 * Kiem tra uploaded file.
	 * 
	 * @param images
	 * @return true - khi nguoi dung khong upload file.
	 */
	private boolean isEmptyUploadFile(MultipartFile[] images) {
		if (images == null || images.length <= 0)
			return true;
		if (images.length == 1 && images[0].getOriginalFilename().isEmpty())
			return true;
		return false;
	}

	private boolean isEmptyUploadFile(MultipartFile images) {
		return images == null || images.getOriginalFilename().isEmpty();
	}

	@Transactional(rollbackOn = Exception.class)
	public void saveOrUpdate(Product product, MultipartFile productAvatar, MultipartFile[] listProductImageFile)
			throws Exception {
		try {
			// TH chỉnh sửa
			if (product.getId() != null && product.getId() > 0) {
				Product productInDB = productRepo.findById(product.getId()).get();
				// nếu upload lại avatar thì phải xóa avatar cũ đi
				if (!isEmptyUploadFile(productAvatar)) {
					String oldAvatarPath = productInDB.getAvatar();
					if (!StringUtils.isEmpty(oldAvatarPath)) {
						new File(PathConstant.ROOT_UPLOAD_PATH + oldAvatarPath).delete();
					}
					String avatarPath = "product/avatar/" + productAvatar.getOriginalFilename();
					product.setAvatar(avatarPath);
					productAvatar.transferTo(new File(PathConstant.ROOT_UPLOAD_PATH + avatarPath));
				} else {
					product.setAvatar(productInDB.getAvatar());
				}
				product.setCreatedDate(productInDB.getCreatedDate());
			}
			// TH tạo mới, nếu upload avatar
			else if (!isEmptyUploadFile(productAvatar)) {
				String avatarPath = "product/avatar/" + productAvatar.getOriginalFilename();
				product.setAvatar(avatarPath);
				productAvatar.transferTo(new File(PathConstant.ROOT_UPLOAD_PATH + avatarPath));
			}
			// Upload nhiều file ảnh sản phẩm
			if (!isEmptyUploadFile(listProductImageFile)) {
				List<ProductsImages> images = new ArrayList<>();

				for (MultipartFile productImageFile : listProductImageFile) {
					String productPath = "product/picture/" + productImageFile.getOriginalFilename();

					ProductsImages productsImages = new ProductsImages();
					productsImages.setTitle(productImageFile.getOriginalFilename());
					productsImages.setPath(productPath);
					productsImages.setProduct(product);
					productsImages.setCreatedDate(new Date());

					productImageFile.transferTo(new File(PathConstant.ROOT_UPLOAD_PATH + productPath));
					images.add(productsImages);
				}
				product.setProductsImages(images);
			}
			product.setUpdatedDate(new Date());
			product.setCreatedDate(product.getCreatedDate() == null ? new Date() : product.getCreatedDate());
			product.setSeo(Utilities.seo(product.getTitle() + "-" + System.currentTimeMillis()));
			productRepo.save(product);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

	public List<Product> getBestSellingProducts() {
		// http://diendan.congdongcviet.com/threads/t73773::lam-sao-de-lay-5-ten-san-pham-duoc-ban-nhieu-nhat.cpp
		String nativeSql = "SELECT sp.id,sp.title, SUM(cthd.quantity) AS quantity, sp.price "
				+ "FROM tbl_products sp, tbl_saleorder_products cthd "
				+ "WHERE sp.id=cthd.product_id AND sp.status=true " 
				+ "GROUP BY sp.id,sp.title,sp.price "
				+ "ORDER BY Sum(cthd.quantity) DESC " + "LIMIT 0, 8";
		Query query = entityManager.createNativeQuery(nativeSql);

		// https://stackoverflow.com/questions/13700565/jpa-query-getresultlist-use-in-a-generic-way
		List<Object[]> listPartialProductObject = query.getResultList();

		List<Integer> idProductList = new ArrayList<Integer>();

		for (Object[] partialProductObject : listPartialProductObject) {
			idProductList.add((Integer) partialProductObject[0]);
		}

		// https://stackoverflow.com/questions/6277807/jpa-passing-list-to-in-clause-in-named-native-query
		// https://stackoverflow.com/questions/396748/ordering-by-the-order-of-values-in-a-sql-in-clause
		String nativeQueryGetProducts = "SELECT * FROM tbl_products WHERE id IN :ids ORDER BY FIELD(id, :ids)";
		Query q = entityManager.createNativeQuery(nativeQueryGetProducts, Product.class);
		q.setParameter("ids", idProductList);

		return q.getResultList();
	}
	
	public Product findById(int id) {
		String jpql = "SELECT p FROM Product p where p.status = true";
		Query query = entityManager.createQuery(jpql, Product.class);
		return (Product) query.getSingleResult();
	}

}
