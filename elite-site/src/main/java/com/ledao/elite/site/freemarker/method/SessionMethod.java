package com.ledao.elite.site.freemarker.method;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.site.shiro.Principal;

import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 * 获取在线用户信息方法
 */
@Component
public class SessionMethod implements TemplateMethodModelEx {
	
    @Resource protected Principal principal;
    
    @SuppressWarnings("rawtypes")
	@Override
    public Object exec(List arguments) throws TemplateModelException {
        return principal;
    }
}
