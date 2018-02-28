<@extend name="homepageLayout">
    <@block name="cs">
    <style type="text/css">
        .tabs{
            width:100%;
            height:40px;
            text-align: center;
            font-size: 18px;
            border-bottom: 1px solid #B3B3B3;
        }
        .active-tab{
            color:#FEA600;
        }
        .tab-number{
            font-size: 14px;
            color:#FEA600;
        }

        .active-line{
            width:213px;
            border-bottom: 2px solid #FEA600;
            position: absolute;
            left:0;
            bottom: 0;
        }

        .tab{
            cursor: pointer;
        }

        .release-list{
            width:100%;
            height:188px;
            border:1px solid #E6E6E6;
            border-radius: 5px;
            margin-top:20px;
        }

        .part1{
            width:100%;
            height:40px;
            line-height: 40px;
            background-color: #F2F2F2;
            border-bottom: 1px solid #E6E6E6;
            text-align: center;
            font-size: 14px;
            color:#9B9B9B;
            border-top-left-radius: 5px;
            border-top-right-radius: 5px;
        }

        .opt-icon{
            width:16px;
            height:16px;
            cursor: pointer;
            display: inline-block;
            /*vertical-align: text-bottom;*/
            vertical-align: sub;
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
            height:146px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        .main-info{
            float:left;
            width:54%;
            height:146px;
            border-right: 1px solid #E6E6E6;
        }

        .project-logo{
            width:105px;
            height:105px;
            line-height: 105px;
            text-align: center;
            background-color: #F29D70;
            color:white;
            font-size: 58px;
            float:left;
        }

        .project-info{
            margin-left:130px;
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
            height:146px;
            border-right: 1px solid #E6E6E6;
            font-size: 12px;
            color:#9B9B9B;
            text-align: center;
        }

        .opt{
            float:left;
            width:24%;
            height:146px;
            background-color: #F5FBFC;
        }

        .current-status{
            font-size: 14px;
            color:#2CB7C9;
        }

        .opt-btn{
            width:90px;
            height:26px;
            background-color: #FEA600;
            border:1px solid #FEA600;
            border-radius: 14px;
            color:white;
            font-size: 14px;
        }

        .recommend-btn{
            width:90px;
            height:26px;
            background-color: transparent;
            border:1px solid #FEA600;
            border-radius: 14px;
            color:#FEA600;
            font-size: 14px;
            margin-top:10px;
        }

        .cancel-btn{
            width:90px;
            height:26px;
            background-color: #CCCCCC;
            border:1px solid #CCCCCC;
            border-radius: 14px;
            color:white;
            font-size: 14px;
        }

        .sign{
            font-size: 12px;
            color:#9B9B9B;
            cursor: pointer;
        }

        .result{
            padding:20px 20px 0;
            text-align: center;
            font-size: 12px;
            color:#4A4A4A;
        }

    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div style="position: relative;">
        <div class="tabs">
            <div class="col-xs-4 col-md-4 active-tab">
                <div class="tab">推荐给你的&nbsp;<span class="tab-number">2</span></div>
            </div>
            <div class="col-xs-4 col-md-4">
                <div class="tab">认领中的任务&nbsp;<span class="tab-number">2</span></div>
            </div>
            <div class="col-xs-4 col-md-4">
                <div class="tab">已认领的任务&nbsp;<span class="tab-number">2</span></div>
            </div>
        </div>
        <div class="active-line"></div>
    </div>

    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：2016030200008</span>
                    <div class="pull-right">
                        <img src="/res/images/add_icon3.png" class="opt-icon">
                        &nbsp;<span class="sign">关注</span>
                        <img src="/res/images/person_icon.png" class="opt-icon" style="margin-left:20px;">
                        &nbsp;<span class="sign">暂不参与</span>
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo">堆</div>
                    <div class="project-info">
                        <div class="project-name">堆糖网Web原型设计</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">招募中</p>
                    <p style="font-size: 12px;color:#9B9B9B;">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <button type="button" class="opt-btn">申请加入</button>
                    <button type="button" class="recommend-btn">推荐给他人</button>
                </div>
            </div>
        </div>
    </div>

    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：2016030200008</span>
                    <div class="pull-right">
                        <img src="/res/images/add_icon3.png" class="opt-icon">
                        &nbsp;<span class="sign">关注</span>
                        <img src="/res/images/person_icon.png" class="opt-icon" style="margin-left:20px;">
                        &nbsp;<span class="sign">暂不参与</span>
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo">堆</div>
                    <div class="project-info">
                        <div class="project-name">堆糖网Web原型设计</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">招募中</p>
                    <p style="font-size: 12px;color:#9B9B9B;">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p style="font-size: 14px;">已申请</p>
                    <button type="button" class="cancel-btn">取消申请</button>
                    <button type="button" class="recommend-btn">推荐给他人</button>
                </div>
            </div>
        </div>
    </div>

    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：2016030200008</span>
                    <div class="pull-right">
                        <img src="/res/images/add_icon3.png" class="opt-icon">
                        &nbsp;<span class="sign">关注</span>
                        <img src="/res/images/person_icon.png" class="opt-icon" style="margin-left:20px;">
                        &nbsp;<span class="sign">暂不参与</span>
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo">堆</div>
                    <div class="project-info">
                        <div class="project-name">堆糖网Web原型设计</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status" style="color:#D0021B;">招募结束</p>
                    <p style="font-size: 12px;color:#9B9B9B;">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div class="result">
                    <img src="/res/images/sad_face_icon.png" width="16" height="16">
                    &nbsp;<span>您下手太慢啦,下次快点哦!</span>
                </div>
            </div>
        </div>
    </div>

    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：2016030200008</span>
                    <div class="pull-right">
                        <img src="/res/images/add_icon3.png" class="opt-icon">
                        &nbsp;<span class="sign">关注</span>
                        <img src="/res/images/person_icon.png" class="opt-icon" style="margin-left:20px;">
                        &nbsp;<span class="sign">暂不参与</span>
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo">堆</div>
                    <div class="project-info">
                        <div class="project-name">堆糖网Web原型设计</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status" style="color:#D0021B;">招募结束</p>
                    <p style="font-size: 12px;color:#9B9B9B;">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div class="result">
                    <img src="/res/images/sad_face_icon.png" width="16" height="16">
                    &nbsp;<span>您未认领到该任务，</span>
                    <p>再接再厉!</p>
                    <button type="button" class="opt-btn">查看原因</button>
                </div>
            </div>
        </div>
    </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>