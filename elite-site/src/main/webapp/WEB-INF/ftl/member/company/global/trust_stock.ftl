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
            width:760px;
            height:100px;
            position: relative;
            margin:0 auto;
        }

        .process-line{
            width:110px;
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
            left:24px;
        }

        .active-title{
            color:#2CB7C9;
        }

        .finish-line{
            border-color: #95D6E2;
        }

        .process-tip{
            position: absolute;
            left:94px;
            top:0;
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

        .ok-btn{
            width:286px;
            height:40px;
            border:none;
            border-radius: 20px;
            background-color: #FEA600;
            color:white;
            margin-top:20px;
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
	            <span style="color:#2CB7C9;cursor:pointer;">托管股权</span>
            </span>
        </div>

        <div class="content-box">
           <div class="process">
                <div class="process-circle active-process">1</div>
                <#assign left=190/>
                <#assign step=1/>
                <#assign activeStep=""/>
                <#if obj.projectDefine??>
                   <#list obj.projectDefine.stages as pds>
                      <#assign step=step+1/>
                      <#if obj.trustStageCount gt pds_index>
	                        <#assign activeStep="active-process"/>
                       </#if>
                      <div class="process-circle ${activeStep}" style="left:${left}px;">${step}</div>
                      <#assign left=left+170/>
                      <#assign activeStep=""/>
                   </#list>
                </#if>
                <div class="process-circle" style="left:${left}px;">
                    <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">立项</p>
                <#assign leftq=178/>
                <#assign activeTitle=""/>
                <#if obj.projectDefine??>
                   <#list obj.projectDefine.stages as pds>
                      <#if obj.trustStageCount gt pds_index>
	                       <#assign activeTitle="active-title"/>
                      </#if>
                      <p class="process-title ${activeTitle}" style="left:${leftq}px;">${pds.title}</p>
                      <#assign leftq=leftq+170/>
                      <#assign activeTitle=""/>
                   </#list>
                </#if>
                <p class="process-title" style="left:${leftq}px;">质保</p>
                
                <div class="process-line finish-line"></div>
                <#assign leftw=240/>
                <#assign active=""/>
                <#if obj.projectDefine??>
                   <#list obj.projectDefine.stages as pds>
	                   <#if obj.trustStageCount gt pds_index>
	                        <#assign active="finish-line"/>
                       </#if>
                       <div class="process-line ${active}" style="left:${leftw}px;"></div>
                       <#assign leftw=leftw+170/>
                       <#assign active=""/>
                   </#list>
                </#if>
                
                <#assign trustStage=obj.trustStage/>
                <#assign tipLeft=70/>
                <#if obj.projectDefine??>
                   <#list 1..obj.trustStageCount as cc>
                       <#assign tipLeft=tipLeft+170/>
                   </#list>
                </#if>
                <div class="process-tip" style="color:#FEA600;left:${tipLeft}px;">
				                    托管${trustStage.title}<br>
				                    费用
                </div>
            </div>

            <p style="margin-top:30px;">股权托管对象</p>
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

                <div class="project-detail">
                    <div class="main-info">
                        <div style="padding:20px;">
                            <div class="project-logo" style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
                            <div class="project-info">
                                <div>
                                    <span class="project-name">${obj.name}</span>
                                    <#list obj.functionValList as pfc>
			                            <div class="class-label">${pfc}</div>
			                        </#list>
                                </div>
                                <div style="margin-top:10px;"><span class="index">开发类型:</span>&nbsp;<span class="value">${obj.solutionVals}</span></div>
                                <div><span class="index">预算:</span>&nbsp;<span class="value">${obj.projectBudget}</span></div>
                                <div><span class="index">期望交付日期:</span>&nbsp;
                                    <span class="value">${obj.expectTime?string("yyyy-MM-dd")} &nbsp;共${obj.deliveryDay?string('#')}个工作日</span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="status">
                        <div style="padding-top:36px;padding-left:50px;">
                            <p style="color:#9B9B9B;"></p>
                            <p><span class="index">已提交意向金：</span>&nbsp;<span class="value">${obj.intentionAmount?string.currency}</span></p>
                            <p><span class="index">股权托管：</span>&nbsp;<span class="value">${obj.stockPercent}</span></p>
                        </div>
                    </div>
                    <div class="opt">
                        <div style="padding-top:46px;">
                            <p style="color:#2CB7C9;">已立项</p>
                            <a href="javascript:void(0);" id="projectDetail1" data="${obj.id}" target="_blank" style="font-size: 14px;">查看详情</a>
                        </div>
                    </div>
                </div>
            </div>

            <div style="margin-top:20px;text-align: right;padding-right:20px;">
                <p><span style="font-size: 14px;">托管股权数</span> &nbsp;&nbsp;<span style="font-size:20px;color:#FEA600;">${obj.stockPercent}</span></p>
                <div style="margin-top:30px;">
                    <label class="checkbox-inline">
                        <input type="checkbox" id="stockCheck" value="1" checked>
                        &nbsp;<span style="font-size:12px;">我已阅读并同意<span style="color:#D0021B;">《云英汇托管协议书》</span></span>
                    </label>
                </div>
                <button type="button" class="ok-btn" data="${obj.id}">确认托管，继续托管第一阶段费用</button>
            </div>

        </div>
    </div>

    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/company/global/stock.js"></script>
    </@block>
</@extend>