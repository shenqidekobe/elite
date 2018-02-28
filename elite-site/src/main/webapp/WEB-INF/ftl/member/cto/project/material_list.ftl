<span style="font-size: 18px;">文件管理</span>
<hr style="margin-top:10px;">
<div style="font-size: 14px;position: relative;" id="material_list_option">
    <div class="period-select">
        <input type="text" class="form-control" placeholder="请选择阶段" id="stageBudget" name="stageBudget" style="width:180px;">
        <div class="triangle">
            <div class="trigger"></div>
        </div>
        <@projectStage projectId="${project.id}"></@projectStage>
        <div class="budget">
	        <#if stageList??>
		        <ul>
		            <li data="">全部</li>
		            <#list stageList as sl>
	                   <li data="${sl.id}">${sl.title}</li>
	                </#list>
		        </ul>
		    </#if>
        </div>
    </div>
    <span class="tab" data="receive">我收到的&nbsp;&nbsp;<span class="keyword">${receiveCount}</span></span>
    <span class="tab" data="send">我上传的&nbsp;&nbsp;<span class="keyword">${sendCount}</span></span>
    <span class="tab" data="unread">未读&nbsp;&nbsp;<span class="keyword">${unreadCount}</span></span>
    <div class="split-line"></div>
    <div class="split-line" style="left:508px;"></div>
</div>
<input type="hidden" id="selectMember" value="">
<div class="row" id="sendDiv">
    <div class="col-xs-5 col-md-5" style="margin-top:30px;">
        <div class="input-group">
            <input type="text" class="form-control" id="memberName" placeholder="@选择要发送的人" style="font-size: 12px;">
            <input type="hidden" value="${project.companyId}" id="memberId"/>
            <input type="hidden" id="role" name="" value="">
              <span class="input-group-btn">
                <button class="btn btn-default" id="sendFile" type="button" style="background-color: #FEA600;border:1px solid #FEA600;color:white;">
                    <img src="/res/images/upload_white_icon.png" width="16" height="17">
                    &nbsp;上传文档
                </button>
                <input type="file" name="file" id="selectFile" style="display:none;">
              </span>
        </div>
      <span id="tip" style="color:red;"></span>
    </div>
</div>
<input type="hidden" id="stage" name="stage" value="${project.forStage.id}">
<form id="materialListForm">
	<div class="suppliers-box">
	   <input type="hidden" id="projectId" name="projectId" value="${project.id}">
	   <input type="hidden" id="stageId" name="stageId" value="">
	   <input type="hidden" id="queryType" name="queryType" value="all">
	   <div id="dataSecondList"></div>
	</div>
</form>
 <#-- 
<form id="materialForm" name="materialForm">
    <div style="text-align: center;margin-top:40px;">
        <button type="button" class="upload-btn" onclick="document.materialForm.materialFile.click()">
            <img src="${_PATH}/res/images/upload_icon2.png" width="19" height="19">&nbsp;&nbsp;上传文档
        </button>
        <input type="file" name="file" id="materialFile" style="display:none;">
        <span class="upload-tip"><span class="required">*</span>小于500M</span>
    </div>
</form>
 -->

<div class="container">
    <div class="row">
        <div class="modal fade" id="progressDialog" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content" style="top:250px;height:100px;">
                    <div class="modal-body" style="text-align: center;padding:35px;">
                        <div class="progress progress-striped active">
					        <div class="progress-bar progress-bar-success" role="progressbar" style="width: 0%;">0%</div>
					    </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
