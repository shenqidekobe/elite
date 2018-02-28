<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/pay/pay.css">
    </@block>
    <@block name="body">

    <#--头部-->
    <@accounthead opt=""/>


<div id="submit_intention">

    <div class="content">
        <div class="flag">
            <span><span style="cursor:pointer;" id="crumbs_main">个人主页</span> &gt; 
	            <span style="color:#2CB7C9;cursor:pointer;" id="myProject">我的项目</span> &gt; 
	            <span style="color:#2CB7C9;cursor:pointer;" id="projectDetail" data="${obj.id}">项目详情</span> &gt; 
	            <span style="color:#2CB7C9;cursor:pointer;">提交项目意向金</span>
            </span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
			    <div class="process-circle <#if obj.status!='wait_audit'>active-process</#if>" style="left:230px;">2</div>
			    <div class="process-circle <#if obj.status!='audit_in'&&obj.status!='wait_audit'>active-process</#if>" style="left:440px;">3</div>
			    <div class="process-circle <#if obj.status!='pass_wait'&&obj.status!='audit_in'&&obj.status!='wait_audit'>active-process</#if>" style="left:650px;">
			        <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
			    </div>
			    <p class="process-title active-title">待审核</p>
			    <p class="process-title <#if obj.status!='wait_audit'>active-title</#if>" style="left:224px;">审核中</p>
			    <p class="process-title <#if obj.status!='audit_in'&&obj.status!='wait_audit'>active-title</#if>" style="left:410px;">审核通过,待立项</p>
			    <p class="process-title <#if obj.status!='pass_wait'&&obj.status!='audit_in'&&obj.status!='wait_audit'>active-title</#if>" style="left:636px;">立项成功</p>

                <div class="process-line finish-line"></div>
                <div class="process-line <#if obj.status!='audit_in'&&obj.status!='wait_audit'>finish-line</#if>" style="left:280px;"></div>
                <div class="process-line <#if obj.status!='pass_wait'&&obj.status!='audit_in'&&obj.status!='wait_audit'>finish-line</#if>" style="left:490px;"></div>

                <p class="process-tip">项目经理对接</p>
                <p class="process-tip" style="left:310px;color:#FEA600;">提交项目意向金</p>
                <p class="process-tip" style="left:524px;">核实立项确认书</p>
            </div>

            <p class="p1"><#--意向金服务对象--></p>
            <div class="project-box">
                <div style="position: relative;">
                        <div class="part1">
                            <div class="col-xs-3 col-md-3">
                                <span>创建时间：${obj.createTime?string("yyyy-MM-dd HH:mm")}</span>
                            </div>
                            <div class="col-xs-9 col-md-9">
                                <span class="pull-left">项目编号：${obj.projectNum}</span>
                            </div>
                        </div>
                        <div class="vertical-line"></div>
                    </div>

                <div class="project-detail">
                        <div class="main-info">
                            <div style="padding:20px;">
                                <div class="project-logo" style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
                                <div class="project-info">
                                    <div>
                                        <span class="project-name"><#if obj.name?length gt 12>${obj.name?substring(0,12)}...<#else>${obj.name}</#if></span>
                                        <#--<div class="class-label">电商</div><div class="class-label">社交</div>-->
                                    </div>
                                    <div style="margin-top:10px;">
	                                    <span class="index">开发类型:</span>&nbsp;
	                                    <span class="value"><#if obj.solutionVals?length gt 24>${obj.solutionVals?substring(0,24)}...<#else>${obj.solutionVals}</#if></span>
                                    </div>
                                    <div>
	                                    <span class="index">预算:</span>&nbsp;
	                                    <span class="value">${obj.projectBudget}</span>
                                    </div>
                                    <div>
	                                    <span class="index">期望交付日期:</span>&nbsp;
	                                    <span class="value"><#if obj.expectTime??>${obj.expectTime?string("yyyy-MM-dd")}</#if> &nbsp;共${obj.deliveryDay?string('#')}个工作日</span>
                                    </div>
                                </div>

                            </div>
                        </div>
                        <div class="status">
                            <div style="padding-top:36px;padding-left:50px;">
                                <p style="color:#9B9B9B;"></p>
                                <p><span class="index">已提交意向金：</span>&nbsp;<span class="value">￥${obj.intentionAmount}</span></p>
                                <p><span class="index">股权托管：</span>&nbsp;<span class="value">无</span></p>
                            </div>
                        </div>
                        <div class="opt">
                            <div style="padding-top:60px;">
                                <span>项目状态：</span>
                                <#assign statusLabel=""/>
				                <#if obj.status=='wait_audit'>
				                    <#assign statusLabel="待审核"/>
				                <#elseif obj.status=='audit_in'>
				                    <#assign statusLabel="审核中"/>
				                <#elseif obj.status=='pass_wait'>
				                    <#assign statusLabel="审核通过待立项"/>
				                <#elseif obj.status=='unpass'>
	                                <#assign statusLabel="审核未通过"/>
				                <#elseif obj.status=='pass_already'>
				                    <#assign statusLabel="已立项"/>
				                </#if>
                                <span style="color:#2CB7C9;">${statusLabel}</span>
                            </div>
                        </div>
                    </div>
            </div>

            <div class="deposit">
                <div class="money">
                	<span class="money_text">意向金金额：</span>
                	<span class="deposit-count">￥${intentionAmount}</span>
                </div>
                <div class="note1 note">
                    <span class="note_icon"></span><span class="note_text">意向金可充当项目费用</span>
                </div>
                <div class="note2 note">
                	<span class="note_icon"></span><span class="note_text">意向金仅代表你的意向,项目未立项成功可退款</span>
                </div>
            </div>

            <p class="p2">选择支付方式</p>
            <div class="pay-box">
                <img src="${_PATH}/res/images/ceo/check.png" class="radio-box" data="alipay">
                <div class="two-ways current-way" data="alipay">
                    <img src="${_PATH}/res/images/ceo/alipay.png" class="ali-pay">
                </div>
                
                <img src="${_PATH}/res/images/ceo/check_no.png" class="radio-box" style="left:250px;" data="wx_pub_qr">
                <div class="two-ways" style="left:280px;" data="wx_pub_qr">
	                <img src="${_PATH}/res/images/ceo/wechat_pay.png" class="bank-card">
                </div>
                
                <img src="${_PATH}/res/images/ceo/check_no.png" class="radio-box" style="left:490px;" data="upacp_pc">
                <div class="two-ways" style="left:530px;" data="upacp_pc">
	                <img src="${_PATH}/res/images/ceo/unionpay.png" class="bank-card">
                </div>
                
                <img src="${_PATH}/res/images/ceo/check_no.png" class="radio-box" style="left:760px;" data="cp_b2b">
                <div class="two-ways" style="left:800px;" data="cp_b2b">
	                <img src="${_PATH}/res/images/ceo/companypay.png" class="company-card">
                </div>
                                
            </div>

            <div style="margin-top:20px;display:none;" id="bank_transfer">
                <p style="font-size: 12px;color:#9B9B9B;">收款账户</p>
                <div class="account-box form-horizontal">
                    <div style="margin-top:20px;">
                        <label class="col-xs-2 col-md-2 control-label">收款账户</label>
                        <div class="col-xs-10 col-md-10">
                            <div style="line-height: 34px;color:#2CB7C9;">
                                <span>宁波乐道网络科技有限公司</span>
                                <span style="margin-left:20px;">1001 8078 1930 0055 574</span>
                                <span style="margin-left:20px;">工商银行华江路支行</span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="certificate">
            	<div class="show_div" id="invoice_show"><img src="${_PATH}/res/images/ceo/show_img.png" id="invoice_ask" class="show_img"><span class="show_txt">索要发票</span></div>
            	<div class="certificate-box hide_div" style="display:none;">
            		<form>
            			<p><span class="p_index">发票：</span><span class="p_value">纸质发票</span></p>
		                <p><span class="p_index">发票类型：</span><span class="p_value">服务费</span></p>
		                <p class="input_div"><span class="input_index">发票抬头</span><input type="text" class="invoice_value" id="invoice_value"></p>
		                <div class="btnBox">
		                	<div class="error_div" style="display:none;"><span class="error_icon"></span><span class="error_text">请填写发票抬头</span></div>
		                	<button type="button" class="save" id="save_btn">保存</button>
		                	<button type="button" class="cancel" id="cancel_btn">取消</button>
		                </div>
            		</form>
            	</div>
            </div>
            
            <#if payFlag!='yet'>
                 <button type="button" class="goto-pay-btn" data="${obj.id}">去支付</button>
            </#if>

            <form id="payForm" method="POST" action="${_PATH}/project/alipay/pay" target="_blank" >
                <input type="hidden" name="type" id="_type" value="intention">
                <input type="hidden" name="projectId" id="_projectId" value="${obj.id}">
                <input type="hidden" name="channel" id="_channel" value="alipay">
                <input type="hidden" name="invoiceRise" id="_invoiceRise">
                <input type="hidden" name="invoiceAddress" id="_invoiceAddress">
                <input type="hidden" name="invoiceName" id="_invoiceName">
                <input type="hidden" name="invoicePhone" id="_invoicePhone">
             </form>
        </div>
    </div>
    
    <div class="modal fade modal4login" id="modal-pay" role="dialog" tabindex="-1"  aria-labelledby="myModalLabel" data-backdrop="false" aria-hidden="true">
	    <div class="modal4login-overlay"></div>
	    <div class="modal-dialog modal4login-dialog">
	        <div class="modal-content modal4login-content">
	            <div class="modal-header modal4login-header">
	                <h4 class="modal-title" id="myModalLabel">支付中</h4>
	            </div>
	            <div class="modal-body modal4login-body">
                    <div class="btn-wrap">
                        <button type="button" id="complete-pay-btn">支付完成</button>
                    </div>
	            </div>
	        </div>
	    </div>
	</div>
    
    
    <#--支付成功失败弹窗-->
    <div class="payWindow" style="display:none;">
	    <div class="payForm">
	        <div class="div1">
	            <!--  支付成功为successLogo successColor ；失败为 failureLogo     failureColor       -->
	            <span id="head_prompt" class="successLogo logo"></span>
	            <span id="text_prompt" class="successColor text">支付成功</span>
	        </div>
	        <div class="div2">
	            <span class="time">6s</span>
	            <span class="text">后回到个人主页</span>
	        </div>
	        <div class="div3">
	            <button type="button" id="returnBtn" class="returnBtn">立即返回</button>
	        </div>
	    </div>
    </div>
    
    
    <#--微信支付弹窗-->
    <div class="payWindow" style="display:none;" id="wxpay">
	    <div class="payForm" style="width:350px;height:300px;margin-left:-200px;">
	     <h3 style="margin-left:10px;">微信支付</h3>
	     <div class="payClose"></div>
	        <div class="div1" style="align:center;left:100px;margin-top:25px;">
	            <img id="wxpay_img"/>
	            <p style="margin-top:10px;" id="payText">请尽快使用 微信 <br/>扫描二维码完成支付</p>
	        </div>
	    </div>
    </div>
    
</div>
    </@block>
    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/company/global/intention.js"></script>
    </@block>
</@extend>