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
			            <div class="process-circle active-process" style="left:274px;">
			                <img src="/res/images/tick.png" width="24" height="auto">
			            </div>
			            <div class="process-circle active-process" style="left:539px;">3</div>
			            <p class="process-title active-title">基本资料</p>
			            <p class="process-title active-title" style="left:266px;">创业属性</p>
			            <p class="process-title" style="left:532px;">征信信息</p>
			
			            <div class="process-line finish-line"></div>
			            <div class="process-line finish-line" style="left:340px;"></div>
        		</div>
    	</div>
        
    </div>

    <div class="content">
        


        <form class="form-group form-horizontal form-person paddingtop60" role="form" id="companyFrom">
        <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
        	
	        	<div class="form-group">
					    <label class="col-xs-2 col-md-2 control-label">
					        <div class="title-input"><span class="required">* </span> 真实姓名 <span class="secret-circle">密</span></div>
					    </label>
					    <div class="col-xs-4 col-md-4">
					        <input class="form-control input-unified width300 " type="text" id="name" name="name" placeholder="与身份证上姓名保持一致" maxlength="20">
					    </div>
				</div>
        	
        	
            <div class="form-group">
                <label class="col-xs-2 col-md-2 control-label">
                	<div class="title-input">
                			<span class="required">* </span> 身份证号 <span class="secret-circle">密</span>
                	</div>
                    
                </label>
                <#--<div class="col-xs-5 col-md-5">-->
                    <#--<input class="form-control" type="text" id="name" name="name" placeholder="18位二代身份证号" maxlength="18">-->
                <#--</div>-->

                <div class="col-xs-5 col-md-5">
                   <input class="form-control input-unified width400" type="text" id="name" name="name" placeholder="18位二代身份证号" maxlength="18">
                </div>
            </div>

            <div class="form-group" style="margin-bottom: 10px;">
                <label class="col-xs-2 col-md-2 control-label">
                			<div class="title-input">
                					 上传认证资料<span class="secret-circle" style="right:-10px;">密</span>
                			</div>
                </label>
            </div>

           <div class="theme-line"></div>

            <div class="photos">
                <div class="photo-row">
                    <div class="photo-box">
                        <p><span class="required">* </span> 身份证号正面</p>
                        <div class="photo">
                            <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p><span class="required">* </span> 身份证号反面</p>
                        <div class="photo">
                           <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>
                </div>

                <div class="photo-row" style="margin-top:20px;">
                    <div class="photo-box">
                        <p>营业执照</p>
                        <div class="photo">
                           <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p>工作证正面</p>
                        <div class="photo">
                           <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>

                    <div class="photo-box">
                        <p>名片正面</p>
                        <div class="photo">
                           <img src="/res/images/target_icon.png" class="photo-add">
                        </div>
                    </div>
                </div>

            </div>

            
            <div class="form-opt paddingtop103">
            	<a href="javascript:history.go(-1);" style="color: #FEA600;font-size: 18px">上一步</a>
                <button type="button" class="btn-ok-credit" id="saveBtn">创建完成！去发布项目</button>
                <a href="#" style="font-size: 14px;" class="color2c">暂时跳过</a>
            </div>
        </form>


    </div>
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>