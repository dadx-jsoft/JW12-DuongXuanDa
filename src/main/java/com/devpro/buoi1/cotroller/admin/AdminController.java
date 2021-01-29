package com.devpro.buoi1.cotroller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.buoi1.dto.Product1;
import com.devpro.buoi1.entities.Product;
import com.devpro.buoi1.repositories.CategoriesRepo;
import com.devpro.buoi1.repositories.ProductRepo;

@Controller
public class AdminController {
	/**
	 * @param model    - Dùng để đẩy dữ liệu hoặc hứng dữ liệu từ tầng VIEW.
	 * @param request  - Các thông tin người dùng yêu cầu.
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/admin", "/dashboard", "/quantri" }, method = RequestMethod.GET)
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/index";
	}

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

	@RequestMapping(value = { "/forgot_password" }, method = RequestMethod.GET)
	public String forgot_password(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "back-end/forgot_password";
	}

	@RequestMapping(value = { "admin/products_images" }, method = RequestMethod.GET)
	public String products_images(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "back-end/products_images";
	}

	@RequestMapping(value = { "admin/roles" }, method = RequestMethod.GET)
	public String roles(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/roles";
	}

	@RequestMapping(value = { "admin/users" }, method = RequestMethod.GET)
	public String users(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/users";
	}

	@RequestMapping(value = { "admin/users_roles" }, method = RequestMethod.GET)
	public String users_roles(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "back-end/users_roles";
	}

	@RequestMapping(value = { "admin/saleorders" }, method = RequestMethod.GET)
	public String saleorders(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/saleorders";
	}

	@RequestMapping(value = { "admin/saleorders_products" }, method = RequestMethod.GET)
	public String saleorders_products(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "back-end/saleorders_products";
	}

	@RequestMapping(value = { "/charts" }, method = RequestMethod.GET)
	public String charts(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/charts";
	}

	@RequestMapping(value = { "admin/blank" }, method = RequestMethod.GET)
	public String blank(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/blank";
	}

}
