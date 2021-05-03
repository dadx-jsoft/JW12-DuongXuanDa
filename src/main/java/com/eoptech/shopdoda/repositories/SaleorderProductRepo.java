package com.eoptech.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.SaleorderProduct;

@Repository
public interface SaleorderProductRepo extends JpaRepository<SaleorderProduct, Integer>{

}
