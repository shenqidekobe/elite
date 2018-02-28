<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
 <h5 class="box-title" align="left">审核通过项目方( ${normalCount} )&nbsp;&nbsp; 共注册项目方( ${allCount} )</h5>
<table class="project-table table">
	<thead>
		<tr>
			<th>概况</th>
			<th>详情</th>
			<th>数据</th>
			<th>操作</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${dataList}" var="it">
			<tr>
				<td>
					<div style="padding: 5px 10px; text-align: left;">

						<div>
							<p>
								<span>注册时间 ：<fmt:formatDate value="${it.createTime}"
										pattern="yyyy-MM-dd HH:mm" /></span> <span
									style="margin-left: 20px;">编号:${it.memberNum}</span> <span
									style="text-align: right; margin-left: 50px;">${it.basic.areaName}</span>

							</p>
							<div class="project-logo">
								   <c:if test="${it.basic.photoId!=null }">
								<img src="${it.basic.memberPhoto.getPath()}" height="105px"
									width="105px" />
								</c:if>	
							   <c:if test="${it.basic.photoId==null }">
								<img src="${_PATH}/res/img/default.jpg" height="105px"
									width="105px" />
								</c:if>	
							</div>
							<div class="project-info">
								<div style="margin-bottom: 6px;">
									<span class="project-name">${it.nickName}</span>
									<div class="class-label" style="width:60px">项目方</div>
									<c:if test="${it.company.status=='normal' }">
									<div class="class-label">√</div>
									</c:if>
									<!-- <div class="class-label">Star</div> -->
								</div>

								<div style="margin-bottom: 6px;">${it.basic.memberSign}</div>
								<div
									style="margin-bottom: 6px; text-align: right; padding-top: 15px;">
									<div>
										<span class="index"> 联系方式:${it.accountOffSuffix} </span>
									</div>
								</div>
							</div>

						</div>
					</div>
				</td>
				<td>
					<div style="margin-top: 25px;">
						<c:if test="${it.status=='disabled'}">
					        ${it.status.label}
					</c:if>
						<c:if test="${it.status!='disabled'}">
							<c:if test="${it.company.status=='normal'}">
					 正常状态
					</c:if>
							<c:if test="${it.company.status=='auditNotPass'}">
					 审核未通过
					</c:if>
							<c:if
								test="${it.company.status!='normal'&&it.company.status!='auditNotPass'}">
								<p>${it.company.status.label}</p>
							</c:if>
						</c:if>
						<shiro:hasPermission name="elite:project:company:viewDetail">
						<p style="margin-bottom: 6px;">
							<a href="javascript:void(0);" id="detail" data="${it.id}">查看详情</a>
						</p>
						</shiro:hasPermission>
					</div>
				</td>
				<td>
					<div style="padding-top: 15px;">
						<div style="margin-bottom: 6px; text-align: gray;">进行中项目
							&nbsp;${it.projectDoCount}</div>
						<div style="margin-bottom: 6px; text-align: gray;">已托管费用
							&nbsp; ${it.memberAccount.trustAmount}元</div>
					</div>
				</td>
				<td>
					<div style="margin-top: 25px;" class="opt">
						<shiro:hasPermission name="elite:project:company:projectCompanyPrefect">
							<p>
								<a style="margin-bottom: 6px;" href="javascript:void(0);" id="assist_perfect" data="${it.id}">协助完善项目方资料</a>
							</p>
						</shiro:hasPermission>
                        <shiro:hasPermission name="elite:project:company:enable">
						<c:if test="${it.status=='disabled'}">
							<p>
								<a style="margin-bottom: 6px;" href="javascript:void(0);"
									id="unDisabledBtn" data="${it.id}">启用</a>
							</p>
						</c:if>
						</shiro:hasPermission>
						<c:if test="${it.status!='disabled'}">
						<shiro:hasPermission name="elite:project:company:aduit">
						<p>
							<a style="margin-bottom: 6px;" href="javascript:void(0);"
								id="audit" data="${it.id}"> <c:if
									test="${it.company.status=='waitAduit'}">
							      审核
							      <input type="button"
										style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;"></input>
								</c:if> <c:if test="${it.company.status!='waitAduit'}">
							      复审
							</c:if>

							</a>
						</p>
						</shiro:hasPermission>
						<shiro:hasPermission name="elite:project:company:disable">
							<p>
								<a style="margin-bottom: 6px;" href="javascript:void(0);"
									id="disabledBtn" data="${it.id}">禁用</a>
							</p>
							</shiro:hasPermission>
						</c:if>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>