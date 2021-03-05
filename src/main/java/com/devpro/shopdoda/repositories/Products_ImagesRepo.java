package com.devpro.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devpro.shopdoda.entities.ProductsImages;

@Repository
public interface Products_ImagesRepo extends JpaRepository<ProductsImages, Integer>{

}
