package com.eoptech.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.entities.ProductsImages;

@Service
public class ProductImagesService {
	@PersistenceContext
	EntityManager entityManager;

	// tim kiem product images
	public List<ProductsImages> findByProduct(Product product) {
		String jpql = "SELECT p FROM ProductsImages p WHERE 1=1 AND p.status = true";

		jpql = jpql + " AND p.product.id = '" + product.getId() + "'";

		Query query = entityManager.createQuery(jpql, ProductsImages.class);
		return query.getResultList();
	}
}
