package com.devpro.buoi1.cotroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.buoi1.dto.Product;

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

	@RequestMapping(value = { "products_admin" }, method = RequestMethod.GET)
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/products";
	}

	@RequestMapping(value = { "/charts" }, method = RequestMethod.GET)
	public String charts(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/charts";
	}

	@RequestMapping(value = { "/blank" }, method = RequestMethod.GET)
	public String blank(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/blank";
	}

	@RequestMapping(value = { "/add_product" }, method = RequestMethod.GET)
	public String add_product_get(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		model.addAttribute("product", new Product());

		return "back-end/add_product";
	}

	@RequestMapping(value = { "/add_product" }, method = RequestMethod.POST)
	public String add_product_post(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("product") Product product) throws Exception {

		System.out.println("AddProduct[name]: "+product.getName());
		System.out.println("AddProduct[price]: "+product.getPrice());
		System.out.println("AddProduct[quantity]: "+product.getQuantity());
		System.out.println("AddProduct[image]: "+product.getImage());
		System.out.println("AddProduct[description]: "+product.getDescription());
		
		return "back-end/add_product";
	}

}
