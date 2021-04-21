package com.devpro.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.devpro.shopdoda.entities.Category;
import com.devpro.shopdoda.entities.blog.BlogType;
import com.devpro.shopdoda.utils.Constants;

@Service
public class CategoriesService implements Constants {

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
