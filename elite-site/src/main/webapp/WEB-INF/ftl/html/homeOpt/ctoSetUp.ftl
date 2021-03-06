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

        .project-box{
            width:100%;
            height:175px;
            border:1px solid #E6E6E6;
            border-radius: 6px;
            margin-top:50px;
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
            margin-top:10px;
        }

    </style>
    </@block>
    <@block name="body">

    <#--头部-->
        <@accounthead opt=""/>


    <div class="content">
        <div class="flag">
            <span>个人主页 &gt; 我的项目 &gt; <span style="color:#2CB7C9;">确认立项书</span></span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle" style="left:190px;">2</div>
                <div class="process-circle" style="left:360px;">3</div>
                <div class="process-circle" style="left:530px;">4</div>
                <div class="process-circle" style="left:700px;">
                    <img src="/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">立项</p>
                <p class="process-title" style="left:178px;">需求梳理</p>
                <p class="process-title" style="left:345px;">界面设计</p>
                <p class="process-title" style="left:518px;">开发测试</p>
                <p class="process-title" style="left:688px;">交付上线</p>

                <div class="process-line"></div>
                <div class="process-line" style="left:240px;"></div>
                <div class="process-line" style="left:410px;"></div>
                <div class="process-line" style="left:580px;"></div>

            </div>

            <div class="project-box">
                <div style="position: relative;">
                    <div class="part1">
                        <div class="col-xs-3 col-md-3">
                            <span>创建时间：2016-03-02  9：30</span>

                        </div>
                        <div class="col-xs-9 col-md-9">
                            <span class="pull-left">任务编号：2016030200008</span>
                        </div>
                    </div>
                    <div class="vertical-line"></div>
                </div>

                <div class="project-detail">
                    <div class="main-info">
                        <div style="padding:20px;">
                            <div class="project-logo">堆</div>
                            <div class="project-info">
                                <div>
                                    <span class="project-name">堆糖网</span>
                                    <div class="class-label">电商</div>
                                    <div class="class-label">社交</div>
                                </div>
                                <div style="margin-top:10px;"><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                                <div><span class="index">我的竞标价格:</span>&nbsp;<span class="value">20万</span></div>
                                <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02 &nbsp;共45个工作日</span></div>
                            </div>

                        </div>
                    </div>
                    <div class="status">
                        <div style="padding-top:60px;text-align: center;">
                            <p><span class="index">标书承诺质保金：</span>&nbsp;<span class="value">20000（10%）</span></p>
                        </div>
                    </div>
                    <div class="opt">
                        <div style="padding-top:46px;">
                            <p style="color:#2CB7C9;">已立项</p>
                            <a href="#" target="_blank" style="font-size: 14px;">查看详情</a>
                        </div>
                    </div>
                </div>
            </div>

            <div class="setup-agreement">
                <p class="text-center">《堆糖网立项确认书》</p>
            </div>
            <div style="margin-top:20px;text-align: right;padding-right:100px;">
                <div style="margin-bottom:10px;">
                    <label class="checkbox-inline">
                        <input type="checkbox" id="inlineCheckbox1" value="option1" checked>
                        &nbsp;<span style="font-size:12px;">我已阅读并同意<span style="color:#D0021B;">《堆糖网立项确认书》</span></span>
                    </label>
                </div>
                <p>
                    <img src="/res/images/clock_icon.png" width="16" height="16">
                    &nbsp;<span style="font-size: 12px;color:#2CB7C9;">还剩3天 13时 16：40分</span>
                </p>
                <button type="button" class="ok-btn">确认</button>
            </div>

        </div>
    </div>


    </@block>
    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/index.js')}"></script>
    </@block>
</@extend>