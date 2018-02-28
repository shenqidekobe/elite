<@extend name="layout">
    <@block name="cs">
    <style type="text/css">

        .content{
            width:1000px;
            margin:0 auto;
        }

        /*图片内嵌的表单*/
        .recommend-form{
       		background:red;
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
            padding:30px;
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
            width:395px;
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

        /*第二个表单*/
        .two-column-tr{
            text-align: left;
            padding-left:64px;
        }
        .two-column-tr > *{
            display: inline-block;
        }
        .location{
        	background:red;
            position: absolute;
            top:8px;
            left:12px;
            width:auto;
            height:20px;
        }

        /*第三个表单*/
        .ID-pic-add{
            width:240px;
            height:40px;
            line-height: 40px;
            border:1px solid #D9D9D9;
            border-radius: 5px;
            margin-top:20px;
            color:#9B9B9B;
            cursor: pointer;
            display: inline-block;
            /*display: none;*/
        }

        .ID-pic{
            width:240px;
            height:80px;
            margin-top:20px;
            /*display: inline-block;*/
            display: none;
        }
		
		
		<#--人才基本信息开始 样式-->
        .reset-btn{
            display: inline-block;
            width: 80px;
            height: 34px;
            background-color: #2CB7C9;
            border: 1px solid #2CB7C9;
            border-radius: 8px;
            color: white;
            margin-left: 10px;
            font-size: 14px;
        }
        
       
      	.group-years {
        	position: relative;
        	width:52%;
        }
        .group-years .date-select img {
        	width:26px;
        	height:26px;
        	display:block;
        	position: absolute;
        	top:4px;
        	right:61px;
        }
    	.next-step a {
			display:inline-block;
			color:#4A90E2;
			text-decoration:none;
			margin-left:33px;
			vertical-align:bottom;
		}
		<#--人才基本信息结束-->
		<#--人才注册首页 样式-->
		.form-group-cent {
			width:100%;
		}
		.group-cent-inpt {
			width:400px;
			margin:0 auto;
		}
			<#--人才注册首页结束-->
		
	<#--行业信息开始-->		
		
	
		
		
		
		.next-step{
		    margin-top:20px;
		}
		.next-step-aleft {
		    display:inline-block;
		    color:#FEA600;
		    text-decoration:none;
		    margin-right:33px;
		   
		}    
		.next-step-btn{
		    width:338px;
		    height:50px;
		    background-color: #FEA600;
		    border-radius: 100px;
		    border:none;
		    color:white;
		    font-size:18px;
		}

		
		.next-step .next-step-aright {
		    display:inline-block;
		    color:#4A90E2;
		    text-decoration:none;
		    margin-left:33px;
		    vertical-align:bottom;
		}
		
		
		
		
		 form {
		        margin:40px 0;
		    }
		    form .for {
		    margin:15px 0;
		    color:#fff;
		    position:relative;
		}
		label {
		    width:15%;
		    line-height: 50px;
		    margin-right:19px;
		    text-align:right;
		    display: inline-block;
		    float:left;
		}
		.detu {
		    display:block;
		    width:20px;
		    height:27px;
		    position: absolute;
		    top:14px;
		    left:132px;
		    background:url(imgs/map.png) no-repeat center;
		}
		
		.for-btn-triangel {
		    left:250px;
		}
		.for-btn-triangela {
		    left:253px;
		}
		.for-btn-triangelb {
		    position: absolute;
		    top:23px;
		    left:435px;
		}
		 form .for-sbt {
		    width:410px;
		    margin:35px auto;
		    overflow:hidden;
		}
		 .for-sbt button {
		    width:180px;
		    height:60px;
		    float:left;
		    border:none;
		    color:#fff;
		    border:1px solid #fff;
		    border-radius:100px;
		    background:none;
		    font-size:18px;
		}
		 .for-sbt button:hover {
		    background:#fea600;
		    border:none;
		}
		 .for-sbt input[type="reset"] {
		    margin-left: 50px
		}
		.for input {
		    width:450px;
		    height:50px;
		    border-radius:8px;
		    outline: none;
		    text-indent:.5cm;
		    border:1px solid #95d6e2;
		}
		label,p {
		    color:#333;
		}
		.juchan {
		    width: 89%;
		    overflow: hidden;
		}
		.juchan .for {
		    width:50%;
		    float:left;
		}
		.juchan >.for > label {
		    width:34%;
		}
		.roup {
		    width:16px;
		    height:16px;
		    display: inline-block;
		    background: url(imgs/roup.png) no-repeat center;
		    vertical-align:bottom;
		}  
		#inpt {
		    width:30%;
		    border:none;
		    background:none;
		} 
		.rest {
		    display: block;
		    padding-left: 50px;
		    font-size: 14px;
		    border:0;
		    background:none;
		}
		<#--行业信息结束-->			
				
		
		
		
		/*上传认证信息*/
		
	
		.form-group {
		    overflow: hidden;
		}
		.control-label {
		    width:140px;
		    float:left;
		    text-align: right;
		    line-height: 54px;
		}
		 .input {
		    width:530px;
		    float:right;
		    text-align:left;
		}
		.input input {
		    width:400px;
		    height: 50px;
		    border-radius: 8px;
		    border:1px solid #e6e6e6;
		    text-indent: .4cm;
		}
		.boxt {
		    width:230px;
		    height:160px;
		    margin-top:30px;
		    background:#f2f2f2;
		    border:1px dashed #ccc;
		}
		.boxt-add {
		    width:138px;
		    height: 25px;
		    margin:65px auto 0 ;
		    line-height: 25px;
		    font-size:16px;
		    color:#9b9b9b;
		    padding-left:55px;
		    background:url(/res/images/ff.png) no-repeat left center;
		}
		
		
		
		
	
		.for-btn {
		    padding-bottom:30px;
		    border-bottom:1px dashed #fff;
		}
		<#--人才注册首页开始-->
		.form-group-cent {
			width:100%;
		}
		.group-cent-inpt {
			width:400px;
			margin:0 auto;
		}
		<#--人才注册首页结束-->
		<#--人才基本信息开始 样式-->
        
        
        .glyphicon-plus{
            font-family: 'Microsoft YaHei'!important;
        }
    	
		<#--人才基本信息结束-->
		
		
		
		.txt {
		    width:336px;
		    margin:0 auto;
		    text-align: left;
		}
		.txt p {
		    color:#999;
		}
		.txt-right {
		    display: inline-block;
		    width:16px;
		    height:16px;
		    background:url(imgs/dsd.png);
		}
		
		
		
		
		
		
		
		
    </style>
    </@block>
    <@block name="body">
    <#--头部-->
        <@mainhead clazz="partner" picpath="/res/images/banner_recommend.png" pagetype="form" user="person"/>
	
		<#--<div class="recommend-form" id="personnelStep1"  style="width:564px; margin:0 auto">
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
		                    <label class="checkbox-inline" style="padding-left:12%;width:72%">
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
		</div>
	</div>	-->
		<#--人才推荐方注册-基本信息开始-->
	<#-- <div class="recommend-form" id="personnelStep1"  style="width:564px;">
		    <div class="form-box" style="background:#123">
		        <div class="title">人才推荐方注册-基本信息</div>
		        <div class="hr-line"></div>

		        <form class="row form-group form-horizontal" role="form" id="personnel-form1">
		            <input type="hidden" id="title" value="person">
		            <div class="form-group">
		                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span>您的身份</label>
		                <div class="col-xs-7 col-md-7" style="width:67%">
		                    <div class="btn-group">	
		                        <button type="button" class="btn btn-default" data-title="company">猎头公司</button>
		                        <button type="button" class="btn btn-default active-title" data-title="person">个人猎头</button>
		                        <button type="button" class="btn btn-default" data-title="hr">HR</button>
		                        <button type="button" class="btn btn-default" data-title="hr">培训机构</button>
		                        <button type="button" class="btn btn-default" data-title="other">其他</button>
		                    </div>
		                    <input type="hidden" name="type" id="typeId" value="person"/>
		                </div>
		            </div>

		            <div class="form-group">
		                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 姓名</label>
		                <div class="col-xs-7 col-md-7">
		                    <input class="form-control" type="text" id="name" name="name" placeholder="请输入姓名" maxlength="20" style="width:264px;">
		                </div>
		            </div>

		            
		           <div class="form-group">
		                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 出生年月<span class="secret-circle"></span></label>
		                <div class="col-xs-7 col-md-7 group-years">
		                     <span class="date-select">
		                     	<img src="${_PATH}/res/images/date_icon.png" class="date-icon">
		                        <input type="text"  class="form-control"  id="birthday" name="birthday"  readonly  placeholder="请选择你的出生年月" style="background-color: white;min-width:264px;"/>
		                    </span>
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
		                <a href="javascript:;">先跳过</a>
		            </div>
		        </form>
		    </div>
		</div>
	</div>-->
	
	
		<#--行业信息开始-->
	<div class="recommend-form" id="personnelStep1"  style="width:720px; ">
	    <div class="form-box">
	        <div class="title">人才推荐方注册-行业信息</div>
	        <div class="hr-line"></div>
	        <form class="row form-group form-horizontal" role="form" id="personnel-form1">
		       	<div class="for ">
                        <label for=""><span class="required">* </span>从业年限</label>
                        <input type="email" placeholder="6年" style="width:172px; float:left">
                        <span class="triangle for-btn-triangel"></span>
                    </div>
                    <div class="juchan">
                        <div class="for ">
                            <label for="" style="margin-right:2px" ><span class="required">* </span>聚焦行业</label>
                            <input type="email" placeholder="互联网" style="width:166px">
                            <span class="triangle for-btn-triangel"></span>
                         </div>
                         <div class="for ">
                            <label for=""><span class="required">* </span>擅长职位</label>
                            <input type="email" placeholder="开发工程师" style="width:166px">
                            <span class="triangle for-btn-triangel"></span>
                         </div>
                    </div>
                    <div class="for">
                        <label for=""><span class="required">* </span>所在城市</label>
                        <ul class="tab tab-li">
                            <li style="width:50%">上海</li>
                            <li style="width:50%">上海市</li>
                        </ul>
                        <span class="detu"></span>
                        <span class="triangle for-btn-triangela"></span>
                        <span class="triangle for-btn-triangelb"></span>
                    </div>
                    <div class="for" style="overflow:hidden">
                        <label for="">公司名称</label>
                        <input type="text" placeholder="请输入所在公司名称" style="width:70%; float:left">
                    </div>
	            <div class="next-step">
	            	<a style="color:#fea600;margin-right:33px; vertical-align:text-top;>上一步</a>
	                <button type="button" class="next-step-btn" id="nextStepBtn1">保存并继续下一步</button>
	                <a class="next-step-aright" href="javascript:;">先跳过</a>
	            </div>
	        </form>
	    </div>
	</div> -->
		<#-- 上传认证信息 -->
	<#--<div class="recommend-form" id="personnelStep1"  style="width:720px; background:#354">
	    <div class="form-box">
	        <div class="title">人才推荐方注册-上传认证信息</div>
	        <div class="hr-line"></div>
	        <form class="row form-group form-horizontal" role="form" id="personnel-form1">
		        <div class="form-group form-group-btn">
	                <label class="control-label">身份证号</label>
	                <div class="input">
	                    <input class="form-control" type="text" id="name" name="name" placeholder="请输入身份证号" maxlength="20" style="width:400px;">
	                    <div class="boxt">
	                    	<p class="boxt-add">身份证正面</p>
	                    </div>
	                </div>
	            </div>
	            <div class="txt">
	            <h1>选传其它证件：</h1>
	            <p>项目紧急联系人&nbsp;<span class="txt-right"></span></p>
	            <p>参考项目&nbsp;<span class="txt-right"></span></p>
	            <p>项目所在城市&nbsp;<span class="txt-right"></span></p>
	            </div>
	            <div class="next-step">
	            	<a href="javascript:;" style="color:#fea600;margin-right:33px; vertical-align:text-top;">上一步</a>
	                <button type="button" class="next-step-btn" id="nextStepBtn1">保存并继续下一步</button>
	                <a class="next-step-aright" href="javascript:;">先跳过</a>
	            </div>
	        </form>
	    </div>
	</div>
	
	
	<#--行业信息结束-->
        <#--正文开始-->
        <div class="content">
        	
        </div>
		
    </@block>
	
    <#--插入底部标签覆盖底部-->
	    <@block name="footer"></@block>

     <@block name="script">
        <script  src="${_PATH}/res/script/myjs/partner/elite.js"></script>
    </@block>
</@extend>