<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<div style="margin-top: 35px;">
	<ul class="nav nav-tabs">
		<li class="active"><a href="#tab_7" data-toggle="tab" id="enterProject">已入驻项目 </a></li>
		<li><a href="#tab_8" data-toggle="tab" id="putProject">已备案项目 </a></li>
	</ul>
	<div id="myTabContent" class="tab-content">
		<div id="tab_7" class="tab-pane fade in active" style="padding: 10px;">
			<table class="details-table">
				<c:forEach items="${list}" var="it">
				    <tr>
				    	<td colspan="2" align="left">备案时间:<fmt:formatDate value="${it.project.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
				    	<td colspan="2" align="right"><c:if test="${it.partnerProject.contactpartner }">可以以他的名义联系</c:if></td>
				    </tr>
					<tr>
						<td>
							<div>${it.project.name}</div>
							<div>${it.project.memberPassport.nickName}</div>
							<div>${it.project.memberPassport.account}</div>
							<div>${it.project.areaName}</div>
						</td>
						<td>
							<div>项目阶段:${it.partnerProject.productStageVal}</div>
							<div>所属行业:<c:forEach items="${it.project.getIndustryValList()}" var="industry">${industry}&nbsp;&nbsp;</c:forEach></div>
							<div>开发类型:${it.project.solutionVals}</div>
						</td>
						<td>
							${it.project.status.label}
						</td>
						<td>
							<a href="javascript:void(0);" id="projectIntro" data="${it.project.intro}">查看项目描述</a>
						</td>
					</tr>
					</c:forEach>
				</tbody>
			</table>
			<c:import url="/inc/pager.jsp"></c:import>
		</div>
		<div id="tab_8" class="tab-pane fade ">
			<form id="">
				<input type="hidden" value="${member.partnerCompany.id}" name="partnerId" />
				<div class="channel-mag" id="putProjectList"></div>
			</form>
		</div>
	</div>
</div>

<!-- 备案项目 -->
<div id="projectDeatil" class="modal fade" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" style="display: none;" aria-hidden="true">
	<div id="showIntroDeatil" class="modal-dialog" role="document" style="width: 800px;">
			<div class="modal-dialog" role="document">
				<div class="box box-success">
					<div class="box-header">
						<h3 class="box-title">项目描述</h3>
						<div class="box-tools pull-right" data-toggle="tooltip" title="Status">
							<div class="btn-group" data-toggle="btn-toggle">
								<button type="button" class="close" data-dismiss="modal" aria-label="Close">
									<span aria-hidden="true">×</span>
								</button>
							</div>
						</div>
					</div>
					<div style="margin-left: 15px; margin-right: 15px;">
						<span class="form-control" style="height:auto" id="workRecordContent"></span>
					</div>
					<div style="margin-top: 15px;"><hr></div>
				</div>
			</div>
	</div>
</div>
