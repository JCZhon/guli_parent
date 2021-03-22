package com.zjh.eduCms.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.zjh.commonUtils.R;
import com.zjh.eduCms.entity.EduBanner;
import com.zjh.eduCms.service.EduBannerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前台页面显示-后台部分
 * </p>
 *
 * @author zjh
 * @since 2021-01-03
 */
@RestController
@RequestMapping("/eduCms/bannerAdmin")
@CrossOrigin
public class BannerAdminController {

    @Autowired
    private EduBannerService eduBannerService;

    /**
     * 获取banner分页列表
     */
    @GetMapping("getBannerPage/{page}{limit}")
    @ApiOperation("获取banner列表")
    public R getBannerPage(@ApiParam(name = "page", value = "当前页码") @PathVariable long page,
                           @ApiParam(name = "limit", value = "每页记录数") @PathVariable long limit) {
        Page<EduBanner> pageParam = new Page<>(page, limit);
        eduBannerService.page(pageParam, null);
        return R.ok().data("items", pageParam.getRecords()).data("total", pageParam.getTotal());
    }

    /**
     * 获取banner
     */
    @GetMapping("getBanner/{id}")
    @ApiOperation("获取banner")
    public R getBanner(@PathVariable long id) {
        EduBanner banner = eduBannerService.getById(id);
        return R.ok().data("items", banner);
    }

    @PostMapping("saveBanner")
    @ApiOperation("新增banner")
    public R saveBanner(@RequestBody EduBanner eduBanner) {
        eduBannerService.save(eduBanner);
        return R.ok();
    }

    @GetMapping("deleteBanner/{id}")
    @ApiOperation("删除banner")
    public R deleteBanner(@PathVariable long id) {
        eduBannerService.removeById(id);
        return R.ok();
    }

    @PostMapping("updateBanner")
    @ApiOperation("修改banner")
    public R updateBanner(@RequestBody EduBanner eduBanner) {
        eduBannerService.updateById(eduBanner);
        return R.ok();
    }


}

