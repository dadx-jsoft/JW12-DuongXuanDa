package com.devpro.shopdoda.cotroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.repositories.ProductRepo;

@Controller
public class ProductController {
	@Autowired
	private ProductRepo productRepo;

	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "front-end/products";
	}

	@RequestMapping(value = { "/product-detail/{id}" }, method = RequestMethod.GET)
	public String product_detail(final ModelMap model, @PathVariable("id") String id, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		model.addAttribute("pro_detail", productRepo.findById(Integer.valueOf(id)).get());
		return "front-end/product_detail";
	}

//	@RequestMapping(value = { "/product-detail" }, method = RequestMethod.GET)
//	public String product_detail(final ModelMap model, final HttpServletRequest request,
//			final HttpServletResponse response) throws Exception {
//
//		model.addAttribute("pro_detail", productRepo.findById(49).get());
//		return "front-end/product_detail";
//	}

}
