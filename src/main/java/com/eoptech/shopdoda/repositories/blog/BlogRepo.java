package com.eoptech.shopdoda.repositories.blog;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.blog.Blog;

@Repository
public interface BlogRepo extends JpaRepository<Blog, Integer> {

}
