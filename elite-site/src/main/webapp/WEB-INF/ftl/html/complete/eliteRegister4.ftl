<@extend name="completeLayout">
    <@block name="cs">
    <style type="text/css">

        .process{
            width:100%;
            height:130px;
            background-color: #F5FBFC;
        }

        .process-box{
            width:1000px;
            margin:0 auto;
            position: relative;
        }

        .process-line{
            width:132px;
            border-bottom: 2px solid #BFBFBF;
            position: absolute;
            top:50px;
            left:210px;
        }

        .finish-line{
            border-color: #95D6E2;
        }

        .content{
            width:1000px;
            margin:0 auto;
            min-height:250px;

            padding-bottom: 60px;
        }
        .process-circle{
            width: 40px;
            height: 40px;
            border:none;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            background-color: #BFBFBF;
            text-align: center;
            line-height: 40px;
            color:white;

            position: absolute;
            top:32px;
            left:160px;
            z-index: 200;
        }

        .active-process{
            background-color: #7DD2E1;
        }

        .process-title{
            position: absolute;
            top:84px;
            left:148px;
        }

        .active-title{
            color:#2CB7C9;
        }

        .complete-circle{
            width:100px;
            height:100px;
            border:1px solid red;
            margin-left:200px;
            margin-top:20px;
            float:left;
        }


        .complete-tips{
            margin-top:30px;
            height:140px;
        }

        .complete-theme{
            margin-left:50px;
            padding-top:40px;
            float:left;
        }

        .tips{
            float:left;
            margin-left:20px;
        }

        .tips img{
            width:20px;
            height:20px;
        }

        .tips p{
            font-size: 12px;
            color:#9B9B9B;
        }

        .split-line{
            height:140px;
            border-left:1px solid #E6E6E6;
            float:left;
            margin-left:40px;
        }

        .form-person{
            display: block;
            padding-left:200px;
            margin-top:50px;
        }

        .form-person .form-group{
            margin-bottom: 20px;
        }

        .form-person label{
            text-align: left!important;
            position: relative;
        }


        .required{
            color:#FEA600!important;
        }

        .secret-circle{
            width: 20px;
            height: 20px;
            border: 1px solid #4A90E2;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            text-align: center;
            line-height: 20px;
            font-size: 12px;
            color:#4A90E2;
            display: inline-block;

            position: absolute;
            right:10px;
            top:8px;
        }


        .form-opt{
            margin-top:50px;
            padding-left:140px;
        }

        .btn-ok{
            width:274px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 32px;
            color:white;
            vertical-align: bottom;
            margin-right:20px;
        }

        .theme-line{
            width:100%;
            border-bottom: 1px solid #E6E6E6;
        }

        .photos{
            margin-top:20px;
            height:420px;
            margin-bottom: 20px;
        }

        .photo-row{
            height:200px;
        }

        .photo-box{
            width: 230px;
            text-align: center;
            float:left;
            margin-right:36px;
        }

        .photo-box p{
            font-size:14px;
        }

        .photo{
            width:230px;
            height:160px;
            line-height: 160px;
            background-color: #F2F2F2;
            border:1px dashed #CCCCCC;
            cursor: pointer;
        }

        .photo-add{
            width:24px;
            height:24px;
        }

        .number-table tr{
            border-radius: 4px;
        }

        .number-table tr td{
            width:30px;
            height:30px;
            border:1px solid #CCC;
        }

        .number-table input{
            width:30px;
            height:30px;
            border:none;
            text-align: center;
        }

        .number-box-first{
            width: 30px;
            height:32px;
            border:1px solid #CCC;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
            position: absolute;
            top:0;
            left:0;
        }

        .number-box-last{
            width: 30px;
            height:32px;
            border:1px solid #CCC;
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px;
            position: absolute;
            top:0;
            left:0;
        }

    </style>
    </@block>

<#--中间正文部分-->
    <@block name="content">

    <div class="process">
        <div class="process-box">
            <div class="process-circle active-process">
                <img src="/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:352px;">
                <img src="/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:544px;">
                <img src="/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:736px;">4</div>
            <p class="process-title active-title">当前状态</p>
            <p class="process-title active-title" style="left:342px;">基本信息</p>
            <p class="process-title active-title" style="left:526px;">技能&nbsp;|&nbsp;经历</p>
            <p class="process-title active-title" style="left:724px;">个人征信</p>

            <div class="process-line finish-line"></div>
            <div class="process-line finish-line" style="left:402px;"></div>
            <div class="process-line finish-line" style="left:594px;"></div>
        </div>
    </div>

    <div class="content">
        <div class="complete-tips">
            <div class="complete-circle"></div>
            <div class="complete-theme">
                <p>完善个人信息</p>
                <p style="font-size: 14px;color:#9B9B9B;">个人信息越全，越受开发团队和投资方欢迎</p>
            </div>

            <div class="split-line"></div>

            <div class="tips">
                <img src="/res/images/tips_icon.png">&nbsp;&nbsp;tips
                <p style="margin-top:10px;">1.上传的资料越全您所获得的征信等级越高</p>
                <p>2.越高的征信等级将会再给您更多的展示权重</p>
                <p>3.高的征信等级能够吸引更多的合作伙伴</p>
                <p>4.此征信报告可以作为您的商业行为背书</p>
            </div>
        </div>


        <form class="form-group form-horizontal form-person" role="form" id="companyFrom">
        <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <span class="required">* </span> 身份证号 <span class="secret-circle">密</span>
                </label>
            <#--<div class="col-xs-5 col-md-5">-->
            <#--<input class="form-control" type="text" id="name" name="name" placeholder="18位二代身份证号" maxlength="18">-->
            <#--</div>-->

                <div class="col-xs-9 col-md-9">
                    <table class="number-table">
                        <tr>
                            <td style="border:none;position: relative;">
                                <div class="number-box-first" style="border-right:none;">
                                    <input type="text" maxlength="1" style="width:25px;">
                                </div>
                            </td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td><input type="text" maxlength="1"></td>
                            <td style="border:none;position: relative;">
                                <div class="number-box-last" style="border-left:none;">
                                    <input type="text" maxlength="1" style="width:25px;">
                                </div>
                            </td>
                        </tr>
                    </table>

                </div>
            </div>

            <div class="form-group" style="margin-bottom: 10px;">
                <label class="col-xs-2 col-md-2 control-label">上传认证资料<span class="secret-circle" style="right:-10px;">密</span></label>
            </div>

            <div class="theme-line"></div>

            <div class="photos">
                <div class="photo-row">
                    <div class="photo-box">
                        <p><span class="required">* </span> 身份证号正面</p>
                        <div class="photo">
                            <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p><span class="required">* </span> 身份证号反面</p>
                        <div class="photo">
                            <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>
                </div>

                <div class="photo-row" style="margin-top:20px;">
                    <div class="photo-box">
                        <p>营业执照</p>
                        <div class="photo">
                            <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p>工作证正面</p>
                        <div class="photo">
                            <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p>名片正面</p>
                        <div class="photo">
                            <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>
                </div>

            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <span class="required">* </span> 支付宝账号 <span class="secret-circle">密</span>
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="name" name="name" placeholder="只调用支付宝的信用积分，不影响账户" maxlength="18">
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                    <span class="required">* </span> 银行卡绑定 <span class="secret-circle">密</span>
                </label>
                <div class="col-xs-5 col-md-5">
                    <input class="form-control" type="text" id="name" name="name" placeholder="用于个人征信及项目账款的结算" maxlength="18">
                </div>
            </div>

            <div class="form-opt">
                <button type="button" class="btn-ok" id="saveBtn">创建完成！去发布项目</button>
                <a href="#" style="font-size: 14px;">先跳过，去发布项目</a>
            </div>
        </form>


    </div>
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>