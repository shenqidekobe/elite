package com.ledao.elite.site.freemarker.directive;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 获取服务器时间指令
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@SuppressWarnings("rawtypes")
@Component
public class ServerDateDirective implements TemplateDirectiveModel{
	
	
	public static final String PATTEN = "patten";//时间格式
	public static final String IS_TRANSFORM = "transform";//转换为毫秒
	
	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		try {
			Object patten = params.get(PATTEN);
			String pattenStr = patten == null ? null : (patten.toString());
			Object is_transform = params.get(IS_TRANSFORM);
			boolean transform = is_transform == null ? null : Boolean.valueOf(is_transform.toString());
			SimpleDateFormat defaultFormat=new SimpleDateFormat(pattenStr);
			Date date=new Date();
			if(transform){
				env.getOut().write(defaultFormat.parse(defaultFormat.format(date)).getTime()+"");
			}else{
				env.getOut().write(defaultFormat.format(date));
			}
			if(body!=null){
				body.render(env.getOut());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
