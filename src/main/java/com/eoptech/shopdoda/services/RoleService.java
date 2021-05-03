package com.eoptech.shopdoda.services;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.Role;

@Service
public class RoleService {
	@PersistenceContext
	EntityManager entityManager;

	// tim kiem role by name
	public Role getRoleByName(String roleName) {
		String jpql = "SELECT r FROM Role r where r.name = '"+roleName+"' AND r.status = true";

		System.out.println(jpql);

		Query query = entityManager.createQuery(jpql, Role.class);

		return (Role) query.getSingleResult();
	}
}
