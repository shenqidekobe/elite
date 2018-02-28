<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/partner_main.css"/>
    <link rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
          <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/partnerElite/partnerElite_basic.css"/>
    </@block>
    <@block name="body">
    <#--头部-->
         <@accounthead opt=""/>
		<#--人才推荐方注册-基本信息开始-->
	<div id="partnerElite_basic">
		<div class="recommend-form-box">
			 <div class="recommend-form" id="personnelStep1" style="height:634px">
				        <div class="processBox">
				        	<div class="point1 point" style="border:1px solid #2cb7c9;color:#2cb7c9;">1</div>
				        	<div class="point2 point">2</div>
				        	<div class="point3 point">3</div>
				        	<div class="line1 line"></div>
				        	<div class="line2 line"></div>
				        	<div class="text1 text">基本信息</div>
				        	<div class="text2 text">行业信息</div>
				        	<div class="text3 text">征信信息</div>
				        </div>
				        <form name="personFrom" id="personFrom">
				        <!--隐藏数据-->
				           <input type="hidden"  id="photoId" name="photoId" value="${basic.photoId}">
				        	<div class="headBox">
				        		<div class="head_img">
				        		<#if basic.photoId??>
				        			<img src="${basic.memberPhoto.path}" alt="" id="headImg">
				        		<#else>
				        			<img src="${path}/res/images/partner/head.png" alt="" id="headImg">
				        		</#if>
				        		</div>
				        		<div class="modifyBtn">修改头像</div>
				        	</div>
				        	<input type="file" name="file" id="headFile" style="display:none;">
				        	<#--上传头像弹窗-->
                            <div class="headWindow" style="display:none;" save="N"></div>
                
				        	<div class="group clearfloat">
				        		<label class="labelDiv"><span class="must">*</span>身份类型</label>
				        		<div class="inputDiv" style="width:240px">
				        		    <#if elite.type??>
				        		    <input type="hidden"  name="type" value="${elite.type}" id="selectTypeValueId">
				        		    <input type="text" value="${elite.type.label}" id="selectTypeId" readonly><span class="down iconBtn" id="selectTypeIconId"></span>
				        		    <#else>
				        		    <input type="hidden"  name="type" value="company" id="selectTypeValueId">
				        		    <input type="text" value="猎头公司" id="selectTypeId" readonly><span class="down iconBtn" id="selectTypeIconId"></span>
				        			</#if>
				        			<ul class="selectID_ul" hidden id="selectTypeUlId">
				        				<li data="company">猎头公司</li>
				        				<li data="person">个人猎头</li>
				        				<li data="hr">HR</li>
				        				<li data="training">培训机构</li>
				        				<li data="technicalTalent">技术达人</li>
				        				<li data="other">其他</li>
				        			</ul>
				        		</div>
				        	</div>
				        	<div class="group clearfloat">
				        		<label class="labelDiv"><span class="must">*</span>性别</label>
				        		<ul class="sexBox" id="selectSexPan">
				        		 <#if basic.sex=='F'>
				        		    <li class="noselect_bg" data="M" ><img src="${_PATH}/res/images/partner/man_noselect.png" alt="" id="manPigId"><span class="noselect_color" id="manselectColorId">男</span></li>
				        			<li class="select_bg" data="F"><img src="${_PATH}/res/images/partner/woman_select.png" alt="" id="womanPigId"><span class="select_color" id="womanselectColorId">女</span></li>
				        		     <input type="hidden" value="F" id="sexId" name="sex">
				        		 <#else>
				        			<li class="select_bg" data="M" ><img src="${_PATH}/res/images/partner/man_select.png" alt="" id="manPigId"><span class="select_color" id="manselectColorId">男</span></li>
				        			<li class="noselect_bg" data="F"><img src="${_PATH}/res/images/partner/woman_noselect.png" alt="" id="womanPigId"><span class="noselect_color" id="womanselectColorId">女</span></li>
				        		     <input type="hidden" value="M" id="sexId" name="sex">
				        		  </#if>
				        		</ul>
				        	</div>
				        	<div class="group clearfloat">
				        		<label class="labelDiv"><span class="must">*</span>绑定邮箱</label>
				        		<div class="inputDiv" style="width:300px"><input type="text" placeholder="用于信息通知和登录" style="width:300px" name="email" id="emailId" value="${basic.email}" maxlength="25"></div>
				        	</div>
				        	<div class="group clearfloat">
				        		<label class="labelDiv"><span class="must">*</span>所在城市</label>
				        		<div class="inputDiv cityContainer" style="width:300px"><input type="text" value="${basic.areaName}" style="width:300px" id="areaName" name="areaName"><span class="down iconBtn" id="cityIcon"></span></div>
				        	</div>
				        	<div class="btnBox">
				        		<div class="error_div" id="error" style="display:none;">
				        			<span class="error_icon"></span>
				        			<span class="error_text"></span>
				        		</div>
				        		<button type="button" id="saveBasicBtn" class="saveBtn">保存并继续下一步</button>
				        		<a href="${_PATH}/member/index?rp=inviteRegister" class="skipBtn">先跳过，直接去备案人才</a>
				        	</div>
				        </form>
					</div>
			</div>
		
	</div>
	<#--行业信息结束-->
    <#--正文开始-->
    </@block>
     <@block name="script">
         <script  src="${_PATH}/res/script/myjs/member/partnerElite/registerBasic.js"></script>
    </@block>
</@extend>