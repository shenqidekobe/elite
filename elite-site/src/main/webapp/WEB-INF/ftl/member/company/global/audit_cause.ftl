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
            width:520px;
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

        .goto-pay-btn{
            width:140px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 20px;
            color:white;
            float:right;
            margin-top:20px;
            margin-bottom: 60px;
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
	            <span style="color:#2CB7C9;cursor:pointer;">查看原因</span>
	        </span> 
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle active-process" style="left:230px;">2</div>
			    <div class="process-circle " style="left:440px;">
			        <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
			    </div>
			    <p class="process-title active-title">审核未通过</p>
			    <p class="process-title active-title" style="left:224px;">查看原因</p>
			    <p class="process-title " style="left:410px;">再次发布</p>

                <div class="process-line finish-line"></div>
                <div class="process-line" style="left:280px;"></div>

                <p class="process-tip" style="left:310px;">修改完善项目细节</p>
            </div>

            <br/><br/>
            <div class="project-box">
                <div style="position: relative;">
                    <div class="part1">
                        <div class="col-xs-3 col-md-3">
                            <span>创建时间：${obj.createTime?string("yyyy-MM-dd HH:mm")}</span>
                        </div>
                        <div class="col-xs-9 col-md-9">
                            <span class="pull-left">任务编号：${obj.projectNum}</span>
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
                                    <span class="project-name">${obj.name}</span>
                                     <#list obj.functionList as fnc>
                                        <div class="class-label">${fnc}</div>
                                     </#list>
                                </div>
                                <div style="margin-top:10px;"><span class="index">开发类型:</span>&nbsp;<span class="value">${obj.solutionVals}</span></div>
                                <div><span class="index">预算:</span>&nbsp;<span class="value">${obj.projectBudget}</span></div>
                                <div><span class="index">期望交付日期:</span>&nbsp;
                                <span class="value">${obj.expectTime?string("yyyy-MM-dd")} &nbsp;共${obj.deliveryDay?string('#')}个工作日</span></div>
                            </div>

                        </div>
                    </div>
                    <div class="status">
                        <div style="padding-top:36px;padding-left:50px;">
                            <p style="color:#9B9B9B;"></p>
                            <p><span class="index">已提交意向金：</span>&nbsp;<span class="value">${obj.intentionAmount?string.currency}</span></p>
                            <p><span class="index">股权托管：</span>&nbsp;<span class="value"><#if obj.isStock>有<#else>无</#if></span></p>
                        </div>
                    </div>
                    <div class="opt">
                        <div style="padding-top:46px;">
                            <p style="color:#2CB7C9;">审核未通过</p>
                            <#--<a href="javascript:void(0);" target="_blank" id="projectDetail1" style="font-size: 14px;" data="${obj.id}">查看详情</a>-->
                        </div>
                    </div>
                </div>
            </div>
            <p style="margin-top:30px;">审核未通过原因</p>
            <div class="setup-agreement">
                <p class="marginbottom20 fontsize16 color4a"><span class="marginleft20">${obj.auditReason}</span></p>
            </div>
            <button type="button" class="goto-pay-btn" data="${obj.id}">去修改</button>
        </div>
    </div>
    
    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/company/global/cause.js"></script>
    </@block>
</@extend>