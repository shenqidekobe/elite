package com.ledao.elite.atta.controller;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.baidu.ueditor.ConfigManager;
import com.baidu.ueditor.define.ActionMap;
import com.baidu.ueditor.define.AppInfo;
import com.baidu.ueditor.define.BaseState;
import com.baidu.ueditor.define.State;
import com.ledao.elite.atta.service.AliyunOssService;
import com.ledao.elite.atta.service.AppConfig;
import com.ledao.elite.atta.utils.FileAssistUtils;

/**
 * 百度编辑器
 * */
@Controller
@RequestMapping(value = "/editor")
public class BaiduEditorController {
    public Logger logger = LoggerFactory.getLogger(BaiduEditorController.class);
    @Autowired
    private AppConfig appConfig;
    @Autowired
    private AliyunOssService aliyunOssService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        //不自动绑定对象中的roleList属性，另行处理
        //binder.setDisallowedFields("roleList");
        //转换时间类型用的
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(true);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

    @RequestMapping(value = "action", method = RequestMethod.POST)
    @ResponseBody
    public String goSysLogListAjax(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request) {
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        this.rootPath = rootPath;
        this.contextPath = request.getContextPath();
        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
        String actionType = request.getParameter("action");
        return exec(request, actionType,file);
    }

    @RequestMapping(value = "action", method = RequestMethod.GET)
    @ResponseBody
    public String goSysLogListAjax(HttpServletRequest request) {
        String rootPath = request.getSession().getServletContext().getRealPath("/");
        this.rootPath = rootPath;
        this.contextPath = request.getContextPath();
        this.configManager = ConfigManager.getInstance(this.rootPath, this.contextPath, request.getRequestURI());
        String actionType = request.getParameter("action");
        return exec(request, actionType,null);
    }

    private String rootPath = null;
    private String contextPath = null;
    private ConfigManager configManager = null;

    public String exec(HttpServletRequest request, String actionType,MultipartFile file) {
        String callbackName = request.getParameter("callback");
        if (callbackName != null) {
            if (!validCallbackName(callbackName)) {
                return new BaseState(false, AppInfo.ILLEGAL).toJSONString();
            }
            return callbackName + "(" + this.invoke(request,actionType,file) + ");";
        } else {
            return this.invoke(request,actionType,file);
        }
    }

    @SuppressWarnings("unused")
	public String invoke(HttpServletRequest request,String actionType,MultipartFile file) {
        if (actionType == null || !ActionMap.mapping.containsKey(actionType)) {
            return new BaseState(false, AppInfo.INVALID_ACTION).toJSONString();
        }
        if (this.configManager == null || !this.configManager.valid()) {
            return new BaseState(false, AppInfo.CONFIG_ERROR).toJSONString();
        }
        State state = null;
        int actionCode = ActionMap.getType(actionType);
        Map<String, Object> conf = null;
        switch (actionCode) {
            case ActionMap.CONFIG:
                return this.configManager.getAllConfig().toString();
            case ActionMap.UPLOAD_IMAGE:
            case ActionMap.UPLOAD_SCRAWL:
            case ActionMap.UPLOAD_VIDEO:
            case ActionMap.UPLOAD_FILE:
                conf = this.configManager.getConfig(actionCode);
                state= uploadCommon(file,"images.project.detail",request);
                break;
        }
        return state.toJSONString();
    }

    /**
     * callback参数验证
     */
    public boolean validCallbackName(String name) {
        if (name.matches("^[a-zA-Z_]+[\\w0-9_]*$")) {
            return true;
        }
        return false;
    }

    public  String getPath(HttpServletRequest request,String append) {
        String pathString=request.getSession().getServletContext().getRealPath("/")+"upload"+File.separator+append;
        return pathString;
    }

    public State uploadCommon(MultipartFile file, String app,HttpServletRequest request)
    {
        BaseState storageState = new BaseState();
        try {
            String path = getPath(request,app.replace(".", File.separator));
            logger.info("百度编辑器开始上传附件到指定目录："+app);
            File dtnFile = new File(path);
            if(!dtnFile.exists()){
                dtnFile.mkdir();
            }
            String fileName = file.getOriginalFilename();
            int index=fileName.indexOf(".");
            String type=fileName.substring(index, fileName.length());
            fileName = new Date().getTime()+type;
            logger.info(path);
            File targetFile = new File(path, fileName);
            if(!targetFile.exists()){
                targetFile.mkdirs();
            }
            file.transferTo(targetFile);
            String url;
            if (appConfig.getAliyunOssEnable().equals("1")) {
                String opath = app.replace(".", "/") + "/" + fileName;
                aliyunOssService.putObject2AliyunOSS(opath, targetFile);
                if (appConfig.getAliyunOssReadProxy().equals("1")) {
                    url=FileAssistUtils.getServerUrl(request, "/pics/" + opath);
                } else {
                    url="http://" + appConfig.getAliyunOssBucketName() + "." + appConfig.getAliyunOssEndpointPublic() + "/" + opath;
                }
                targetFile.delete();
            } else {
                url= FileAssistUtils.getServerUrl(request, "/upload/" + app.replace(".", "/")+ "/" + fileName);
            }
            storageState.setState(true);
            storageState.putInfo("url",url);
            storageState.putInfo("title", fileName);
            storageState.putInfo("original", file.getOriginalFilename());
        } catch (Exception e) {
            logger.error("app--上传图片错误",e);
            storageState.setState(false);
        }
        return storageState;
    }

}
