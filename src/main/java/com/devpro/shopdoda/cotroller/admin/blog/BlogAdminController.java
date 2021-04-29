package com.devpro.shopdoda.cotroller.admin.blog;

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

import com.devpro.shopdoda.dto.search.BlogSearch;
import com.devpro.shopdoda.dto.search.ProductSearch;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.blog.Blog;
import com.devpro.shopdoda.repositories.blog.BlogRepo;
import com.devpro.shopdoda.services.blog.BlogService;
import com.devpro.shopdoda.services.blog.BlogTypeService;

@Controller
public class BlogAdminController {

	@Autowired
	private BlogRepo blogRepo;

	@Autowired
	private BlogService blogService;

	@Autowired
	private BlogTypeService blogTypeSevice;

	@RequestMapping(value = { "admin/blogs" }, method = RequestMethod.GET)
	public String blogs(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		BlogSearch blogSearch = new BlogSearch();
		blogSearch.buildPaging(request);

		List<Blog> blogs = blogService.search(blogSearch);

		model.addAttribute("blogs", blogs);
		model.addAttribute("blogSearch", blogSearch);

		return "back-end/blog/blogs";
	}

	@RequestMapping(value = { "admin/blogs/add" }, method = RequestMethod.GET)
	public String addBlog(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("blog", new Blog());
		model.addAttribute("blogTypes", blogTypeSevice.getBlogTypes());

		return "back-end/blog/save_blog";
	}

	@RequestMapping(value = { "admin/blogs/add" }, method = RequestMethod.POST)
	public String saveBlog(final ModelMap model, @ModelAttribute("blog") Blog blog,
			@RequestParam("avatar_file") MultipartFile avatar) throws Exception {

		blogService.saveOrUpdate(blog, avatar);

		return "redirect:/admin/blogs";
	}

	@RequestMapping(value = { "admin/blogs/edit/{id}" }, method = RequestMethod.GET)
	public String editProduct(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int blogId) throws Exception {

		model.addAttribute("blog", blogRepo.findById(blogId).get());
		model.addAttribute("blogTypes", blogTypeSevice.getBlogTypes());

		return "back-end/blog/save_blog";
	}

	@RequestMapping(value = { "admin/blogs/delete/{id}" }, method = RequestMethod.GET)
	public String deleteProduct(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int blogId) throws Exception {
		Blog deletedBlog = blogRepo.findById(blogId).get();
		deletedBlog.setStatus(false);
		blogRepo.save(deletedBlog);

		return "redirect:/admin/blogs";
	}
	
	@RequestMapping(value = { "admin/blogs/search-all" }, method = RequestMethod.GET)
	public String searchAll(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		BlogSearch blogSearch = new BlogSearch();
		blogSearch.buildPaging(request);

		blogSearch.setSearchText(request.getParameter("searchText"));
		List<Blog> blogs = blogService.search(blogSearch);
		
		
		model.addAttribute("blogs", blogs);
		model.addAttribute("blogSearch", blogSearch);

		return "back-end/blog/blogs";
	}
}
