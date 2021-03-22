package com.zjh.vod.service.impl;

import com.aliyun.vod.upload.impl.UploadVideoImpl;
import com.aliyun.vod.upload.req.UploadStreamRequest;
import com.aliyun.vod.upload.resp.UploadStreamResponse;
import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.vod.model.v20170321.DeleteVideoRequest;
import com.zjh.commonUtils.R;
import com.zjh.servicebase.exception.CustomizeExceptiion;
import com.zjh.vod.service.VodService;
import com.zjh.vod.utils.InitVodClient;
import com.zjh.vod.utils.ReadPropertiesUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.List;

@Service
public class VodServiceImpl implements VodService {
    /**
     * 上传视频到阿里云
     *
     * @param file
     * @return
     */
    @Override
    public String uploadVideo(MultipartFile file) {

        try {
            InputStream inputStream = file.getInputStream();
            String fileName = file.getOriginalFilename();//上传文件原始名称
            String title = fileName.substring(0, fileName.lastIndexOf("."));//上传后的文件名称(去掉了.mp4)

            UploadStreamRequest request = new UploadStreamRequest(ReadPropertiesUtil.ACCESS_ID, ReadPropertiesUtil.ACCESS_SECRET, title, fileName, inputStream);
            UploadVideoImpl uploader = new UploadVideoImpl();
            UploadStreamResponse response = uploader.uploadStream(request);
            String videoId = null;
            if (response.isSuccess()) {
                videoId = response.getVideoId();
            }
            return videoId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从阿里云删除视频
     *
     * @param id
     * @return
     */
    @Override
    public R removeVideo(String id) {

        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ReadPropertiesUtil.ACCESS_ID, ReadPropertiesUtil.ACCESS_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            request.setVideoIds(id);
            client.getAcsResponse(request);
            return R.ok();
        } catch (ClientException e) {
            e.printStackTrace();
            throw new CustomizeExceptiion(20001, "删除失败");
        }
    }

    /**
     * 删除多个视频
     * 要点：和删除单个视频的区别就是要拼接多个视频id
     *
     * @param videoIdList
     */
    @Override
    public void removeMoreVideos(List videoIdList) {
        try {
            DefaultAcsClient client = InitVodClient.initVodClient(ReadPropertiesUtil.ACCESS_ID, ReadPropertiesUtil.ACCESS_SECRET);
            DeleteVideoRequest request = new DeleteVideoRequest();
            String videoIds = StringUtils.join(videoIdList, ",");
            request.setVideoIds(videoIds);
            client.getAcsResponse(request);
        } catch (Exception e) {
            e.printStackTrace();
            throw new CustomizeExceptiion(20001, "删除视频失败");
        }


    }
}
