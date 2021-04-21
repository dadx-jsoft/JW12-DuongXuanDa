package com.devpro.shopdoda.cotroller.client;

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

import com.devpro.shopdoda.dto.AjaxResponse;
import com.devpro.shopdoda.dto.ReviewDto;
import com.devpro.shopdoda.dto.search.BlogSearch;
import com.devpro.shopdoda.dto.search.ProductSearch;
import com.devpro.shopdoda.entities.User;
import com.devpro.shopdoda.entities.blog.Blog;
import com.devpro.shopdoda.entities.blog.BlogComment;
import com.devpro.shopdoda.repositories.blog.BlogCommentRepo;
import com.devpro.shopdoda.repositories.blog.BlogRepo;
import com.devpro.shopdoda.services.UserService;
import com.devpro.shopdoda.services.blog.BlogCommentService;
import com.devpro.shopdoda.services.blog.BlogService;

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
	private UserService userService;

	@RequestMapping(value = { "/blog" }, method = RequestMethod.GET)
	public String blogs(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		BlogSearch blogSearch = new BlogSearch();
		blogSearch.buildPaging(request);
		
		
		model.addAttribute("blogs", blogService.search(blogSearch));

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
		
		return "front-end/blog_detail";
	}

	// C3: Dùng Ajax
	@RequestMapping(value = { "/blog/comments/add" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> addComment(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody ReviewDto commentDto) {
		BlogComment comment = new BlogComment();

		int userId = commentDto.getUserId();
		User u = userService.loadUserById(userId);
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
