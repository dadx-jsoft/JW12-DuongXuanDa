package com.devpro.buoi1.cotroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.buoi1.repositories.CategoriesRepo;
import com.devpro.buoi1.repositories.ProductRepo;

@Controller
public class HomeController {

	@Autowired
	private CategoriesRepo categoriesRepo;

	@Autowired
	private ProductRepo productRepo;

	/**
	 * @param model    - Dùng để đẩy dữ liệu hoặc hứng dữ liệu từ tầng VIEW.
	 * @param request  - Các thông tin người dùng yêu cầu.
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/", "/home", "/trangchu" }, method = RequestMethod.GET)
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

//		Categories parent = categoriesRepo.getOne(16);
//		System.out.println("parents: "+parent.getName());
//		for (Categories child : parent.getChilds()) {
//			System.out.println("child: " + child.getName());
//		}

		model.addAttribute("products", productRepo.findAll());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "front-end/index";
	}

	@RequestMapping(value = { "/shopping-cart" }, method = RequestMethod.GET)
	public String shopping_cart(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "front-end/shopping_cart";
	}

}
