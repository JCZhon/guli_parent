package com.zjh.oss.service.impl;

import cn.hutool.core.date.DateUtil;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.zjh.commonUtils.R;
import com.zjh.oss.service.OssService;
import com.zjh.oss.utils.ReadPropertiesUtil;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class OssServiceImpl implements OssService {

    /**
     * 上传头像到oss（直接使用阿里云模板）
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public String uploadFileAvatar(MultipartFile file) throws FileNotFoundException {
        //工具类获取值
        String endpoint = ReadPropertiesUtil.END_POINT;
        String accessKeyId = ReadPropertiesUtil.ACCESS_KEY_ID;
        String accessKeySecret = ReadPropertiesUtil
                .ACCESS_KEY_SECRET;
        String bucketName = ReadPropertiesUtil.BUCKET_NAME;

        try {
            // 创建OSS实例。
            OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);

            //获取上传文件的输入流
            InputStream inputStream = file.getInputStream();
            //获取文件名称(包含路径和路径名称)
            String fileName = file.getOriginalFilename();

            //1.在文件名中添加一个随机的唯一的值,并去掉随机值中的“-”
            String uuid = UUID.randomUUID().toString().replace("-", "");
            fileName = uuid + fileName;

            //2.把文件按照日期进行分类
            //获取当前日期
            //SimpleDateFormat sdf = new SimpleDateFormat("yyy/MM/dd");
            //            String datePath = sdf.format(new Date());太麻烦，直接使用hutool工具类

            String datePath = DateUtil.format(new Date(), "yyyy/MM/dd");//使用hutool工具类更方便
            fileName = datePath + "/" + fileName;


            //调用oss方法实现上传
            //第一个参数：bucket名称
            //第二个参数：上传到oss的文件的路径和文件名称 fileName
            //第三个参数：上传文件的输入流
            ossClient.putObject(bucketName, fileName, inputStream);

            // 关闭OSS。
            ossClient.shutdown();

            //返回上传之后的文件路径（数据库中的avatar属性就存这个路径），需要自己手动拼接
            //eg:https://zjh01-edu.oss-cn-shenzhen.aliyuncs.com/eduproject01/QQ%E5%9B%BE%E7%89%8720200630101547.jpg
            String url = "https://" + bucketName + "." + endpoint + "/" + fileName;
            return url;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
