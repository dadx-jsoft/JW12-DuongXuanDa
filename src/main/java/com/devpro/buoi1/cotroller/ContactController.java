package com.devpro.buoi1.cotroller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.buoi1.dto.AjaxResponse;
import com.devpro.buoi1.dto.Contact;

@Controller
public class ContactController {
	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public String details(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		/*
		 * String name = request.getParameter("name"); System.out.println("Name(URL): "+
		 * name);
		 * 
		 * model.addAttribute("name", name);
		 */

		model.addAttribute("contact", new Contact());

		return "front-end/contact";
	}

	@RequestMapping(value = { "/contact1" }, method = RequestMethod.POST)
	public String contact1(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		String email = request.getParameter("email");
		System.out.println("Email:" + email);

		String msg = request.getParameter("msg");
		System.out.println("Message:" + msg);

		return "front-end/contact";
	}

	/* Dùng srping form */
	@RequestMapping(value = { "/contact2" }, method = RequestMethod.POST)
	public String contact2(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@ModelAttribute("contact") Contact contact) throws Exception {

		System.out.println("Contact[email]: " + contact.getEmail());
		System.out.println("Contact[Msg]: " + contact.getMsg());

		return "front-end/contact";
	}

	@RequestMapping(value = { "/contact3" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> contact3(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody Contact contact) {
		
		System.out.println(contact.getEmail() + ": "+ contact.getMsg());
		
		return ResponseEntity.ok(new AjaxResponse(200, "Success"));
	}
}
