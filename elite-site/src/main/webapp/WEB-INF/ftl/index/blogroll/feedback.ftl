<@extend name="layout">
    <@block name="cs">
	    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
	    <#--正文开始-->
	    <form id="feedbackForm" name="feedbackForm">
	      <input type="hidden" id="content" name="content">
	    </form>
    </@block>

    <@block name="script">
         <script src="${_PATH}/res/script/myjs/feedback.js"></script>
    </@block>
</@extend>