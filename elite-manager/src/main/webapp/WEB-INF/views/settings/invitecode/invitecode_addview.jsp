<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<link href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.css"
	rel="stylesheet" type="text/css" />
<link
	href="${_PATH}/res/css/daterangepicker/bootstrap-datepicker.min.css"
	rel="stylesheet" type="text/css" />
<script src="${_PATH}/res/js/datetimepicker/bootstrap-datepicker.js"></script>
<script
	src="${_PATH}/res/js/datetimepicker/locales/bootstrap-datepicker.zh-CN.js"></script>
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">生成邀请码</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style2">
						邀请码类型:  <select class="form-control" id="addsearchType" name="type">
										<c:forEach items="${types}" var="type">
										  <c:set var="theString" value="${type}"/>
										    <c:if test="${fn:contains(theString,'platform_')}">
											<option value="${type}">${type.label}</option>
											</c:if>
										</c:forEach>
									</select>
						</div>
					</label>
				</div>
				<div class="" id="maxCountDivId" hidden>
					<label class="title-style2">邀请最大次数</label>
					<input type="number" id="maxCountId" value="1"/>
					
				</div>
				<div class="">
					<label class="title-style2">过期时间</label>
					<input type="text" id="expireTimeId"/>
				</div>
			</div>
		<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="button" id="addInviteCodebtn_submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk"
								aria-hidden="true"></span>生成
						</button>
					</div>
	</div>

