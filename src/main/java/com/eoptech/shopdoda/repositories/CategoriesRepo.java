package com.eoptech.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.Category;

@Repository
public interface CategoriesRepo extends JpaRepository<Category, Integer>{
	
}
