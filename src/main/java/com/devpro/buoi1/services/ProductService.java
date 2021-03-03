package com.devpro.buoi1.services;

import java.io.File;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.buoi1.entities.Product;
import com.devpro.buoi1.repositories.ProductRepo;
import com.devpro.buoi1.utils.Constants;

@Service
public class ProductService implements Constants {

	@Autowired
	private ProductRepo productRepo;

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
	public void saveOrUpdate(Product product, MultipartFile productAvatar) throws Exception {
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

			// kiem tra neu nguoi dung co upload file.
			if (!isEmptyUploadFile(productAvatar)) {
				String avatarPath = "/product/avatar/" + productAvatar.getOriginalFilename();
				product.setAvatar(avatarPath);

				productAvatar.transferTo(new File(ROOT_UPLOAD_PATH + avatarPath));
			}

			productRepo.save(product);

		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
	}

}
