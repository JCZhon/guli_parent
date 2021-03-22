package com.zjh.oss.utils;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 读取配置文件中的内容
 * 使用@Value属性注入
 *
 * @Value只是起赋值作用，但是我们还用不了，所以要实现InitializingBean这个接口 这个接口的特点是：spring中@value赋值完成后，这个接口中的afterPropertiesSet方法会立即执行
 * 所以在这个方法中将常量中的值进行获取（用静态属性的方式方便调用）
 */
@Component
public class ReadPropertiesUtil implements InitializingBean {

    @Value("${aliyun.oss.file.endpoint}")
    private String endPoint;

    @Value("${aliyun.oss.file.keyid}")
    private String keyId;

    @Value("${aliyun.oss.file.keysecret}")
    private String keySecret;

    @Value("${aliyun.oss.file.bucketname}")
    private String bucketName;

    public static String END_POINT;
    public static String ACCESS_KEY_ID;
    public static String ACCESS_KEY_SECRET;
    public static String BUCKET_NAME;

    @Override
    public void afterPropertiesSet() throws Exception {

        END_POINT = endPoint;
        ACCESS_KEY_ID = keyId;
        ACCESS_KEY_SECRET = keySecret;
        BUCKET_NAME = bucketName;
    }
}
