package com.eoptech.shopdoda.cotroller.client;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.dto.search.ProductSearch;
import com.eoptech.shopdoda.entities.Product;
import com.eoptech.shopdoda.entities.Shop;
import com.eoptech.shopdoda.repositories.ShopRepo;
import com.eoptech.shopdoda.services.ProductService;

@Controller
public class HomeController extends BaseController {

	@Autowired
	private ProductService productService;

	@Autowired
	private ShopRepo shopRepo;
	/**
	 * @param model    - Dùng để đẩy dữ liệu hoặc hứng dữ liệu từ tầng VIEW.
	 * @param request  - Các thông tin người dùng yêu cầu.
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/", "/home", "/trangchu" }, method = RequestMethod.GET)
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

//		Categories parent = categoriesRepo.getOne(16);
//		System.out.println("parents: "+parent.getName());
//		for (Categories child : parent.getChilds()) {
//			System.out.println("child: " + child.getName());
//		}
//		model.addAttribute("categories", categoriesRepo.findAll());
		model.addAttribute("menu", buildMenu());

		ProductSearch productSearch = new ProductSearch();
		productSearch.buildPaging(request);

		List<Product> products = productService.search(productSearch);

		model.addAttribute("products", products);
		model.addAttribute("productSearch", productSearch);
		model.addAttribute("bestSellingProducts", productService.getBestSellingProducts());

		// Thông tin cửa hàng
		Shop shopInfo = (Shop) shopRepo.findAll().get(0);

		return "front-end/index";
	}

}
