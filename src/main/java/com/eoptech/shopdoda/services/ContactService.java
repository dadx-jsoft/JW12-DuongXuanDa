package com.eoptech.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.Contact;

@Service
public class ContactService {

	@PersistenceContext
	EntityManager entityManager;
	
	public List<Contact> getContacts() {
		String jpql = "SELECT c FROM Contact c where c.status = true";
		Query query = entityManager.createQuery(jpql, Contact.class);
		return query.getResultList();
	}
	
}