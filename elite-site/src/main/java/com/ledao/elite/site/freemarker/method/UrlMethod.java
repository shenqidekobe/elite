package com.ledao.elite.site.freemarker.method;

import java.util.List;

import org.springframework.stereotype.Component;

import com.ledao.elite.site.freemarker.Utils;

import freemarker.template.TemplateHashModelEx;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;

/**
 *UrlMethod
 */
@Component
public class UrlMethod implements TemplateMethodModelEx {
	
    @SuppressWarnings("rawtypes")
	@Override
    public Object exec(List arguments) throws TemplateModelException {
        if(arguments.size()<1)
            throw new TemplateModelException("arguments takes at least 1 parameter");
        String path=arguments.get(0).toString();
        TemplateHashModelEx params=null;
        if(arguments.size()>1 && arguments.get(1) instanceof TemplateHashModelEx){
            Object tmp=arguments.get(1);
            if(tmp instanceof TemplateHashModelEx){
                params=(TemplateHashModelEx)tmp;
            }else
                throw new TemplateModelException("second parameter must be map");
        }
        return Utils.makeURI(path, params);
    }
}
