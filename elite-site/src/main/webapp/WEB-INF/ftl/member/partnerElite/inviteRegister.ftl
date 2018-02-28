    
    
        <#--右边的操作区-->
        <div id="inviteContainer">
            <div class="choose_title">
	                <ul class="title_ul" id="title_ul">
	                    <li data="elite" class="active_color">精英注册邀请</li>
	                    <li data="channel">人才渠道注册邀请</li>
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
		                <div class="your-code yourCode2">
		                	<div class="code-text">我的邀请连接</div>
		                	<div class="codeDiv">
		                		<div class="copy-result" id="inviteUrl">${code.href}&ic=${code.inviteCode}</div>
								<a class="copy-code" data="${code.href}&ic=${code.inviteCode}" href="javascript:void(0);" data-clipboard-action="copy" data-clipboard-target="#inviteUrl">复制</a>
		                	</div>
		                </div>
		                  <input type="hidden" value="${code.href}" id="codeHrefId">
		                <div class="add-btn-box" id="elite_choose">
		                	<div class="addDiv" id="elite_add">
		                		<span class="add-icon"></span>
		                    	<div class="add-txt">新增精英人才备案</div>
		                	</div>
		                    <div class="add-txt-b">
		                    	<p>
			                    	<span class="add-icon-b" ></span>
			                    	精英注册干活，您可获得永久推荐收益，<a href="${_PATH}/protocol/eliteInviteRule" target="_blank" class="rule-btn">邀请规则</a>
		                    	</p>
		                    </div>
		                 </div>
		                 <div class="add-btn-box" id="channel_choose" hidden>
			                	<div class="addDiv" id="channel_add">
			                		<span class="add-icon"></span>
			                    	<div class="add-txt">新增人才渠道备案</div>
			                	</div>
			                    <div class="add-txt-b">
			                    	<p>
				                    	<span class="add-icon-b" ></span>
				                    	精英注册干活，您可获得永久推荐收益，<a href="${_PATH}/protocol/partnerEliteInviteRule" target="_blank" class="rule-btn">邀请规则</a>
			                    	</p>
			                    </div>
			                 </div>
	                </div>
	                
	                
	                
	                
                 </div>
               <form id="searchForm" name="searchForm">
                <div class="search-box table-search">
                    <input class="form-control" type="text" id="searchWord"  name="keyword" placeholder="手机号/用户名" maxlength="11" style="width:240px;">
                    <span class="date-select" style="left:310px">
                        <img src="/res/images/date_icon.png" class="date-icon" id="receiptdateStart-date-icon">
                        <input type="text" class="form-control" id="receiptdateStart" name="startTime" placeholder="备案开始时间" style="background-color: white;"/>
                    </span>
                    <span class="dateLine"></span>
                    <span class="date-select" style="left:480px">
                        <img src="/res/images/date_icon.png" class="date-icon" id="receiptdateEnd-date-icon">
                        <input type="text" class="form-control" id="receiptdateEnd" name="endTime" placeholder="备案结束时间" style="background-color: white;"/>
                    </span>
                    <button type="button" class="search-btn" data="inviteRegister">
                        <img src="/res/images/search_icon.png" class="search-icon">&nbsp;&nbsp;搜索
                    </button>
                </div>			
            <#--表格专区-->
            <div class="search-status">
            <!--hideen 隐藏的数据-->
            	<input type="hidden" value="" name="status" id="statusId"/>
            	<input type="hidden" value="elite" name="searchType" id="searchTypeId"/>
               <input type="hidden" value="true" name="searchTypeCount" id="searchTypeCountId"/>
               <input type="hidden" value="${elite.status}" id="eliteStatusId"/>
            	<div class="selectTitle">
            		<ul id="selectStatusPane">
            			<div class="ul_border"></div>
	                    <li class="active-status" data=""  index="0" id="allCountId">已备案精英 (${allCount})</li>
	                    <li  index="1" id="registeredCountId" data="registered">已注册精英 (${registeredCount})</li>
	                    <li index="2" data="audit_pass" id="enterCountId">已入驻精英(${enterCount})</li>
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
				
				
				
				
            <#--新增精英备案模态框-->
            <div class="newRecord" id="newRecord_elite" style="display:none;">   
                    <#--正文-->
                        <form class="newRecord_form" id="addPersonForm" >
                        	<div class="close_btn" id="elite_close_btn"></div>
							<div class="form_bg"></div>
							<h1><span>新增精英备案</span><span>以下人才备案信息都将保密</span></h1>
                            <div class="form_group name_group">
                                <label class="label_div"><span class="required">* </span> 姓名</label>
                                <div class="input_div">
                                    <input type="text" id="name" name="name" placeholder="请输入姓名" maxlength="10">
                                </div>
                            </div>

                            <div class="form_group">
                                <label class="label_div"><span class="required">* </span> 手机号</label>
                                <div class="input_div">
                                    <input type="text" id="phone" name="phone" placeholder="请输入11位手机号" maxlength="11">
                                </div>
                            </div>

                            <div class="form_group">
                                <label class="label_div"><span class="required"></span> 身份证号</label>
                                <div class="input_div">
                                    <input type="text" id="idCard" name="idCard" placeholder="请输入身份证号码" maxlength="18">
                                </div>
                            </div>

                            <div class="hr-line2"></div>

                            <div class="form_group people_div">
                            	<input type="hidden" name="defineRole" id="defineRoleId" />
                                <label class="label_div"><span class="required">*</span>人才属性</label>
                                <div class="input_div attributeDiv">
                                	 <div class="birth-box" >
                                        <input type="text" id="defineRole" readonly="readonly" style="width:160px;">
                                        <div class="birth-year" id="defineRoleShow" >请选择</div>
                                        <ul class="year-select"  id="defineRole-select">
                                          <#list rolelist as role>
                                                   <li data-type="workYear" text="${role.dictVal}" data="${role.dictKey}">${role.dictVal}</li>
                                               </#list>
                                  
                                         </ul>
                                         <div class="modal-triangle" id="defineRole-triangle">
                                                <div class="modal-trigger"></div>
                                         </div>
                                    </div>
                                </div>
                                <input type="hidden" name="jobAge" id="jobAge" />
                                <label class="label_div"><span class="required">*</span>从业年限</label>
                                <div class="input_div attributeDiv">
                                    <div class="birth-box" >
                                        <input type="text" id="birth" readonly="readonly" style="width:160px;">
                                        <div class="birth-year" id="workYear" >请选择</div>
                                        <ul class="year-select"  id="workYear-select">
                                                <#list 1..10 as i>
                                                    <li data-type="workYear" data="${i}">${i}年</li>
                                                </#list>
                                                   <li data-type="workYear" data="${20}">10年以上</li>
                                         </ul>
                                         <div class="modal-triangle" id="workyear-triangle">
                                                <div class="modal-trigger"></div>
                                         </div>
                                    </div>
                                </div>
                                
                                
                            </div>

                           
                            <div class="form_group city_div" style="margin-left:0px;" id="eliteDiv">
                                <label class="label_div"><span class="required">*</span>所在城市</label>
                                <div class="birth-box cityBox cityContainer">
                                        <input type="text" id="areaName" name="areaName" placeholder="选择所在城市">
                                        <img src="${_PATH}/res/images/location_icon.png" class="location">
			                            <span class="triangle_down triangleBtn" id="triangleBtn1"></span>
                                  </div>
                            </div>

                            <div class="form_group">
                            	<input type="hidden" name="attaId" id="resumeAattaId" />
                                <label class="label_div"><span class="required"></span> 上传简历</label>
                                <div class="input_div upload_div">
                                    <div class="upload-resume" onclick="addPersonForm.resumeAttaFile.click()">
                                        <img src="/res/images/upload_icon.png" width="20" height="20" class="upload_icon">
                                        <input type="file" name="file" id="resumeAttaFile" style="display:none;">
                                        <span id="show" class="upload_text">上传附件简历</span>
                                    </div>
                                    <div class="upload-button" style="display:none;" id="resumeAtta_show"></div>
                                </div>
                            </div>
                            <div class="modal-opt btn_div">
                            	<div class="error_div" id="eliteTipErrorId" hidden><span class="error_icon"></span><span class="error_text" id="eliteTipTextId"></span></div>
                                <button type="button" class="opt-btn-ok" id="saveEliteBtn">保存</button>
                                <button type="button" class="opt-btn-cancel" data-dismiss="modal" id="cancelEliteBtn">取消</button>
                            </div>
                        </form>
                    </div>
                    
                    
                    
                    
                    
                    
                      <#--新增人才备案模态框-->
                <div class="newRecord"  id="newRecord_channel" hidden>   
                    <#--正文-->
                        <form class="newRecord_form" id="addChannelForm_elite">
                        	<div class="close_btn" id="channel_close_btn"></div>
							<div class="form_bg"></div>
							<h1><span>新增人才渠道备案</span><span>以下人才备案信息都将保密</span></h1>
                            <div class="form_group name_group">
                                <label class="label_div"><span class="required">* </span> 姓名</label>
                                <div class="input_div">
                                    <input type="text" id="channelNameId" name="name" placeholder="请输入姓名" maxlength="20">
                                </div>
                            </div>

                            <div class="form_group">
                                <label class="label_div"><span class="required">* </span> 手机号</label>
                                <div class="input_div">
                                    <input type="text" id="channelPhoneId" name="memberPhone" placeholder="请输入11位手机号" maxlength="11">
                                </div>
                            </div>

                            <div class="form_group">
                                <input type="hidden" name="type" id="channelDefineRoleId" />
                                <label class="label_div"><span class="required">*</span>人才属性</label>
                                <div class="input_div attributeDiv">
                                	 <div class="birth-box" >
                                        <input type="text" id="channelDefineRole" readonly="readonly" style="width:160px;">
                                        <div class="birth-year" id="channelDefineRoleShow" >请选择</div>
                                        <ul class="year-select"  id="channelDefineRole-select">
                                          <#list typelist as role>
                                                   <li data-type="workYear" text="${role}" data="${role}">${role.label}</li>
                                               </#list>
                                         </ul>
                                         <div class="modal-triangle" id="channelDefineRole-triangle">
                                                <div class="modal-trigger"></div>
                                         </div>
                                    </div>
                                </div>
                            </div>
                            <div class="form_group city_div" id="channelDiv">
                                <label class="label_div"><span class="required">*</span>所在城市</label>
                                <div class="birth-box cityBox cityContainer">
                                        <input type="text" id="areaNameChannel" name="areaName" placeholder="选择所在城市">
                                        <img src="${_PATH}/res/images/location_icon.png" class="location">
			                                <ul class="year-select"></ul>
			                                <span class="triangle_down triangleBtn" id="triangleBtn1"></span>
                                  </div>
                            </div>

                            <div class="form_group">
                            	
                                <label class="label_div">机构名称</label>
                                <div class="input_div">
                                   <input type="text" name="companyName" placeholder="请输入所在机构名称">
                                </div>
                            </div>
                            <div class="modal-opt btn_div">
                            	<div class="error_div" id="channelTipErrorId" hidden><span class="error_icon"></span><span class="error_text" id="channelTipTextId"></span></div>
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
