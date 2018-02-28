<@extend name="personLayout">
    <@block name="cs">
    <style type="text/css">

        .right-opt{
            padding-bottom: 30px!important;
        }

        #personRecommend{
            min-height: 450px!important;
        }
        .opt-and-search{
            width:100%;
        }

        .add-title{
            font-size: 18px;
            line-height: 54px;
        }

        .split-line{
            width:100%;
            border-bottom: 1px solid #E6E6E6;
        }


        .send-btn{
            width:100%;
            background-color: #F8D593;
            color:white;
            height:40px;
        }

        .form-opt{
            margin-top:30px;
            padding-left:128px;
        }

        .btn-ok{
            width:286px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 20px;
            color:white;
        }



        .birth-box{
            position: relative;
        }

        .year-select{
            position: absolute;
            top:33px;
            left:0px;
            z-index: 100;
            border:1px solid #2CB7C9;

        }

        .year-select{
            display: none;
            height:266px;
            overflow: auto;
        }

        .year-select li{
            padding:0 16px;
            background-color: white;

        }

        .year-select li:hover{
            background-color: #2CB7C9;
            color:white;
            cursor: pointer;
        }
        .birth-year{
            position: absolute;
            top:7px;
            left:16px;
        }


        .form-triangle{
            position: absolute;
            bottom: 6px;
            left:58px;
            cursor: pointer;
        }
        .form-trigger {
            display: inline-block;
            height: 0;
            width: 0;
            overflow: hidden;
            border-style: solid;
            margin-left: 4px;
            margin-right: 4px;
            position: relative;
            top:0;
            border-width:6px 6px 0;
            border-color:#D8D8D8 transparent transparent;
            -webkit-transition:all .4s ease 0s;
            -moz-transition:all .4s ease 0s;
            -o-transition:all .4s ease 0s;
            transition:all .4s ease 0s;
        }

        .trigger-inverse{
            -webkit-transform: rotate(180deg);
            -moz-transform: rotate(180deg);
            -ms-transform: rotate(180deg);
            transform: rotate(180deg);
            animation-fill-mode: forwards;
        }

        .location{
            position: absolute;
            top:8px;
            left:12px;
            width:auto;
            height:20px;
        }

        .card-code{
            position: absolute;
            top:0;
            left:158px;
            width:250px;
        }



    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div class="opt-and-search">
        <div class="add">
            <span class="add-title">账户安全</span>
        </div>
    </div>

    <div class="split-line"></div>

    <div class="opt-and-search" style="margin-top:24px;">

        <form class="form-group form-horizontal" role="form" id="accountFrom">
            <p style="margin-bottom: 20px;">基本资料&nbsp;&nbsp;<span style="font-size: 12px;">(*不可修改)</span></p>
            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 手机号</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="phone" name="phone" placeholder="请输入您的手机号" maxlength="11">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 姓氏</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="surname" name="surname" placeholder="请输入您的姓氏" maxlength="10">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 所在城市</label>
                <div class="col-xs-5 col-md-5">
                    <div class="birth-box">
                        <input class="form-control" type="text"  style="background-color: white;" disabled>
                        <img src="/res/images/location_icon.png" class="location">
                        <div class="birth-year" id="province" style="left:50px;">上海</div>
                        <div>
                            <ul class="year-select" style="left:40px;">
                                <li data-type="province">上海</li>
                                <li data-type="province">北京</li>
                                <li data-type="province">深圳</li>
                            </ul>

                            <div class="form-triangle" style="left:104px;top:6px;width:22px;">
                                <div class="form-trigger"></div>
                            </div>
                        </div>

                        <div class="birth-year" id="city" style="left:134px;top:7px;">上海市</div>
                        <div>
                            <ul class="year-select" style="left:106px;">
                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                <li data-type="city" style="padding:0 28px;">上海市</li>
                            </ul>

                            <div class="form-triangle" style="left:206px;top:6px;width:22px;">
                                <div class="form-trigger"></div>
                            </div>
                        </div>

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 公司名称</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="companyname" name="companyname" placeholder="请输入您的公司名称" maxlength="50">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 邮箱</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="email" name="email" placeholder="请输入您的邮箱账号" maxlength="50">
                </div>
            </div>

            <p style="margin-top: 60px;margin-bottom: 20px;">提现账户绑定</p>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span>  银行卡</label>
                <div class="col-xs-10 col-md-10">
                    <div class="birth-box">
                        <input class="form-control" type="text" id="bank" name="bank" placeholder="请选择银行" style="width:140px;background-color: white;" disabled>
                        <#--<div class="birth-year" id="workYear">6&nbsp;年</div>-->
                        <div>
                            <ul class="year-select">
                                <#--<li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>-->
                            </ul>

                            <div class="form-triangle" style="left:100px;">
                                <div class="form-trigger"></div>
                            </div>
                        </div>

                        <input class="form-control card-code" type="text" id="cardcode" name="cardcode" placeholder="请输入银行卡号">

                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 支付宝</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="payaccount" name="payaccount" placeholder="请输入您的支付宝账号" maxlength="30">
                </div>
            </div>

            <p style="margin-top: 60px;margin-bottom: 20px;">设置新密码</p>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span>  新密码</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="newPassword" name="newPassword" placeholder="设置新密码" maxlength="16">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span>  确认密码</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="repeatNewPassword" placeholder="再次输入新密码" maxlength="16">
                </div>
            </div>

            <div class="form-group">
                <div class="col-xs-5 col-md-5 col-md-offset-2 col-xs-offset-2">
                    <button type="button" class="btn send-btn">向公司手机13566537810发送验证短信</button>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span>  验证码</label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="verifycode" name="verifycode" placeholder="输入短信验证码" maxlength="6">
                </div>
            </div>

            <div class="form-opt">
                <button type="button" class="btn-ok" id="saveBtn">提交修改</button>
            </div>
        </form>

    </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>