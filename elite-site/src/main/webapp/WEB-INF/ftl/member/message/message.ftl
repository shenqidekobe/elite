<@extend name="messageLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/message/message.css">
    </@block>
    <#--右边的操作区-->
    <@block name="rightContent">
        <div id="dataList"></div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/member/message.js"></script>
        <script src="${_PATH}/res/script/myjs/member/header.js"></script>
    </@block>
</@extend>