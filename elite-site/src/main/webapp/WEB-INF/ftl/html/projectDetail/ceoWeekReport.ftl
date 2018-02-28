<@extend name="homepageLayout">
    <@block name="cs">
    <style type="text/css">
        .report-count{
            font-size: 12px;
            color:#9B9B9B;
            margin-left:20px;
        }

        .keyword{
            color:#2CB7C9;
        }

        .report-box{
            width:100%;
            background-color: #F5FBFC;
            border: 1px solid #E6E6E6;
            box-shadow: 2px 2px 6px 0px rgba(0,0,0,0.20);
            border-radius: 6px;
            padding:20px;
            margin-bottom: 40px;
        }

        .report{
            width:100%;
            height:40px;
            line-height: 40px;
            border:1px solid #E6E6E6;
            border-radius: 4px;
            overflow: hidden;
            position: relative;
            margin-top: 10px;
        }

        .column1{
            width:30%;
            height:40px;
            background-color: #FAFAFA;
            border-right: 1px solid #E6E6E6;
            font-size: 14px;
            padding-left:20px;
            float:left;
        }

        .column2{
            width:70%;
            height:40px;
            padding-left:50px;
            padding-right:40px;
            float:left;
        }

        .opt-btn{
            width:80px;
            height:20px;
            line-height: 18px;
            background-color: #FEA600;
            border:none;
            border-radius: 14px;
            font-size: 12px;
            color:white;
            position: absolute;
            right: 40px;
            top:10px;
        }

        .tag{
            width:40px;
            height:19px;
            position: absolute;
            top:3px;
            left:210px;
        }

        .report-opt{
            position: absolute;
            right: 34px;
            top:0px;
        }

        .opt-icon{
            width:20px;
            height:20px;
            cursor: pointer;
            margin-right: 14px;
        }

    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div>
        <span style="font-size: 18px;">堆糖网项目周报汇总</span>
        <span class="report-count">共收到<span class="keyword">11周</span>周报，其中<span class="keyword">2周</span>未收到周报，未读周报<span class="keyword">2份</span></span>
        <span style="float:right;">
            <img src="/res/images/telephone_small_icon.png" width="20" height="21">&nbsp;项目经理:Tom
        </span>
    </div>
    <hr style="margin-top:10px;">

    <p>2016年05月周报</p>
    <div class="report-box">
        <div class="report" style="margin-top:0;">
            <div class="column1">5.30-6.5 &nbsp;&nbsp;<span style="color:#FEA600;">当前周</span></div>
            <div class="column2">未收到周报</div>
            <button type="button" class="opt-btn">索要周报</button>
        </div>

        <div class="report">
            <div class="column1">5.23-5.29 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/tag_blue_icon.png" class="tag">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>

        <div class="report">
            <div class="column1">5.23-5.29 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>

        <div class="report">
            <div class="column1">5.16-5.22 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/tag_red_icon.png" class="tag">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>
        
        
        <div class="report">
            <div class="column1">5.9-5.15 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>
        
        
        <div class="report">
            <div class="column1">5.2-5.8 &nbsp;&nbsp;<span>周报</span></div>
            <div class="column2">未收到周报</div>
            <button type="button" class="opt-btn">索要周报</button>
        </div>
        
        
        
    </div>

    <p>2016年04月周报</p>
    <div class="report-box">
        <div class="report" style="margin-top:0;">
            <div class="column1">4.30-5.5 &nbsp;&nbsp;<span style="color:#FEA600;">当前周</span></div>
            <div class="column2">未收到周报</div>
            <button type="button" class="opt-btn">索要周报</button>
        </div>

        <div class="report">
            <div class="column1">4.23-4.29 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/tag_blue_icon.png" class="tag">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>

        <div class="report">
            <div class="column1">4.23-4.29 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>

        <div class="report">
            <div class="column1">4.16-4.22 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/tag_red_icon.png" class="tag">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>
        
        <div class="report">
            <div class="column1">4.9-5.5 &nbsp;周报</div>
            <div class="column2">
                <img src="/res/images/paper_icon.png" width="18" height="22" style="vertical-align: sub;">
                <span style="font-size: 14px;">堆糖网项目周报…</span>
                &nbsp;<span style="font-size: 12px;color:#9B9B9B;">5.9  20:25</span>
            </div>
            <div class="report-opt">
                <img src="/res/images/download_row_icon.png" class="opt-icon">
                <img src="/res/images/search_row_icon.png" class="opt-icon">
            </div>
        </div>
        
        <div class="report">
            <div class="column1">4.2-5.5 &nbsp;&nbsp;<span>当前周</span></div>
            <div class="column2">未收到周报</div>
            <button type="button" class="opt-btn">索要周报</button>
        </div>
        
        
        
        
    </div>
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>