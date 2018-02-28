package com.ledao.elite.site.freemarker;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import org.apache.commons.io.IOUtils;

import freemarker.cache.TemplateLoader;

/**
 * freemarker加载模版规则{配置模版的加载路径和来源}
 */
public class EliteTemplateLoader implements TemplateLoader {
	
    public static final String ESCAPE_PREFIX = "<#ftl strip_whitespace=true><#escape x as x?html>";
    public static final String ESCAPE_SUFFIX = "</#escape>";
    public static final String PAY_HTML_FTL="pay.ftl";
    public static final String DEFINE_HTML_FTL="affirm_define.ftl";
    protected TemplateLoader loader;
    
    public EliteTemplateLoader(){}
    
    public EliteTemplateLoader(TemplateLoader loader){
        this.loader=loader;
    }
    @Override
    public Object findTemplateSource(String name) throws IOException {
        return loader.findTemplateSource(name);
    }

    @Override
    public long getLastModified(Object templateSource) {
        return loader.getLastModified(templateSource);
    }

    @Override
    public Reader getReader(Object templateSource, String encoding) throws IOException {
        Reader reader = loader.getReader(templateSource, encoding);
        try {
            String templateText = IOUtils.toString(reader);
            //无需转移的字符
            if(templateSource.toString().endsWith(PAY_HTML_FTL)||templateSource.toString().endsWith(DEFINE_HTML_FTL)){
            	return new StringReader(templateText);
            }
            return new StringReader(templateText);
            //return new StringReader(ESCAPE_PREFIX + templateText + ESCAPE_SUFFIX);
        } finally {
            IOUtils.closeQuietly(reader);
        }
    }

    @Override
    public void closeTemplateSource(Object templateSource) throws IOException {
    	loader.closeTemplateSource(templateSource);
    }
}
