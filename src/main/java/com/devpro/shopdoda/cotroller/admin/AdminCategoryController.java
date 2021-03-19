package com.devpro.shopdoda.cotroller.admin;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.devpro.shopdoda.entities.Category;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.repositories.CategoriesRepo;

@Controller
public class AdminCategoryController {

	@Autowired
	private CategoriesRepo categoriesRepo;

	@RequestMapping(value = { "/admin/categories" }, method = RequestMethod.GET)
	public String categories(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("categoryList", categoriesRepo.findAll());

		return "back-end/category";
	}

	@RequestMapping(value = { "/admin/categories/edit/{id}" }, method = RequestMethod.GET)
	public String categories(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("id") Integer CategoryId) throws Exception {
		
		Category cat = categoriesRepo.findById(CategoryId).get();
		
		model.addAttribute("categoryEdit", cat);
		
		return "back-end/save_category";
	}

	@RequestMapping(value = { "/admin/categories/save" }, method = RequestMethod.POST)
	public String add_category(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @ModelAttribute("categoryEdit") Category categoryEdit) throws Exception {
		categoryEdit.setCreatedDate(new Date());
		categoriesRepo.save(categoryEdit);

		return "redirect:/admin/categories";
	}
	
	@RequestMapping(value = { "/admin/categories/delete/{id}" }, method = RequestMethod.GET)
	public String deleteCategory(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("id") Integer CategoryId) throws Exception {
		Category deletedCategory = categoriesRepo.findById(CategoryId).get();
		deletedCategory.setStatus(false);
		categoriesRepo.save(deletedCategory);
		
		return "redirect:/back-end/categories";
	}
	

}
