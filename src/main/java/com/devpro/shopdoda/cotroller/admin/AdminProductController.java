package com.devpro.shopdoda.cotroller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shopdoda.dto.ProductSearch;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.repositories.CategoriesRepo;
import com.devpro.shopdoda.repositories.ProductRepo;
import com.devpro.shopdoda.services.ProductService;

@Controller
@RequestMapping("admin/products")
public class AdminProductController {
	@Autowired
	private CategoriesRepo categoriesRepo;

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;

	@GetMapping("add")
	public String add_product_get(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/save_product";
	}
	
	@PostMapping("add")
	public String add_product_post(final ModelMap model, @ModelAttribute("product") Product product,
			@RequestParam("avatar_file") MultipartFile avatar, @RequestParam("listProductImage") MultipartFile[] listProductImage) throws Exception {

		product.setCreatedDate(new Date());
		productService.saveOrUpdate(product, avatar, listProductImage);

		return "redirect:/admin/products";
	}

	@GetMapping("edit/{id}")
	public String edit_product_get(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int productId) throws Exception {

		model.addAttribute("product", productRepo.findById(productId).get());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/save_product";
	}
	
	@GetMapping("delete/{id}")
	public String deleteProduct(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int productId) throws Exception {
		
		Product deletedProduct = productRepo.findById(productId).get();
		deletedProduct.setStatus(false);
		productRepo.save(deletedProduct);

		return "redirect:/admin/products";
	}
	
	@GetMapping
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);
		
		List<Product> productList = productService.search(productSearch);
		
		model.addAttribute("productList", productList);
		model.addAttribute("productSearch", productSearch);
		
//		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/products";
	}
	
	@RequestMapping(value = { "/search-all" }, method = RequestMethod.GET)
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
