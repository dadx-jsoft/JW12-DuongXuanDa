package com.devpro.shopdoda.cotroller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.entities.Review;
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

		return "back-end/review";
	}
}
