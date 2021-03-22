package com.zjh.eduCms.controller;

import com.zjh.commonUtils.R;
import com.zjh.eduCms.entity.EduBanner;
import com.zjh.eduCms.service.EduBannerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 前台页面显示-前台部分
 */
@RestController
@RequestMapping("eduCms/bannerFront")
@Api("网站首页banner列表")
@CrossOrigin
public class BannerFrontController {

    @Autowired
    private EduBannerService eduBannerService;

    @ApiOperation("获取首页Banner")
    @GetMapping("getBanner")
    public R getBanner() {
        List<EduBanner> list = eduBannerService.list(null);
        return R.ok().data("bannerList", list);
    }
}
