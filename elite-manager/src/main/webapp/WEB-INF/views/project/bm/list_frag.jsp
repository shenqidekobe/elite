<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<table class="project-table table box-body">
	<thead>
		<tr>
			<th>项目概况</th>
			<th>项目费用</th>
			<th>所处状态</th>
			<th>操作区域</th>
		</tr>
	</thead>
	<tbody>
		<c:forEach items="${list}" var="it">
			<tr <c:if test="${it.status=='invalid'}">style='background-color:#CDC9C9' title='无效项目'</c:if>>
				<td>
					<div style="padding: 5px 10px; text-align: left;">
						<p class="time-code">
							<span>项目创建时间 &nbsp;<fmt:formatDate
									value="${it.createTime}" pattern="yyyy-MM-dd HH:mm" /></span> <span
								style="margin-left: 20px;">项目编号:&nbsp;${it.projectNum}</span>
						</p>
						<div>
							<div class="project-logo">
								<span class="logo-title">${it.firstName}</span> <span
									class="logo-bottom"></span>
							</div>
							<div class="project-info">
								<div>
									<span class="project-name">${it.name}</span>
									<c:forEach items="${it.getIndustryValList()}" var="industrys">
									<div class="class-label">${industrys}</div>
									</c:forEach>
								</div>
								<div>
									<span class="index">开发类型:</span>&nbsp;
									<c:forEach items="${it.getSolutionListVal()}" var="types">
									<span class="value">${types}</span>
									</c:forEach>
									
								</div>
								<div>
									
									<c:if test="${it.status=='pass_wait'||it.status=='wait_audit'||it.status=='audit_in'||it.status=='unpass'||it.status=='invalid'}">
									<span class="index">预算:</span>&nbsp;<span class="value">
									${it.projectBudget}&nbsp;
									</span>
									</c:if>
									<c:if test="${it.status=='pass_already'||it.status=='stage_course'||it.status=='quality'}">
									<span class="index">费用:</span>&nbsp;<span class="value">
									${it.totalAmount}&nbsp;
									</span>
									</c:if>
								
								</div>
								<div>
									<span class="index">期望交付日期:</span>&nbsp; <span class="value"><fmt:formatDate
											value="${it.expectTime}" pattern="yyyy-MM-dd" /></span>
								</div>
								<div>
									<span class="index">共<span style="color: #000;">${it.deliveryDay}
									</span>个工作日
									</span>
								</div>
								<div>
									<span class="index"> 
									联系人:${it.contactName}
										${it.contactPhone} </span>
								</div>
							</div>
						</div>
					</div>
				</td>
				<td>
				<c:if test="${it.status!='wait_audit'&&it.status!='audit_in'&&it.status!='unpass'&&it.status!='invalid'}">
						<c:if test="${it.trustedAmount==0 }">
					   <div class="deposit">
						已交意向金：${it.intentionAmount} 元
					  </div>
							</c:if>
					<div class="deposit">
						已托管费用合计：
						<c:if test="${it.trustedAmount==0 }">
						   ${it.intentionAmount} 元
						</c:if>
						<c:if test="${it.trustedAmount!=0 }">
							${it.trustedAmount} 元
						</c:if>
						
					
					</div>
					<div class="deposit-item">本阶段已托管费用：
					  <c:if test="${it.trustStage.trusted=='true'}">
					         ${it.trustStage.amount}
					    </c:if>
					     <c:if test="${it.trustStage.trusted!='true'}">
					         0
					    </c:if> 
					    元
					    
					</div>
					 <div class="deposit-item">
					 
						股权托管：<c:if test="${it.isStock=='true'}">
						                           有
						      </c:if>
						     <c:if test="${it.isStock=='false'}">
					          	     无
						      </c:if>
					</div> 
					</c:if>
				</td>
				<td>
					<div style="padding-top: 5px;">
						<p style="font-size: 14px; color: #2CB7C9;">
						<c:if test="${it.status!='stage_course'}">
						${it.status.label}
						</c:if>
						<c:if test="${it.status=='stage_course'}">
						${it.forStage.title}
						</c:if>
						</p>
						<p style="font-size: 12px;">
						<shiro:hasPermission name="elite:project:bm:viewDetail">  
							<a href="javascript:void(0);"  id="show_detail"
								data="${it.id}">查看详情</a>
							<c:if test="${it.status=='wait_audit'}">
							<input type="button" style="background-color:red;height:12px; width:12px;border:1px;border-radius:6px;"></input>
							</c:if>
						</shiro:hasPermission>
						</p>
						<p style="font-size: 12px; color: #9B9B9B;">PM:&nbsp;${it.pmName}</p>
						<p style="font-size: 12px; color: #9B9B9B;">BM:&nbsp;${it.bmName}</p>
						
						<c:if test="${it.status!='invalid'}">
							<shiro:hasPermission name="elite:project:bm:assignBM"> 
							<p style="font-size: 12px; color: #9B9B9B;">
									<a href="javascript:void(0);"  id="change_bm" 
									data="${it.id}">更换BM</a>
							</p>
							</shiro:hasPermission>
						</c:if>
					</div>
				</td>
				<td>
					<div class="opt">
					  <c:if test="${it.status!='invalid'}">
					    <shiro:hasPermission name="elite:project:bm:projectPrefect">  
						<p>
							<a style="margin-bottom: 6px;" href="javascript:void(0);" id="assist_perfect" data="${it.id}">协助完善项目资料</a>
						</p>
						</shiro:hasPermission>
						<shiro:hasPermission name="elite:project:bm:projectCompanyPrefect">
						<p>
							<a style="margin-bottom: 6px;" href="javascript:void(0);" id="assistelite_prefect" data="${it.companyId}">协助完善项目方资料</a>
						</p>
						</shiro:hasPermission>
						<shiro:hasPermission name="elite:project:bm:aduitCompany">
						<p>
						  <c:if test="${it.auditedMemberCompany=='waitAduit'}">
						  <a href="javascript:void(0);" id="audit_company" data="${it.companyId}">审核项目方资料</a>
						  </c:if>
						</p>
						</shiro:hasPermission>
						<c:if test="${it.status!='unpass'}">
						<p>
						</p>
						<shiro:hasPermission name="elite:project:bm:assignPM">
						<p>
						     <c:if test="${it.status!='wait_audit'}">
							     <a href="javascript:void(0);" id="allot_pm" data="${it.id }">分配PM</a>
							 </c:if>
						</p>
						</shiro:hasPermission>
						<shiro:hasPermission name="elite:project:bm:addBussines">
						<p>
							<a href="javascript:void(0);" id="add_business" data="${it.id}">添加商务记录</a>
						</p>
						</shiro:hasPermission>
						<p>
						   <c:if test="${it.tendered=='true'}">
						   <shiro:hasPermission name="elite:project:bm:findDefine">
						   <a href="javascript:void(0);" id="search_define" data="${it.id}">查看立项书</a>
						   </shiro:hasPermission>
						   <c:if test="${it.status=='audit_in'||it.status=='pass_wait'}">
						   <shiro:hasPermission name="elite:project:bm:removeDefine">
						   <a href="javascript:void(0);" id="remove_define" data="${it.id}">撤销立项书</a>
						   </shiro:hasPermission>
						   </c:if>
						   </c:if>
						   <c:if test="${it.intentionStatus=='success'}">
						   <c:if test="${it.tendered=='false'}">
						    <shiro:hasPermission name="elite:project:bm:addDefine">
						  	<c:if test="${it.projectSettingStages== null || fn:length(it.projectSettingStages) == 0}">
							    <a href="javascript:void(0);" id="alert_define"  >发立项书</a>
							</c:if>
							<c:if test="${fn:length(it.projectSettingStages) >0 }">
							 	<a href="javascript:void(0);" id="add_define" data="${it.id}">发立项书</a>
						    </c:if>
						    </shiro:hasPermission>
						   </c:if>
						   </c:if>
						</p>
						</c:if>
						<p>
						</p>
						<c:if test="${it.status=='wait_audit'||it.status=='audit_in'||it.status=='unpass'}">
							<shiro:hasPermission name="elite:project:bm:invalid">  
								<p>
									<a style="margin-bottom: 6px;" href="javascript:void(0);" id="invalid" data="${it.id}">无效项目</a>
								</p>
							</shiro:hasPermission>
						</c:if>
					</c:if>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>