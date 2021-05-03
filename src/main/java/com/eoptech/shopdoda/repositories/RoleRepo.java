package com.eoptech.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.Role;

@Repository
public interface RoleRepo extends JpaRepository<Role, Integer> {

}
