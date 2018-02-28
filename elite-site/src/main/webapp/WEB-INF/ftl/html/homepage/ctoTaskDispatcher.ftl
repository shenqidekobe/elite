<@extend name="homepageLayout">
    <@block name="cs">
    <style type="text/css">
        .release-list{
            width:100%;
            height:178px;
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
            width:50px;
            height:20px;
            cursor: pointer;
            display: inline-block;
            /*vertical-align: text-bottom;*/
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
            height:136px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        .main-info{
            float:left;
            width:54%;
            height:136px;
            border-right: 1px solid #E6E6E6;
        }

        .project-logo{
            width:95px;
            height:95px;
            line-height: 105px;
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
            margin-bottom: 10px;
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
            height:136px;
            border-right: 1px solid #E6E6E6;
            font-size: 12px;
            color:#9B9B9B;
            text-align: center;
        }

        .opt{
            float:left;
            width:24%;
            height:136px;
            background-color: #F5FBFC;
        }

        .current-status{
            font-size: 14px;
        }

        .opt-btn{
            width:100px;
            height:26px;
            background-color: #FEA600;
            border:1px solid #FEA600;
            border-radius: 14px;
            color:white;
            font-size: 14px;
        }

        .recommend-btn{
            width:100px;
            height:26px;
            background-color: transparent;
            border:1px solid #FEA600;
            border-radius: 14px;
            color:#FEA600;
            font-size: 14px;
        }

        .sign{
            font-size: 12px;
            color:#9B9B9B;
            cursor: pointer;
        }

        .view-times{
            font-size: 12px;
            color:#9B9B9B;
            text-align: left;
            padding-left:26px;
        }
        .head-logo{
            width: 30px;
            height: 30px;
            background:url("/res/images/test.jpg") no-repeat;
            background-size: 30px 30px;
            border: 1px solid #979797;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;

            position: absolute;
            left:64px;
            top:20px;
        }

        .release-btn{
            width:300px;
            height:50px;
            border:1px solid #95D6E2;
            border-radius: 26px;
            background-color: transparent;
            color:#95D6E2;
        }

        .close-icon{
            width:24px;
            height:24px;
            float:right;
            margin-top:6px;
            margin-right:6px;
            cursor: pointer;
        }

        .required{
            color:#FEA600!important;
        }
        .error-tip{
            text-align: center;
            height:24px;
            color:red;
            clear: both;
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
            border-color:#000 transparent transparent;
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

        .modal-select{
            position: relative;
            width:220px;
            height:34px;
            float:left;
            padding-left:18px;
        }

        .date-icon{
            width: 20px;
            height: 20px;
            position: absolute;
            top: 7px;
            right: 16px;
        }

        .date-link{
            float:left;
            line-height: 34px;
            margin-left:36px;
        }

        .money-unit{
            position: absolute;
            right:0;
            top:6px;
        }

        .logo-box{
            position: absolute;
            top:34px;
            right:80px;
        }

        .word-logo{
            width:80px;
            height:80px;
            line-height: 80px;
            text-align: center;
            background-color: #F29D70;
            color:white;
            font-size: 36px;
            cursor: pointer;
        }

        .upload-tip{
            text-align: center;
            font-size: 12px;
            color:#9B9B9B;
            margin-top:10px;
        }

        .upload-btn{
            float:left;
            width: 140px;
            height: 40px;
            border: none;
            border-radius: 6px;
            background-color: #F0EEEC;
            color: #9B9B9B;
            margin-left:20px;
            margin-bottom: 10px;
        }

        .modal-opt{
            padding-left:20px;
            margin-top:10px;
        }

        .modal-opt-btn{
            width:180px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 20px;
            font-size: 18px;
            color:white;
            margin-right:30px;
        }

        .cancel-btn{
            width:120px;
            height:40px;
            border:1px solid #CCCCCC;
            border-radius: 20px;
            background-color: transparent;
            font-size: 18px;
            color:#CCCCCC;
        }

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
        .active-line{
            width:213px;
            border-bottom: 2px solid #FEA600;
            position: absolute;
            left:0;
            bottom: 0;
        }

        .tab{
            width:32%;
            float:left;
        }

        .team-box{
            float:left;
            width:330px;
            height:425px;
            border:1px solid #CCCCCC;
            border-radius: 6px;
            background-color: white;
            padding:20px 10px 20px 20px;
            font-size: 12px;
        }

        .trigger-icon{
            width:7px;
            height:12px;
            vertical-align: text-top;
            cursor: pointer;
        }

        .trigger-icon-inverse{
            -webkit-transform: rotate(90deg);
            -moz-transform: rotate(90deg);
            -ms-transform: rotate(90deg);
            transform: rotate(90deg);
            animation-fill-mode: forwards;
        }

        .person-box{
            width:294px;
            height:47px;
            /*background-color: #EBEBEB;*/
            border-radius: 6px;
            border:none;
            float:right;
        }

        .modal-head{
            width: 30px;
            height: 30px;
            background:url("/res/images/test.jpg") no-repeat;
            background-size: 30px 30px;
            border: 1px solid #979797;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            margin:9px;
            float:left;
        }

        .name-title{
            float:left;
            width:90px;
            height:34px;
            margin-top:6px;
        }
        .phone-task{
            float:left;
            width:114px;
            height:34px;
            margin-top:6px;
        }

        .opt-person{
            width:16px;
            height:16px;
            margin:16px 12px;
            cursor: pointer;
            display: none;
        }

        .person-box:hover{
            background-color: #EBEBEB;
        }

        .person-box:hover .opt-person{
            display: block;
        }

        .search-box{
            width:100%;
            background-color: #F5F5F5;
            border-top-left-radius: 6px;
            border-top-right-radius: 6px;
            /*padding:0 20px;*/
            color:#9B9B9B;
        }

        .phone-email-input{
            width:80%;
            border:none;
            background-color: #F5F5F5;
            height:40px;
            display: inline-block;
            padding:20px;
        }

        .search-person{
            width:21px;
            height:20px;
            display: inline-block;
            cursor: pointer;
        }

        .select-person{
            font-size: 12px;
            color:#4A4A4A;
            margin-top:10px;
        }

        .select-person-box{
            width:294px;
            height:47px;
            background-color: #EBEBEB;
            border-radius: 6px;
            border:none;
            margin-bottom: 10px;
            position: relative;
        }

        .delete-cross{
            width:16px;
            height:16px;
            position: absolute;
            top:15px;
            right:18px;
        }

        .send-to-friends{
            padding-top:40px;
            padding-left:70px;
            font-size: 14px;
            color:#4A4A4A;
            width:100%;
            height:330px;
        }



    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">

    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">任务编号：2016030200008</span>
                    <div class="pull-right">
                        <img src="/res/images/shutdown_icon.png" class="opt-icon">
                        &nbsp;<span class="sign">关闭任务</span>
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
                        <div class="project-name">堆糖网</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">已推送至<span style="color:#2CB7C9;">任务大厅</span></p>
                    <p class="view-times">
                        <img src="/res/images/eye_icon.png" width="20" height="12">
                        &nbsp;1333
                    </p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
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
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo">堆</div>
                    <div class="project-info">
                        <div class="project-name">堆糖网</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;position: relative;">
                    <div class="head-logo"></div>
                    <p style="padding-top:40px;"><a href="#" target="_blank">查看详情</a></p>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p>
                        <img src="/res/images/clock_icon.png" width="16" height="16">
                    &nbsp;<span style="font-size: 12px;">距任务交付：&nbsp;<span style="color:#FEA600;">3天23时</span></span>
                    </p>
                    <button type="button" class="opt-btn">提醒</button>
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
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo">堆</div>
                    <div class="project-info">
                        <div class="project-name">堆糖网</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;position: relative;">
                    <div class="head-logo" style="left:53px;"></div>
                    <div class="head-logo" style="left:78px;"></div>
                    <p style="padding-top:40px;"><a href="#" target="_blank">查看详情</a></p>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <p style="font-size: 14px;">已完成</p>
                    <button type="button" class="opt-btn">评价</button>
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
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo">堆</div>
                    <div class="project-info">
                        <div class="project-name">堆糖网</div>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">产品经理</span></div>
                        <div><span class="index">报酬:</span>&nbsp;<span class="value">50000.00</span></div>
                        <div><span class="index">交付日期:</span>&nbsp;<span class="value">2016-11-02 (45个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;position: relative;">
                    <div class="head-logo" style="left:40px;"></div>
                    <div class="head-logo" style="left:64px;"></div>
                    <div class="head-logo" style="left:90px;"></div>
                    <div style="padding-top:40px;">
                        <img src="/res/images/group_icon.png" width="18" height="16">
                        &nbsp;3
                        <img src="/res/images/eye_icon.png" width="20" height="12" style="margin-left:14px;">
                        &nbsp;322
                    </div>
                    <p style="margin-top:10px;"><a href="#" target="_blank">查看详情</a></p>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <button type="button" class="recommend-btn">去确定人选</button>
                    <button type="button" class="opt-btn" style="margin-top:10px;">推荐给他人</button>
                </div>
            </div>

        </div>
    </div>


    <div style="text-align: center;margin-top:40px;">
        <button type="button" class="release-btn">
            <img src="/res/images/edit_icon2.png" width="20" height="20">
            &nbsp;发布新任务
        </button>
    </div>


    <#--模态框-->
    <div class="container">
        <div class="row">
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="releaseTaskDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:760px; height:500px;margin:0 auto;background-color: #F5FBFC;overflow: auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                            <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                        <#--正文-->

                            <form class="form-group form-horizontal" role="form" id="releaseTaskForm" style="padding:30px;position: relative;">
                                <div class="logo-box">
                                    <div class="word-logo">堆</div>
                                    <div class="upload-tip">
                                        <img src="/res/images/upload_icon.png" width="16" height="16">
                                        &nbsp;上传logo
                                    </div>
                                </div>
                                <div style="margin-bottom:20px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;任务名称</p>
                                    <div style="padding-left:18px;">
                                        <input type="text" class="form-control" placeholder="任务名称" style="width:400px;">
                                    </div>
                                </div>

                                <div style="margin-bottom:20px;height:67px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;所需精英角色<span class="required" style="margin-left:160px;">*</span>&nbsp;&nbsp;任务类型</p>
                                    <div class="modal-select">
                                        <input type="text" class="form-control" placeholder="请选择" style="width:220px;">
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

                                    <div style="margin-left:50px;" class="modal-select">
                                        <input type="text" class="form-control" placeholder="请选择" style="width:220px;">
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

                                <div style="margin-bottom:20px;height:67px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;任务周期</p>
                                    <div class="modal-select">
                                        <input type="text" class="form-control" placeholder="任务开始时间" style="width:220px;">
                                        <img src="/res/images/date_icon.png" class="date-icon">
                                    </div>
                                    <span class="date-link">-</span>
                                    <div style="margin-left:4px;" class="modal-select">
                                        <input type="text" class="form-control" placeholder="任务交付时间" style="width:220px;">
                                        <img src="/res/images/date_icon.png" class="date-icon">
                                    </div>
                                </div>

                                <div style="margin-bottom:20px;height:67px;padding-left:20px;">
                                    <p>任务招募截止日期<span class="required" style="margin-left:130px;">*</span>&nbsp;&nbsp;任务报酬</p>
                                    <div class="modal-select" style="padding-left:0;">
                                        <input type="text" class="form-control" placeholder="选择日期" style="width:220px;">
                                        <img src="/res/images/date_icon.png" class="date-icon">
                                    </div>

                                    <div style="margin-left:30px;" class="modal-select">
                                        <input type="text" class="form-control" placeholder="输入金额" style="width:220px;">
                                        <span class="money-unit">人民币</span>
                                    </div>
                                </div>

                                <div style="margin-bottom:20px;height:144px;">
                                    <p><span class="required">*</span>&nbsp;&nbsp;要求</p>
                                    <div style="padding-left:18px;">
                                       <textarea class="form-control" rows="5" style="width:490px;float:left;" placeholder="提一些要求"></textarea>
                                        <button type="button" class="upload-btn">
                                            <img src="/res/images/upload_icon.png" width="16" height="16">
                                            &nbsp;上传附件
                                        </button>
                                        <span style="font-size: 12px;color:#9B9B9B;margin-left:45px;">其他说明性文档</span>
                                    </div>
                                </div>

                                <div class="error-tip">请输入姓名</div>

                                <div class="modal-opt">
                                    <button type="button" class="modal-opt-btn" id="assignBtn">将任务指派给</button>
                                    <button type="button" class="modal-opt-btn" id="publicBtn">发布到任务大厅</button>
                                    <button type="button" class="cancel-btn" data-dismiss="modal" aria-label="Close">取消</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <div class="container">
        <div class="row">
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="assignDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:760px; height:500px;margin:0 auto;background-color: #F5FBFC;overflow: auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                            <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                        <#--正文-->

                            <div style="position: relative;padding-top:30px;">
                                <div class="tabs">
                                    <div class="tab active-tab">推荐给你的</div>
                                    <div class="tab">认领中的任务</div>
                                    <div class="tab">已认领的任务</div>
                                </div>
                                <div class="active-line"></div>
                            </div>

                            <div style="padding:30px;">
                                <div class="team-box" style="float:left;">
                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon">
                                        &nbsp;&nbsp;最近分享（3）
                                    </div>

                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon trigger-icon-inverse">
                                        &nbsp;&nbsp;团队里的人（12）
                                    </div>

                                    <div>
                                        <div class="person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/select_tick.png" class="opt-person">
                                        </div>

                                        <div class="person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/select_tick.png" class="opt-person">
                                        </div>
                                    </div>

                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon">
                                        &nbsp;&nbsp;我关注的精英（58）
                                    </div>

                                    <div style="line-height: 30px;">
                                        <img src="/res/images/trigger_close.png" class="trigger-icon">
                                        &nbsp;&nbsp;关注我的精英（96）
                                    </div>

                                </div>
                                <div class="team-box" style="float:right;padding:0;">
                                    <div class="search-box">
                                        <input type="text" class="form-control phone-email-input" placeholder="输入手机号或邮箱添加精英">
                                        <img src="/res/images/search_green_icon.png" class="search-person">
                                    </div>

                                    <div style="padding:10px 14px;">
                                        <p class="select-person">已选择2个精英</p>
                                        <div class="select-person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/delete_cross.png" class="delete-cross">
                                        </div>

                                        <div class="select-person-box">
                                            <div class="modal-head"></div>
                                            <div class="name-title">
                                                <div>Janet Woods</div>
                                                <div>产品经理</div>
                                            </div>
                                            <div class="phone-task">
                                                <div>15789209400</div>
                                                <div>进行中的任务数：<span style="color:#2CB7C9;">3</span></div>
                                            </div>
                                            <img src="/res/images/delete_cross.png" class="delete-cross">
                                        </div>
                                    </div>

                                </div>
                            </div>

                            <#--<div class="send-to-friends">-->
                                <#--<div>-->
                                    <#--<p>输入他（她）的<span style="color:#30C3D3;">手机号</span></p>-->
                                    <#--<input type="text" class="form-control" placeholder="他（她）的手机号" style="width:400px;display: inline-block;">-->
                                    <#--&lt;#&ndash;<img src="/res/images/add_icon2.png" width="25" height="25" style="margin-left:20px;">&ndash;&gt;-->
                                <#--</div>-->

                                <#--<div style="margin-top:20px;">-->
                                    <#--<p>输入指派对象的<span style="color:#30C3D3;">邮箱</span></p>-->
                                    <#--<input type="text" class="form-control" placeholder="他（她）的邮箱" style="width:400px;">-->
                                <#--</div>-->
                            <#--</div>-->

                            <div style="padding-left:230px;">
                                <button type="button" class="modal-opt-btn" style="width:290px;margin:30px 0;">指派给他，同步推送到任务大厅</button>
                                <button type="button" class="cancel-btn" style="float:right;margin:30px 40px 30px 0;" data-dismiss="modal" aria-label="Close">取消</button>
                            </div>


                            <#--<div style="padding-left:230px;margin-top:150px;">-->
                                <#--<button type="button" class="modal-opt-btn" style="width:290px;margin:30px 0;">指派给他，同步推送到任务大厅</button>-->
                                <#--<button type="button" class="cancel-btn" style="float:right;margin:30px 40px 30px 0;" data-dismiss="modal" aria-label="Close">取消</button>-->
                            <#--</div>-->

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>