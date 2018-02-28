package com.ledao.elite.atta.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.ledao.elite.atta.service.AliyunOssService;
import com.ledao.elite.atta.service.AppConfig;
import com.ledao.elite.atta.utils.FileAssistUtils;
import com.ledao.elite.atta.utils.FileVerifyUtils;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.framework.base.ResponseBase;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.dto.attachment.UploadResult;
import com.ledao.elite.core.service.sys.AttasService;

/**
 * 图片上传控制中心
 * 
 * @author kobe.liu
 * @version 1.0
 */
@Controller
@RequestMapping("/upload")
public class UploadController {
	
    public Logger logger = LoggerFactory.getLogger(UploadController.class);
    
    
    @Resource
    private AppConfig appConfig;
    @Resource
    private AliyunOssService aliyunOssService;
    @Resource
    private AttasService attasService;
    @Resource
    private FileVerifyUtils fileVerifyUtils;

    /**
     * 文件上传
     * 
     * @param type:上传的文件类型(图片or文件)
     * @param app:存储目录
     * @param isDownload:是否可以下载
     * */
    @ResponseBody
    @RequestMapping(value="/{uploadType}/{app}/to",method=RequestMethod.POST)
    public UploadResult upload(
            @RequestParam(value = "file", required = false) MultipartFile file,
            @PathVariable(value = "uploadType") String uploadType,
            @PathVariable(value = "app") String app,
            @RequestParam(value = "fileName", required = false) String fileName,
            @RequestParam(value = "autoExt", required = false, defaultValue = "0") String autoExt,
            @RequestParam(value = "isDownload", required = false, defaultValue = "0") String isDownload,
            HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        return uploadCommon(file,uploadType,app, fileName, autoExt,isDownload,request, response, session);
    }

    public UploadResult uploadCommon(MultipartFile file,String uploadType,String app, String name, String autoExt,String isDownload,
                             HttpServletRequest request, HttpServletResponse response, HttpSession session) throws IOException {
        UploadResult result = new UploadResult();
        try {
        	if(file==null){
        		MultipartHttpServletRequest mreq=(MultipartHttpServletRequest) request;
            	Map<String,MultipartFile> map=mreq.getFileMap();
            	for(String ky:map.keySet()){
            		file=map.get(ky);
            	}
        	}
        	//验证文件类型和大小
        	String verifyCode=fileVerifyUtils.verify(uploadType, file);
        	if(!ErrorCodeEnum.SUCCESS.code.equals(verifyCode)){
                result.isSuccess(false);
                result.setCode(verifyCode);
                result.setMsg("文件上传被系统禁止");
                return result;
        	}
            String path = FileAssistUtils.getPath(request, app.replace(".", File.separator));
            logger.info("文件开始上传到目录："+app+"*********************************************");
            File dtnFile = new File(path);
            if (!dtnFile.exists()) {
                dtnFile.mkdir();
            }
            String fileName = file.getOriginalFilename();
            String originalFilename=fileName;
            String attaPath=app.replace(".", "/");//文件存储目录，相对于根目录
            int index = fileName.indexOf(".");
            String type = fileName.substring(index+1, fileName.length());
            //文件名统一为uuid生成
            fileName = UUID.randomUUID().toString().replaceAll("-", "")+"." + type;
            logger.info(path);
            File targetFile = new File(path, fileName);
            if (!targetFile.exists()) {
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            //是否开启阿里云OSS存储服务，若开启就把附件存入阿里云
            if (appConfig.getAliyunOssEnable().equals("1")) {
                String opath = app.replace(".", "/") + "/" + fileName;
                aliyunOssService.putObject2AliyunOSS(opath, targetFile);
                if (appConfig.getAliyunOssReadProxy().equals("1")) {
                    result.setUrl(FileAssistUtils.getServerUrl(request, "/pics/" + opath));
                } else {
                    result.setUrl("http://" + appConfig.getAliyunOssBucketName() + "." + appConfig.getAliyunOssEndpointPublic() + "/" + opath);
                }
                targetFile.delete();
            } else {
                String basePath = FileAssistUtils.getServerUrl(request,"");
                String action="/preview/img/"+attaPath+"/"+type+"/"+fileName;
                result.setUrl(basePath + action);
            }
            result.setFileName(fileName);
            result.isSuccess(true);
            //保存文件记录到数据库
            Attas attas=new Attas();
            attas.setAttaName(fileName);
            attas.setFileName(originalFilename);
            attas.setAttaType(type);
            attas.setAttaLength(file.getSize());
            attas.setUserType(Attas.UPLOAD_USER_TYPE.SYSTEM.name());
            attas.setAttaPath(attaPath);
            attas.setDownloaded(isDownload.equals("1"));
            Attas obj=this.attasService.createAttas(attas);
            result.setOriginalName(originalFilename);
            result.setAttaId(obj.getId());
            result.setAttaType(type);
        } catch (Exception e) {
            logger.error("app--上传文件错误", e);
            result.isSuccess(false);
            result.setCode(ErrorCodeEnum.ERROR.code);
            result.setMsg("上传失败:"+e);
        }
        return result;
    }


    /**
     * 按文件ID删除文件
     * */
    @RequestMapping(value="/{app}/delete",method=RequestMethod.POST)
    @ResponseBody
    public ResponseBase delete(HttpServletRequest request,@PathVariable String app,Long attaId) throws IOException {
    	ResponseBase result = new ResponseBase();
        try {
        	Attas atta=this.attasService.findById(attaId);
        	if(atta!=null){
        		app=atta.getAttaPath();
        		String path = FileAssistUtils.getPath(request, app.replace(".", File.separator));
                File targetFile = new File(path, atta.getAttaName());
                if (targetFile.exists()) {
                    logger.info("正在删除"+app+"目录下的附件文件:" + atta.getAttaName());
                    targetFile.delete();
                }
                result.isSuccess(true);
                this.attasService.removeLogicById(attaId);
        	}
            
        } catch (Exception e) {
        	 result.isSuccess(false);
             result.setCode(ErrorCodeEnum.ERROR.code);
             result.setMsg("删除文件失败:"+e);
        }
        return result;
    }

    @RequestMapping("/move")
    @ResponseBody
    public ResponseBase move(
            HttpServletRequest request,
            @RequestParam(value = "names", required = false) String[] names,
            @RequestParam(value = "src", required = false) String src,
            @RequestParam(value = "des", required = false) String des
    ) {
    	ResponseBase result = new ResponseBase();
        try {
            String path_src = FileAssistUtils.getPath(request, src.replace(".", File.separator));
            String path_des = FileAssistUtils.getPath(request, des.replace(".", File.separator));
            for (String name : names) {
                File srcFile = new File(path_src, name);
                File desFile = new File(path_des);
                FileUtils.moveFileToDirectory(srcFile, desFile, true);
            }
            result.isSuccess(true);
        } catch (Exception e) {
            result.isSuccess(false);
        }
        return result;
    }

    @RequestMapping("/moveas")
    @ResponseBody
    public ResponseBase moveAs(
            HttpServletRequest request,
            @RequestParam(value = "srcName", required = false) String srcName,
            @RequestParam(value = "src", required = false) String src,
            @RequestParam(value = "des", required = false) String des,
            @RequestParam(value = "desName", required = false) String desName
    ) {
    	ResponseBase result = new ResponseBase();
        try {
            String path_src = FileAssistUtils.getPath(request, src.replace(".", File.separator));
            String path_des = FileAssistUtils.getPath(request, des.replace(".", File.separator));
            File srcFile = new File(path_src, srcName);
            File desFile = new File(path_des);
            if (!desFile.exists())
                desFile.mkdir();
            File desFile1 = new File(path_des, desName);
            if (desFile1.exists()) {
                desFile1.delete();
            }
            FileUtils.moveFile(srcFile, desFile1);
            logger.info("移动" + srcFile.getPath() + "到" + desFile1.getPath());
            result.isSuccess(true);
        } catch (Exception e) {
            result.isSuccess(false);
        }
        return result;
    }

    @RequestMapping("/copyas")
    @ResponseBody
    public ResponseBase copyas(
            HttpServletRequest request,
            @RequestParam(value = "srcName", required = false) String srcName,
            @RequestParam(value = "src", required = false) String src,
            @RequestParam(value = "des", required = false) String des,
            @RequestParam(value = "desName", required = false) String desName
    ) {
    	ResponseBase result = new ResponseBase();
        try {
            String path_src = FileAssistUtils.getPath(request, src.replace(".", File.separator));
            String path_des = FileAssistUtils.getPath(request, des.replace(".", File.separator));
            File srcFile = new File(path_src, srcName);
            if (srcFile.exists()) {
                File desFile = new File(path_des);
                if (!desFile.exists())
                    desFile.mkdir();
                File desFile1 = new File(path_des, desName);
                if (desFile1.exists()) {
                    desFile1.delete();
                }
                FileUtils.copyFile(srcFile, desFile1);
                logger.info("复制" + srcFile.getPath() + "到" + desFile1.getPath());
                result.isSuccess(true);
            } else {
                result.isSuccess(false);
            }

        } catch (Exception e) {
            result.isSuccess(false);
        }
        return result;
    }


    @RequestMapping(value = "pics/**")
    public void pics(HttpServletRequest request, HttpServletResponse response) {
        picsHandler(request, response);
    }

    public void picsHandler(HttpServletRequest request, HttpServletResponse response) {
        InputStream objectContent = null;
        try {
            String path = request.getRequestURI();
            path = path.replace(request.getContextPath() + "/pics/", "");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setHeader("Pragma", "no-cache");
            response.setDateHeader("Max-Age", 0);
            OutputStream output = response.getOutputStream();
            objectContent = aliyunOssService.getObjectFromAliyunOSS(path);
            if (objectContent != null) {
                IOUtils.write(IOUtils.toByteArray(objectContent), output);
                output.flush();
                objectContent.close();
            } else {
                response.sendError(404);
            }
        } catch (Exception e) {
            if (objectContent != null) {
                try {
                    objectContent.close();
                } catch (Exception e1) {
                    logger.error("", e1);
                }
            }
            logger.error("", e);
        }
    }
}
