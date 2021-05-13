package com.eoptech.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.User;

@Service
public class UserService {
	@PersistenceContext
	protected EntityManager entityManager;

	public User findByUsername(String userName) {
		try {
			String jpql = "Select u From User u Where u.username='" + userName + "' And u.status = true";
			Query query = entityManager.createQuery(jpql, User.class);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User findById(Integer userId) {
		try {
			String jpql = "Select u From User u Where u.id='" + userId + "'";
			Query query = entityManager.createQuery(jpql, User.class);
			return (User) query.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public User findByEmail(String email){
		try {
			String jpql = "Select u From User u Where u.email='" + email + "'";
			Query query = entityManager.createQuery(jpql, User.class);
			return (User) query.getSingleResult();
		} catch (NoResultException ne) { // không tìm thấy
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public List<User> getAllUsers() {
		try {
			String jpql = "Select u From User u Where u.status = true Order By u.createdDate";
			Query query = entityManager.createQuery(jpql, User.class);
			return query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
