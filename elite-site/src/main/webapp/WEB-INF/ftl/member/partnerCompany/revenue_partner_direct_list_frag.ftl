     <!--渠道直接收益-->
      <input type="hidden" id="myIncomeId" value="${myIncome}">
      <input type="hidden" id="proportionId" value="${proportion*100}%">
	   <div class="table_head">
			<div class="part" style="width:25%;border-right:1px solid #fff;">渠道编号</div>
			<div class="part" style="width:20%;border-right:1px solid #fff;">渠道用户名</div>
			<div class="part" style="width:10%;border-right:1px solid #fff;"><span>项目数</span></div>
			<div class="part" style="width:15%;border-right:1px solid #fff;"><span>项目总额</span><span>(元)</span></div>
			<div class="part" style="width:15%"><span>已验收总额</span><span>(元)</span></div>
			<div class="part" style="width:15%"><span>我的收益额</span><span>(元)</span></div>
		</div>
		<div class="table_body">
			 <#list list as it>
			<div class="group">
				<div class="part" style="width:25%"title="${it.channelNum}">${it.channelNum?substring(0,8)}</div>
				<div class="part" style="width:20%">${it.memberPassport.nickName}</div>
				<div class="part" style="width:10%">
                   <#if it.enterCount==0>
                         ${it.enterCount}
                       <#else>
                        <a href="javascript:void(0);" id="showProjectListId" data="${it.id}" type="partner_direct">${it.enterCount}</a>
                       </#if>
                  </div>
				<div class="part" style="width:15%">${it.projectTotalAmount}</div>
				<div class="part" style="width:15%">${it.projectTrustedAmount}</div>
				<div class="part" style="width:15%">${it.partnerIncome}</div>
			</div>
			</#list>
        </div>
<#include "/init/pager.ftl"/>