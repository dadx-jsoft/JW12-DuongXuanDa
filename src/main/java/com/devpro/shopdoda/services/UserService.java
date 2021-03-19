package com.devpro.shopdoda.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.devpro.shopdoda.entities.User;

@Service
public class UserService {
	@PersistenceContext
	protected EntityManager entityManager;

	public User loadUserByUsername(String userName) {
		try {
			String jpql = "Select u From User u Where u.username='" + userName + "'";
			Query query = entityManager.createQuery(jpql, User.class);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
