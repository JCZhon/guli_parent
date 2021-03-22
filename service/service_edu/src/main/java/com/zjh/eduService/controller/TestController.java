package com.zjh.eduService.controller;

import com.zjh.eduService.myAnnotations.MyLogging;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("testController")
@Api("练习当中的一些测试接口")
public class TestController {

    @GetMapping("testAopLogger")
    @ApiOperation("测试aop的日志记录和@RequestParam注解的使用")
    @MyLogging  //让aop的pointcut精确地找到我
    public String testAopLogger(@RequestParam("name") String name) {
        return "hello" + name;
    }

}
