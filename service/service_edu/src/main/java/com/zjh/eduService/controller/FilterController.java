package com.zjh.eduService.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("testFilter")
public class FilterController {

    @RequestMapping("result/{name}")
    public String result() {

        return null;
    }
}
