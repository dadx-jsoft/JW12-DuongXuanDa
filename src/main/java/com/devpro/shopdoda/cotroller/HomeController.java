package com.devpro.shopdoda.cotroller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.shopdoda.dto.ProductSearch;
import com.devpro.shopdoda.entities.Product;
import com.devpro.shopdoda.repositories.CategoriesRepo;
import com.devpro.shopdoda.repositories.ProductRepo;
import com.devpro.shopdoda.services.ProductService;

@Controller
public class HomeController extends BaseController{

	@Autowired
	private CategoriesRepo categoriesRepo;

	@Autowired
	private ProductRepo productRepo;
	
	@Autowired
	private ProductService productService;

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
		
		return "front-end/index";
	}

	

}
