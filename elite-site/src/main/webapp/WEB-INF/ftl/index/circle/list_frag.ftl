<div class="role_content_box">
	<#if list?size lt 1>
	暂无数据
	</#if>
    <#list list as it>
	    <div class="role_group">
	        <div class="group_box">
	            <div class="box_l">
	                <div class="imgBox" data="${it.id}">
		                <#if it.basic.photoId??>
				           <div class="box-logo-a" >
				           	   <img src="${it.basic.memberPhoto.path}">
				           </div>
				        <#else>
				           <div class="box-logo-a">
				           	   <img src="${_PATH}/res/images/default.jpg">
				           </div>
				        </#if>
	                </div>
	                <div class="box_l_text">
	                    <div class="skill_box clearfloat">
	                        <div class="skill_name" data="${it.id}" title="${it.nickName}">
							    <#if it.nickName?length gt 8>${it.nickName?substring(0,8)}<#else>${it.nickName}</#if>
							</div>
	                        <div class="skill">
	                            <ul>
	                                <#list it.elite.eliteJobs as ejs>
	                                   <#list ejs.jobAdeptValList as ibs>
	                                      <li>${ibs.value}</li>
	                                   </#list>
	                                </#list>
	                            </ul>
	                        </div>
	                    </div>
	                    <div class="time_box clearfloat">
	                        <span class="time">${it.elite.jobAgeVal}</span><span class="work">${it.basic.memberSign}</span>
	                    </div>
	                    <div class="resume clearfloat">
	                        <@clearTag value='${it.elite.intro}' length=65/>
	                    </div>
	                </div>
                </div>
                <div class="box_line"></div>
	            <div class="box_r">
	                <div class="box_r_a">
	                    <span>每周可共享时长：</span><span>${it.elite.allotDurationVal}</span>
	                </div>
	                <div class="box_r_b">
	                    <img src="${_PATH}/res/images/elite/attention.png" class="attention_img" alt="">
	                    <span class="fans_text">粉丝数量：</span>
	                    <span class="fans_number">${it.fansCount}</span>
	                    <#--<#if session().memberId?exists&&session().currentType!='company'>-->
	                    <#if it.attentioned>
	                       <div class="attention_btn" data="${it.id}" oper="remove" style="background: #999">已关注</div>
	                    <#else>
	                       <div class="attention_btn" data="${it.id}" oper="yes" style="background: #fea600">关注</div>	
	                    </#if>
	                </div>
	            </div>
               
	        </div>
	    </div>
    </#list>
</div>
<#include "/init/pager.ftl"/>
