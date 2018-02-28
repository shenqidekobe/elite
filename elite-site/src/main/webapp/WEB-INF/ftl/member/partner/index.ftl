<@extend name="layoutChannel">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/channel/channelContainer.css"/>
    </@block>
    <@block name="body">
    <#--头部-->
    <@accountheadChannel opt2=""/>

    <#--正文开始-->
    <div id="channelContainerIndex">
    <div class="channel_body">
    	<!--项目渠道方-->
    	<div class="company_channel" id="company_channel">
	        <ul class="divUl" id="divUl">
	            <li class="li1" id="li1">
	                <div class="companyBg1" id="companyBg1"></div>
	                <div class="li_main" id="li_main1">
	                    <div class="future"><img src="${_PATH}/res/images/channel/company_p1.png" alt=""></div>
	                    <div class="btnDiv1">
	                         <#if session().memberId?exists>
	                             <a href="${_PATH}/member/index"><button type="button" class="li_login">个人主页</button></a>
	                         <#else>
		                         <a href="${_PATH}/login"><button type="button" class="li_login">登录</button></a>
		                         <a href="${_PATH}/register/p?ts=partnerCompany"><button type="button" class="li_register"><div class="button_bg"></div>注册</button></a>
	                         </#if>
	                    </div>
	                    <div class="debris"><div class="debris_bg"></div>在碎片化时代，该如何多渠道获取碎片化收益？</div>
	                    <div class="downIcon" id="downBtn1"></div>
	                </div>
	            </li>
	
	
	            <li class="li2" id="li2">
	                <div class="companyBg2" id="companyBg2"></div>
	                <div class="li_main" id="li_main2">
	                    <div class="obtain"><img src="${_PATH}/res/images/channel/company_p2.png" alt=""></div>
	                    <div class="time2">合理利用碎片化时间，提高资源转化率</div>
	                    <div class="think2"><span class="think_text">作为一名普通销售，我常常思考如何获取更高的个人收益；后来了解到很多客户都有互联网转型需求，我将他们推荐到云英汇，不断获得项目佣金，对此，感到很满足。</span></div>
	                    <div class="downIcon" id="downBtn2"></div>
	                </div>
	                <div class="process">
	                    <div class="process_bg"></div>
	                    <div class="process_main">
	                        <ul>
	                            <li>
	                                <span class="logo registered_process"></span>
	                                <span class="text">注册成为项目渠道方</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo company_process"></span>
	                                <span class="text">推荐渠道</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo acceptance_process"></span>
	                                <span class="text">项目阶段付款验收</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo same_process"></span>
	                                <span class="text">提现获取收益</span>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	            </li>
	
	            <li class="li3" id="li3">
	                <div class="companyBg3" id="companyBg3"></div>
	                <div class="li_main" id="li_main3">
	                    <div class="follow"><img src="${_PATH}/res/images/channel/company_p3.png" alt=""></div>
	                    <div class="time3">收益积少成多，长久有效</div>
	                    <div  class="think3"><span class="think_text">后来，我给很多同事、朋友介绍，并把他们推荐到云英汇，他们获取项目佣金收益的同时，我也可以从中获取收益，这一规则，长久有效。在云英汇，我就是一名销售总监，高收入之外的成就感让我无比兴奋。</span></div>
	                    <div class="btnBox3" id="btnBox3_company">查看项目渠道方收益规则</div>
	                    <div class="downIcon" id="downBtn3"></div>
	                </div>
	                <div class="process">
	                    <div class="process_bg"></div>
	                    <div class="process_main">
	                        <ul>
	                            <li>
	                                <span class="logo registered_process"></span>
	                                <span class="text">注册成为项目渠道方</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo channel_process"></span>
	                                <span class="text">推荐渠道</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo money_process"></span>
	                                <span class="text">渠道产生收益</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo same_process"></span>
	                                <span class="text">我也获取收益</span>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	            </li>
	
	
	            <li class="li4" id="li4">
	                <div class="companyBg4" id="companyBg4"></div>
	                <div class="li_main" id="li_main4">
	                    <div class="so"><img src="${_PATH}/res/images/channel/company_p4.png" alt=""></div>
	                    <div class="think4"><span class="think_text">无论你是：销售、创业孵化器、投资人、培训学校等等普通上班族都可以像我一样，轻松推荐，长久获益。</span></div>
	                    <div class="btnBox4" style="display:none">立刻开始</div>
	                    <div class="rule4">
	                        <dl>
	                            <dt>项目渠道方收益规则:</dt>
	                            <dd class="dd4">渠道收益结构：A=L1*Q1 + L2*Q2 + L3*Q3</dd>
	                            <dd>L：代表层级，Q代表提成比例</dd>
	                            <dd>L1：我推荐的项目总额</dd>
	                            <dd>L2：我推荐的人（渠道）产生的项目总额（二级）</dd>
	                            <dd>L3：二级推荐的人（渠道）产生的项目总额（三级）</dd>
	                            <dd>Q1：L1适用的比例,&nbsp;&nbsp;Q2：L2适用的比例，&nbsp;&nbsp;Q3：L3适用的比例</dd>
	                        </dl>
	                    </div>
	                </div>
	            </li>
	
	
	
	
	        </ul>
	        <ul class="pointUl" id="pointUl">
	            <li class="li_active" data="point1" id="point1"></li>
	            <li data="point2" id="point2"></li>
	            <li data="point3" id="point3"></li>
	            <li data="point4" id="point4"></li>
	        </ul>
	    </div>
	    
	   <!--人才渠道--> 
	    <div class="person_channel" id="person_channel" style="display:none;">
	        <ul class="divUl" id="divUl_person">
	            <li class="li1" id="li1_person">
	                <div class="personBg1" id="personBg1"></div>
	                <div class="li_main" id="personli_main1">
	                    <div class="future"><img src="${_PATH}/res/images/channel/person_p1.png" alt=""></div>
	                    <div class="btnDiv1">
	                        <#if session().memberId?exists>
	                             <a href="${_PATH}/member/index"><button type="button" class="li_login">个人主页</button></a>
	                         <#else>
		                         <a href="${_PATH}/login"> <button type="button" class="li_login">登录</button></a>
	                             <a href="${_PATH}/register/p?ts=partnerElite"> <button type="button" class="li_register"><div class="button_bg"></div>注册</button></a>
	                         </#if>
	                    </div>
	                    <div class="debris"><div class="debris_bg"></div>在碎片化时代，该如何多渠道获取碎片化收益？</div>
	                    <div class="downIcon" id="downBtn1_person"></div>
	                </div>
	            </li>
	
	
	            <li class="li2" id="li2_person">
	                <div class="personBg2" id="personBg2"></div>
	                <div class="li_main" id="personli_main2">
	                    <div class="obtain_person"><img src="${_PATH}/res/images/channel/person_p2.png" alt=""></div>
	                    <div class="time2_person">合理利用碎片化时间，提高资源转化率</div>
	                    <div class="think2_person"><span class="think_text">作为一名普通HR，平时兼职个人猎头，我常常思考如何将手中的人才资源多利用起来，价值最大化。于是，我将这些人才推荐到云英汇，他们认领任务，交付结算后，我都可以获得收益，是永久生效的哦</span></div>
	                    <div class="downIcon" id="downBtn2_person"></div>
	                </div>
	                <div class="process">
	                    <div class="process_bg"></div>
	                    <div class="process_main">
	                        <ul>
	                            <li>
	                                <span class="logo registered_process"></span>
	                                <span class="text">注册成为人才渠道渠道方</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo technology_process"></span>
	                                <span class="text">推荐备案技术人才</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo task_process"></span>
	                                <span class="text3_person">技术人才注册<br/>认领任务</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo same_process"></span>
	                                <span class="text4_person">任务交付<br/>提现获取永久佣金收益</span>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	            </li>
	
	            <li class="li3" id="li3_person">
	                <div class="personBg3" id="personBg3"></div>
	                <div class="li_main" id="personli_main3">
	                    <div class="follow_person"><img src="${_PATH}/res/images/channel/person_p3.png" alt=""></div>
	                    <div class="time3_person">收益积少成多，长久有效</div>
	                    <div  class="think3_person"><span class="think_text">后来，我给很多同事、朋友介绍，并把他们推荐到云英汇，他们获取项目佣金收益的同时，我也可以从中获取收益，这一规则，长久有效。在云英汇，我就是一名销售总监，高收入之外的成就感让我无比兴奋。</span></div>
	                    <div class="btnBox3_person" id="btnBox3_elite" ><div class="btn_bg"></div>查看人才渠道方收益规则</div>
	                    <div class="downIcon" id="downBtn3_person"></div>
	                </div>
	                <div class="process">
	                    <div class="process_bg"></div>
	                    <div class="process_main">
	                        <ul>
	                            <li>
	                                <span class="logo registered_process"></span>
	                                <span class="text">注册成为人才渠道方</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo channel_process"></span>
	                                <span class="text">推荐人才渠道</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo money_process"></span>
	                                <span class="text">渠道产生收益</span>
	                                <span class="right_icon"></span>
	                            </li>
	                            <li>
	                                <span class="logo same_process"></span>
	                                <span class="text">我也获取收益</span>
	                            </li>
	                        </ul>
	                    </div>
	                </div>
	            </li>
	
	
	            <li class="li4" id="li4_person">
	                <div class="personBg4"  id="personBg4"></div>
	                <div class="li_main" id="personli_main4">
	                    <div class="so_person"><img src="${_PATH}/res/images/channel/person_p4.png" alt=""></div>
	                    <div class="think4_person"><span class="think_text">无论你是：销售、创业孵化器、投资人、培训学校等等普通上班族都可以像我一样，轻松推荐，长久获益。</span></div>
	                    <div class="btnBox4_person" style="display:none">立刻开始</div>
	                    <div class="rule4_person">
	                        <dl>
	                            <dt>人才渠道方收益规则:</dt>
	                            <dd class="dd4">渠道收益结构：A=L1*Q1 + L2*Q2 + L3*Q3</dd>
	                            <dd>L：代表层级，Q代表提成比例</dd>
	                            <dd>L1：第一级推荐人才效益额合计（自身）</dd>
	                            <dd>L2：第二级推荐人才效益额合计（直接渠道）</dd>
	                            <dd>L3：第三级推荐人才效益额合计（间接渠道）</dd>
	                            <dd>Q1：L1适用的比例,&nbsp;&nbsp;Q2：L2适用的比例，&nbsp;&nbsp;Q3：L3适用的比例</dd>
	                        </dl>
	                    </div>
	                </div>
	            </li>
	
	
	        </ul>
	        <ul class="pointUl" id="pointUl_person">
	            <li class="li_active" data="point1" id="point1_person"></li>
	            <li data="point2" id="point2_person"></li>
	            <li data="point3" id="point3_person"></li>
	            <li data="point4" id="point4_person"></li>
	        </ul>
	    </div>
	    
	    
    </div>
    </div>
  
    </@block>
    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/partner/partner.js"></script>
    </@block>
</@extend>