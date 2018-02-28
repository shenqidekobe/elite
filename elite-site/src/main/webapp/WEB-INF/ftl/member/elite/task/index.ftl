<@extend name="eliteTaskHomeLayout">
    <@block name="cs">
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
        <link rel="stylesheet" href="${_PATH}/res/style/css/project_main.css"/>
        <link rel="stylesheet" href="${_PATH}/res/style/css/elite_main.css"/>
        <link rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    </@block>
    <@block name="rightContent">
    	<input type="hidden" id="projectId" value="${obj.project.id}">
    	<input type="hidden" id="taskId" value="${obj.id}">
		<div id="dataList"></div>
	    <#include "/rightFloat.ftl">
	</@block>
	    
    <@block name="script">
    	<script src="${_PATH}/res/script/myjs/member/elite/task/index.js"></script>
    	<script src="${_PATH}/res/script/myjs/member/elite/main/task.js"></script>
    	<script src="${_PATH}/res/script/myjs/member/elite/task/weekly.js"></script>
        <script src="${_PATH}/res/script/myjs/member/elite/task/material.js"></script>
        <script src="${_PATH}/res/script/myjs/member/elite/task/projectlog.js"></script>
    </@block>
</@extend>