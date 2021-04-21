package com.devpro.shopdoda.services.blog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.devpro.shopdoda.entities.Review;
import com.devpro.shopdoda.entities.blog.Blog;
import com.devpro.shopdoda.entities.blog.BlogComment;

@Service
public class BlogCommentService {
	@PersistenceContext
	EntityManager entityManager;

	public List<BlogComment> getBlogComments() {
		String jpql = "Select c From BlogComment c Where c.status = true ";
		Query query = entityManager.createQuery(jpql, BlogComment.class);
		return query.getResultList();
	}

	public List<BlogComment> findByBlog(Blog blog) {
		try {
			String jpql = "Select c From BlogComment c Where c.blog.id='" + blog.getId() + "' And c.status = true";
			Query query = entityManager.createQuery(jpql, BlogComment.class);
			return (List<BlogComment>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
