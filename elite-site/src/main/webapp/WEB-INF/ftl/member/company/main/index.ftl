<@extend name="companyHomeLayout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/company_main.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/settlement.css">
    </@block>
	<#--右边的操作区-->
	<@block name="rightContent">
	    <div id="dataList"></div>
	    <#include "/rightFloat.ftl">
	</@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/project/operate.js"></script>
        <script src="${_PATH}/res/script/myjs/member/company/main/index.js"></script>
    </@block>
</@extend>