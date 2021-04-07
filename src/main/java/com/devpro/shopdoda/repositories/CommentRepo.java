package com.devpro.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devpro.shopdoda.entities.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer>{

}
