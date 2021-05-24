package com.eoptech.shopdoda.cotroller.admin;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.entities.Saleorder;
import com.eoptech.shopdoda.entities.Shop;
import com.eoptech.shopdoda.repositories.ShopRepo;
import com.eoptech.shopdoda.services.SaleorderService;
import com.eoptech.shopdoda.utils.SaleorderExcelExporter;

@Controller
public class AdminController {
	
	@Autowired
	private SaleorderService saleorderService;
	@Autowired
	private ShopRepo shopRepo;
	
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

	// https://www.codejava.net/frameworks/spring-boot/export-data-to-excel-example
	@RequestMapping(value = {"/admin/export/excel"}, method = RequestMethod.GET)
    public void exportToExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=saleorders_" + currentDateTime + ".xlsx";
        response.setHeader(headerKey, headerValue);
         
        List<Saleorder> listSaleorders = saleorderService.getAllByMonth();
         
        SaleorderExcelExporter excelExporter = new SaleorderExcelExporter(listSaleorders);
         
        
        excelExporter.export(response);    
    }
	
	@RequestMapping(value = { "admin/shop/info" }, method = RequestMethod.GET)
	public String shopInfo(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
		Shop shopInfo = shopRepo.findAll().get(0);
		model.addAttribute("shopInfo", shopInfo);
		
		return "back-end/shop_info";
	}
	
	@RequestMapping(value = { "admin/shop/info" }, method = RequestMethod.POST)
	public String saveShopInfo(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response, 
			@ModelAttribute("shopInfo") Shop shopInfo)
			throws Exception {
		
		shopRepo.save(shopInfo);
		model.addAttribute("saveShopInfoSuccess", "Đã lưu!");
		
		return "back-end/shop_info";
	}

}
