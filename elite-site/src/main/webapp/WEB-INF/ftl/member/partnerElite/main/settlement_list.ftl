<form id="settlementListForm" name="settlementListForm">
	<div class="settlement-box">
	    <div class="s-a">
	        <div class="hosting-box">
	            <p class="color9b padding-bottom5">已托管费用合计</p>
	            <p class="font-size16 color00 padding-bottom9">${account.trustAmount}元</span></p>
	            <p class="color9b padding-bottom5">已托管股权合计</p>
	            <p class="font-size16 color00">${account.trustStock}</span></p>
	        </div>
	        <div class="line-r"></div>
	        <div class="account-box">
	            <p class="font-size12 color9b padding-bottom10">账户余额<#--<span class="font-size12">（冻结的质保金：1360.00元）</span>--></p>
	            <p class="font-size16 color00">${account.balance}元</span></p>
	        </div>
	        <a class="reflect-btn" href="javascript:void(0);" id="withdraw">提现</a>
	        <#--<a class="top-up" href="javascript:void(0);" id="charge">充值</a>-->
	    </div>
	    <input type="hidden" id="balance" value="${account.balance}">
	    <div class="s-b">
	        <h1>结算记录</h1>
	        <div class="line-bottom"></div>
	        <div class="s-b-title clearfloat">
	            <div class="title-title" id="allType" style="cursor: pointer;">全部</div>
	            <ul class="title-record">
	                <li data="prostage">托管记录</li>
	                <#--<li>退款记录</li>-->
	                <li style="border-right: none" data="withdraw">提现记录</li>
	            </ul>
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
			                        <li data="${pl.id}">${pl.name}</li>
			                    </#list>
			                </ul>
		                </div>
	                </div>
	            </div>
	        </div>
	
	        <div class="s-b-content">
	            <input type="hidden" id="projectId" name="projectId" value="">
	            <input type="hidden" id="type" name="type" value="">
	            <div id="dataSecondList"></div>
	        </div>
	    </div>
	</div>
</form>