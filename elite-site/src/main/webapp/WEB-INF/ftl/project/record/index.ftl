<@extend name="projectHomeLayout">
    <@block name="cs">
        <link rel="stylesheet" href="${_PATH}/res/style/css/project_main.css"/>
        <link rel="stylesheet" href="${_PATH}/res/script/plugin/datepicker/datepicker3.css"/>
    </@block>
	<#--右边的操作区-->
	<@block name="rightContent">
        <input type="hidden" id="projectId" value="${projectId}">
	    <div id="dataList"></div>
	    <#include "/rightFloat.ftl">
	</@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/project/operate.js"></script>
        <script src="${_PATH}/res/script/myjs/project/record/material.js"></script>
        <script src="${_PATH}/res/script/myjs/project/record/projectlog.js"></script>
        <script src="${_PATH}/res/script/myjs/project/record/weekly.js"></script>
        <script src="${_PATH}/res/script/myjs/project/record/index.js"></script>
        <script src="${_PATH}/res/script/myjs/member/header.js"></script>
        <script src="${_PATH}/res/script/myjs/project/operate.js"></script>
    </@block>
</@extend>