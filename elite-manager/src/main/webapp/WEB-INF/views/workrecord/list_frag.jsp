<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<script type="text/javascript">
//编辑按钮
$("button[id='eliteWorkRecordPage']").click(function() {
	var content = $(this).parent().next();
	$(this).next().toggle();
	content.toggle();
	content.next().toggle();
	if ($(this).html() == "编辑") {
		$(this).html("取消")
	} else if ($(this).html() == "取消") {
		$(this).html("编辑")
	} 
}),
$("button[id=updateWorkRecordPage]").click(function(){
	var id = $(this).parent().attr("data");
	var contentMsg = $(this).parent().next().next().val();
	if (contentMsg == "") {
		$.tips({
			content : "备注内容不能为空"
		});
		return;
	}else if(contentMsg.length>200){
		$.tips({
			content : "请输入的备注内容在200字以内"
		});
		return;
	}
	jsonAjax.post({
		url : ctx + '/work/update',
		data : {
			id : id,
			content:contentMsg
		},
		success : function(data) {
			if (BASE.JS_RESULT.SUCCESS == data.result) {
				list.showWorkLogs();
			} else {
				$.tips({
					content : data.msg
				});
			}
		}

	})
}),
function test(){
	//此方法不能删除
}
</script>
	<form id="workRecordForm">
<div class="modal-dialog" role="document">
	<div class="box box-success">
		<div class="box-header">
			<h3 class="box-title">工作记录</h3>
			<div class="box-tools pull-right" data-toggle="tooltip"
				title="Status">
				<div class="btn-group" data-toggle="btn-toggle">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
				</div>
			</div>
		</div>
	    <div class="input-group" style="margin-left: 15px; margin-right: 15px;">
					    
						<input id="workRecordType" name="type" hidden="true">
						<input id="workRecordForeignid" name="foreignId" hidden="true" value="${foreignId}">
						<textarea class="form-control" rows="4" placeholder="添加工作记录" id="workRecordContent"
						name="content" ></textarea>
						<div class="input-group-btn">
							<button class="btn btn-success" type="button" id="addWorkRecord" onclick="list.addWorkRecord()" >
								<i class="fa fa-plus"></i>
							</button>
						</div>
					</div>
		<div class="box-body" id="chat-box"
			style="margin-left: 15px; margin-right: 15px;">
			<hr>
			<!-- chat item -->
			<c:forEach items="${list}" var="record">
				<div class="item">
					<p class="message">
							<small class="text-muted pull-right"><i
						class="fa fa-clock-o"></i><fmt:formatDate value="${record.createTime}"
						pattern="yyyy-MM-dd HH:mm:ss" /></small>
						<div>记录人：${record.realName}
							<div data="${record.id}">
						    <button type="button" class="text-muted pull-right" onclick="list.removeWorkRecord(${record.id})">
							   删除
						     </button>
						     <button type="button" class="text-muted pull-right" onclick=""
									id="eliteWorkRecordPage">编辑</button>
							<button type="button" class="text-muted pull-right"
									id="updateWorkRecordPage" style="display:none">保存</button>
						    
						   </div>
							<div>内容:${record.content}</div>
						   <textarea style="display:none" class="form-control" rows="4"  placeholder="请输入备注内容"  >${record.content}</textarea>
						</p>
						</div>
						<hr>
					</div>
					</c:forEach>
					<!-- /.item -->
					<!-- chat item -->
					<!-- /.item -->
					<!-- chat item -->
					
					<!-- /.item -->
				</div>
				<!-- /.chat -->
			</div>
		</div>
		</form>
		