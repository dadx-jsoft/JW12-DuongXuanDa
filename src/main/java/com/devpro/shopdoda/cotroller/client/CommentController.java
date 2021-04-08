package com.devpro.shopdoda.cotroller.client;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.dto.AjaxResponse;
import com.devpro.shopdoda.dto.CommentDto;
import com.devpro.shopdoda.entities.Comment;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.User;
import com.devpro.shopdoda.repositories.CommentRepo;
import com.devpro.shopdoda.repositories.ProductRepo;
import com.devpro.shopdoda.services.UserService;

@Controller
public class CommentController {
	
	@Autowired
	private CommentRepo commentRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepo productRepo;
	// C3: Dùng Ajax
	@RequestMapping(value = { "/comments/add" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> addComment(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody CommentDto commentDto) {
		Comment comment = new Comment();
		int userId = commentDto.getUserId();
		User u = userService.loadUserById(userId);
		comment.setUser(u);
		int productId = commentDto.getProductId();
		Product p = productRepo.findById(productId).get();
		comment.setProduct(p);
		comment.setMessage(commentDto.getMessage());
		comment.setStatus(false); // chờ admin duyệt
		comment.setCreatedDate(new Date());
		comment.setUpdatedDate(comment.getCreatedDate());
		comment.setStatus(false);
		
		commentRepo.save(comment);
		
		return ResponseEntity.ok(new AjaxResponse(200, "Comment thành công. Vui lòng chờ quản trị viên phê duyệt comment."));
	}
}
