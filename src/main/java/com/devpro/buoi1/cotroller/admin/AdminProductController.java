package com.devpro.buoi1.cotroller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.buoi1.entities.Product;
import com.devpro.buoi1.repositories.CategoriesRepo;
import com.devpro.buoi1.repositories.ProductRepo;

@Controller
public class AdminProductController {
	@Autowired
	private CategoriesRepo categoriesRepo;

	@Autowired
	private ProductRepo productRepo;

	@RequestMapping(value = { "admin/add-product" }, method = RequestMethod.GET)
	public String add_product_get(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/add_product";
	}

	@RequestMapping(value = { "admin/add-product" }, method = RequestMethod.POST)
	public String add_product_post(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("product") Product product) throws Exception {

		System.out.println("AddProduct[name]: " + product.getTitle());
		System.out.println("AddProduct[price]: " + product.getPrice());
		System.out.println("AddProduct[image]: " + product.getAvatar());
		System.out.println("AddProduct[category]: " + product.getCategories().getName());
		System.out.println("AddProduct[categoryId]: " + product.getCategories().getId());

		productRepo.save(product);
		
		model.addAttribute("message", "Lưu thành công!");
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoriesRepo.findAll());
		
		return "back-end/add_product";
	}
	
	@RequestMapping(value = { "admin/products" }, method = RequestMethod.GET)
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/products";
	}
}
