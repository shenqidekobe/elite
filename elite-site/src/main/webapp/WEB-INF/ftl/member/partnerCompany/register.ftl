<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/channel/channelContainer.css"/>
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/partner_main.css"/>
    </@block>
    <@block name="body">
    <#--头部-->
    <@accountheadChannel opt2=""/>
	<!-- 项目方注册首页 >
	<!--项目推荐方-资料完善-->
	<div class="recommend-form-box">
	<div class="recommend-form" id="personnelStep1" style="height:700px">
	    <div class="form-box">
		        <div class="title">简单几步，完成渠道方注册</div>
		        <div class="hr-line"></div>
		        <form class="row form-group form-horizontal" role="form" id="project-form1">
		        	<div class="selectChannel">
		        		<ul>
		        			<li class="active_select" style="margin-right:60px;">我有项目推荐</li>
		        			<li class="default_select">我有人才推荐</li>
		        		</ul>
		        	</div>
		        	<div class="form-group form-grp" style="margin-top:20px;">
		                <div class="col-xs-7 col-md-7 form-group-cent">
		                    <input class="form-control group-cent-inpt" type="text" id="inviteCodeId" name="inviteCode" autocomplete="off" placeholder="推荐人手机号或邀请码"  style="width:400px;" maxlength="20">
		                </div>
		            </div>
		            <div class="form-group form-grp" style="margin-top:20px;">
		                <div class="col-xs-7 col-md-7 form-group-cent">
		                    <input class="form-control group-cent-inpt" type="text" id="nickName" name="nickName" autocomplete="off" placeholder="请输入用户名"  style="width:400px;" maxlength="20">
		                </div>
		            </div>
		            <div class="form-group form-grp" style="margin-top:20px;">
		                <div class="col-xs-7 col-md-7 form-group-cent">
		                    <input class="form-control group-cent-inpt" type="text" id="phone" name="account"  placeholder="请输入11位手机号" maxlength="11" style="width:400px;">
		                </div>
		            </div>
		            <div class="form-group form-grp">
		                <div class="col-xs-7 col-md-7 form-group-cent">
		                    <input class="form-control group-cent-inpt" type="password" id="password"  placeholder="请输入密码" maxlength="16" style="width:400px;" >
		                    <input class="form-control" type="hidden" id="passwordHiddenId" name="password" >
		                </div>
		            </div>
		            <div class="form-group form-grp" style="padding-left:76px">
		                <div class="col-xs-7 col-md-7 form-group-cent">
		                    <input class="form-control" type="text" id="verifycode" name="verifycode" placeholder="手机验证码" maxlength="6" style="width:269px;float:left;margin-left:68px">
		                    <input type="button" class="send-msg-btn" id="send-msg-btn" value="发送验证码">
		                </div>
		            </div>
		            <div class="form-group" style="margin-top:-30px;">
		                <div class="col-xs-9 col-md-9 col-md-offset-3" style="padding-left:0; margin-left:28px">
		                    <label class="checkbox-inline">
		                        <input type="checkbox" id="inlineCheckbox" value="1" checked>
		                        <span class="agree">我已阅读并同意<a href="${_PATH}/protocol/user" target="_blank" class="viewbook">《云英汇平台用户协议》</a></span>
		                    </label>
		                </div>
		            </div>
		            <div class="next-step">
		            	<div class="error-tip"></div>
		                <button type="button" class="next-step-btn" id="registerBtn">确认注册</button>
		                <div class="loginDiv">已有账户？<a href="${_PATH}/login">立即登录</a></div>
		            </div>
		        </form>
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