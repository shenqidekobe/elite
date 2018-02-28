<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<form id="createForm" name="createForm">
	<div class="modal-content">
		<div class="modal-header">
			<button type="button" class="close" data-dismiss="modal"
				aria-label="Close">
				<span aria-hidden="true">×</span>
			</button>
			<h4 class="modal-title" id="myModalLabel">新建广告</h4>
		</div>
		<div class="modal-body">
			<div>
				<div class="">
					<label class="title-style2">标题: </label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
					<label class="title-style2">
						<div class="col-xs-30">
							<input type="text" class="form-control" id="titleId" size="60"
								name="title" placeholder="请输入标题 ">
						</div>
					</label>
				</div>
				<div class="">
					<label class="title-style2">营销类型: </label>
					<label class="title-style2">&nbsp;</label>
					<div class="btn-group" role="group" aria-label="...">

						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" style="width:120px;">
								<div id="selectTypeShowId">${selectType}</div>
								<span class="caret"></span>
							</button>

							<ul class="dropdown-menu" id="selectTypesId">
								<c:forEach items="${typeList}" var="type">
									<li><a href="javascript:void(0);" id="selectTypeId"
										value="${type}" data="${type.label}">${type.label}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div><br/>
				<div class="">
					<label class="title-style2">使用平台: </label>
					<label class="title-style2">&nbsp;</label>
					<div class="btn-group" role="group" aria-label="...">

						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" style="width:120px;">
								<div id="selectPlatTypeShowId">
									<c:if test="${platformlist!=null}">
									  ${platformlist[0]}
									  </c:if>
									<c:if test="${platformlist==null}">
									  	请选择
									</c:if>
								</div>
								<span class="caret"></span>
							</button>

							<ul class="dropdown-menu">
								<c:forEach items="${platformlist}" var="stage">
									<li><a href="javascript:void(0);" id="selectPlatTypeId"
										value="${stage}">${stage}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div><br/>
				<div class="">
					<label class="title-style2">角色频道: </label>
					<label class="title-style2">&nbsp;</label>
					<div class="btn-group" role="group" aria-label="...">

						<div class="btn-group" role="group">
							<button type="button" class="btn btn-default dropdown-toggle"
								data-toggle="dropdown" aria-haspopup="true"
								aria-expanded="false" style="width:120px;">
								<div id="selectRoleTypeShowId">
									<c:if test="${roleChannellist!=null}">
									  ${roleChannellist[0].label}
									  </c:if>
									<c:if test="${roleChannellist==null}">
									  	请选择
									</c:if>
								</div>
								<span class="caret"></span>
							</button>

							<ul class="dropdown-menu">
								<c:forEach items="${roleChannellist}" var="type">
									<li><a href="javascript:void(0);" id="selectRoleTypeId"
										value="${type}" data="${type.label}">${type.label}</a></li>
								</c:forEach>
							</ul>
						</div>
					</div>
				</div><br/>
				<input id="selectTypeValueId" type="hidden" name="type">
				<input id="selectPlatTypeValueId" type="hidden" name="usePlatform"
					value="${platformlist[0]}"> <input
					id="selectRoleTypeValueId" type="hidden" name="channel"
					value="${roleChannellist[0]}">
				<div class="">
					<label class="title-style2">链接: </label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>

					<label class="title-style2">
						<div class="col-xs-30">
							<input type="text" class="form-control" size="60" name="href"
								placeholder="请输入链接 ">
						</div>
					</label>
				</div>
				<div class="">
					<label class="title-style2">内容</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<script id="content" type="text/plain" style="width: 95%;" name="content"></script>
				<div class="">
					<label class="title-style2">图片上传</label><label class="title-style2">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<small><small></small></small></label>
				</div>
				<label class="title-style2">

					<div class="papers">
						<div>
							<img src="" id="logoIdImgId"
								onclick="document.createForm.logoIdFile.click()">
						</div>
					</div>

				</label> <input type="file" name="file" id="logoIdFile"
					style="display: none;"> <input type="hidden" name="logoId"
					id="logoId">
				<div class="modal-footer">
					<button type="button" id="btn_submit_ad" class="btn btn-primary">
						<span class="glyphicon glyphicon-floppy-disk" aria-hidden="true"></span>确认提交
					</button>
					<button type="button" class="btn btn-default" data-dismiss="modal">
						<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>取消
					</button>

				</div>
			</div>
</form>
