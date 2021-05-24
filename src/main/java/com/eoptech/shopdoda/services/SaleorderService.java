package com.eoptech.shopdoda.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.eoptech.shopdoda.dto.TKThang;
import com.eoptech.shopdoda.dto.search.SaleorderSearch;
import com.eoptech.shopdoda.entities.Saleorder;
import com.eoptech.shopdoda.taglibs.PaginationTaglib;

@Service
public class SaleorderService {

	@PersistenceContext
	EntityManager entityManager;

	private int DAY_OF_MONTH = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	private int MONTH = Calendar.getInstance().get(Calendar.MONTH) + 1;
	private int YEAR = Calendar.getInstance().get(Calendar.YEAR);

	public List<Saleorder> search(SaleorderSearch saleorderSearch) {
		String jpql = "SELECT p FROM Saleorder p where p.status = true";

//		if (!StringUtils.isEmpty(saleorderSearch.getSearchText())) {
//			String st = "'%" + saleorderSearch.getSearchText().toLowerCase() + "%'";
//			jpql = jpql + " AND ( LOWER(p.customerName) LIKE " + st + " ) ";
//		}
		if (!StringUtils.isEmpty(saleorderSearch.getSearchText())) {
			String st = "'" + saleorderSearch.getSearchText().toLowerCase() + "'";
			jpql = jpql + " AND ( p.customerPhone = " + st + " ) ";
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

	public List<Saleorder> getAllByMonth() {
		String nativeSql = "SELECT * FROM tbl_saleorder s " + " WHERE s.status = true "
				+ " AND s.order_status = 3 "
				+ " AND MONTH(s.created_date) = " + this.MONTH + " AND YEAR(s.created_date) = " + this.YEAR;
		Query query = entityManager.createNativeQuery(nativeSql, Saleorder.class);

		List<Saleorder> results = query.getResultList();

		return results;
	}

	public String thongKeTheoThang() {
		StringBuilder data = new StringBuilder();

		String nativeSql = "SELECT MONTH(s.created_date), SUM(s.total) FROM tbl_saleorder s " 
				+ " WHERE s.status = true "
				+ " AND s.order_status = 3 "
				+ " GROUP BY MONTH(s.created_date) "
				+ " ORDER BY MONTH(s.created_date) ";

		Query query = entityManager.createNativeQuery(nativeSql);

		List<Object[]> lstObj = query.getResultList();

		List<TKThang> lst = new ArrayList<TKThang>();
		for (Object[] object : lstObj) {
			TKThang tkThang = new TKThang();
			tkThang.setThang((int) object[0]);
			tkThang.setTotal(((BigDecimal) object[1]).intValue());
			lst.add(tkThang);
		}

		int[] dataInt = new int[12];
		for (int i = 0; i < lst.size(); i++) {
			for (int j = 0; j < 12; j++) {
				if (lst.get(i).getThang() == j + 1) {
					dataInt[j] = lst.get(i).getTotal();
					break;
				}
			}
		}
		data.append("[");
		for (int i = 0; i < dataInt.length - 1; i++) {
			data.append(dataInt[i] + ",");
		}
		data.append(dataInt[dataInt.length - 1]);
		data.append("]");

		return data.toString();
	}

	public Integer doanhThuNgay() {
		Integer data = 0;

		String nativeSql = "SELECT SUM(s.total) " + "FROM tbl_saleorder s" + " WHERE s.status = true"
				+ " AND s.order_status = 3 "
				+ " AND DAY(s.created_date) = " + this.DAY_OF_MONTH + " AND MONTH(s.created_date) = " + this.MONTH
				+ " AND YEAR(s.created_date) = " + this.YEAR;

		Query query = entityManager.createNativeQuery(nativeSql);

		try {
			data = ((BigDecimal) query.getSingleResult()).intValue();
		} catch (NullPointerException e) {
			return 0;
		}
		return data;
	}

	public Integer doanhThuThang() {
		Integer data = 0;

		String nativeSql = "SELECT SUM(s.total)" + "FROM tbl_saleorder s" + " WHERE s.status = true"
				+ " AND s.order_status = 3 "
				+ " AND MONTH(s.created_date) = " + this.MONTH + " AND YEAR(s.created_date) = " + this.YEAR;

		Query query = entityManager.createNativeQuery(nativeSql);

		try {
			data = ((BigDecimal) query.getSingleResult()).intValue();
		} catch (NullPointerException e) {
			return 0;
		}
		return data;
	}

	public Integer doanhThuNam() {
		Integer data = 0;

		String nativeSql = "SELECT SUM(s.total)" + " FROM tbl_saleorder s" + " WHERE s.status = true"
				+ " AND s.order_status = 3 "
				+ " AND YEAR(s.created_date) = " + this.YEAR;

		Query query = entityManager.createNativeQuery(nativeSql);

		try {
			data = ((BigDecimal) query.getSingleResult()).intValue();
		} catch (NullPointerException e) {
			return 0;
		}
		return data;
	}

	public List<Saleorder> findSaleordersByUserId(int userId) {

		String jpql = "SELECT p FROM Saleorder p where p.status = true And p.userId = " + userId;
		Query query = entityManager.createQuery(jpql, Saleorder.class);
		List<Saleorder> saleorders = (List<Saleorder>) query.getResultList();
		return saleorders;

	}

}
