<form id="settlementListForm" name="settlementListForm">
	<div class="settlement-box">
	    <div class="s-a">
	        <div class="hosting-box">
	            <div class="hosting-box-p1" id="hosting-box-p1"><span class="text1">总收益</span><span class="text2">（冻结的质保金：${account.guaranteeAmount}元）</span></div>
	            <div class="hosting-box-p2">${account.totalIncome}元</span></div>
	            <#--<div class="hosting-box-p3">已托管股权合计</div>-->
	            <#--<div class="hosting-box-p4">${account.trustStock}</span></div>-->
	        </div>
	        <div class="line-r"></div>
	        <div class="account-box">
	            <p class="account-box-p1"><span class="text1">账户余额</span><span class="font-size12"></span></p>
	            <p class="account-box-p2">${account.balance}元</span></p>
	        </div>
	        <a class="reflect-btn" href="javascript:void(0);" id="withdraw">提现</a>
	        <#--<a class="top-up" href="javascript:void(0);" id="charge">充值</a>-->
	    </div>
	    <input type="hidden" id="balance" value="${account.balance}">
	    <input type="hidden" id="card" value="${credit.isCard}">
	    <div class="s-b">
	        <h1>结算记录</h1>
	        <div class="line-bottom"></div>
	        <div class="s-b-title clearfloat">
	            <div class="title-title" id="allType" style="cursor: pointer;">全部</div>
	            <ul class="title-record">
	                <li data="income">收益记录</li>
	                <#--<li>退款记录</li>-->
	                <li style="border-right: none" data="withdraw">提现记录</li>
	            </ul>
	            <#-- 
	            <div class="title-select">
	                <div class="title-select-a">
	                    <span>选择全部项目：</span>
	                    <input type="text" id="projectName" value="全部" readonly>
	                    <div class="select-btn">
	                        <span class="trigger"></span>
	                    </div>
	                    <div style="display:none;" id="procUl">
		                    <ul>
		                        <li data="">全部</li>
			                    <#list projectList as pl>
			                        <li data="${pl.id}"><#if pl.name?length gt 8>${pl.name?substring(0,8)}<#else>${pl.name}</#if></li>
			                    </#list>
			                </ul>
		                </div>
	                </div>
	            </div>
	             -->
	        </div>
	
	        <div class="s-b-content">
	            <input type="hidden" id="projectId" name="projectId" value="">
	            <input type="hidden" id="type" name="type" value="">
	            <div id="dataSecondList"></div>
	        </div>
	    </div>
	</div>
</form>