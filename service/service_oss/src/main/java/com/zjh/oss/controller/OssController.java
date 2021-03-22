package com.zjh.oss.controller;

import com.zjh.commonUtils.R;
import com.zjh.oss.service.OssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileNotFoundException;

/**
 * OSS上传文件功能
 */
@RestController
@RequestMapping("eduOss")
@CrossOrigin//避免跨域
public class OssController {

    @Autowired
    private OssService ossService;

    /**
     * 上传文件功能
     *
     * @param file 前端需要传入的本地文件路径
     * @return
     * @throws FileNotFoundException
     */
    @PostMapping("uploadFile")
    public R uploadOssFile(MultipartFile file) throws FileNotFoundException {

        //获取上传文件    MultipartFile
        //返回上传到oss的路径
        String url = ossService.uploadFileAvatar(file);

        return R.ok().data("url", url);
    }
}
