package com.eoptech.shopdoda.cotroller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.eoptech.shopdoda.repositories.ContactRepo;

@Controller
@RequestMapping("admin/contacts")
public class ContactAdminController {
	
	@Autowired
	private ContactRepo contactRepo;
	
	@GetMapping
	public String contacts(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		model.addAttribute("contactList", contactRepo.findAll());
		return "back-end/contacts";
	}
	
}
