<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/company/companyContainer.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/register/projectCredit.css"/>
    </@block>

<#--中间正文部分-->
    <@block name="content">

    <div class="process" id="projectCredit_process">
	    <div class="process-box-container">
	        <div class="process-box">
	            <div class="process-circle active-process">
	                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
	            </div>
	            <div class="process-circle active-process" style="left:274px;">
	                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
	            </div>
	            <div class="process-circle active-process" style="left:539px;">3</div>
	            <p class="process-title active-title">基本资料</p>
	            <p class="process-title active-title" style="left:266px;">创业属性</p>
	            <p class="process-title active-title" style="left:532px;">征信信息</p>
	
	            <div class="process-line finish-line"></div>
	            <div class="process-line finish-line" style="left:340px;"></div>
	        </div>
	    </div>
    </div>

    <div class="content" style="margin-top: -100px;" id="projectCredit_content">
        <form class="form-group form-horizontal form-person paddingtop60" role="form" id="companyFrom" name="companyFrom">
        <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
	                <div class="title-input">
	                    <span class="required">* </span> 真实姓名 <span class="secret-circle">密</span>
	                </div>    
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control input-unified width300" type="text" id="realName" name="realName" placeholder="与身份证上姓名保持一致" maxlength="10" value="${obj.realName}">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
	                <div class="title-input">
	                    <span class="required">* </span> 身份证号 <span class="secret-circle">密</span>
	                    <input type="hidden" name="id" value="${obj.id}">
		                <input type="hidden" name="cardJust" id="cardJust">
		                <input type="hidden" name="cardReverse" id="cardReverse">
		                <input type="hidden" name="businessCert" id="businessCert">
		                <input type="hidden" name="jobCert" id="jobCert">
		                <input type="hidden" name="visitingCert" id="visitingCert">
		            </div>    
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control input-unified width300" type="tel" id="idCard" name="idCard" placeholder="18位二代身份证号" maxlength="18">
                </div>
            </div>

            <div class="form-group" style="margin-bottom: 10px;">
                <label class="col-xs-2 col-md-2 control-label">
		                 <div class="title-input">
		                                    上传认证资料<span class="secret-circle" style="right:-10px;">密</span>
		                </div>
                </label>
            </div>

           <div class="theme-line"></div>

            <div class="photos">
                <div class="photo-row">
                    <div class="photo-box">
                        <p><span class="required">* </span> 身份证号正面</p>
                        <div class="photo" onclick="document.companyFrom.cardJustFile.click()">
                            <img src="${_PATH}/res/images/project/ID_front.png" id="cardJustImg">
                            <input type="file" name="file" id="cardJustFile" style="display:none;">
                        </div>
                    </div>
                    <div class="photo-box">
                        <p><span class="required">* </span> 身份证号反面</p>
                        <div class="photo" onclick="document.companyFrom.cardReverseFile.click()">
                           <img src="${_PATH}/res/images/project/ID_back.png"  id="cardReverseImg">
                           <input type="file" name="file" id="cardReverseFile" style="display:none;">
                        </div>
                    </div>
                </div>

                <div class="photo-row" style="margin-top:20px;">
                    <div class="photo-box">
                        <p>营业执照</p>
                        <div class="photo" onclick="document.companyFrom.businessCertFile.click()">
                           <img src="${_PATH}/res/images/project/license.png"  id="businessCertImg">
                           <input type="file" name="file" id="businessCertFile" style="display:none;">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p>工作证或名片</p>
                        <div class="photo" onclick="document.companyFrom.jobCertFile.click()">
                           <img src="${_PATH}/res/images/project/card.png"  id="jobCertImg">
                           <input type="file" name="file" id="jobCertFile" style="display:none;">
                        </div>
                    </div>

                    <#--<div class="photo-box">
                        <p>名片正面</p>
                        <div class="photo" onclick="document.companyFrom.visitingCertFile.click()">
                           <img src="${_PATH}/res/images/project/ID_front.png"  id="visitingCertImg">
                           <input type="file" name="file" id="visitingCertFile" style="display:none;">
                        </div>
                    </div>-->
                    
                </div>

            </div>

            <#--<div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <span class="required">* </span> 支付宝账号 <span class="secret-circle">密</span>
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="alipayAccount" name="alipayAccount" placeholder="只调用支付宝的信用积分，不影响账户" maxlength="18">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <span class="required">* </span> 银行卡绑定 <span class="secret-circle">密</span>
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="bankCard" name="bankCard" placeholder="用于个人征信及项目账款的结算" maxlength="24">
                </div>
            </div>-->
            
            
            

            <div class="form-opt">
            	<div class="error_div" style="display:none;">
            		<span class="error_icon"></span>
	                <span id="tipError" class="error_text"></span>
	            </div>
                <a href="javascript:void(0);" style="color: #FEA600;font-size: 18px" id="prevBtn">上一步</a>
                <button type="button" class="btn-ok-credit" id="saveBtn">创建完成</button>
                <a href="javascript:void(0);" style="font-size: 14px;" id="skip" class="color2c">暂时跳过，去发布项目</a>
            </div>
        </form>
        
        
        
        
		<div class="window-confirm" style="display:none;" >
		    <div class="window-confirm-box">
		        <div class="window-confirm-box-a">
		            <span class="wcba-icon"></span>
		            <span class="wcba-text">恭喜！你已成功注册云英汇账号！</span>
		        </div>
		
		        <div class="window-confirm-box-c">
		            <div class="wcbc-text">
		                <span class="colorfe" id="down_time"></span>
		                <span>后进入个人主页</span>
		            </div>
		            <a href="javascript:void(0);" class="backButton">去发布项目</a>
		        </div>
		
		    </div>
		</div>
		</div>
        
        


    </div>
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/company/credit.js"></script>
    </@block>
</@extend>