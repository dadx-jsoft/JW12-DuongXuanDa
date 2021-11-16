package com.eoptech.shopdoda.cotroller.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutController {

    @GetMapping(value = { "/about" })
    public String about() {
        return "front-end/about";
    }
    
}
