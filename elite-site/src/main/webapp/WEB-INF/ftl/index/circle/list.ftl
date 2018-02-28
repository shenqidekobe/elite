<@extend name="layout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/elite_main.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
	<div class="y-bd">
	    <div class="elite_circle">
	        <div class="circle_bg">
	            <div class="bg_text">
	                <div class="text_box">
	                    <h1>共享才华<span></span>轻创未来</h1>
	                    <#--<h2>已有<span>${count}</span>位精英产生了技能链接</h2>-->
                        <div class="findDiv">
                            <input type="text" placeholder="请输入关键字，找到想要的精英" id="searchText">
                            <div class="findButton" id="search"></div>
                        </div>
	                </div>
	            </div>
	        </div>
	        <div class="circle_news">
	            <div class="news_box">
	                <div class="news_icon"></div>
	                <div class="news_text">最新动态：欢迎来到云英汇，云集天下精英，快去完善个人资料，逐梦前行吧~</div>
	                <#--<div class="news_arrow">
	                    <a class="arrow_l"><img src="${_PATH}/res/images/elite/arrow_l.png" alt=""></a>
	                    <a class="arrow_r"><img src="${_PATH}/res/images/elite/arrow_r.png" alt=""></a>
	                </div>-->
	            </div>
	        </div>
	        <div class="circle_role">
	            <div class="role_nav">
	                <div class="role_nav_box">
	                    <div class="nav_group clearfloat">
	                        <div class="group_title">精英角色</div>
	                        <ul class="group_content" id="roleUl">
	                            <li class="selected" data="">全部</li>
	                            <@dict type="job_role"></@dict>
				                <#list dictList as dt>
				                    <li data="${dt.dictKey}">${dt.dictVal}</li>
				                </#list>
	                        </ul>
	                    </div>
	                    <div class="nav_group clearfloat">
	                        <div class="group_title">相关工作年限</div>
	                        <ul class="group_content" id="jobageUl">
	                            <li class="selected" data="">全部</li>
			                    <li data="1-3">1-3年</li>
			                    <li data="3-5">3-5年</li>
			                    <li data="5-7">5-7年</li>
			                    <li data="7-10">7-10年</li>
			                    <li data="20">10年以上</li>
	                        </ul>
	                    </div>
	
	                    <div class="nav_group clearfloat">
	                        <div class="group_title">每周可工作时长</div>
	                        <ul class="group_content" id="durationUl">
	                            <li class="selected" data="">全部</li>
	                            <@dict type="allot_duration"></@dict>
			                    <#list dictList as dt>
			                        <li data="${dt.dictKey}">${dt.dictVal}</li>
			                    </#list>
	                        </ul>
	                    </div>
	
	                    <div class="nav_group clearfloat">
	                        <div class="group_title">行业</div>
	                        <ul class="group_content business" id="industryUl">
	                            <li class="selected" data="" id="industryAll">全部</li>
	                            <@dict type="choice_industry"></@dict>
		                        <#list dictList as dt>
		                            <#if dt_index gt 8>
		                                <li data="${dt.dictKey}" style="display:none;" class="moreLi"><span class="li_text">${dt.dictVal}</span><img class="close_btn" src="/res/images/elite/close_elite.png" alt=""></li>
		                            <#else>
		                                <li data="${dt.dictKey}"><span class="li_text">${dt.dictVal}</span><img class="close_btn" src="/res/images/elite/close_elite.png" alt=""></li>
		                            </#if>
		                        </#list>
	                        </ul>
	                        <div class="moreDiv" id="industryMore">
	                            <div class="more_text">更多</div>
	                            <div class="more_btn">
	                                <span class="more_icon"></span>
	                            </div>
	                        </div>
	                    </div>
	                </div>
	            </div>
	
	            <div class="role_content">
	                <ul class="role_content_title">
	                    <#--<li style="padding-left: 0px;" class="activeLi">随机</li>
	                    <Li>最受关注</Li>
	                    <li>最新加入</li>
	                    <Li style="border-right: none">最活跃</Li>-->
	                </ul>
	                <form id="circleForm">
	                   <input type="hidden" name="keyword" id="keyword">
	                   <input type="hidden" name="role" id="role">
	                   <input type="hidden" name="jobAge" id="jobAge">
	                   <input type="hidden" name="duration" id="duration">
	                   <input type="hidden" name="industry" id="industry">
	                   <div id="dataList"></div>
	                </form>
	           </div>
	        </div>
	        <div class="circle_daniel" style="display: none">
	            <div class="box_title">
	                <span>大牛推荐</span>
	                <span>没活干？那就速度关注我们吧</span>
	            </div>
	            <ul class="box_ul">
	                <li>
	                    <div class="imgDiv">
	                        <img src="${_PATH}/res/images/elite/partner_bg.png" alt="">
	                    </div>
	                    <div class="name">Simmons</div>
	                    <div class="attention">
	                        <span class="attention_icon"></span>
	                        <span class="attention_number">136</span>
	                    </div>
	                    <div class="browse">
	                        <span class="browse_icon"></span>
	                        <span class="browse_number">675</span>
	                    </div>
	                    <div class="likeDiv">
	                        <img  src="${_PATH}/res/images/elite/like.png" alt="">
	                    </div>
	                </li>
	            </ul>
	        </div>
	        <div class="circle_master" style="display: none">
	            <div class="box_title">
	                <span>新晋高手</span>
	                <span>人手不够？快将我招入麾下</span>
	            </div>
	            <ul class="box_ul">
	                <li>
	                    <div class="imgDiv">
	                        <img src="${_PATH}/res/images/elite/partner_bg.png" alt="">
	                    </div>
	                    <div class="name">Simmons</div>
	                    <div class="attention">
	                        <span class="attention_icon"></span>
	                        <span class="attention_number">136</span>
	                    </div>
	                    <div class="browse">
	                        <span class="browse_icon"></span>
	                        <span class="browse_number">675</span>
	                    </div>
	                    <div class="likeDiv">
	                        <img  src="${_PATH}/res/images/elite/like.png" alt="">
	                    </div>
	                </li>
	            </ul>
	        </div>
	    </div>
	</div>

    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/circle/list.js"></script>
    </@block>
</@extend>