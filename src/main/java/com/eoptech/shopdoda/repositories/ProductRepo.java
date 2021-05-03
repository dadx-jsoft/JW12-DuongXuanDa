package com.eoptech.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.Product;

@Repository
public interface ProductRepo extends JpaRepository<Product, Integer>{

}
