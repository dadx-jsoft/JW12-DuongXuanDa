package com.devpro.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devpro.shopdoda.dto.search.ReviewOrCommentSearch;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.Review;
import com.devpro.shopdoda.taglibs.PaginationTaglib;

@Service
public class ReviewService {
	@PersistenceContext
	protected EntityManager entityManager;

	public List<Review> findByProduct(Product product) {
		try {
			String jpql = "Select c From Review c Where c.product.id='" + product.getId() + "' And c.status = true";
			Query query = entityManager.createQuery(jpql, Review.class);
			return (List<Review>) query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<Review> search(ReviewOrCommentSearch reviewSearch) {
		String jpql = "SELECT p FROM Review p where 1=1 ";

		if (!StringUtils.isEmpty(reviewSearch.getSearchText())) {
			String st = "'%" + reviewSearch.getSearchText().toLowerCase() + "%'";
			jpql = jpql + " AND ( LOWER(p.message) LIKE " + st + " ) ";
		}
		jpql = jpql + " ORDER BY p.createdDate DESC";

		Query query = entityManager.createQuery(jpql, Review.class);

		// paging
		if (reviewSearch.getOffset() != null) {
			reviewSearch.setCount(query.getResultList().size());

			query.setFirstResult(reviewSearch.getOffset());
			query.setMaxResults(PaginationTaglib.MAX);

		}
		return query.getResultList();
	}
}
