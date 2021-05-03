package com.eoptech.shopdoda.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import com.eoptech.shopdoda.entities.User;

public class UserUtils {
	
	// serving for "created by"
	public static Integer getUserId() {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		if (principal instanceof UserDetails) {
			return ((User)principal).getId();
		}
		return null;
	}
}
