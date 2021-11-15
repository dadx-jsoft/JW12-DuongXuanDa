package com.eoptech.shopdoda.cotroller.client;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.eoptech.shopdoda.dto.AjaxResponse;
import com.eoptech.shopdoda.dto.Subscribe;

@Controller
public class SubscribeController {

    @PostMapping(value = { "/subscribe" })
    public ResponseEntity<AjaxResponse> subscribe(@RequestBody Subscribe subscribe) {

        System.out.println("Subscribe[emailSubscribe]: " + subscribe.getEmail());

        return ResponseEntity.ok(new AjaxResponse(200, "Chúc mừng bạn đã đăng ký nhận ưu đãi thành công"));
    }

}
