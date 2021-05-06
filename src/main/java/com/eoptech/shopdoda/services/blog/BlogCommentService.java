package com.eoptech.shopdoda.services.blog;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eoptech.shopdoda.dto.search.ReviewOrCommentSearch;
import com.eoptech.shopdoda.entities.blog.Blog;
import com.eoptech.shopdoda.entities.blog.BlogComment;
import com.eoptech.shopdoda.taglibs.PaginationTaglib;

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
	public List<BlogComment> search(ReviewOrCommentSearch commentSearch) {
		String jpql = "SELECT p FROM BlogComment p where 1=1 ";

		if (!StringUtils.isEmpty(commentSearch.getSearchText())) {
			String st = "'%" + commentSearch.getSearchText().toLowerCase() + "%'";
			jpql = jpql + " AND ( LOWER(p.comment) LIKE " + st + " ) ";
		}
		jpql = jpql + " ORDER BY p.createdDate DESC";

		if(commentSearch.getIdProductOrBlog() > 0) {
			jpql = "Select c From BlogComment c Where c.blog.id='" + commentSearch.getIdProductOrBlog() + "' And c.status = true";
		}
		Query query = entityManager.createQuery(jpql, BlogComment.class);

		// paging
		if (commentSearch.getOffset() != null) {
			commentSearch.setCount(query.getResultList().size());

			query.setFirstResult(commentSearch.getOffset());
			query.setMaxResults(PaginationTaglib.MAX);

		}
		return query.getResultList();
	}
}
