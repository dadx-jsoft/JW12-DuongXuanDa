package com.devpro.buoi1.cotroller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.devpro.buoi1.repositories.CategoriesRepo;

@Controller
@RequestMapping("admin")
public class AdminCategoryController {
	
	@Autowired
	private CategoriesRepo categoriesRepo;
	
	@GetMapping("categories")
	public String categories(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		model.addAttribute("categoryList", categoriesRepo.findAll());
		
		return "back-end/category";
	}
	
	
	
}
