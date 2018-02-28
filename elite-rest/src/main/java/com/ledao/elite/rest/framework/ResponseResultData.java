package com.ledao.elite.rest.framework;

import org.springframework.web.servlet.ModelAndView;

import com.ledao.elite.rest.config.GlobalData;
import com.ledao.elite.rest.framework.utils.ExtendMappingJackson2JsonView;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一的返回结果数据(带对象的返回)
 * */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResultData<T>{

	private ResponseBaseRest result=new ResponseBaseRest();//返回的结果标识
	private T data=null;//返回的数据对象
	
	
	/**
	 * 直接返回result信息
	 * 
	 * */
	public ModelAndView responseResult(){
		ModelAndView modelAndView = new ModelAndView(new ExtendMappingJackson2JsonView(true));
		modelAndView.addObject(GlobalData.RESULT_RESPONSE_NAME,new ResponseResultData<>(result,null));
		return modelAndView;
	}
	
	/**
	 * 直接返回result信息
	 * 
	 * */
	public ModelAndView responseResult(ResponseBaseRest result){
		this.result=result;
		ModelAndView modelAndView = new ModelAndView(new ExtendMappingJackson2JsonView(true));
		modelAndView.addObject(GlobalData.RESULT_RESPONSE_NAME,new ResponseResultData<>(result,null));
		return modelAndView;
	}
	
	/**
	 * 返回一个带参数信息
	 * 
	 * @param result
	 * @param t
	 * */
	public ModelAndView responseResult(ResponseBaseRest result,T t){
		ModelAndView modelAndView = new ModelAndView(new ExtendMappingJackson2JsonView(true));
		modelAndView.addObject(GlobalData.RESULT_RESPONSE_NAME,new ResponseResultData<T>(result,t));
		return modelAndView;
	}
	
	/**
	 * 返回一个带参数成功示信息
	 * 
	 * @param t
	 * */
	public ModelAndView responseResult(T t){
		ModelAndView modelAndView = new ModelAndView(new ExtendMappingJackson2JsonView(true));
		modelAndView.addObject(GlobalData.RESULT_RESPONSE_NAME,new ResponseResultData<T>(result,t));
		return modelAndView;
	}

}
