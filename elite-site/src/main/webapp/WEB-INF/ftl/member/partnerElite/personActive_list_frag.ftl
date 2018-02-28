<div class="person-table table" id="table1">
    <div class="table_title clearfloat">
	    <div class="part1 part">注册时间</div>
	    <div class="part2 part">用户名</div>
	    <div class="part3 part">姓名</div>
	    <div class="part4 part">手机号</div>
	    <div class="part5 part">状态</div>
	    <div class="part6 part" style="line-height:22px;">进行中的<br/>任务|项目</div>
	    <div class="part7 part">最后一次登录</div>
	    <div class="part8 part" style="line-height:22px;">最后一次<br/>接单操作</div>
    </div>
    <div class="table_content">
    <#if list?size lt 1>
	暂无数据
	</#if>
    <#list list as it>
	    <div class="groupBox clearfloat">
	    	<span class="part1 part">${it.createTime?string("yyyy.MM.dd")}</span>
	    	<span class="part2 part">${it.nickName}</span>
	    	<span class="part3 part">${it.credit.realName}</span>
	    	<span class="part4 part">${it.accountOffSuffix}</span>
	    	<span class="part5 part">${it.elite.status.label}</span>
	    	<span class="part6 part">${it.parElite.tasksInCount}</span>
	    	<span class="part7 part">${it.lastLoginTime?string("yyyy.MM.dd")}</span>
	    	<span class="part8 part">
                <#if it.parElite.lastRecruitTime??>
                   ${it.parElite.lastRecruitTime?string("yyyy.MM.dd")}
              <#else>
                                     未曾接过单
               </#if></td>
	    	
	    	</span>
	     </div>
    </#list>
    </div>
</div>

<#include "/init/pager.ftl"/>

<dl class="noteBox">
	<dt class="clearfloat"><span class="note_icon"></span>Tips</dt>
	<dd><span class="dd_icon"></span>为了能够能好地服务于您备案的人才，提升精英的和您的收益，备案时请提供尽可能详尽的资料。</dd>
	<dd><span class="dd_icon"></span>备案后，您需要给精英介绍云英汇平台，并督促其注册和接活，如有需要，您可以协助其操作。。</dd>
</dl>