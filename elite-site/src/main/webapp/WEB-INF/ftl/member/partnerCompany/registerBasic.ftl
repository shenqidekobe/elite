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
<div class="recommend-form" id="personnelStep1">
	<div class="processBox">
        	<div class="ball1 ball">1</div>
        	<div class="ball2 ball">2</div>
        	<div class="line"></div>
        	<div class="text1 text">基本信息</div>
        	<div class="text2 text">征信信息</div>
    </div>
    <div class="form-box">
        <div role="form" id="personnel-form1">
        
            <input type="hidden" id="title" value="person">
            
            <div class="group clearfloat" style="padding-top:30px;">
                    <label class="control-label"><span class="required">* </span>所在城市</label>
                    <div class="city_input">
                         <div class="birth-box cityContainer" >
                              <input class="form-control" type="text" id="areaName" name="areaName" placeholder="选择所在城市" style="background-color: white;padding-left:38px;width:300px;" value="${basic.areaName}">
                              <img src="${_PATH}/res/images/location_icon.png" class="location" id="cityIcon" >
                          <div>     
                     </div>
             </div>
        </div>
        </div>
           	<div class="group clearfloat">
                <label class="control-label label_sex"><span class="required">* </span> 性别<span class="secret-circle"></span></label>
                <div class="group-years group-yea">
                  <#if basic.sex=='F'>
                   	<button class="rdio man_radio " type="text" id="sex" value="M"><span class="man_noselect radio-male"></span>男</button>
                	<button class="rdio woman_radio active_radio" type="text" style="margin-left:15px;" id="sex" value="F"><span class="woman_select radio-male"></span>女</button>
                     <input type="hidden" id="sexId" value="F"/>
                    <#else>
	                <button class="rdio man_radio active_radio" type="text" id="sex" value="M"><span class="man_select radio-male"></span>男</button>
                	<button class="rdio woman_radio" type="text" style="margin-left:15px;" id="sex" value="F"><span class="woman_noselect radio-male"></span>女</button>
                    <input type="hidden" id="sexId" value="M"/>
                    </#if>
                        </div>
            </div>
            <div class="group clearfloat">
                <label class="control-label"><span class="required">* </span>绑定邮箱</label>
                <div class="email_input">
                    <input class="form-control" type="text" id="emailId" name="email" value="${basic.email}" placeholder="请输入您的邮箱账号" maxlength="32" style="width:400px;">
                </div>
            </div>
            
            <div class="group clearfloat">
                <label class="control-label">机构名称</label>
                <div class="company_input">
                    <input class="form-control" type="text" id="companyNameId" name="compnayName" value="${company.companyName}" placeholder="请输入您的机构名称" maxlength="32" style="width:400px;">
                </div>
            </div>
            <div class="next-step">
            	<div class="error-tip"></div>
                <button type="button" class="next-step-btn" id="updateBasic">保存并提交</button>
                <a href="${_PATH}/member/index?rp=inviteRegister" class="skip_btn">先跳过，直接去备案人才</a>
            </div>
            
        </div>
    </div>
</div>
</div>
</div>
<!--项目推荐方-资料完善-->

    <#--正文开始-->
   
    </@block>

    <@block name="script">
    <script  src="${_PATH}/res/script/myjs/member/partnerCompany/register.js"></script>
    </@block>
</@extend>