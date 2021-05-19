package com.eoptech.shopdoda.cotroller.client;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.ContactDto;
import com.eoptech.shopdoda.entities.Contact;
import com.eoptech.shopdoda.entities.Shop;
import com.eoptech.shopdoda.repositories.ShopRepo;

@Controller
public class ContactController {
	
	@Autowired
	private ShopRepo shopRepo;

	@RequestMapping(value = { "/contact" }, method = RequestMethod.GET)
	public String details(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		model.addAttribute("contact", new ContactDto());

		// Thông tin cửa hàng
		Shop shopInfo = (Shop) shopRepo.findAll().get(0);
		model.addAttribute("shopInfo", shopInfo);

		return "front-end/contact";
	}

	/* Các cách đẩy dữ liệu lên server */
	// C1: Dùng HttpServletRequest
	@RequestMapping(value = { "/contact1" }, method = RequestMethod.POST)
	public String contact1(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response)
			throws Exception {

		String email = request.getParameter("email");
		System.out.println("Email:" + email);

		String msg = request.getParameter("msg");
		System.out.println("Message:" + msg);

		return "front-end/contact";
	}

	/* C2: Dùng srping form */
	@RequestMapping(value = { "/contact2" }, method = RequestMethod.POST)
	public String contact2(final ModelMap model, final HttpServletRequest request, final HttpServletResponse response,
			@ModelAttribute("contact") ContactDto contact) throws Exception {

		System.out.println("Contact[email]: " + contact.getEmail());
		System.out.println("Contact[Msg]: " + contact.getMsg());

		return "front-end/contact";
	}

	// C3: Dùng Ajax
	@RequestMapping(value = { "/contact3" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> contact3(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody ContactDto contactDto) {

//		Contact lh = contactService.saveContact(contact);
//		if (lh != null) {
//			return ResponseEntity.ok(new AjaxResponse(200, "The your message is sent to Administrator."));
//		} else {
//			return ResponseEntity.badRequest().body(new AjaxResponse(200, "Your message is corrupted when sending to Administrator."));
//		}

		Contact contact = new Contact();

		contact.setFullName(contactDto.getFullName());
		contact.setEmail(contactDto.getEmail());
		contact.setMessage(contactDto.getMsg());

		contact.setRequestType("CONTACT");
		contact.setCreatedDate(new Date());
		contact.setUpdatedDate(contact.getCreatedDate());

//		contactRepo.save(contact);

		return ResponseEntity.ok(new AjaxResponse(200, "Thành công"));
	}
}
