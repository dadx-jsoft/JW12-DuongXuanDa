package com.devpro.shopdoda.cotroller.admin;

import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.entities.Role;
import com.devpro.shopdoda.entities.User;
import com.devpro.shopdoda.repositories.UserRepo;
import com.devpro.shopdoda.services.MailService;
import com.devpro.shopdoda.services.RoleService;
import com.devpro.shopdoda.services.UserService;
import com.devpro.shopdoda.utils.GeneratePassword;

@Controller
public class AdminUserController {
	@Autowired
	private UserRepo userRepo;

	@Autowired
	private UserService userService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MailService mailService;

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
		if (password.equals(confirmPassword)) {
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

	@RequestMapping(value = { "/password/forgot" }, method = RequestMethod.GET)
	public String forgotPassword(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "back-end/forgot_password";
	}

	@RequestMapping(value = { "/password/reset" }, method = RequestMethod.POST)
	public String resetPassword(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		String emailResetPass = request.getParameter("emailResetPass");
		// reset pass
		User uResetPass = userRepo.findUserByEmail(emailResetPass);
		if (uResetPass == null) {
			model.addAttribute("messageReSetPass", "Không tồn tại tài khoản có email: " + emailResetPass);
			return "back-end/forgot_password";
		}
		String newPass = String.valueOf(System.currentTimeMillis() / 100);
		uResetPass.setPassword(GeneratePassword.encodePassword(newPass));
		userRepo.save(uResetPass);

		// send mail
		mailService.sendEmailResetPass(emailResetPass, newPass);

		model.addAttribute("messageReSetPass", "Reset password thành công, vui lòng kiếm tra email của bạn!");
		return "back-end/forgot_password";
	}

	@RequestMapping(value = { "admin/users" }, method = RequestMethod.GET)
	public String users(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("users", userService.getAllUsers());

		return "back-end/users";
	}

	@RequestMapping(value = { "/admin/users/add" }, method = RequestMethod.GET)
	public String addUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

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
	public String saveUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@ModelAttribute("userEdit") User userEdit) throws Exception {

		if (userEdit.getId() != null && userEdit.getId() > 0) {
			User userInDB = userRepo.findById(userEdit.getId()).get();
			userEdit.setCreatedDate(userInDB.getCreatedDate());
			userEdit.setPassword(userInDB.getPassword());
		}

		userEdit.setUpdatedDate(new Date());

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
