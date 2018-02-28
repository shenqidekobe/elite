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
			            <div class="process-circle active-process" style="left:274px;">
			                <img src="/res/images/tick.png" width="24" height="auto">
			            </div>
			            <div class="process-circle active-process" style="left:539px;">3</div>
			            <div class="process-circle" style="left:806px;">4</div>
			            <p class="process-title active-title">当前状态</p>
			            <p class="process-title active-title" style="left:266px;">基本信息</p>
			            <p class="process-title active-title" style="left:532px;">项目经历</p>
			            <p class="process-title" style="left:799px;">个人征信</p>
			
			            <div class="process-line finish-line"></div>
			            <div class="process-line finish-line" style="left:337px;"></div>
			            <div class="process-line" style="left:607px;"></div>
        		</div>
    	</div>
    </div>

    <div class="content">
        
            <form class="form-group form-horizontal form-person form-box-cto paddingtop60" role="form" id="personFrom">
               
                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label">
                    		<div class="title-input">
                    				<span class="required">* </span> 关注行业<span class="label-tip">&nbsp;(少于3个)</span>
                    		</div>
                    </label>
                    <div class="col-xs-8 col-md-8">
                        <div class="belong-class">
                            <div class="select-box">
                                <button type="button" class="active-btn" data-class="education">教育</button>
                                <button type="button" data-class="finance">金融</button>
                                <button type="button" data-class="car">汽车</button>
                                <button type="button" data-class="logistics">物流</button>
                                <button type="button" data-class="coffee">餐饮</button>
                                <button type="button" data-class="tour">旅游</button>
                                <button type="button" data-class="tour">游戏</button>
                                <button type="button" data-class="tour">硬件</button>
                                <button type="button" data-class="tour">视频</button>
                                <button type="button" data-class="tour">音乐</button>
                                <button type="button" data-class="tour">体育</button>
                                <button type="button" data-class="tour">社交</button>
                                <button type="button" data-class="tour">服务器</button>
                                <button type="button" data-class="tour">数据库</button>
                                <button type="button" data-class="tour">房产服务</button>
                                <button type="button" data-class="tour">医疗健康</button>
                                <button type="button" data-class="tour">信息分类</button>
                                <button type="button" data-class="tour">电子商务</button>
                                <button type="button" data-class="tour">企业服务</button>
                                <button type="button" data-class="tour">其他</button>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="form-group relative">
                    <label class="col-xs-3 col-md-3 control-label">
                    		<div class="title-input"><span class="required">* </span> 项目经历</div>
                    </label>
                    <div class="col-xs-6 col-md-6">
                        <div class="append-tr">
                        	<p>
                        			<span class="font-size16 marginright20">“最亮的明天”暖灯行动</span>
                            		<span class="font-size16">项目负责人</span>
                        	</p>
                            <p>
                            		<span class="marginright32">产品经理</span>
                            		<span class="font-size12 color9b">2014.02-2015.06</span>
                            </p>
                            <div class="line-append"></div>
                            <ol>
                            		<li>主导QQ邮箱的版本迭代，并负责主要需求、协调开发、设计资源，推动项目进度，保证产品质量，打磨细节体验</li>
                            		<li>参与讨论及制定QQ邮箱的各版本需求，根据行业热点及用户反馈，提出有效的产品方案</li>
                            		<li>负责版本上线后的推广工作，通过微博、博客和论坛等渠道将新功能触达用户</li>
                            		<li>跟进用户反馈并分析数据，持续优化已有功能</li>
                            </ol>
                        </div>

                        <div class="add-tr">
                            <img src="/res/images/add_icon2.png" id="addIcon"> &nbsp;添加
                        </div>
                    </div>
                    
                    <div class="append-btn-box">
					    <span class="add-btn"></span>
					    <span class="delete-btn"></span>
					</div>
					                    
                    
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label">
                    		<div class="title-input">自我描述</div>
                    </label>
                    <div class="col-xs-6 col-md-6">
                       <textarea class="form-control textarea-describe width540" rows="5" placeholder=" 少于50字" maxlength="50"></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label">
                    			<div class="title-input">附件简历</div>
                    </label>
                    <div class="col-xs-4 col-md-4">
                        <div class="upload-resume">
                            <img src="/res/images/upload_icon.png" width="20" height="20">
                            <span style="margin-left:10px;">上传附件简历</span>
                        </div>
                    </div>
                </div>

                <div class="form-opt-cto3">
                	<a href="javascript:history.go(-1);" style="color: #FEA600;font-size: 18px">上一步</a>
                    <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                    <a href="#" style="font-size: 14px;" class="color2c">暂时跳过</a>
                </div>
            </form>

    </div>



    <#--添加项目经历模态框-->
    <div class="container">
        <div class="row">
            <div class="modal fade" data-backdrop="true" role="dialog" aria-labelledby="myModalLabel"   id="addProjectDialog" style="top:50%;margin-top:-250px;">
                <div class="modal-dialog" style="margin:0 auto;">
                    <div class="modal-content" style="width:580px; height:400px;margin:0 auto;">
                        <div class="modal-body" style="padding:0;height:100%;border-radius: 4px;">
                        <#--<button type="button" class="close" style="color:white;top: -18px;right: -20px;position: absolute;outline: none;" data-dismiss="modal" aria-label="Close">-->
                        <#--<span class="glyphicon glyphicon-remove-circle closeBtn" aria-hidden="true"></span>-->
                        <#--</button>-->

                            <img src="/res/images/close_icon.png" class="close-icon" data-dismiss="modal" aria-label="Close">
                        <#--正文-->
                            <form class="form-group form-horizontal" role="form" id="addProjectForm" style="padding-top:30px;">

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 公司或项目名称</label>
                                    <div class="col-xs-7 col-md-7">
                                        <input class="form-control" type="text" id="name" name="name" placeholder="公司或项目名称" maxlength="20">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 担任的职位</label>
                                    <div class="col-xs-7 col-md-7">
                                        <input class="form-control" type="text" id="phone" name="phone" placeholder="担任的职位" maxlength="11">
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 在职或项目时间</label>
                                    <div class="col-xs-7 col-md-7">
                                         <span class="date-select">
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="起始时间" style="background-color: white;"/>
                                        </span>

                                        <span style="float:left;color:white;line-height: 34px;margin:0 5px;">-</span>
                                         <span class="date-select">
                                            <img src="/res/images/date_icon.png" class="date-icon">
                                            <input type="text" class="form-control" id="receiptdate"  readonly  placeholder="截止时间" style="background-color: white;"/>
                                        </span>
                                    </div>
                                </div>

                                <div class="form-group">
                                    <label class="col-xs-4 col-md-4 control-label"><span class="required">* </span> 项目描述</label>
                                    <div class="col-xs-7 col-md-7">
                                       <textarea class="form-control" rows="5" placeholder="截止时间"></textarea>
                                    </div>
                                </div>


                                <div class="error-tip">请输入姓名</div>

                                <div class="modal-opt">
                                    <button type="button" class="opt-btn-ok" id="saveBtn">保存</button>
                                    <button type="button" class="opt-btn-cancel" data-dismiss="modal" aria-label="Close">取消</button>
                                </div>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    </@block>

    <@block name="script">
    <script  src="${static('/scripts/yunyinghui/test.js')}"></script>
    </@block>
</@extend>