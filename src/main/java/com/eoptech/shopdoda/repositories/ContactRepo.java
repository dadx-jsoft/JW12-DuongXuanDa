package com.eoptech.shopdoda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer> {
    
    @Query(value = "SELECT c FROM Contact c where c.status = true")
    public List<Contact> getContacts();
    
}
