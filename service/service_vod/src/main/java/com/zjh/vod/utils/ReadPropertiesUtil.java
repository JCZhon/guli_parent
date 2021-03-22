package com.zjh.vod.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 读取properties配置文件信息（视频上传秘钥部分）
 */
@Component
public class ReadPropertiesUtil implements InitializingBean {

    @Value("${aliyun.vod.file.keyid}")
    private String accessId;

    @Value("${aliyun.vod.file.keysecret}")
    private String accessSecret;

    public static String ACCESS_ID;

    public static String ACCESS_SECRET;

    @Override
    public void afterPropertiesSet() throws Exception {
        ACCESS_ID = accessId;
        ACCESS_SECRET = accessSecret;
    }
}
