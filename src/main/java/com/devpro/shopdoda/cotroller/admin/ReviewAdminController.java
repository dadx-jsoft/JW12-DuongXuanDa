package com.devpro.shopdoda.cotroller.admin;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.dto.AjaxResponse;
import com.devpro.shopdoda.dto.ApprovedObj;
import com.devpro.shopdoda.dto.ReviewDto;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.entities.Review;
import com.devpro.shopdoda.entities.User;
import com.devpro.shopdoda.repositories.ReviewRepo;

@Controller
public class ReviewAdminController {
	@Autowired
	private ReviewRepo reviewRepo;

	@RequestMapping(value = { "/admin/reviews" }, method = RequestMethod.GET)
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		List<Review> reviews = reviewRepo.findAll();
		model.addAttribute("reviews", reviews);

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
