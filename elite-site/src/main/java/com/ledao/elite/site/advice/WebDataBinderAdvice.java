package com.ledao.elite.site.advice;

import java.util.Map;

import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.InitBinder;

import com.alibaba.fastjson.JSON;

@ControllerAdvice
public class WebDataBinderAdvice {

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.registerCustomEditor(String.class, new StringTrimmerEditor(true));
		
	}
	
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		String c="{\"engineer\":\"php,Hadoop\"}";
		@SuppressWarnings("unchecked")
		Map<String,String> map=JSON.parseObject(c,Map.class);
	}
}
