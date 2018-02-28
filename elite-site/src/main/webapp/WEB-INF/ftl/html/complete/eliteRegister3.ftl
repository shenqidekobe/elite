<@extend name="completeLayout">
    <@block name="cs">
    <style type="text/css">

        .process{
            width:100%;
            height:130px;
            background-color: #F5FBFC;
        }

        .process-box{
            width:1000px;
            margin:0 auto;
            position: relative;
        }

        .process-line{
            width:132px;
            border-bottom: 2px solid #BFBFBF;
            position: absolute;
            top:50px;
            left:210px;
        }

        .content{
            width:1000px;
            margin:0 auto;
            min-height:250px;

            padding-bottom: 60px;
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
            top:32px;
            left:160px;
            z-index: 200;
        }

        .active-process{
            background-color: #7DD2E1;
        }

        .process-title{
            position: absolute;
            top:84px;
            left:148px;
        }

        .active-title{
            color:#2CB7C9;
        }

        .complete-circle{
            width:100px;
            height:100px;
            border:1px solid red;
            margin-left:200px;
            margin-top:20px;
            float:left;
        }


        .complete-tips{
            margin-top:30px;
            height:140px;
        }

        .complete-theme{
            margin-left:50px;
            padding-top:40px;
            float:left;
        }

        .tips{
            float:left;
            margin-left:20px;
        }

        .tips img{
            width:20px;
            height:20px;
        }

        .tips p{
            font-size: 12px;
            color:#9B9B9B;
        }

        .split-line{
            height:140px;
            border-left:1px solid #E6E6E6;
            float:left;
            margin-left:40px;
        }

        .form-person{
            display: block;
            padding-left:126px;
            padding-top:30px;
        }

        .form-tip{
            font-size: 12px;
            color:#FEA600;
            padding-left:66px;
        }

        .required{
            color:#FEA600!important;
        }

        .date-select{
            display: block;
            width:152px;
            position: relative;
            float:left;
        }

        .date-icon{
            width:20px;
            height:20px;
            position: absolute;
            top:7px;
            right:16px;
            z-index: 100;
        }


        .form-opt{
            margin-top:50px;
            padding-left:224px;
        }

        .btn-ok{
            width:186px;
            height:50px;
            background-color: #FEA600;
            border:none;
            border-radius: 32px;
            color:white;
            vertical-align: bottom;
            margin-right:20px;
        }

        .finish-line{
            border-color: #95D6E2;
        }

        .label-tip{
            font-size: 12px;
            color:#9B9B9B;
        }

        .belong-class{
            /*width:100%;*/
            /*height:162px;*/
            width:538px;
            background-color: #FAFAFA;
            border-radius: 4px;
        }

        .select-box{
            padding:10px;
        }

        .select-box button{
            width:80px;
            border:1px solid #CCCCCC;
            /*background-color: white;*/
            background-color: transparent;
            border-radius: 4px;
            font-size: 14px;
            margin:7px 10px;
        }

        .active-btn{
            background-color: #7DD2E1!important;
            border:1px solid #7DD2E1!important;
            color:white;
        }

        .add-tr{
            line-height: 30px;
            font-size: 14px;
            margin-top:10px;
        }

        .add-tr img{
            width:24px;
            height:24px;
            cursor: pointer;
        }

        .append-tr{
            width:100%;
            height:34px;
            line-height: 34px;
            border:1px solid #CCC;
            border-radius: 4px;
            font-size: 14px;
            padding:0 12px;
        }

        .work-period{
            color:#9B9B9B;
            float: right;
        }

        .opt-icon{
            width:20px;
            height:20px;
            margin-right:15px;
        }

        .upload-resume{
            width:100%;
            height:34px;
            line-height: 34px;
            background-color: #F2F2F2;
            border:none;
            border-radius: 4px;
            text-align: center;
            cursor: pointer;
            font-size: 14px;
            color:#4A4A4A;
        }

        .form-person .form-group{
            margin-bottom: 30px;
        }

        .modal-body{
            background-image:url('/res/images/pic_shadow3.png');
        }
        .close-icon{
            width:24px;
            height:24px;
            float:right;
            margin-top:6px;
            margin-right:6px;
            cursor: pointer;
        }

        .my-form label{
            color:white;
        }

        .error-tip{
            text-align: center;
            height:24px;
            color:red;
        }

        .modal-opt{
            margin-top:10px;
            padding-left:202px;
        }

        .opt-btn-ok{
            width:100px;
            height:34px;
            background-color: #FEA600;
            border:1px solid #FEA600;
            border-radius: 20px;
            color:white;
        }

        .opt-btn-cancel{
            width:100px;
            height:34px;
            background-color: transparent;
            border:1px solid #CCCCCC;
            border-radius: 20px;
            color:white;
            margin-left:120px;
        }

        .birth-box{
            position: relative;
        }

        .year-select{
            position: absolute;
            top:33px;
            left:0px;
            z-index: 100;
            border:1px solid #2CB7C9;

        }

        .year-select{
            display: none;
            height:266px;
            overflow: auto;
        }

        .year-select li{
            padding:0 16px;
            background-color: white;

        }

        .year-select li:hover{
            background-color: #2CB7C9;
            color:white;
            cursor: pointer;
        }
        .birth-year{
            position: absolute;
            top:7px;
            left:16px;
        }

        .birth-month{
            position: absolute;
            top:7px;
            left:37px;
        }

        .triangle{
            position: absolute;
            bottom: 6px;
            left:58px;
            cursor: pointer;
        }
        .trigger {
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
            border-color:#D8D8D8 transparent transparent;
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

        .form-label{
            float:left;
            line-height: 36px;
            margin-right:10px;
            color:white;
        }

    </style>
    </@block>

<#--中间正文部分-->
    <@block name="content">

    <div class="process">
        <div class="process-box">
            <div class="process-circle active-process">
                <img src="/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:352px;">
                <img src="/res/images/tick.png" width="24" height="auto">
            </div>
            <div class="process-circle active-process" style="left:544px;">3</div>
            <div class="process-circle" style="left:736px;">4</div>
            <p class="process-title active-title">当前状态</p>
            <p class="process-title active-title" style="left:342px;">基本信息</p>
            <p class="process-title active-title" style="left:526px;">技能&nbsp;|&nbsp;经历</p>
            <p class="process-title" style="left:724px;">个人征信</p>

            <div class="process-line finish-line"></div>
            <div class="process-line finish-line" style="left:402px;"></div>
            <div class="process-line" style="left:594px;"></div>
        </div>
    </div>

    <div class="content">
        <div class="complete-tips">
            <div class="complete-circle"></div>
            <div class="complete-theme">
                <p>完善个人信息</p>
                <p style="font-size: 14px;color:#9B9B9B;">个人信息越全，越受开发团队和投资方欢迎</p>
            </div>

            <div class="split-line"></div>

            <div class="tips">
                <img src="/res/images/tips_icon.png">&nbsp;&nbsp;tips
                <p style="margin-top:10px;">1.上传的资料越全您所获得的征信等级越高</p>
                <p>2.越高的征信等级将会再给您更多的展示权重</p>
                <p>3.高的征信等级能够吸引更多的合作伙伴</p>
                <p>4.此征信报告可以作为您的商业行为背书</p>
            </div>
        </div>

            <form class="form-group form-horizontal form-person" role="form" id="personFrom">
                <span class="form-tip">* &nbsp;必填项</span>
                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 关注行业<span class="label-tip">&nbsp;(少于3个)</span></label>
                    <div class="col-xs-8 col-md-8">
                        <div class="belong-class">
                            <div class="select-box">
                                <button type="button" class="active-btn" data-class="education">教育</button>
                                <button type="button" data-class="finance">金融</button>
                                <button type="button" data-class="car">汽车</button>
                                <button type="button" data-class="logistics">物流</button>
                                <button type="button" data-class="coffee">餐饮</button>
                                <button type="button" data-class="tour">旅游</button>
                                <button type="button" data-class="tour">游戏</button>
                                <button type="button" data-class="tour">硬件</button>
                                <button type="button" data-class="tour">视频</button>
                                <button type="button" data-class="tour">音乐</button>
                                <button type="button" data-class="tour">体育</button>
                                <button type="button" data-class="tour">社交</button>
                                <button type="button" data-class="tour">服务器</button>
                                <button type="button" data-class="tour">数据库</button>
                                <button type="button" data-class="tour">房产服务</button>
                                <button type="button" data-class="tour">医疗健康</button>
                                <button type="button" data-class="tour">信息分类</button>
                                <button type="button" data-class="tour">电子商务</button>
                                <button type="button" data-class="tour">企业服务</button>
                                <button type="button" data-class="tour">其他</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 技能槽</label>
                    <div class="col-xs-8 col-md-8">
                        <#--<div class="skill-list">-->
                            <#--<label>管理能力</label>-->
                            <#--<div></div>-->
                        <#--</div>-->
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 项目|工作经历</label>
                    <div class="col-xs-6 col-md-6">
                        <div class="append-tr">
                            <span>淘宝网商家服务集群</span>
                            <span>|</span>
                            <span>CTO</span>
                            <span class="work-period">2014.02-2015.06</span>
                        </div>

                        <div class="add-tr">
                            <img src="/res/images/add_icon2.png" id="addIcon1"> &nbsp;添加
                        </div>
                    </div>
                    <div class="col-xs-2 col-md-2">
                        <div style="line-height: 30px;">
                            <img src="/res/images/edit_icon.png" class="opt-icon">
                            <img src="/res/images/delete_icon.png" class="opt-icon">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 教育经历</label>
                    <div class="col-xs-6 col-md-6">
                        <div class="append-tr">
                            <span>淘宝网商家服务集群</span>
                            <span>|</span>
                            <span>CTO</span>
                            <span class="work-period">2014.02-2015.06</span>
                        </div>

                        <div class="add-tr">
                            <img src="/res/images/add_icon2.png" id="addIcon2"> &nbsp;添加
                        </div>
                    </div>
                    <div class="col-xs-2 col-md-2">
                        <div style="line-height: 30px;">
                            <img src="/res/images/edit_icon.png" class="opt-icon">
                            <img src="/res/images/delete_icon.png" class="opt-icon">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label">其他培训经历</label>
                    <div class="col-xs-6 col-md-6">
                        <div class="append-tr">
                            <span>淘宝网商家服务集群</span>
                            <span>|</span>
                            <span>CTO</span>
                            <span class="work-period">2014.02-2015.06</span>
                        </div>

                        <div class="add-tr">
                            <img src="/res/images/add_icon2.png" id="addIcon3"> &nbsp;添加
                        </div>
                    </div>
                    <div class="col-xs-2 col-md-2">
                        <div style="line-height: 30px;">
                            <img src="/res/images/edit_icon.png" class="opt-icon">
                            <img src="/res/images/delete_icon.png" class="opt-icon">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label">附件简历</label>
                    <div class="col-xs-4 col-md-4">
                        <div class="upload-resume">
                            <img src="/res/images/upload_icon.png" width="20" height="20">
                            <span style="margin-left:10px;">上传附件简历</span>
                        </div>
                    </div>
                </div>

                <div class="form-opt">
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="#" style="font-size: 14px;">先跳过，去发布项目</a>
                </div>
            </form>

    </div>



    <#--添加项目经历模态框-->
    <div class="container">
        <div class="row">
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="addProjectDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:580px; height:400px;margin:0 auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                        <#--<button type="button" class="close" style="color:white;top: -18px;right: -20px;position: absolute;outline: none;" data-dismiss="modal" aria-label="Close">-->
                        <#--<span class="glyphicon glyphicon-remove-circle closeBtn" aria-hidden="true"></span>-->
                        <#--</button>-->

                            <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                        <#--正文-->
                            <form class="form-group form-horizontal my-form" role="form" id="addProjectForm" style="padding-top:30px;">

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 公司或项目名称</label>
                                    <div class="col-xs-7 col-md-7">
                                        <input class="form-control" type="text" id="name" name="name" placeholder="公司或项目名称" maxlength="20">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 担任的职位</label>
                                    <div class="col-xs-7 col-md-7">
                                        <input class="form-control" type="text" id="phone" name="phone" placeholder="担任的职位" maxlength="11">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 在职或项目时间</label>
                                    <div class="col-xs-7 col-md-7">
                                         <span class="date-select">
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="起始时间" style="background-color: white;"/>
                                        </span>

                                        <span style="float:left;color:white;line-height: 34px;margin:0 5px;">-</span>
                                         <span class="date-select">
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="截止时间" style="background-color: white;"/>
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 项目描述</label>
                                    <div class="col-xs-7 col-md-7">
                                        <textarea class="form-control" rows="5" placeholder="截止时间"></textarea>
                                    </div>
                                </div>


                                <div class="error-tip">请输入姓名</div>

                                <div class="modal-opt">
                                    <button type="button" class="opt-btn-ok" id="saveBtn">保存</button>
                                    <button type="button" class="opt-btn-cancel" data-dismiss="modal" aria-label="Close">取消</button>
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
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="addSchoolDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:580px; height:246px;margin:0 auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                            <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                        <#--正文-->
                            <form class="form-group form-horizontal my-form" role="form" style="padding-top:30px;" id="addSchoolForm">
                                <div class="form-group">
                                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 毕业院校</label>
                                    <div class="col-xs-8 col-md-8">
                                        <input class="form-control" type="text" id="name" name="name" placeholder="毕业院校" maxlength="20">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 最高学历</label>
                                    <div class="col-xs-3 col-md-3">
                                        <div class="birth-box">
                                            <input class="form-control" type="text" id="birth"  style="width:100%;background-color: white;" disabled>
                                            <div>
                                                <ul class="year-select">
                                                    <#list 1..18 as i>
                                                        <li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>
                                                    </#list>
                                                </ul>

                                                <div class="triangle" style="left:70px;">
                                                    <div class="trigger"></div>
                                                </div>
                                            </div>

                                        </div>
                                    </div>

                                    <span class="form-label"><span class="required">* </span> 毕业时间</span>
                                     <span class="date-select">
                                        <img src="/res/images/date_icon.png" class="date-icon">
                                        <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="起始时间" style="background-color: white;"/>
                                    </span>
                                </div>

                                <div class="error-tip">请输入姓名</div>

                                <div class="modal-opt" style="padding-left:150px;">
                                    <button type="button" class="opt-btn-ok" id="saveBtn">保存</button>
                                    <button type="button" class="opt-btn-cancel" data-dismiss="modal" aria-label="Close" style="margin-left:80px;">取消</button>
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
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="addTrainDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:562px; height:286px;margin:0 auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                        <#--<button type="button" class="close" style="color:white;top: -18px;right: -20px;position: absolute;outline: none;" data-dismiss="modal" aria-label="Close">-->
                        <#--<span class="glyphicon glyphicon-remove-circle closeBtn" aria-hidden="true"></span>-->
                        <#--</button>-->

                            <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                        <#--正文-->
                            <form class="form-group form-horizontal my-form" role="form" id="addTrainForm" style="padding-top:30px;">

                                <div class="form-group">
                                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 培训机构</label>
                                    <div class="col-xs-8 col-md-8">
                                        <input class="form-control" type="text" id="name" name="name" placeholder="培训机构" maxlength="20">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 培训内容</label>
                                    <div class="col-xs-8 col-md-8">
                                        <input class="form-control" type="text" id="phone" name="phone" placeholder="培训内容" maxlength="11">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 培训时间</label>
                                    <div class="col-xs-8 col-md-8">
                                         <span class="date-select">
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="起始时间" style="background-color: white;"/>
                                        </span>

                                        <span style="float:left;color:white;line-height: 34px;margin:0 5px;">-</span>
                                         <span class="date-select">
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="截止时间" style="background-color: white;"/>
                                        </span>
                                    </div>
                                </div>

                                <div class="error-tip">请输入姓名</div>

                                <div class="modal-opt" style="padding-left:150px;">
                                    <button type="button" class="opt-btn-ok" id="saveBtn">保存</button>
                                    <button type="button" class="opt-btn-cancel" data-dismiss="modal" aria-label="Close" style="margin-left:80px;">取消</button>
                                </div>
                            </form>

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