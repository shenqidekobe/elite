    
    
        <#--右边的操作区-->
        <div id="inviteContainer">
            <div class="choose_title">
	                <ul class="title_ul" id="title_ul">
	                    <li data="elite" class="active_color">项目备案邀请</li>
	                    <li data="channel">项目渠道备案邀请</li>
	                </ul>
	                <div class="title_line"></div>
            </div>
            <div class="chooseBox">
				<div class="search-box-a" >
		                <div class="your-code yourCode1">
		                	<div class="code-text">我的邀请码</div>
		                	<div class="codeDiv">
		                		<div class="copy-result" id="inviteCode">${code.inviteCode}</div>
								<a class="copy-code" data="${code.inviteCode}"  href="javascript:void(0);" data-clipboard-action="copy" data-clipboard-target="#inviteCode">复制</a>
		                	</div>
		                </div>
		               <input type="hidden" value="${code.href}" id="codeHrefId">
		                <div class="your-code yourCode2">
		                	<div class="code-text">我的邀请连接</div>
		                	<div class="codeDiv">
		                		<div class="copy-result" id="inviteUrl">${code.href}&ic=${code.inviteCode}</div>
								<a class="copy-code" data="${code.href}&ic=${code.inviteCode}" href="javascript:void(0);" data-clipboard-action="copy" data-clipboard-target="#inviteUrl">复制</a>
		                	</div>
		                </div>
		                <div class="add-btn-box" id="elite_choose">
		                	<div class="addDiv" id="elite_add">
		                		<span class="add-icon"></span>
		                    	<div class="add-txt">推荐项目</div>
		                	</div>
		                    <div class="add-txt-b">
		                    	<p>
			                    	<span class="add-icon-b" ></span>
			                    	该项目的所有消费你都可以获得收益，推荐的项目或渠道都可以获得永久推荐收益，<a href="${_PATH}/protocol/companyInviteRule" target="_blank" class="rule-btn">邀请规则</a>
		                    	</p>
		                    </div>
		                 </div>
		                 <div class="add-btn-box" id="channel_choose" hidden>
			                	<div class="addDiv" id="channel_add">
			                		<span class="add-icon"></span>
			                    	<div class="add-txt">推荐项目渠道</div>
			                	</div>
			                    <div class="add-txt-b">
			                    	<p>
				                    	<span class="add-icon-b" ></span>
				                    	该项目的所有消费你都可以获得收益，推荐的项目或渠道都可以获得永久推荐收益，<a href="${_PATH}/protocol/partnerCompanyInviteRule" target="_blank" class="rule-btn">邀请规则</a>
			                    	</p>
			                    </div>
			                 </div>
	                </div>
	                
	                
                 </div>
               <form id="searchForm" name="searchForm">
             <div class="search-box table-search">
                   <input class="form-control" type="text" id="searchWord"  name="keyword" placeholder="手机号/用户名" maxlength="11" style="width:240px;">
                    <span class="date-select"  style="left:310px">
                        <img src="/res/images/date_icon.png" class="date-icon" id="receiptdateStart-date-icon">
                        <input type="text" class="form-control" id="receiptdateStart" name="startTime" placeholder="选择备案时间" style="background-color: white;"/>
                    </span>
                    <span class="dateLine"></span>
                    <span class="date-select" style="left:480px">
                        <img src="/res/images/date_icon.png" class="date-icon" id="receiptdateEnd-date-icon">
                        <input type="text" class="form-control" name="endTime" id="receiptdateEnd" placeholder="备案结束时间" style="background-color: white;">
                    </span>
                    <button type="button" class="search-btn" data="inviteRegister" id="inviteRegisterBtn">
                        <img src="/res/images/search_icon.png" class="search-icon" >&nbsp;&nbsp;搜索
                    </button>
                </div>	
            <#--表格专区-->
            <div class="search-status">
            	<input type="hidden" value="" name="status" id="statusId"/>
            	<input type="hidden" value="elite" name="searchType" id="searchTypeId"/>
            	<input type="hidden" value="true" name="searchTypeCount" id="searchTypeCountId"/>
            	<input type="hidden" value="${company.status}" id="companyStatusId">
            	<div class="selectTitle">
            		<ul id="selectStatusPane">
            			<div class="ul_border" style="width:380px" id="ulBordrId"></div>
	                    <li class="active-status" data=""  index="0" id="allCountId">已备案项目<span class="number">${allCount}</span></li>
	                    <li  index="1" id="registeredCountId" data="registered">已入驻<span class="number">${registeredCount}</span></li>
	                    <li index="2" data="audit_pass" id="enterCountId" hidden>已入驻项目渠道(${enterCount})</li>
                	</ul>
            	</div>
                <div id="datalist">
                </div>
            </div>
                 <input type="file"  id="eliteAttaId" style="display:none;">
           </form>
           <div class="modal fade" id="infoDetail" tabindex="-1" role="dialog" 
				   aria-labelledby="myModalLabel" aria-hidden="true">
				   <div class="modal-dialog">
				      <div class="modal-content">
				         <div class="modal-header">
				            <button type="button" class="close" 
				               data-dismiss="modal" aria-hidden="true">
				                  &times;
				            </button>
				            <h4 class="modal-title" id="myModalLabel">
				               模态框（Modal）标题
				            </h4>
				         </div>
				         <div class="modal-body">
				         </div>
				         <div class="modal-footer">
				            <button type="button" class="btn btn-default" 
				               data-dismiss="modal">关闭
				            </button>
				            <button type="button" class="btn btn-primary">
				               提交更改
				            </button>
				         </div>
				      </div><!-- /.modal-content -->
				</div><!-- /.modal -->
				</div>
				
				
				
				
            <#--新增项目备案模态框-->
            <div class="newRecord" id="newRecord_elite" style="display:none;">   
                    <#--正文-->
                        <form class="newRecord_form"   id="addPersonForm">
                        	<div class="close_btn" id="elite_close_btn"></div>
							<h1><span>新增项目备案</span><span>以下备案信息都将保密</span></h1>
                            <div class="nameBox">
                                <label class="label_div"><span class="required">* </span>项目联系人姓名</label>
                                <div class="input_div">
                                    <input type="text" id="name" name="name" placeholder="请输入姓名" maxlength="20">
                                </div>
                            </div>

                            <div class="phoneBox">
                                <label class="label_div"><span class="required">* </span>联系人手机号</label>
                                <div class="input_div">
                                    <input type="text" id="phone" name="phone" placeholder="请输入11位手机号" maxlength="11">
                                </div>
                            </div>

                            <div class="industryBox">
                            <input type="hidden" name="productIndustry" id="productIndustryId" />
                                <label class="label_div"><span class="required">*</span>项目所属行业<span class="font_small">(少于3个)</span></label>
                                <div class="ul_div">
                                    <ul class="industry_ul" id="industryUlId">
                                      <#list industrylist as industry>
                                    	<li class="default" data="${industry.dictKey}">${industry.dictVal}</li>
                                    	</#list>
                                    </ul>
                                </div>
                            </div>
                            
                            <div class="typeBox">
                                <label class="label_div"><span class="required">*</span><span id="projectTypeShowId">开发类型</span></label>
                                <div class="ul_div">
                                    <ul class="type_ul clearfloat" id="projectTypeSelectId">
                                    <input type="hidden" name="projectSolution" id="projectSolutionId">
                                     <@dict type="project_type"></@dict>
                                	  <#list dictList as stage>
                                    	<li class="default" data="${stage.dictKey}">${stage.dictVal}</li>
                                    	</#list>
                                    </ul>
                                </div>
                            </div>
                            
                            <input type="hidden" name="productStage" id="productStageId" />
				          <div class="phaseBox">
				          		<label class="label_div"><span class="required">*</span>项目所处阶段</label>
								<div class="ul_div">
									<span class="phaseText" id="projectStageShowId">请选择项目所处阶段</span>
									<span class="triangleBtn triangle_down" id="projectStageSelectIconId"></span>
									<ul class="selectUl" id="projectStage_selectUI" hidden >
									 <@dict type="project_filing_stage"></@dict>
                                	<#list dictList as stage>
										<li data="${stage.dictKey}">${stage.dictVal}</li>
										</#list>
									</ul>
								</div>
				           </div>                     
                            <div class="city_div" id="eliteDiv">
                                <label class="label_div"><span class="required">*</span>所在城市</label>
                                <div class="birth-box cityBox cityContainer">
                                        <input type="text" id="areaName" name="areaName" placeholder="选择所在城市">
                                        <img src="${_PATH}/res/images/location_icon.png" class="location">
			                                <ul class="year-select"></ul>
			                                <span class="triangle_down triangleBtn" id="triangleBtn1"></span>
                                  </div>
                            </div>

                             <div class="contactBox">
				          		<label class="label_div"><span class="required">*</span>是否能以您的名义进行联系</label>
								<div class="ul_div">
								    <input type="hidden" name="contactpartner" value="true" id="contactpartnerId">
									<ul class="contact_ul" id="selectContactId">
										<li data="true"><span class="li_icon contact_yes"></span><span class="li_text">是</span></li>
										<li data="false"><span class="li_icon contact_no"></span><span class="li_text">否</span></li>
									</ul>
								</div>
				           </div>      
				           
				           <div class="describeBox">
				          		<label class="label_div"><span class="required">*</span>项目简单描述</label>
								<div class="textarea_div">
									<textarea placeholder="少于1000字" id="projectIntro" style="overflow-y:auto;" name="projectIntro"></textarea>
								</div>
				           </div>      
                            
                            
                            <div class="modal-opt btn_div">
                            	<div class="error_div" id="companyTipErrorId" style="left:42px;" hidden><span class="error_icon"></span><span class="error_text" id="companyTipErrorTextId"></span></div>
                                <button type="button" class="opt-btn-ok" id="saveProjectBtn" style="margin-left:42px;">保存</button>
                                <button type="button" class="opt-btn-cancel" data-dismiss="modal" id="cancelEliteBtn">取消</button>
                            </div>
                        </form>
                    </div>
                    
                    
                    
                    
                    
                    
                      <#--新增项目渠道备案模态框-->
                <div class="newRecord"  id="newRecord_channel" hidden>   
                    <#--正文-->
                        <form class="newRecord_form" id="addChannelForm">
                        	<div class="close_btn" id="channel_close_btn"></div>
							<h1><span>新增项目渠道备案</span><span>以下项目备案信息都将保密</span></h1>
                            <div class="form_group name_group">
                                <label class="label_div"><span class="required">* </span>项目联系人姓名</label>
                                <div class="input_div">
                                    <input type="text" id="channelNameId" name="name" placeholder="请输入姓名" maxlength="10">
                                </div>
                            </div>

                            <div class="form_group">
                                <label class="label_div"><span class="required">* </span>联系人手机号</label>
                                <div class="input_div">
                                    <input type="text" id="channelPhoneId" name="memberPhone" placeholder="请输入11位手机号" maxlength="11">
                                </div>
                            </div>

                            <div class="form_group">
                                <input type="hidden" name="companyType" id="channelDefineRoleId" />
                                <label class="label_div"><span class="required">*</span>身份类型</label>
                                <div class="input_div attributeDiv">
                                	 <div class="birth-box" >
                                        <input type="text" id="channelDefineRole" readonly="readonly">
                                        <div class="birth-year" id="channelDefineRoleShow" >请选择</div>
                                        <ul class="year-select"  id="channelDefineRole-select" style="width:300px;">
                                          <#list typelist as role>
                                                   <li style="width:300px;" data-type="workYear" text="${role}" data="${role}">${role.label}</li>
                                               </#list>
                                         </ul>
                                         <span class="triangleBtn triangle_down" id="channelDefineRole-triangle"></span>
                                    </div>
                                </div>
                            </div>
                            <div class="form_group city_div" id="channelDiv">
                                <label class="label_div"><span class="required">*</span>所在城市</label>
                                <div class="birth-box cityBox cityContainer">
                                    <input type="text" id="areaNameChannel" name="areaName" placeholder="选择所在城市" maxlength="10">
                                    <img src="${_PATH}/res/images/location_icon.png" class="location">
	                                <ul class="year-select"></ul>
	                                <span class="triangle_down triangleBtn" id="triangleBtn1"></span>
                                </div>
                            </div>

                            <div class="form_group">
                                <label class="label_div">机构名称</label>
                                <div class="input_div">
                                   <input type="text" name="companyName" placeholder="请输入所在机构名称" maxlength="32">
                                </div>
                            </div>
                            <div class="modal-opt btn_div">
                            	<div class="error_div"  id="channelErrorTipId" style="display:none;"><span class="error_icon"></span><span class="error_text" id="channelErrorTextId"></span></div>
                                <button type="button" class="opt-btn-ok" id="saveChannelBtn">保存</button>
                                <button type="button" class="opt-btn-cancel" data-dismiss="modal" aria-label="Close" id="cancelChannelBtn">取消</button>
                            </div>
                        </form>
                    </div>
</div>

  <!-- 待完善弹窗 -->
    <div class="window"  id="goprefectBoxId" hidden>
    	<div class="window_bg"></div>
    	<form class="windowForm">
    		<div class="close_btn" id="gocloseId"></div>
    		<div class="logoDiv"><span class="logo failure"></span></div>
    		<div class="textDiv failure_text">你的资料完善度还没达到审核要求，先完善哦~</div>
    		<div class="btnDiv"><button type="button" class="saveBtn" id="goPrefectBtn">去完善</button></div>
    	</form>
    </div>

  <!-- 查看项目描述 弹窗-->
  <div class="window" id="projectInfoBoxId" hidden>
  		<form class="describeForm">
  			<div class="close_btn" id="projectInfoBoxCloseBtn"></div>
  			<h2>查看项目描述</h2>
  			<div class="describe_content" id="projectInfoContetnId"></div>
  		</form>
  </div>