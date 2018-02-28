<@extend name="completeLayout">
    <@block name="cs">
       <link type="text/css" rel="stylesheet" href="${_PATH}/res/style/css/index/ceo_main.css">
    </@block>

    <#--中间正文部分-->
    <@block name="content">

    <div class="process">
    	<div class="box-container-elite">
		        <div class="process-box-elite">
		            <div class="process-circle active-process">1</div>
		            <div class="process-circle" style="left:269px;">2</div>
		            <div class="process-circle" style="left:534px;">3</div>
		            <div class="process-circle" style="left:805px;">4</div>
		            <p class="process-title active-title">当前状态</p>
		            <p class="process-title" style="left:266px;">基本信息</p>
		            <p class="process-title" style="left:532px;">技能<span>|</span>经历</p>
		            <p class="process-title" style="left:799px;">个人征信</p>
		
		            <div class="process-line"></div>
		            <div class="process-line" style="left:337px;"></div>
		            <div class="process-line" style="left:602px;"></div>
		        </div>
        </div>
    </div>
    <div class="content_elite">
        <form class="form-group form-horizontal form-person paddingtop60" role="form" id="personFrom">
            <#--<p style="margin-bottom: 20px;font-size: 12px;" class="form-tip">*不可修改</p>-->
            <input type="hidden" name="id" id="id" value="${obj.id}">
            <input type="hidden" name="jobAge" id="jobAge" value="${obj.jobAge}">
            <input type="hidden" name="allotDuration" id="allotDuration" value="${obj.allotDuration}">
            <input type="hidden" name="onjobed" id="onjobed" value="<#if obj.onjobed>true<#else>false</#if>">
            <input type="hidden" name="eliteJobMaps" id="eliteJobMaps">
            <input type="hidden" name="ctoSigned" id="ctoSigned" value="<#if obj.ctoSigned>true<#else>false</#if>">
            <input type="hidden" name="inviteCode" id="inviteCode" value="${obj.inviteCode}">
            <input type="hidden" id="selectKey" value="${obj.firstJobs.jobRole}">
            <input type="hidden" id="selectSecond" value="${obj.firstJobs.jobAdept}">
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label">
                		<div class="title-input"><span class="required">* </span>我是</div>
                </label>
                <div class="col-xs-9 col-md-9">
                	<div class="btn-group clearfloat choice-div">
           				<ul class="theChoice" style="<#if !obj.firstJobs??>display:none;</#if>"">
	           				<#if obj.firstJobs??>
				                <#list obj.firstJobs.jobAdeptValList as ofj>
				                    <li id="${ofj.key}">${ofj.value}</li>
				                </#list>
			                </#if>
           				</ul>
                	</div>
                	<div class="join-plan">
                        <div class="agree-checkbox">
                            <#if obj.ctoSigned>
                               <img src="${_PATH}/res/images/checkbox_tick.png">
                            <#else>
                               <img src="${_PATH}/res/images/checkbox_untick.png">
                            </#if>
                        </div>
                        <p>&nbsp;&nbsp;参与<span style="color:#FEA600;">《云英汇CTO签约计划》</span>，如果你有项目管理和技术任务分解能力，可以勾选此项</p>
                    </div>
                	
                    <#assign btn_index=""/>
                    <div class="btn-group" id="jobRole_div">
                        <@dict type="job_role"></@dict>
                        <#list dictList as dt>
                           <#if obj.eliteJobs??>
                              <#assign flag="false"/>
                              <#list obj.eliteJobs as oej>
                                  <#if oej.jobRole==dt.dictKey>
                                     <#assign btn_index=dt_index/>
                                  </#if>
                              </#list>
                           </#if>
                           <button type="button" class="btn btn-default" data="${dt.dictKey}" pid="${dt.id}" orders="${dt_index}">${dt.dictVal}</button>
                        </#list>
                    </div>
                    <div class="skill-select" style="display:none;">
                    </div>
                </div>
            </div>
            <input type="hidden" id="btn_index" value="${btn_index}">
            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label">
	                <div class="title-input">
	                	<span class="required">* </span> 相关工作年限
	                </div>		
                </label>
                <div class="col-xs-7 col-md-7">
	                <#--<div class="radioDiv">
	                    <@dict type="job_age"></@dict>
	                    <#list dictList as dt>
	                       <span class="check-custom" style="cursor:pointer" id="jobAge_span">
	                           <img src="${_PATH}/res/images/radio_empty_icon.png" class="radio-box check-radio">
	                           <span class="check-title" data="${dt.dictKey}">${dt.dictVal}</span>
	                       </span>
	                    </#list>
	                </div>-->
	                <div class="text_box">
	                    <div class="text_div" id="year_div"><span class="year_text" id="year_text"></span><span class="triangle_icon"></span></div>
	                    <ul class="text_ul" id="jobAge_span" style="display:none;">
	                    	<#list 1..10 as i>
	                            <li data="${i}" <#if obj.jobAge==i>class="li_text_active"</#if>>${i}年</li>
	                        </#list>
	                        <li data="20" <#if obj.jobAge==20>class="li_text_active"</#if>>10年以上</li>
	                    	<input type="hidden" id="year_val" value="${obj.jobAgeVal}" />
	                    </ul>
	                </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label" style="text-align:right;">
                	<div class="title-input"><span class="required">* </span> 每周可分配时长</div>
                </label>
                <div class="col-xs-9 col-md-9">
            		<div class="radioDiv">
	                    <@dict type="allot_duration"></@dict>
	                    <#list dictList as dt>
	                       <span class="check-custom" style="cursor:pointer" id="duration_span">
	                           <img src="${_PATH}/res/images/radio_empty_icon.png" class="radio-box check-radio">
	                           <span class="check-title" data="${dt.dictKey}">${dt.dictVal}</span>
	                       </span>
	                    </#list>
                    </div>
                </div>
            </div>

            <div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><div class="title-input"><span class="required">* </span> 是否在职</div></label>
                <div class="col-xs-5 col-md-5">
                    <div class="btn-group is-work">
                        <button type="button" class="btn btn-default yes_btn" data="Y" id="yBtn">是</button>
                        <button type="button" class="btn btn-default no_btn" data="N" id="nBtn">否</button>
                    </div>
                </div>
            </div>

            <#--<div class="form-group">
                <label class="col-xs-3 col-md-3 control-label"><div class="title-input">邀请码 <span class="label-tip">(选填)</span></div></label>
                <div class="col-xs-6 col-md-6">
                    <table class="number-table">
                        <tr>
                            <td style="border:none;position: relative;">
                                <div class="number-box-first" style="border-right:none;">
                                    <input type="text" maxlength="1" style="width:25px;" index="1" value="<#if obj.inviteCode??&&obj.inviteCode?length gt 5>${obj.inviteCode?substring(0,1)}</#if>">
                                </div>
                            </td>
                            <td><input type="text" maxlength="1" value="<#if obj.inviteCode??&&obj.inviteCode?length gt 5>${obj.inviteCode?substring(1,2)}</#if>"></td>
                            <td><input type="text" maxlength="1" value="<#if obj.inviteCode??&&obj.inviteCode?length gt 5>${obj.inviteCode?substring(2,3)}</#if>"></td>
                            <td><input type="text" maxlength="1" value="<#if obj.inviteCode??&&obj.inviteCode?length gt 5>${obj.inviteCode?substring(3,4)}</#if>"></td>
                            <td><input type="text" maxlength="1" value="<#if obj.inviteCode??&&obj.inviteCode?length gt 5>${obj.inviteCode?substring(4,5)}</#if>"></td>
                            <td style="border:none;position: relative;">
                                <div class="number-box-last" style="border-left:none;">
                                    <input type="text" maxlength="1" style="width:25px;" value="<#if obj.inviteCode??&&obj.inviteCode?length gt 5>${obj.inviteCode?substring(5,6)}</#if>">
                                </div>
                            </td>
                        </tr>
                    </table>
                </div>
            </div>-->
            <div class="error-tip">
                <span id="tipError"></span>
            </div>
            

            <div class="form-opt-elite">
                <button type="button" class="btn-ok" id="saveBtn">下一步</button>
                <a href="javascript:void(0);" class="skip_btn"  id="skip">暂时跳过</a>
            </div>
        </form>
    </div>
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/elite/current.js"></script>
    </@block>
</@extend>