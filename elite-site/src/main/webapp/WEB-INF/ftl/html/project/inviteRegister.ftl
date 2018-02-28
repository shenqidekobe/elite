<@extend name="personLayout">
    <@block name="cs">
    <link rel="stylesheet" type="text/css" href="${static('/styles/datePicker.css')}"/>
    <style type="text/css">


        .opt-and-search{
            width:580px;
        }

        .add-btn{
            width:240px;
            height:40px;
            line-height: 40px;
            cursor: pointer;
            border:1px solid #FEA600;
            border-radius: 4px;
            color:#FEA600;
            float:right;
        }

        .add-title{
            font-size: 18px;
            line-height: 54px;
        }

        .add-btn img{
            width:24px;
            height:24px;
            margin-left:20px;
            vertical-align: middle;
            margin-bottom: 3px;
        }

        .split-line{
            width:100%;
            border-bottom: 1px solid #E6E6E6;
        }


        /*****************************************模态框样式**********************/
        .modal-body{
            background-image:url('/res/images/pic_shadow3.png');
        }

        #addProjectForm label{
            font-size: 14px;
            color:white;
        }

        .close-icon{
            width:24px;
            height:24px;
            float:right;
            margin-top:6px;
            margin-right:6px;
            cursor: pointer;
        }

        #addProjectForm{
            padding-top:30px;
        }


        .hr-line2{
            width:88%;
            border-bottom: 1px dashed #CCCCCC;
            margin-left:48px;
            margin-top:30px;
            margin-bottom: 20px;
        }


        #addProjectForm .btn,.btn-default{
            border-color:#9B9B9B;
            background-color: transparent;
            outline: none;
            color:white;
            width:86px;
        }

        .active-period{
            background-color: #7DD2E1!important;
            /*color:white!important;*/
            border:1px solid #7DD2E1!important;
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


        .modal-triangle{
            position: absolute;
            bottom: 6px;
            left:58px;
            cursor: pointer;
        }
        .modal-trigger {
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

        .location{
            position: absolute;
            top:8px;
            left:12px;
            width:auto;
            height:20px;
        }

        .upload-resume{
            width:100%;
            height:34px;
            line-height: 34px;
            background-color: white;
            border-radius: 4px;
            text-align: center;
            cursor: pointer;
        }

        .modal-opt{
            text-align: center;
            margin-top:10px;
        }

        .opt-btn-ok{
            width:126px;
            height:40px;
            background-color: #FEA600;
            border:1px solid #FEA600;
            border-radius: 20px;
            color:white;
        }

        .opt-btn-cancel{
            width:126px;
            height:40px;
            background-color: transparent;
            border:1px solid #CCCCCC;
            border-radius: 20px;
            color:white;
        }

        .error-tip{
            text-align: center;
            height:24px;
            color:red;
        }

        .belong-class{
            width:100%;
            height:162px;
            background-color: white;
            border-radius: 4px;
        }

        .select-box{
            padding:10px;
        }

        .select-box button{
            width:80px;
            border:1px solid #7DD2E1;
            background-color: white;
            border-radius: 4px;
            font-size: 14px;
            margin:5px 6px;
        }

        .radio-box{
            width:18px;
            height:18px;
            margin-top:7px;
        }




        /*****************************************模态框样式**********************/

        .your-code{
            font-size: 14px;
            color:#4A4A4A;
            padding-top:10px;
        }

        .copy-btn{
            width:155px;
            height:40px;
            background-color: #7DD2E1;
            border:none;
            border-radius: 4px;
            color:white;
            float:right;
        }

        .invite-code-input{
            width:200px;
            background-color: #F2F2F2;
            text-align: center;
            float:left;
            margin-top:7px;
        }

        .table-search >*{
            display: inline-block;
        }

        .search-btn{
            width:120px;
            height:36px;
            background-color: #FEA600;
            border:none;
            border-radius: 18px;
            color:white;
            font-size: 14px;
            float:right;
        }

        .search-icon{
            width:20px;
            height:20px;
        }

     /****************************时间控件样式*************************/
        .date-select{
            /*display: block;*/
            width:176px;
            height:34px;
            position: relative;
            margin-left:20px;
        }

        .date-icon{
            width:20px;
            height:20px;
            position: absolute;
            top:7px;
            right:16px;
        }


    /****************************时间控件样式*************************/

        .search-status{
            margin-top:30px;
            width:100%;
        }
        .active-status{
            color:#FEA600;
        }

        .person-table thead tr th{
            background-color: #F5F5F5;
            color:#9B9B9B;
            font-weight: normal;
            border-bottom: none;
            line-height: 30px;
            font-size: 14px;
        }

        .person-table tbody tr td{
           border-top:none;
            height:40px;
            line-height: 40px;
            font-size: 14px;
            color:#4A4A4A;
        }

        .active-line{
            position: absolute;
            top:0;
            left:0;
            width:136px;
            border-bottom: 2px solid #FEA600;
        }

        .other-select{
            cursor: pointer;
        }

        .table-btn{
            width:60px;
            height:22px;
            line-height:18px;
            border:1px solid #9B9B9B;
            color:#4A4A4A;
            border-radius: 10px;
            vertical-align: middle;
        }
        /****************************分页样式*************************/

        .page >*{
            display: inline-block;
        }
        .prev{
            color:#CCCCCC;
        }

        .next{
            color:#CCCCCC;
        }

        .page-circle{
            width: 20px;
            height: 20px;
            line-height: 20px;
            border: 1px solid #CCCCCC;
            text-align: center;
            color:#CCCCCC;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            font-size: 12px;
        }

        .active-page{
            border:1px solid #4A4A4A;
            color:#4A4A4A;
        }

        .goto-page{
            width:40px;
            height:20px;
            border:1px solid #9B9B9B;
            border-radius: 4px;
            outline: none;
            font-size: 12px;
            text-align: center;
        }
        .go-circle{
            width: 20px;
            height: 20px;
            /*line-height: 20px;*/
            border: 1px solid #979797;
            text-align: center;
            background-color: #979797;
            color:white;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            font-size: 12px;
        }

        /****************************分页样式*************************/


    </style>
    </@block>
        <#--右边的操作区-->
        <@block name="rightContent">
            <div class="opt-and-search">
                <div class="add">
                    <span class="add-title">项目注册邀请</span>
                    <div class="add-btn">
                        <img src="/res/images/add_icon.png">
                        &nbsp;&nbsp;&nbsp;新增项目邀请备案
                    </div>
                </div>
            </div>

            <div class="split-line" style="margin:0 0;"></div>

            <div class="opt-and-search">
                <div class="your-code">您的邀请码:</div>
                <div class="invite-code" style="margin-top:5px;height:44px;">
                    <input class="form-control invite-code-input" type="text" id="inviteCode" value="20JV755IJLDjkJH">
                    <button type="button" class="copy-btn" id="copy-btn">复制邀请码</button>
                </div>

                <div class="your-code" style="padding:0;margin-top:20px;">您的邀请链接:</div>
                <div class="invite-code" style="margin-top:5px;height:44px;">
                    <input class="form-control invite-code-input" type="text" id="inviteUrl" style="width:370px;" value="http://www.yunyinghui.com/regster.html?20JV755IJLDjkJH">
                    <button type="button" class="copy-btn">复制邀请网址</button>
                </div>

                <#--<div class="search-box table-search" style="margin-top:80px;">-->
                    <#--<input class="form-control" type="text" id="searchWord" placeholder="精英/手机号/角色" maxlength="11" style="width:200px;">-->

                    <#--<span class="date-select">-->
                        <#--<img src="/res/images/date_icon.png" class="date-icon">-->
                        <#--<input type="text" class="form-control" id="receiptdate"  readonly  placeholder="选择备案时间" style="background-color: white;"/>-->
                    <#--</span>-->
                    <#--<button type="button" class="search-btn">-->
                        <#--<img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索-->
                    <#--</button>-->
                <#--</div>-->

            </div>

            <#--表格专区-->
            <div class="search-status">
                <#--<div style="padding-left:30px;">-->
                    <#--<span class="has-record active-status">已备案精英</span>-->
                    <#--<span class="has-register other-select" style="margin-left:60px;">已注册精英</span>-->
                <#--</div>-->
                <#--<div  style="margin:20px 0;position: relative;">-->
                    <#--<div class="split-line" style="margin:0 0;"></div>-->
                    <#--<div class="active-line"></div>-->
                <#--</div>-->


                <div style="padding-left:30px;">
                    <span>已备案项目</span>
                </div>
                <div style="margin:20px 0;">
                    <div class="split-line"></div>
                </div>

                <table class="person-table table" id="table1">
                    <thead>
                    <tr>
                        <th>项目联系人</th>
                        <th style="border-right:1px solid #D9D9D9;">手机号</th>
                        <th>项目所属行业</th>
                        <th>项目当前阶段</th>
                        <th style="border-right:1px solid #D9D9D9;">项目所在城市</th>
                        <th>是否能以您的名义联系</th>
                        <th>项目描述</th>
                    </tr>
                    </thead>
                    <tbody>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>
                            <button type="button" class="table-btn">查看</button>
                        </td>
                    </tr>
                    <tr>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                        <td>aaaa</td>
                    </tr>



                    </tbody>
                </table>

                <#--分页组件(试用)-->
                <div class="page">
                    <span class="prev">&lt;</span>
                    <div class="page-circle">1</div>
                    <div class="page-circle active-page">2</div>
                    <div class="page-circle">3</div>
                    <span class="next">&gt;</span>
                    <input type="text" class="goto-page">
                    <div class="go-circle">go</div>
                </div>

            </div>


            <#--新增精英模态框-->
            <div class="container">
                <div class="row">
                    <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true"  id="addPersonDialog" style="top:50%;margin-top:-332px;">
                        <div class="modal-dialog" style="margin:0 auto;width:auto;">
                <div class="modal-content" style="width:760px; height:664px;margin:0 auto;overflow: auto;">
                    <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                        <#--<button type="button" class="close" style="color:white;top: -18px;right: -20px;position: absolute;outline: none;" data-dismiss="modal" aria-label="Close">-->
                            <#--<span class="glyphicon glyphicon-remove-circle closeBtn" aria-hidden="true"></span>-->
                        <#--</button>-->

                        <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                    <#--正文-->

                        <form class="row form-group form-horizontal" role="form" id="addProjectForm">

                            <div class="form-group">
                                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 姓名</label>
                                <div class="col-xs-5 col-md-5">
                                    <input class="form-control" type="text" id="name" name="name" placeholder="请输入姓名" maxlength="20">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 手机号</label>
                                <div class="col-xs-5 col-md-5">
                                    <input class="form-control" type="text" id="phone" name="phone" placeholder="请输入11位手机号" maxlength="11"">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 项目所属行业<span style="font-size: 12px;">(少于3个)</span></label>
                                <div class="col-xs-8 col-md-8">
                                   <div class="belong-class">
                                       <div class="select-box">
                                           <button type="button" data-class="education">教育</button>
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

                            <div class="hr-line2"></div>

                            <div class="form-group">
                                <label class="col-xs-3 col-md-3 control-label">项目所处阶段</label>
                                <div class="col-xs-9 col-md-9">
                                    <button type="button" class="btn btn-default" data-title="engineer">启动阶段</button>
                                    <button type="button" class="btn btn-default active-period" data-title="UI">天使轮</button>
                                    <button type="button" class="btn btn-default" data-title="product">pre A</button>
                                    <button type="button" class="btn btn-default" data-title="product">A轮</button>
                                    <button type="button" class="btn btn-default" data-title="product">B轮</button>
                                    <button type="button" class="btn btn-default" data-title="product">C轮及以上</button>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3 col-md-3 control-label">项目所在城市</label>
                                <div class="col-xs-5 col-md-5">
                                    <div class="birth-box">
                                        <input class="form-control" type="text"  style="background-color: white;" disabled>
                                        <img src="/res/images/location_icon.png" class="location">
                                        <div class="birth-year" id="province" style="left:50px;">上海</div>
                                        <div>
                                            <ul class="year-select" style="left:40px;">
                                                <li data-type="province">上海</li>
                                                <li data-type="province">北京</li>
                                                <li data-type="province">深圳</li>
                                            </ul>

                                            <div class="modal-triangle" style="left:104px;top:6px;width:22px;">
                                                <div class="modal-trigger"></div>
                                            </div>
                                        </div>

                                        <div class="birth-year" id="city" style="left:134px;top:7px;">上海市</div>
                                        <div>
                                            <ul class="year-select" style="left:106px;">
                                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                                <li data-type="city" style="padding:0 28px;">上海市</li>
                                            </ul>

                                            <div class="modal-triangle" style="left:206px;top:6px;width:22px;">
                                                <div class="modal-trigger"></div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-4 col-md-4 control-label">是否能以您的名义进行联系</label>
                                <div class="col-xs-6 col-md-6">
                                    <img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
                                    <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio" style="margin-left:30px;">
                                </div>
                            </div>

                            <div class="form-group">
                                <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 项目简单描述</label>
                                <div class="col-xs-5 col-md-5">
                                    <textarea class="form-control" rows="3" placeholder="少于1000字">aaaa</textarea>
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
        </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>