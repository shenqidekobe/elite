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
			            <div class="process-circle active-process">1</div>
			            <div class="process-circle" style="left:274px;">2</div>
			            <div class="process-circle" style="left:539px;">3</div>
			            <div class="process-circle" style="left:806px;">4</div>
			            <p class="process-title active-title">当前状态</p>
			            <p class="process-title" style="left:266px;">基本信息</p>
			            <p class="process-title" style="left:532px;">项目经历</p>
			            <p class="process-title" style="left:799px;">个人征信</p>
			
			            <div class="process-line"></div>
			            <div class="process-line" style="left:337px;"></div>
			            <div class="process-line" style="left:607px;"></div>
        		</div>
    	</div>
    </div>

    <div class="content">
        

            <form class="form-group form-horizontal form-person paddingtop110 form-box-cto" role="form" id="personFrom">
                <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span>我是</label>
                    <div class="col-xs-9 col-md-9">
                    	<div class="btn-group clearfloat choice-div">
                   				<ul class="theChoice">
                   						<li>web前端</li>
                   						<li>C#</li>
                   				</ul>
                    	</div>
                    	
                    	<div class="join-plan">
                            <div class="agree-checkbox">
                                <img src="/res/images/checkbox_tick.png">
                            </div>
                            <p>&nbsp;&nbsp;参与<span style="color:#FEA600;">《云英汇CTO签约计划》</span>，如果你有项目管理和技术任务分解能力，可以勾选此项</p>
                        </div>
                    	
                        <div class="btn-group">
                            <button type="button" class="btn btn-default">工程师</button>
                            <button type="button" class="btn btn-default active-btn">设计师</button>
                            <button type="button" class="btn btn-default">产品经理</button>
                            <button type="button" class="btn btn-default">项目经理</button>
                        </div>

                        

                        <div class="skill-select">
                            <div class="skill-box">
                                

                                <div class="skills">
                                
                                	
                                	<div class="skill-group clearfloat">
                                			<span>前端开发</span>
                                			<ul>	
                                					  <li class="skill-checkbox"> web前端 </li>
                                					  <li> Flash</li>
                                					  <li> html5</li>
                                					  <li> JavaScript</li>
                                					  <li> U3D</li>
                                					  <li> COCOS2D-X</li>
                                			</ul>
                                	</div>
                                	
                                	<div class="skill-group clearfloat">
                                			<span>后端开发</span>
                                			<ul>
                                					  <li class="skill-checkbox"> Java </li>
                                					  <li> Python</li>
                                					  <li> PHP</li>
                                					  <li>.NET</li>
                                					  <li>C#</li>
                                					  <li>C++</li>
                                					  <li>C</li>
                                					  <li>Perl</li>
                                					  <li>Ruby </li>
                                					  <li>Shell</li>
                                					  <li>搜索算法</li>
                                					  <li>精准推荐</li>
                                					  <li>自然法处理</li>
                                			</ul>
                                	</div>
                                	
                                	
                                	<div class="skill-group clearfloat">
                                			<span>移动开发</span>
                                			<ul>
                                					  <li > </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                			</ul>
                                	</div>
                                	
                                	<div class="skill-group clearfloat">
                                			<span>运维</span>
                                			<ul>
                                					  <li > </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                			</ul>
                                	</div>
                                
                                     <div class="skill-group clearfloat">
                                			<span>DBA</span>
                                			<ul>
                                					  <li > </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                			</ul>
                                	</div> 
                                	
                                	<div class="skill-group clearfloat">
                                			<span>硬件</span>
                                			<ul>
                                					  <li > </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                					  <li> </li>
                                			</ul>
                                	</div>      
                                        
                                             
                                      
                                </div>
                            </div>


                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 相关工作年限</label>
                    <div class="col-xs-7 col-md-7">
                        <img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
                        <span class="check-title">5-7年</span>
                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">7-9年</span>
                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">10年及以上</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 每周可分配时长</label>
                    <div class="col-xs-7 col-md-7">
                        <img src="/res/images/radio_check_icon.png" class="radio-box check-radio">
                        <span class="check-title">10小时以下</span>
                        <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">10-30小时</span>
                         <img src="/res/images/radio_empty_icon.png" class="radio-box check-radio">
                        <span class="check-title">30小时以上</span>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label"><span class="required">* </span> 是否在职</label>
                    <div class="col-xs-5 col-md-5">
                        <div class="btn-group is-work">
                            <button type="button" class="btn btn-default active-btn">是</button>
                            <button type="button" class="btn btn-default">否</button>
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-xs-3 col-md-3 control-label">邀请码 <span class="label-tip">(选填)</span></label>
                    <div class="col-xs-6 col-md-6">
                        <table class="number-table">
                            <tr>
                                <td style="border:none;position: relative;">
                                    <div class="number-box-first" style="border-right:none;">
                                        <input type="text" maxlength="1" style="width:25px;">
                                    </div>
                                </td>
                                <td><input type="text" maxlength="1"></td>
                                <td><input type="text" maxlength="1"></td>
                                <td><input type="text" maxlength="1"></td>
                                <td><input type="text" maxlength="1"></td>
                                <td style="border:none;position: relative;">
                                    <div class="number-box-last" style="border-left:none;">
                                        <input type="text" maxlength="1" style="width:25px;">
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </div>
                </div>

                <div class="form-opt-cto">
                	<a href="javascript:history.go(-1);" style="color: #FEA600;font-size: 18px">上一步</a>
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