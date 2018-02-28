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
<form id="prefectForm">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">协助完善项目资料
			</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style3">解决方案类型
					<c:if test="${project.projectDefine.status=='normal'}">
						<small>(此项目已经立项,此条目不能修改)</small>
						</c:if>
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
							<c:forEach items="${project.getSolutionListVal()}" var="types">
									  ${types}
									</c:forEach>
					</label></label> <input type="text" name="projectSolution" hidden="true"
						id="projectTypeId" value="${project.projectSolution}">
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
					<label class="title-style3">项目名称
					<c:if test="${project.projectDefine.status=='normal'}">
						<small>(此项目已经立项,此条目不能修改)</small>
						</c:if>
					</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small>少于15字</small></small></label>
				</div>
				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" class="form-control" id="dictDescId"
							<c:if test="${project.projectDefine.status=='normal'}">
						readonly="readonly"
						</c:if>
							width="20px" name="name" placeholder="请输入项目名称 " maxlength="15"
							value="${project.name}">
					</div>
				</label>

				<div class="">
					<label class="title-style3">项目简介</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>

				<%-- <textarea class="form-control" rows="4" placeholder="请输入项目简介"
					name="intro" id="auditReasonId">${project.intro}</textarea> --%>
				<script id="content" type="text/plain" style="width: 95%;"
					name="intro" id="auditReasonId">${project.intro}</script>
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
				<label class="title-style3">项目所在城市：</label><label
					class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>

				<label class="title-style2">
					<div class="col-xs-30">
						<input type="text" class="form-control" id="areaName" width="20px"
							name="areaName" placeholder="请选择城市" value="${project.areaName}">
					</div>
				</label>
			</div>
			<div class="">
				<label class="title-style3">行业分类：</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
			</div>
			<label class="title-style2">
			 <c:forEach items="${industrys}" var="stage" varStatus="status">
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
		 <label class="title-style2">已经选择：
			<label id="funcShowId">
					<c:forEach items="${project.functionValList}" var="stage">
							 ${stage}
				   </c:forEach>
			</label>
		  </label> 
		  <input type="text" hidden="true" name="productFunction" id="postfuncId" value="${project.productFunction}" />
		</div>
		<div class="">
			<label class="title-style3">项目联系人姓名</label><label
				class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
		</div> <label class="title-style2">
			<div class="col-xs-30">
				<input type="text" class="form-control" width="20px"
					name="contactName" placeholder="请输入项目紧急联系人姓名" maxlength="6"
					value="${project.contactName}">
			</div>
	</label>
		<div class="">
			<label class="title-style3">项目联系人电话</label><label
				class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
		</div> <label class="title-style2">
			<div class="col-xs-30">
				<input type="text" class="form-control" width="20px"
					name="contactPhone" placeholder="请输入项目紧急联系人电话或者邮箱" maxlength="20"
					value="${project.contactPhone}">
			</div>
	</label>
		<div class="">
			<label class="title-style3">推荐人手机号或者邀请码</label><label
				class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
		</div> <label class="title-style2">
			<div class="col-xs-30">
				<input type="text" class="form-control" width="20px"
					name="recommendUser" placeholder="请输入项目紧急联系人电话或者邮箱" maxlength="20"
					value="${project.recommendUser}">
			</div>
	</label>
		<div class="">
			<label class="title-style3">参考项目</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
		</div> <label class="title-style2">
			<div class="col-xs-30">
				<input type="text" class="form-control" width="20px"
					name="referProject" placeholder="请输入参考项目" maxlength="20"
					value="${project.referProject}">
			</div>
	</label>
	
	<div class="">
		<label class="title-style3">是否参与明星团队计划</label> <label
			class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<select class="form-control" name="planed" value="${project.planed }">
				<option value="true"
					<c:if test="${project.planed=='true' }">selected="selected"</c:if>>是</option>
				<option value="false"
					<c:if test="${project.planed=='false' }">selected="selected"</c:if>>否</option>
		</select>


		</label>
	</div>

	</label>
	</div>
	</div>
	<div class="modal-footer">
		<button type="button" class="btn btn-default" data-dismiss="modal">
			<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
		</button>
		<button type="submit" id="btn_submit" class="btn btn-primary">
			<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>提交
		</button>
	</div>
	</div>
</form>
