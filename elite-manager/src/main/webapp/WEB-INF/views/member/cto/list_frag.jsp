<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
 <h5 class="box-title" align="left">审核通过CTO( ${normalCount} )&nbsp;&nbsp; 共注册CTO( ${allCount} )</h5>
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
									<div class="class-label">CTO</div>
									<c:if test="${it.elite.status=='normal'}">
									<div class="class-label">√</div>
									</c:if>
									<!-- <div class="class-label">Star</div> -->
								</div>
								<c:forEach items="${it.elite.eliteJobs}" var="eej">
									<div style="margin-bottom: 6px;width:115px" class="title-label">
										${eej.getJobRoleVal()}
										<div class="title-grade" style="background-color: #7FCACB;width:27px">L${eej.level}</div>
									</div>
								</c:forEach>
								<div style="margin-bottom: 6px; text-align: right;">
									<c:if test="${it.elite.taked=='true'}">接活
									</c:if>
									<c:if test="${it.elite.taked=='false'}">不接活
									</c:if>
								</div>

								<div style="margin-bottom: 6px; text-align: right;">
								  <small> 
								 	<c:if test="${it.inviteCode!=null}">
								 	 邀请码：${it.inviteCode}
									</c:if>
								 </small> 
									<span style="text-align: right;" class="index">诚信度: <span
										style="color: #000; text-align: right;">${it.basic.integrity}</span>
									</span>
								</div>
								<div style="margin-bottom: 6px; text-align: right; padding-top: 0px;">
								 	<div>
									<span class="index"> 
									联系方式:${it.accountOffSuffix}
										 </span>
								</div>
							</div>
						</div>
					</div>
				</td>
				<td>
					<div style="margin-top: 15px;">
						 <c:if test="${it.status=='disabled'}">
					   <div style="margin-bottom: 6px;">${it.status.label}</div>
					   </c:if>
					   <c:if test="${it.status!='disabled'}">
					   <div style="margin-bottom: 6px;">${it.elite.status.label}</div>
					   </c:if>
						<div style="margin-bottom: 6px;">来源：${it.channel.label}</div>
						<shiro:hasPermission name="elite:member:cto:viewDetail">
						<p style="margin-bottom: 6px;">
							<a href="javascript:void(0);"  id="detail"
								data="${it.id}">查看详情</a>
						</p>
						</shiro:hasPermission>
					</div>
				</td>
				<td>
					<div style="padding-top: 15px;">
						<div style="margin-bottom: 6px; text-align: gray;">我的收益${it.memberAccount.totalIncome} 元</div>
					</div>
				</td>
				<td>
					<div style="margin-top: 25px;" class="opt">
						<div>
							<p>
							<%-- 	<c:if test="${it.status=='normal'}">

									<a style="margin-bottom: 6px;" href="javascript:void(0);"
										id="createClose" data="${it.id}">关停人员</a>
								</c:if>
								<c:if test="${it.status=='disabled'}">
									<a style="margin-bottom: 6px;" href="javascript:void(0);"
										id="stopClose" data="${it.id}">停止关停</a>
								</c:if> --%>
							</p>
						</div>
						<div>
							<shiro:hasPermission name="elite:member:cto:viewLevel">
							<p>
								<a style="margin-bottom: 6px;" href="javascript:void(0);"
									id="level" data="${it.id}">重新定级</a>
							</p>
							</shiro:hasPermission>
							<c:if test="${it.elite.status=='normal'}">
							<shiro:hasPermission name="elite:member:cto:homeShow">
							<c:if test="${it.homeShow=='true'}">
								<p>
									<a style="margin-bottom: 6px;" href="javascript:void(0);"
										id="noHomeShowBtn" data="${it.id}">取消首页展示</a>
								</p>
							</c:if>
							<c:if test="${it.homeShow=='false'}">
								<p>
									<a style="margin-bottom: 6px;" href="javascript:void(0);"
										id="homeShowBtn" data="${it.id}">首页展示</a>
								</p>
							</c:if>
							</shiro:hasPermission>
							
							</c:if><shiro:hasPermission name="elite:member:cto:enable">
								<c:if test="${it.status=='disabled'}">
								<p>
									<a style="margin-bottom: 6px;" href="javascript:void(0);"
										id="unDisabledBtn" data="${it.id}">启用</a>
								</p>
							</c:if>
							</shiro:hasPermission>
						    
							<c:if test="${it.status!='disabled'}">
							<shiro:hasPermission name="elite:member:cto:aduit">
							<c:choose>
								<c:when test="${it.elite.status == 'waitAduit'}">
									<p>
										<a style="margin-bottom: 6px;" href="javascript:void(0);"
											id="audit" data="${it.id}">强审核</a>
											<input type="button" style="background-color:red;height:12px; width:12px;border:1px;border-radius:6px;"></input>
									</p>
								</c:when>
								<c:when test="${it.elite.status != 'waitAduit'&&it.elite.status!='aduitIn'}">
									<p>
										<a style="margin-bottom: 6px;" href="javascript:void(0);"
											id="audit" data="${it.id}">强审核</a>
									</p>
								</c:when>
								<c:when test="${it.elite.status == 'aduitIn'}">
									<p>
										<a style="margin-bottom: 6px;" href="javascript:void(0);"
											id="audit" data="${it.id}">审核</a>
											<input type="button"
											style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;"></input>
									</p>
								</c:when>
							</c:choose> 
							</shiro:hasPermission>
							<shiro:hasPermission name="elite:member:cto:disable">
								<p>
								   <a style="margin-bottom: 6px;" href="javascript:void(0);"
										id="disabledBtn" data="${it.id}">禁用</a>
								</p>
							</shiro:hasPermission>
							</c:if>
						</div>
					</div>
				</td>
			</tr>

		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>