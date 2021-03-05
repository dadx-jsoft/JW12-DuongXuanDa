package com.devpro.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devpro.shopdoda.entities.Saleorder;

@Repository
public interface SaleorderRepo extends JpaRepository<Saleorder, Integer> {

}
