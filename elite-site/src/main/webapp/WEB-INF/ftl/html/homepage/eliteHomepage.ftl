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
        .focusprt {
        list-style:none;
        margin:0;
    }
    .focusprt li {
        display:inline-block;
        margin:0 10px;
    }
    .clock {
	  display:block;
	  width:20px;
	  height:20px;
	  margin-top:10px;
	  background:url(/res/images/clock.png) no-repeat center;
	}
    
    .focusprt li a {
        display:inline-block;
        color:#9b9b9b;
        font-size:12px;
    }
    .focusprt li span {
        width:16px;
        height:17px;
        display:inline-block;
        margin-right:5px;
        vertical-align:middle;
    }
    .focus {
        background:url(/res/images/focus.png);
    }
    .part {
        background:url(/res/images/participate.png);
    }
    .tet {
      font-size:16px;
      color:#a4a4a4a;
      margin:0 0 5px 0;
    
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
            width:60%;
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
       
       .project-logos{
            width:105px;
            height:105px;
            line-height: 105px;
            text-align: center;
            background-color: #4198c7;
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

        .status {
            float:left;
            width:18%;
            height:146px;
            border-right: 1px solid #E6E6E6;
            font-size: 12px;
            color:#9B9B9B;
            text-align: center;
        }
        .view {
          color:#4a90e2;
          text-decoration:none;
        }
        .opt{
            float:left;
            width:22%;
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
      .complete-btns {
            /*width:90px;
            height:26px;*/
            padding:5px 10px;
            background-color: #fea600;
            border:none;
            border-radius: 41px;
            color:white;
            font-size: 14px;
        }
        .complete-btnss {
        	background:#ccc; 
    	}
        .opt-title{
            display: inline-block;
            cursor: pointer;
        }
        
    .rcmd {
        display:block;
        padding:3px 10px;
        color:#ccc;
        font-size:14px;
        border-radius:14px;
        position:relative;
    }
    .rcmds {
    	width:90px;
    	height:26px;
    	line-height:26px;
    	margin:auto;
    	border:1px solid #fea600;
    	color:#fea600;
    	border-radius:14px;
    	
    }
    .rcmd-txt {
      display:block;
      width:14px;
      height:14px;
      border-radius:50%;
      color:#FFF;
      background-color: #fea600;
      position: absolute;
      top:-5px;
      right:30px;
    }
     .content{
          width:1000px;
          min-height:500px;
          margin-left:auto;
          margin-right:auto;
          position: relative;
      }

      .location-nav{
          padding-top:20px;
          margin-bottom: 20px;
      }
      .left-column{
          width:240px;
          border:1px solid #E6E6E6;
          border-radius: 4px;
          text-align: center;
          background-color: #FAFAFA;
          box-shadow: 2px 2px 6px 0px rgba(0,0,0,0.30);
          margin-bottom: 20px;
      }

      .column-head{
          width: 80px;
          height: 80px;
          background:url("/res/images/test.jpg") no-repeat;
          background-size: 80px 80px;
          border: 1px solid #979797;
          -moz-border-radius: 50px;
          -webkit-border-radius: 50px;
          border-radius: 50px;
      }

      .circle-box{
          padding-left:80px;
          padding-top:20px;
          position: relative;
      }

      .username{
          margin-top:14px;
      }

      .split-line{
          width:100%;
          border-bottom: 1px solid #E6E6E6;
      }

      .left-menu li{
          cursor: pointer;
          position: relative;
      }
menu-icon
      .left-menu li>*{
          display: inline-block;
      }
      
      .menu-icon{
          display:inline-block;
          width:20px;
          height:20px;
          /*background-color: red;*/
          vertical-align: middle;
          margin-right:15px;
      }

      .active-menu{
          color:#2CB7C9;
      }

      .right-opt{
          float:right;
          width:730px;
      }
    .slider {
        display:inline-block;
        width:50px;
        height:20px;
        background:#ccc;
        border-radius:30px;
        position:relative;
        cursor:pointer;
        margin-left:10px;
        vertical-align: middle;
        overflow:hidden;
    }
    .slider .fast {
        display:block;
        width:50px;
        height:20px;
        border-radius:30px;
        background:green;
        transition:all 1s;
        position:absolute;
        top:0;
        left:-30px;
        /*z-index:-1;*/
    }
    .slider .slippage {
        display:block;
        width:20px;
        height:20px;
        border-radius:50%;
        background:#fff;
        transition:all 1s;
        position: absolute;
        top:0;
        right:0;
    }
    
    .slider:hover .fast  {
        left:0px;
    }
    .concerns {
        background:#fea600;
        width:20px;
        height:20px;
        line-height:13px;
        border-radius:50%;
        padding:6px;
        color:#fff;
        position: absolute;
        top:7px;
        right:73px;
    }
    .al {
        color:#2cb7c9;
        font-size:12px;
        margin:10px 0;
    }
    .afine li {
        display:inline-block;
        color:#9B9B9B;
        font-size:12px;
         margin:10px 0;
    }
    .afine .fine {
        margin-left:17px;  
    }
    .sortf {
        margin-bottom:10px;
    }
      .index-title{
          width:33%;
          float:left;
          color:#B3B3B3;
          font-size: 12px;
      }
      .index-data{
          width:33%;
          float:left;
          font-size: 12px;
      }

      .head-label{
          position: absolute;
          top:20px;
          right:56px;
          z-index: 200;

          width:44px;
          height:18px;
          line-height: 18px;
          text-align: center;
          background-color: #FEA600;
          color:white;
          font-size: 12px;
          border-radius: 10px;
      }
      .gold-use {
          width:19px;
          height:21px;
          position:absolute;
          top:21px;
          left:155px;
      }
      .gold-user{
          position: absolute;
          top:20px;
          right:30px;
          width:19px;
          height:21px;
      }
      .separator{
          height:22px;
          border-right: 1px solid #E6E6E6;
          position: absolute;
          top:0;
          left:110px;
      }

      .current-status{
          font-size: 12px;
          position: relative;
          margin-top:5px;
      }

      .open-or-close{
          width:50px;
          height:20px;
          vertical-align: sub;
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

      .message-circle{
          display: block;
          width: 16px;
          height: 16px;
          line-height: 16px;
          text-align: center;
          -moz-border-radius: 50px;
          -webkit-border-radius: 50px;
          border-radius: 50px;
          background-color: #FEA600;
          color:white;
          font-size: 12px;

          position: absolute;
          top:4px;
          right:50px;
      }

      .nav-menus{
          position: absolute;
          top:0;
          right:0;
          z-index: 1000;

          width:121px;
          height:208px;
      }

      .nav-menus ul li{
          text-align: center;
          font-size: 14px;
          color:white;
          line-height: 41px;
          background-color: #000;
          opacity: 0.5;
          filter: alpha(opacity=50);
          -moz-opacity: 0.5;
      }

      .active-nav-menu{
          opacity: 0.8!important;
          filter: alpha(opacity=80)!important;
          -moz-opacity: 0.8!important;
      }
      .statu {
      color:#d0021b;
    }
    .opt-tai {
      color:#4a4a4a;
       padding:0 21px;
    }
    .opt-tai .tai {
      display:inline-block;
      width:16px;
      height:16px;
      margin-right:5px;
      background:url(/res/images/Group.png) no-repeat center;
    }
    
     .sheape {
      display: inline-block;
      width:16px;
      height:16px;
      vertical-align: top;
      margin-right: 5px;
      background:url(/res/images/shpecopy.png) no-repeat center;
    }
	.inde {
		margin:0;
	}
	
	
	/*我的资料 右边区域*/
.data {
	width:730px;	
	margin:0 auto;
	background:#fafafa;
	position:relative;
}
.data .data-logo {
	width:240px;
	height:194px;
	position: relative;
	position:absolute;
	top:149px;
	left:50%;
	margin-left:-120px;
}
.data .data-logo img {
	display:block;
	width:120px;
	height:120px;
	border-radius:50%;
	margin:0 auto;
}
.data .data-logo p {
	text-align:center;
}
.data .data-logo .name {
	font-size:2rem;
	margin:10px 0;
}
.data .data-logo .badge {
	position:absolute;
	top:0;
	right:0;
}
.data .data-logo .dui,
.data .data-logo .huizan {
	display:inline-block;
	width:18px;
	height:20px;
	margin:0 5px;

}
.data .data-logo .dui {
	background:url(/res/images/dui.png) no-repeat center;
}
.data .data-logo .huizan {
	background:url(/res/images/huizan.png) no-repeat center;
}
.data .data-logo .name >span {
	display:inline-block;
	width:18px;
	height:18px;
	vertical-align:text-bottom;
	background:url(/res/images/nv.png) no-repeat;
}
.data .data-logo .maps {
	font-size:.8rem;
	color:#999;
}
.data .data-logo .map {
	display:inline-block;
	width:15px;
	height:20px;
	margin:0 5px;
	vertical-align:middle;
	background:url(/res/images/map.png) no-repeat center;
}





.data .b-content-a{
    width: 701px;
    height: 446px;
    padding-top: 172px;
    margin: 0 auto;
}

.data .b-content-b{
    width: 701px;
    margin: 20px auto;
}
.data .b-content-c{
    width: 645px;
    height: 267px;
    margin: 0 auto;
}

.data .line-box{
    width: 560px;
    height: 36px;
    margin: 0 auto;
    position:relative;
}
.data .line{
    width: 200px;
    height: 18px;
    border-bottom: 1px solid #95d6e2;
    line-height: 36px;
    display: block;
    float: left;
}
.data .line-btn{
    width: 120px;
    height: 36px;
    border-radius: 100px;
    background: #7dd2e1;
    color: #fff;
    font-size: 16px;
    display: block;
    float: left;
    line-height: 36px;
    text-align: center;
    margin-left: 20px;
    margin-right: 20px;
}

.data .idbox{
    width: 150px;
    height: 105px;
    border: 1px dashed #ccc ;
    background: #fff;
    cursor: pointer;
    display: block;
    margin-right: 8px;
}
.data .idlogo{
    width: 30px;
    height: 30px;
    display: block;
    background: url("/res/images/idlogo.png");
    margin: 22px 60px 16px;
}
.data .idtxt{
    font-size: 14px;
    color: #9b9b9b;

}
.data .c-logo{
    width: 15px;
    height: 20px;
    display: block;
    background: url("/res/images/c-logo.png");
    float: right;
}
.data .c-tite{
    display: block;
    float: right;
}
.data .c-city{
    display: block;
    float: right;
}

.data .box-logo-b {
    margin: auto;
    width: 72px;
    padding-top: 16px;
    padding-bottom: 11px;
}
.data .cy-l{
    display: block;
    float: left;
    margin-right: 80px;
}
.data .cy-c{
    display: block;
    float: left;
}
.data .cy-r{
    display: block;
    float: left;
    margin-right: 280px;
}

.data .box-txt {
	overflow:hidden;
	padding:25px 0;
}
.data .box-txt-left {
	width:70%;
	float:left;
	padding-left:14px;
	border-right:1px solid #e0e0e0;
}


.data .work-label{
    font-size: 12px;
    display: inline-block;
}

.data .title-label{
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

.data .title-grade{
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
    top:0px;
    right:0;
}

.data .index{
	color:#9B9B9B;
	font-size: 12px;
}

.data .value{
	padding:5px 10px;
	display:inline-block;
	margin: 5px;
	background:#e0e0e0;
	border-radius:6px;
    color:#4A4A4A;
}

.data .opt-btn{
    padding:4px 20px;
    background-color: #FEA600;
    border:none;
    border-radius: 14px;
    color:white;
    font-size: 14px;
    margin-right: 6px;
}

.data .person-name{
    display: inline-block;
    width:63px;
}
.data .find-btn{
    width:300px;
    height:50px;
    border:1px solid #95D6E2;
    background-color: white;
    border-radius: 36px;
    color:#95D6E2;
}
.data .box-txt-right {
	width:25%;
	float:right;
}
.data .box-txt-right p {
	margin:10px 0;
}
.data .editor {
	display:inline-block;
	width:40px;
	height:22px;
	
	vertical-align:middle;
}
.data .editr {
	background:url(/res/images/bier.png) no-repeat center ;
}
.data .editrr {
	background:url(/res/images/icteo.png) no-repeat center ;
}
.data .c-box-rt {
	position: absolute;
	top:9px;
	right:-71px;
}
.data .box-txt-righta {
    text-align: right;
}
.data .pbtn {
	margin-bottom:15px

}
.data .bot-txte ol {
	padding-left:20px;
}
.data .bot-txte ol li {
	margin:10px 0;
	color:#9b9b9b;
}
.data .certificate ul li {
	display:inline-block;
}
.data .idlogo {
	background:url(/res/images/ff.png);
}

	
	
	.settlement ul,.settlement li {
	list-style:none;
	display:inline-block;
}
.settlement {
	width:730px;
	margin:0 auto;
}
.settlement-header {
	width:95%;
	height:90px;
	padding:20px;
	border-radius:6px;
	background:#f5fbfc;
}
 .record-left-box {
	overflow: hidden;
}
.settlement .header-left {
	width:30%;
	float:left;
	padding:18px 0;
	border-right:1px solid #ccc;
}
.settlement .header-left p,
.settlement .header-right .right-lt p {
	margin:5px 0;
}
 .settlement .gray {
	color:#9b9b9b;
	font-size:1.2rem;
}
.settlement .money {
	font-size:1.4rem;
	color:#333;
}
.settlement .header-right {
	width:66%;
	padding:18px 0 18px 20px;
	float:right;
}
.settlement .header-right .right-lt {
	width:50%;
	float:left;
}
.settlement .header-right .right-rt {
	width:50%;
	float:right;
	margin:0 auto;
}
.settlement .right-rt .opt-btnt {
    padding:10px 26px;
    background-color: #FEA600;
    border:none;
    border-radius:50px;
    font-size: 14px;
    color:white;
    margin:0 10px;
}
.settlement .settlement-record {
	width:100%;
	margin-top:39px;
}

.record-left {
	width:50%;
	float:left;
}
.settlement hr {
	width:730px;
	margin:10px 0;
}
.settlement .record-left ul li {
	padding:2px 10px;
}
.settlement .record-left ul li a {
	color:#2cb7c9;
	text-decoration:none;
}
.settlement .record-left ul {
	margin-left:58px;
}
.settlement .record-left ul .tixian {
	border-right:1px solid #2cb7c9;
}
.settlement .record-right {
	width:50%;
	float:right;
	text-align:right;
}
.settlement .record-right form select {
	width:162px;
	height:40px;
	padding-left:10px;
	border-radius:6px;
}
.settlement .b-content-b .adds {
	text-align:center;
}

.settlement-details  {
	width:100%;
	padding:10px;
	overflow:hidden;
}
.settlement-detailss {
	background:#f5fbfc;
}
.settlement-details .project-log {
	width:40px;
	float:left;
	text-align:center;
	line-height:40px;
	color:#fff;
	background:green;
}
.settlement-details >ul {
	display:inline-block;
	width:90%;
	line-height:40px;
	margin:0 auto;
}
.settlement-details ul li {
	width:24%;
	text-align:center;
}
.settlement-details .detal {
	width:30px;
	height:20px;
	display:block;
	float:right ;
	line-height:40px;
	color:#4a90e2;
	text-decoration:none;
}
/*灰色字體 14px*/
 .gray {
	color:#9b9b9b;
	font-size:1.2rem;
}


/*結算管理*/
	
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
                <div class="tab">认领中的任务&nbsp;<span class="tab-number">2</span></div>
            </div>
            <div class="col-xs-4 col-md-4">
                <div class="tab">已认领的任务&nbsp;<span class="tab-number">2</span></div>
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
                        <sapn class="clock"></sapn>
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
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status">招募中</span>&nbsp;<span class="value">6人報名</span></p>
                    <p class="index">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                	<p class="index">招标截止:&nbsp;<span style="color:#000;">3</span>天<span style="color:#000;">23</span>时</p>
                    <button type="button" class="complete-btn">我要報名</button>
                    <p class="rcmd" style="font-size: 12px;margin-top:10px;">推荐给他人</p>
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
                        <ul class="focusprt">
							<li><span class="focus"></span><a href="#">关注</a></li>
							<li><span class="part"></span><a href="#">暂参加</a></li>
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
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status">招募中</span>&nbsp;<span class="value">6人報名</span></p>
                    <p class="index">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                	<p class="index">招标截止:&nbsp;<span style="color:#000;">3</span>天<span style="color:#000;">23</span>时</p>
                    <button type="button" class="complete-btn">我要報名</button>
                    <p class="rcmd" style="font-size: 12px;margin-top:10px;">推荐给他人</p>
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
                        <ul class="focusprt">
							<li><span class="focus"></span><a href="#">关注</a></li>
							<li><span class="part"></span><a href="#">暂参加</a></li>
						</ul>
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

       	 <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logos">优</div>
                    <div class="project-info">
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status">招募中</span>&nbsp;<span class="value">6人報名</span></p>
                    <p class="index">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                	<p class="index">已报名</p>
                   
                    <button type="button" class="complete-btns complete-btnss">取消报名</button>
                    <p class="rcmds" style="font-size: 12px;margin-top:10px;">推荐给他人</p>
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
                        <ul class="focusprt">
							<li><span class="focus"></span><a href="#">关注</a></li>
							<li><span class="part"></span><a href="#">暂参加</a></li>
						</ul>
                    </div>
                </div>
            </div>
            <div class="vertical-line"></div>
        </div>

       	 <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logos">优</div>
                    <div class="project-info">
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status statu">招募结束</span></p>
                    <p class="index">Ruth推荐/平台推荐</p>
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                	<p class="opt-tai"><span class="tai"></span>您下手太慢啦，下次快点哦！</p>
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
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>
                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status">进行中</span></p>
                    
                    <a href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div style="padding-top:20px;text-align: center;">
                	
                   
                    <button type="button" class="complete-btns">提交产出物</button>
                    <p class="rcmd" style="font-size: 12px;margin-top:10px;">申请延期</p>
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
            <div class="vertical-line"></div>
        </div>

       	 <div class="project-detail">
            <div class="main-info">
                <div style="padding:20px;">
                    <div class="project-logos">优</div>
                    <div class="project-info">
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status">待验收</span></p>
                    <a class="view" href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
                	 
                    <p class="rcmd" style="font-size: 12px;margin-top:10px;">收到新文件<span class="rcmd-txt">9</span></p>
                    <button type="button" class="complete-btns">查看/提交产出物</button>
                </div>
            </div>
        </div>
    </div>
    
    
    
    <#--                                    去评价                                                               -->
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
                    <div class="project-logos">N</div>
                    <div class="project-info">
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status">质保期</span></p>
                    <a class="view" href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div class="opt-rcd" style="padding-top:20px;text-align: center;">
                	<p class="index inde"><span class="sheape"></span>距质保期结束时间：</p>
					<p class="index">还剩<span style="color:#000;">3</span>天<span style="color:#000;">23</span>时</p>
                    
                    <button type="button" class="complete-btns">去评价</button>
                </div>
            </div>
        </div>
    </div>
    
      <#--                                 双方已评论                                              -->
	
    
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
                    <div class="project-logos">友</div>
                    <div class="project-info">
                        <h1 class="tet">堆糖网Web原型设计</h1>
                        <div><span class="index">所需精英:</span>&nbsp;<span class="value">產品經理</span></div>
                        <div><span class="index">任务类型:</span>&nbsp;<span class="value">web网站</span></div>
                         <div><span class="index">报酬:</span>&nbsp;<span class="value">5000.00元</span></div>
                        <div><span class="index">期望交付日期:</span>&nbsp;<span class="value">2016-11-02</span>
                        <span class="index">(<span style="color:#000;">45</span>个工作日)</span></div>
                    </div>

                </div>
            </div>
            
            <div class="status">
                <div style="padding-top:20px;text-align: center;">
                    <p ><span class="current-status">已完成</span></p>
                    <a class="view" href="#" target="_blank">查看详情</a>
                </div>
            </div>
            <div class="opt">
                <div class="opt-rcd" style="padding-top:20px;text-align: center;">双方已评论
                </div>
            </div>
        </div>
    </div>
	<#--我的 资料 开始-->
	<div class="data">
		<div class="data-log">
			<img src="/res/images/c-box-a.png" alt="">
		</div>
		<div class="data-logo">
			<p class=" badge" style="background:none"><span class="dui"></span><span class="huizan"></span></p>
			<img src="/res/images/box-logo-a.png" alt="頭像">
			<p class="name">Amber <span></span></p>
			<p class="maps">阿里粑粑高級工程師 <span class="map"></span><span>杭州</span></p>
		</div>
		<div class="c-box-b">
	        <div class="box-b-content">
	            <div class="b-content-a">
	                <div class="line-box">
	                    <span class="line"></span>
	                    <span class="line-btn">当前状态</span>
	                    <span class="line"></span>
	                    <p class="c-box-b-rt c-box-rt"><span class="editor editr"></span>编辑</p>
	                </div>
	                <div class="box-txt">
	                  	<div class="box-txt-left">
				                <span class="person-name">擅長領域</span>
				                <div class="work-label">
				                    <div class="title-label">工程师 
				                    	<div class="title-grade">L3</div>
			                    	</div>
				                </div>
				                <div class="work-label">
				                    <div class="title-label">長品 
				                    	<div class="title-grade" style="background-color: #8b572a;">L4</div>
			                    	</div>
				                </div>
				                <div class="work-label">
				                    <div class="title-label">设计师 
				                    	<div class="title-grade" style="background-color: #7FCACB;">L2</div>
				                    </div>
				                </div>
				                <p style="margin:15px 0 15px 66px;">
				                   <span class="value">Web网站</span><span class="value">Web网站</span><span class="value">Web网站</span><span class="value">Web网站</span><span class="value">Web网站</span>
				                </p>
		              		<div style="font-size: 12px;margin-top:14px;">
		                   	 	<p style="margin-bottom: 15px;">
		                            <span>關注行業</span>&nbsp;<span class="value">Web网站</span><span class="value">Web网站</span><span class="value">Web网站</span>
		                   		</p>
		                    	<p style="margin-bottom: 6px;">
		                            <span>相關年限</span>&nbsp;<span>3年</span>
		                    	</p>
		              		</div>
	               		</div>
		               	<div class="box-txt-right">
		               		<p>在職/自由狀態</p>
		               		<p>每週可分配時長 <span >10-30小時</span></p>
		               	</div>
	            	</div>
	            </div>
	            <div class="b-content-b">
	                <div class="line-box" >
	                    <span class="line"></span>
	                    <span class="line-btn">项目经历</span>
	                    <span class="line"></span>
	                    <p class="c-box-b-rt c-box-rt"><span class="editor editrr"></span>添加</p>
	                </div>
	                <div class="box-txt" style="border-bottom:1px dashed #999;">
	                  	<div class="box-txt-left" style="border:none">
				                <p class="pbtn">“最亮的明天”暖灯行动 项目负责人</p>
				                <p class="pbtn">产品经理</p>
	               		</div>
		               	<div class="box-txt-right box-txt-righta">
		               		<p class="pbtn"><span class="editor editr"></span>编辑</p>
		               		<p class="pbtn"><span>2012.03</span>-<span>2014.08</span></p>
		               	</div>
	            	</div>
	            	<div class="bot-txte">
	            		<ol>
	            			<li>主导QQ邮箱</li>
	            			<li>参与讨论</li>
	            			<li>版本上线后的推广工作</li>
	            			<li>持续优化已有功能</li>
	            		</ol>
	            	</div>
	            </div>
	            <div class="b-content-b">
	                <div class="line-box">
	                    <span class="line"></span>
	                    <span class="line-btn">教育经历</span>
	                    <span class="line"></span>
	                    <p class="c-box-b-rt c-box-rt"><span class="editor editrr"></span>添加</p>
	                </div>
	                <div class="box-txt">
	                  	<div class="box-txt-left" style="border:none">
				                <p class="pbtn education">清华北大美术学院
				                本科</p>
	               		</div>
	               			<div class="box-txt-right box-txt-righta">
		               		<p class="pbtn"><span class="editor editr"></span>编辑</p>
		               		<p class="pbtn"><span>2012.03</span>毕业></p>
		               	</div>
	            </div>
	            <div class="b-content-b">
	                <div class="line-box clearfloat marginbottom50">
	                    <span class="line"></span>
	                    <span class="line-btn">征信信息</span>
	                    <span class="line"></span>
	                    <p class="c-box-b-rt c-box-rt"><span class="editor editrr"></span>添加</p>
	                </div>
	                <div class="box-txt">
	                    <div class="pbtn"><span class="marginright20">身份证号</span><span class="marginright20">3210*********2690</span><span>*胜利</span></div>
	                    <div class="certificate">
	                        <p class="pbtn ">证件上传</p>
	                        <ul style="text-align:center">
	                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">身份证</span></div></li>
	                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">身份证</span></div></li>
	                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">身份证</span></div></li>
	                            <li><div class="idbox"><span class="idlogo"></span><span class="idtxt">身份证</span></div></li>
	                        </ul>
	                    </div>
	                </div>
	            </div>
	            <div class="b-content-c">
	                <div class="line-box clearfloat marginbottom50">
	                    <span class="line"></span>
	                    <span class="line-btn">账号设置</span>
	                    <span class="line"></span>
	                </div>
	                <div class="box-txt">
	                    <div class="pbtn"><span class="marginright20">支付宝账号</span><span></span></div>
	                    <div class="pbtn"><span class="marginright20">收款银行卡账号绑定</span><span class="marginright15">中国建设银行</span><span>622871******789008479</span></div>
	                    <div class="pbtn"><span class="marginright20">邮箱</span><span>18601651637@163.com</span></div>
	                    <div class="pbtn"><span class="marginright20">手机号</span><span>186****2690</span></div>
	                    <div class="pbtn"><span class="marginright20">密码设置</span><span></span></div>
	                </div>
	
	            </div>
	        </div>
	</div>
<#--我的 资料结束-->

</div>

<!-- 結算管理 -->
	<div class="settlement">
		<div class="settlement-header">
			<div class="header-left">
				<p class="gray">總收益&nbsp;(含稅: <span>13400.00</span>)</p>
				<p ><span class="money">400.00</span>元</p>
			</div>
			<div class="header-right">
				<div class="right-lt">
					<p class="gray">賬戶餘額&nbsp;(凍結的資金: <span>13400.00</span>)</p>
					<p ><span class="money">4000.00</span>元</p>
				</div>
				<div class="right-rt">
					<button type="button" class="opt-btnt">提現</button>
					<button type="button" class="opt-btnt">充值</button>
				</div>
			</div>
		</div>
		<div class="settlement-record">
			<p class="rcrd">結算記錄</p>
			<hr>
			<div class="record-left-box">
				<div class="record-left">
					<span>全部</span>
					<ul>
						<li class="tixian"><a href="javascript:;">提現記錄</a></li>
						<li><a href="javascript:;">充值記錄</a></li>
					</ul>
				</div>
			
				<div class="record-right">
					<form action="">
						選擇項目：
						<select name="cars" id="">
							<option value="全部">全部</option>
							<option value="全部是">全部是</option>
							<option value="全部">全部</option>
							<option value="全部是">全部是</option>
						</select>
					</form>
				</div>
		</div>
		<div class="box-details">
			<div class="settlement-details settlement-detailss">
				<div class="project-log">堆</div>
				<ul>
					<li class="gray"><span >2016-03-02</span>&nbsp;<span>9:30</span></li>
					<li>堆糖网原型设计</li>
					<li><span style="color:#fea600">+ 9000.0</span></li>
					<li><span class="gray">待项目方托管</span></li>
				</ul>
				<a class="detal" href="javascript:;">详情</a>
			</div>
			<div class="settlement-details ">
				<div class="project-log">堆</div>
				<ul>
					<li class="gray"><span >2016-03-02</span>&nbsp;<span>9:30</span></li>
					<li>堆糖网原型设计</li>
					<li><span style="color:#fea600">+ 9000.0</span></li>
					<li><span class="gray">进行中</span></li>
				</ul>
				<a class="detal" href="javascript:;">详情</a>
			</div>
			<div class="settlement-details settlement-detailss">
				<div class="project-log">堆</div>
				<ul>
					<li class="gray"><span >2016-03-02</span>&nbsp;<span>9:30</span></li>
					<li>堆糖网原型设计</li>
					<li><span style="color:#fea600">+ 9000.0</span></li>
					<li><span class="gray">待项目方托管</span></li>
				</ul>
				<a class="detal" href="javascript:;">详情</a>
			</div>
		</div>
	</div>
<!-- 結算管理 -->


    <#--<#include "/rightFloat.ftl">-->
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>