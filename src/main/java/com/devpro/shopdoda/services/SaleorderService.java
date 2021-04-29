package com.devpro.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.devpro.shopdoda.dto.search.SaleorderSearch;
import com.devpro.shopdoda.entities.Saleorder;
import com.devpro.shopdoda.taglibs.PaginationTaglib;

@Service
public class SaleorderService {

	@PersistenceContext
	EntityManager entityManager;

	public List<Saleorder> search(SaleorderSearch saleorderSearch) {
		String jpql = "SELECT p FROM Saleorder p where p.status = true";

		if (!StringUtils.isEmpty(saleorderSearch.getSearchText())) {
			String st = "'%" + saleorderSearch.getSearchText().toLowerCase() + "%'";
			jpql = jpql + " AND ( LOWER(p.customerName) LIKE " + st + " ) ";
		}
		jpql = jpql + " ORDER BY p.createdDate DESC";

		Query query = entityManager.createQuery(jpql, Saleorder.class);

		// paging
		if (saleorderSearch.getOffset() != null) {
			saleorderSearch.setCount(query.getResultList().size());

			query.setFirstResult(saleorderSearch.getOffset());
			query.setMaxResults(PaginationTaglib.MAX);

		}
		return query.getResultList();
	}
}
