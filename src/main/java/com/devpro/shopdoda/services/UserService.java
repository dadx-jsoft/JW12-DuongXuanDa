package com.devpro.shopdoda.services;

import java.util.List;

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
			String jpql = "Select u From User u Where u.username='" + userName + "' And u.status = true";
			Query query = entityManager.createQuery(jpql, User.class);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User loadUserById(Integer userId) {
		try {
			String jpql = "Select u From User u Where u.id='" + userId + "'";
			Query query = entityManager.createQuery(jpql, User.class);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public List<User> getAllUsers(){
		try {
			String jpql = "Select u From User u Where u.status = true";
			Query query = entityManager.createQuery(jpql, User.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
}
