package com.eoptech.shopdoda.repositories.blog;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.eoptech.shopdoda.entities.blog.BlogType;

public interface BlogTypeRepo extends JpaRepository<BlogType, Integer> {
    @Query(value = "select b from BlogType b where b.status = true")
    List<BlogType> getBlogTypes();
}
