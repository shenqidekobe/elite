package com.ledao.elite.rest.advice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.messaging.handler.annotation.MessageExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import com.alibaba.fastjson.JSON;
import com.ledao.elite.core.exception.EliteBusinessException;
import com.ledao.elite.core.exception.EliteServiceException;
import com.ledao.elite.core.framework.constant.ErrorCodeEnum;
import com.ledao.elite.core.framework.constant.GlobalDefinedConstant;
import com.ledao.elite.rest.framework.ResponseBaseRest;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.exection.ExceptionJsonModel;
import com.ledao.elite.rest.framework.exection.NoLoginException;
import com.ledao.elite.rest.framework.exection.TokenExpireException;
import com.ledao.elite.rest.framework.exection.TokenInvalidException;
import com.ledao.elite.rest.framework.exection.UnauthorizedOfException;

/**
 * 异常处理
 * @author kobe.liu
 * @date 2016/8/9
 */
@ControllerAdvice
public class WebExceptionAdvice {

    @SuppressWarnings("rawtypes")
	@ResponseBody
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public String exceptionHandler(HttpServletRequest request, HttpServletResponse response, Exception e) {
    	ExceptionJsonModel jsonModel=new ExceptionJsonModel();
    	ResponseResultData<Object> rsp=new ResponseResultData<>();
    	ResponseBaseRest result=new ResponseBaseRest();
    	result.setResult(GlobalDefinedConstant.FAILURE);
        if(e instanceof NoLoginException){
        	result.setCode(ErrorCodeEnum.NOLOGIN.code);
        	result.setMsg(e.getMessage());
        }else if(e instanceof UnauthorizedOfException){
        	result.setCode(ErrorCodeEnum.UNAUTHORIZED.code);
        	result.setMsg(e.getMessage());
        }else if(e instanceof TokenExpireException||e instanceof TokenInvalidException){
        	result.setCode(ErrorCodeEnum.TOKENFAILED.code);
        	result.setMsg(e.getMessage());
        }else{
        	String code=ErrorCodeEnum.ERROR.code;
            String msg = e.getMessage();
        	if (e instanceof EliteServiceException) {
            	EliteServiceException ce = (EliteServiceException) e;
                msg = ce.getMsg();
                code=ce.getCode();
            }else if (e instanceof EliteBusinessException) {
            	EliteBusinessException ce = (EliteBusinessException) e;
                msg = ce.getMsg();
                code=ce.getCode();
            }else if (e instanceof ConstraintViolationException) {
                ConstraintViolationException ce = (ConstraintViolationException) e;
                msg = "";
                for (ConstraintViolation violation : ce.getConstraintViolations()) {
                    msg += violation.getMessage() + "<br>";
                }
            } else if (e instanceof MaxUploadSizeExceededException) {
                MaxUploadSizeExceededException ex = (MaxUploadSizeExceededException) e;
                msg = "文件大小超过限制[" + ex.getMaxUploadSize() + "]";
            }
            if (e instanceof DataIntegrityViolationException) {
                msg = "数据已经使用,不能删除";
            }
            result.setCode(code);
            result.setMsg(msg);
            e.printStackTrace();
        }
        rsp.setResult(result);
        rsp.setData(null);
        jsonModel.setResponse(rsp);
        return JSON.toJSONString(jsonModel);
    }

    @MessageExceptionHandler
    public String handleException(Throwable exception) {
        return exception.getMessage();
    }

}
