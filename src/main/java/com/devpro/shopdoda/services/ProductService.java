package com.devpro.shopdoda.services;

import java.io.File;
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

import com.devpro.shopdoda.dto.ProductSearch;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.ProductsImages;
import com.devpro.shopdoda.repositories.ProductRepo;
import com.devpro.shopdoda.repositories.Products_ImagesRepo;
import com.devpro.shopdoda.taglibs.PaginationTaglib;
import com.devpro.shopdoda.utils.Constants;
import com.devpro.shopdoda.utils.Utilities;

@Service
public class ProductService implements Constants {

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private Products_ImagesRepo products_ImagesRepo;

	@PersistenceContext
	EntityManager entityManager;

	// tim kiem product
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
			String st = "'%" + productSearch.getSearchText() + "%'";
			jpql = jpql + " AND (p.title LIKE " 
					+ st + " OR p.shortDescription LIKE " 
					+ st + " ) ";
		}
		
		System.out.println(jpql);

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
			Product productInDB = productRepo.findById(product.getId()).get();
			// truong hop la chinh sua
			if (product.getId() != null && product.getId() > 0) {

				// nếu upload lại avatar thì phải xóa avatar cũ đi
				if (!isEmptyUploadFile(productAvatar)) {
					String oldAvatarPath = productInDB.getAvatar();
					if(!StringUtils.isEmpty(oldAvatarPath)) {
						new File(ROOT_UPLOAD_PATH + oldAvatarPath).delete();
					}
				} else {
					product.setAvatar(productInDB.getAvatar());
				}
			}

			// kiem tra neu nguoi dung co upload file avatar?
			if (!isEmptyUploadFile(productAvatar)) {
				String avatarPath = "product/avatar/" + productAvatar.getOriginalFilename();
				product.setAvatar(avatarPath);

				productAvatar.transferTo(new File(ROOT_UPLOAD_PATH + avatarPath));
			}else {
				product.setAvatar(productInDB.getAvatar());
			}

			// Nếu upload nhiều file ảnh sản phẩm
			if (!isEmptyUploadFile(listProductImageFile)) {
				for (MultipartFile productImageFile : listProductImageFile) {
					String productPath = "product/picture/" + productImageFile.getOriginalFilename();

					ProductsImages productsImages = new ProductsImages();
					productsImages.setTitle(productImageFile.getOriginalFilename());
					productsImages.setPath(productPath);
					productsImages.setProduct(product);
					productsImages.setCreatedDate(new Date());
					
					productImageFile.transferTo(new File(ROOT_UPLOAD_PATH + productPath));
					products_ImagesRepo.save(productsImages);
				}
			}

			product.setSeo(Utilities.seo(product.getTitle() + "-" + System.currentTimeMillis()));

			productRepo.save(product);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
