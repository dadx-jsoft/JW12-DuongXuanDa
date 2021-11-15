package com.eoptech.shopdoda.cotroller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.ApprovedObj;
import com.eoptech.shopdoda.dto.search.ReviewOrCommentSearch;
import com.eoptech.shopdoda.entities.Review;
import com.eoptech.shopdoda.repositories.ReviewRepo;
import com.eoptech.shopdoda.services.ReviewService;

@Controller
public class ReviewAdminController {
    @Autowired
    private ReviewRepo reviewRepo;

    @Autowired
    private ReviewService reviewService;

    @GetMapping(value = { "/admin/reviews/{id}" })
    public String showReviews(final ModelMap model, final HttpServletRequest request,
            @PathVariable("id") int productId) {
        ReviewOrCommentSearch reviewSearch = new ReviewOrCommentSearch();
        reviewSearch.setIdProductOrBlog(productId);
        reviewSearch.buildPaging(request);
        List<Review> reviews = reviewService.search(reviewSearch);

        model.addAttribute("reviews", reviews);
        model.addAttribute("reviewSearch", reviewSearch);

        return "back-end/user/review";
    }

    // C3: Dùng Ajax
    @PostMapping(value = { "/admin/reviews/approve" })
    public ResponseEntity<AjaxResponse> approveReview(final ModelMap model, @RequestBody ApprovedObj approvedObj) {
        System.out.println(approvedObj.getId());
        Review review = reviewRepo.findById(approvedObj.getId()).get();
        review.setStatus(true);
        reviewRepo.save(review);

        return ResponseEntity.ok(new AjaxResponse(200, "Duyệt Review thành công!"));
    }
}
