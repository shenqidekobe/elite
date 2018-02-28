package com.ledao.elite.site.freemarker;

import java.io.File;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import com.ledao.elite.site.freemarker.directive.BlockDirective;
import com.ledao.elite.site.freemarker.directive.ClearTagDirective;
import com.ledao.elite.site.freemarker.directive.DictListDirective;
import com.ledao.elite.site.freemarker.directive.ExtendDirective;
import com.ledao.elite.site.freemarker.directive.HomeEliteListDirective;
import com.ledao.elite.site.freemarker.directive.PagerDirective;
import com.ledao.elite.site.freemarker.directive.ProjectStageDirective;
import com.ledao.elite.site.freemarker.directive.ServerDateDirective;
import com.ledao.elite.site.freemarker.method.SessionMethod;
import com.ledao.elite.site.freemarker.method.StaticMethod;
import com.ledao.elite.site.freemarker.method.SubStringMethod;
import com.ledao.elite.site.freemarker.method.UrlMethod;

import freemarker.cache.FileTemplateLoader;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

/**
 * freemarker配置自定义
 * */
@Component
public class EliteConfiguration implements FactoryBean<Configuration>{

	private static final String ENCODING="UTF-8";
	private static final String DATEFORMAT="yyyy-MM-dd";
	private static final String TIMEFORMAT="HH:mm:ss";
	private static final String DATETIMEFORMAT="yyyy-MM-dd HH:mm:ss";
	
	@Resource
	private WebApplicationContext webApplicationContext;
	@Resource
	private ExtendDirective extendDirective;
	@Resource
	private BlockDirective blockDirective;
	@Resource
	private SessionMethod sessionMethod;
	@Resource
	private StaticMethod staticMethod;
	@Resource
	private UrlMethod urlMethod;
	@Resource
	private SubStringMethod subStringMethod;
	@Resource
	private ClearTagDirective clearTagDirective;
	@Resource
	private DictListDirective dictListDirective;
	@Resource
	private ServerDateDirective serverDateDirective;
	@Resource
	private PagerDirective pagerDirective;
	@Resource
	private ProjectStageDirective projectStageDirective;
	@Resource
	private HomeEliteListDirective homeEliteListDirective;
	
	@Override
	public Configuration getObject() throws Exception {
		Configuration configuration=new Configuration();
		BeansWrapper beansWrapper=new BeansWrapper();
		configuration.setDefaultEncoding(ENCODING);
		String root=webApplicationContext.getServletContext().getRealPath("/");
		configuration.setTemplateLoader(new EliteTemplateLoader(new FileTemplateLoader(new File(root+"/WEB-INF/ftl"))));
		configuration.setDateFormat(DATEFORMAT);
		configuration.setDateTimeFormat(DATETIMEFORMAT);
		configuration.setTimeFormat(TIMEFORMAT);
		configuration.setLocale(new Locale("zh_CN"));
		configuration.setBooleanFormat("true,false");
		configuration.setURLEscapingCharset(ENCODING);
		configuration.setNumberFormat("0.##");
		configuration.setWhitespaceStripping(true);
		configuration.setObjectWrapper(beansWrapper);//设置对象包装器
		configuration.setTemplateExceptionHandler(TemplateExceptionHandler.IGNORE_HANDLER);//设置异常处理器{IGNORE_HANDLER:简单地压制所有异常,对处理异常没有任何作用，也不会重新抛出异常,RETHROW_HANDLER:遇到异常不再继续执行编译模版}
		
	    configuration.setSharedVariable("extend", extendDirective);
        configuration.setSharedVariable("block", blockDirective);
        configuration.setSharedVariable("dict", dictListDirective);
        configuration.setSharedVariable("clearTag", clearTagDirective);
        configuration.setSharedVariable("projectStage", projectStageDirective);
        configuration.setSharedVariable("eliteList", homeEliteListDirective);
        configuration.setSharedVariable("serverDate", serverDateDirective);
        configuration.setSharedVariable("pagerObj", pagerDirective);
        configuration.setSharedVariable("session", sessionMethod);
        configuration.setSharedVariable("static",staticMethod);
        configuration.setSharedVariable("url", urlMethod);
        configuration.setSharedVariable("subString",subStringMethod);
        for(String name :new String[]{"head","headChannel", "modal","page"}){
            configuration.addAutoInclude("init/"+name+".ftl");
        }
	    configuration.setClassicCompatible(true);
		return configuration;
	}

	@Override
	public Class<?> getObjectType() {
		return Configuration.class;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

}
