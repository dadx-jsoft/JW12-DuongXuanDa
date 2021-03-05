package com.devpro.shopdoda.cotroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BlogController {
	@RequestMapping(value = { "/blog" }, method = RequestMethod.GET)
	public String blog(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "front-end/blog";
	}

	@RequestMapping(value = { "/blog-detail" }, method = RequestMethod.GET)
	public String blog_detail(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response) throws Exception {

		return "front-end/blog_detail";
	}
}
