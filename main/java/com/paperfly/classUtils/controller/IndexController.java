package com.paperfly.classUtils.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    @RequestMapping("/")
    public String index(Map map){
        return "index";
    }

    @RequestMapping("/admin")
    @PreAuthorize("hasRole('admin')")
    public String admin(Map map){
        return "admin/index";
    }
}
