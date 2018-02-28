<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="form" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">定标</h4>
		</div>
		<div class="modal-body"
			style="margin-left: 10px; list-style-type: none;">
			<div class="tab-pane fade in active" id="home">

				<label class="common-red">确定标书<span></span></label>
				<input type="hidden" id="recordId" name="recordId" value="${it.id }"></input>
				<input type="hidden" id="tenderId" name="tenderId" value="${it.tender.id}"></input>
			</div>
			<div>
				研发费用：<input type="text" style="width: 80px;" value="${it.amount}" name="amount" />&nbsp;元
			</div>
			<div>
				项目分付出股权：<input type="text" style="width: 40px;"
					value="${it.tender.platformAmount}" name="platformAmount"/>&nbsp;%
			</div>
			<div>
				质保期：<input type="text" style="width: 40px;"
					value="${it.tender.qualityTime}" name=""/>&nbsp;月
			</div>
			<div>
				<label class="title-style2">研发计划 * </label>
				<table class="sp-table">
					<tr style="text-align: center;">
						<td style="width: 130px;">项目阶段</td>
						<td>阶段时间规划</input></td>
						<td>阶段所需费用 (元)</td>
					</tr>
					<c:forEach items="${it.tenderStages}" var="stage">
						<tr>
							<td style="width: 150px;"><span class="custom-bg circle"
								id="defineNameId" data=""> ${stage.title}</span></td>
							<td><input type="text" class="short-input2"
								value="<fmt:formatDate value="${stage.startTime}"
									pattern="yyyy-MM-dd" />"></input>
								<input type="text" class="short-input2"
								value="<fmt:formatDate value="${stage.endTime}"
									pattern="yyyy-MM-dd" />"></input>

							</td>
							<td><input type="text" class="short-input2"
								value="${stage.amount}"></input></td>
						</tr>
					</c:forEach>
				</table>
			</div>
			<div>签约费用：</div>
			<div>
				报酬总额:<input type="text" />&nbsp;
				<button type="button" >提交</button>
			</div>
			<div>
				<hr>
				<div class="modal-body"
					style="margin-left: 300px; list-style-type: none;">
					<button type="button" id="submitBtn" class="btn btn-large">确认发送</button>
				</div>
			</div>

		</div>
	</div>



</form>
