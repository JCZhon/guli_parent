package com.zjh.vod.controller;

import com.zjh.commonUtils.R;
import com.zjh.vod.service.VodService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import jdk.nashorn.internal.objects.annotations.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * 视频点播功能
 */
@RestController
@RequestMapping("eduVod/video")
@Api("视频点播功能")
public class VodController {

    @Autowired
    private VodService vodService;

    /**
     * 上传视频到阿里云
     */

    @PostMapping("uploadVideo")
    @ApiOperation("视频上传")
    public R upload(MultipartFile file) throws IOException {
        String videoId = vodService.uploadVideo(file);
        return R.ok().data("videoId", videoId);
    }

    /**
     * 删除阿里云中的视频
     *
     * @param id
     * @return
     */
    @GetMapping("deleteVideo/{id}")
    @ApiOperation("删除阿里云视频")
    public R removeVideo(@ApiParam(name = "id", value = "video_source_id", required = true)
                         @PathVariable String id) {
        vodService.removeVideo(id);
        return R.ok();
    }

    /**
     * 删除多个视频
     *
     * @param videoIdList
     * @return
     */
    @PostMapping("selete-batch")
    @ApiOperation("删除多个视频")
    public R deleteBatch(@ApiParam(name = "videoIdList", value = "多个id集合")
                         @RequestParam("videoIdList") List<String> videoIdList) {
        vodService.removeMoreVideos(videoIdList);
        return R.ok();
    }

}
