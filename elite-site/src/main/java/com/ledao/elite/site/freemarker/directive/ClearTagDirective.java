package com.ledao.elite.site.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.utils.CommonUtils;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;

/**
 * 字符串清除标记指令
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@SuppressWarnings("rawtypes")
@Component
public class ClearTagDirective implements TemplateDirectiveModel{
	
	
	public static final String VALUE = "value";//字符的值
	public static final String LENGTH = "length";//获取其长度

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		try {
			Object patten = params.get(VALUE);
			String pattenStr = patten == null ? null : (patten.toString());
			Object length = params.get(LENGTH);
			Integer lengthStr = length == null ? null : Integer.parseInt((length.toString()));
			String htmlStr=CommonUtils.delHTMLTag(pattenStr);
			if(htmlStr.length()>lengthStr){
				htmlStr=htmlStr.substring(0,lengthStr)+"...";
			}
			env.getOut().write(htmlStr);
			if(body!=null){
				body.render(env.getOut());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public String getHtmlStr(Environment env,Map params) throws TemplateModelException {
		Object typeParamObj = params.get(VALUE);
		String htmlStr = typeParamObj == null ? null : (typeParamObj.toString());
		return CommonUtils.delHTMLTag(htmlStr);
	}

}
