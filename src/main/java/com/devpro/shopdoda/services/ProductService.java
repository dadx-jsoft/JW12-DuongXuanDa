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
	
	public List<Product> search(ProductSearch productSearch) {
		String jpql = "SELECT p FROM Product p where 1=1";
		
		if(!StringUtils.isEmpty(productSearch.getSeo())) {
			jpql = jpql + " AND p.seo = '" + productSearch.getSeo() + "'";
		}
		
		if(!StringUtils.isEmpty(productSearch.getCategorySeo())) {
			jpql = jpql + " AND p.categories.seo = '" + productSearch.getCategorySeo() + "'";
		}
		
		Query query = entityManager.createQuery(jpql, Product.class);
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
	public void saveOrUpdate(Product product, MultipartFile productAvatar, MultipartFile[] listProductImageFile) throws Exception {
		try {

			// truong hop la chinh sua
			if (product.getId() != null && product.getId() > 0) {
				Product productInDB = productRepo.findById(product.getId()).get();

				// nếu upload lại ảnh thì phai xoa anh cu di
				if (!isEmptyUploadFile(productAvatar)) {
					String avatarPath = ROOT_UPLOAD_PATH + productInDB.getAvatar();
					new File(avatarPath).delete();
				}
			}

			// kiem tra neu nguoi dung co upload file avatar?
			if (!isEmptyUploadFile(productAvatar)) {
				String avatarPath = "/product/avatar/" + productAvatar.getOriginalFilename();
				product.setAvatar(avatarPath);

				productAvatar.transferTo(new File(ROOT_UPLOAD_PATH + avatarPath));
			}
			
			// kiem tra neu nguoi dung co upload nhieu files anh san pham
			if(!isEmptyUploadFile(listProductImageFile)) {
				for (MultipartFile productImageFile : listProductImageFile) {
					String productPath = "/product/picture/" + productImageFile.getOriginalFilename();
					
					ProductsImages productsImages = new ProductsImages();
					productsImages.setCreatedDate(new Date());
					productsImages.setPath(productPath);
					productsImages.setProduct(product);
					productsImages.setTitle(productImageFile.getOriginalFilename());
					
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
