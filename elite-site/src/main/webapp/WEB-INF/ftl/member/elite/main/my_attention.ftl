

	 <div class="release-list">
	    <div style="position: relative;">
	        <div class="part1">
	            <div class="col-xs-4 col-md-4">
	                <span>创建时间：${it.createTime} </span>
	            </div>
	            <div class="col-xs-8 col-md-8">
	                <span class="pull-left">任务编号：${it.task.taskNum}</span>
	                <div class="pull-right">
	                
	                   <#if pl.status=='wait_audit' || pl.status=='unpass'>
		                    <img src="${_PATH}/res/images/edit_icon.png" class="opt-icon" id="edit" data="${it.project.id}">
		                    <img src="${_PATH}/res/images/delete_icon.png" class="opt-icon" id="delete" data="${it.project.id}">
	                    </#if>
	                </div>
	            </div>
	        </div>
	        <div class="vertical-line"></div>
	    </div>
	    <div class="project-detail">
	        <div class="main-info">
	            <div style="padding:20px;">
	                <div class="project-logo" style="background-color:#${it.project.backgroundColor}">${it.project.firstName}</div>
	                <div class="project-info">
	                    <div>
	                        <span class="project-name">${it.project.name}</span>
	                        
	                    </div>
	                     <div><span class="index">所需精英:</span>&nbsp;<span class="value">${it.task.demandType}</span></div>
	                    <div><span class="index">任务类型:</span>&nbsp;<span class="value">${it.task.taskType}</span></div>
	                    <div><span class="index">报酬:</span>&nbsp;<span class="value">${it.task.amount}</span></div>
	                    <div><span class="index">期望交付日期:<span style="color:#000;">${it.task.originalDeliveryTime}</span></span></div>
	                </div>
	
	            </div>
	        </div>
	       
	        <div class="status">
	            <div style="padding-top:20px;text-align: center;">
	          
	              
	                <p class="current-status"> ${it.task.status.label}</p>
	                <a href="javascript:void(0);" target="_blank" id="detail" data="${it.task.id}">查看详情</a>
	            </div>
	        </div>
	        <div class="opt">
	            <div style="padding-top:20px;text-align: center;">
	               
	                 <button type="button" class="complete-btn" data="${it.task.id}" id="submit">
	                 	<#if it.task.status =="starting">
	                 					提交产出物
	                 			<#elseif it.task.status =="wait_accept">
	                 					查看产出物
	                 			
	                 	
	                 	</#if>
	                 </button>
	                 <p class="current-status"> 申请延期</p>
	            </div>
	        </div>
	    </div>
	</div>
