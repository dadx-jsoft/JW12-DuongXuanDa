package com.devpro.shopdoda.repositories.blog;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devpro.shopdoda.entities.blog.BlogComment;

public interface BlogCommentRepo extends JpaRepository<BlogComment, Integer>{

}
