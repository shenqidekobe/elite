<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/pay/pay.css">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/pay/trustCost.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>

    <div class="content" id="trustCost_content">
        <div class="flag">
            <span><span style="cursor:pointer;" id="crumbs_main">个人主页</span> &gt; 
	            <span style="color:#2CB7C9;cursor:pointer;" id="myProject">我的项目</span> &gt; 
	            <span style="color:#2CB7C9;cursor:pointer;" id="projectDetail" data="${obj.id}">项目详情</span> &gt; 
	            <span style="color:#2CB7C9;cursor:pointer;">托管费用</span>
            </span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <#--阶段超过三个的时候，减少间隔-->
                <#assign sums=0/>
                <#if obj.projectDefine??>
                   <#if obj.projectDefine.stages?size lt 3>
                       <#assign sums=50/>
                   </#if>
                </#if>
                <#assign left=140+sums/>
                <#assign step=1/>
                <#assign activeStep=""/>
                <#if obj.projectDefine??>
                   <#list obj.projectDefine.stages as pds>
                      <#assign step=step+1/>
                      <#if obj.trustStageCount gt pds_index>
	                        <#assign activeStep="active-process"/>
                       </#if>
                      <div class="process-circle ${activeStep}" style="left:${left}px;">${step}</div>
                      <#assign left=left+120+sums/>
                      <#assign activeStep=""/>
                   </#list>
                </#if>
                <div class="process-circle <#if obj.status=='quality'>active-process</#if>" style="left:${left}px;">
                    <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">立项</p>
                <#assign leftq=118+sums/>
                <#assign activeTitle=""/>
                <#if obj.projectDefine??>
                   <#list obj.projectDefine.stages as pds>
                      <#if obj.trustStageCount gt pds_index>
	                       <#assign activeTitle="active-title"/>
                      </#if>
                      <p class="process-title ${activeTitle}" style="left:${leftq}px;">${pds.title}</p>
                      <#assign leftq=leftq+122+sums/>
                      <#assign activeTitle=""/>
                   </#list>
                </#if>
                <p class="process-title" style="left:${leftq}px;">质保</p>
                
                <div class="process-line finish-line"></div>
                <#assign leftw=190+sums/>
                <#assign active=""/>
                <#if obj.projectDefine??>
                   <#list obj.projectDefine.stages as pds>
	                   <#if obj.trustStageCount gt pds_index>
	                        <#assign active="finish-line"/>
                       </#if>
                       <div class="process-line ${active}" style="left:${leftw}px;"></div>
                       <#assign leftw=leftw+120+sums/>
                       <#assign active=""/>
                   </#list>
                </#if>
                
                <#assign trustStage=obj.trustStage/>
                <#assign tipLeft=70/>
                <#if obj.projectDefine??&&obj.trustStageCount gt 0>
                   <#list 1..obj.trustStageCount as cc>
                       <#assign tipLeft=tipLeft+120+sums/>
                   </#list>
                </#if>
                <#if !trustStage.finished&&(obj.projectDefine.stages?size!=obj.trustStageCount)>
	                <div class="process-tip" style="color:#FEA600;left:${tipLeft}px;">
					                    托管${trustStage.title}<br>
					                    
					                    费用
	                </div>
                </#if>
            </div>

            <p style="margin-top:30px;"><#--费用托管对象--></p>
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
                            <div class="project-logo"  style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
                            <div class="project-info">
                                <div>
                                    <span class="project-name"><#if obj.name?length gt 12>${obj.name?substring(0,12)}...<#else>${obj.name}</#if></span>
                                    <#list obj.functionValList as pfc>
			                            <div class="class-label">${pfc}</div>
			                        </#list>
                                </div>
                                <div style="margin-top:10px;"><span class="index">开发类型:</span>&nbsp;<span class="value"><#if obj.solutionVals?length gt 24>${obj.solutionVals?substring(0,24)}...<#else>${obj.solutionVals}</#if></span></div>
                                <div><span class="index">费用:</span>&nbsp;<span class="value">${obj.totalAmount?string.currency}</span></div>
                                <div><span class="index">交付日期:</span>&nbsp;
                                <span class="value"><#if obj.expectTime??>${obj.expectTime?string("yyyy-MM-dd")}</#if>&nbsp;共${obj.deliveryDay?string('#')}个工作日</span></div>
                            </div>

                        </div>
                    </div>
                    <div class="status">
                        <div style="padding-top:36px;padding-left:50px;">
                            <p style="color:#9B9B9B;"></p>
                            <p><span class="index">已提交意向金：</span>&nbsp;<span class="value">${obj.intentionAmount?string.currency}</span></p>
                            <p><span class="index">股权托管：</span>&nbsp;<span class="value"><#if obj.isStock>有<#else>无</#if></span></p>
                        </div>
                    </div>
                    <div class="opt">
                        <div style="padding-top:46px;">
                            <p style="color:#2CB7C9;"><#if obj.forStage??>${obj.forStage.title}<#else>已立项</#if></p>
                            <a href="javascript:void(0);" id="projectDetail1" data="${obj.id}" target="_blank" style="font-size: 14px;">查看详情</a>
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top:20px;text-align: right;padding-right:20px;">
                <div class="demand_div">
	                <span>${trustStage.title}<#if !trustStage.finished>托管金额：</#if></span>
	                <span><#if trustStage.firstAmount??>${trustStage.firstAmount?string.currency}<#else>${trustStage.amount?string.currency}</#if></span>
                </div>
                <#if trustStage.firstAmount??>
	                <div class="money_div"><span class="text">第一阶段费用托管总额：</span><span class="number">${trustStage.amount?string.currency}</span></div>
	                <div class="money_div"><span class="text">您已提交意向金：</span><span class="number">${obj.intentionAmount?string.currency}</span></div>
	                <div class="money_div"><span class="text">本阶段还需托管：</span><span class="number">${trustStage.firstAmount?string.currency}</span></div>
                </#if>
            </div>

            <div class="pay_title">选择支付方式</div>
            <div class="pay-box" style="height:198px;">
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
                
                <img src="${_PATH}/res/images/ceo/check_no.png" class="radio-box" style="float:left;margin-top:80px" data="offline">
                <div class="two-ways" style="float:left;margin-top:80px" data="offline">
                    <img src="${_PATH}/res/images/ceo/offlinepay.png" class="offline-pay">
                </div>
            </div>

            <div id="gatheringDiv" class="accountBox" style="display:none;">
                <div class="account clearfloat">
                	<div class="account_text">收款账户：</div>
                	<div class="account_number">
                		<span>宁波乐道网络科技有限公司</span><span>1001&nbsp;8078&nbsp;1930&nbsp;0055&nbsp;574</span><span>工商银行华江路支行</span>
                	</div>
                </div>
                <div class="note">
                	<span class="note_icon"></span>
                	<span class="note_text">线下打款完成请给您的项目经理提供：流水号、打款账号</span>
                </div>
            </div>

		    <div class="invoiceBox">
	            <div class="show_div" id="invoice_ask">
	       			<img src="/res/images/ceo/show_img.png" class="show_img"><span class="show_txt">索要发票</span>
	            </div>
	            <div class="certificate-box hide_div" style="display:none;" id="invoiceDiv">
	                <p><span class="index">发票：</span><span class="value">纸质发票</span></p>
	                <p><span class="index">发票类型：</span><span class="value">服务费</span></p>
	                <form>
		            	<div class="form_group"><span class="input_title">发票抬头</span><input type="text" id="invoiceRise" data="lookUp" maxlength="64"></div>
		            	<div class="form_group"><span class="input_title">邮寄地址</span><input type="text" id="invoiceAddress" data="address" maxlength="100"></div>
		            	<div class="form_group"><span class="input_title">收件人姓名</span><input type="text" id="invoiceName" data="name" maxlength="32"></div>
		            	<div class="form_group"><span class="input_title">联系方式</span><input type="text" id="invoicePhone" data="phone" maxlength="20"></div>
		            	<div class="btnDiv">
		            		<div class="error_div" style="display:none;">
		            			<span class="error_icon"></span>
		            			<span class="error_text"></span>
		            		</div>
		            		<button type="button" class="saveBtn" id="saveBtn">保存</button>
		            		<button type="button" class="cancelBtn" id="cancelBtn">取消</button>
		            	</div>
	            	</form>
	             </div>
             </div>
             <#if payFlag!='yet'>
                 <button type="button" class="goto-pay-btn" data="${obj.id}">去支付</button>
             </#if>
             <form id="payForm" method="POST" action="${_PATH}/project/alipay/pay" target="_blank" >
                <input type="hidden" name="type" id="_type" value="prostage">
                <input type="hidden" name="projectId" id="_projectId" value="${obj.id}">
                <input type="hidden" name="channel" id="_channel" value="alipay">
                <input type="hidden" name="invoiceRise" id="_invoiceRise">
                <input type="hidden" name="invoiceAddress" id="_invoiceAddress">
                <input type="hidden" name="invoiceName" id="_invoiceName">
                <input type="hidden" name="invoicePhone" id="_invoicePhone">
             </form>
         
            
         <div class="payBank" style="display:none;">
			<div class="payBank_box" >
			    <#--<div class="payClose"></div>-->
			    <div class="box_a_box">
			    	<div class="box-a clearfloat">
				        <span class="box-a-logo successLogo"></span>
				        <span class="box-a-txt color2c">您已选择：银行转账（线下打款）</span>
				    </div>
			    </div>
			    <div class="box-b">
			        <div class="box-b-content">
			                                     完成后记得提供
			            <span class="colorfe"> 转账流水号、打款账号</span>
			                                       给您的项目经理哦~
			        </div>
			    </div>
			    <div class="box-c">
			        <div class="box-c-box">
			            <div class="box-c-l">
			                <span class="colorfe" id="down_time"></span>
			                <span class="bo-c-txt">回到个人主页</span>
			            </div>
			            <a class="box-c-content" href="javascript:void(0);">立即返回</a>
			        </div>
		        </div>
			</div>
        </div>
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

	<#--微信支付弹窗-->
    <div class="payWindow" style="display:none;" id="wxpay">
	    <div class="payForm" style="width:350px;height:300px;margin-left:-200px;">
         <h3 style="margin-left:10px;">微信支付</h3>
	     <div class="payClose"></div>
	        <div class="div1" style="align:center;left:100px;margin-top:25px;">
	            <img id="wxpay_img"/>
	            <p style="margin-top:10px;">请尽快使用 微信 <br/>扫描二维码完成支付</p>
	        </div>
	    </div>
    </div>
    </@block>
    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/company/global/cost.js"></script>
    </@block>
</@extend>