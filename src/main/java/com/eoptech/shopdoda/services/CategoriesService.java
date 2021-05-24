package com.eoptech.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.Category;

@Service
public class CategoriesService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Category> getAllParents() {
		String jpql = "select * from tbl_category tc where tc.parent_id is null and tc.status = true";
		Query query = entityManager.createNativeQuery(jpql, Category.class);
		return query.getResultList();
	}

	public List<Category> getCategories() {
		String jpql = "select c from Category c where c.status = true";
		Query query = entityManager.createQuery(jpql, Category.class);
		return query.getResultList();
	}

}
