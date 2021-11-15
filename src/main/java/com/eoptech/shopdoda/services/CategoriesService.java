package com.eoptech.shopdoda.services;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.Category;
import com.eoptech.shopdoda.repositories.CategoriesRepo;

@Service
public class CategoriesService {

    @Autowired
    private CategoriesRepo categoriesRepo;

    @PersistenceContext
    EntityManager entityManager;

    public List<Category> getAllParents() {
        String jpql = "select * from tbl_category tc where tc.parent_id is null and tc.status = true";
        Query query = entityManager.createNativeQuery(jpql, Category.class);
        return query.getResultList();
    }

    public List<Category> getCategories() {
        return categoriesRepo.getCategories();
    }

}
