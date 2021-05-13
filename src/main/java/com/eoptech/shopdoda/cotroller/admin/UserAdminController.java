package com.eoptech.shopdoda.cotroller.admin;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eoptech.shopdoda.entities.Role;
import com.eoptech.shopdoda.entities.User;
import com.eoptech.shopdoda.repositories.UserRepo;
import com.eoptech.shopdoda.services.MailService;
import com.eoptech.shopdoda.services.RoleService;
import com.eoptech.shopdoda.services.UserService;
import com.eoptech.shopdoda.utils.GeneratePassword;
import com.eoptech.shopdoda.utils.PathConstant;

@Controller
public class UserAdminController {
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

		return "back-end/user/login";
	}

	// login thành công
	// https://stackoverflow.com/questions/45709333/page-redirecting-depending-on-role-using-spring-security-and-thymeleaf-spring
	@RequestMapping("/success")
	public void loginPageRedirect(HttpServletRequest request, HttpServletResponse response, Authentication authResult)
			throws IOException, ServletException {

		User u = (User) authResult.getPrincipal();
		String role = u.getRoles().get(0).getName();

		if (role.contains("ADMIN")) {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/admin"));
		} else {
			response.sendRedirect(response.encodeRedirectURL(request.getContextPath() + "/home"));
		}
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.GET)
	public String register(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/user/register";
	}

	@RequestMapping(value = { "/register" }, method = RequestMethod.POST)
	public String addAccount(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@RequestParam("userAvatar") MultipartFile userAvatar) throws Exception {

		String fullName = request.getParameter("fullName");
		String email = request.getParameter("email");
		if (userRepo.findUserByEmail(email) != null) {
			model.addAttribute("error", "Email đã tồn tại!");
			return "back-end/user/register";
		}
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		String confirmPassword = request.getParameter("confirmPassword");
		
		if(userName.trim().equals("")) {
			model.addAttribute("error", "Username không được để trống!");
			return "back-end/user/register";
		}
		
		User user = new User();
		user.setUsername(userName);
		if (password.equals(confirmPassword)) {
			user.setPassword(GeneratePassword.encodePassword(password));
		}
		user.setEmail(email);
		user.setCreatedDate(new Date());
		user.setFullName(fullName);

		// avatar
		String avatarPath = "user/avatar/" + userAvatar.getOriginalFilename();
		user.setAvatar(avatarPath);
		userAvatar.transferTo(new File(PathConstant.ROOT_UPLOAD_PATH + avatarPath));

		// Role
		Role role = roleService.getRoleByName("GUEST");
		ArrayList<Role> listRoles = new ArrayList<Role>();
		listRoles.add(role);
		user.setRoles(listRoles);

		userRepo.save(user);

		model.addAttribute("registerSuccess", "Đăng ký tài khoản thành công!!");
		return "back-end/user/register";
	}

	@RequestMapping(value = { "/password/forgot" }, method = RequestMethod.GET)
	public String forgotPassword(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "back-end/user/forgot_password";
	}

	@RequestMapping(value = { "/password/reset" }, method = RequestMethod.POST)
	public String resetPassword(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {
		String emailResetPass = request.getParameter("emailResetPass");
		// reset pass
		User uResetPass = userRepo.findUserByEmail(emailResetPass);
		if (uResetPass == null) {
			model.addAttribute("messageReSetPass", "Không tồn tại tài khoản có email: " + emailResetPass);
			return "back-end/user/forgot_password";
		}
		String newPass = String.valueOf(System.currentTimeMillis() / 100);
		uResetPass.setPassword(GeneratePassword.encodePassword(newPass));
		userRepo.save(uResetPass);

		// send mail
		mailService.sendEmailResetPass(emailResetPass, newPass);

		model.addAttribute("messageReSetPass", "Reset password thành công, vui lòng kiếm tra email của bạn!");
		return "back-end/user/forgot_password";
	}

	@RequestMapping(value = { "admin/users" }, method = RequestMethod.GET)
	public String users(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("users", userService.getAllUsers());

		return "back-end/user/users";
	}

	@RequestMapping(value = { "/admin/users/add" }, method = RequestMethod.GET)
	public String addUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("userEdit", new User());

		return "back-end/user/save_user";
	}

	@RequestMapping(value = { "/admin/users/edit/{id}" }, method = RequestMethod.GET)
	public String editUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("id") Integer userID) throws Exception {

		User userEdit = userRepo.findById(userID).get();

		model.addAttribute("userEdit", userEdit);

		return "back-end/user/save_user";
	}

	@RequestMapping(value = { "/admin/users/save" }, method = RequestMethod.POST)
	public String saveUser(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@ModelAttribute("userEdit") User userEdit) throws Exception {

		if (userEdit.getId() != null && userEdit.getId() > 0) {
			User userInDB = userRepo.findById(userEdit.getId()).get();
			userEdit.setCreatedDate(userInDB.getCreatedDate());
			userEdit.setPassword(userInDB.getPassword());
		} else {
			userEdit.setPassword(GeneratePassword.encodePassword("admin"));
			// Role
			Role role = roleService.getRoleByName("ADMIN");
			ArrayList<Role> listRoles = new ArrayList<Role>();
			listRoles.add(role);
			userEdit.setRoles(listRoles);
		}

		userEdit.setUpdatedDate(new Date());

		if (userService.findByEmail(userEdit.getEmail()) != null) {
			model.addAttribute("saveError", "Email này đã được sử dụng!!");
			return "back-end/user/save_user";
		}

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
