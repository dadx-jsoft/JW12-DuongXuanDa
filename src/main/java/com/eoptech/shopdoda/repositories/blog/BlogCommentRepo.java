package com.eoptech.shopdoda.repositories.blog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eoptech.shopdoda.entities.blog.BlogComment;

public interface BlogCommentRepo extends JpaRepository<BlogComment, Integer>{

}
