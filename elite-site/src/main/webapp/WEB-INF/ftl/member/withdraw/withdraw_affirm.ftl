<@extend name="layout">
    <@block name="cs">
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/confirm.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
	<div class="yyh">
	    <div class="y-hd"></div>
	    <div class="y-bd">
	        <div class="bd-confirm">
	            <div class="bd-confirm-title">
	                <h1 style="margin-top: 0px;margin-bottom: 0px;">
	                <span style="cursor:pointer;" id="crumbs_main">个人主页</span>
	                <span class="paddinglr5">&gt;</span><span style="cursor:pointer;" id="seles_manage">结算管理</span><span class="paddinglr5">&gt;</span>
	                <span class="color2c">提现</span></h1>
	            </div>
	            <div class="bd-confirm-content">
	                <div class="bcc-a">
	                    <h2>确认提现信息</h2>
	                </div>
	                <div class="bcc-b">
	                    <div class="bcc-b-box">
	                        <div class="bcc-b-box-content">
	                            <p class="marginbottom20">
	                            	<span class="p_text">到账账号：</span>
	                                <#if obj.type=='alipay'>
	                                  <span class="font-size18 color4a marginleft15 marginright40">支付宝</span>
		                              <span class="font-size16 color4a marginright50">${obj.formatBankCard}</span>
		                              <span class="font-size16 color4a">${obj.formatHolder}</span>
		                            <#elseif obj.type=='bank'>
		                              <span class="font-size18 color4a marginleft15 marginright40">${obj.bankName}</span>
		                              <span class="font-size16 color4a marginright50">${obj.formatBankCard}</span>
		                              <span class="font-size16 color4a">${obj.formatHolder}</span>
	                                </#if>
		                           
	                            </p>
	                            
	                            <p class="marginbottom20"><span class="p_text">提现金额：</span><span class="marginleft15 font-size20 colorfe" id="wamount">￥${withdrawAmount}</span></p>
	                            
	                            <div class="actual_money">
			                    	<div class="money_text">实际到账：</div>
			                    	<div class="money_number">
			                    		<div class="div1 clearfloat"><span>￥${receiptAmount}</span><img src="${_PATH}/res/images/ceo/questionMark.png" alt=""></div>
			                    		<div class="div2">
			                    			<#--<span>1. 扣除个人劳动所得税：￥${tax}。</span>--> 
			                    			<span>1. 单笔提现费用：2元（单笔提现＜￥1000时）</span>  
			                    		</div>
			                    	</div>
	                    		</div>
	                            
	                            <p class="marginbottom20">
	                            	<span class="p_text">到账时间：</span>
	                            	<span class="marginleft15 colorfe font-size16"><#if date??>${date?string("yyyy年MM月dd号")}<#else>立即到账</#if></span>
	                            	<span class="time_note">（7个工作日）</span>
	                            </p>
	                        </div>
	                    </div>
	                </div>
	                <div class="bcc-c">
	                    <span class="code-name">账户密码</span>
	                    <input type="hidden" id="bankId" value="${obj.id}">
	                    <input type="hidden" id="withdrawAmount" value="${withdrawAmount}">
	                    <input type="hidden" id="receiptAmount" value="${receiptAmount}">
	                    <input type="hidden" id="invoiceWay" value="${invoiceWay}">
	                    <input type="password" placeholder="请输入账户登录密码" class="code-content" id="password">
	                    <a href="${_PATH}/forget/p" class="code-forget">忘记密码？</a>
	                </div>
	                <div class="bcc-d">
	                    <div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error_text">错误提示</span></div>
	                    <button type="button" class="confirm-btn">确认提现</button>
	                    <a class="confirm-return" href="javascript:history.back(-1)">返回修改</a>
	                </div>
	            </div>
	        </div>
	        <div class="window-confirm" style="display:none;">
	            <div class="window-confirm-box">
	                <div class="window-confirm-box-a">
	                    <span class="wcba-icon"></span>
	                    <span class="wcba-text">提现申请已提交</span>
	                </div>
	                <div class="window-confirm-box-b">
	                    <span>耐心等待哦，您将在约定时间内收到提现款项</span>
	                </div>
	                <div class="window-confirm-box-c">
	                    <div class="wcbc-text">
	                        <span class="colorfe" id="down_time"></span>
	                        <span>后回到结算管理</span>
	                    </div>
	                    <a href="javascript:void(0);" class="backButton">立即返回</a>
	                </div>
	            </div>
	        </div>
	    </div>
	    <div class="y-ft"></div>
	</div>
    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/withdraw/withdrawAffirm.js"></script>
    </@block>
</@extend>