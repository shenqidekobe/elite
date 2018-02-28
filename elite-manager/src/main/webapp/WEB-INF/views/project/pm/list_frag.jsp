<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
							<span>项目创建时间 &nbsp;<fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm" /></span> <span style="margin-left: 20px;">项目编号:&nbsp;${it.projectNum}</span>
						</p>
						<div>
							<div class="project-logo">
								<span class="logo-title">${it.firstName}</span> <span class="logo-bottom"></span>
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
										<span class="index">预算:</span>&nbsp;<span class="value"> ${it.projectBudget}&nbsp; </span>
									</c:if>
									<c:if test="${it.status=='pass_already'||it.status=='stage_course'||it.status=='quality'}">
										<span class="index">费用:</span>&nbsp;<span class="value"> ${it.totalAmount}&nbsp; </span>
									</c:if>
								</div>
								<div>
									<span class="index">期望交付日期:</span>&nbsp; <span class="value"><fmt:formatDate value="${it.expectTime}" pattern="yyyy-MM-dd" /></span>
								</div>
								<div>
									<span class="index">共<span style="color: #000;">${it.deliveryDay}</span>个工作日
									</span>
								</div>
								<div>
									<c:if test="${it.ctoId!=null}">
										<span class="index"> CTO:${it.CTOName} ${it.CTOPhone} </span>
									</c:if>
								</div>
							</div>
						</div>
					</div>
				</td>
				<td><c:if test="${it.status!='wait_audit'&&it.status!='audit_in'&&it.status!='unpass'&&it.status!='invalid'}">
						<c:if test="${it.trustedAmount==0 }">
							<div class="deposit">已交意向金：${it.intentionAmount} 元</div>
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
						<div class="deposit-item">
							本阶段已托管费用：
							<c:if test="${it.trustStage.trusted=='true'}">
					         ${it.trustStage.amount}
					    </c:if>
							<c:if test="${it.trustStage.trusted=='false'}">
					         0
					    </c:if>
							元
						</div>
						<div class="deposit-item">
							股权托管：
							<c:if test="${it.isStock=='true'}">
						                           有
						      </c:if>
							<c:if test="${it.isStock=='false'}">
					          	     无
						      </c:if>
						</div>
					</c:if></td>
				<td>
					<div style="padding-top: 5px;">
						<p style="font-size: 14px; color: #2CB7C9;">
							<c:if test="${it.status!='stage_course'}">
						${it.status.label}
						</p>
						</c:if>
						<c:if test="${it.status=='stage_course'}">
						${it.forStage.title}</p>
						</c:if>
						<shiro:hasPermission name="elite:project:pm:viewDetail">
							<p style="font-size: 12px;">
								<a href="javascript:void(0);" id="show_detail" data="${it.id}">查看详情</a>
								<c:if test="${it.pmNewProjectNotice=='true'}">
									<input type="button" style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;"></input>
								</c:if>
							</p>
						</shiro:hasPermission>
						<p style="font-size: 12px; color: #9B9B9B;">PM:&nbsp;${it.pmName}</p>
						<p style="font-size: 12px; color: #9B9B9B;">BM:&nbsp;${it.bmName}</p>
					</div>
				</td>
				<td>
					<div class="opt">
                       <c:if test="${it.status!='invalid'}">
						<c:if test="${it.status!='unpass'}">
							<p></p>
							<shiro:hasPermission name="elite:project:pm:setting">
								<c:if test="${it.status=='wait_audit'||it.status=='audit_in'||it.status=='pass_wait'||it.status=='audited'}">
									<p>
										<a href="javascript:void(0);" id="setting_stage" data="${it.id}">设置服务阶段</a>
										<c:if test="${it.projectSettingStages== null || fn:length(it.projectSettingStages) == 0}">
											<input type="button" style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;"></input>
										</c:if>
									</p>
								</c:if>
							</shiro:hasPermission>
							<p>
								<shiro:hasPermission name="elite:project:pm:addTender">
									<c:if test="${it.status=='audit_in'||it.status=='pass_wait'||it.status=='pass_already'||it.status=='stage_course'}">
										<a href="javascript:void(0);" id="send_tender" data="${it.id}">发招标书</a>
									</c:if>
								</shiro:hasPermission>
								<shiro:hasPermission name="elite:project:pm:viewTender">
									<a href="javascript:void(0);" style="margin-left: 14px;" data="${it.id}" id="tender_list">投标列表</a>
									<c:if test="${it.newTenderNotice=='true'}">
										<input type="button" style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;"></input>
									</c:if>
								</shiro:hasPermission>
							</p>
							<shiro:hasPermission name="elite:project:pm:aduitProject"> 
							<p>
								<c:if test="${it.status!='unpass'&&it.status!='pass_already'&&it.status!='stage_course'&&it.status!='finish'}">
									<a href="javascript:void(0);" id="audit_project" data="${it.id}">审核项目</a>
								</c:if>
							</p>
							</shiro:hasPermission>
							<p> 
							   <shiro:hasPermission name="elite:project:pm:viewMaterial">
								<c:if test="${it.status=='stage_course'||it.status=='quality'||it.status=='finish'}">
									<a href="javascript:void(0);" id="view_material" data="${it.id}">查看新文件</a>
									<c:if test="${it.newMaterialNotice=='true'}">
										<input type="button" style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;"></input>
									</c:if>
								</c:if>
								</shiro:hasPermission>
								<shiro:hasPermission name="elite:project:pm:viewWeekly">
								<c:if test="${it.status=='stage_course'}">
									<a href="javascript:void(0);" id="view_weekly" data="${it.id}">查看新周报</a>
									<c:if test="${it.newWeeklyNotice=='true'}">
										<input type="button" style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;"></input>
									</c:if>
								</c:if>
								</shiro:hasPermission>
							</p>
							<p></p>
						</c:if>
						</c:if>
					</div>
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>