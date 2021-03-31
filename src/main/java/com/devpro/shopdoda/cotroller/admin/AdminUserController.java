package com.devpro.shopdoda.cotroller.admin;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.entities.Role;
import com.devpro.shopdoda.entities.User;
import com.devpro.shopdoda.repositories.UserRepo;
import com.devpro.shopdoda.services.RoleService;
import com.devpro.shopdoda.utils.GeneratePassword;

@Controller
public class AdminUserController {
	@Autowired
	private UserRepo userRepo;
	
	@Autowired
	private RoleService roleService;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public String login(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/login";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String addAccount(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		User user = new User();
		user.setUsername(userName);
		if(password.equals(confirmPassword)) {
			user.setPassword(GeneratePassword.encodePassword(password));
		}
		user.setEmail(email);
		user.setCreatedDate(new Date());
		user.setFullName(fullName);
		
		// Role
		Role role = roleService.getRoleByName("GUEST");
		ArrayList<Role> listRoles = new ArrayList<Role>();
		listRoles.add(role);
		user.setRoles(listRoles);
		
		userRepo.save(user);
		
		return "front-end/index";
	}
	
	@RequestMapping(value = { "admin/users" }, method = RequestMethod.GET)
	public String users(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		model.addAttribute("users", userRepo.findAll());
		
		return "back-end/users";
	}
	
	@RequestMapping(value = { "/admin/users/add" }, method = RequestMethod.GET)
	public String addUser(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		
		model.addAttribute("userEdit", new User());
		
		return "back-end/save_user";
	}
	@RequestMapping(value = { "/admin/users/edit/{id}" }, method = RequestMethod.GET)
	public String editUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("id") Integer userID) throws Exception {
		
		User userEdit = userRepo.findById(userID).get();
		
		model.addAttribute("userEdit", userEdit);
		
		return "back-end/save_user";
	}
	
	@RequestMapping(value = { "/admin/users/save" }, method = RequestMethod.POST)
	public String saveUser(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("userEdit") User userEdit) throws Exception {
		
		userEdit.setCreatedDate(new Date());
		
		userRepo.save(userEdit);

		return "redirect:/admin/users";
	}
	
	@RequestMapping(value = { "/admin/users/delete/{id}" }, method = RequestMethod.GET)
	public String deleteUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("id") Integer userId) throws Exception {
		User deletedUser = userRepo.findById(userId).get();
		deletedUser.setStatus(false);
		userRepo.save(deletedUser);
		
		return "redirect:/admin/users";
	}
	
}
