package com.ledao.elite.core.framework.cache.custom;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.dto.StringKeyValue;

import lombok.extern.slf4j.Slf4j;

/**
 * 消息箱文字说明的缓存
 */
@Slf4j
@Component
public class MessageBoxCache implements InitializingBean,DisposableBean{
	
	static Map<String,StringKeyValue> message = new HashMap<String,StringKeyValue>();

	@Override
	public void afterPropertiesSet() throws Exception {
		log.info("**********************************开始加载message*************************************************");
		loadProperties();
	}
	
	@Override
	public void destroy() throws Exception {
		message.clear();
	} 
	
	/**
	 * 获取对应key的内容
	 * */
	public static StringKeyValue get(String key){
		StringKeyValue obj=new StringKeyValue();
		if(StringUtils.isEmpty(key))return obj;
		StringKeyValue val=message.get(key);
		return val==null?obj:val;
	}

	static void loadProperties() {
		Properties properties = new Properties();
		try {
			InputStream in = MessageBoxCache.class.getResourceAsStream("/profile/messageBox.properties");
			properties.load(new InputStreamReader(in,"UTF-8"));
			in.close();
			Set<Map.Entry<Object,Object>> set=properties.entrySet();
			Iterator<Map.Entry<Object,Object>> it =set.iterator();
			StringKeyValue obj=null;
			while(it.hasNext()){
				Map.Entry<Object,Object> entry=it.next();
				if(entry.getValue()==null||entry.getKey()==null)continue;
				String value=entry.getValue().toString();
				String[] tcl=StringUtils.split(value, "||");
				if(tcl.length!=2)continue;
				obj=new StringKeyValue(tcl[0],tcl[1]);
				message.put(entry.getKey().toString(),obj);
			}
		} catch (Exception e) {
			log.error("加载messageBox.properties资源文件失败："+e.getMessage());
		}
	}
	
	public static void main(String[] args) {
		loadProperties();
	}
	
	//消息key定义
	public static class MESSAGE_KEY{
		public static final String COMPANY_REGISTER_SUCCESS="company_register_success";//项目方注册成功
		public static final String COMPANY_AUDIT_PASS="company_audit_pass";//项目方审核通过
		public static final String COMPANY_AUDIT_UNPASS="company_audit_unpass";//项目方审核不通过
		public static final String COMPANY_ASK_WEEKLY="company_ask_weekly";//项目方索要周报
		
		public static final String ELITE_REGISTER_SUCCESS="elite_register_success";//精英方注册成功
		public static final String CTO_AUDIT_PASS="cto_audit_pass";//CTO首次审核通过
		public static final String ELITE_AUDIT_PASS="elite_audit_pass";//精英首次审核通过
		public static final String ELITE_AUDIT_UNPASS="elite_audit_unpass";//精英审核不通过
		public static final String ELITE_TO_CTO_PASS="elite_to_cto_pass";//精英转CTO成功
		public static final String ELITE_TO_CTO_UNPASS="elite_to_cto_unpass";//精英转CTO失败
		public static final String ELITE_UPDATE_ROLE_PASS="elite_update_role_pass";//精英更换角色成功
		public static final String ELITE_UPDATE_ROLE_UNPASS="elite_update_role_unpass";//精英更换角色失败
		public static final String ELITE_RECEIVE_INVITECODE="elite_receive_invitecode";//精英审核通过获取邀请码
		
		public static final String PROJECT_PUBLISH_SUCCESS="project_publish_success";//项目发布成功
		public static final String PROJECT_PUBLISH_SUCCESS_TOBM="project_publish_success_tobm";//项目发布成功通知bm
		public static final String PROJECT_AUDIT_UNPASS="project_audit_unpass";//项目审核不通过
		public static final String PROJECT_SUBMIT_INTENTION_SUCCESS="project_submit_intention_success";//项目提交意向金成功
		public static final String PROJECT_RECEIVE_DEFINE="project_receive_define";//项目方收到立项书
		public static final String PROJECT_AFFIRM_DEFINE_SUCCESS="project_affirm_define_success";//项目方确认立项书成功
		public static final String PROJECT_SUBMIT_STAGECOST_SUCCESS="project_submit_stagecost_success";//项目方提交阶段费用成功
		public static final String PROJECT_RECEIVE_MATERIAL="project_receive_material";//项目收到文件
		public static final String PROJECT_RECEIVE_WEEKLY="project_receive_weekly";//项目收到周报
		public static final String PROJECT_STAGE_ACCEPT_SUCCESS="project_stage_accept_success";//项目阶段验收成功
		public static final String PROJECT_ACCEPT_SUCCESS="project_accept_success";//项目整体验收成功
		public static final String PROJECT_QUALITY_END="project_quality_end";//项目质保期结束
		
		public static final String PLATFORM_REM_CTO_PROJECT="platform_rem_cto_project";//平台给CTO推荐项目
		public static final String CTO_TENDER_COMPLETE="cto_tender_complete";//CTO竞标完成
		public static final String CTO_TENDER_SUCCESS="cto_tender_success";//CTO竞标成功
		public static final String CTO_TENDER_CANCEL="cto_tender_cancel";//CTO被取消定标
		public static final String CTO_RECEIVE_DEFINE="cto_receive_define";//CTO收到定标书
		public static final String CTO_TENDER_FAIL="cto_tender_fail";//CTO竞标失败
		public static final String CTO_AFFIRM_DEFINE_SUCCESS="cto_affirm_define_success";//CTO确认定标书完成
		public static final String CTO_ACCEPT_MATERIAL="cto_accept_material";//CTO收到文件
		public static final String CTO_ASK_WEEKLY="cto_ask_weekly";//CTO索要周报
		public static final String CTO_STAGE_ACCEPT_SUCCESS="cto_stage_accept_success";//CTO的阶段验收通过
		
		public static final String CTO_ACCEPT_PLAN="cto_accept_plan";//CTO收到计划
		public static final String CTO_ACCEPT_TASK="cto_accept_task";//CTO验收任务接到的消息
		public static final String ELITE_ACCEPT_TASK="elite_accept_task";//精英任务被验收收到的消息
		public static final String CTO_TASK_QUALITY_END="cto_task_quality_end";//CTO收到的任务质保期结束
		public static final String ELITE_TASK_QUALITY_END="elite_task_quality_end";//精英收到的任务质保期结束
		public static final String ELITE_APPLY_TASK_COMPLETE="elite_apply_task_complete";//精英申请任务完成
		public static final String ELITE_APPLY_TASK_CANCEL="elite_apply_task_cancel";//精英取消申请任务
		public static final String CTO_AFFIEM_TASK_ELITE="cto_affiem_task_elite";//CTO确认任务的人选
		
		public static final String COMPANY_PARTNER_REGISTER_SUCCESS="company_partner_register_success";//项目渠道方注册成功
		public static final String ELITE_PARTNER_REGISTER_SUCCESS="elite_partner_register_success";//人才渠道方注册成功
		
		public static final String COMPANY_PARTNER_AUDIT_PASS="company_partner_audit_pass";//项目渠道方审核通过
		public static final String COMPANY_PARTNER_AUDIT_UNPASS="company_partner_audit_unpass";//项目渠道方审核不通过
		public static final String ELITE_PARTNER_AUDIT_PASS="elite_partner_audit_pass";//人才渠道方审核通过
		
		public static final String PM_TENDER_APPLY="pm_tender_apply";//竞标通知pm
		public static final String PM_ACCEPT_MATERIAL="pm_accept_material";//pm收到新的文件
		public static final String PM_RECEIVE_WEEKLY="pm_receive_weekly";//pm收到新的周报
		public static final String PM_ASK_WEEKLY="pm_ask_weekly";//pm索要新的周报
		public static final String PM_UNAUDIT_WEEKLY="pm_unaudit_weekly";//pm审核未通过周报
		public static final String PM_CANCEL_TENDER="pm_cancel_tender";//pm取消招标
		public static final String PM_CANCEL_DEFINE_BOOK="pm_cancel_define_book";//pm取消定标书
		
		public static final String BM_RECEIVE_CTO_AFFIRM_DEFINE="bm_receive_cto_affirm_define";//bm收到cto确认定标书
		public static final String BM_RECEIVE_CTO_AFFIRM_TENDER="bm_receive_cto_affirm_tender";//bm收到cto确认立项书
		public static final String BM_CANCEL_TENDER_BOOK="bm_cancel_tender_book";//bm撤回立项书
	}

}
