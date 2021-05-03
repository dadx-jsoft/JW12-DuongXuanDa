package com.eoptech.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.Contact;

@Repository
public interface ContactRepo extends JpaRepository<Contact, Integer>{

}
