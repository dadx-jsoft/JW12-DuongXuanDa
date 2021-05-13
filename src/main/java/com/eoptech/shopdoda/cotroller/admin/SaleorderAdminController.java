package com.eoptech.shopdoda.cotroller.admin;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.ApprovedObj;
import com.eoptech.shopdoda.dto.search.SaleorderSearch;
import com.eoptech.shopdoda.entities.Saleorder;
import com.eoptech.shopdoda.entities.SaleorderProduct;
import com.eoptech.shopdoda.repositories.SaleorderRepo;
import com.eoptech.shopdoda.services.SaleorderService;

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
		saleorderRepo.save(saleorder);
		
		return "redirect:/admin/saleorders";
	}

	// C3: Dùng Ajax
		@RequestMapping(value = { "/admin/saleorders/approve" }, method = RequestMethod.POST)
		public ResponseEntity<AjaxResponse> approveSaleorder(final ModelMap model, final HttpServletRequest request,
				final HttpServletResponse response, @RequestBody ApprovedObj approvedObj) {
			
			Saleorder saleorder = saleorderRepo.findById(approvedObj.getId()).get();
			saleorder.setOrderStatus(1);
			saleorderRepo.save(saleorder);

			return ResponseEntity.ok(new AjaxResponse(200, "Duyệt đơn thành công!"));
		}
		
		@RequestMapping(value = { "/admin/saleorders/delivery" }, method = RequestMethod.POST)
		public ResponseEntity<AjaxResponse> deliverySaleorder(final ModelMap model, final HttpServletRequest request,
				final HttpServletResponse response, @RequestBody ApprovedObj approvedObj) {
			
			Saleorder saleorder = saleorderRepo.findById(approvedObj.getId()).get();
			saleorder.setOrderStatus(2);
			saleorderRepo.save(saleorder);

			return ResponseEntity.ok(new AjaxResponse(200, "Giao hàng thành công!"));
		}
		
		@RequestMapping(value = { "/admin/saleorders/complete" }, method = RequestMethod.POST)
		public ResponseEntity<AjaxResponse> completeSaleorder(final ModelMap model, final HttpServletRequest request,
				final HttpServletResponse response, @RequestBody ApprovedObj approvedObj) {
			
			Saleorder saleorder = saleorderRepo.findById(approvedObj.getId()).get();
			saleorder.setOrderStatus(3);
			saleorderRepo.save(saleorder);

			return ResponseEntity.ok(new AjaxResponse(200, "Hoàn thành đơn hàng thành công!"));
		}
}
