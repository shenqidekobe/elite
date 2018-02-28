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
			<h4 class="modal-title" id="myModalLabel">设置服务阶段</h4>
		</div>
		<div class="modal-body">
			<div>
				<table id="stageTable">
				<input type="hidden" name="projectId" id="input_projectId"/>
					<c:forEach items="${stages}" var="dict" varStatus="order">
						<tr>
							<td style="width: 150px;"><span class="custom-bg circle" id='stageItem' data="${dict.stageCode}" value="${dict.stageCode}">${dict.title}</span>
							 <input type="hidden" name="dicts" value="${dict.stageCode}"/>
							</td>
							<td>
                             <button type="button" class="btn btn-default" id="deleteStage"><span>×</span></button></td>
						</tr>
					</c:forEach>	

				</table>
				<br>
				<div style="float: left; margin-left: 20px; width: 150px">
					<!-- <span >角色: </span> -->
					<select class="form-control" id="stage_select" >
						<c:forEach items="${list}" var="dict">
							<option value="${dict.dictKey}" data="${dict.orders}">${dict.dictVal}</option>
						</c:forEach>
					</select> <br>
				</div>
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
				<button type="button" class="btn btn-default" id="addStage">添加</button>

				<br>

			</div>
		</div>
		<div class="modal-footer">
			<button type="button" id="setting_submitBtn" class="btn btn-primary">
				<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
			</button>
			<button type="button" class="btn btn-default" data-dismiss="modal">
				<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
			</button>

		</div>
	</div>
</form>
