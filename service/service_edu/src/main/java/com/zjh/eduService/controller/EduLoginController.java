package com.zjh.eduService.controller;

import com.zjh.commonUtils.R;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 前台登入页面控制
 */

@RestController
@RequestMapping("/eduservice/user")
@CrossOrigin    //解决跨域问题
public class EduLoginController {

    @RequestMapping("login")
    public R login() {

        return R.ok().data("token", "admin");

    }

    @RequestMapping("info")
    public R info() {
        return R.ok().data("roles", "[admin]").data("name", "admin").data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }
}
