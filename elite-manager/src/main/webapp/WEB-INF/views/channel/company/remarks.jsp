<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="workRecordForm">
	<div class="modal-dialog" role="document">
		<div class="box box-success">
			<div class="box-header">
				<h3 class="box-title">您将要对此人才渠道添加备注</h3>
				<div class="box-tools pull-right" data-toggle="tooltip" title="Status">
					<div class="btn-group" data-toggle="btn-toggle">
						<button type="button" class="close" data-dismiss="modal" aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
					</div>
				</div>
			</div>
			<hr>
			<div style="margin-left: 15px; margin-right: 15px;">

				<input name="type" hidden="true" value="${type}"/>
				<input  name="foreignId" hidden="true" value="${foreignId}" id="foreignId"/>
				<textarea class="form-control" rows="4"  stype="text-align:left" placeholder="添加备注" id="workRecordContent" name="content"></textarea>
			</div>
			<br>
			<div style="margin-left: 15px; margin-right: 15px;">
			<span style="margin-left: 5px">
				<button  type="button"  id="cancelBtn">
				               取消
				</button>
			</span>
			<span style="margin-left: 25px;">
				<button type="button"  id="addWorkRecord" type="margin-right: 15px;">
				     提交
				</button>
				</span>
			</div>
			<div class="box-body" id="chat-box" style="margin-left: 15px; margin-right: 15px;">
			
				<div>此项目备注记录：</div>
					<hr>
				<c:forEach items="${list}" var="record" varStatus="statu">
					<div class="item">
						<p class="message">
							
						    <div>备注 ${statu.index+1}
						    <div>
						        <span> <fmt:formatDate
									value="${record.createTime}" pattern="yyyy-MM-dd   HH:mm" />
					    	     </span>
						        <span style="margin-left:320px;">${record.realName}添加</span>
						    </div>
						           内容：
							<div data="${record.id}">
								<!-- 
								<button type="button" class="text-muted pull-right"
									id="removeWorkRecord">删除</button>
								<button type="button" class="text-muted pull-right"
									id="eliteWorkRecord">编辑</button>
								<button type="button" class="text-muted pull-right"
									id="updateWorkRecord" style="display:none">保存</button>
								 -->	
							</div>
							<div>${record.content}</div>
							<textarea style="display:none" class="form-control" rows="4"  placeholder="请输入备注内容"  >${record.content}</textarea>
						</div>
						</p>
						<hr>
				</c:forEach>
			</div>
			<!-- /.chat -->
		</div>
	</div>
</form>
