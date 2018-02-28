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
            width:20px;
            height:20px;
            cursor: pointer;
            display: inline-block;
            /*vertical-align: text-bottom;*/
            margin-left:10px;
        }

        .show-icon{
            width:16px;
            height:16px;
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
            height:146px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 5px;
            border-bottom-right-radius: 5px;
        }

        .main-info{
            float:left;
            width:50%;
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
            float:left;
            margin-left:20px;
        }
        .project-name{
            display: inline-block;
            margin-right:10px;
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

        .index{
            font-size: 12px;
            color:#9B9B9B;
        }

        .value{
            font-size: 12px;
        }

        .status{
            float:left;
            width:15%;
            height:146px;
            border-right: 1px solid #E6E6E6;
            font-size: 12px;
            color:#9B9B9B;
            /*text-align: center;*/
        }

        .opt{
            float:left;
            width:20%;
            height:146px;
            background-color: #F5FBFC;
        }

        .current-status{
            font-size: 14px;
            color:#2CB7C9;
        }

        .complete-btn{
            width:90px;
            height:26px;
            background-color: #FEA600;
            border:none;
            border-radius: 14px;
            color:white;
            font-size: 14px;
        }

        .check-btn{
            width:50px;
            height:20px;
            border:1px solid #FEA600;
            background-color: white;
            border-radius: 10px;
            color:#FEA600;
            font-size: 12px;
            margin-left:20px;
        }

        .message-tip{
            width: 14px;
            height: 14px;
            line-height: 14px;
            text-align: center;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            background-color: #FEA600;
            color:white;
            font-size: 12px;

            position: absolute;
            top:0;
            right:62px;
        }

        .evaluate-btn{
            width:90px;
            height:26px;
            border:1px solid #FEA600;
            border-radius: 13px;
            color:#FEA600;
            background-color: white;
        }

    </style>
    </@block>
<#--右边的操作区-->
<@block name="rightContent">
        <div style="position: relative;">
            <div class="tabs">
                <div class="col-xs-4 col-md-4 active-tab">
                    <div class="tab">待审核&nbsp;<span class="tab-number">2</span></div>
                </div>
                <div class="col-xs-4 col-md-4">
                    <div class="tab">审核中&nbsp;<span class="tab-number">2</span></div>
                </div>
                <div class="col-xs-4 col-md-4">
                    <div class="tab">已审核&nbsp;<span class="tab-number">2</span></div>
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
                        <span class="pull-left">项目编号：2016030200008</span>
                        <div class="pull-right">
                            <img src="/res/images/delete_icon.png" class="opt-icon">
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
                           <div>
                               <span class="project-name">堆糖网</span>
                               <div class="class-label">电商</div>
                               <div class="class-label">社交</div>
                           </div>
                            <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                            <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                            <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                            <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                        </div>

                    </div>
                </div>
                <div class="status">
                    <div style="padding:20px 10px;">
                        <p>意向金：0</p>
                        <p>股权托管：有/无</p>
                    </div>
                </div>
                <div class="status">
                    <div style="padding-top:20px;text-align: center;">
                        <p class="current-status">待完善资料</p>
                        <a href="#" target="_blank">查看详情</a>
                    </div>
                </div>
                <div class="opt">
                    <div style="padding-top:20px;text-align: center;">
                        <button type="button" class="complete-btn">去完善</button>
                        <p style="font-size: 12px;margin-top:10px;">提交意向金</p>
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
                <span class="pull-left">项目编号：2016030200008</span>
                <div class="pull-right">
                    <img src="/res/images/edit_icon.png" class="opt-icon">
                    <img src="/res/images/delete_icon.png" class="opt-icon">
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
                    <div>
                        <span class="project-name">堆糖网</span>
                        <div class="class-label">电商</div>
                        <div class="class-label">社交</div>
                    </div>
                    <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                    <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                    <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 10px;">
                <p>意向金：0</p>
                <p>股权托管：有/无</p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">待完善资料</p>
                <a href="#" target="_blank">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding-top:20px;text-align: center;">
                <p style="font-size: 12px;">项目经理对接中</p>
                <button type="button" class="complete-btn">提交意向金</button>
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
                <span class="pull-left">项目编号：2016030200008</span>
                <div class="pull-right">
                    <img src="/res/images/edit_icon.png" class="opt-icon">
                    <img src="/res/images/delete_icon.png" class="opt-icon">
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
                    <div>
                        <span class="project-name">堆糖网</span>
                        <#--<div class="class-label">电商</div>-->
                        <#--<div class="class-label">社交</div>-->
                    </div>
                    <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                    <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                    <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 10px;">
                <p>意向金：0</p>
                <p>股权托管：有/无</p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">待完善资料</p>
                <a href="#" target="_blank">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding-top:60px;text-align: center;">
                <button type="button" class="complete-btn">查看原因</button>
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
                <span class="pull-left">项目编号：2016030200008</span>
                <div class="pull-right">
                    <img src="/res/images/edit_icon.png" class="opt-icon">
                    <img src="/res/images/delete_icon.png" class="opt-icon">
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
                    <div>
                        <span class="project-name">堆糖网</span>
                    <#--<div class="class-label">电商</div>-->
                    <#--<div class="class-label">社交</div>-->
                    </div>
                    <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                    <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                    <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 10px;">
                <p>意向金：0</p>
                <p>股权托管：有/无</p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">待完善资料</p>
                <a href="#" target="_blank">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding-top:20px;text-align: center;">
                <div>
                    <img src="/res/images/write_icon.png" class="show-icon">&nbsp;&nbsp;<span style="font-size: 12px;">需求修改</span>
                </div>
                <div style="margin-top:10px;position: relative;">
                    <span style="font-size: 12px;color:#9B9B9B;">收到新文件</span><button type="button" class="check-btn">去查看</button>
                    <div class="message-tip">3</div>
                </div>
                <button type="button" class="complete-btn" style="margin-top:10px;">验收</button>
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
                <span class="pull-left">项目编号：2016030200008</span>
                <div class="pull-right">
                    <img src="/res/images/edit_icon.png" class="opt-icon">
                    <img src="/res/images/delete_icon.png" class="opt-icon">
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
                    <div>
                        <span class="project-name">堆糖网</span>
                    <#--<div class="class-label">电商</div>-->
                    <#--<div class="class-label">社交</div>-->
                    </div>
                    <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                    <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                    <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 10px;">
                <p>意向金：0</p>
                <p>股权托管：有/无</p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">待完善资料</p>
                <a href="#" target="_blank">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding-top:20px;text-align: center;">
                <div>
                    <img src="/res/images/clock_icon.png" class="show-icon">&nbsp;&nbsp;<span style="font-size: 12px;">已验收4天22时</span>
                </div>
                <button type="button" class="complete-btn" style="margin-top:10px;font-size: 12px;width:126px;">去托管下一阶段费用</button>
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
                <span class="pull-left">项目编号：2016030200008</span>
                <div class="pull-right">
                    <img src="/res/images/edit_icon.png" class="opt-icon">
                    <img src="/res/images/delete_icon.png" class="opt-icon">
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
                    <div>
                        <span class="project-name">堆糖网</span>
                    <#--<div class="class-label">电商</div>-->
                    <#--<div class="class-label">社交</div>-->
                    </div>
                    <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                    <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                    <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 10px;">
                <p>意向金：0</p>
                <p>股权托管：有/无</p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">待完善资料</p>
                <a href="#" target="_blank">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding-top:20px;text-align: center;">
                <div>
                    <button type="button" class="evaluate-btn">评价</button>
                </div>
                <button type="button" class="complete-btn" style="margin-top:10px;font-size: 12px;width:126px;">去托管下一阶段费用</button>
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
                <span class="pull-left">项目编号：2016030200008</span>
                <div class="pull-right">
                    <img src="/res/images/edit_icon.png" class="opt-icon">
                    <img src="/res/images/delete_icon.png" class="opt-icon">
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
                    <div>
                        <span class="project-name">堆糖网</span>
                    <#--<div class="class-label">电商</div>-->
                    <#--<div class="class-label">社交</div>-->
                    </div>
                    <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                    <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                    <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 10px;">
                <p>意向金：0</p>
                <p>股权托管：有/无</p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">待完善资料</p>
                <a href="#" target="_blank">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding-top:20px;text-align: center;">
                <div>
                    <img src="/res/images/write_icon.png" class="show-icon">&nbsp;&nbsp;<span style="font-size: 12px;">需求修改</span>
                </div>
                <div style="margin-top:10px;">
                    <img src="/res/images/tool_icon.png" class="show-icon">&nbsp;&nbsp;<span style="font-size: 14px;">开发测试中</span>
                </div>
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
                <span class="pull-left">项目编号：2016030200008</span>
                <div class="pull-right">
                    <img src="/res/images/edit_icon.png" class="opt-icon">
                    <img src="/res/images/delete_icon.png" class="opt-icon">
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
                    <div>
                        <span class="project-name">堆糖网</span>
                    <#--<div class="class-label">电商</div>-->
                    <#--<div class="class-label">社交</div>-->
                    </div>
                    <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                    <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                    <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                    <div><span class="index">共<span style="color:#000;">45</span>个工作日</span></div>
                </div>

            </div>
        </div>
        <div class="status">
            <div style="padding:20px 10px;">
                <p>意向金：0</p>
                <p>股权托管：有/无</p>
            </div>
        </div>
        <div class="status">
            <div style="padding-top:20px;text-align: center;">
                <p class="current-status">待完善资料</p>
                <a href="#" target="_blank">查看详情</a>
            </div>
        </div>
        <div class="opt">
            <div style="padding-top:20px;text-align: center;">
                <div>
                    <img src="/res/images/view_icon.png" class="show-icon">&nbsp;&nbsp;<span style="font-size: 12px;">查看成果</span>
                </div>
                <button type="button" class="complete-btn" style="margin-top:10px;">确认交付</button>
            </div>
        </div>
    </div>
</div>

        <#include "/rightFloat.ftl">
</@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>