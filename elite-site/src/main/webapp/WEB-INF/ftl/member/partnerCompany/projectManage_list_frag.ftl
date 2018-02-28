 <div id="table1" class="tableBox">
    <div class="table_title clearfloat">
	    <div class="part1 part">概况</div>
	    <div class="part2 part">统计</div>
	    <div class="part3 part">状态</div>
    </div>
    <div class="table_content">
    <#if list?size lt 1>
	暂无数据
	</#if>
    
    <#list list as it>
	    <div class="groupBox">
	    	<div class="group_head clearfloat">
	    		<span class="city_icon"></span>
	    		<span class="city_name">
                       <#if it.project.areaName??>
                         ${it.project.areaName}
                       <#else>
                        ${it.areaName}
                       </#if>
                 </span>
	    		<span class="record_time">创建时间：${it.createTime?string("yyyy.MM.dd")}</span>
	    		<span class="register_time">交付时间：
	    		                         <#if it.memberId??>
                                         ${it.memberPassport.createTime?string("yyyy.MM.dd")}
                                         </#if>
                         </span>
                 <span class="time_last">
                 <#if it.contactpartner=='true'>
                                                      可以以我的名义联系
                 </#if>
                 </span>
	    	</div>
	    	<div class="group_body clearfloat">
	    		<div class="part1">
	    			<div class="name">${it.project.name}</div>
	    			<div class="nickname">联系人：<span class="font_active">${it.project.memberPassport.nickName}</span></div>
	    		    <div class="phone">${it.project.memberPassport.accountOffSuffix}</div>
	    		</div>
			    <div class="part2">
			    	<div class="part2_left">
			    		<div class="attribute">
	    					<span>已经托管费用：</span>
	    					<span class="font_active">
                                                                                     ￥  <#if it.project.trustedAmount==0>
                             ${it.project.intentionAmount}
                             <#else>
                              ${it.project.trustedAmount}
                             </#if>
  							</span>
                      	</div>
	    				<div class="identity">
	    					<span>本阶段托管费用：</span>
	    					<span class="font_active"> 
                                                                                    ￥ <#if it.project.forStage.amount??>
                               ${it.project.forStage.amount}
                       		 <#else>
                       		 0
                       		 </#if>	
                         	</span>
                         </div>
	    				
	    				<div class="acceptance">
	    				 	<span>已验收费用合计：</span>
	    				 	<span class="font_active">￥${it.project.allPayAmount}</span>
                        </div>
			    	</div>
			    	<div class="part2_right">
			    		<div class="total">
	    				 	<span>项目总额：</span>
	    				 	<span class="font_active">￥
	    				 	<#if it.project.totalAmount??>
	    				 	 ${it.project.totalAmount}
	    				 	<#else>
	    				 	0
	    				 	</#if>
                              </span>
                        </div>
                        <div class="mine">
	    				 	<span>我的收益：</span>
	    				 	<span class="font_active">￥
                             <#if it.income??>
	    				 	 ${it.income}
	    				 	<#else>
	    				 	0
	    				 	</#if>
                             </span>
                        </div>
			    	</div>
			  </div>
	             <div class="part3">
		             <button type="button" class="downloadBtn" id="uploadAttaBtnId"  >
		             <#if it.project.status=='stage_course'>
	              		<#if it.project.forStage??>${it.project.forStage.title}<#else>已立项</#if>
	            		<#else>
	                	${it.project.status.label}
	            	</#if>
		             </button>
	             </div>
	    	</div>
	     </div>
    </#list>
    </div>
  </div>
<#include "/init/pager.ftl"/>
<dl class="noteBox">
	<dt class="clearfloat"><span class="note_icon"></span>Tips</dt>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">为了提高项目的成功率，从而提升您的收益，请提供尽可能详尽的项目资料。</span></dd>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">备案后，您需要和客户进行初步沟通，介绍云英汇平台，并督促其注册发布项目，如有需要，您可以协助其操作。</span></dd>
	<dd class="clearfloat"><span class="dd_icon"></span><span class="dd_text">平台不会向任何第三方泄漏您提供的任何项目资料。</span></dd>
</dl>
