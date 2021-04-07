package com.devpro.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.devpro.shopdoda.entities.Comment;
import com.devpro.shopdoda.entities.Product;

@Service
public class CommentService {
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Comment> findByProduct(Product product) {
		try {
			String jpql = "Select c From Comment c Where c.product.id='" + product.getId() + "' And c.status = true";
			Query query = entityManager.createQuery(jpql, Comment.class);
			return (List<Comment>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
