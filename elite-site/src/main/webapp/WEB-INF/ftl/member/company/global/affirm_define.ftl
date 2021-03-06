<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
        .content{
            width:1000px;
            margin:0 auto;
        }

        .flag{
            font-size: 14px;
            color:#5C5C5C;
            padding-top:20px;
            margin-bottom: 20px;
        }

        .content-box{
            width:100%;
            background-color: #FAFAFA;
            padding-top:20px;
            padding-bottom: 130px;
        }

        .process{
            width:720px;
            height:100px;
            position: relative;
            margin:0 auto;
        }

        .process-line{
            width:150px;
            border-bottom: 2px solid #BFBFBF;
            position: absolute;
            top:36px;
            left:70px;
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
            top:16px;
            left:20px;
            z-index: 200;
        }

        .active-process{
            background-color: #7DD2E1;
        }

        .process-title{
            position: absolute;
            top:68px;
            left:16px;
        }

        .active-title{
            color:#2CB7C9;
        }

        .finish-line{
            border-color: #95D6E2;
        }

        .process-tip{
            position: absolute;
            left:108px;
            top:16px;
            font-size: 12px;
            color:#9B9B9B;
        }

        .project-box{
            width:100%;
            height:175px;
            border:1px solid #E6E6E6;
            border-radius: 6px;
        }

        .part1{
            width:100%;
            height:40px;
            line-height: 40px;
            background-color: #F2F2F2;
            border-bottom: 1px solid #E6E6E6;
            font-size: 14px;
            color:#9B9B9B;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
        }

        .vertical-line{
            height:28px;
            border-right:1px solid #E6E6E6;
            position: absolute;
            top:5px;
            left:240px;
        }

        .project-detail{
            width:100%;
            height:133px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        .main-info{
            float:left;
            width:54%;
            height:135px;
            border-right: 1px solid #E6E6E6;
        }

        .project-logo{
            width:95px;
            height:95px;
            line-height: 95px;
            text-align: center;
            background-color: #F29D70;
            color:white;
            font-size: 58px;
            float:left;
        }

        .project-info{
            float:left;
            margin-left:20px;
        }
        .project-name{
            display: inline-block;
            margin-right:10px;
        }
        .index{
            font-size: 12px;
            color:#9B9B9B;
        }

        .value{
            font-size: 12px;
        }

        .status{
            float:left;
            width:22%;
            height:133px;
            border-right: 1px solid #E6E6E6;
            font-size: 12px;
        }

        .opt{
            float:left;
            width:24%;
            height:133px;
            background-color: #F5FBFC;
            text-align: center;
        }


        .class-label{
            display: inline-block;
            width:40px;
            height:16px;
            line-height: 16px;
            text-align: center;
            border:1px solid #E6E6E6;
            border-radius: 10px;
            color:#9B9B9B;
            font-size: 12px;
            margin-right:5px;
        }

        .setup-agreement{
            width:800px;
            border-radius: 8px;
            background-color: #F5FBFC;
            margin-left:auto;
            margin-right:auto;
            margin-top: 40px;
            padding-top:20px;
            padding-bottom: 20px;
        }

        .ok-btn{
            width:150px;
            height:40px;
            border:none;
            border-radius: 18px;
            background-color: #FEA600;
            color:white;
            margin-top:30px;
        }
        
        .setup-agreement-content{
		    margin: 25px 92px;
		}
		
		.text-center{
		    text-align: center;
		}
		
		/*确认书*/
		.margintop20{
		    margin-top: 20px;
		}
		.marginbottom12{
		    margin-bottom: 12px;
		}
		.marginbottom20{
		    margin-bottom: 20px;
		}
		.marginbottom25{
		    margin-bottom: 25px;
		}
		.fontsize16{
		    font-size: 16px;
		}
		.fontsize12{
		    font-size: 12px;
		}
		.color4a{
		    color: #4a4a4a;
		}
		.colorfe{
		    color: #FEA600;
		}
		
		.color9b{
		    color: #9b9b9b;
		}
		
		.marginleft20{
		    margin-left: 20px;
		}
		/*表格*/
		
		
		.td-btn{
		    width: 90px;
		    height: 26px;
		    border-radius: 32px;
		    font-size: 12px;
		    background: #2cb7c9;
		    color: #fff;
		    line-height: 26px;
		    text-align: center;
		
		}
		
		.three-box{
		    width: 500px;
		    height: 430px
		}
		.content-title .title-b{
		    font-size: 12px;
		    color: #4a4a4a;
		    line-height: 30px;
		}
		.content-title .title-b span{
		    color: #4a4a4a;
		}
		.content-title1{
		    width: 500px;
		    height: 30px;
		}
		.content-title{
		    width: 500px;
		    height: 30px;
		    margin-bottom: 25px;
		}
		.title-a{
		    width: 25%;
		    height: 30px;
		    float: left;
		    text-align: center;
		}
		
		.title-b{
		    width: 50%;
		    height: 30px;
		    float: left;
		    text-align: center;
		}
		.title-c{
		    width: 25%;
		    height: 30px;
		    float: left;
		    text-align: center;
		}
		
		.four-box{
		    width: 675px;
		    height: 170px;
		    border-radius: 6px;
		    font-size: 16px;
		    color: #4a4a4a;
		    background: #fff;
		    border: 1px solid #e6e6e6;
		    margin-top: 15px;
		    margin-left: 15px;
		    margin-bottom: 15px;
		    overflow: auto;
		}
		.four-box-content{
		    margin: 20px;
		    font-size: 14px;
		    height: 1000px;
		}
		.fiv-box{
		    width: 675px;
		    height: 151px;
		    border-radius: 6px;
		    border: 1px solid #e6e6e6;
		    background: #fff;
		    margin-top: 15px;
		    margin-left: 15px;
		    margin-bottom: 15px;
		}
		.fiv-box-content{
		    margin: 20px 110px 36px 20px;
		    font-size: 14px;
		}
		.six-box{
		    width: 673px;
		    height: 38px;
		    border-radius: 6px;
		    border: 1px solid #e6e6e6;
		    background: #fff;
		    margin-top: 15px;
		    margin-left: 15px;
		    margin-bottom: 15px;
		}
		.six-box-content{
		    margin-top: 10px;
		    margin-bottom: 10px;
		    margin-left: 25px;
		}
		.six-icon-a{
		    width: 18px;
		    height: 20px;
		    display: block;
		    background: url("${_PATH}/res/images/six-icon-a.png");
		    margin-right: 9px;
		    cursor: pointer;
		    
		}
		.six-icon-b{
		    width: 20px;
		    height: 20px;
		    display: block;
		    background: url("${_PATH}/res/images/six-icon-b.png");
		    cursor: pointer;
		    margin-right:30px;
		}
		.fl{
		    float: left;
		}
		.six-txt{
		    margin-right: 49px;
		 }  

		.complete-success {
			width: 100%;
			height: 100%;
			min-width: 1920px;
			position: fixed;
			overflow-x: hidden;
			top: 0;
			left: 0;
			z-index: 999;
			background: url("/res/images/layer_bg.png");
		}
		
		.db {
			display: block;
		}
		
		.resu {
			width: 600px;
			height: 280px;
			background: #F5FBFC;
			border-radius: 20px;
			box-shadow: 2px 2px 10px 0px rgba(0, 0, 0, 0.18);
			position: absolute;
			left: 50%;
			top: 50%;
			margin-left: -300px;
			margin-top: -140px;
		}
		
		.resu-b {
			width: 310px;
			margin: 0 auto;
		}
		
		.color2c {
			color: #2CB7C9;
		}
		
		.color9B {
			color: #9B9B9B;
		}
		
		.colorFE {
			color: #FEA600;
		}
		
		.resu-bg {
			display: block;
			width: 50px;
			height: 48px;
			background: url("/res/images/resu-bg.png");
		}
		
		.resu-btn {
			display: block;
			background: #FEA600;
			border-radius: 72px;
			width: 140px;
			height: 40px;
			line-height: 40px;
			color: #fff;
			text-align: center;
			cursor: pointer;
			text-decoration: none;
		}
		.paddingtop50 {
			padding-top: 50px;
		}
		
		.padingleft58 {
			padding-left: 58px;
		}
		
		.lineh40 {
			line-height: 40px;
		}
		
		.lineh50 {
			line-height: 50px;
		}
		
		.fontsize24 {
			font-size: 24px;
		}
		
		.fontsize14 {
			font-size: 14px;
		}
		
		.marginright30 {
			margin-right: 30px;
		}
		
		.marginright36 {
			margin-right: 36px;
		}
		
		.margintop69 {
			margin-top: 69px;
		}
    </style>
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <div class="content">
        <div class="flag">
            <span><span style="cursor:pointer;" id="crumbs_main">个人主页</span> &gt; 
                <span style="color:#2CB7C9;cursor:pointer;" id="myProject">我的项目</span> &gt; 
                <span style="color:#2CB7C9;cursor:pointer;" id="projectDetail" data="${obj.id}">项目详情</span> &gt; 
	            <span style="color:#2CB7C9;cursor:pointer;">确认立项书</span>
	        </span> 
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
               <div class="process-circle <#if obj.status!='wait_audit'>active-process</#if>" style="left:230px;">2</div>
			    <div class="process-circle <#if obj.status!='audit_in'&&obj.status!='wait_audit'>active-process</#if>" style="left:440px;">3</div>
			    <div class="process-circle <#if obj.status!='pass_wait'&&obj.status!='audit_in'&&obj.status!='wait_audit'>active-process</#if>" style="left:650px;">
			        <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
			    </div>
			    <p class="process-title active-title">待审核</p>
			    <p class="process-title <#if obj.status!='wait_audit'>active-title</#if>" style="left:224px;">审核中</p>
			    <p class="process-title <#if obj.status!='audit_in'&&obj.status!='wait_audit'>active-title</#if>" style="left:410px;">审核通过,待立项</p>
			    <p class="process-title <#if obj.status!='pass_wait'&&obj.status!='audit_in'&&obj.status!='wait_audit'>active-title</#if>" style="left:636px;">立项成功</p>

                <div class="process-line finish-line"></div>
                <div class="process-line finish-line" style="left:280px;"></div>
                <div class="process-line finish-line" style="left:490px;"></div>

                <p class="process-tip">项目经理对接</p>
                <p class="process-tip" style="left:310px;">提交项目意向金</p>
                <p class="process-tip" style="left:524px;color:#FEA600;">核实立项确认书</p>
            </div>

            <p style="margin-top:30px;">项目立项对象</p>
            <div class="project-box">
                <div style="position: relative;">
                    <div class="part1">
                        <div class="col-xs-3 col-md-3">
                            <span>创建时间：${obj.createTime?string("yyyy-MM-dd HH:mm")}</span>
                        </div>
                        <div class="col-xs-9 col-md-9">
                            <span class="pull-left">项目编号：${obj.projectNum}</span>
                        </div>
                    </div>
                    <div class="vertical-line"></div>
                </div>
                <input type="hidden" id="projectId" name="projectId" value="${obj.id}">
                <div class="project-detail">
                    <div class="main-info">
                        <div style="padding:20px;">
                            <div class="project-logo" style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
                            <div class="project-info">
                                <div>
                                    <span class="project-name" title="${obj.name}"><#if obj.name?length gt 8>${obj.name?substring(0,8)}...<#else>${obj.name}</#if></span>
                                     <#list obj.functionValList as fnc>
                                        <div class="class-label">${fnc}</div>
                                     </#list>
                                </div>
                                <div style="margin-top:10px;"><span class="index">开发类型:</span>&nbsp;<span class="value" title="${obj.solutionVals}"><#if obj.solutionVals?length gt 12>${obj.solutionVals?substring(0,12)}<#else>${obj.solutionVals}</#if></span></div>
                                <div><span class="index">预算:</span>&nbsp;<span class="value">${obj.projectBudget}</span></div>
                                <div><span class="index">期望交付日期:</span>&nbsp;
                                <span class="value"><#if obj.deliveryTime??>-${obj.deliveryTime?string("yyyy.MM.dd")}<#else>${obj.expectTime?string("yyyy-MM-dd")}</#if> &nbsp;共${obj.deliveryDay?string('#')}个工作日</span></div>
                            </div>

                        </div>
                    </div>
                    <div class="status">
                        <div style="padding-top:36px;padding-left:50px;">
                            <p style="color:#9B9B9B;"></p>
                            <p><span class="index">已提交意向金：</span>&nbsp;<span class="value">${obj.intentionAmount?string.currency}</span></p>
                            <p><span class="index">股权托管：</span>&nbsp;<span class="value"><#if define.isStock>有<#else>无</#if></span></p>
                        </div>
                    </div>
                    <div class="opt">
                        <div style="padding-top:46px;">
                            <#assign statusLabel=""/>
			                <#if obj.status=='wait_audit'>
			                    <#assign statusLabel="待审核"/>
			                <#elseif obj.status=='audit_in'>
			                    <#assign statusLabel="审核中"/>
			                <#elseif obj.status=='pass_wait'>
			                    <#assign statusLabel="审核通过待立项"/>
			                <#elseif obj.status=='unpass'>
                                <#assign statusLabel="审核未通过"/>
			                <#elseif obj.status=='pass_already'>
			                    <#assign statusLabel="已立项"/>
			                </#if>
                            <p style="color:#2CB7C9;">${statusLabel}</p>
                            <a href="javascript:void(0);" id="projectDetail1" style="font-size: 14px;" data="${obj.id}">查看详情</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="setup-agreement">
                <p class="text-center">《${obj.name}立项确认书》</p>
                <p class="marginbottom20 fontsize16 color4a">一、研发费用<span class="marginleft20">${define.totalAmount?string.currency}</span></p>
		        <p class="fontsize16 color4a marginbottom20">二、股权<span class="marginleft20"><#if define.isStock>${define.stockPercent}<#else>无</#if></span></p>
		        <div>
		            <p class="fontsize16 color4a marginbottom12">三、研发计划</p>
		            <div class="three-box">
		                <div class="three-box-content">
		                    <div class="content-title1">
		                        <div class="title-a fontsize12 color9b">项目阶段</div>
		                        <div class="title-b fontsize12 color9b">阶段时间规划</div>
		                        <div class="title-c fontsize12 color9b">阶段所需费用</div>
		                    </div>
		
		                    <div class="content-txt">
		                        <#list define.stages as dst>
			                        <div class="content-title">
			                            <div class="title-a">
			                                <div class="td-btn">${dst.title}</div>
			                            </div>
			                            <div class="title-b">${dst.startTime?string("yyyy-MM-dd")}-${dst.originalStopTime?string("yyyy-MM-dd")}<span>|
			                            </span>共<span class="colorfe">${dst.deliveryDay}</span>天</div>
			                            <div class="title-c colorfe">${dst.amountUnit}</div>
			                        </div>
		                        </#list>
		                    </div>
		                </div>
		            </div>
		        </div>
		
		        <div>
		            <p>四、立项确认书详细条款</p>
		            <div class="four-box">
		                <div class="four-box-content">
		                     ${define.clause}
		                </div>
		            </div>
		        </div>
		        <#if define.otherDesc??>
			        <div>
			            <p>五，其他说明</p>
			            <dl class="fiv-box">
			                <div class="fiv-box-content">
			                   ${define.otherDesc}
			                </div>
			            </dl>
			        </div>
		        </#if>
		        <#if define.attaId??>
		        <div>
		            <p>六、附件下载</p>
		            <div class="six-box">
		                <div class="six-box-content">
		                    <span class="six-icon-a fl"></span>
		                    <span class="six-txt fl">${define.atta.fileName}</span>
		                    <a href="${define.atta.downPath}" target="_blank"><span class="six-icon-b fl"></span></a>&nbsp;&nbsp;&nbsp;
		                </div>
		            </div>
		        </div>
		        </#if>
            </div>
            <div style="margin-top:20px;text-align: right;padding-right:20px;">
                <div>
                    <label class="checkbox-inline">
                        <input type="checkbox" id="agreeDefine" value="1" checked>
                        &nbsp;<span style="font-size:12px;">同意立项书条款</span>
                    </label>
                </div>
                <button type="button" class="ok-btn">确认</button>
            </div>
        </div>
    </div>
    
    <div class="complete-success" style="display: none">
        <div class="resu">
			<div class="paddingtop50 padingleft58 lineh50"><span class="resu-bg fl marginright36"></span><span class="color2c fl fontsize24">恭喜！你已成功立项！</span></div>
			<div class="margintop69 resu-b">
				<span class="colorFE fontsize14 fl db lineh40" id="down_time">6s</span>
				<span class="color9B fl db lineh40 marginright30" id="enter_index">后进入个人主页</span>
				<a href="javascript:void(0);" class="resu-btn fl db">进入个人主页</a>
			</div>
		</div>
	</div>
    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/company/global/define.js"></script>
    </@block>
</@extend>