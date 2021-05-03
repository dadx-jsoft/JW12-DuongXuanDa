package com.eoptech.shopdoda.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.eoptech.shopdoda.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer>{
	@Query(value="select * from tbl_users u where u.status = true and u.email= :email",nativeQuery = true)
	public User findUserByEmail(String email);
}
