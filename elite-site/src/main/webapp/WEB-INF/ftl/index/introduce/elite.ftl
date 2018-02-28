<@extend name="layout">
    <@block name="cs">  
    </@block>
    <@block name="body">
    <#--头部-->
    <@accounthead opt=""/>
    <#--正文开始-->
    <div class="y-bd">
	    <div class="bd_elite_introduce">
	        <div class="elite_cover"></div>
	        <div class="introduce_nav"></div>
	        <div class="introduce_content">
	            <div class="me_cto"></div>
	            <div class="me_elite"></div>
	            <div class="elite_img"></div>
	        </div>
	    </div>
	</div>
    </@block>

    <@block name="script">
        <script src="${_PATH}/res/script/myjs/index.js"></script>
    </@block>
</@extend>