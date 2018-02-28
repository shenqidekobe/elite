package com.ledao.elite.site.freemarker.directive;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.ledao.elite.core.domain.member.MemberAttention;
import com.ledao.elite.core.domain.member.MemberPassport;
import com.ledao.elite.core.framework.dto.MemberDetailInfo;
import com.ledao.elite.core.service.member.MemberAttentionService;
import com.ledao.elite.core.service.member.MemberPassportService;
import com.ledao.elite.site.shiro.PrincipalService;

import freemarker.core.Environment;
import freemarker.template.ObjectWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import lombok.extern.slf4j.Slf4j;

/**
 * 首页精英展示列表数据查询指令
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Slf4j
@Component
public class HomeEliteListDirective implements TemplateDirectiveModel{

	@Resource
	private MemberPassportService memberPassportService;
	@Resource
	private MemberAttentionService memberAttentionService;
	@Resource
	private PrincipalService principalService;
	public static final String ELITE_COUNT="eliteSize";//数量

	
	@SuppressWarnings("rawtypes")
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body)
			throws TemplateException, IOException {
		try {
			List<MemberDetailInfo> list=getMemberEliteList(env,params);
			env.setVariable("eliteList", ObjectWrapper.DEFAULT_WRAPPER.wrap(list));
			env.getConfiguration().setSharedVariable("list", list);
			if(body!=null){
				body.render(env.getOut());
			}
		} catch (Exception e) {
			log.error("解析精英列表指令异常："+e.getMessage());
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List<MemberDetailInfo> getMemberEliteList(Environment env,Map params) throws TemplateModelException {
		Object idParamObj = params.get(ELITE_COUNT);
		Integer count = idParamObj == null ? 8 : (Integer.valueOf(idParamObj.toString()));
		List<MemberDetailInfo> list=this.memberPassportService.findMemberEliteListAuditPass(count);
		Long memberId=principalService.getPrincipalId();
		if(memberId!=null){
			for (MemberPassport member : list) {
				if(memberId!=null){
					MemberAttention maObj=this.memberAttentionService.checkAttentioned(memberId, member.getId());
					member.setAttentioned(maObj!=null);
				}
			}
		}
		return list;
	}

	
}
