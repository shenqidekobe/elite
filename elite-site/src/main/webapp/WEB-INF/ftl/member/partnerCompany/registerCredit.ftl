<@extend name="layout">
    <@block name="cs">
      <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/partner_main.css"/>
      <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
      <link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
      <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/partnerCompany/registerBasic.css"/>
    </@block>
    <@block name="body">
    <#--头部-->
         <@accounthead opt=""/>

<!--项目推荐方-资料完善-->
<div id="registerBasic">
<div class="recommend-form-box">
<div class="recommend-form" id="personnelStep1" style="height:680px;">
	<div class="processBox">
        	<div class="ball1 ball">1</div>
        	<div class="ball2 ball"  style="border:1px solid #2cb7c9;color:#2cb7c9;">2</div>
        	<div class="line"></div>
        	<div class="text1 text">基本信息</div>
        	<div class="text2 text" style="color:#2cb7c9;">征信信息</div>
    </div>
    <div class="form-box">
        <form role="form" id="personnel-form2" name="creditForm">
         <div class="group clearfloat">
                <label class="control-label"><span class="required">* </span>你的姓名</label>
                <div class="email_input">
                    <input class="form-control" type="text"  name="realName" id="realNameId" placeholder="请输入您的姓名" maxlength="6" style="width:300px;">
                </div>
            </div>
            <div class="group clearfloat">
                <label class="control-label"><span class="required">* </span>身份证号</label>
                <div class="company_input">
                    <input class="form-control" type="text" name="idCard" id="idCardId"  placeholder="请输入您的身份证号" maxlength="18" style="width:300px;">
                </div>
            </div>
            
            
            <div class="photoBox clearfloat">
            	<div class="font">
            		<span>上传身份证正面</span>
            		<img class="boxt" onclick="document.creditForm.cardJustFile.click()" id="cardJustImg" src="${path}/res/images/partner/ID_front.png">
	                    <input type="file" name="file" id="cardJustFile" style="display:none;">
                        <input type="hidden" name="cardJust" id="cardJustId" />
	                  </img>
            	</div>
            	<div class="back">
            		<span>上传身份证背面</span>
            		<img class="boxt" onclick="document.creditForm.cardReverseFile.click()" id="cardReverseImg" src="${path}/res/images/partner/ID_front.png">
	                    <input type="file" name="file" id="cardReverseFile" style="display:none;">
                        <input type="hidden" name="cardReverse" id="cardReverseId" />
	                  </img>
            	</div>
            </div>
        </div>
            <div class="next-step" style="margin-left: -90px;">
            	<div class="error-tip"></div>
            	<a href="${_PATH}/partner/partnerCompany/basic/register/view" class="back_btn">上一步</a>
                <button type="button" class="saveBtn" id="saveCreditBtn">创建完成</button>
                <a href="${_PATH}/member/index?rp=inviteRegister" class="skip_btn">先跳过，直接去备案项目</a>
            </div>
    </form>
</div>
</div>
</div>
<!--项目推荐方-资料完善-->

    <#--正文开始-->
    </@block>
    <@block name="script">
 <script  src="${_PATH}/res/script/myjs/member/partnerCompany/registerCredit.js"></script>
    
    </@block>
</@extend>