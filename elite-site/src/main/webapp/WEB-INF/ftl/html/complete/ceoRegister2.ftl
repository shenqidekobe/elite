<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/public/public.css">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo-main.css">
    </@block>

<#--中间正文部分-->
    <@block name="content">

        <div class="process">
        	<div class="process-box-container">
        			<div class="process-box">
			                <div class="process-circle active-process">
			                    <img src="/res/images/tick.png" width="24" height="auto">
			                </div>
			                <div class="process-circle active-process" style="left:274px;">2</div>
			                <div class="process-circle" style="left:539px;">3</div>
			                <p class="process-title active-title">基本资料</p>
			                <p class="process-title active-title" style="left:266px;">创业属性</p>
			                <p class="process-title" style="left:532px;">征信信息</p>
			
			                <div class="process-line finish-line"></div>
			                <div class="process-line" style="left:340px;"></div>
            		</div>
        	</div>
        </div>

        <div class="content">
            
            <#--选择已注册或未注册的公司-->
            <div class="select-status">
               <span class="select">已注册公司</span><span style="margin-left:221px;">尚未注册公司</span>
                <div class="select-line"></div>
                <div class="active-line"></div>
            </div>

            <form class="form-group form-horizontal form-person paddingtop30" role="form" id="companyFrom" >
                <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    	<div class="title-input">
                    			<span class="required">* </span> 头衔/职位 <span class="secret-circle">密</span>
                    	</div>
                    </label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control input-unified width400" type="text" id="name" name="name" placeholder="你在公司担任的角色" maxlength="20">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    	<div class="title-input">
                    			<span class="required">* </span> 公司名称<span class="secret-circle">密</span>
                    	</div>
                    </label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control input-unified width400" type="text" id="name" name="name" placeholder="你的公司名称" maxlength="20">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    	<div class="title-input">
                    			<span class="required">* </span> 公司规模<span class="secret-circle">密</span>
                    	</div>
                    </label>
                    <div class="col-xs-9 col-md-9">
                    	<div class="radioDiv">
                    			<img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
		                        <span class="check-title">10人以下</span>
		                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
		                        <span class="check-title">11-50人</span>
		                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
		                        <span class="check-title">51-100人</span>
		                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
		                        <span class="check-title">100人以上</span>
                    	</div>
                    </div>
                </div>


                <div class="form-group paddingtop19">
                    <label class="col-xs-2 col-md-2 control-label">
                    	<div class="title-input paddingleft12">
                    			 公司简介<span class="secret-circle">密</span>
                    	</div>
                    </label>
                    <div class="col-xs-7 col-md-7">
                        <textarea class="form-control textarea-unified width506"  rows="8"  placeholder="简单介绍一下你的公司，投资人可能会作简单浏览 (少于200字)" maxlength="200"></textarea>
                    </div>
                </div>

                <div class="form-opt paddingtop50">
                	<a href="javascript:history.go(-1);" class="colorFE font-size18">上一步</a>
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="#" style="font-size: 14px;" class="color2c">暂时跳过</a>
                </div>
            </form>


            <form class="form-group form-horizontal form-person paddingtop60" role="form" id="companyFrom2" style="display: none;" >
            <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    	<div class="title-input">
                    			<span class="required">* </span> 你的职位 <span class="secret-circle">密</span>
                    	</div> 
                    </label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control input-unified width400" type="text" id="name" name="name" placeholder="你在项目中担任的角色" maxlength="20">
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    	<div class="title-input"><span class="required">* </span> 团队人数<span class="secret-circle">密</span></div>
                    </label>
                    <div class="col-xs-9 col-md-9">
                    		<div class="radioDiv">
                    				<img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
			                        <span class="check-title">10人以下</span>
			                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
			                        <span class="check-title">11-50人</span>
			                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
			                        <span class="check-title">51-100人</span>
			                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
			                        <span class="check-title">100人以上</span>
                    		</div>
                    </div>
                </div>


                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    		<div class="title-input">
                    				<span style="padding-left:10px;">团队介绍</span><span class="secret-circle">密</span>
                    		</div>
                    </label>
                    <div class="col-xs-7 col-md-7">
                        <textarea class="form-control textarea-unified width506 " rows="6" placeholder="简单介绍一下你的公司，投资人可能会作简单浏览 (少于200字)" maxlength="200"></textarea>
                    </div>
                </div>

                
                <div class="form-opt paddingtop50">
                	<a href="javascript:history.go(-1);" class="colorFE fontsize18">上一步</a>
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="#" style="font-size: 14px;" class="color2c">暂时跳过</a>
                </div>
                
            </form>

        </div>
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>