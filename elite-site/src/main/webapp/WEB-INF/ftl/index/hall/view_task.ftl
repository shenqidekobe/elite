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
	    <div class="bd-task-details">
	        <h1><span id="hallIndex" style="cursor:pointer">任务大厅</span>&gt;
		        <span class="color2c marginright10">${obj.name}</span>&gt;
		        <span class="color2c">任务详情</span>
	        </h1>
	        <div class="btd-content">
	            <div class="btd-content-a">
	                <div class="content-a-l">
	                    <p class="font-size30 colorbl marginbottom15">${obj.name}</p>
	                    <p class="font-size12 marginbottom25 relative">发布时间：
		                    <span class="marginright40">${obj.createTime?string("yyyy-MM-dd")} &nbsp;&nbsp;${obj.createTime?string("HH:mm")}</span>
		                    <span class="word-line1"></span>任务编号：<span>${obj.taskNum}</span>
	                    </p>
	                    <p class="marginbottom19 relative">所需精英：<span class="font-size16 marginright32">${obj.demandTypeVal}</span>
		                    <span class="word-line2"></span>任务类型：<span class="font-size16 marginright32">${obj.taskTypeVal}</span>
		                    <span class="word-line3"></span>报酬<span class="font-size16 color2c">${obj.amount?string.currency}</span>
	                    </p>
	                    <p>交付日期周期：<span>${obj.startTime?string("yyyy-MM-dd")}至${obj.deliveryTime?string("yyyy-MM-dd")}</span></p>
	                </div>
	                <input type="hidden" id="deadlineTime" value="${deadlineTime}">
	                <div class="content-a-r">
	                    <p class="marginbottom18">
	                        <#assign statusLabel=""/>
			                <#if obj.status=='wait_recruit'>
			                    <#assign statusLabel="待认领"/>
			                <#elseif obj.status=='recruit_in'>
			                    <#assign statusLabel="招募中"/>
			                <#elseif obj.status=='starting'||obj.status=='wait_accept'||obj.status=='accept_success'>
			                    <#assign statusLabel="任务进行中"/>
			                <#elseif obj.status=='finish'>
			                    <#assign statusLabel="已完成"/>
			                </#if>
		                    <span class="font-size20 marginright18 color2c">${statusLabel}</span>
		                    <span id="applyCount">${applyCount}人报名</span>
	                    </p>
	                    <#if obj.status=='wait_recruit'||obj.status=='recruit_in'>
		                    <p class="marginbottom39 timeDiv clearfloat">
			                    <span class="time-icon"></span>
			                    <span class="time-text" id="deadlineSpan"></span>
		                    </p>
		                    <p class="recruit-btn">
		                        <#if applyFlag=='N'>
			                        <a href="javascript:void(0);" class="select" id="apply" data="${obj.id}" oper="Y">我要报名</a>
		                        <#else>
		                            <a href="javascript:void(0);" class="select" id="apply" data="${obj.id}" oper="N">取消报名</a>
		                        </#if>
			                    <#--<a href="#">推荐给他人</a>-->
		                    </p>
			            </#if>
	                </div>
	                <#--<div class="praiseDiv">
	                    <span class="praise-icon"></span>
	                    <span class="praise-text">收藏</span>
	                </div>-->
	            </div>
	            <div class="btd-content-b">
	                <h2>任务描述</h2>
	                <div class="bcb-box1">
	                    <div class="box1-content">
	                        <p>${obj.intro}</p>
	                    </div>
	
	                </div>
	                <#if applyFlag=='Y'>
		                <#if obj.attaId??>
			                <div class="bcb-box2">
			                    <h3>附件下载</h3>
			                    <div class="book"><span class="book-icon"></span>${obj.atta.fileName}<span class="download-icon"  data="${obj.atta.downPath}"></span></div>
			                </div>
		                </#if>
                    </#if>
	            </div>
	        </div>
	        <div class="page-icon"></div>
	    </div>
	</div>
    </@block>

    <@block name="script">
		<script src="${_PATH}/res/script/myjs/hall/task.js"></script>
    </@block>
</@extend>
