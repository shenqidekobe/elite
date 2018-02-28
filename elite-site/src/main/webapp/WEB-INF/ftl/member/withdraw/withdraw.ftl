<@extend name="layout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/withdrawal.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
	<div class="withdrawal-box">
	    <div class="withdrawal">
	        <h1 style="margin-top: 0px;margin-bottom: 0px;">
	        <span style="cursor:pointer;" id="crumbs_main">个人主页</span>
	        <span class="paddinglr5">&gt;</span><span style="cursor:pointer;" id="seles_manage">结算管理</span><span class="paddinglr5">&gt;</span>
	        <span class="color2c">提现</span></h1>
	        <div class="withdrawal-content">
	            <div class="w-box clearfloat">
	                <div class="w-box-a">
	                    <div class="balance clearfloat">可提现余额<span>${balance?string.currency}</span></div>
	                    <input type="hidden" id="balance" value="${balance}">
	                    <div class="addAccount">
	                        <img src="${_PATH}/res/images/ceo/addAccount_icon.png" alt="">
	                        <span>添加提取账户</span>
	                    </div>
	                </div>
	                <div class="w-box-line"></div>
	                <div class="w-box-b">
	                    <div class="way-box clearfloat">
	                        <div class="way-box-l ">
	                            	选择到账方式:
	                        </div>
	                        <div class="way-box-r">
	                        </div>
	                    </div>
	                    <div class="amount"><span class="amount_text">提取金额：</span><input type="text" placeholder="输入提现金额" id="withdrawAmount">元
	                         
	                    </div>
	                    <div class="invoice clearfloat">
	                    	<div class="invoice_text">发票提供：</div>
	                    	<ul class="invoice_ul clearfloat">
	                    		<li data="platform"><img src="${_PATH}/res/images/ceo/li_select.png" alt=""><span class="li_text">平台代开</span></li>
	                    		<li data="customer" style="display:none;">
	                    			<img src="${_PATH}/res/images/ceo/li_noselect.png" alt=""><span class="li_text">自行提供</span>
	                    			<div class="example" style="display:none;" id="customerHint">
	                    				<span class="example_icon"></span>
	                    				<div class="example_text">
	                    					<div class="div1">发票抬头：宁波乐道网络科技有限公司</div>
		                    				<div class="div2">发票类型：服务</div>
		                    				<div class="div3">发票邮寄地址：上海市嘉定区金沙江西路1555弄377号6楼 收</div>
	                    				</div>	
	                    			</div>
	                    		</li>
	                    	</ul>
	                    </div>
	                    
	                    <div class="actual_money">
	                    	<div class="money_text">实际到账：</div>
	                    	<div class="money_number">
	                    		<div class="div1 clearfloat"><span><span id="conversion"></span></span><img src="${_PATH}/res/images/ceo/questionMark.png" alt=""></div>
	                    		<div class="div2">
	                    			<#--<span>1. 扣除个人劳动所得税：￥<span id="tax">0</span>。</span>-->
	                    			<span>1. 单笔提现费用：2元（单笔提现＜￥1000时）</span>
	                    		</div>
	                    	</div>
	                    </div>
	                    
	                    
	                    <div class="estimatedTime">
	                    	<div class="estimatedTime_text">预计到账：</div>
	                    	<div class="estimatedTime_time">
		                    	<span>${date?string("yyyy年MM月dd号")}</span>
		                    	<span>（7个工作日）</span>
	                    	</div>
	                    </div>
	                    
	                    <form id="withdrawAffirmForm" method="post" action="${_PATH}/member/withdraw/affirm">
	                        <input type="hidden" name="bankId" id="form_bankId">
	                        <input type="hidden" name="invoiceWay" id="form_invoiceWay" value="platform">
	                        <input type="hidden" name="withdrawAmount" id="form_withdrawAmount">
	                    </form>
	                    
	                    <div class="public_btn_box">
	                    	<div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error_text">错误提示</span></div>
	                    	<a class="nextStep" href="javascript:void(0);">下一步</a>
	                    </div>
	                </div>
	            </div>
	        </div>
	    </div>
	
	    <div class="account-box" style="display:none;">
	        <div class="account">
	        	<div class="account_bg"></div>
	            <div class="account-a">
	                	添加提现账户
	                <span class="a-close"></span>
	            </div>
	            <form id="bankForm" name="bankForm">
		            <div class="account-b">
		                <div class="b-content">
		                    <div class="b-content-one">
		                        <div class="clearfloat marginbottom20 margintop30 relative"><span class="span-a">姓名</span>
			                        <input type="text" id="name" readonly class="input-a" placeholder="请输入你的真实姓名" value="${credit.realName}">
			                        <span class="error"></span>
		                        </div>
		                        
		                         <div class="clearfloat marginbottom20 relative"><span class="span-a">身份证</span>
			                        <input type="text" id="idCard" readonly class="input-b" placeholder="请输入你的身份证号码" maxlength="18" value="${credit.idCard}">
			                        <span class="error"></span>
		                        </div>
		                        
		                        <div class="clearfloat marginbottom20 relative"><span class="span-a">储蓄卡卡号</span>
			                        <input type="text" id="bankCard" class="input-b" placeholder="请输入你的卡号" maxlength="25">
			                        <span class="error"></span>
		                        </div>
		                        
		                        <div class="clearfloat marginbottom20 relative"><span class="span-a">手机号</span>
			                        <input type="text" id="bankPhone" class="input-b" placeholder="请输入银行预留的手机号" maxlength="11">
			                        <span class="error"></span>
		                        </div>
		                        
		                        
		                        <div class="clearfloat marginbottom30 relative"><span class="span-a">验证码</span>
			                        <input type="text" class="input-c" id="verifyCode" placeholder="手机验证码">
			                        <button class="obtain-btn" type="button" id="sendBank">获取</button>
		                        </div>
		                        <span class="error" id="bankSmsError"></span>
		                    </div>
		                    
		                </div>
		            </div>
		            <input type="hidden" id="bankType" name="type" value="bank">
		            <div class="account-c">
		                <button class="confirm-btn" type="button">确认并添加</button>
		            </div>
	            </form>
	        </div>
	    </div>
	</div>
    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/withdraw/withdraw.js"></script>
    </@block>
</@extend>