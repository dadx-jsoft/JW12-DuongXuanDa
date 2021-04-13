package com.devpro.shopdoda.cotroller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.entities.Saleorder;
import com.devpro.shopdoda.repositories.SaleorderRepo;

@Controller
public class SaleorderAdminController {
	@Autowired 
	private SaleorderRepo saleorderRepo;
	
	@RequestMapping(value = { "admin/saleorders" }, method = RequestMethod.GET)
	public String saleorders(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		List<Saleorder> saleorders = saleorderRepo.findAll();
		model.addAttribute("saleorders", saleorders);
		return "back-end/saleorders";
	}
}
