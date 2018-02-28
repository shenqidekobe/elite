package com.ledao.elite.atta.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ledao.elite.atta.utils.FileAssistUtils;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.service.sys.AttasService;

import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Encoder;

/**
 * 附件下载控制器
 * 
 * @author kobe.liu
 * @version 1.0
 */
@SuppressWarnings("restriction")
@Controller
@RequestMapping("")
@Slf4j
public class DownloadController {

	@Resource
	private AttasService attasService;

	/**
	 * 预览图片{按文件预览}
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/preview/img/{app}/{suffix}/{filename}")
	public void base64AsFile(@PathVariable String app, @PathVariable String suffix, @PathVariable String filename,
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		String path = FileAssistUtils.getPath(request, app + "/" + filename + "." + suffix);
		OutputStream out;
		try {
			File srcFile = new File(path);
			out = response.getOutputStream();
			byte[] b = FileUtils.readFileToByteArray(srcFile);
			out.write(b);
			out.flush();
		} catch (IOException e) {
			log.info(e.getMessage());
		}
	}

	/**
	 * 预览图片{按ID预览}
	 * 
	 * @param id
	 * @param response
	 */
	@RequestMapping(value = "/preview/img/{id}")
	@ResponseBody
	public String base64AsId(@PathVariable Long id, HttpServletResponse response) throws Exception {
		Attas atta = this.attasService.findById(id);
		if (atta == null) {
			return null;
		}
		String path = atta.getPath();
		File file = new File(path);
		FileInputStream inputFile = new FileInputStream(file);
		byte[] buffer = new byte[(int) file.length()];
		inputFile.read(buffer);
		inputFile.close();
		return new BASE64Encoder().encode(buffer);
	}

	/**
	 * 文件下载
	 */
	@RequestMapping(value = "download/{attaId}")
	public ResponseEntity<byte[]> download(@PathVariable Long attaId, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			Attas atta = this.attasService.findById(attaId);
			if (atta != null) {
				String downloadfFileName = atta.getFileName();
				downloadfFileName = new String(downloadfFileName.getBytes("UTF-8"), "ISO-8859-1");
				HttpHeaders headers = new HttpHeaders();
				headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
				headers.setContentDispositionFormData("attachment", downloadfFileName);
				String path = FileAssistUtils.getPath(request, atta.getAttaPath());
				File file = new File(path + File.separator + atta.getAttaName());
				return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);
			} 
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
