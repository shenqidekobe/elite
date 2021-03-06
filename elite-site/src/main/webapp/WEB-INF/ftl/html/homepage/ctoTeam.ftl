<@extend name="homepageLayout">
    <@block name="cs">
    <style type="text/css">
        .tabs{
            width:100%;
            height:40px;
            text-align: center;
            font-size: 18px;
            border-bottom: 1px solid #B3B3B3;
            margin-bottom: 30px;
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
            cursor: pointer;
        }

        .person-box{
            width:100%;
            overflow: hidden;
            height:140px;
            border:1px solid #E6E6E6;
            border-radius: 6px;
            margin-bottom: 20px;
        }

        .column1{
            width:66%;
            height:140px;
            float:left;
            border-right: 1px solid #E6E6E6;
            background-color: #FAFAFA;
        }

        .column2{
            width:34%;
            height:140px;
            float:left;
            background-color: #F5FBFC;
        }

        .person-logo{
            width: 100px;
            height: 100px;
            background:url("/res/images/test.jpg") no-repeat;
            background-size: 100px 100px;
            border: 1px solid #979797;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            float:left;
        }

        .person-index{
            float:left;
            margin-left:20px;
        }

        .work-label{
            font-size: 12px;
            display: inline-block;
        }

        .title-label{
            width:65px;
            height:20px;
            line-height: 20px;
            border:1px solid #CCCCCC;
            border-radius: 10px;
            color:#9B9B9B;
            text-align: left;
            padding-left:5px;
            position: relative;
        }

        .title-grade{
            width: 20px;
            height: 20px;
            line-height: 20px;
            text-align: center;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            background-color: #4A90E2;
            color:white;

            position: absolute;
            top:-1px;
            right:0;
        }

        .coop-box{
            width:63px;
            height:20px;
            line-height: 20px;
            text-align: center;
            border:1px solid #95D6E2;
            border-radius: 4px;
            font-size: 12px;
            color:#95D6E2;
            display: inline-block;
        }

        .index{
            color:#9B9B9B;
            font-size: 12px;
        }

        .value{
            color:#4A4A4A;
        }

        .opt-btn{
            padding:4px 20px;
            background-color: #FEA600;
            border:none;
            border-radius: 14px;
            color:white;
            font-size: 14px;
            margin-right: 6px;
        }

        .person-name{
            display: inline-block;
            width:100px;
        }
        .find-btn{
            width:300px;
            height:50px;
            border:1px solid #95D6E2;
            background-color: white;
            border-radius: 36px;
            color:#95D6E2;
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
        /*                                       改动                       */

        .opt{
            float:left;
            width:19%;
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
        
        
        
        
        .part11{
			width:100%;
			height:40px;
			line-height: 40px;
			text-align: center;
			font-size: 14px;
			color:#9B9B9B;
			background:#fafafa;
			border-top-left-radius: 5px;
			border-top-right-radius: 5px;
		}
		.project-info {
		    width:78%;
		}
		
		.main-inf{
		    float:left;
		    width:85%;
		    height:146px;
		}
		.ios {
		    width:500px;
		    list-style:none;
		    margin:0;
		    padding:0;
		}
		.ios li {
		    display: inline-block;
		    text-align:center;
		}
		.ios li img {
		    display:block;
		    width:40px;
		    height:40px;
		    border-radius:50%;
		    margin:10px auto;
		}
		
		.main-right {
		    float:right;
		    width:15%;
		}
		.main-right>p {
		    color:#4a90e2;
		    padding-top:75px;
		}

    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
    <div style="position: relative;">
        <div class="tabs">
            <div class="col-xs-4 col-md-4 active-tab">
                <div class="tab">合作过的</div>
            </div>
            <div class="col-xs-4 col-md-4">
                <div class="tab">项目组</div>
            </div>
            <div class="col-xs-4 col-md-4">
                <div class="tab">
                    <div class="input-group">
                        <input type="text" class="form-control" placeholder="请输入角色/姓名/等级" style="font-size: 12px;">
                          <span class="input-group-btn">
                            <button class="btn btn-default" type="button" style="background-color: #FEA600;border:1px solid #FEA600;">
                                <img src="/res/images/search_icon.png" width="20" height="20">
                            </button>
                          </span>
                    </div>
                </div>
            </div>
        </div>
        <div class="active-line"></div>
    </div>

    <div class="person-box">
        <div class="column1">
            <div style="padding:20px;">
                <div class="person-logo"></div>
                <div class="person-index">
                    <div>
                        <span class="person-name">Lisa Martin</span>
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                        <div class="work-label">
                            <div class="title-label">设计师 <div class="title-grade" style="background-color: #7FCACB;">L2</div></div>
                        </div>
                        <div class="coop-box">搭伙1次</div>
                    </div>
                    <div style="font-size: 12px;margin-top:14px;">
                        <p style="margin-bottom: 6px;">
                            <span class="index">擅长领域：</span>&nbsp;<span class="value">Web网站 &nbsp;&nbsp;移动应用</span>
                        </p>
                        <p style="margin-bottom: 6px;">
                            <span class="index">关注行业：</span>&nbsp;<span class="value">社交类 &nbsp;&nbsp;金融类</span>
                        </p>
                        <div>
                            <img src="/res/images/fans_icon.png" width="16" height="16" style="vertical-align:sub;">
                            &nbsp;粉丝数量：&nbsp;<span>136</span>
                            <img src="/res/images/eye_icon.png" width="18" height="12" style="vertical-align:sub;margin-left:20px;">
                            &nbsp;查看次数：&nbsp;<span>136</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="column2">
            <div style="padding:20px 30px 0;">
                <p>
                    <span class="index">正在进行的任务：</span>&nbsp;<span class="value" style="font-size: 14px;">3</span>
                </p>
                <p>
                    <span class="index">每周可共享时长：</span>&nbsp;<span class="value" style="font-size: 14px;">10-30小时</span>
                </p>
                <button type="button" class="opt-btn">关注</button>
                <button type="button" class="opt-btn">邀请干活</button>
            </div>
        </div>
    </div>

    <div class="person-box">
        <div class="column1">
            <div style="padding:20px;">
                <div class="person-logo"></div>
                <div class="person-index">
                    <div>
                        <span class="person-name">Lisa Martin</span>
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                        <div class="work-label">
                            <div class="title-label">设计师 <div class="title-grade" style="background-color: #7FCACB;">L2</div></div>
                        </div>
                        <div class="coop-box">搭伙1次</div>
                    </div>
                    <div style="font-size: 12px;margin-top:14px;">
                        <p style="margin-bottom: 6px;">
                            <span class="index">擅长领域：</span>&nbsp;<span class="value">Web网站 &nbsp;&nbsp;移动应用</span>
                        </p>
                        <p style="margin-bottom: 6px;">
                            <span class="index">关注行业：</span>&nbsp;<span class="value">社交类 &nbsp;&nbsp;金融类</span>
                        </p>
                        <div>
                            <img src="/res/images/fans_icon.png" width="16" height="16" style="vertical-align:sub;">
                            &nbsp;粉丝数量：&nbsp;<span>136</span>
                            <img src="/res/images/eye_icon.png" width="18" height="12" style="vertical-align:sub;margin-left:20px;">
                            &nbsp;查看次数：&nbsp;<span>136</span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="column2">
            <div style="padding:20px 30px 0;">
                <p>
                    <span class="index">正在进行的任务：</span>&nbsp;<span class="value" style="font-size: 14px;">3</span>
                </p>
                <p>
                    <span class="index">每周可共享时长：</span>&nbsp;<span class="value" style="font-size: 14px;">10-30小时</span>
                </p>
                <span style="font-size: 14px;margin-right:10px;">已互相关注</span>
                <button type="button" class="opt-btn">邀请干活</button>
            </div>
        </div>
    </div>
	<div class="release-list">
    <div style="position: relative;">
        <div class="part11">
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
        <div class=" main-inf">
            <div style="padding:20px;">
                <div class="project-logo">堆</div>
               	<div class="project-info">
                    <div>
                        <span class="project-name">堆糖网web設計</span>
                        <span class="index">共<span style="color:#2cb7c9;">45</span>個任務</span>
					</div>
					<ul class="ios">
							<li>
								<img src="/res/images/oval.png" alt="">
								<span>已完成</span>
							</li>
							<li>
								<img src="/res/images/oval.png" alt="">
								<span>进行中</span>
							</li>
							<li>
								<img src="/res/images/oval.png" alt="">
								<span>进行中</span>
							</li>
							<li>
								<img src="/res/images/oval.png" alt="">
								<span>待认领</span>
							</li>
					</ul>	
				</div>
				
            </div>
        </div>
      	<div class="main-right">
      		<p>查看詳情</p>
      	</div>
    </div>
</div>

    <div style="text-align: center;margin-top:40px;">
        <button type="button" class="find-btn">帮手不够？我要找精英</button>
    </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>