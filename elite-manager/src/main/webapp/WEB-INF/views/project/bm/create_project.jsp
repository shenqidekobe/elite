<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
<link href="${_PATH}/res/css/kuCity.css" rel="stylesheet"
	type="text/css" />
<script src="${_PATH}/res/js/city/kuCity.js"></script>
<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
<script
	src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
<form id="createForm" name="createForm">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">新建项目</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style3">所属项目方 </label><label
						class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" class="form-control" readonly width="20px"
							id="selectCompanyBtn" placeholder="选择项目方 " maxlength="15">
						<input type="hidden" id="companyId" name="companyId" />
					</div>
				</label> <label class="title-style2" id="selectCompany" hidden>
					<table class="project-table table"
						style="margin-left: 15px; margin-right: 15px; width: 770；">
						<thead>
							<tr>
								<div class="box-header">
									<div class="input-group"
										style="width: 500px; margin-left: 15px; float: left;">
										<input style='display: none' /><input type="text"
											class="form-control" id="keyword" placeholder="项目方昵称，电话，编号">
									</div>
									<button type="button" class="btn btn-primary"
										id="search_company" style="margin-left: 10px; float: left;">搜索</button>
								</div>
							</tr>
						</thead>
					</table>
					<div style="width: 770; height: 500px; overflow: scroll;"
						id="companylistData"></div>
				</label>
				<div class="">
					<label class="title-style3">解决方案类型 <small>(多选)</small>

					</label>
				</div>
				<div class="">
					<label class="title-style2">
						<div class="btn-group btn-group-justified" role="group"
							id="typeBtnGroups" aria-label="...">
							<c:forEach items="${projectTypes}" var="stage">
								<div class="btn-group" role="group" data="${stage.value}">
									<button type="button" class="btn btn-default" id="pc_btn"
										<c:if test="${project.projectDefine.status!='normal'}">
						                name="projectTypeSelect" 
						            </c:if>
										data="${stage.key}">${stage.value}</button>
								</div>
							</c:forEach>
						</div>
					</label>
				</div>
				<div class="">
					<label class="title-style2">已经选择：<label id="typeShow">

					</label></label> <input type="text" name="projectSolution" hidden="true"
						id="projectTypeId">
				</div>
				<div class="">
					<label class="title-style3">项目预算</label>
				</div>
				<div class="">
					<label class="title-style2">
						<div class="btn-group" role="group" aria-label="...">

							<div class="btn-group" role="group">
								<button type="button" class="btn btn-default dropdown-toggle"
									data-toggle="dropdown" aria-haspopup="true"
									aria-expanded="false">
									<div id="bugetShowId">
										<c:if test="${project.projectBudget!=null}">
									  ${project.projectBudget}
									</c:if>
										<c:if test="${project.projectBudget==null}">
									  	请选择
									</c:if>
									</div>
									<span class="caret"></span>
								</button>

								<ul class="dropdown-menu" id="budgetSelect">
									<c:forEach items="${projectBudgets}" var="stage">
										<li><a href="javascript:void(0);" id="budgeSelectId"
											value="${stage.value}" data="${stage.value}">${stage.value}</a></li>
									</c:forEach>
								</ul>
							</div>
						</div>
					</label>
				</div>
				<div class="">
					<input type="text" hidden="true" name="projectBudget"
						id="projectBudgetId" value="${project.projectBudget}" /> <input
						type="text" hidden="true" name="id" id="projectId"
						value="${project.id}" />
				</div>
				<div class="">
					<label class="title-style3">项目名称 </label><label
						class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small>少于15字</small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" class="form-control" id="dictDescId"
							width="20px" name="name" placeholder="请输入项目名称 " maxlength="15">
					</div>
				</label>

				<div class="">
					<label class="title-style3">项目简介</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<script id="content" type="text/plain" style="width: 95%;"
					name="intro" id="auditReasonId">${project.intro}</script>
				<!-- <div class="">
					<label class="title-style3">其他描述文档</label><label
						class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
					  
						<button type="button" class="btn btn-primary"
										id="add_company" >上传描述性文件</button>
										</label>
					</div>
				</label> -->
				
				<div class="">
					<label class="title-style3">推荐人</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small>如果有，请填写推荐人的手机号或邀请码</small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" class="form-control" width="20px"
							name="recommendUser" maxlength="20">
					</div>
				</label>
				<div class="">
					<label class="title-style3">期望交付时间：</label><label
						class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" id="startTimeId" name="startTime"
							value="${project.startTime }" readonly="readonly" /> <span><input
							type="text" id="expectTimeId" name="expectTime"
							value="${project.expectTime}" /></span><label>共<span
							id="expecTimeDayId">${project.deliveryDay}</span></label>个工作日
				</label>
			</div>
			</label>
			<div class="">
				<label class="title-style3">项目紧急联系人</label><label
					class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
			</div>
			<label class="title-style2">
				<div class="col-xs-30">
					<input type="text" name="contactName" placeholder="姓名"
						style="width: 80px" /> <span><input type="text"
						name="contactPhone" placeholder="电话，邮箱等联系方式" /></span><label>
				</div>
			</label>
			<div class="">
				<div class="">
					<label class="title-style3">参考项目</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small>其他相似功能或产品的名称或网址</small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" class="form-control" width="20px"
							name="referProject" placeholder="请输入参考项目" maxlength="20"
							value="${project.referProject}">
					</div>
				</label>
				<div class="">
					<label class="title-style3">项目所在城市</label><label
						class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" class="form-control" id="areaName" width="20px"
							name="areaName" placeholder="请选择城市" value="${project.areaName}">
					</div>
				</label>
				<div class="">
					<label class="title-style3">行业分类：</label><label
						class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<label class="title-style2"> <c:forEach items="${industrys}"
						var="stage" varStatus="status">
						<c:if test="${status.index%8==0}">
							<div class="btn-group btn-group-justified" role="group"
								id="dustryBtnGroups" aria-label="...">
						</c:if>
						<div class="btn-group" role="group" data="${stage.value}"
							id="dustrysGroupsId">
							<button type="button" class="btn btn-default" id="dustry_btn"
								name="dustry_btn" data="${stage.key}">${stage.value}</button>
						</div>
						<c:if test="${status.index%8==7}">
			</div>
			</c:if>
			</c:forEach>
		</div>
		</label>
		<div class="col-xs-30">
			<label class="title-style2">已经选择：<label id="dustryShowId">
					<c:forEach items="${project.getIndustryValList()}" var="stage">
				  ${stage}
			    </c:forEach>
			</label></label> <input type="text" hidden="true" name="productIndustry"
				id="productIndustryId" value="${project.productIndustry}" />
		</div>
		<div class="">
			<label class="title-style3">功能分类：</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
		</div>
		<label class="title-style2">

			<div class="btn-group btn-group-justified" role="group"
				id="funcBtnGroups" aria-label="...">
				<c:forEach items="${projectFuncs}" var="stage" varStatus="status">
					<div class="btn-group" role="group" data="${stage.value}"
						id="funcGroupsId">
						<button type="button" class="btn btn-default" id="func_btn"
							name="func_btn" data="${stage.key}">${stage.value}</button>
					</div>
				</c:forEach>
			</div>
		</label>
		<div class="col-xs-30">
			<label class="title-style2">已经选择： <label id="funcShowId">
					<c:forEach items="${project.functionValList}" var="stage">
							 ${stage}
				   </c:forEach>
			</label>
			</label> <input type="text" hidden="true" name="productFunction"
				id="postfuncId" value="${project.productFunction}" />
		</div>
		<div class="">
			<label class="title-style3">是否参与云英汇《明星开发团队持股开发计划》</label> <label
				class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
				<select class="form-control" name="planed">
					<option value="true">是</option>
					<option value="false">否</option>
			</select>
			</label>
			
		</div>
	<label class="title-style2">上传商务文档:<span id="fileName"></span></label>
										<div class="ID-pic-add" style="margin-left: 10px;">

											<span class="glyphicon glyphicon-upload custom-icon"
												onclick="document.createForm.defineFile.click()"></span> <a
												target="_blank" id="uploadSpan" style="display: none"><span
												class="glyphicon glyphicon-download custom-icon"></span></a> <span
												class="glyphicon glyphicon-trash custom-icon"
												id="removeSpan" style="display: none"></span>

										</div>
										<div>
											<input type="file" name="file" id="defineFile"
												style="display: none;"> <input type="hidden"
												name="planBook" id="defineFileId" />
										</div>
	<div class="modal-footer">
		<button type="submit" id="btn_submit" class="btn btn-primary">
			<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确认提交
		</button>
		<button type="button" class="btn btn-default" data-dismiss="modal">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
		</button>

	</div>
	</div>
</form>
