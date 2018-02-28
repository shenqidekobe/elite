package com.ledao.elite.rest.framework.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ledao.elite.rest.framework.ResponseResultData;
import com.ledao.elite.rest.framework.response.member.RMemberElite;

/**
 * 重写MappingJackson2JsonView类
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public class ExtendMappingJackson2JsonView extends MappingJackson2JsonView{
	
	private static ObjectMapper objectMapper=null;
	static{
		objectMapper=new ObjectMapper();
		//objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	}
	
	public ExtendMappingJackson2JsonView(){
		super();
	}

	public ExtendMappingJackson2JsonView(boolean auto){
		super(objectMapper);
	}
	
	public static void main(String[] args)throws Exception {
		ResponseResultData<Map<RMemberElite,List<String>>> response=new ResponseResultData<>();
		Map<RMemberElite,List<String>> eliteJobListMap=new LinkedHashMap<>();
		List<String> a=new ArrayList<>();
		RMemberElite e=new RMemberElite();
		e.setJobAgeVal("wusong");
		eliteJobListMap.put(e, a);
		response.setData(eliteJobListMap);
		String json=objectMapper.writeValueAsString(response);
		System.out.println(json );
	}
	
}
