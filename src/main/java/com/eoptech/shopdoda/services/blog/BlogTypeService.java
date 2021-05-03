package com.eoptech.shopdoda.services.blog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.blog.BlogType;

@Service
public class BlogTypeService {
	@PersistenceContext
	EntityManager entityManager;
	
	public List<BlogType> getBlogTypes() {
		String jpql = "select b from BlogType b where b.status = true";
		Query query = entityManager.createQuery(jpql, BlogType.class);
		return query.getResultList();
	}
}
