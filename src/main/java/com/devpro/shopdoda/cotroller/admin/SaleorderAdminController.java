package com.devpro.shopdoda.cotroller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.dto.search.SaleorderSearch;
import com.devpro.shopdoda.entities.Saleorder;
import com.devpro.shopdoda.entities.SaleorderProduct;
import com.devpro.shopdoda.repositories.SaleorderRepo;
import com.devpro.shopdoda.services.SaleorderService;

@Controller
public class SaleorderAdminController {
	@Autowired
	private SaleorderRepo saleorderRepo;

	@Autowired
	private SaleorderService saleorderService;

	@RequestMapping(value = { "admin/saleorders" }, method = RequestMethod.GET)
	public String saleorders(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		SaleorderSearch saleorderSearch = new SaleorderSearch();
		saleorderSearch.buildPaging(request);

		List<Saleorder> saleorders = saleorderService.search(saleorderSearch);

		model.addAttribute("saleorders", saleorders);
		model.addAttribute("saleorderSearch", saleorderSearch);

		return "back-end/saleorders";
	}

	@RequestMapping(value = { "admin/saleorders/search-all" }, method = RequestMethod.GET)
	public String searchAll(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		SaleorderSearch saleorderSearch = new SaleorderSearch();
		saleorderSearch.buildPaging(request);
		saleorderSearch.setSearchText(request.getParameter("searchText"));

		List<Saleorder> saleorders = saleorderService.search(saleorderSearch);

		model.addAttribute("saleorders", saleorders);
		model.addAttribute("saleorderSearch", saleorderSearch);

		return "back-end/saleorders";
	}

	@RequestMapping(value = { "admin/saleorders/detail/{id}" }, method = RequestMethod.GET)
	public String saleoderDetail(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int saleorderId) throws Exception {

		Saleorder saleorder = saleorderRepo.findById(saleorderId).get();
		model.addAttribute("saleorder", saleorder);
		
		// lấy danh sách sản phẩm đã order
		List<SaleorderProduct> saleorderProducts = saleorder.getSaleorderProducts();
		model.addAttribute("saleorderProducts", saleorderProducts);
		
		return "back-end/saleorder_detail";
	}
	
	@RequestMapping(value = { "admin/saleorders/delete/{id}" }, method = RequestMethod.GET)
	public String deleteSaleoder(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @PathVariable("id") int saleorderId) throws Exception {

		Saleorder saleorder = saleorderRepo.findById(saleorderId).get();
		saleorder.setStatus(false);
		
		return "back-end/saleorder_detail";
	}

}
