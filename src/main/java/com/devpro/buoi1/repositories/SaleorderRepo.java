package com.devpro.buoi1.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devpro.buoi1.entities.Saleorder;

@Repository
public interface SaleorderRepo extends JpaRepository<Saleorder, Integer> {

}
