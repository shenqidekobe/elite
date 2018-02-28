package com.ledao.elite.site.freemarker.method;

import java.util.List;

import org.springframework.stereotype.Component;

import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModelException;


/**
 * StaticMethod
 */
@Component
public class StaticMethod implements TemplateMethodModelEx {

    @SuppressWarnings("rawtypes")
	@Override
    public Object exec(List arguments) throws TemplateModelException {
        if(arguments.size()!=1)
            throw new TemplateModelException("arguments only take 1 ");
        return ((SimpleScalar) arguments.get(0)).toString();

    }
}
