package com.eoptech.shopdoda.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.Contact;
import com.eoptech.shopdoda.repositories.ContactRepo;

@Service
public class ContactService {
    
    @Autowired
    private ContactRepo contactRepo;

	public List<Contact> getContacts() {
		return contactRepo.getContacts();
	}
	
}