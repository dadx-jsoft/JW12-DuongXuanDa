package com.devpro.buoi1.cotroller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.devpro.buoi1.dto.AjaxResponse;
import com.devpro.buoi1.dto.Contact;
import com.devpro.buoi1.dto.Student;
import com.devpro.buoi1.dto.Subscribe;

@Controller
public class HomeController {

	/**
	 * @param model - Dùng để đẩy dữ liệu hoặc hứng dữ liệu từ tầng VIEW.
	 * @param request - Các thông tin người dùng yêu cầu.
	 * @param response
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = { "/", "/home", "/index", "/trangchu" }, method = RequestMethod.GET)
	public String index(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
		
//		List<Student> students = new ArrayList<Student>();
//		
//		students.add(new Student("Đông Tà", 18, "Nhổn",9.5f));
//		students.add(new Student("Tây Độc", 20, "Mai Dịch",5.7f));
//		students.add(new Student("Nam Đế", 19, "Thái Hà",7));
//		students.add(new Student("Bắc Cái", 22, "Đống Đa",8));
//		students.add(new Student("Dương Xuân Đà", 21, "Đống Đa",9));
//		
//		model.addAttribute("students", students);
		
		return "front-end/index";
	}
	
	@RequestMapping(value = { "/products" }, method = RequestMethod.GET)
	public String products(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
				
		return "front-end/products";
	}
	
	@RequestMapping(value = { "/product_detail" }, method = RequestMethod.GET)
	public String product_detail(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
				
		return "front-end/product_detail";
	}
	
	@RequestMapping(value = { "/shopping_cart" }, method = RequestMethod.GET)
	public String shopping_cart(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
				
		return "front-end/shopping_cart";
	}
	
	@RequestMapping(value = { "/blog" }, method = RequestMethod.GET)
	public String blog(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
				
		return "front-end/blog";
	}
	@RequestMapping(value = { "/blog_detail" }, method = RequestMethod.GET)
	public String blog_detail(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
				
		return "front-end/blog_detail";
	}
	
	@RequestMapping(value = { "/about" }, method = RequestMethod.GET)
	public String about(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {
				
		return "front-end/about";
	}
	
	@RequestMapping(value = { "/subscribe" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> subscribe(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody Subscribe subscribe) {
		
		System.out.println("Subscribe[email]: "+ subscribe.getEmail());
		
		return ResponseEntity.ok(new AjaxResponse(200, "Chúc mừng bạn đã đăng ký nhận ưu đãi thành công"));
	}
	
}
