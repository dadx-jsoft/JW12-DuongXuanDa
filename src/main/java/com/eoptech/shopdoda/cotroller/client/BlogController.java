package com.eoptech.shopdoda.cotroller.client;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.ReviewDto;
import com.eoptech.shopdoda.dto.search.BlogSearch;
import com.eoptech.shopdoda.entities.User;
import com.eoptech.shopdoda.entities.blog.Blog;
import com.eoptech.shopdoda.entities.blog.BlogComment;
import com.eoptech.shopdoda.repositories.blog.BlogCommentRepo;
import com.eoptech.shopdoda.repositories.blog.BlogRepo;
import com.eoptech.shopdoda.services.UserService;
import com.eoptech.shopdoda.services.blog.BlogCommentService;
import com.eoptech.shopdoda.services.blog.BlogService;
import com.eoptech.shopdoda.services.blog.BlogTypeService;

@Controller
public class BlogController {

	@Autowired
	private BlogRepo blogRepo;
	@Autowired
	private BlogCommentRepo blogCommentRepo;
	@Autowired
	private BlogCommentService blogCommentService;

	@Autowired
	private BlogService blogService;
	@Autowired
	private BlogTypeService blogTypeService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value = { "/blog" }, method = RequestMethod.GET)
	public String blogs(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		BlogSearch blogSearch = new BlogSearch();
		blogSearch.buildPaging(request);
		
		List<Blog> blogs = blogService.search(blogSearch);
		
		model.addAttribute("blogs", blogs);
		model.addAttribute("blogSearch", blogSearch);
		
		model.addAttribute("blogTypes", blogTypeService.getBlogTypes());
		
		return "front-end/blog";
	}

	@RequestMapping(value = { "/blog-detail/{seoPath}" }, method = RequestMethod.GET)
	public String blogDetail(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("seoPath") String seoPath) throws Exception {
		BlogSearch blogSearch = new BlogSearch();
		blogSearch.setSeo(seoPath);
		Blog blog = blogService.search(blogSearch).get(0);
		
		blog.setViews(blog.getViews() + 1);
		blogRepo.save(blog);
		blog = blogService.search(blogSearch).get(0);
		model.addAttribute("blog", blog);
		model.addAttribute("comments", blogCommentService.findByBlog(blog));
		
		model.addAttribute("blogTypes", blogTypeService.getBlogTypes());
		
		return "front-end/blog_detail";
	}
	
	@RequestMapping(value = { "/blog-type/{seoPath}" }, method = RequestMethod.GET)
	public String findBlogByBlogType(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@PathVariable("seoPath") String seoPath) throws Exception {
		BlogSearch blogSearch = new BlogSearch();
		blogSearch.buildPaging(request);
		blogSearch.setBlogTypeSeo(seoPath);
		
		List<Blog> blogs = blogService.search(blogSearch);
		
		model.addAttribute("blogs", blogs);
		model.addAttribute("blogSearch", blogSearch);
		
		model.addAttribute("blogTypes", blogTypeService.getBlogTypes());
		
		return "front-end/blog";
	}

	// C3: Dùng Ajax
	@RequestMapping(value = { "/blog/comments/add" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> addComment(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody ReviewDto commentDto) {
		BlogComment comment = new BlogComment();

		int userId = commentDto.getUserId();
		User u = userService.findById(userId);
		comment.setUser(u);
		int blogId = commentDto.getProductOrBlogId();
		Blog b = blogRepo.findById(blogId).get();

		comment.setBlog(b);
		comment.setComment(commentDto.getMessage());
		comment.setStatus(false); // chờ admin duyệt
		comment.setCreatedDate(new Date());
		comment.setUpdatedDate(comment.getCreatedDate());
		comment.setStatus(false);

		blogCommentRepo.save(comment);

		return ResponseEntity.ok(new AjaxResponse(200, "Comment thành công. Vui lòng chờ quản trị viên phê duyệt."));
	}
}
