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
		                <div class="process-circle active-process">1</div>
		                <div class="process-circle" style="left:274px;">2</div>
		                <div class="process-circle" style="left:539px;">3</div>
		                <p class="process-title active-title">基本资料</p>
		                <p class="process-title" style="left:266px;">创业属性</p>
		                <p class="process-title" style="left:532px;">征信信息</p>
		                <div class="process-line"></div>
		                <div class="process-line" style="left:340px;"></div>
                   </div>	
        	</div>
        </div>

        <div class="content">
            
            <div style="position: relative;" class="content-box">
                <form class="form-group form-horizontal form-person paddingtop60" role="form" id="personFrom">
                
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
			                    <div class="title-input">
			                    		<span class="required">* </span> 出生年月<span class="secret-circle">密</span>
			                    </div>
                    </label>
                    <div class="col-xs-4 col-md-4">
                         <span class="date-select">
                            <img src="/res/images/date_icon.png" class="date-icon">
                            <input type="text" class="form-control input-unified width300" id="brith"  readonly  placeholder="请选择你的出生年月" style="background-color: #fff;">
                        </span>
                    </div>
                </div>

                <div class="form-group
                
                ">
                    <label class="col-xs-2 col-md-2 control-label">
                    			<div class="title-input"><span class="required">* </span> 所在城市</div>
                    </label>
                    <div class="col-xs-4 col-md-4">
                        <div class="birth-box">
                            <input class="form-control input-unified width300" type="text" id="city" name="city" placeholder="选择所在城市" style="background-color: white;padding-left:50px;" disabled>
                            <img src="/res/images/location_icon.png" class="location">
                        <#--<div class="birth-year" id="workYear">6&nbsp;年</div>-->
                            <div>
                                <ul class="year-select">
                                <#--<li data-type="workYear" style="padding-right:26px;">${i?c}&nbsp;年</li>-->
                                </ul>

                                <div class="form-triangle">
                                    <div class="form-trigger"></div>
                                </div>
                            </div>

                        </div>
                    </div>
                </div>

                <#--上传头像-->
                <div class="upload-head">
                    <img src="/res/images/head.png">
                    <p style="margin-top:20px;font-size: 16px;">点击上传头像</p>

                </div>

                

                <div class="form-group paddingbottom15" >
                    <label class="col-xs-2 col-md-2 control-label">
                    			<div class="title-input">
                    					<span class="required">* </span> 绑定邮箱
                    			</div>
                    </label>
                    <div class="col-xs-5 col-md-5">
                        <input class="form-control input-unified width400" type="text" id="name" name="name" placeholder="用于账户登录及项目进展的消息推送" maxlength="20">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
             			<div class="title-input"><span class="required">* </span>头衔/职位</div>	`       
                    </label>
                    <div class="col-xs-6 col-md-6">
                        <input class="form-control input-unified width400" type="text" id="name" name="name" placeholder="请输入你的职位头衔，少于20个字" maxlength="20">
                    </div>
                </div>

                <div class="form-opt paddingtop50">
					<a href="javascript:history.go(-1);" class="colorFE font-size18">上一步</a>
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="#" style="font-size: 14px;" class="color2c">暂时跳过</a>
                </div>
            </form>
            </div>

        </div>
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>