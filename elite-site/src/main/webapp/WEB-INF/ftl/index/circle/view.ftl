<@extend name="layout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/elite_main.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
	<div class="y-bd">
	    <div class="elite_eyes" id="elite_eyes">
	        <div class="eyes_bg">
	            <div class="bg_text">
	                    <div class="eyes_img">
	                        <#if obj.basic.photoId??>
					           <div class="box-logo-a" >
					           	   <img src="${obj.basic.memberPhoto.path}">
					           </div>
					        <#else>
					           <div class="box-logo-a">
					           	   <img src="${_PATH}/res/images/default.jpg">
					           </div>
					        </#if>
	                    </div>
	                    <#if obj.elite.status=='normal'&&obj.elite.ctoed>
					    	<div class="head-label">CTO</div>
						</#if>
	                    <div class="img_icon">
	                        <img src="${_PATH}/res/images/elite/badge.png" alt="" class="img_icon_a">
	                        <#--<img src="${_PATH}/res/images/elite/star.png" alt="" class="img_icon_b">-->
	                    </div>
	                    <div class="head_user_name">
		                    <div class="nameDiv">
		                        <#if obj.nickName?length gt 7>${obj.nickName?substring(0,7)}<#else>${obj.nickName}</#if>
		                    </div>
		                    <div class="nameSex">
		                        <#if obj.basic.sex??||obj.basic.sex=='F'>
		                           <img src="${_PATH}/res/images/ceo/woman.png" alt="" class="sex">
		                        <#else>
		                           <img src="${_PATH}/res/images/ceo/man.png" alt="" class="sex">
		                        </#if>
		                    </div>
	                    </div>
	                    <div class="head_user_city">
		                    <div class="nameJob">
		                        ${obj.basic.memberSign}
		                    </div>
		                    <div class="nameCity_icon">
		                        <img src="${_PATH}/res/images/elite/city.png" alt="" class="">
		                    </div>
		                    <div class="nameCity">
		                        ${obj.basic.areaName}
		                    </div>
	                    </div>
	                   
	                    <div class="attentionDiv">
	                       <#if obj.attentioned>
	                          <div class="attentionImg" data="${obj.id}" oper="remove" style="background: #999">已关注</div>
	                       <#else>
	                          <div class="attentionImg" data="${obj.id}" oper="yes" style="background: #fea600">关注</div>
	                       </#if>
	                    </div>
	                </div>
	            </div>
	        
	        <div class="eyes_nav">
	            <div class="nav_container">
	                <div class="nav_box box_one">
	                    <span>${obj.basic.integrity}</span>
	                    <span>诚信度</span>
	                </div>
	                <div class="nav_box box-two">
	                	<span id="fansCount">${obj.fansCount}</span>
	                    <span>获得关注</span>
	                </div>
	                <div class="nav_box box_three">
	                    <span>${obj.viewCount}</span>
	                    <span>次查看</span>
	                </div>
	                <div class="nav_line line_one"></div>
	                <div class="nav_line line_two"></div>
	            </div>
	        </div>
	        <div class="eyes_content">
	            <div class="eyes_content_l">
	                <div class="content_l_a">
	                    <div class="eyeDiv">
	                        <img src="${_PATH}/res/images/elite/eye.png" alt="">
	                    </div>
	                    <div class="l_a_title">他的联系方式</div>
	                    <div class="l_a_box">
	                        <#--<div class="l_a_name"><span>姓名：</span><span>${obj.credit.formatName}</span></div>-->
	                        <div class="l_a_phone"><span>联系方式：</span><span>${obj.formatPhone}</span></div>
	                        <div class="l_a_email"><span>邮箱：</span><span>${obj.formatEmail}</span></div>
	                    </div>
	                </div>
	                <div class="content_l_b">
	                    <ul id="selectUl">
	                        <li data="main">
	                            <div class="li_box">
	                                <img src="${_PATH}/res/images/elite/head_select.png" alt="" class="li_img" id="head_img"><div class="li_text active_color">他的主页</div>
	                            </div>
	                        </li>
	                        <li data="attention">
	                            <div class="li_box">
	                                <img src="${_PATH}/res/images/elite/heart.png" alt="" class="li_img" id="heart_img"><div class="li_text">他的关注<span class="number_box">${asize}</span></div>
	                            </div>
	                        </li>
	                        <span class="active_line"></span>
	                    </ul>
	                </div>
	            </div>
	            <div class="eyes_content_r">
	                <div class="select_box"  id="mainContent">
	                    <div class="content_r_a">
	                        <div class="titleBox">
	                            <div class="title_text">当前状态</div>
	                            <div class="title_line title_line_one"></div>
	                            <div class="title_line title_line_two"></div>
	                        </div>
	                        <div class="contentBox state_box">
	                            <div class="state_l">
	                                <div class="state_group clearfloat">
	                                    <div class="state_group_l">擅长领域</div>
	                                    <div class="state_group_r">
	                                        <ul class="work_name clearfloat">
	                                            <#list obj.elite.eliteJobs as ibs>
				                                    <li><div class="name_box"><span class="name_text">${ibs.jobRoleVal}</span><span class="name_icon">${ibs.level}</span></div></li>
				                                </#list>
	                                        </ul>
	                                        <ul class="work_skill clearfloat">${obj.elite.eliteJobs.jobAdept}
		                                        <#list obj.elite.eliteJobs as ejs>
		                                           <#list ejs.jobAdeptValList as ibs>
				                                      <li>${ibs.value}</li>
				                                   </#list>
				                                </#list>
	                                        </ul>
	                                    </div>
	                                </div>
	                                <div class="state_group clearfloat">
	                                    <div class="state_group_l">关注行业</div>
	                                    <div class="state_group_r">
	                                        <ul class="group_industry">
	                                            <#list obj.elite.attentionIndustryListVal as ivs>
	                                               <li>${ivs}</li>
	                                            </#list>
	                                        </ul>
	                                    </div>
	                                </div>
	                                <div class="state_group clearfloat">
	                                    <div class="state_group_l">相关年限</div>
	                                    <div class="state_group_r">${obj.elite.jobAgeVal}</div>
	                                </div>
	                            </div>
	                            <div class="state_r">
	                                <div class="state_r_a"><#if obj.elite.onjobed>在职<#else>自由状态</#if></div>
	                                <div class="state_r_b">每周可分配时长<span>${obj.elite.allotDurationVal}</span></div>
	                            </div>
	                            <div class="state_line"></div>
	                        </div>
	                    </div>
	                    <#if obj.elite.intro??>
	                    <div class="content_r_e">
	                        <div class="titleBox">
	                            <div class="title_text">自我描述</div>
	                            <div class="title_line title_line_one"></div>
	                            <div class="title_line title_line_two"></div>
	                        </div>
	                        <div class="contentBox education_box">
	                            <div class="education_box">
	                                ${obj.elite.intro}
	                            </div>
	                        </div>
	                    </div>
	                    </#if>
	                    <#if obj.projects??&&(obj.projects?size gt 0)>
	                    <div class="content_r_b">
	                        <div class="titleBox">
	                            <div class="title_text">项目经历</div>
	                            <div class="title_line title_line_one"></div>
	                            <div class="title_line title_line_two"></div>
	                        </div>
	                        <#list obj.projects as pbs>
	                            <div class="contentBox project_box">
		                            <div class="project_box_a">
		                                <div class="project_title" style="position:inherit">${pbs.project}</div>
		                                <div class="project_person">${pbs.company}</div>
		                                <div class="project_work">${pbs.position}</div>
		                                <div class="project_time">${pbs.startTime?string("yyyy.MM")}-${pbs.endTime?string("yyyy.MM")}</div>
		                            </div>
		                            <div class="project_box_b">${pbs.intro}</div>
	                            </div>
	                        </#list>
	                    </div>
	                    </#if>
	                    <#if obj.educations??&&(obj.educations?size gt 0)>
	                    <div class="content_r_c">
	                        <div class="titleBox">
	                            <div class="title_text">教育经历</div>
	                            <div class="title_line title_line_one"></div>
	                            <div class="title_line title_line_two"></div>
	                        </div>
	                        <#list obj.educations as ebs>
	                            <div class="contentBox education_box">
		                            <div class="education_box">
		                                <div class="education_school">${ebs.organ}</div>
		                                <div class="education_degree">${ebs.educationVal}</div>
		                                <div class="education_time">${ebs.graduateTime?string("yyyy.MM")}毕业</div>
		                            </div>
	                            </div>
	                        </#list>
	                    </div>
	                    </#if>
	
	                    <#--<div class="content_r_d">
	                        <div class="titleBox">
	                            <div class="title_text">技能评价</div>
	                            <div class="title_line title_line_one"></div>
	                            <div class="title_line title_line_two"></div>
	                        </div>
	                        <div class="contentBox education_box">
	                            <#list obj.skills as sbs>
		                            <div class="education_box">
		                                ${sbs.level}--${sbs.skill}
		                            </div>
	                            </#list>
	                        </div>
	                    </div>
	                    <div class="content_r_e">
	                        <div class="titleBox">
	                            <div class="title_text">作品成果</div>
	                            <div class="title_line title_line_one"></div>
	                            <div class="title_line title_line_two"></div>
	                        </div>
	                        <div class="contentBox education_box">
	                            <div class="education_box">
	                            </div>
	                        </div>
	                    </div>-->
	                </div>
	                <div class="select_box" id="attentionContent" style="display:none;">
	                    <form id="attentionListForm">
	                        <input type="hidden" name="memberId" value="${obj.id}">
	                		<div id="attentionMain" data="${obj.id}">
							    
						    </div>
					    </form>
	                </div>
	            </div>
	        </div>
	    </div>
	</div>
    </@block>
    <@block name="script">
        <script src="${_PATH}/res/script/myjs/circle/view.js"></script>
    </@block>
</@extend>