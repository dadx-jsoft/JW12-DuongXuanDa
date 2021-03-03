package com.devpro.buoi1.cotroller.admin;

import java.util.Date;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.buoi1.entities.Product;
import com.devpro.buoi1.repositories.CategoriesRepo;
import com.devpro.buoi1.repositories.ProductRepo;
import com.devpro.buoi1.services.ProductService;
import com.devpro.buoi1.utils.FileUtil;

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
			@RequestParam("avatar_file") MultipartFile file) throws Exception {

		product.setCreatedDate(new Date());
//		try {
//			product.setAvatar(file.getOriginalFilename());
//			FileUtil.doUploadFile("src/main/resources/img/products", file);
//		} catch (Exception e) {
//			model.addAttribute("message", "Lưu thất bại!");
//			model.addAttribute("product", new Product());
//			model.addAttribute("categories", categoriesRepo.findAll());
//
//			return "back-end/save_product";
//		}
		productService.saveOrUpdate(product, file);

//		model.addAttribute("message", "Lưu thành công!");
//		model.addAttribute("product", new Product());
//		model.addAttribute("categories", categoriesRepo.findAll());

		return "redirect:/admin/products";
//		return "back-end/save_product";
	}

	@GetMapping("edit/{id}")
	public String edit_product_get(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int productId) throws Exception {

		model.addAttribute("product", productRepo.findById(productId).get());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/save_product";
	}
	
	@GetMapping
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/products";
	}
}
