<@extend name="ctoProjectHomeLayout">
    <@block name="cs">
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/script/plugin/autocomplete/jquery-ui.min.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/project_main.css"/>
    	<link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/task.css">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/acceptance.css">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/task_assign.css">
	</@block>
	<#--右边的操作区-->
	<@block name="rightContent">
        <input type="hidden" id="projectId" value="${projectId}">
	    <div id="dataList"></div>
	    <#include "/rightFloat.ftl">
	</@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/cto/project/material.js"></script>
        <script src="${_PATH}/res/script/myjs/member/cto/project/projectlog.js"></script>
        <script src="${_PATH}/res/script/myjs/member/cto/project/weekly.js"></script>
        <script src="${_PATH}/res/script/myjs/member/cto/project/index.js"></script>
        <script src="${_PATH}/res/script/myjs/member/cto/project/task.js"></script>
        <#--ue-->
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.config.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/ueditor.all.min.js"></script>
        <script src="${_PATH}/res/script/plugin/ueditor/lang/zh-cn/zh-cn.js"></script>
    </@block>
</@extend>