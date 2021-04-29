package com.devpro.shopdoda.cotroller.admin;

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

import com.devpro.shopdoda.dto.AjaxResponse;
import com.devpro.shopdoda.dto.ApprovedObj;
import com.devpro.shopdoda.dto.search.ReviewOrCommentSearch;
import com.devpro.shopdoda.entities.Review;
import com.devpro.shopdoda.repositories.ReviewRepo;
import com.devpro.shopdoda.services.ReviewService;

@Controller
public class ReviewAdminController {
	@Autowired
	private ReviewRepo reviewRepo;
	
	@Autowired
	private ReviewService reviewService;
	
	@RequestMapping(value = { "/admin/reviews" }, method = RequestMethod.GET)
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		ReviewOrCommentSearch reviewSearch = new ReviewOrCommentSearch();
		reviewSearch.buildPaging(request);
		List<Review> reviews = reviewService.search(reviewSearch);
		
		model.addAttribute("reviews", reviews);
		model.addAttribute("reviewSearch", reviewSearch);
		
		return "back-end/user/review";
	}

	// C3: Dùng Ajax
	@RequestMapping(value = { "/admin/reviews/approve" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> approveReview(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody ApprovedObj approvedObj) {
		System.out.println(approvedObj.getId());
		Review review = reviewRepo.findById(approvedObj.getId()).get();
		review.setStatus(true);
		reviewRepo.save(review);

		return ResponseEntity.ok(new AjaxResponse(200, "Duyệt Review thành công!"));
	}
}
