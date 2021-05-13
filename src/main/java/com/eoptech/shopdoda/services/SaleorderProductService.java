package com.eoptech.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.SaleorderProduct;

@Service
public class SaleorderProductService {
	@PersistenceContext
	protected EntityManager entityManager;
	
	public List<SaleorderProduct> findAllBySaleorderId(int saleorderId){
		String jpql = "Select u From SaleorderProduct u Where u.saleorder.id='" + saleorderId + "' And u.status = true";
		Query query = entityManager.createQuery(jpql, SaleorderProduct.class);
		return (List<SaleorderProduct>) query.getResultList();
	}
}
