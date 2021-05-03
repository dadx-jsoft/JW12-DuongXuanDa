package com.devpro.shopdoda.cotroller.admin;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.services.SaleorderService;

@Controller
public class AdminController {
	
	@Autowired
	private SaleorderService saleorderService;
	
	/**
	 * @param model    - Dùng để đẩy dữ liệu hoặc hứng dữ liệu từ tầng VIEW.
	 * @param request  - Các thông tin người dùng yêu cầu.
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/admin", "/dashboard", "/quantri" }, method = RequestMethod.GET)
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		/*
		 * StringBuilder myData = new StringBuilder(); myData.append("["); for (int i =
		 * 1; i <= 11; i++) { myData.append("1000"+","); } myData.append("1000");
		 * myData.append("]");
		 * 
		 * int[] mydata = new int[12]; for (int i = 0; i <= 11; i++) { mydata[i] = 1000;
		 * }
		 */
		
		model.addAttribute("myData", saleorderService.thongKeTheoThang());
		model.addAttribute("doanhThuNam", saleorderService.doanhThuNam());
		model.addAttribute("doanhThuThang", saleorderService.doanhThuThang());
		model.addAttribute("doanhThuNgay", saleorderService.doanhThuNgay());
		return "back-end/index";
	}

	@RequestMapping(value = { "/charts" }, method = RequestMethod.GET)
	public String charts(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		return "back-end/charts";
	}

}
