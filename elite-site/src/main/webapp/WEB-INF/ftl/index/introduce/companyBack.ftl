<@extend name="layout">
    <@block name="cs">
	   <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    <div class="y-bd">
	    <div class="bd_project_introduce">
	        <div class="project_cover"></div>
	        <div class="introduce_nav"></div>
	        <div class="introduce_content">
	            <div class="me_project"></div>
	            <div class="project_img"></div>
	        </div>
	    </div>
	</div>
    </@block>

    <@block name="script">
         <script src="${_PATH}/res/script/myjs/index.js"></script>
    </@block>
</@extend>