<@extend name="layout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/register/login.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/> 
    <#--正文开始-->
    <div class="content" style="padding-top:0;" id="login">
        <div class="show-box">
       
            <div class="login-box">
                <div class="welcome">欢迎回到云英汇！</div>
                <form class="form-group form-horizontal relative" role="form" id="loginForm">
                    <div class="form-group">
                        <input type="hidden" id="rps" value="${rps}">
                        <input class="form-control focus-input" type="text" id="account" name="account" placeholder="手机或邮箱" maxlength="50">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="password" id="password" name="password" placeholder="密码" maxlength="16" autocomplete="off">
                    </div>

                    <div class="form-group">
                        <label class="checkbox-inline">
                            <input type="checkbox" id="autoLogin" value="0" style="width:inherit;">
                            <span class="auto-login">自动登录</span>
                        </label>

                        <span class="forget-password"><a href="${_PATH}/forget/p">忘记密码?</a></span>
                    </div>

                    <div class="error-tip">
                        <span id="loginError"></span>
                    </div>

                    <div class="form-group" style="padding:0;margin-top:50px;">
                        <button type="button" class="login-btn" id="loginBtn">登录</button>
                    </div>

                    <p class="register-tip">还没有账户?&nbsp;
                        <#if RequestParameters["ts"]=='partnerCompany'||RequestParameters["ts"]=='partnerElite'>
                		   <a href="${_PATH}/register/p?ts=${RequestParameters["ts"]}">立即注册</a>
                		<#else>
                		   <a href="${_PATH}/register?ts=${RequestParameters["ts"]}">立即注册</a>
                		</#if>
                    </p>

                </form>

            </div>
        </div>
    </div>
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/login.js"></script>
    </@block>
</@extend>