package com.eoptech.shopdoda.cotroller.client;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.ReviewDto;
import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.entities.Review;
import com.eoptech.shopdoda.entities.Saleorder;
import com.eoptech.shopdoda.entities.SaleorderProduct;
import com.eoptech.shopdoda.entities.User;
import com.eoptech.shopdoda.repositories.ProductRepo;
import com.eoptech.shopdoda.repositories.ReviewRepo;
import com.eoptech.shopdoda.services.SaleorderProductService;
import com.eoptech.shopdoda.services.SaleorderService;
import com.eoptech.shopdoda.services.UserService;

@Controller
public class ReviewController {
	
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private SaleorderService saleorderService;
	
	@Autowired
	private SaleorderProductService saleorderProductService;
	// C3: Dùng Ajax
	@RequestMapping(value = { "/reviews/add" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> addReview(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody ReviewDto reviewDto) {
		Review review = new Review();
		int userId = reviewDto.getUserId();
		int productId = reviewDto.getProductOrBlogId();
		
		// Lấy ra danh sách saleorder của user này
		List<Saleorder> saleorders = saleorderService.findSaleordersByUserId(userId);
		// Lấy ra các sản phẩm user này đã từng mua
		List<SaleorderProduct> saleorderProducts = new ArrayList<SaleorderProduct>();
		for (Saleorder saleorder : saleorders) {
			saleorderProducts.addAll(saleorderProductService.findAllBySaleorderId(saleorder.getId()));
		}
		
		boolean checkBought = false;
		for (SaleorderProduct saleorderProduct : saleorderProducts) {
			if(saleorderProduct.getProduct().getId() == productId) {
				checkBought = true;
				break;
			}
		}
		
		if(checkBought) {
			User u = userService.findById(userId);
			review.setUser(u);
			
			Product p = productRepo.findById(productId).get();
			review.setProduct(p);
			review.setMessage(reviewDto.getMessage());
			review.setStatus(false); // chờ admin duyệt
			review.setCreatedDate(new Date());
			review.setUpdatedDate(review.getCreatedDate());
			review.setStatus(false);
			
			reviewRepo.save(review);
			
			return ResponseEntity.ok(new AjaxResponse(200, "Review thành công. Vui lòng chờ quản trị viên phê duyệt."));
		}else {
			return ResponseEntity.ok(new AjaxResponse(200, "Bạn phải mua sản phẩm mới có thể đánh giá."));
		}
	}
}
