package com.eoptech.shopdoda.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.Category;

@Repository
public interface CategoriesRepo extends JpaRepository<Category, Integer> {

    @Query(value = "select c from Category c where c.status = true")
    List<Category> getCategories();
}
