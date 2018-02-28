<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="modal fade" id="affirmModel" tabindex="-1" role="dialog"
	aria-labelledby="myModalLabel">
	<div class="modal-dialog" role="document">
		<form id="affirmForm" method="post">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">×</span>
					</button>
					<h4 class="modal-title" id="myModalLabel">注销用户</h4>
				</div>
				<div class="modal-body">
					<div class="col-xs-12">
						<div class="form-group">
							<label for="txt_departmentlevel">上级指令</label> <input
								type="password" name="directive" class="form-control"
								id="directive" placeholder="输入指令">
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
					</button>
					<button type="button" id="affirm_submit" class="btn btn-primary">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>保存
					</button>
				</div>
			</div>

		</form>
	</div>
</div>