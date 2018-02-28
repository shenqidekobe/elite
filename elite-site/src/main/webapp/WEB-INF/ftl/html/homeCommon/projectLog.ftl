<@extend name="homepageLayout">
    <@block name="cs">
    <style type="text/css">
        .log-count{
            font-size: 12px;
            color:#9B9B9B;
            margin-left:20px;
            letter-spacing: 1px;
        }

        .date-select{
            display: inline-block;
            width:100%;
            position: relative;
        }

        .date-icon{
            width:20px;
            height:20px;
            
        }
        
        .date-icon-a{
           
            position: absolute;
            top:7px;
            right:16px;
            z-index: 100;
        }
        
        
        .date-icon-b{
            
            position: absolute;
            top:7px;
            right:31px;
            z-index: 100;
        }
        


        .triangle {
		    position: absolute;
		    right: 7px;
		    cursor: pointer;
		    top: 14px;
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

        .search-btn{
            width:120px;
            height:40px;
            background-color: #FEA600;
            border:none;
            border-radius: 28px;
            color:white;
            display: inline-block;
            vertical-align: bottom;
            margin-left:20px;
        }

        .search-icon{
            width:20px;
            height:20px;
            vertical-align: sub;
        }

        .project-log{
            margin-top:40px;
            padding:14px 20px;
            background: #F5FBFC;
            border: 1px solid #E6E6E6;
            box-shadow: 2px 2px 6px 0px rgba(0,0,0,0.20);
            border-radius: 6px;
            font-size: 12px;
            min-height: 500px;
        }

        .period-btn{
            width:76px;
            height:26px;
            line-height: 26px;
            text-align: center;
            border:1px solid #95D6E2;
            border-radius: 14px;
            font-size: 14px;
            color:#95D6E2;
            position: absolute;
            top:0;
            left:0;
        }

        .log-point{
            width: 10px;
            height: 10px;
            border:1px solid #2CB7C9;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            background-color: #95D6E2;
            z-index: 100;

            position: absolute;
            top:8px;
            left:240px;
        }

        .log{
            position: relative;
            height:30px;
        }

        .log-date{
            position: absolute;
            top:6px;
            left:100px;
        }

        .log-time{
            position: absolute;
            top:6px;
            left:186px;
        }

        .log-content{
            position: absolute;
            top:6px;
            left:270px;
        }

        .link-point{
            position: absolute;
            top:-12px;
            left:245px;
            height:20px;
            border-right:1px solid #CCCCCC;
        }



    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <span style="font-size: 18px;">堆糖网项目日志</span>
    <span class="log-count">共产生<span style="color:#30C3D3;">120</span>条项目日志</span>
    <hr style="margin-top:10px;">

    <div>
        <div style="position: relative;width:180px;display: inline-block;">
            <input type="text" class="form-control" placeholder="全部阶段" style="width:180px;">
            <div class="triangle">
                <div class="trigger"></div>
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

        <div style="display: inline-block;margin-left:20px;">
          <span class="date-select" style="width:140px;">
            <img src="/res/images/date_icon.png" class="date-icon date-icon-a">
            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="起始日期" style="background-color: white;width:140px;"/>
          </span>
            <span>-</span>
          <span class="date-select" style="width:160px;">
            <img src="/res/images/date_icon.png" class="date-icon date-icon-b">
            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="至今" style="background-color: white;width:140px;"/>
          </span>
        </div>

        <div style="margin-top:16px;">
            <input type="text" class="form-control" placeholder="请输入关键字" style="width:310px;display: inline-block;">
            <button type="button" class="search-btn">
                <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
            </button>
        </div>

        <div class="project-log">
            <div class="log">
                <div class="period-btn">需求阶段</div>
                <span class="log-date">2016-04-20</span>
                <span class="log-time">19:50</span>
                <div class="log-point"></div>
                <span class="log-content">我发表 评价 需求梳理阶段</span>
            </div>

            <div class="log">
                <span class="log-time">19:50</span>
                <div class="link-point"></div>
                <div class="log-point"></div>
                <span class="log-content">我发表 评价 需求梳理阶段</span>
            </div>

            <div class="log">
                <span class="log-time">19:50</span>
                <div class="link-point"></div>
                <div class="log-point"></div>
                <span class="log-content">aaaa</span>
            </div>

            <div class="log">
                <span class="log-date">2016-04-20</span>
                <span class="log-time">19:50</span>
                <div class="link-point"></div>
                <div class="log-point"></div>
                <span class="log-content">我发表 评价 需求梳理阶段</span>
            </div>

            <div class="log">
                <span class="log-date">2016-04-20</span>
                <span class="log-time">19:50</span>
                <div class="link-point"></div>
                <div class="log-point"></div>
                <span class="log-content">我发表 评价 需求梳理阶段</span>
            </div>

            <div class="log">
                <div class="period-btn">立项阶段</div>
                <span class="log-date">2016-04-20</span>
                <span class="log-time">19:50</span>
                <div class="link-point"></div>
                <div class="log-point"></div>
                <span class="log-content">我发表 评价 需求梳理阶段</span>
            </div>
            <div class="log">
                <span class="log-date">2016-04-20</span>
                <span class="log-time">19:50</span>
                <div class="link-point"></div>
                <div class="log-point"></div>
                <span class="log-content">我发表 评价 需求梳理阶段</span>
            </div>

        </div>

    </div>


    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>