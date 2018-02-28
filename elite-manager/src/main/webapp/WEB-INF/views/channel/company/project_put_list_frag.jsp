<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
  <table class="details-table">
	<c:forEach items="${list}" var="it">
	    <tr>
	    	<td align="left">${it.areaName }</td>
	    	<td colspan="2" align="left">备案时间:<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	    	<td align="right"><c:if test="${it.contactpartner }">可以以他的名义联系</c:if></td>
	    </tr>
		<tr>
			<td>
				<div>${it.name}</div>
				<div>${it.phone}</div>
			</td>
			<td>
				<div>项目阶段:${it.productStageVal}</div>
				<div>所属行业:<c:forEach items="${it.getIndustryValList()}" var="industry">${industry}&nbsp;&nbsp;</c:forEach></div>
				<div>开发类型:${it.solutionVals}</div>
			</td>
			<td>
				${it.status.label}
			</td>
			<td>
				<a href="javascript:void(0);" id="projectIntro" data="${it.projectIntro}">查看项目描述</a>
			</td>
		</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>
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
