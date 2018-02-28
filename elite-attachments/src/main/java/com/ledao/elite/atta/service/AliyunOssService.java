package com.ledao.elite.atta.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

@Component
public class AliyunOssService {
	
    public static Logger logger = LoggerFactory.getLogger(AliyunOssService.class);
    
    @Autowired
    private AppConfig appConfig;
    
    public static void main(String[] args) {
        try {
            putObject("rockfund","p/123.jpg","C:"+File.separator+"Users"+File.separator+"zhaosg"+File.separator+"Pictures"+File.separator+"Camera Roll"+File.separator+"55fea55bNf09a7f5a.jpg");
        }catch (Exception e){
            logger.error("",e);
        }
    }

    public static void putObject(String bucketName, String key, String filePath) throws FileNotFoundException {
        String endpoint = "oss-cn-hangzhou.aliyuncs.com";
        String accessKeyId = "D8JeLMPT7nFJM2PJ";
        String accessKeySecret = "KX71Et5uuvcmA7fJYcJ81iggWEYl4W";
        // 初始化OSSClient
        OSSClient client = new OSSClient(endpoint,accessKeyId, accessKeySecret);

        // 获取指定文件的输入流
        File file = new File(filePath);
        logger.info(file.getPath());
        InputStream content = new FileInputStream(file);

        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();

        // 必须设置ContentLength
        meta.setContentLength(file.length());

        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, key, content, meta);

        // 打印ETag
        System.out.println(result.getETag());
    }


    public PutObjectResult putObject2AliyunOSS(String key, File file) throws FileNotFoundException {
        String bucketName = appConfig.getAliyunOssBucketName();
        String endpoint = appConfig.getAliyunOssEndpointInternal();
        String accessKeyId = appConfig.getAliyunAccessKeyId();
        String accessKeySecret = appConfig.getAliyunAccessKeySecret();
        // 初始化OSSClient
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 获取指定文件的输入流
        InputStream content = new FileInputStream(file);
        // 创建上传Object的Metadata
        ObjectMetadata meta = new ObjectMetadata();
        // 必须设置ContentLength
        meta.setContentLength(file.length());
        // 上传Object.
        PutObjectResult result = client.putObject(bucketName, key, content, meta);
        return result;
    }

    public InputStream getObjectFromAliyunOSS(String key) throws Exception {
        String bucketName = appConfig.getAliyunOssBucketName();
        String endpoint = appConfig.getAliyunOssEndpointInternal();
        String accessKeyId = appConfig.getAliyunAccessKeyId();
        String accessKeySecret = appConfig.getAliyunAccessKeySecret();
        // 初始化OSSClient
        OSSClient client = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 获取Object，返回结果为OSSObject对象
        if (client.doesObjectExist(bucketName, key)) {
            OSSObject object = client.getObject(bucketName, key);
            // 获取Object的输入流
            InputStream objectContent = object.getObjectContent();
            return objectContent;
        }
        return null;
    }
}
