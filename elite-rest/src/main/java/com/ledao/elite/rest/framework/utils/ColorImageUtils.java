package com.ledao.elite.rest.framework.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ColorImageUtils {
	 
	 private static String[] imgNames=new String[]{"pic1@3x","pic2@3x","pic3@3x","pic4@3x","pic5@3x","pic6@3x"};
	
	 public static Map<String,String> getColorImage(List<String> colorList){
		 Map<String,String> map = new HashMap<String, String>();
		 for(int i=0;i<colorList.size();i++){
			 if(imgNames.length-1<i){
				 map.put(colorList.get(i), imgNames[i-imgNames.length]);
			 }else{
				 map.put(colorList.get(i), imgNames[i]);
			 }
		 }
		 return map;
	 } 	 
	 
}
