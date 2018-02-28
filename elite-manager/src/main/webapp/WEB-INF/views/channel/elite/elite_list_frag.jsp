<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>

<!-- 备案项目 -->
<table class="details-table">
	<c:forEach items="${list}" var="it">
	    <tr>
	    	<td>
	    		<c:if test="${it.memberBasic.areaName!=null }">${it.memberBasic.areaName}</c:if>
	    		<c:if test="${it.memberBasic.areaName==null }">${it.areaName}</c:if>
            </td>
	    	<td align="left">备案时间:<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm" /></td>
	    	<td><c:if test="${it.memberId!=null }">注册时间:<fmt:formatDate value="${it.memberPassport.createTime}" pattern="yyyy-MM-dd HH:mm" /></c:if>
	    	<td align="left">注册编号:<c:if test="${it.memberId!=null }">${it.memberPassport.memberNum}</c:if></td>
	    </tr>
		<tr>
			<td>
				<div>
				 	<c:if test="${it.memberCredit.realName!=null}"> ${it.memberCredit.realName}</c:if>
					<c:if test="${it.memberCredit.realName==null}"> ${it.name}</c:if>
                </div>
				<div><c:if test="${it.memberPassport.nickName!=null}">(${it.memberPassport.nickName})</c:if></div>
				<div>${it.phone}</div>
			</td>
			<td>
				<div>人才属性:
					<c:if test="${it.memberElite.eliteJobs!=null}">
						<c:forEach items="${it.memberElite.eliteJobs }" var="jobs">
							 ${jobs.jobRoleVal}
						</c:forEach>
					</c:if>
					<c:if test="${it.memberElite.eliteJobs==null}">${it.defineRoleVal}</c:if>
				</div>
				<div>平台身份:
					<c:if test="${it.memberId!=null}">
						<c:if test="${it.memberElite.ctoed}">CTO</c:if>
						<c:if test="${!it.memberElite.ctoed}">精英</c:if>
					</c:if>
				</div>
				<div>工作年限:
					<c:if test="${it.memberElite.jobAge!=null}">${it.memberElite.jobAgeVal}</c:if>
					<c:if test="${it.memberElite.jobAge==null}">${it.jobAgeVal}</c:if>
				</div>
			</td>
			<td>
				${it.status.label}
			</td>
			<td>
				<c:choose>
					<c:when test="${it.memberId!=null && it.memberElite.resumeAttaId!=null}">
						<div><a href="${it.memberElite.resumeAtta}">下载简历</a></div>
					</c:when>
					<c:when test="${it.attaId!=null}">
						<div><a href="${it.atta.downPath}">下载简历</a></div>
					</c:when>
					<c:otherwise>
						<form id="submitForm" name="submitForm${it.id }">
							<div><a href="javascript:void(0);" id="uploadResume_${it.id }" data="${it.phone }">上传简历</a></div>
							<input type="file" name="file"  id="defineFile" style="display: none;">
					    </form>							
					</c:otherwise>
				</c:choose>
			</td>
		</tr>
		</c:forEach>
		<input type="hidden" id="account" value="" />
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>
<script src="${_PATH}/res/js/admin/channel/elite/uploadResume.js"></script>
<script src="${_PATH}/res/js/plugins/jqueryupload/webupload.js"></script>
<script src="${_PATH}/res/js/plugins/jqueryupload/jquery.fileupload.js"></script>