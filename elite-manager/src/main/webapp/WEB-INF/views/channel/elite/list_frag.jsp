<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
 <h5 class="box-title" align="left">审核通过渠道方( ${normalCount} )&nbsp;&nbsp; 共注册渠道方( ${allCount} )</h5>
<table class="table table-bordered" id="tab">
	<tbody id="tbody">
		<c:forEach items="${list}" var="it">
			<tr style="text-align: center;">
				<td colspan="2"><small>注册时间:<fmt:formatDate value="${it.partnerElite.createTime}" pattern="yyyy-MM-dd HH:mm" /></small></td>
				<td colspan="2"><small>渠道编号:${it.partnerElite.channelNum}</small></td>
				<td><small>Ta的推荐人:${it.partnerElite.parentName}</small></td>
				<td><small>负责人:${it.partnerElite.chargeName}</small></td>
			</tr>
			<tr>
				<td width="15%" align="center">
					<c:if test="${it.basic.photoId!=null }"><img src="${it.basic.memberPhoto.path}" height="105px" width="105px"></c:if>
					<c:if test="${it.basic.photoId==null }"><img src="${_PATH}/res/img/default.jpg" height="105px" width="105px" /></c:if>
				</td>
				<td>
					<div>${it.nickName}</div>
					<div>${it.accountOffSuffix}</div>
				</td>
				<td>
					<div>姓名:${it.credit.realName}</div>
					<!-- <div>机构:${it.partnerElite.companyName}</div> -->
					<div>类别:${it.partnerElite.type.label}</div>
				</td>
				<td>
					<div>收益总额:${it.memberAccount.totalIncome}</div>
					<div>备案及入驻人才数:${it.partnerElite.putCount}(${it.partnerElite.enterCount})</div>
					<div>备案及入驻人才渠道数:${it.partnerElite.putParnterCount}(${it.partnerElite.enterParnterCount})</div>
				</td>
				<td>
				　　	<div>
						<div>
							<c:if test="${it.status=='disabled'}">
								${it.status.label}
							</c:if>
							<c:if test="${it.status!='disabled'}">
								${it.partnerElite.status.label}
							</c:if>
						</div>
						<shiro:hasPermission name="elite:channel:project:viewDetail">
							<div><a href="javascript:void(0);" id="detail" data="${it.id}">查看详情</a></div>
						</shiro:hasPermission>	
					</div>
				</td>
				<td>
					<div style="margin-top: 25px;" class="opt">
						<div>
							<shiro:hasPermission name="elite:channel:project:allot">
								<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="allot" data="${it.id}">分配负责人</a></p>
							</shiro:hasPermission>
							<shiro:hasPermission name="elite:channel:member:aduit">
								<c:if test="${it.partnerElite.status=='aduitIn'}">
									<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="aduit" data="${it.id}">审核</a>
									<input style="background-color: red; height: 12px; width: 12px; border: 1px; border-radius: 6px;" type="button">
									</p>
								</c:if>
								<c:if test="${it.partnerElite.status!='aduitIn'}">
									<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="aduit" data="${it.id}">强审核</a></p>
								</c:if>
							</shiro:hasPermission>	
							<shiro:hasPermission name="elite:channel:member:remark">
								<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="addRemarks" data="${it.id}">备注</a></p>
							</shiro:hasPermission>	
							<!--<p><a style="margin-bottom: 6px;" href="javascript:void(0);" id="" data="${it.id}">协助编辑资料</a></p>-->
						</div>
					</div>					
				</td>
			</tr>
		</c:forEach>
	</tbody>
</table>
<c:import url="/inc/pager.jsp"></c:import>