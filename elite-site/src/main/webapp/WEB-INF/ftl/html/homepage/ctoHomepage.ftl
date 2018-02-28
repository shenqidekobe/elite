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
            vertical-align: text-bottom;
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
            margin-top:10px;
            
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

        .opt-title{
            display: inline-block;
            cursor: pointer;
        }

        .show-icon{
            width:16px;
            height:16px;
            cursor: pointer;
            display: inline-block;
            /*vertical-align: text-bottom;*/
        }

        .evaluate-btn{
            width:90px;
            height:26px;
            border:1px solid #FEA600;
            border-radius: 13px;
            color:#FEA600;
            background-color: white;
        }

        .cancel-bid-btn{
            width:90px;
            height:26px;
            background-color: #CCCCCC;
            border-radius: 14px;
            border:none;
            color:white;
            font-size: 14px;
        }

        .exclamation-mark{
            width:3px;
            height:16px;
        }
		      .logo-blue {
            background:#4198c7;
        }
        .yanqi {
            text-align:center;
            font-size:12px;
            color:#9b9b9b;
            margin-top:10px;
            position: relative;
            
        }

        .numb {
             background:#f8cc55;
        }


     .file {
        display:block;
        width:14px;
        height:14px;
        background:#fea600;
        border-radius:50%;
        color:#fff;
        position:absolute;
        top:-8px;
        right:12px;

     }
     .clock {
        display:block;
        width:20px;
        height:20px;
        margin-top:10px;
        background:url(/res/images/clock.png) no-repeat center;
     }
      .hand {
        background:#719898;
     }
     .to {
        background:#66c89c;
     }
     
     .crtsts {
        color:#ff0000;
     }
     .cbtn {
        width:108px;
        padding:5px 10px;
        border-radius:41px;
        border:1px solid #fea600;
        color:#fea600;
        background:none;
     }
     .ims {
        font-size:0;
        position:relative;
     }
    .imsa {
        margin-left:-33px;
     }
     .imsb {
        position:absolute;
        top:0;
        left:50px;
     }
    .rd {
        background:#F08080; 
    }

     .toss {
        background:#666795;
     }
    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div style="position: relative;">
        <div class="tabs">
            <div class="col-xs-4 col-md-4 active-tab">
                <div class="tab">推荐给我的&nbsp;<span class="tab-number">2</span></div>
            </div>
            <div class="col-xs-4 col-md-4">
                <div class="tab">竞标项目&nbsp;<span class="tab-number">2</span></div>
            </div>
            <div class="col-xs-4 col-md-4">
                <div class="tab">中标项目&nbsp;<span class="tab-number">2</span></div>
            </div>
        </div>
        <div class="active-line"></div>
    </div>

    <!-- 推荐给我的 -->
    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    <div class="pull-right">
                        <ul class="focusprt">
							<li><span class="part"></span><a href="#">暂不参加</a></li>
						</ul>
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
                <div style="padding:20px 21px;">
                    <p>Ruth推荐/平台推荐</p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">待竞标</p>
                    <p>已有<span>6</span>人竞标</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <div>
                        <img src="/res/images/clock_icon.png" class="show-icon">
                        &nbsp;&nbsp;<span style="font-size: 12px;"><span style="color:#9B9B9B;">招标截止：</span>3天23时</span>
                    </div>
                    <div style="margin-top:10px;">
                        <button type="button" class="complete-btn">去竞标</button>
                    </div>

                    <div style="margin-top:10px;">
                        <button type="button" class="evaluate-btn" style="width:110px;font-size: 14px;">推荐给他人</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- 已改 -->
<!-- 推荐给我的 -->



<!-- 竞标项目  -->
    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>
        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo logo-blue">优</div>
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
            	<div style="padding:20px 18px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                </div>
            </div>
             <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">已竞标</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <div>
                        <img src="/res/images/clock_icon.png" class="show-icon">
                        &nbsp;&nbsp;<span style="font-size: 12px;"><span style="color:#9B9B9B;">招标截止：</span>3天23时</span>
                    </div>
                    <div style="margin-top:10px;">
                        <button type="button" class="cancel-bid-btn">取消投标</button>
                    </div>

                    <div style="margin-top:10px;">
                        <button type="button" class="evaluate-btn" style="width:110px;font-size: 14px;">推荐给他人</button>
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
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo logo-blue">优</div>
                    <div class="project-info">
                        <div>
                            <span class="project-name">优界网</span>
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
            	<div style="padding:20px 18px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">未中标</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                    <div>
                        <img src="/res/images/sad_face_icon.png" class="show-icon">
                        &nbsp;<span style="font-size: 12px;">招标截止，再接再厉！</span>
                    </div>
                    <button type="button" class="complete-btn" style="margin-top:10px;">查看原因</button>
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
               
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">放弃项目</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding:20px 10px;">
                    <img src="/res/images/exclamation_mark.png" class="exclamation-mark">
                    &nbsp;<span style="font-size: 12px;">中标取消后会<span style="color:#FEA600;">影响你的诚信度，</span>下次请非常谨慎。</span>
                </div>
            </div>
        </div>
    </div>
 <!-- 竞标项目  -->

 <!-- 中标项目 -->
    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    
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
                        <div><span class="index">我的竞标价格:</span>&nbsp;<span class="value">20万</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding:20px 18px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p class="current-status">已中标</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding:20px 10px;">
                    <div>
                        <img src="/res/images/smile_face_icon.png" class="show-icon">
                        &nbsp;<span style="font-size: 12px;">恭喜中标!</span>
                    </div>
                    <div>
                        <img src="/res/images/clock_icon.png" class="show-icon">
                        &nbsp;<span style="font-size: 12px;"><span style="color:#9B9B9B;">还剩</span>3天23时</span>
                    </div>
                    <button type="button" class="complete-btn" style="margin-top:10px;width:130px;">去确认项目立项书</button>
                </div>
            </div>
        </div>
    </div>

    下面还需要加右图
    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    <div class="pull-right">
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo logo-blue">优</div>
                    <div class="project-info">
                        <div>
                            <span class="project-name">优界网</span>
                            <div class="class-label">电商</div>
                            <div class="class-label">社交</div>
                        </div>
                        <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                        <div><span class="index">我的中标价格:</span>&nbsp;<span class="value">240万</span></div>
                        <div><span class="index">本阶段费用:</span>&nbsp;<span class="value">2000</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                        <div><span class="index">（<span style="color:#000;">45</span>个工作日）</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding:20px 10px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">      
                    <p class="current-status">需求梳理阶段</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding:20px 10px;">
                    <button type="button" class="complete-btn" style="margin-top:10px;width:130px;">分配任务</button>
                    <p class="yanqi">申请延期</p>
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
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo numb">9</div>
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
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                	<img src="/res/images/oval.png" alt="">   
                    <p class="current-status">需求梳理阶段</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
           <div class="opt">
                <div style="padding:20px 10px;">
                	<p class="yanqi">收到新文件 <span class="file">3</span></p>
                    <button type="button" class="complete-btn" style="margin-top:10px;width:130px;">查看/提交产出物</button>
                    <p class="yanqi">申请延期</p>
                </div>
            </div>
        </div>
    </div>
    <!-- 致设计 -->
    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                 <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    <div class="pull-right">
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo numb hand">手</div>
                    <div class="project-info">
                        <div>
                            <span class="project-name">致设计</span>
                            <div class="class-label">电商</div>
                            <div class="class-label">社交</div>
                        </div>
                        <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                        <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                        <div><span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding:20px 10px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                	<img src="/res/images/oval.png" alt="">
                    <p class="current-status">需求梳理阶段&nbsp;
                    待验收</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
           <div class="opt">
                <div style="padding:20px 10px;">
                	<p class="yanqi">项目方验收中</p>
                    <button type="button" class="complete-btn" style="margin-top:10px;width:130px;">查看/提交产出物</button>
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
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo numb to">致</div>
                    <div class="project-info">
                        <div>
                            <span class="project-name">致设计</span>
                            <div class="class-label">电商</div>
                            <div class="class-label">社交</div>
                        </div>
                        <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                        <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                        <div><span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding:20px 10px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                	<img src="/res/images/oval.png" alt="">
                    <p class="current-status crtsts">需求梳理阶段&nbsp;
                    待验收</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
           <div class="opt">
                <div style="padding:20px 10px;">
                	<p class="yanqi">待项目方托管下一阶段费用</p>
                    <button type="button" class="cbtn" style="margin-top:10px;">评价精英</button>
                </div>
            </div>
        </div>
    </div>
<!-- 致设计 -->
    <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                 <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    <div class="pull-right">
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo numb toss">设</div>
                    <div class="project-info">
                        <div>
                            <span class="project-name">优界网</span>
                            <div class="class-label">电商</div>
                            <div class="class-label">社交</div>
                        </div>
                        <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                        <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                        <div><span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding:20px 10px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                	
                    <p class="current-status">已结顶</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
           <div class="opt">
                <div style="padding:20px 10px;">
                    <button type="button" class="complete-btn" style="margin-top:40px;">查看评价</button>
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
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo royal">优</div>
                    <div class="project-info">
                        <div>
                            <span class="project-name">优界网</span>
                            <div class="class-label">电商</div>
                            <div class="class-label">社交</div>
                        </div>
                        <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                        <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                        <div><span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding:20px 10px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                	
                    <p class="current-status crtsts">顶项终止</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding:20px 10px;">
                	<p class="yanqi">终止原因：资金问题</p>
                    <button type="button" class="cbtn" style="margin-top:10px;">评价精英</button>
                </div>
            </div>
        </div>
    </div>
	<!-- 友 -->
   <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                 <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    <div class="pull-right">
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo ">友</div>
                    <div class="project-info">
                        <div>
                            <span class="project-name">优界网</span>
                            <div class="class-label">电商</div>
                            <div class="class-label">社交</div>
                        </div>
                        <div><span class="index">开发类型:</span>&nbsp;<span class="value">PC网站+移动端App</span></div>
                        <div><span class="index">预算:</span>&nbsp;<span class="value">20-40万</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span></div>
                        <div><span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            <div class="status">
                <div style="padding:20px 10px;">
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
	               		<div class="ims">                	
	               		<img class="imsa" src="/res/images/oval.png" alt="">
	                	<img class="imsb" src="/res/images/oval.png" alt="">
                	</div>

                    <p class="current-status">界面设置阶段</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
           <div class="opt">
                <div style="padding:20px 10px;">
                    <button type="button" class="complete-btn" style="margin-top:10px;width:130px;">分配任务</button>
                	<p class="yanqi">申请延期</p>
                </div>
            </div>
        </div>
   </div>
   <!-- 手 -->
   <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                 <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    <div class="pull-right">
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo hand">手</div>
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
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
	               		<div class="ims">                	
	               		<img class="imsa" src="/res/images/oval.png" alt="">
	                	<img class="imsb" src="/res/images/oval.png" alt="">
                	</div>

                    <p class="current-status">界面设置阶段</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
           <div class="opt">
                <div style="padding:20px 10px;">
                	<p class="yanqi">收到新文件 <span class="file">3</span></p>
                    <button type="button" class="complete-btn" style="margin-top:10px;width:130px;">查看/提交产出物</button>
                </div>
            </div>
        </div>
   </div>
    <!-- 合 -->
   <div class="release-list">
        <div style="position: relative;">
            <div class="part1">
                <div class="col-xs-4 col-md-4">
                    <span>创建时间：2016-03-02  9：30</span>

                </div>
                 <div class="col-xs-8 col-md-8">
                    <span class="pull-left">项目编号：2016030200008</span>
                    <div class="pull-right">
                        <sapn class="clock"></sapn>
                    </div>
                </div>
            </div>
            <div class="vertical-line">
            	
            </div>
        </div>

        <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logo rd">合</div>
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
                    <p>标书承诺书:<br><span>20000(10%)</span></p>
                
                    <p>累计托管金额:<br><span>20000</span></p>
                </div>
            </div>
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
	               		<div class="ims">                	
	               		<img class="imsa" src="/res/images/oval.png" alt="">
	                	<img class="imsb" src="/res/images/oval.png" alt="">
                	</div>

                    <p class="current-status">界面设置阶段</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
           <div class="opt">
                <div style="padding:20px 10px;">
                	<p class="yanqi">项目方验收中</p>
                    <button type="button" class="complete-btn" style="margin-top:10px;width:130px;">查看/提交产出物</button>
                </div>
            </div>
        </div>
   </div>
 <!-- 中标项目 -->
</div>

    <#--<#include "/rightFloat.ftl">-->
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>