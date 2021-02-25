package com.devpro.buoi1.cotroller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.buoi1.entities.Product;
import com.devpro.buoi1.repositories.CategoriesRepo;
import com.devpro.buoi1.repositories.ProductRepo;
import com.devpro.buoi1.utils.FileUtil;

@Controller
@RequestMapping("admin")
public class AdminProductController {
	@Autowired
	private CategoriesRepo categoriesRepo;

	@Autowired
	private ProductRepo productRepo;

	@GetMapping("add-product")
	public String add_product_get(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/save_product";
	}

	@PostMapping("add-product")
	public String add_product_post(final ModelMap model, @ModelAttribute("product") Product product,
			@RequestParam("avatar_file") MultipartFile file) throws Exception {

		product.setCreatedDate(new Date());
		product.setAvatar(file.getOriginalFilename());
		FileUtil.doUploadFile("src/main/webapp/resources/img/products", file);
		
		productRepo.save(product);

		model.addAttribute("message", "Lưu thành công!");
		model.addAttribute("product", new Product());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/save_product";
	}

	@GetMapping("products")
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("productList", productRepo.findAll());
		model.addAttribute("categories", categoriesRepo.findAll());

		return "back-end/products";
	}
}
