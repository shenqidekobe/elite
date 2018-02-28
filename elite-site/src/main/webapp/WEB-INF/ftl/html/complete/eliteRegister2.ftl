<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/kuCity.css"/>
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
            padding-top:30px;
        }

        .form-person label{
            text-align: left!important;
            position: relative;
        }

        .form-tip{
            font-size: 12px;
            color:#FEA600;
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

        .date-select{
            display: block;
            width:100%;
            position: relative;
        }

        .date-icon{
            width:20px;
            height:20px;
            position: absolute;
            top:7px;
            right:16px;
            z-index: 100;
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
            right:20px;
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

        .split-line2{
            width:100%;
            border-bottom: 1px dashed #E6E6E6;
            position: absolute;
            top:264px;
            left:0;
        }

        .upload-head{
            position: absolute;
            top:72px;
            right:164px;
            text-align: center;
        }

        .upload-head img{
            width:100px;
            height:100px;
        }

        .form-opt{
            margin-top:50px;
            padding-left:136px;
        }

        .btn-ok{
            width:186px;
            height:50px;
            background-color: #FEA600;
            border:none;
            border-radius: 32px;
            color:white;
            vertical-align: bottom;
            margin-right:20px;
        }

        .finish-line{
            border-color: #95D6E2;
        }

        .active-btn{
            background-color: #2CB7C9!important;
            color:white!important;
            border:1px solid #2CB7C9!important;
        }

        .sex button{
            padding-left:30px;
            padding-right:30px;
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
            <div class="process-circle active-process" style="left:352px;">2</div>
            <div class="process-circle" style="left:544px;">3</div>
            <div class="process-circle" style="left:736px;">4</div>
            <p class="process-title active-title">当前状态</p>
            <p class="process-title" style="left:342px;">基本信息</p>
            <p class="process-title" style="left:526px;">技能&nbsp;|&nbsp;经历</p>
            <p class="process-title" style="left:724px;">个人征信</p>

            <div class="process-line finish-line"></div>
            <div class="process-line" style="left:402px;"></div>
            <div class="process-line" style="left:594px;"></div>
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

        <div style="position: relative;">
            <form class="form-group form-horizontal form-person" role="form" id="personFrom">
                <span class="form-tip">* &nbsp;必填项</span>
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                        <span class="required">* </span> 真实姓名 <span class="secret-circle">密</span>
                    </label>
                    <div class="col-xs-4 col-md-4">
                        <input class="form-control" type="text" id="name" name="name" placeholder="与身份证上姓名保持一致" maxlength="20">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 出生年月<span class="secret-circle">密</span></label>
                    <div class="col-xs-4 col-md-4">
                         <span class="date-select">
                            <img src="/res/images/date_icon.png" class="date-icon">
                            <input type="text" class="form-control" id="brith"  readonly  placeholder="请选择你的出生年月" style="background-color: white;"/>
                        </span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 性别</label>
                    <div class="col-xs-4 col-md-4">
                        <div class="btn-group sex">
                            <button type="button" class="btn btn-default active-btn">是</button>
                            <button type="button" class="btn btn-default">否</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 所在城市</label>
                    <div class="col-xs-4 col-md-4">
                        <div class="birth-box">
                        	
                        	<input class="form-control search" type="text" id="city" name="city" placeholder="选择所在城市" style="background-color: white;padding-left:38px;" >
                            
                            <img src="/res/images/location_icon.png" class="location">
                        <#--<div class="birth-year" id="workYear">6&nbsp;年</div>-->
                            <div>
                                <ul class="year-select">
                                <#--<li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>-->
                                </ul>

                                <div class="form-triangle">
                                    <div class="form-trigger"></div>
                                </div>
                            </div>

                        </div>
                        
                        
                      
                        
                        
                    </div>
                </div>

            <#--上传头像-->
                <div class="upload-head">
                    <img src="/res/images/head.png">
                    <p style="margin-top:10px;font-size: 14px;">点击上传头像</p>

                </div>

                <div class="split-line2"></div>

                <div class="form-group" style="margin-top:60px;">
                    <label class="col-xs-2 col-md-2 control-label"><span class="required">* </span> 绑定邮箱</label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control" type="text" id="name" name="name" placeholder="用于账户登录及项目进展的消息推送" maxlength="20">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">个性签名</label>
                    <div class="col-xs-6 col-md-6">
                        <input class="form-control" type="text" id="name" name="name" placeholder="少于15字" maxlength="20">
                    </div>
                </div>

                <div class="form-opt">
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="#" style="font-size: 14px;">先跳过，去发布项目</a>
                </div>
            </form>
        </div>

    </div>
    </@block>

    <@block name="script">
    
   	<script  src="${_PATH}/res/script/city/jquery-2.1.4.min.js"></script>
    <script  src="${_PATH}/res/script/city/kuCity.js"></script>
    
    <script>
        $('.search').kuCity();
    </script>
    </@block>
</@extend>