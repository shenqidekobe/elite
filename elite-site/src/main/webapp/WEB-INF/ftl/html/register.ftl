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


        #registerForm{
           padding-top:20px;
        }

        .form-horizontal .form-group{
            margin-left:0;
            margin-right:0;
            padding-left:20px;
            padding-right:20px;
        }

        .forget-password a{
            color:#2CB7C9;
        }

        .register-btn{
            width:84%;
            height:36px;
            background-color: #F8D593;
            color:white;
            border:none;
            border-radius: 18px;
        }

        .login-tip{
            font-size: 14px;
        }

        .register-tip a{
            color:#2CB7C9;
        }

       .identity{
           width:50%;
           height:30px;
           float:left;
           text-align: center;
       }

        .select-line{
            width:50%;
            border-bottom: 2px solid #FEA600;
            position: absolute;
            bottom: -2px;
            left:0;
        }

        .other-select{
            cursor: pointer;
        }

        .send-msg-btn{
            float:left;
            width:104px;
            height:34px;
            background-color: #2CB7C9;
            border:1px solid #2CB7C9;
            border-radius: 8px;
            color:white;
            margin-left:18px;
            font-size: 14px;
        }

        .copyright{
            font-size: 12px;
            color:white;
            position: absolute;
            top:620px;
            left:50%;
            margin-left:-104px;
        }

        .identity-select{
            width:100%;
            height:30px;
            border-bottom:2px solid #E6E6E6;
            position: relative;
        }

        .focus-input:focus{
            box-shadow: 0 0 6px 0 #2CB7C9!important;
        }
        .current-select{
            color:#FEA600;
        }

        .error-tip{
            width:100%;
            height:24px;
            line-height: 24px;
            padding-left:24px;
            color:red;
            text-align: left;
            font-size:12px;
        }
        
        .error-sign{
          border-color:red;
        }


    </style>
    </@block>
    <@block name="body">

    <#--正文开始-->
    <div class="content">
        <div class="show-box">
            <img src="/res/images/banner_register1.png" class="theme-banner">
            <img src="/res/images/register_logo.png" class="login-logo">
            <span class="slogan">你身边的技术合伙人</span>

            <div class="login-box" style="top:210px;">
                <form class="form-group form-horizontal" role="form" id="registerForm">
                    <div class="form-group">
                        <div class="identity-select" id="identitySelect">
                            <div class="identity project current-select">我是项目方</div>
                            <div class="identity person other-select">我是精英</div>
                            <div class="select-line"></div>
                        </div>
                    </div>
                    <input type="hidden" value="company" id="currentType" name="currentType">

                    <div class="form-group">
                        <input class="form-control focus-input" type="text" id="nickName" name="nickName" placeholder="用户名" maxlength="20">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="text" id="account" name="account" placeholder="手机号" maxlength="11">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="text" id="password" name="password" placeholder="密码" maxlength="16">
                    </div>

                    <div class="form-group">
                        <input class="form-control focus-input" type="text" id="verifycode" name="verifyCode" placeholder="手机验证码" maxlength="6" style="width:134px;float:left;">
                        <input type="button" class="send-msg-btn" id="sendMsgBtn" value="获取验证码" style="width:104px;">
                    </div>

                   <div class="error-tip">
                       <span id="registerError"></span>
                   </div>

                    <div class="form-group" style="padding:0;">
                        <button type="button" class="register-btn" id="registerBtn">注册</button>
                    </div>

                    <p class="login-tip">已有账户?&nbsp;<a href="/login">立即登录</a></p>

                </form>

            </div>

            <div class="copyright">©CopyRight By YUNYINGHUI.COM</div>
        </div>
    </div>


    </@block>
    <@block name="footer"></@block>

    <@block name="script">
        <script  src="/res/script/myjs/register.js"></script>
    </@block>
</@extend>