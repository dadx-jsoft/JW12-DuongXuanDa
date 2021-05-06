package com.eoptech.shopdoda.cotroller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.eoptech.shopdoda.dto.search.ProductSearch;
import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.repositories.ProductRepo;
import com.eoptech.shopdoda.services.CategoriesService;
import com.eoptech.shopdoda.services.ProductService;

@Controller
public class ProductAdminController {
	@Autowired
	private CategoriesService categoriesService;

	@Autowired
	private ProductRepo productRepo;

	@Autowired
	private ProductService productService;

	@RequestMapping(value = { "admin/products" }, method = RequestMethod.GET)
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		List<Product> productList = productService.search(productSearch);

		model.addAttribute("productList", productList);
		model.addAttribute("productSearch", productSearch);

//		model.addAttribute("categories", categoriesService.getCategories());

		return "back-end/products";
	}

	@RequestMapping(value = { "admin/products/add" }, method = RequestMethod.GET)
	public String addProduct(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoriesService.getCategories());

		return "back-end/save_product";
	}

	@RequestMapping(value = { "admin/products/add" }, method = RequestMethod.POST)
	public String saveProduct(final ModelMap model, @ModelAttribute("product") Product product,
			@RequestParam("avatar_file") MultipartFile avatar,
			@RequestParam("listProductImage") MultipartFile[] listProductImage) throws Exception {

		productService.saveOrUpdate(product, avatar, listProductImage);

		return "redirect:/admin/products";
	}

	@RequestMapping(value = { "admin/products/edit/{id}" }, method = RequestMethod.GET)
	public String editProduct(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int productId) throws Exception {

		model.addAttribute("product", productRepo.findById(productId).get());
		model.addAttribute("categories", categoriesService.getCategories());

		return "back-end/save_product";
	}

	@RequestMapping(value = { "admin/products/delete/{id}" }, method = RequestMethod.GET)
	public String deleteProduct(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int productId) throws Exception {

		Product deletedProduct = productRepo.findById(productId).get();
		deletedProduct.setStatus(false);
		productRepo.save(deletedProduct);
//		System.out.println("Delete product success");

		return "redirect:/admin/products";
	}

	@RequestMapping(value = { "admin/products/search-all" }, method = RequestMethod.GET)
	public String searchAll(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		productSearch.setSearchText(request.getParameter("searchText")); // name input
		List<Product> productList = productService.search(productSearch);

		model.addAttribute("productList", productList);
		model.addAttribute("productSearch", productSearch);

		return "back-end/products";
	}

}
