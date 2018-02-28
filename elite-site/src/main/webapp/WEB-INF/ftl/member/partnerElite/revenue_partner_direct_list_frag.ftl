     <!--渠道直接收益-->
      <input type="hidden" id="myIncomeId" value="${myIncome}">
	   <div class="table_head">
			<div class="part" style="width:30%;border-right:1px solid #fff;">渠道编号</div>
			<div class="part" style="width:20%;border-right:1px solid #fff;">渠道用户名</div>
			<div class="part" style="width:10%;border-right:1px solid #fff;">人才数</div>
			<div class="part" style="width:20%;border-right:1px solid #fff;">人才收益总金额<span>(元)</span></div>
			<div class="part" style="width:20%">我的收益额<span>(元)</span></div>
		</div>
		<div class="table_body">
			 <#list list as it>
			<div class="group">
				<div class="part" style="width:30%" title="${it.channelNum}">${it.channelNum?substring(0,8)}></div>
				<div class="part" style="width:20%">${it.memberPassport.nickName}</div>
				<div class="part" style="width:10%">
                      <#if it.putCount==0>
                         ${it.putCount}
                       <#else>
                        <a href="javascript:void(0);" id="showPartnerEliteListId" mess="${it.income}" memberId="${it.memberPassport.id}" text="${it.memberPassport.nickName}"data="${it.id}">${it.putCount}</a>
                       </#if>
                  </div>
				<div class="part" style="width:20%">${it.income}</div>
				<div class="part" style="width:20%">${it.partnerIncome}</div>
			</div>
			</#list>
        </div>
<#include "/init/pager.ftl"/>