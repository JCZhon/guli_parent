package com.zjh.eduService.client;

import com.zjh.commonUtils.R;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * 服务调用接口
 */
@FeignClient("service-vod")//指定调用的服务
@Component
public interface VodClient {
    //定义调用的方法路径，注意这个路径要改成完全路径
    //注意：这里的@PathVariable注解后面要加上参数名称，否则会出错

    /**
     * 删除单个视频
     *
     * @param id
     * @return
     */
    @GetMapping("eduVod/video/deleteVideo/{id}")
    @ApiOperation("删除阿里云视频")
    R removeVideo(@ApiParam(name = "id", value = "video_source_id", required = true)
                  @PathVariable("id") String id);

    /**
     * 删除多个视频
     *
     * @param videoIdList
     * @return
     */
    @GetMapping("eduVod/video/selete-batch")
    @ApiOperation("删除阿里云视频")
    R deleteBatch(@ApiParam(name = "eduVod/video/videoIdList", value = "多个id集合")
                  @RequestParam("videoIdList") List<String> videoIdList);
}
