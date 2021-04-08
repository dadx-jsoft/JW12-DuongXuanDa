package com.devpro.shopdoda.services.fb;

import java.util.ArrayList;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.Connection;
import org.springframework.social.connect.ConnectionSignUp;
import org.springframework.stereotype.Service;

import com.devpro.shopdoda.entities.Role;
import com.devpro.shopdoda.entities.User;
import com.devpro.shopdoda.repositories.UserRepo;
import com.devpro.shopdoda.services.RoleService;
import com.devpro.shopdoda.utils.GeneratePassword;

// xử lý đăng ký tài khoản mới đăng nhập Facebook lần đầu.
@Service
public class FacebookConnectionSignup implements ConnectionSignUp {

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private RoleService roleService;

	@Override
	public String execute(Connection<?> connection) {
		User user = new User();
		String userTemp = "userfb_" + (System.currentTimeMillis() / 100);
		String passTemp = "passfb_" + (System.currentTimeMillis() / 100);
		passTemp = "12345678";
		user.setUsername(userTemp);
		user.setPassword(GeneratePassword.encodePassword(passTemp));
		System.out.println("Password temp for facebook login: " + passTemp);
		user.setFullName(connection.getDisplayName());
		user.setCreatedDate(new Date());
//		user.setEmail();
		// Role
		Role role = roleService.getRoleByName("GUEST");
		ArrayList<Role> listRoles = new ArrayList<Role>();
		listRoles.add(role);
		user.setRoles(listRoles);

		userRepo.save(user);
		return user.getUsername();
	}
}