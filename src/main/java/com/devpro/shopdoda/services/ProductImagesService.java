package com.devpro.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devpro.shopdoda.dto.ProductSearch;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.ProductsImages;
import com.devpro.shopdoda.repositories.Products_ImagesRepo;

@Service
public class ProductImagesService {
	@Autowired
	private Products_ImagesRepo products_ImagesRepo;

	@PersistenceContext
	EntityManager entityManager;

	// tim kiem product images
	public List<ProductsImages> findByProduct(Product product) {
		String jpql = "SELECT p FROM ProductsImages p where 1=1";

		jpql = jpql + " AND p.product.id = '" + product.getId() + "'";

		Query query = entityManager.createQuery(jpql, ProductsImages.class);
		return query.getResultList();
	}
}
