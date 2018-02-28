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
            color:#FEA600;
        }

        .tender-box{
            margin-top:40px;
            margin-left:auto;
            margin-right:auto;
            width:860px;
            background-color: #F5FBFC;
            border:1px solid #E6E6E6;
            border-radius: 8px;
            padding:20px 30px;
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

        .service-btn{
            padding:6px 10px;
            background-color: white;
            border:1px solid #CCCCCC;
            border-radius: 6px;
            font-size: 14px;
            color:#4A4A4A;
            min-width: 80px;
        }

        .active-btn{
            background-color: #7DD2E1;
            border:1px solid #7DD2E1;
            color:white;
        }
        .title-index{
            margin-top:30px;
        }

        .tip{
            margin-left:20px;
            font-size: 12px;
            color:#9B9B9B;
            vertical-align: bottom;
        }

        .required{
            color:#FEA600!important;
        }

        .period-table thead tr th{
            font-size: 12px;
            color:#9B9B9B;
            font-weight: normal;
            line-height: 30px;
        }

        .period-table th,td{
            border:none!important;
        }

        .period-lab{
            width:90px;
            height:26px;
            line-height:26px;
            text-align: center;
            border:1px solid #FEA600;
            border-radius: 14px;
            font-size: 12px;
            color:#FEA600;
            margin-top:6px;
            margin-left:auto;
            margin-right:auto;

        }

        .period-time-cost{
            width:350px;
            height:36px;
            border:1px solid #95D6E2;
            border-radius: 6px;
            background-color: white;
            margin:0 auto;
            position: relative;
        }

        .date-icon{
            width:20px;
            height:20px;
            cursor: pointer;

            position: absolute;
            top:7px;
            left:96px;
        }

        .date-block{
            display: block;
            width:88px;
            height:34px;
            line-height: 34px;
            position: absolute;
            top:0;
            left:5px;
        }

        .vertical-line{
            height:23px;
            border-right:1px solid #E6E6E6;
            position: absolute;
            left:264px;
            top:6px;
        }

        .day-count{
            position: absolute;
            left:280px;
            top:9px;
        }

        .upload-btn{
            width:182px;
            height:40px;
            border:none;
            border-radius: 6px;
            background-color: #F0EEEC;
            color:#9B9B9B;
            position: absolute;
            right:10px;
            bottom:0;
        }

        .download-box{
            width:250px;
            height:30px;
            line-height: 30px;
            font-size: 14px;
            color:#4A90E2;
            border:1px solid #E6E6E6;
            border-radius: 4px;
            text-align: center;
            margin-left:40px;
        }

        .download-icon{
            width:20px;
            height:20px;
        }

    </style>
    </@block>
    <@block name="body">

    <#--头部-->
        <@accounthead opt=""/>


    <div class="content">
        <div class="flag">
            <span>个人主页 &gt; 我的项目 &gt; 堆糖网 项目详情 &gt; <span style="color:#2CB7C9;">竞标</span></span>
        </div>

        <div class="content-box">
            <div class="process">
                <div class="process-circle active-process">1</div>
                <div class="process-circle active-process" style="left:230px;">2</div>
                <div class="process-circle" style="left:440px;">3</div>
                <div class="process-circle" style="left:650px;">
                    <img src="/res/images/tick.png" width="24" height="auto">
                </div>
                <p class="process-title active-title">待竞标</p>
                <p class="process-title active-title" style="left:213px;">CTO竞标</p>
                <p class="process-title" style="left:442px;">中标</p>
                <p class="process-title" style="left:638px;">项目启动</p>

                <div class="process-line finish-line"></div>
                <div class="process-line finish-line" style="left:280px;"></div>
                <div class="process-line" style="left:490px;"></div>

                <p class="process-tip" style="left:284px;">填写竞标书，项目经理审核</p>
            </div>

            <div class="tender-box">
                <p style="font-size: 18px;" class="text-center">《堆糖网项目招标书》</p>

                <p class="title-index" style="padding-left:10px;">一、选择服务方式</p>
                <div style="padding-left:40px;">
                    <button type="button" class="service-btn active-btn">费用</button>
                    <button type="button" class="service-btn" style="margin-left:40px;">费用&nbsp;+&nbsp;股权</button>
                    <span class="tip" style="vertical-align: sub;">股权数待线下<span style="color:#7DD2E1;">沟通</span>协商确定</span>
                </div>

                <p class="title-index"><span class="required">*</span>&nbsp;二、项目所需费用 <span class="tip">一个合理的报价将大大提高中标率</span></p>
                <div style="padding-left:40px;">
                    <input type="text" class="form-control" placeholder="填写费用" style="width:140px;display: inline-block;">
                    &nbsp;<span style="font-size: 12px;color:#4A4A4A;vertical-align: bottom;">元</span>
                </div>

                <p class="title-index"><span class="required">*</span>&nbsp;三、研发计划 <span class="tip">合理地对项目进行计划安排</span></p>
                <div style="width:600px;padding-left:32px;">
                       <table class="table period-table">
                           <thead>
                                <tr>
                                    <th>项目阶段</th>
                                    <th>阶段时间规划</th>
                                    <th>阶段所需费用</th>
                                </tr>
                           </thead>
                           <tbody>
                                <tr>
                                    <td>
                                        <div class="period-lab">咨询策划阶段</div>
                                    </td>
                                    <td>
                                        <div class="period-time-cost" style="font-size: 12px;">
                                            <span class="date-block">2016-02-02</span>
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <span class="date-block" style="width:10px;left:124px;">-</span>
                                            <span class="date-block" style="left:134px;">2016-02-03</span>
                                            <img src="/res/images/date_icon.png" class="date-icon" style="left:224px;">
                                            <div class="vertical-line"></div>
                                            <span class="day-count">共<span style="color:#FEA600;">30</span>天</span>
                                        </div>
                                    </td>

                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
                                        <input class="period-time-cost" style="width:50px;text-align: center;">&nbsp;万
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="period-lab">品牌设计阶段</div>
                                    </td>
                                    <td>
                                        <div class="period-time-cost" style="font-size: 12px;">
                                            <span class="date-block">2016-02-02</span>
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <span class="date-block" style="width:10px;left:124px;">-</span>
                                            <span class="date-block" style="left:134px;">2016-02-03</span>
                                            <img src="/res/images/date_icon.png" class="date-icon" style="left:224px;">
                                            <div class="vertical-line"></div>
                                            <span class="day-count">共<span style="color:#FEA600;">30</span>天</span>
                                        </div>
                                    </td>

                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
                                        <input class="period-time-cost" style="width:50px;text-align: center;">&nbsp;万
                                    </td>
                                </tr>

                                <tr>
                                    <td>
                                        <div class="period-lab">需求梳理阶段</div>
                                    </td>
                                    <td>
                                        <div class="period-time-cost" style="font-size: 12px;">
                                            <span class="date-block">2016-02-02</span>
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <span class="date-block" style="width:10px;left:124px;">-</span>
                                            <span class="date-block" style="left:134px;">2016-02-03</span>
                                            <img src="/res/images/date_icon.png" class="date-icon" style="left:224px;">
                                            <div class="vertical-line"></div>
                                            <span class="day-count">共<span style="color:#FEA600;">30</span>天</span>
                                        </div>
                                    </td>

                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
                                        <input class="period-time-cost" style="width:50px;text-align: center;">&nbsp;万
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="period-lab">界面设计阶段</div>
                                    </td>
                                    <td>
                                        <div class="period-time-cost" style="font-size: 12px;">
                                            <span class="date-block">2016-02-02</span>
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <span class="date-block" style="width:10px;left:124px;">-</span>
                                            <span class="date-block" style="left:134px;">2016-02-03</span>
                                            <img src="/res/images/date_icon.png" class="date-icon" style="left:224px;">
                                            <div class="vertical-line"></div>
                                            <span class="day-count">共<span style="color:#FEA600;">30</span>天</span>
                                        </div>
                                    </td>

                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
                                        <input class="period-time-cost" style="width:50px;text-align: center;">&nbsp;万
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="period-lab">开发测试阶段</div>
                                    </td>
                                    <td>
                                        <div class="period-time-cost" style="font-size: 12px;">
                                            <span class="date-block">2016-02-02</span>
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <span class="date-block" style="width:10px;left:124px;">-</span>
                                            <span class="date-block" style="left:134px;">2016-02-03</span>
                                            <img src="/res/images/date_icon.png" class="date-icon" style="left:224px;">
                                            <div class="vertical-line"></div>
                                            <span class="day-count">共<span style="color:#FEA600;">30</span>天</span>
                                        </div>
                                    </td>

                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
                                        <input class="period-time-cost" style="width:50px;text-align: center;">&nbsp;万
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="period-lab">交付上线阶段</div>
                                    </td>
                                    <td>
                                        <div class="period-time-cost" style="font-size: 12px;">
                                            <span class="date-block">2016-02-02</span>
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <span class="date-block" style="width:10px;left:124px;">-</span>
                                            <span class="date-block" style="left:134px;">2016-02-03</span>
                                            <img src="/res/images/date_icon.png" class="date-icon" style="left:224px;">
                                            <div class="vertical-line"></div>
                                            <span class="day-count">共<span style="color:#FEA600;">30</span>天</span>
                                        </div>
                                    </td>

                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
                                        <input class="period-time-cost" style="width:50px;text-align: center;">&nbsp;万
                                    </td>
                                </tr>
                                <tr>
                                    <td>
                                        <div class="period-lab">运营阶段</div>
                                    </td>
                                    <td>
                                        <div class="period-time-cost" style="font-size: 12px;">
                                            <span class="date-block">2016-02-02</span>
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <span class="date-block" style="width:10px;left:124px;">-</span>
                                            <span class="date-block" style="left:134px;">2016-02-03</span>
                                            <img src="/res/images/date_icon.png" class="date-icon" style="left:224px;">
                                            <div class="vertical-line"></div>
                                            <span class="day-count">共<span style="color:#FEA600;">30</span>天</span>
                                        </div>
                                    </td>

                                    <td style="text-align: center;font-size: 14px;color:#FEA600;">
                                        <input class="period-time-cost" style="width:50px;text-align: center;">&nbsp;万
                                    </td>
                                </tr>
                           </tbody>

                       </table>
                </div>

                <p class="title-index" style="padding-left:10px;">四、您针对此项目的优势</p>
                <div style="padding-left:40px;position: relative;">
                   <textarea class="form-control" rows="7" style="width:550px;" placeholder="告诉我们您的报价和计划是合理且具备可行性的，如类似项目经历、框架、成果等（不少于50字）"></textarea>
                    <button type="button" class="upload-btn">
                        <img src="/res/images/upload_icon.png" width="20" height="20">
                        &nbsp;上传附件文档
                    </button>
                </div>

                <div style="height:30px;margin-top:20px;">
                    <div class="download-box">
                        <img src="/res/images/brooch_icon.png" width="18" height="20">
                        &nbsp;<a href="#">堆糖网项目参考文档</a>
                        &nbsp;
                        <a href="#" style="margin-left:30px;">
                            <img src="/res/images/download_icon.png" class="download-icon">
                        </a>
                    </div>
                </div>

            </div>

            <p class="end-time">
                <span>距离招标截止：</span>&nbsp;<span>3天 13时 16：40分</span>
            </p>
            <button type="button" class="go-bid-btn">确认并提交</button>

        </div>
    </div>


    </@block>
    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/index.js')}"></script>
    </@block>
</@extend>