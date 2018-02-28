<@extend name="layout">
    <@block name="cs">
    <style type="text/css">
  
    </style>
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
                        <img src="${_PATH}/res/images/dui.png" class="gold-use">
                        <img src="${_PATH}/res/images/huizan.png" class="gold-user">
                        <div class="column-head"></div>
                    </div>

                    <div class="username">
                        <span style="font-size: 20px;vertical-align:sub;">Olivia</span>
						<span class="slider"><span class="fast"><i class="slippage"></i></span></span>
                        <span style="font-size: 14px;margin-left:10px;color:#9B9B9B;">不接活</span>
                    </div>
			
                   <!--  图标区   口号 -->
					<p class="al">阿里巴巴高级工程师</p>
                    <ul class="afine">
                    	<li>诚信度:&nbsp;<span>5.0</span></li>
                    	<li class="fine">精英币:&nbsp;<span>20</span>币</li>
                    </ul>
                   <div class="sortf"><img src="${_PATH}/res/images/soritef.png" alt=""></div>

                    <div class="split-line"></div>

                    <div style="padding:10px;">
                        <div class="index-title text-left">收益</div>
                        <div class="index-title text-center" style="width:34%;">接活</div>
                        <div class="index-title text-right">获得关注</div>
                    </div>

                    <div style="padding:0 20px;margin-top:10px;">
                        <div class="index-data text-left">￥<span>30000.0</span></div>
                        <div class="index-data text-center" style="width:34%;">3</div>
                        <div class="index-data text-right">100</div>
                    </div>
                </div>

                <div class="left-column">
                    <ul class="left-menu" style="padding-top:10px;">
                        <li class="active-menu"><span class="menu-icon" style="background: url('${_PATH}/res/images/invite_icon.png') no-repeat -5px -5px"></span>我的项目</li>
                        <li><span class="menu-icon"></span>我的关注<span class="concerns">9</span></li>
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