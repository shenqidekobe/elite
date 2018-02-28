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
            /*background-color: #FAFAFA;*/
            padding-top:20px;
            padding-bottom: 130px;
        }

        .process{
            width:716px;
            height:100px;
            position: relative;
            margin:0 auto;
        }

        .process-line{
            width:220px;
            border-bottom: 2px solid #BFBFBF;
            position: absolute;
            top:36px;
            left:120px;
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
            left:70px;
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

        .left-part{
            width:320px;
            float:left;
        }

        .right-part{
            width:650px;
            height:650px;
            float:right;
            border:1px solid #E6E6E6;
            background-color: #F5FBFC;
            border-radius: 8px;
        }

        .project-intro{
            width:100%;
            height:400px;
            border:1px solid #E6E6E6;
            border-radius: 8px;

        }

        .box-title{
            width:100%;
            height:40px;
            line-height: 40px;
            background-color: #F2F2F2;
            padding-left:10px;
            border-bottom: 1px solid #E6E6E6;
        }

        .project-box{
            width:100%;
            height:160px;
            background-color: #FAFAFA;
        }

        .project-logo{
            width:60px;
            height:60px;
            line-height: 60px;
            text-align: center;
            background-color:#EE706B;
            font-size: 24px;
            color:white;
            float:left;
        }

        .project-index{
            float:left;
            margin-left:16px;
        }

        .index{
            font-size: 12px;
            color:#9B9B9B;
        }

        .value{
            font-size: 12px;
        }

        .common-problems{
            width:100%;
            height:250px;
            border:1px solid #E6E6E6;
            border-radius: 8px;
            margin-top:20px;
        }

        .more-questions{
            float:right;
            color:#2CB7C9;
            font-size: 12px;
            padding-right:10px;
        }
        .questions-box{
            width:100%;
            height:208px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 8px;
            border-bottom-right-radius: 8px;
            font-size: 12px;
            color:#4A4A4A;
        }

        .questions-box ul li{
            line-height: 27px;
        }

        .tabs{
            width:100%;
            height:50px;
            line-height: 50px;
            border-bottom: 1px solid #B3B3B3;
            position: relative;
        }

        .tab{
            float:left;
            width:50%;
            text-align: center;
        }

        .active-line{
            width:50%;
            border-bottom: 2px solid #FEA600;
            position: absolute;
            bottom: -1px;
            left:0;
        }

        .active-tab{
            color:#FEA600;
        }

        .form-demand{
            margin-top:30px;
            padding-left:20px;
        }

        .required{
            color:#FEA600!important;
        }

        .form-triangle{
            position: absolute;
            bottom: 7px;
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
            border-color:#4A4A4A transparent transparent;
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

        .upload-btn{
            width:280px;
            height:39px;
            line-height: 39px;
            background-color: #F0EEEC;
            border:none;
            border-radius: 6px;
            color:#9B9B9B;
            margin-top:30px;
            display: block;
        }

        .ok-btn{
            width:180px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 20px;
            font-size: 18px;
            color:white;
            margin-top:40px;
        }

    </style>
    </@block>
    <@block name="body">

    <#--头部-->
        <@accounthead opt=""/>


    <div class="content">
        <div class="flag">
            <span>个人主页 &gt; 我的项目 &gt; 堆糖网 项目详情 &gt; <span style="color:#2CB7C9;">需求修改</span></span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle" style="left:350px;">2</div>
                <div class="process-circle" style="left:630px;">
                    <img src="/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">项目方申请需求修改</p>
                <p class="process-title" style="left:278px;">项目经理协商处理需求修改</p>
                <p class="process-title" style="left:600px;">需求追加完成</p>

                <div class="process-line finish-line"></div>
                <div class="process-line" style="left:400px;"></div>
            </div>

            <div style="margin-top:30px;min-height: 650px;">
                <div class="left-part">
                    <div class="project-intro">
                        <div class="box-title" style="font-size: 12px;color:#9B9B9B;border-top-left-radius: 8px;border-top-right-radius: 8px;">项目编号：&nbsp;2016030200008</div>
                        <div class="project-box">
                            <div style="padding:10px;">
                                <div>
                                    <div class="project-logo">堆</div>
                                    <div class="project-index">
                                        <div>堆糖网</div>
                                        <div>
                                            <span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span>
                                        </div>
                                        <div>
                                            <span class="index">总费用:</span>&nbsp;<span class="value">40万</span>
                                        </div>
                                    </div>
                                </div>
                                <p style="clear: left;">
                                    <span class="index">期望交付日期:</span>
                                    &nbsp;<span class="value">2016.4.20-2016.6.1&nbsp;&nbsp;共45个工作日</span>
                                </p>
                            </div>
                        </div>

                        <div class="box-title" style="color:#4A4A4A;border-top: 1px solid #E6E6E6;">项目进程</div>
                        <div class="project-box" style="height:158px;border-bottom-left-radius: 8px;border-bottom-right-radius: 8px;">
                            <div style="padding:14px 10px;">
                                <p>
                                    <span class="index">当前阶段:</span>&nbsp;<span class="value">需求梳理阶段</span>
                                </p>
                                <p>
                                    <span class="index">已托管费用:</span>&nbsp;<span class="value">￥90000.00</span>
                                </p>
                            </div>
                        </div>

                    </div>
                    <div class="common-problems">
                        <div class="box-title" style="color:#4A4A4A;border-top-left-radius: 8px;border-top-right-radius: 8px;">
                            <span>常见问题</span>
                            <span style="float:right;" class="more-questions">更多 &gt;</span>
                        </div>
                        <div class="questions-box">
                            <div style="padding:10px 30px;">
                                <ul style="list-style: inherit;">
                                    <li>产品未达到预期？</li>
                                    <li>发布遇到问题？</li>
                                    <li>对项目有异议？？</li>
                                    <li>可以授权我的员工来发布任务吗？</li>
                                    <li>变更申请提交后多久审核？</li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="right-part">
                    <div class="tabs">
                        <div class="tab active-tab">大需求变更</div>
                        <div class="tab">小细节修改</div>
                        <div class="active-line"></div>
                    </div>

                    <form class="form-group form-horizontal form-demand" role="form" id="demandFrom">
                        <div>
                            <p><span class="required">*</span>&nbsp;&nbsp;需求名称&nbsp;&nbsp;<span style="font-size:12px;color:#9B9B9B;">能够简单描述你的需求的短语，少于15字</span></p>
                            <input type="text" class="form-control" placeholder="需求名称" style="width:400px;">
                        </div>

                        <div style="margin-top:30px;">
                            <p><span class="required">*</span>&nbsp;&nbsp;变更原因</p>
                            <div style="position: relative;width:400px;">
                                <input type="text" class="form-control" placeholder="请选择" style="width:400px;">
                                <div class="form-triangle">
                                    <div class="form-trigger"></div>
                                </div>
                            <#--<div class="budget">-->
                            <#--<ul>-->
                            <#--<li>5万以下</li>-->
                            <#--<li>5-10万</li>-->
                            <#--<li>10-20万</li>-->
                            <#--<li>20-30万</li>-->
                            <#--<li>30-40万</li>-->
                            <#--<li>40万以上</li>-->
                            <#--</ul>-->
                            <#--</div>-->
                            </div>
                        </div>

                        <div style="margin-top:30px;">
                            <p><span class="required">*</span>&nbsp;&nbsp;需求描述</p>
                            <textarea class="form-control" rows="7" placeholder="需求名称" style="width:500px;"></textarea>
                        </div>

                        <button type="button" class="upload-btn">
                            <img src="/res/images/upload_icon.png" width="20" height="20">
                            <span style="margin-left:10px;">上传附件文档</span>
                        </button>

                        <button type="button" class="ok-btn">提交变更申请</button>
                    </form>
                </div>
            </div>

        </div>
    </div>


    </@block>
    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/index.js')}"></script>
    </@block>
</@extend>