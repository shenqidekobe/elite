package com.ledao.elite.site.freemarker.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.sys.Dict;
import com.ledao.elite.core.service.sys.DictService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;

/**
 * 数据字典数据查询指令
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@SuppressWarnings("rawtypes")
@Slf4j
@Component
public class DictListDirective implements TemplateDirectiveModel{
	
	
	public static final String TYPE = "type";//数据字典类型
	public static final String PARENT_ID="parentId";//父节点ID
	public static final String KEY = "key"; //数据字典key
	
	@Resource
	private DictService dictService;

	@Override
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		try {
			List<Dict> list=getDictList(env,params);
			env.setVariable("dictList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			env.getConfiguration().setSharedVariable("list", list);
			if(body!=null){
				body.render(env.getOut());
			}
		} catch (Exception e) {
			log.error("解析数据字典指令异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	public List<Dict> getDictList(Environment env,Map params) throws TemplateModelException {
		Object typeParamObj = params.get(TYPE);
		String dictType = typeParamObj == null ? null : (typeParamObj.toString());
		Object parentParamObj = params.get(PARENT_ID);
		String parentId = parentParamObj == null ? null : (parentParamObj.toString());
		if(parentId==null){
		    return this.dictService.findRootDictListByDictType(dictType);
		}else{
			return this.dictService.findDictsByDictType(dictType,Long.valueOf(parentId));
		}
	}

}
