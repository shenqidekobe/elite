package com.ledao.elite.site.exception;

import java.io.IOException;
import java.io.Writer;

import freemarker.core.Environment;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;
import lombok.extern.slf4j.Slf4j;

/**
 * freemarker异常处理
 * */
@Slf4j
public class FreemarkerExceptionHandler  implements TemplateExceptionHandler {
	
	
    
    public void handleTemplateException(TemplateException te, Environment env,
                                        Writer out) throws TemplateException {
            log.warn("[Freemarker analysis template Error: " + te.getMessage() + "]");
            try {
				out.write("");
			} catch (IOException e) {
				//e.printStackTrace();
			}
    }
}
