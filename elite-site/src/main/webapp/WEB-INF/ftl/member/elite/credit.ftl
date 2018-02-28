<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/register/eliteCredit.css">
    </@block>

    <#--中间正文部分-->
    <@block name="content">

    <div class="process" id="eliteCredit_process">
    	<div class="box-container-elite">
        <div class="process-box-elite">
            <div class="process-circle active-process">
                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:269px;">
                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:534px;">
                <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:805px;">4</div>
            <p class="process-title active-title">当前状态</p>
            <p class="process-title active-title" style="left:266px;">基本信息</p>
            <p class="process-title active-title" style="left:532px;">技能<span>|</span>经历</p>
            <p class="process-title active-title" style="left:799px;">个人征信</p>
            <div class="process-line finish-line"></div>
            <div class="process-line finish-line" style="left:337px;"></div>
            <div class="process-line finish-line" style="left:602px;"></div>
        </div>
        </div>
        
    </div>

    <div class="content" id="eliteCredit_content">
    	<div class="content_credit">
        <form class="form-group form-horizontal form-person" role="form" id="companyFrom" name="companyFrom" style="padding-left:0;">
             <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <div class="title-input"><span class="required">* </span> 真实姓名 <span class="secret-circle">密</span></div>
                </label>
                <div class="col-xs-4 col-md-4">
                    <input class="form-control input-unified width300"  style="width:300px;" type="text" id="realName"  name="realName" placeholder="与身份证上姓名保持一致" maxlength="10" value="${obj.realName}">
                </div>
            </div>
        
            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <div class="title-input"><span class="required">* </span> 身份证号 <span class="secret-circle">密</span></div>
                    <input type="hidden" name="id" value="${obj.id}">
	                <input type="hidden" name="cardJust" id="cardJust">
	                <input type="hidden" name="cardReverse" id="cardReverse">
	                <input type="hidden" name="businessCert" id="businessCert">
	                <input type="hidden" name="jobCert" id="jobCert">
	                <input type="hidden" name="visitingCert" id="visitingCert">
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control input-unified width400" type="tel" id="idCard" name="idCard" placeholder="18位二代身份证号" maxlength="18" style="width:400px;">
                </div>
            </div>

            <div class="form-group" style="margin-bottom: 10px;">
                <label class="col-xs-2 col-md-2 control-label"><div class="title-input">上传认证资料<span class="secret-circle" style="right:-10px;">密</span></div></label>
            </div>

            <div class="theme-line-credit"></div>

            <div class="photos">
                <div class="photo-row">
                   <div class="photo-box">
                        <p><span class="required">* </span> 身份证正面</p>
                        <div class="photo" onclick="document.companyFrom.cardJustFile.click()">
                            <img src="${_PATH}/res/images/elite/ID_card.png" class="photo-add" id="cardJustImg">
                            <input type="file" name="file" id="cardJustFile" style="display:none;">
                        </div>
                    </div>
                    <div class="photo-box">
                        <p><span class="required">* </span> 身份证反面</p>
                        <div class="photo" onclick="document.companyFrom.cardReverseFile.click()">
                           <img src="${_PATH}/res/images/elite/ID_back.png" class="photo-add" id="cardReverseImg">
                           <input type="file" name="file" id="cardReverseFile" style="display:none;">
                        </div>
                    </div>
                </div>

                <div class="photo-row" style="margin-top:20px;min-height:20px;height:auto!important">
                    <div class="photo-box" style="min-height:20px;height:auto!important">
                        <div class="other_p">其他技能证书<img src="${_PATH}/res/images/ceo/other-icon.png" id="otherOper"></div>
                        <div class="photo" onclick="document.companyFrom.businessCertFile.click()" id="otherDiv" style="display:none;">
                           <img src="${_PATH}/res/images/elite/ID_certificate.png" class="photo-add" id="businessCertImg">
                           <input type="file" name="file" id="businessCertFile" style="display:none;">
                        </div>
                    </div>

                    <#--<div class="photo-box">
                        <p>工作证正面</p>
                        <div class="photo" onclick="document.companyFrom.jobCertFile.click()">
                           <img src="${_PATH}/res/images/target_icon.png" class="photo-add" id="jobCertImg">
                           <input type="file" name="file" id="jobCertFile" style="display:none;">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p>名片正面</p>
                        <div class="photo" onclick="document.companyFrom.visitingCertFile.click()">
                           <img src="${_PATH}/res/images/target_icon.png" class="photo-add" id="visitingCertImg">
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
            
            

            <div class="form-opt" style="padding-left:0;">
            	<div class="error-tip">
	                <span id="tipError"></span>
	            </div>
            	<a href="javascript:void(0);" class="back_btn" id="prevBtn">上一步</a>
                <button type="button" class="btn-ok-credit" id="saveBtn">创建完成！去认领任务</button>
                <a href="javascript:void(0);" class="skip_btn" id="skip">暂时跳过</a>
            </div>
        </form>
        
        
        <div class="window-confirm" style="display:none;">
            <div class="window-confirm-box">
                <div class="window-confirm-box-a">
                    <span class="wcba-icon"></span>
                    <span class="wcba-text">恭喜！你已成功注册云英汇账号！</span>
                </div>
                
                <div class="window-confirm-box-c">
                    <div class="wcbc-text">
                        <span class="colorfe" id="down_time"></span>
                        <span>后回到个人主页</span>
                    </div>
                    <a href="javascript:void(0);" class="backButton">立即进入个人主页</a>
                </div>
                
            </div>
        </div>
     </div>
        

	</div>
    </div>
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/elite/credit.js"></script>
    </@block>
</@extend>