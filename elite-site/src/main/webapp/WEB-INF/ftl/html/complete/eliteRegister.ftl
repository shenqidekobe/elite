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
            margin-left:140px;
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
            padding-left:200px;
            margin-top:60px;
        }

        .form-person .form-group{
            margin-bottom: 30px;
        }

        /*.form-person label{*/
            /*text-align: left!important;*/
            /*position: relative;*/
        /*}*/

        .required{
            color:#FEA600!important;
        }

        .date-select{
            display: block;
            width:100%;
            position: relative;
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
            padding-left:136px;
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

        .label-tip{
            font-size: 12px;
            color:#9B9B9B;
        }

        .agree-checkbox{
            width:18px;
            height:18px;
            line-height: 16px;
            border:1px solid #FEA600;
            border-radius: 2px;
            text-align: center;
        }

        .agree-checkbox img{
            width: 12px;
            height:12px;
        }

        .join-plan{
            font-size: 14px;
            margin-top:16px;
        }

        .join-plan >*{
            display: inline-block;
        }

        .skill-select{
            width:100%;
            background-color: #F2F2F2;
            border-radius: 6px;
            position: relative;
        }

        .skill-box{
            padding:15px;
            /*padding-top:15px;*/
            /*padding-left:15px;*/
        }

        .position{
            width:100%;
            height:60px;
            background-color: white;
            line-height: 60px;
            text-align: center;
        }


        .position button{
            margin-right:5px;
        }

        .skills{
            width:522px;
            /*height:122px;*/
            background-color: #000;
            opacity: 0.6;
            position: absolute;
            top:112px;
            left:34px;
            z-index: 300;
            color:white;
            border-radius: 5px;
            font-size: 12px;
        }

        .skills ul{
            padding:15px;
        }

        .skills ul li{
            float:left;
            width:98px;
            height:36px;
        }

        .skills ul li >*{
            float:left;
        }

        .skill-checkbox{
            display: block;
            width:18px;
            height:18px;
            background-color: white;
            border-radius: 2px;

            line-height: 16px;
            text-align: center;
        }

        .skill-checkbox img{
            width: 12px;
            height:12px;
        }

        .radio-box{
            width:18px;
            height:18px;
            margin-top:7px;
            vertical-align: sub;
        }

        .check-title{
            margin-right:30px;
        }

        .is-work button{
            padding-left:30px;
            padding-right:30px;
        }

        .number-table tr{
            border-radius: 4px;
        }

        .number-table tr td{
            width:30px;
            height:30px;
            border:1px solid #CCC;
        }

        .number-table input{
            width:30px;
            height:30px;
            border:none;
            text-align: center;
        }

        .number-box-first{
            width: 30px;
            height:32px;
            border:1px solid #CCC;
            border-top-left-radius: 4px;
            border-bottom-left-radius: 4px;
            position: absolute;
            top:0;
            left:0;
        }

        .number-box-last{
            width: 30px;
            height:32px;
            border:1px solid #CCC;
            border-top-right-radius: 4px;
            border-bottom-right-radius: 4px;
            position: absolute;
            top:0;
            left:0;
        }

        .active-btn{
            background-color: #2CB7C9!important;
            color:white!important;
            border:1px solid #2CB7C9!important;
        }



    </style>
    </@block>

<#--中间正文部分-->
    <@block name="content">

    <div class="process">
        <div class="process-box">
            <div class="process-circle active-process">1</div>
            <div class="process-circle" style="left:352px;">2</div>
            <div class="process-circle" style="left:544px;">3</div>
            <div class="process-circle" style="left:736px;">4</div>
            <p class="process-title active-title">当前状态</p>
            <p class="process-title" style="left:342px;">基本信息</p>
            <p class="process-title" style="left:526px;">技能&nbsp;|&nbsp;经历</p>
            <p class="process-title" style="left:724px;">个人征信</p>

            <div class="process-line"></div>
            <div class="process-line" style="left:402px;"></div>
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
                <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 胜任角色<span class="label-tip">(可多选)</span></label>
                    <div class="col-xs-9 col-md-9">
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">工程师</button>
                            <button type="button" class="btn btn-default active-btn">设计师</button>
                            <button type="button" class="btn btn-default">产品经理</button>
                            <button type="button" class="btn btn-default">项目经理</button>
                        </div>

                        <div class="join-plan">
                            <div class="agree-checkbox">
                                <img src="/res/images/checkbox_tick.png">
                            </div>
                            <p>&nbsp;&nbsp;参与<span style="color:#FEA600;">《云英汇CTO签约计划》</span>，如果你有项目管理和技术任务分解能力，可以勾选此项</p>
                        </div>

                        <div class="skill-select">
                            <div class="skill-box">
                                <ul class="nav nav-tabs">
                                    <li role="presentation" class="active"><a href="#">工程师</a></li>
                                    <li role="presentation"><a href="#">设计师</a></li>
                                </ul>
                                <div class="position">
                                    <button type="button" class="btn btn-default">前端开发</button>
                                    <button type="button" class="btn btn-default active-btn">后端开发</button>
                                    <button type="button" class="btn btn-default">移动开发</button>
                                    <button type="button" class="btn btn-default">运维</button>
                                    <button type="button" class="btn btn-default">DBA</button>
                                    <button type="button" class="btn btn-default">硬件</button>
                                    <button type="button" class="btn btn-default">测试</button>
                                </div>

                                <div class="skills">
                                    <ul>
                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;Java
                                        </li>
                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;Phthon
                                        </li>
                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;PHP
                                        </li>
                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;.NET
                                        </li>
                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;C#
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;C ++
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;C
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;VB
                                        </li>

                                        <li style="width:120px;">
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;自然语言处理
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;搜索算法
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;精准推荐
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;Perl
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;Ruby
                                        </li>

                                        <li>
                                            <span class="skill-checkbox">
                                                <img src="/res/images/checkbox_tick2.png">
                                            </span> &nbsp;&nbsp;Shell
                                        </li>

                                    </ul>
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 相关工作年限</label>
                    <div class="col-xs-7 col-md-7">
                        <img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
                        <span class="check-title">1-2年</span>
                        <img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
                        <span class="check-title">2-3年</span>
                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">3-5年</span>
                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">5年及以上</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 每周可分配时长</label>
                    <div class="col-xs-7 col-md-7">
                        <img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
                        <span class="check-title">10小时以下</span>
                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">10-30小时</span>
                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">30小时以上</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 是否在职</label>
                    <div class="col-xs-5 col-md-5">
                        <div class="btn-group is-work">
                            <button type="button" class="btn btn-default active-btn">是</button>
                            <button type="button" class="btn btn-default">否</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label">邀请码 <span class="label-tip">(选填)</span></label>
                    <div class="col-xs-6 col-md-6">
                        <table class="number-table">
                            <tr>
                                <td style="border:none;position: relative;">
                                    <div class="number-box-first" style="border-right:none;">
                                        <input type="text" maxlength="1" style="width:25px;">
                                    </div>
                                </td>
                                <td><input type="text" maxlength="1"></td>
                                <td><input type="text" maxlength="1"></td>
                                <td><input type="text" maxlength="1"></td>
                                <td><input type="text" maxlength="1"></td>
                                <td style="border:none;position: relative;">
                                    <div class="number-box-last" style="border-left:none;">
                                        <input type="text" maxlength="1" style="width:25px;">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="form-opt">
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="#" style="font-size: 14px;">先跳过，去发布项目</a>
                </div>
            </form>

    </div>
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>