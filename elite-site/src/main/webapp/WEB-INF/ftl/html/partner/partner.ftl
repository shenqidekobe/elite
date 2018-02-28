<@extend name="layout">
    <@block name="cs">
    <style type="text/css">

       
        .way > *{
            float:left;
        }

        .way img{
            padding-top:12px;
        }

        .way img:hover{
            opacity: 0.8;
        }
        .waybox{
            margin-left:40px;
            padding-top:12px;
        }
        .want{
            font-size: 20px;
            color:#9B9B9B;
        }

        .opt{
            margin-top:28px;
        }

        .opt button{
            padding:4px 20px;
            border-radius: 14px;
        }

        .coop-btn{
            border:1px solid #2CB7C9;
            background-color: #2CB7C9;
            color:white;
        }

        .goto-btn{
            border:1px solid #2CB7C9;
            background-color: white;
            color:#2CB7C9;
            margin-left:10px;
        }

        .twoways >*{
            float:left;
        }
        
        /*渠道主页背景*/
        
         .channel{
            position: relative;
            min-width: 1000px;
            height: 960px;
            background: url("/res/images/elite/channel_bg.png") no-repeat;
            background-size: auto;
        }
        .channel_text{
            position: absolute;
            z-index: 80;
            left: 50%;
            margin-left: -312px;
            top: 50%;
            margin-top: -156px;
            width: 625px;
            height: 189px;
            border-radius:10px;
            -webkit-border-radius: 10px;
            -moz-border-radius: 10px;
            background-color: #000;
            opacity: 0.5;
        }
        .text_box{
            position:relative;
            margin: 0 auto;
            width: 523px;
            height: 168px;
            margin: 10px auto;
            color: #fff;

        }
        p.text_p1 {
            margin-top: 10px;
        }
        .text_box_content{
            position: absolute;
            z-index: 150;
            left: 50%;
            margin-left: -261px;
            top: 50%;
            margin-top: -84px;

        }
        .text_p1 span:nth-child(1){
            font-size: 60px;
        }
        .text_p1 span:nth-child(2){
            font-size: 50px;
        }

        .text_p2 span:nth-child(1){
            font-size: 60px;
        }
        .text_p2 span:nth-child(2){
            font-size: 50px;
        }

        

    </style>
    </@block>
    <@block name="body">
    <#--头部-->
        <@mainhead clazz="partner" picpath="/res/images/banner_office.png" pagetype="main" user=""/>

    <#--正文开始-->
    <div class="content">
    	
    	
		<div class="channel">
		    <div class="channel_text">
		        <div class="text_box">
		            <div class="text_box_content">
		                <p class="text_p1"><span>一次</span><span>推荐就能获得</span></p>
		                <p class="text_p2"><span>一劳永逸</span><span>的持续回报</span></p>
		            </div>
		        </div>
		    </div>
		</div>

    	
        <div class="twoways">
            <div class="way" style="border-right:1px solid #CCCCCC;margin-right:60px;">
                <img src="/res/images/bulb.jpg">
                <div class="waybox">
                    <p style="font-size: 30px;color:#4A4A4A;">项目渠道</p>
                    <p class="want">我有项目要推荐</p>
                    <div class="opt">
                        <button type="button" class="coop-btn">合作</button>
                        <button type="button" class="goto-btn">登录</button>
                    </div>
                </div>
            </div>

            <div class="way">
                <img src="/res/images/gear.jpg">
                <div class="waybox">
                    <p style="font-size: 30px;color:#4A4A4A;">人才渠道</p>
                    <p class="want">我有精英要推荐</p>
                    <div class="opt">
                        <button type="button" class="coop-btn">合作</button>
                        <button type="button" class="goto-btn">登录</button>
                    </div>
                </div>
            </div>

        </div>
        

    </div>


    </@block>
    <@block name="script">
    </@block>
</@extend>