<@extend name="homepageLayout">
    <@block name="cs">
    <style type="text/css">

        .right-opt{
            margin-bottom: 60px;
        }
        .process{
            width:100%;
            height:100px;
            position: relative;
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

        .theme-lab{
            font-size: 20px;
            color:#4A4A4A;
            display: inline-block;
            padding-left:100px;
        }

        .brooch-icon{
            width:18px;
            height:20px;
            display: inline-block;
            margin-left:20px;
        }

        .document-name{
            font-size: 14px;
            color:#4A4A4A;
        }

        .project-index{
            width:100%;
            height:202px;
            background-color: #F5F5F5;
            box-shadow: 2px 2px 4px 0 rgba(0,0,0,0.30);
            border-radius: 6px 6px 0 0;
            margin-top:14px;
        }

        .project-logo{
            width:60px;
            height:60px;
            line-height: 60px;
            text-align: center;
            background-color: #EE706B;
            font-size: 24px;
            color:white;
            display: inline-block;
        }

        .project-status{
            float:right;
            display: inline-block;
            color:#2CB7C9;
        }

        .time-code-status{
            display: inline-block;
            font-size: 12px;
            color:#9B9B9B;
            vertical-align: top;
            margin-left:14px;
            position: relative;
        }

        .vertical-line{
            position: absolute;
            top:0;
            left:188px;
            height:20px;
            border-right: 1px solid #E6E6E6;
        }

        .vertical-line2{
            position: absolute;
            bottom:0;
            left:100px;
            height:20px;
            border-right: 1px solid #E6E6E6;
        }

        .main-index{
            position: absolute;
            top:50px;
            left:98px;
        }

        .location-icon{
            width:18px;
            height:24px;
            vertical-align: sub;
            margin-left:40px;

        }

        .edit-icon{
            width:20px;
            height:20px;
            vertical-align: sub;
            margin-left:60px;
            cursor: pointer;
        }

        .index{
            color:#9B9B9B;
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

        .vertical-line3{
            position: absolute;
            top:70px;
            left:410px;
            height:116px;
            border-right: 1px solid #E6E6E6;
        }

        .deposit{
            font-size: 14px;
            position: absolute;
            right:190px;
            top:100px;
        }

        .opt-btn{
            width:90px;
            height:26px;
            background-color: #FEA600;
            border:none;
            border-radius: 13px;
            font-size: 14px;
            color:white;

            position: absolute;
            right:30px;
            top:90px;
        }
        .project-intro{
            margin-top:5px;
            padding:20px 30px;
            background-color: #F5FBFC;
            position: relative;
        }

        .paper-foot{
            position: absolute;
            bottom: 0;
            right:0;
            z-index: 100;
            width:55px;
            height:48px;
        }

    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
        <div class="process">
            <div class="process-circle active-process">1</div>
            <div class="process-circle" style="left:230px;">2</div>
            <div class="process-circle" style="left:440px;">3</div>
            <div class="process-circle" style="left:650px;">
                <img src="/res/images/tick.png" width="24" height="auto">
            </div>
            <p class="process-title active-title">待审核</p>
            <p class="process-title" style="left:224px;">审核中</p>
            <p class="process-title" style="left:410px;">审核通过,待立项</p>
            <p class="process-title" style="left:636px;">立项成功</p>

            <div class="process-line finish-line"></div>
            <div class="process-line" style="left:280px;"></div>
            <div class="process-line" style="left:490px;"></div>

            <p class="process-tip">项目经理对接</p>
            <p class="process-tip" style="left:310px;">提交项目意向金</p>
            <p class="process-tip" style="left:524px;">核实立项确认书</p>
        </div>

    <#--项目详细-->
    <div style="margin-top:40px;">
        <div style="text-align: center;">
            <div class="theme-lab"><strong>项目描述</strong></div>
            <img src="/res/images/brooch_icon.png" class="brooch-icon">
            &nbsp;<span class="document-name">堆糖网项目参考文档</span>
        </div>
        <div class="project-index">
            <div style="padding:20px;position: relative;">
                <div class="project-logo">堆</div>
                <div class="time-code-status">
                    <span>创建时间：2016-03-02  9：30</span>
                    <span class="vertical-line"></span>
                    <span style="margin-left:30px;">项目编号：2016030200008</span>
                </div>
                <span class="project-status">待审核</span>

                <div class="main-index">
                    <div>
                        <span>堆糖网</span>
                        <img src="/res/images/location_icon.png" class="location-icon">
                        &nbsp;<span style="font-size: 14px;color:#4A4A4A;">杭州</span>
                        <img src="/res/images/edit_icon.png" class="edit-icon">
                    </div>
                    <div style="font-size: 12px;margin-top:14px;position: relative;">
                        <p><span class="index">开发类型：</span>PC网站+移动端App</p>
                        <p><span class="index">预算：</span>20-40万</p>
                        <p><span class="index">期望交付日期：</span>2016.4.20-2016.6.1 &nbsp;&nbsp;共45个工作日</p>
                        <div class="class-label">电商</div><div class="class-label">电商</div>
                        <div class="vertical-line2" style="bottom: 0;left:100px;"></div>
                        <div class="class-label" style="margin-left:20px;">电商</div>
                    </div>

                </div>

                <div class="vertical-line3"></div>
                <div class="deposit"><span style="color:#9B9B9B;">意向金:</span>&nbsp;20,000</div>
                <div class="vertical-line3" style="left:570px;"></div>
                <button type="button" class="opt-btn">提交意向金</button>

            </div>
        </div>

        <div class="project-intro">
        	<p>参考项目：<a href="www.yunyinghui.com"></a></p>
			<p>联系人:<span>Tom</span><span>15799909020</span></p>

            <pre style="border:none;background-color: transparent;padding:0;">
            
一、项目描述
一款阅读类应用开发，需要产品经理、设计、iOS 开发、安卓开发、Web 开发、后台开发、Web测试和APP测试

二、项目需求
用户端主要功能描述：
注册登录
手机或邮箱注册登录，第三方注册登录
用户中心
个人中心：评论、分享、点赞、个人主页等
轨迹：书评、关注的人等
书单：已购、已读、收藏
账户：充值、记录、礼品卡、通知提醒
主页
以图片、文字、书评为主的社交，全局搜索等
阅读器
基础阅读、书签目录、笔记目录、阅读进度、书签增删、笔记增删、纠错和反馈
Web端功能描述：
主页、APP下载页面、兑换码页面、帮助中心等
具体需求，后期有文档可以沟通
有阅读器或类似 APP 开发经验优先
仅限上海地区开发团队，团队规模10人左右，现开放报名，年后再具体沟通

三、项目规划
时间安排：
110天
备注：产品原型20天，设计及开发一共90天
交付物：
符合需求的成果交付
产品需求文档、原型设计文档、UI高保真图、切图、注释图
源代码，需要随时把最新的代码 Push 到指定的 Coding 私有项目中
数据库文档、架构文档
APP 上线到对应应用市场
金额：270000
具体阶段划分，以后期沟通为主（付款按阶段划分）


            </pre>
            <img src="/res/images/paper_foot.png" class="paper-foot">
        </div>
    </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>