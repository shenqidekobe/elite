package com.ledao.elite.rest.controller;

import java.io.File;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.ledao.elite.core.utils.encry.Md5;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.response.AppFileResponse;
import com.ledao.elite.rest.framework.utils.FileMd5Utils;

@Controller("AppFileController")
@RequestMapping("/app")
public class AppFileController extends BaseController{
	
	private static String keySuffix="0QEGR9123590u12pEQRGQERG1[o32[13QWEG'4;1v23pQEGR[/.x/.fgEH.epoq4otpiEHR23po[";
	
	@ResponseBody
	@RequestMapping(value="/file/listData",method=RequestMethod.POST)
	public ModelAndView getFiles(HttpServletRequest request){
		String path = request.getSession().getServletContext().getRealPath("/")+"IOS";
		List<AppFileResponse> appFiles = new ArrayList<>();
		ResponseResultData<List<AppFileResponse>> rsp=new ResponseResultData<>(); 
		File dir = new File(path);
		File[] files = dir.listFiles();
		if (files == null){
			return rsp.responseResult();
		}
		for (int i = 0; i < files.length; i++) {
			AppFileResponse app = new AppFileResponse();
			String fileName = files[i].getName();
			app.setName(fileName);
			String suffix="";
			int dot = fileName.lastIndexOf('.'); 
            if ((dot >-1) && (dot < (fileName.length()))) { 
            	suffix=fileName.substring(dot+1,fileName.length()); 
            	fileName = fileName.substring(0, dot); 
            } 
			String downpath = request.getScheme()+"://" + request.getServerName()+ ":" + request.getServerPort()+request.getContextPath()+"/app/download/"+suffix+"/"+fileName;
			String fileKey = FileMd5Utils.getFileMD5(files[i])+keySuffix;
			String key = Md5.getMD5(fileKey);
			app.setDownPath(downpath);
			app.setFileKey(key);
			appFiles.add(app);
		}	
		return rsp.responseResult(appFiles);
	}
	
	
	@RequestMapping(value = "/download/{suffix}/{filename}")
	public void download(@PathVariable String filename,@PathVariable String suffix,HttpServletRequest request,
			HttpServletResponse response) {
		String fileName = filename+"."+suffix;
		String path = request.getSession().getServletContext().getRealPath("/")+"IOS"+ File.separator+fileName;
		OutputStream out;
		try {
			response.setContentType(request.getServletContext().getMimeType(fileName));  
	        response.setHeader("Content-Disposition", "attachment;filename="+fileName); 
	        File srcFile = new File(path);
			out = response.getOutputStream();
			byte[] b = FileUtils.readFileToByteArray(srcFile);
			out.write(b);
			out.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/upload",method=RequestMethod.GET)
	public String uploadFile(Model model){
		return "/upload/file_upload";
	}
	
	
	@RequestMapping(value = "/file/upload",method=RequestMethod.POST)  
    public String upload(@RequestParam(value = "file", required = false) MultipartFile file, HttpServletRequest request) {  
        String path = request.getSession().getServletContext().getRealPath("IOS");  
        String fileName = file.getOriginalFilename();  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        try {  
            file.transferTo(targetFile); 
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return "/upload/file_upload";
    }  

}
