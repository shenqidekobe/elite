<@extend name="completeLayout">
    <@block name="cs">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/public/public.css">
    <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo-main.css">
    </@block>

<#--中间正文部分-->
    <@block name="content">

    <div class="process">
    	<div class="process-box-container">
    			<div class="process-box-cto">
			            <div class="process-circle active-process">
			                <img src="/res/images/tick.png" width="24" height="auto">
			            </div>
			            <div class="process-circle active-process" style="left:274px;">2</div>
			            <div class="process-circle" style="left:539px;">3</div>
			            <div class="process-circle" style="left:806px;">4</div>
			            <p class="process-title active-title">当前状态</p>
			            <p class="process-title active-title" style="left:266px;">基本信息</p>
			            <p class="process-title" style="left:532px;">项目经历</p>
			            <p class="process-title" style="left:799px;">个人征信</p>
			
			            <div class="process-line finish-line"></div>
			            <div class="process-line" style="left:337px;"></div>
			            <div class="process-line" style="left:607px;"></div>
        		</div>
    	</div>
    </div>

    <div class="content">
        
        <div style="position: relative;">
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
                            <input type="text" class="form-control input-unified width300" id="brith"  readonly  placeholder="请选择你的出生年月" style="background-color: white;"/>
                        </span>
                    </div>
                </div>
                
                
                
                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    			<div class="title-input paddingleft30">
                    					<span class="required">* </span> 性别
                    			</div>
                    </label>
                    <div class="col-xs-4 col-md-4">
                    	<div class="sex-box">
    						<div class="man sex-select fl marginright20">
        							<img class="icon" src="../res/images/icon-man.png" alt=""><span >男</span>
        					</div>
    						<div class="woman fl">
        							<img class="icon" src="../res/images/icon-women.png" alt="">
        							<span>女</span>
    						</div>
					</div>
                    </div>
                </div>
                
                

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    		<div class="title-input">
                    				<span class="required">* </span> 所在城市
                    		</div>
                    </label>
                    <div class="col-xs-4 col-md-4">
                        <div class="birth-box">
                            <input class="form-control input-unified width300" type="text" id="city" name="city" placeholder="选择所在城市" style="background-color: white;padding-left:70px;" disabled>
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
                    <p style="margin-top:10px;font-size: 14px;">点击上传头像</p>

                </div>

                <div class="split-line2 line-cto"></div>

                <div class="form-group" style="margin-top:65px;">
                    <label class="col-xs-2 col-md-2 control-label">
                    			<div class="title-input">
                    					<span class="required">* </span> 绑定邮箱
                    			</div>
                    </label>
                    <div class="col-xs-10 col-md-10">
                        <div class="emailBox">
    							<input type="text" placeholder="用于账户登录及项目进展的消息推送" class="input-unified width400">
						</div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-2 col-md-2 control-label">
                    			<div class="title-input">
                    					<span class="required">* </span> 职位头衔</label>
                    			</div>
                    </label>
                   
                    <div class="col-xs-10 col-md-10">
                        <div class="titleBox">
    							<input type="text" placeholder="请输入你的职位头衔" class="input-unified width400" >
						</div>
                    </div>
                    
                    
                </div>

                <div class="form-opt">
                	<a href="javascript:history.go(-1);" style="color: #FEA600;font-size: 18px">上一步</a>
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