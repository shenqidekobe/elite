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

        .tender-box{
            margin-top:40px;
            margin-left:auto;
            margin-right:auto;
            width:800px;
            background-color: #F5FBFC;
            border:1px solid #E6E6E6;
            border-radius: 8px;
            padding:20px;
        }

        .download-box{
            width:250px;
            height:30px;
            line-height: 30px;
            font-size: 14px;
            color:#4A90E2;
            float:right;
            border:1px solid #E6E6E6;
            border-radius: 4px;
            text-align: center;
        }

        .download-icon{
            width:20px;
            height:20px;
        }

        .project-intro{
            width:100%;
            height:274px;
            border:1px dashed #CCCCCC;
            margin-top:20px;
            padding:20px;
        }

        .project-logo{
            width:60px;
            height:60px;
            line-height: 60px;
            text-align: center;
            background-color: #EE706B;
            font-size: 24px;
            color:white;
            float:left;
        }

        .index{
            font-size: 14px;
            color:#4A4A4A;
        }

        .value{
            font-size: 14px;
            color:#ABABAB;
        }

        .info-box{
            width:216px;
            float:left;
        }

        .end-time{
            font-size: 14px;
            text-align: right;
            margin-top:30px;
            padding-right:100px;
        }

        .go-bid-btn{
            width:180px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 20px;
            color:white;
            float:right;
            display: block;
            margin-right: 100px;
        }

    </style>
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>

    <div class="content">
        <div class="flag">
            <span><span style="cursor:pointer" id="myMain">个人主页</span> &gt; <span style="cursor:pointer;color:#2CB7C9;" id="myProject">我的项目</span> &gt;
                <span style="color:#2CB7C9;">竞标</span>
            </span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle active-process" style="left:230px;">2</div>
                <div class="process-circle" style="left:440px;">3</div>
                <div class="process-circle" style="left:650px;">
                    <img src="${_PATH}/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">待竞标</p>
                <p class="process-title active-title" style="left:213px;">CTO竞标</p>
                <p class="process-title" style="left:442px;">中标</p>
                <p class="process-title" style="left:638px;">项目启动</p>

                <div class="process-line finish-line"></div>
                <div class="process-line" style="left:280px;"></div>
                <div class="process-line" style="left:490px;"></div>

                <p class="process-tip" style="left:284px;">填写竞标书，项目经理审核</p>
            </div>
            <input type="hidden" id="projectId" value="${obj.id}">
            <input type="hidden" id="deadlineTime" value="${tender.tenderoverVal}">
            <div class="tender-box">
                <p style="font-size: 18px;" class="text-center">《${obj.name}项目招标书》</p>
                <div>
                    <span style="line-height: 30px;">项目简介</span>
                    <#if tender.atta??>
                     <div class="download-box">
                   		<img src="${_PATH}/res/images/brooch_icon.png" width="18" height="20">
                        &nbsp;<a href="${tender.atta.downPath}">${tender.atta.fileName}</a>
                        &nbsp;
                        <a href="#" style="margin-left:30px;">
                            <a href="${tender.atta.downPath}"><img src="${_PATH}/res/images/download_icon.png" class="download-icon"></a>
                        </a>
                     </div>   
                    </#if>
                </div>

                <div class="project-intro">
                    <p style="font-size: 12px;color:#9B9B9B;">项目编号：${obj.projectNum}</p>
                    <div>
                        <div class="project-logo" style="background-color:#${obj.backgroundColor}">${obj.firstName}</div>
                        <div style="float:left;margin-left:20px;">
                            <div>${obj.name}</div>
                            <div>
                                <span class="index">开发类型：</span>&nbsp;<span class="value">${obj.solutionVals}</span>
                            </div>
                            <div class="date_released">
                                <span class="index">期望交付日期：</span>&nbsp;
                                <span class="value"><#if tender.startTime??>${tender.startTime?string("yyyy-MM-dd")}</#if>-<#if tender.endTime??>${tender.endTime?string("yyyy-MM-dd")}</#if> &nbsp;&nbsp;共${tender.deliveryDay?string('#')}个工作日</span>
                            </div>

                            <div style="margin-top:20px;">
                                <div class="info-box">
                                    <p>
                                        <span class="index">项目经理：</span>&nbsp;<span class="value">${obj.pmName}</span>
                                    </p>
                                    <p>
                                        <span class="index">项目所在城市：</span>&nbsp;<span class="value">${obj.areaName}</span>
                                    </p>
                                </div>

                                <div class="info-box">
                                    <p>
                                        <span class="index">行业分类：</span>&nbsp;
                                        <span class="value">
                                           <#list obj.industryValList as ids>${ids}&nbsp;&nbsp;</#list>
                                        </span>
                                    </p>
                                    <p>
                                        <span class="index">功能分类：</span>&nbsp;
                                        <span class="value">
											<#list obj.functionValList as ids>${ids}&nbsp;&nbsp;</#list>
										</span>
                                    </p>
                                </div>
                            </div>
                            <div>
                                <p>
                                    <span class="index">参考项目：</span>
                                    <span class="value">
                                        ${obj.referProject}
                                    </span>
                                </p>

                                <p>
                                    <span class="index">标书承诺质保金：</span>&nbsp;<span class="index">项目款的15%</span>
                                    <#--&nbsp;<img src="${_PATH}/res/images/tips_icon.png" width="14" height="14">-->
                                </p>
                            </div>
                        </div>
                    </div>

                </div>

                <div style="margin-top:30px;">
                    <p>标书描述</p>
                    <p style="font-size: 14px;">${tender.content}</p>
                </div>
            </div>

           <p class="end-time">
               <span>距离招标截止：</span>&nbsp;<span id="deadlineSpan"></span>
           </p>
            <button type="button" class="go-bid-btn">去竞标</button>

        </div>
    </div>

    </@block>
    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/cto/global/looktender.js"></script>
    </@block>
</@extend>