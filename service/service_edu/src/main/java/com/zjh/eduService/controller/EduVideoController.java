package com.zjh.eduService.controller;


import com.sun.org.apache.bcel.internal.util.BCELifier;
import com.zjh.commonUtils.R;
import com.zjh.eduService.entity.EduVideo;
import com.zjh.eduService.service.EduVideoService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 课程（小节）视频
 * </p>
 *
 * @author zjh
 * @since 2020-12-23
 */
@RestController
@RequestMapping("/eduService/edu-video")
public class EduVideoController {

    @Autowired
    private EduVideoService eduVideoService;

    /**
     * 添加课程小节
     */
    @PostMapping("addVideo")
    @ApiOperation("添加课程小节")
    public R addVideo(@RequestBody EduVideo eduVideo) {
        boolean flag = eduVideoService.save(eduVideo);
        return R.judgeByBo(flag);
    }

    /**
     * 删除课程小节
     * 这个方法做到：在删除小节的时候，同时把对应的阿里云里的视频删掉
     */
    @GetMapping("deleteVideo/{id}")
    @ApiOperation("删除课程小节")
    public R deleteVideo(@PathVariable String id) {
        return eduVideoService.removeVideoById(id);
    }

    /**
     * 修改课程小节
     */
    @PostMapping("updateVideo")
    @ApiOperation("修改课程小节")
    public R updateVideo(@RequestBody EduVideo eduVideo) {
        boolean flag = eduVideoService.updateById(eduVideo);
        return R.judgeByBo(flag);
    }

}

