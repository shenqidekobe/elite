<@extend name="layout">
    <@block name="cs">
    <style type="text/css">

        /*正文样式*/
        .content{
            position: absolute;
            top:0;
            left:0;
            width:100%;
            height:100%;
        }

        .theme-banner{
            width:100%;
            height:auto;
        }

        .show-box{
            position: relative;
        }

        .login-logo{
            position: absolute;
            top:110px;
            left:50%;
            margin-left:-75px;
        }

        .slogan{
            font-size: 14px;
            color:white;
            position: absolute;
            top:168px;
            left:50%;
            margin-left:-60px;
        }

        .login-box{
            position: absolute;
            top:200px;
            left:50%;
            margin-left:-150px;
            width:300px;
            height:auto;
            background-color: white;
            text-align: center;
            border-radius: 10px;
            padding-bottom: 10px;
        }

        .welcome{
            color:#4A4A4A;
            padding-top:20px;
            margin-bottom: 20px;
        }

        #loginForm input{
            width:92%;
        }

        .form-horizontal .form-group{
            margin-left:0;
            margin-right:0;
            padding-left:20px;
        }

        .checkbox-inline{
            float:left;
            padding-left:20px;
            padding-top:0!important;
        }

        .auto-login{
            font-size: 14px;
            margin-left:10px;
        }

        .forget-password{
            font-size: 14px;
            float:right;
            padding-right:26px;
        }

        .forget-password a{
            color:#2CB7C9;
        }

        .login-btn{
            width:84%;
            height:36px;
            background-color: #F8D593;
            color:white;
            border:none;
            border-radius: 18px;
        }

        .register-tip{
            font-size: 14px;
        }

        .register-tip a{
            color:#2CB7C9;
        }

        .error-tip{
            display: none;
            width:100%;
            height:24px;
            line-height: 24px;
            padding-left:24px;
            color:red;
            text-align: left;
        }

        .focus-input:focus{
            box-shadow: 0 0 6px 0 #2CB7C9!important;
        }


    </style>
    </@block>
    <@block name="body">

    <#--正文开始-->
    <div class="content">
        <div class="show-box">
            <img src="/res/images/banner_login.png" class="theme-banner">
            <img src="/res/images/login_logo.png" class="login-logo">
            <span class="slogan">你身边的技术合伙人</span>

            <div class="login-box">
                <div class="welcome">欢迎回到云英汇！</div>
                <form class="form-group form-horizontal" role="form" id="loginForm">
                    <div class="form-group">
                        <input class="form-control focus-input" type="text" id="username" name="username" placeholder="手机或邮箱" maxlength="50">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="password" id="password" name="password" placeholder="密码" maxlength="16">
                    </div>

                    <div class="form-group">
                        <label class="checkbox-inline">
                            <input type="checkbox" id="autoLogin" value="0" style="width:inherit;">
                            <span class="auto-login">自动登录</span>
                        </label>

                        <span class="forget-password"><a href="#">忘记密码?</a></span>
                    </div>

                    <div class="error-tip">
                        <span id="loginError">aaa</span>
                    </div>

                    <div class="form-group" style="padding:0;">
                        <button type="button" class="login-btn" id="loginBtn">登录</button>
                    </div>

                    <p class="register-tip">还没有账户?&nbsp;<a href="/member/register">立即注册</a></p>

                </form>

            </div>
        </div>
    </div>


    </@block>
    <@block name="footer"></@block>

    <@block name="script">
    </@block>
</@extend>