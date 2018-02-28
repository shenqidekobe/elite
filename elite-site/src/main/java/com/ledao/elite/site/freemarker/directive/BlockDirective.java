package com.ledao.elite.site.freemarker.directive;

import freemarker.core.Environment;
import freemarker.template.*;

import java.io.IOException;
import java.io.StringWriter;
import java.util.Map;

import org.springframework.stereotype.Component;

/**
 * BlockDirective
 */
@Component
public class BlockDirective implements TemplateDirectiveModel {

    @SuppressWarnings("rawtypes")
	public void execute(Environment env,
                        Map params, TemplateModel[] loopVars,
                        TemplateDirectiveBody body)
            throws TemplateException, IOException {
        if (loopVars.length != 0) {
            throw new TemplateModelException("This directive doesn't allow loop variables.");
        }
        
        Map<String, String> container= ExtendDirective.container(env);
        if(container==null)
            throw new TemplateModelException("block must work with extend");
        
        String name=ExtendDirective.getName(params);
        if(container.containsKey(name)){
            String content=container.get(name);
            if(content!=null)
                env.getOut().write(content);
        }else{
            String bodyResult =null;
            if(body!=null) {
                StringWriter out = new StringWriter();
                body.render(out);
                bodyResult = out.toString();
                env.getOut().write(bodyResult);
            }
            container.put(name, bodyResult);
        }
    }

}
