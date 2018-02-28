<@extend name="layout">
    <@block name="cs">
    <style type="text/css">

        /*正文样式*/
        .content{
            width:1000px;
            margin:0 auto;
        }

        /*图片内嵌的表单*/
        .recommend-form{
            width:520px;
            /*height:600px;*/
            height:auto;
            position: absolute;
            top:170px;
            left:50%;
            margin-left:-260px;
            background-color: white;
            border-radius: 10px;
            text-align: center;
        }

        .form-box{
            padding:30px 30px;
            text-align: center;
        }

        .title{
            font-size: 24px;
            color:#414141;
        }

        .hr-line{
            width:100%;
            border-bottom: 2px solid #2CB7C9;
            margin:20px 0;
        }

        #recommend-form .btn-group{
            display: block;
        }
        #recommend-form .btn,.btn-default{
            border-color:#2CB7C9;
            outline: none;
        }

        .active-title{
            background-color: #2CB7C9!important;
            color:white!important;
            border:1px solid #2CB7C9!important;
        }

        .send-msg-btn{
            float:left;
            width:120px;
            height:34px;
            background-color: #2CB7C9;
            border:1px solid #2CB7C9;
            border-radius: 8px;
            color:white;
            margin-left:10px;
            font-size: 14px;
        }

        .hr-line2{
            width:92%;
            border-bottom: 1px dashed #CCCCCC;
            margin-left:20px;
            margin-top:30px;
            margin-bottom: 20px;
        }

        .agree{
            font-size: 12px;
            color:#D0021B;
        }

        .error-tip{
            height:24px;
            text-align: left;
            padding-left:20px;
            font-size: 14px;
            color:red;
        }

        .next-step{
            margin-top:20px;
        }

        .next-step-btn{
            width:180px;
            height:40px;
            background-color: #FEA600;
            border-radius: 20px;
            border:none;
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

        .birth-month{
            position: absolute;
            top:7px;
            left:37px;
        }

        .triangle{
            position: absolute;
            bottom: 6px;
            left:58px;
            cursor: pointer;
        }
        .trigger {
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

        .active-control{
            border-color:#2CB7C9;
        }

        .location{
            position: absolute;
            top:8px;
            left:12px;
            width:auto;
            height:20px;
        }

	/*项目方注册首页*/
	.form-grp {
		margin:15px auto;
	}
	.form-group-cent {
		width:100%;
	}
	.group-cent-inpt {
		width:400px;
		margin:0 auto;
	}
/*项目方注册首页*/


/*项目推荐方-资料完善*/

    .rdio {
        width:99px;
        height:40px;
        border:none;
        border-radius:8px;
        background:#999;
        text-indent: 1.6cm;
        position:relative;
    }
    .group-yea {
    	text-align:left;
    }
    .group-yea >p {
        display:inline-block;
        position: relative
    }
    .group-years> p .radio-male {
        display: inline-block;
        width:22px;
        height:22px;
        position:absolute;
        top:9px;
        left:20px;
    }
    .group-yea >p:hover input{
        background:#7dd2e1;
        color:#fff;
    }
    .radio-male {
    	width:20px;
    	height:21px;
    	position:absolute;
    	top:8px;
    	left:19px;
    }
    .male {
    	
        background: url(/res/images/icon-women.png) no-repeat center;
    }
    .afmale {
        background:url(/res/images/icon-women.png) no-repeat center;
    }
    .city {
        width: 360px;
        height:50px;
        border:1px solid #7dd2e1;
        border-radius: 8px;
        list-style: none;
    }
    .maps {
        width:20px;
        height:30px;
        position: absolute;
        top:11px;
        left:30px;
        background:url(/res/images/location_icon.png) no-repeat center;
    }
    .city li {
        width:50%;
        line-height: 50px;
        float:left;
        position: relative;
    }
    .Angle {
        display: inline-block;
        border-top:7px solid #333;
        border-right:7px solid transparent;
        border-left:7px solid transparent;
        position:absolute;
        top:20px;
        right:33px;
    }








    </style>
    </@block>
    <@block name="body">
    <#--头部-->
        <@mainhead clazz="partner" picpath="/res/images/banner_project.png" pagetype="form" user="project"/>

	<!-- 项目方注册首页 -->
	<!-- <div class="recommend-form" id="personnelStep1"  style="width:564px; margin:0 auto">
	    <div class="form-box">
	        <div class="title">简单几步，完成注册</div>
	        <div class="hr-line"></div>

	        <form class="row form-group form-horizontal" role="form" id="personnel-form1">

	            <div class="form-group form-grp">
	                <div class="col-xs-7 col-md-7 form-group-cent">
	                    <input class="form-control group-cent-inpt" type="text" id="phone" name="account" placeholder="请输入11位手机号" maxlength="11" style="width:400px;">
	                </div>
	            </div>
	            <div class="form-group form-grp">
	               
	                <div class="col-xs-7 col-md-7 form-group-cent">
	                    <input class="form-control group-cent-inpt" type="password" id="password"  placeholder="请输入密码" maxlength="16" style="width:400px;" >
	                    <input class="form-control" type="hidden" id="passwordHiddenId" name="password" >
	                </div>
	            </div>
	            <div class="form-group form-grp">
	                <div class="col-xs-7 col-md-7 form-group-cent">
	                    <input class="form-control" type="text" id="verifycode" name="verifycode" placeholder="手机验证码" maxlength="6" style="width:269px;float:left;margin-left:68px">
	                    <input type="button" class="send-msg-btn" id="send-msg-btn" value="发送验证码">
	                </div>
	            </div>
	            <div class="form-group">
	                <div class="col-xs-9 col-md-9 col-md-offset-3" style="padding-left:0; margin-left:43px">
	                    <label class="checkbox-inline" style="padding-left:12%;">
	                        <input type="checkbox" id="eliteInlineCheckboxId" value="1" checked>
	                        <span class="agree">我已阅读并同意<a href="javascript:;">《猎头注册协议》</a>和<a href="javascript:;">《猎头注册承诺书》</a></span>
	                    </label>
	                </div>
	            </div>
	            <div class="next-step">
	                <button type="button" class="next-step-btn" id="nextStepBtn1">确认注册</button>
	                <p style="margin-top:15px">已有账户？<a href="#" style="color:#2cb7c9">立即登录</a></p>
	            </div>
	        </form>
	    </div>
</div>-->
<!-- 项目方注册首页 >

<!--项目推荐方-资料完善-->
<div class="recommend-form" id="personnelStep1"  style="width:564px;">
    <div class="form-box">
        <div class="title">人才推荐方-资料完善</div>
        <div class="hr-line"></div>
        <form class="row form-group form-horizontal" role="form" id="personnel-form1">
            <input type="hidden" id="title" value="person">
           
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 姓名</label>
                <div class="col-xs-7 col-md-7">
                    <input class="form-control" type="text" id="name" name="name" placeholder="请输入姓名" maxlength="20" style="width:264px;">
                </div>
            </div>
           	<div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 性别<span class="secret-circle"></span></label>
                <div class="col-xs-7 col-md-7 group-years group-yea">
                	<button class="rdio" type="text" name="sex" value="男"><span class="male radio-male"></span>男</button>
                	<button class="rdio" type="text" style="margin-left:15px;" name="sex" value="女"><span class="afmale radio-male"></span>女</button>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span>所在城市<span class="secret-circle"></span></label>
                <div class="col-xs-7 col-md-7 group-years ">
                	<span class="maps"></span>
                	<ul class="city">
                		<li>上海<span class="Angle city-angle "></span></li>
                		<li>上海市<span class="Angle city-angle"></span> </li>
                	</ul>
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span>公司名称</label>
                <div class="col-xs-7 col-md-7">
                    <input class="form-control" type="text" id="email" name="email" placeholder="请输入您的邮箱账号" maxlength="50" style="width:264px;">
                </div>
            </div>
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span>绑定邮箱</label>
                <div class="col-xs-7 col-md-7">
                    <input class="form-control" type="text" id="email" name="email" placeholder="请输入您的邮箱账号" maxlength="50" style="width:264px;">
                </div>
            </div>
            <div class="next-step">
                <button type="button" class="next-step-btn" id="nextStepBtn1">保存并提交</button>
                <a href="javascript:;" style="vertical-align:bottom;margin-left:22px;">先跳过</a>
            </div>
        </form>
    </div>
</div>
<!--项目推荐方-资料完善-->

    <#--正文开始-->
    
    <div class="content">

    </div>

    </@block>

<#--插入底部标签覆盖底部-->
    <@block name="footer"></@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/index.js')}"></script>
    </@block>
</@extend>