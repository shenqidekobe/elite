package com.ledao.elite.site.freemarker.directive;

import java.io.IOException;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;

/**
 * 分页指令
 * */
@Component
public class PagerDirective implements TemplateDirectiveModel{

	
	@SuppressWarnings("rawtypes")
	@Override
	public void execute(Environment env, Map params, TemplateModel[] model, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		
	}

}
