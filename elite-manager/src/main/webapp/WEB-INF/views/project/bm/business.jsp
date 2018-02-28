<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="businessForm" method="post">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel" id="titleId">添加商务记录</h4>
		</div>
		<div class="modal-body">

			<div style="margin-left: 10px; list-style-type: none;">
				<div id="controltitleId">
					沟通方式：
					<div class="btn-group">
						<button class="btn btn-default btn-sm dropdown-toggle"
							type="button" data-toggle="dropdown" aria-haspopup="true"
							aria-expanded="false">
							<span id="showWayId">电话<span></span></span> <span class="caret"></span>
						</button>
						<ul class="dropdown-menu">
							<li><a href="javascript:void(0);" id="selectWayId" data="电话">电话</a></li>
							<li><a href="javascript:void(0);" id="selectWayId" data="走访">走访</a></li>
						</ul>
						<input type="text" hidden="true" value="电话" id="wayInputId"
							name="way">
						<input type="text" hidden="true" name="projectId" id="projectIdInputId"
							name="way">
					</div>

					&nbsp; &nbsp; &nbsp; &nbsp;走访地点：<input type="text" name="address"
						placeholder="如无可不填" /> &nbsp; &nbsp; &nbsp; &nbsp;其他参与人：<input
						type="text" placeholder="如无可不填" name="participant" />


				</div>
			</div>
			<input type="hidden" value="${role}" id="roleId">
			<div style="margin-left: 10px; list-style-type: none;">
				<div>
					<textarea class="form-control" rows="6" placeholder="沟通记录"
						name="content" id="content"></textarea>
				    
				</div>
				<div>
					<blockquote class="pull-right">
						<button type="button" id="business_submit" class="btn btn-primary">
							提交</button>
					</blockquote>
				</div>
			</div>
			<br> <br>
			<div>商务记录：</div>
			<hr>
			<div style="margin-left: 20px; list-style-type: none;">
				<c:forEach items="${list}" var="logs" varStatus="statu">
					<div>
						<div>商务记录&nbsp;${statu.index+1}</div>
						<div data="${logs.id}">
							<label><fmt:formatDate value="${logs.createTime}"
								pattern="yyyy-MM-dd hh:ss:mm" /></label>
								 &nbsp; &nbsp; &nbsp; &nbsp; <label>${logs.way}沟通</label>
								 &nbsp; &nbsp; &nbsp; &nbsp; <label>地点：
								 <c:if test="${logs.address==null }">
								  无
								 </c:if>
								 <c:if test="${logs.address!=null }">
								 ${logs.address}
								 </c:if>
								 </label>
							&nbsp; &nbsp; &nbsp; &nbsp; 其他参与人： <label>${logs.participant}</label>
							&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
							<!-- <button id="edit_business" type="button">编辑</button> -->
							<c:if test="${role!=null}">
							<button id="remove_business" type="button">删除</button>
							<button id="eliteWorkRecord" type="button">编辑</button>
							<button id="updateWorkRecord" type="button" style="display:none">保存</button>
							</c:if>
						     </div>
						<div>内容:${logs.content}</div>
						<textarea style="display:none" class="form-control" rows="4"  placeholder="请输入备注内容"  >${logs.content}</textarea>
						<hr>
					</div>
				</c:forEach>
			</div>

		</div>
	</div>
</form>
