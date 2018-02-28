<@extend name="layout">
    <@block name="cs">
	   <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/index.css">
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    
	<div class="y-bd">
	    <div class="channel_people">
	        <div class="people_title">
	            <div class="title_cover">
	                <div class="title_people_img"></div>
	                <div class="title_text">
	                    <p>推荐精英人才</p>
	                    <p>并在平台上入驻成功</p>
	                    <p>您将获得收益</p>
	                    <a class="collaborate_btn" href="${_PATH}/register/e">我要合作</a>
	                </div>
	            </div>
	        </div>
	        <div class="people_content">
	            <div class="content_people_img"></div>
	        </div>
	    </div>
  </div>

	
    </@block>

    <@block name="script">
         <script src="${_PATH}/res/script/myjs/index.js"></script>
    </@block>
</@extend>