package com.eoptech.shopdoda.cotroller.client;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.Subscribe;

@Controller
public class SubscribeController {
	
	@RequestMapping(value = { "/subscribe" }, method = RequestMethod.POST)
	public ResponseEntity<AjaxResponse> subscribe(final ModelMap model, final HttpServletRequest request,
			final HttpServletResponse response, @RequestBody Subscribe subscribe) {

		System.out.println("Subscribe[emailSubscribe]: " + subscribe.getEmail());

		return ResponseEntity.ok(new AjaxResponse(200, "Chúc mừng bạn đã đăng ký nhận ưu đãi thành công"));
	}
	
}
