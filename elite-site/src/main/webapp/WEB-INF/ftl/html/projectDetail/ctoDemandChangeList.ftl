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

        .active-line{
            width:50%;
            border-bottom: 2px solid #FEA600;
            position: absolute;
            left:0;
            bottom: 0;
        }

        .tab{
            cursor: pointer;
        }

        .demand{
            width:100%;
            height:140px;
            border:1px solid #E6E6E6;
            border-radius: 6px;
            margin-bottom: 20px;
            overflow: hidden;
        }

        .time-code{
            width:100%;
            height:40px;
            line-height: 40px;
            background-color: #F2F2F2;
            border-bottom: 1px solid #E6E6E6;
            border-top-left-radius: 6px;
            border-top-right-radius: 6px;
            font-size: 12px;
            color:#9B9B9B;
            padding-left:20px;
            position: relative;
        }

        .demand-detail{
            width:100%;
            height:98px;
            background-color: #FAFAFA;
            border-bottom-left-radius: 6px;
            border-bottom-right-radius: 6px;
        }

        .column1{
            width:25%;
            height:98px;
            border-right:1px solid #E6E6E6;
            float:left;
            padding:20px;
        }

        .column2{
            width:15%;
            height:98px;
            border-right:1px solid #E6E6E6;
            float:left;
            padding-top:20px;
            text-align: center;
        }

        .column3{
            width:20%;
            height:98px;
            float:left;
            background-color: #F5FBFC;
            text-align: center;
            padding-top:20px;
        }

        .split-line{
            height:30px;
            border-right: 1px solid #E6E6E6;
            position: absolute;
            top:5px;
            left:244px;
        }

        .period-btn{
            width:90px;
            height:20px;
            border:1px solid #E6E6E6;
            font-size: 12px;
            color:#9B9B9B;
            border-radius: 10px;
            background-color: transparent;
            margin-top:5px;
        }

        .cancel-btn{
            width:97px;
            height:26px;
            border:none;
            background-color: #CCCCCC;
            border-radius: 14px;
            font-size: 14px;
            color:white;
        }

        .sure-btn{
            width:97px;
            height:26px;
            border:none;
            background-color: #FEA600;
            border-radius: 14px;
            font-size: 14px;
            color:white;
        }
    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div style="position: relative;margin-bottom: 30px;">
        <div class="tabs">
            <div class="col-xs-6 col-md-6 active-tab">
                <div class="tab">大需求变更</div>
            </div>
            <div class="col-xs-6 col-md-6">
                <div class="tab">小细节修改</div>
            </div>
        </div>
        <div class="active-line"></div>
    </div>

    <div class="demand">
        <div class="time-code">
            <span>需求发起时间：&nbsp;2016-03-02  9：30</span>
            <span class="split-line"></span>
            <span style="margin-left:50px;">需求编号：&nbsp;2016030200008</span>
        </div>
        <div class="demand-detail">
            <div class="column1">
                <span style="font-size: 14px;color:#4A4A4A;">更改首页布局</span>
                <button type="button" class="period-btn">需求梳理阶段</button>
            </div>
            <div class="column1">
                <div style="font-size: 12px;color:#9B9B9B;">变更原因:</div>
                <div style="font-size: 12px;">临时产生想法</div>
            </div>
            <div class="column2" style="font-size: 12px;">
                <p><span style="color:#9B9B9B;">追加费用：</span>0</p>
                <p><span style="color:#9B9B9B;">追加时间：</span>0</p>
            </div>
            <div class="column2">
                <p><span style="font-size:14px;color:#2CB7C9;">审核中</span></p>
                <p><a href="#" target="_blank" style="font-size: 12px;">查看详情</a> </p>
            </div>
            <div class="column3">
                <button type="button" class="cancel-btn">取消申请</button>
                <p style="font-size:12px;color:#9B9B9B;margin-top:8px;">项目经理对接中</p>
            </div>
        </div>
    </div>

    <div class="demand">
        <div class="time-code">
            <span>需求发起时间：&nbsp;2016-03-02  9：30</span>
            <span class="split-line"></span>
            <span style="margin-left:50px;">需求编号：&nbsp;2016030200008</span>
        </div>
        <div class="demand-detail">
            <div class="column1">
                <span style="font-size: 14px;color:#4A4A4A;">更改首页布局</span>
                <button type="button" class="period-btn">需求梳理阶段</button>
            </div>
            <div class="column1">
                <div style="font-size: 12px;color:#9B9B9B;">变更原因:</div>
                <div style="font-size: 12px;">临时产生想法</div>
            </div>
            <div class="column2" style="font-size: 12px;">
                <p><span style="color:#9B9B9B;">追加费用：</span>0</p>
                <p><span style="color:#9B9B9B;">追加时间：</span>0</p>
            </div>
            <div class="column2">
                <p><span style="font-size:14px;color:#2CB7C9;">审核中</span></p>
                <p><a href="#" target="_blank" style="font-size: 12px;">查看详情</a> </p>
            </div>
            <div class="column3">
                <p style="font-size:12px;color:#9B9B9B;">收到需求修改确认书</p>
                <button type="button" class="sure-btn">去确认</button>

            </div>
        </div>
    </div>

    <div class="demand">
        <div class="time-code">
            <span>需求发起时间：&nbsp;2016-03-02  9：30</span>
            <span class="split-line"></span>
            <span style="margin-left:50px;">需求编号：&nbsp;2016030200008</span>
        </div>
        <div class="demand-detail">
            <div class="column1">
                <span style="font-size: 14px;color:#4A4A4A;">更改首页布局</span>
                <button type="button" class="period-btn">需求梳理阶段</button>
            </div>
            <div class="column1">
                <div style="font-size: 12px;color:#9B9B9B;">变更原因:</div>
                <div style="font-size: 12px;">临时产生想法</div>
            </div>
            <div class="column2" style="font-size: 12px;">
                <p><span style="color:#9B9B9B;">追加费用：</span>0</p>
                <p><span style="color:#9B9B9B;">追加时间：</span>0</p>
            </div>
            <div class="column2">
                <p><span style="font-size:14px;color:#2CB7C9;">审核中</span></p>
                <p><a href="#" target="_blank" style="font-size: 12px;">查看详情</a> </p>
            </div>
            <div class="column3">
                <span style="font-size:12px;">追加成功</span>
            </div>
        </div>
    </div>



    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>