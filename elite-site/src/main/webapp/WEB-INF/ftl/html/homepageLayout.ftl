<@extend name="layout">
    <@block name="cs">
    <#--<style type="text/css">-->


        <#--/*正文样式*/-->
        <#--.content{-->
            <#--width:1000px;-->
            <#--min-height:500px;-->
            <#--margin-left:auto;-->
            <#--margin-right:auto;-->
            <#--position: relative;-->
        <#--}-->

        <#--.location-nav{-->
            <#--padding-top:20px;-->
            <#--margin-bottom: 20px;-->
        <#--}-->
        <#--.left-column{-->
            <#--width:240px;-->
            <#--border:1px solid #E6E6E6;-->
            <#--border-radius: 4px;-->
            <#--text-align: center;-->
            <#--background-color: #FAFAFA;-->
            <#--box-shadow: 2px 2px 6px 0px rgba(0,0,0,0.30);-->
            <#--margin-bottom: 20px;-->

        <#--}-->

        <#--.column-head{-->
            <#--width: 80px;-->
            <#--height: 80px;-->
            <#--background:url("/res/images/test.jpg") no-repeat;-->
            <#--background-size: 80px 80px;-->
            <#--border: 1px solid #979797;-->
            <#---moz-border-radius: 50px;-->
            <#---webkit-border-radius: 50px;-->
            <#--border-radius: 50px;-->
        <#--}-->

        <#--.circle-box{-->
            <#--padding-left:80px;-->
            <#--padding-top:20px;-->
            <#--position: relative;-->
        <#--}-->

        <#--.username{-->
            <#--margin-top:14px;-->
        <#--}-->

        <#--.split-line{-->
            <#--width:100%;-->
            <#--border-bottom: 1px solid #E6E6E6;-->
        <#--}-->

        <#--.left-menu li{-->
            <#--line-height: 40px;-->
            <#--cursor: pointer;-->
            <#--position: relative;-->
        <#--}-->

        <#--.left-menu li>*{-->
            <#--display: inline-block;-->
        <#--}-->

        <#--.menu-icon{-->
            <#--display: block;-->
            <#--width:20px;-->
            <#--height:20px;-->
            <#--/*background-color: red;*/-->
            <#--vertical-align: middle;-->
            <#--margin-right:15px;-->
        <#--}-->

        <#--.active-menu{-->
            <#--color:#2CB7C9;-->
        <#--}-->

        <#--.right-opt{-->
            <#--float:right;-->
            <#--width:730px;-->
        <#--}-->

        <#--.slogan{-->
            <#--color:#9B9B9B;-->
            <#--font-size: 14px;-->
            <#--margin-top:20px;-->
        <#--}-->

        <#--.index-title{-->
            <#--width:33%;-->
            <#--float:left;-->
            <#--color:#B3B3B3;-->
            <#--font-size: 12px;-->
        <#--}-->
        <#--.index-data{-->
            <#--width:33%;-->
            <#--float:left;-->
            <#--font-size: 12px;-->
        <#--}-->

        <#--.head-label{-->
            <#--position: absolute;-->
            <#--top:20px;-->
            <#--right:56px;-->
            <#--z-index: 200;-->

            <#--width:44px;-->
            <#--height:18px;-->
            <#--line-height: 18px;-->
            <#--text-align: center;-->
            <#--background-color: #FEA600;-->
            <#--color:white;-->
            <#--font-size: 12px;-->
            <#--border-radius: 10px;-->
        <#--}-->

        <#--.gold-user{-->
            <#--position: absolute;-->
            <#--top:20px;-->
            <#--right:30px;-->
            <#--width:19px;-->
            <#--height:21px;-->
        <#--}-->
        <#--.separator{-->
            <#--height:22px;-->
            <#--border-right: 1px solid #E6E6E6;-->
            <#--position: absolute;-->
            <#--top:0;-->
            <#--left:110px;-->
        <#--}-->

        <#--.current-status{-->
            <#--font-size: 12px;-->
            <#--position: relative;-->
            <#--margin-top:5px;-->
        <#--}-->

        <#--.open-or-close{-->
            <#--width:50px;-->
            <#--height:20px;-->
            <#--vertical-align: sub;-->
            <#--margin-left:20px;-->
        <#--}-->

        <#--.work-label{-->
            <#--font-size: 12px;-->
            <#--display: inline-block;-->
        <#--}-->

        <#--.title-label{-->
            <#--width:65px;-->
            <#--height:20px;-->
            <#--line-height: 20px;-->
            <#--border:1px solid #CCCCCC;-->
            <#--border-radius: 10px;-->
            <#--color:#9B9B9B;-->
            <#--text-align: left;-->
            <#--padding-left:5px;-->
            <#--position: relative;-->

        <#--}-->

        <#--.title-grade{-->
            <#--width: 20px;-->
            <#--height: 20px;-->
            <#--line-height: 20px;-->
            <#--text-align: center;-->
            <#---moz-border-radius: 50px;-->
            <#---webkit-border-radius: 50px;-->
            <#--border-radius: 50px;-->
            <#--background-color: #4A90E2;-->
            <#--color:white;-->

            <#--position: absolute;-->
            <#--top:-1px;-->
            <#--right:0;-->

        <#--}-->

        <#--.message-circle{-->
            <#--display: block;-->
            <#--width: 16px;-->
            <#--height: 16px;-->
            <#--line-height: 16px;-->
            <#--text-align: center;-->
            <#---moz-border-radius: 50px;-->
            <#---webkit-border-radius: 50px;-->
            <#--border-radius: 50px;-->
            <#--background-color: #FEA600;-->
            <#--color:white;-->
            <#--font-size: 12px;-->

            <#--position: absolute;-->
            <#--top:4px;-->
            <#--right:50px;-->
        <#--}-->

        <#--.nav-menus{-->
            <#--position: absolute;-->
            <#--top:0;-->
            <#--right:0;-->
            <#--z-index: 1000;-->

            <#--width:121px;-->
            <#--height:208px;-->
        <#--}-->

        <#--.nav-menus ul li{-->
            <#--text-align: center;-->
            <#--font-size: 14px;-->
            <#--color:white;-->
            <#--line-height: 41px;-->
            <#--background-color: #000;-->
            <#--opacity: 0.5;-->
            <#--filter: alpha(opacity=50);-->
            <#---moz-opacity: 0.5;-->
        <#--}-->

        <#--.active-nav-menu{-->
            <#--opacity: 0.8!important;-->
            <#--filter: alpha(opacity=80)!important;-->
            <#---moz-opacity: 0.8!important;-->
        <#--}-->

    <#--</style>-->
    </@block>
    <@block name="body">
    <#--头部-->
        <@accounthead opt=""/>
		
    <#--正文开始-->
    <div class="content" id="homepage">
    <#--<div class="content">-->
        <div class="location-nav">
            <span style="font-size: 18px;">个人主页</span>&nbsp;&gt;&nbsp;<span style="color:#2CB7C9;">我的项目</span>
        </div>
        <#--<div class="nav-menus">-->
            <#--<ul>-->
                <#--<li class="active-nav-menu" style="border-top-left-radius: 6px;border-top-right-radius: 6px;"> <img src="${_PATH}/res/images/white_computer_icon.png" class="nav-menus-icon" width="18" height="16">&nbsp;&nbsp;我的任务</li>-->
                <#--<li> <img src="${_PATH}/res/images/white_love_icon.png" class="nav-menus-icon" width="18" height="18">&nbsp;&nbsp;我的关注</li>-->
                <#--<li> <img src="${_PATH}/res/images/white_packet_icon.png" class="nav-menus-icon" width="16" height="20">&nbsp;&nbsp;结算管理</li>-->
                <#--<li> <img src="${_PATH}/res/images/white_person_icon.png" class="nav-menus-icon" width="16" height="17">&nbsp;&nbsp;我的资料</li>-->
                <#--<li style="border-bottom-left-radius: 6px;border-bottom-right-radius: 6px;"> <img src="${_PATH}/res/images/white_logout_icon.png" class="nav-menus-icon" width="16" height="17">&nbsp;&nbsp;退出登录</li>-->
            <#--</ul>-->
        <#--</div>-->

        <div class="content-box">

            <#--左边的资料区-->
            <div style="float:left;">
                <div class="left-column" style="padding-bottom: 26px;">
                    <div class="circle-box">
                        <div class="head-label">项目方</div>
                        <img src="${_PATH}/res/images/gold_user_icon.png" class="gold-user">
                        <div class="column-head"></div>
                    </div>

                    <div class="username">
                        <span style="font-size: 20px;">Olivia</span><span style="font-size: 14px;margin-left:15px;color:#9B9B9B;">上海</span>
                    </div>

                    <#--图标区-->

                    <#--口号-->
                    <p class="slogan">“梦想总是要有的，万一实现了呢”</<p>

                    <div class="split-line"></div>

                    <div style="padding:10px;">
                        <div class="index-title text-left">诚信度</div>
                        <div class="index-title text-center" style="width:34%;">进行中的项目</div>
                        <div class="index-title text-right">已托管项目</div>
                    </div>

                    <div style="padding:0 20px;margin-top:10px;">
                        <div class="index-data text-left">5.0</div>
                        <div class="index-data text-center" style="width:34%;">3</div>
                        <div class="index-data text-right">10000</div>
                    </div>

                </div>

                <div class="left-column">
                    <ul class="left-menu" style="padding-top:10px;">
                        <li class="active-menu"><span class="menu-icon" style="background: url('${_PATH}/res/images/invite_icon.png') no-repeat -5px -5px"></span>我的项目</li>
                        <li><span class="menu-icon"></span>结算管理</li>
                        <li><span class="menu-icon"></span>我的资料</li>
                    </ul>
                </div>
            </div>

            <div style="float:left;display: none;">
                <div class="left-column" style="padding-bottom: 26px;">
                    <div class="circle-box">
                        <div class="head-label">CTO</div>
                        <img src="${_PATH}/res/images/auth_user_icon.png" class="gold-user">
                        <img src="${_PATH}/res/images/star_user_icon.png" class="gold-user" style="right:6px;">
                        <div class="column-head"></div>
                    </div>

                    <div class="username">
                        <span style="font-size: 20px;">Olivia</span><span style="font-size: 14px;margin-left:15px;color:#9B9B9B;">上海</span>
                    </div>

                <#--图标区-->
                    <div class="current-status">
                        <span><span style="color:#9B9B9B;">诚信度</span>&nbsp;&nbsp;5.0</span>
                        <div class="separator"></div>
                        <img src="${_PATH}/res/images/open_icon.png" class="open-or-close">
                        &nbsp;<span>接活</span>
                    </div>

                    <div style="margin-top:10px;">
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                    </div>

                <#--口号-->
                    <p class="slogan">“梦想总是要有的，万一实现了呢”</<p>

                    <div class="split-line"></div>

                    <div style="padding:10px;">
                        <div class="index-title text-left">诚信度</div>
                        <div class="index-title text-center" style="width:34%;">进行中的项目</div>
                        <div class="index-title text-right">已托管项目</div>
                    </div>

                    <div style="padding:0 20px;margin-top:10px;">
                        <div class="index-data text-left">5.0</div>
                        <div class="index-data text-center" style="width:34%;">3</div>
                        <div class="index-data text-right">10000</div>
                    </div>

                </div>

                <div class="left-column">
                    <ul class="left-menu" style="padding-top:10px;">
                        <li class="active-menu"><span class="menu-icon" style="background: url('${_PATH}/res/images/invite_icon.png') no-repeat -5px -5px"></span>我负责的项目</li>
                        <li><span class="menu-icon"></span>我认领的任务<span class="message-circle" style="right:36px;">8</span></li>
                        <li><span class="menu-icon"></span>我的团队</li>
                        <li><span class="menu-icon"></span>我的关注<span class="message-circle">8</span></li>
                        <li><span class="menu-icon"></span>结算管理</li>
                        <li><span class="menu-icon"></span>我的资料</li>
                    </ul>
                </div>
            </div>

            <div style="float:left;display: none;">
                <div class="left-column" style="padding-bottom: 26px;">
                    <div class="circle-box">
                        <div class="head-label">CTO</div>
                        <img src="${_PATH}/res/images/auth_user_icon.png" class="gold-user">
                        <img src="${_PATH}/res/images/star_user_icon.png" class="gold-user" style="right:6px;">
                        <div class="column-head"></div>
                    </div>

                    <div class="username">
                        <span style="font-size: 20px;">Olivia</span><span style="font-size: 14px;margin-left:15px;color:#9B9B9B;">上海</span>
                    </div>

                <#--图标区-->
                    <div class="current-status">
                        <span><span style="color:#9B9B9B;">诚信度</span>&nbsp;&nbsp;5.0</span>
                        <div class="separator"></div>
                        <img src="${_PATH}/res/images/open_icon.png" class="open-or-close">
                        &nbsp;<span>接活</span>
                    </div>

                    <div style="margin-top:10px;">
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                        <div class="work-label">
                            <div class="title-label">工程师 <div class="title-grade">L3</div></div>
                        </div>
                    </div>

                <#--口号-->
                    <p class="slogan">“梦想总是要有的，万一实现了呢”</<p>

                    <div class="split-line"></div>

                    <div style="padding:10px;">
                        <div class="index-title text-left">诚信度</div>
                        <div class="index-title text-center" style="width:34%;">进行中的项目</div>
                        <div class="index-title text-right">已托管项目</div>
                    </div>

                    <div style="padding:0 20px;margin-top:10px;">
                        <div class="index-data text-left">5.0</div>
                        <div class="index-data text-center" style="width:34%;">3</div>
                        <div class="index-data text-right">10000</div>
                    </div>

                </div>

                <div class="left-column">
                    <ul class="left-menu" style="padding-top:10px;">
                        <li><span class="menu-icon"></span>我的任务</li>
                        <li><span class="menu-icon"></span>我的关注<span class="message-circle">8</span></li>
                        <li><span class="menu-icon"></span>结算管理</li>
                        <li><span class="menu-icon"></span>我的资料</li>
                    </ul>
                </div>
            </div>
            <#--右边的操作区-->
            <div class="right-opt">
                <@block name="rightContent">
                </@block>
            </div>

        </div>
    </div>
    <@block name="flow"></@block>
    </@block>
    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/homeLayout.js')}"></script>
    </@block>
</@extend>