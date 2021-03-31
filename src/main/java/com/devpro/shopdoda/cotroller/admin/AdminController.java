package com.devpro.shopdoda.cotroller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.dto.Product1;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.repositories.CategoriesRepo;
import com.devpro.shopdoda.repositories.ProductRepo;

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


	@RequestMapping(value = { "/forgot_password" }, method = RequestMethod.GET)
	public String forgot_password(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "back-end/forgot_password";
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
