<@extend name="layout">
    <@block name="cs">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/task.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
	<div class="y-bd">
	    <div class="bd-task">
	    	<div class="bd_task_bg">
	    		<div class="bg_btn">
	    			<div class="btn_cto">
		    			<img src="/res/images/task/cto_logo.png" alt="">
		    			<#if session().memberId?exists>
		                    <a href="${_PATH}/member/index">立即入驻</a>
		                <#else>
		                    <a href="${_PATH}/register?ts=elite">立即入驻</a>
		                </#if>
	    			</div>
	    			<div class="btn_elite">
		    			<img src="/res/images/task/elite_logo.png"" alt="">
		    			<#if session().memberId?exists>
		                    <a href="${_PATH}/member/index">立即入驻</a>
		                <#else>
		                    <a href="${_PATH}/register?ts=elite">立即入驻</a>
		                </#if>
	    			</div>
	    		</div>
	    	</div>
	        <div class="bd-task-title">
	            <div class="btt-content">
	                <div class="btt-content-text">
	                    <span class="dynamic-icon"></span>
	                    <span>公告：如何认领任务，看我这个秘籍就会了，</span>
	                    <a href="${_PATH}/introduce/taskClaim" class="check_a">点击查看详情</a>
	                </div>
	                <#--<div class="btt-content-arrow">
	                    <a class="arrow-l" href="javascript:void(0);"></a>
	                    <a class="arrow-r" href="javascript:void(0);"></a>
	                </div>-->
	            </div>
	        </div>
	        <div class="bd-task-content">
	             <#--<div class="choose-title">
	                <ul>
	                   <li class="choosed" data="project">我要接项目</li>
	                    <li class="choosed" data="task">我要做任务</li>
	                </ul>
	            </div>-->
	            <div class="choose-content">
	                <div class="toPickup" style="display: none">
	                    <div class="pickup-nav">
	                        <div class="pickup-nav-box">
	                            <div class="type clearfloat marginbottom15">
	                                <div class="type-l">开发类型</div>
	                                <ul class="type-r" id="pSolutionUl">
	                                    <li class="selected-color" data="">全部<span class="close-choose"></span></li>
	                                    <@dict type="project_type"></@dict>
						                <#list dictList as dt>
						                    <li data="${dt.dictKey}">${dt.dictVal}<span class="close-choose"></span></li>
						                </#list>
	                                </ul>
	                            </div>
	                            <div class="recruit clearfloat">
	                                <div class="recruit-l">项目状态</div>
	                                <ul class="recruit-r" id="pStatusUl">
	                                    <li class="selected-color" data="">全部</li>
	                                    <li data="">招标中</li>
	                                    <li data="">进行中</li>
	                                    <li data="">已完成</li>
	                                </ul>
	                            </div>
	                        </div>
	                    </div>
	                    <form id="projectListForm" name="projectListForm">
	                        <input type="hidden" id="pSolution" name="projectSolution">
	                        <input type="hidden" id="pStatus" name="status">
		                    <div class="pickup-content">
		                    </div>
	                    </form>
	                </div>
	
	                <div class="toDo">
	                    <div class="todo-nav">
	                        <div class="todo-nav-box">
	                            <div class="type clearfloat marginbottom15">
	                                <div class="type-l">任务类型</div>
	                                <ul class="type-r" id="taskTypeUl">
	                                    <li class="selected-color" data="">全部<span class="close-choose"></span></li>
	                                    <@dict type="task_type"></@dict>
						                <#list dictList as dt>
						                    <li data="${dt.dictKey}">${dt.dictVal}<span class="close-choose"></span></li>
						                </#list>
	                                </ul>
	                            </div>
	
	                            <div class="recruit clearfloat marginbottom15">
	                                <div class="recruit-l">任务状态</div>
	                                <ul class="recruit-r" id="tStatusUl">
	                                    <li class="selected-color" data="">全部</li>
	                                    <li data="wait_recruit">待认领</li>
	                                    <li data="recruit_in">招募中</li>
	                                    <li data="starting">进行中</li>
	                                    <li data="quality">质保期</li>
	                                    <li data="finish">已完成</li>
	                                </ul>
	                            </div>
	                            
	                            <div class="recruit clearfloat">
	                                <div class="recruit-l">角色需求</div>
	                                <ul class="recruit-r" id="tDemandUl">
	                                    <li class="selected-color" data="">全部</li>
	                                    <@dict type="job_role"></@dict>
						                <#list dictList as dt>
						                    <li data="${dt.dictKey}">${dt.dictVal}<span class="close-choose"></span></li>
						                </#list>
	                                </ul>
	                            </div>
	
	                        </div>
	                    </div>
	                    <form id="stageListForm" name="stageListForm">
	                        <input type="hidden" id="taskType" name="taskType">
	                        <input type="hidden" id="tStatus" name="status">
	                        <input type="hidden" id="tDemandType" name="demandType">
		                    <div class="todo-content">
		                    </div>
	                    </form>
	                </div>
	
	            </div>
	        </div>
	    </div>
	</div>
    </@block>

    <@block name="script">
		<script src="${_PATH}/res/script/myjs/hall/index.js"></script>
    </@block>
</@extend>