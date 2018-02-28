<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/register/register.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/> 
    <#--正文开始-->
    <div class="content" style="padding-top:0;" id="register">
        <div class="show-box register1">
           
            <div class="login-box">
                <form class="form-group form-horizontal" role="form" id="registerForm">
                    <div class="form-group">
                        <div class="identity-select" id="identitySelect">
                            <div class="identity project current-select">我是项目方</div>
                            <div class="identity person other-select">我是精英</div>
                            <div class="select-line"></div>
                        </div>
                    </div>
                    <input type="hidden" value="company" id="currentType" name="currentType">
                    
                    <div class="form-group invite_code_box"  style="display:none;">
                        <input class="form-control focus-input" type="text" placeholder="邀请码" maxlength="8" id="inviteCode" name="inviteCode">
                        <div class="code_div" style="display:none;">
                        	<span class="icon_code"></span>
                        	如何获取邀请码？
                        </div>
                    </div>
                    
                    <div class="form-group" style="margin-top:30px;">
                        <input class="form-control focus-input" type="text" id="nickName" name="nickName" placeholder="用户名" autocomplete="off" maxlength="10">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="text" id="account" name="account" placeholder="手机号" maxlength="11">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="password" id="password" name="password" placeholder="密码" maxlength="16">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="text" id="verifycode" name="verifyCode" placeholder="手机验证码" maxlength="6" style="width:256px;float:left;">
                        <input type="button" class="send-msg-btn" id="sendMsgBtn" value="获取验证码" >
                    </div>
                    
                    <div class="form-group project_group">
                        <input type="checkbox" class="project_checkbox" >
                        <div class="projext_div">我已阅读并同意<a href="${_PATH}/protocol/user" target="_blank"><<云英汇用户协议>></a></div>
                    </div>

                    <div class="form-group relative" style="padding:0;margin-top:20px;">
                    	<div class="error-tip">
	                       <span id="registerError"></span>
	                   </div>
                        <button type="button" class="register-btn" id="registerBtn">下一步</button>
                    </div>

                    <p class="login-tip">已有账户?&nbsp;<a href="${_PATH}/login">立即登录</a></p>

                </form>

            </div>

        </div>
    </div>

    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/register.js"></script>
    </@block>
</@extend>