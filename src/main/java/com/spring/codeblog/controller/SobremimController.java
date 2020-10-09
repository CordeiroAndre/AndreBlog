package com.spring.codeblog.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SobremimController {

    @RequestMapping(value = "/sobremim", method = RequestMethod.GET)
    public String getSobreMim(){
        return "sobreMim";
    }

}
