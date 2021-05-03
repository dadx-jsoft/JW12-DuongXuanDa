package com.eoptech.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.Saleorder;

@Repository
public interface SaleorderRepo extends JpaRepository<Saleorder, Integer> {

}
