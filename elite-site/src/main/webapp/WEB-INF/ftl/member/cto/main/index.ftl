<@extend name="eliteHomeLayout">
    <@block name="cs">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/cto_main.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite_main.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/attention.css"/>
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/settlement/settlement.css">
        <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/elite/team_list.css"/>
    </@block>
    <#--右边的操作区-->
    <@block name="rightContent">
    <div id="projectList"></div>
    
    <#--<#include "/rightFloat.ftl">-->
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/cto/main/index.js"></script>
        <script src="${_PATH}/res/script/myjs/member/elite/main/task.js"></script>
        <script src="${_PATH}/res/script/myjs/member/attention/attention.js"></script>
        <script src="${_PATH}/res/script/myjs/member/cto/team/team.js"></script>
    </@block>
</@extend>