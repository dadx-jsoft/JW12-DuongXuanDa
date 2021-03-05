package com.devpro.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devpro.shopdoda.entities.Category;

@Repository
public interface CategoriesRepo extends JpaRepository<Category, Integer>{
	
}
