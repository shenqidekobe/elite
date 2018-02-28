package com.ledao.elite.atta.utils;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.ledao.elite.atta.service.AppConfig;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.utils.CommonUtils;

import lombok.extern.slf4j.Slf4j;

/**
 * 文件验证工具类
 * 
 * @author kobe.liu
 * */
@Slf4j
@Component
public class FileVerifyUtils {
	
    public static final String UPADLO_PICTURE="picture";//上传图片类型
    public static final String UPADLO_ACCESSORY="accessory";//上传附件类型

	@Resource
	private AppConfig appConfig;
	
	/**
	 * 验证文件是否合法
	 * 
	 * @param uploadType
	 * @param file
	 * 
	 * @return code
	 * */
	public String verify(String uploadType,MultipartFile file){
	   if(file==null)return ErrorCodeEnum.PARAM_ISNULL.code;
	   try {
		   String fileName = file.getOriginalFilename();
	       int index = fileName.indexOf(".");
	       String type = fileName.substring(index+1, fileName.length());
	       type=type.toLowerCase();
	       Long length=file.getSize();
	       log.info("文件系统收到："+uploadType+"的上传请求,文件名："+fileName+",该文件大小："+CommonUtils.convertFileSize(length)+"，实际Long大小："+length);
	       if(UPADLO_PICTURE.equals(uploadType)){
	    	   //验证图片
	    	   if(!appConfig.getPictureAllowType().contains(type)){
	    		   return ErrorCodeEnum.FILE_TYPE_ILLEGALITY.code;
	    	   }else if(length>appConfig.getPictureAllowLength()){
	    		   return ErrorCodeEnum.FILE_LENGTH_LIMIT.code;
	    	   }
	       }else if(UPADLO_ACCESSORY.equals(uploadType)){
	    	   //验证附件
	    	   if(appConfig.getAccessoryLimitType().contains(type)){
	    		   return ErrorCodeEnum.FILE_TYPE_ILLEGALITY.code;
	    	   }else if(length>appConfig.getAccessoryAllowLength()){
	    		   return ErrorCodeEnum.FILE_LENGTH_LIMIT.code;
	    	   }
	       }
	       return ErrorCodeEnum.SUCCESS.code;
	   } catch (Exception e) {
		   return ErrorCodeEnum.ERROR.code;
	   }
	  
	}
	

}
