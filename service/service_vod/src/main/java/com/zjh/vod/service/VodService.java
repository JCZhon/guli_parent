package com.zjh.vod.service;

import com.zjh.commonUtils.R;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface VodService {
    String uploadVideo(MultipartFile file) throws IOException;

    R removeVideo(String id);

    void removeMoreVideos(List videoIdList);
}
