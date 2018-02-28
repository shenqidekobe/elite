package com.ledao.elite.atta.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.core.utils.CommonUtils;

/**
 * 加载项目资源配置文件
 * 
 * @author kobe.liu
 */

@Component
@Configuration
@PropertySource(value = {"classpath:/profile/app.properties"})
public class AppConfig {

	@Resource
	Environment env;
	
	/*****************************Aliyun OOS*****************************/
	public String getAliyunOssEnable(){
		return env.getProperty("aliyun.oss.enable");
	}
	public String getAliyunOssBucketName(){
		return env.getProperty("aliyun.oss.bucketName");
	}
	public String getAliyunOssReadProxy(){
		return env.getProperty("aliyun.oss.read.proxy");
	}
	public String getAliyunOssEndpointPublic(){
		return env.getProperty("aliyun.oss.endpoint.public");
	}
	public String getAliyunOssEndpointInternal(){
		return env.getProperty("aliyun.oss.endpoint.internal");
	}
	public String getAliyunAccessKeyId(){
		return env.getProperty("aliyun.accessKeyId");
	}
	public String getAliyunAccessKeySecret(){
		return env.getProperty("aliyun.accessKeySecret");
	}
	
	
	/*****************************File Attribute*****************************/
	
	/**
	 * 图片允许上传的类型
	 * */
	public List<String> getPictureAllowType(){
		String types=env.getProperty("picture_allow_type");
		List<String> list = CommonUtils.separatorStrToList(types,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		return list;
	}
	/**
	 * 图片允许上传的大小(2M)
	 * */
	public Long getPictureAllowLength(){
		return env.getProperty("picture_allow_length",Long.class);
	}
	/**
	 * 附件允许上传的类型
	 * */
	public List<String> getAccessoryAllowType(){
		String types=env.getProperty("accessory_allow_type");
		List<String> list = CommonUtils.separatorStrToList(types,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		return list;
	}
	/**
	 * 附近禁止上传的类型
	 * */
	public List<String> getAccessoryLimitType(){
		String types=env.getProperty("accessory_limit_type");
		List<String> list = CommonUtils.separatorStrToList(types,
				GlobalDefinedConstant.System_Symbol.JOINT_SEPARATOR);
		return list;
	}
	/**
	 * 附件允许上传的大小(500M)
	 * */
	public Long getAccessoryAllowLength(){
		return env.getProperty("accessory_allow_length",Long.class);
	}
}
