package com.eoptech.shopdoda.services.blog;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.blog.BlogType;
import com.eoptech.shopdoda.repositories.blog.BlogTypeRepo;

@Service
public class BlogTypeService {

    @Autowired
    private BlogTypeRepo blogTypeRepo;

    public List<BlogType> getBlogTypes() {
        return blogTypeRepo.getBlogTypes();
    }
}
