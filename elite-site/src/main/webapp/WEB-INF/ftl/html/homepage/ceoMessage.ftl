<@extend name="messageLayout">
    <@block name="cs">
    <style type="text/css">
    
        .message-count{
            font-size: 14px;
            color:#9B9B9B;
            margin-left:10px;
        }
        .current-select{
            color:#FEA600;
        }

        .current-count{
            color:#FEA600;
        }

        .right-opt .split-line{
            height:20px;
            border-right: 1px solid #E6E6E6;
            position: absolute;
            top:0;
            left:128px;
        }
        .message-frame{
            margin-bottom:10px;
            position: relative;
        }

        .message-frame img{
            width:732px;
            height:60px;
        }

        .message-select{
            cursor: pointer;
        }

        .message-word{
            position: absolute;
            top:18px;
            left:30px;
            font-size: 14px;
        }

        .message-time{
           position: absolute;
            top:20px;
            right:20px;
            font-size: 12px;
            color:#9B9B9B;
        }

        .project-btn{
            width:88px;
            height:20px;
            line-height: 20px;
            text-align: center;
            border:1px solid #2CB7C9;
            border-radius: 10px;
            font-size: 12px;
            color:#2CB7C9;
            display: inline-block;
            margin-left:40px;
            position: relative;
        }

        .btn-triangle{
            position: absolute;
            top:-1px;
            right:2px;
            cursor: pointer;
        }
        .btn-trigger {
            display: inline-block;
            height: 0;
            width: 0;
            overflow: hidden;
            border-style: solid;
            margin-left: 4px;
            margin-right: 4px;
            border-width:6px 6px 0;
            border-color:#D8D8D8 transparent transparent;
            -webkit-transition:all .4s ease 0s;
            -moz-transition:all .4s ease 0s;
            -o-transition:all .4s ease 0s;
            transition:all .4s ease 0s;
        }
		.proect {
		    width:200px;
		    border:1px solid #ccc;
		    border-radius:6px;
		    list-style:none;
			 position:absolute;
		    top:33px;
		    left:-67px;
		    z-index:2;
		}
		.proect li {
		    background:#ccc;
		    text-align: center;
		}
		.proect li a{
		    display:block;
		    padding:5px;
		    color:#fff;
		    font-size:14px;
		    text-decoration:none;
		}
		.proect li:hover {
		    background:#27b7c9;
		}
        .trigger-inverse{
            -webkit-transform: rotate(180deg);
            -moz-transform: rotate(180deg);
            -ms-transform: rotate(180deg);
            transform: rotate(180deg);
            animation-fill-mode: forwards;
        }

        .project-logo{
            width:40px;
            height:40px;
            line-height: 40px;
            text-align: center;
            font-size: 20px;
            color:white;
            background-color: #EE706B;

            position: absolute;
            top:8px;
            left:26px;
            z-index: 100;
        }
        
         .pagebox {
		    width:269px;
		    float:left;
		    margin:0 auto;
		    overflow: hidden;
		}
		a {
		    text-decoration:none;
		}
		ul,li {
		    list-style:none;
		}
		
		.mbox-left {
		    width:240px;
		    height:230px;
		    float:left;
		    opacity:0.8;
		    filter:alpha(opacity=8);
		    border:1px solid #e6e6e6;
		    border-radius:6px;
		    -moz-border-radius:6px;
		    -webkit-border-radius:6px;
		    box-shadow:2px 2px 6px 0px rgba(0,0,0,0.3);
		}
		.mbox-left ul {
		    text-align:center;
		    margin-top:33px;
		}
		.mbox-left ul li a {
		    display:inline-block;
		    color:#4a4a4a;
		    vertical-align:super;
		    padding:13px 0
		}
		.mbox-left ul li span {
		    display:inline-block;
		    width:22px;
		    height:22px;
		    margin-right:15px;
		}
		.mbox-left ul li .system {
		    background:url(/res/images/system.png) no-repeat center;
		}
		.mbox-left ul li .project {
		    background:url(/res/images/prjt.png) no-repeat center;
		}
		.mbox-left ul li .activity {
		    background:url(/res/images/activity.png) no-repeat center;
		}
		.mbox-left ul li:hover,
		.mbox-left ul .activrt  {
		    border-right:3px solid #2cb7c9;
		}
		.mbox-left ul li:hover a,.mbox-left ul li .activ {
		    color:#2CB7C9;
		}
		.mbox-left ul li:hover .system,
		.mbox-left ul li .activstm {
		    background:url(/res/images/system-1.png) no-repeat center;
		}
		.mbox-left ul li:hover .project,
		.mbox-left ul li .projec {
		    background:url(/res/images/prjt-1.png) no-repeat center;
		}
		.mbox-left ul li:hover .activity,
		.mbox-left ul li .activctivy {
		    background:url(/res/images/activity-1.png) no-repeat center;
		}

    </style>
    </@block>
<#--右边的操作区-->
    <@block name="rightContent">
        <div>
            <span style="font-size: 18px;">系统消息</span>
            <span class="message-count">共3条,未读1条</span>
            <div class="project-btn">全部项目
                <div class="btn-triangle">
                    <div class="btn-trigger">
                    	<ul class="proect">
							<li><a href="#">全部项目</a></li>
							<li><a href="#">全部项目</a></li>
							<li><a href="#">全部项目</a></li>
						</ul>
                    </div>
                </div>
            </div>
        </div>
        <hr style="margin-top:10px;">

        <div style="position: relative;">
            <span class="message-select current-select">未读消息</span>&nbsp;<span class="current-count">2</span>
            <span class="message-select" style="margin-left:100px;">已读消息</span>&nbsp;<span class="current-count">2</span>
            <div class="split-line"></div>
        </div>

        <div style="margin-top:20px;">
            <div class="message-frame">
                <img src="/res/images/message_frame.png">
                <p class="message-word">欢迎来到云英汇，云集天下精英，快去完善个人资料，逐梦前行吧~</p>
                <p class="message-time">2016-03-02  9：30</p>
            </div>
            <div class="message-frame">
                <img src="/res/images/message_frame.png">
                <p class="message-word">云英汇首届助创大会启动，快说出你的梦想~</p>
                <p class="message-time">2016-03-02  9：30</p>
            </div>

            <div class="message-frame">
                <img src="/res/images/message_frame.png">
                <div class="project-logo">堆</div>
                <p class="message-word" style="left:80px;">项目发布成功，待审核，查看详情</p>
                <p class="message-time">2016-03-02  9：30</p>
            </div>
        </div>

        <div>


        </div>

    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>