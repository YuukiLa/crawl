package com.musiccrawl.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ManageController {

    @GetMapping("/index")
    public String index(){

        return "index";
    }


}
