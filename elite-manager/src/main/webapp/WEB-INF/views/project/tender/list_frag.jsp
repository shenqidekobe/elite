<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<input type="hidden" id="tenderWinedId" value="${tenderWined}"/>
<table class="tab-tender">
	<thead>
		<tr>
			<td style="width: 80px;">CTO姓名</td>
			<!-- <td style="width: 50px;">评分</td> -->
			<td style="width: 110px;">已完成项目数</td>
			<td style="width: 110px;">运行中项目数</td>
			<td style="width: 110px;">整体报价(元)</td>
			<td style="width: 140px;">整体时间</td>
			<td style="width: 90px;">结果</td>
			<td style="width: 120px;">投标时间</td>
			<td style="width: 50px;">竞标书</td>
			<!-- <td style="">标记</td> -->
			<td style="width: 90px;">记录</td>
			<td style="width: 180px;">定标</td>
			<td style="width: 180px;">发定标书</td>
		</tr>
	</thead>
	<c:forEach items="${list}" var="it">
	<tr>
		<td>${it.memberName}</td>
		<!-- <td></td> -->
		<td>${it.projectDoneCount}</td>
		<td>${it.projectInCount}</td>
		<td>${it.amount}</td>
		<td>${it.workingStart}/${it.workingEnd}共${it.workingDay}天</td>
		<td>${it.status.label}${lastTender.status}</td>
		<td><fmt:formatDate value="${it.createTime}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
		<td><a href="javascript:void(0)" data="${it.id}" id="show_detail">查看</a></td>
		
		<td><a href="javascript:void(0)" data="${it.memberId}" id="showWorkLogs">工作记录</a></td>
		<td>
		<c:if test="${it.status=='tender_in'}">
		 <c:if test="${tenderWined=='false'&&tenderNormal=='false'}">
		<a href="javascript:void(0)" data="${it.id}" id="show_calibration">定标</a>
		</c:if>
		 <c:if test="${tenderWined=='true'||tenderNormal=='true'}">
		     ------
		</c:if>
		</c:if>
		<c:if test="${it.status=='tender_not'}">
		     ------
		</c:if>
		<c:if test="${it.status=='tender_win'||it.status=='tender_normal'}">
		 <c:if test="${project.tendered=='true'}">
		       <small ><font color="gray">取消定标</font></small>
		 </c:if>
		 <c:if test="${project.tendered!='true'}">
		 	<a href="javascript:void(0)" data="${it.id}" id="cancelshow_calibration">取消定标</a>
		 </c:if>
		</c:if>
		<!-- 中标后显示 -->
		</td>
		<td>
	    <c:if test="${it.status=='tender_win'||it.status=='tender_normal'}">
	          <!-- 是否发过定标书 -->
	          <c:if test="${project.tendered=='true'}">
	           <a href="javascript:void(0)" data="${it.id}" id="search_define">查看定标书</a>
	           <!-- 项目未立项之前可以撤回定标书 -->
	              <c:if test="${tenderNormal=='false'}">
	                <a href="javascript:void(0)" data="${it.id}" id="remove_define">取消定标书</a>
	              </c:if>
	           </c:if>
	        <c:if test="${project.tendered=='false'}">
	          <c:if test="${project.firstStagePayStatus=='true'}">
	          <a href="javascript:void(0)" data="${it.id}" id="add_define">发定标书</a>
	          </c:if>
	          <c:if test="${project.firstStagePayStatus=='false'}">
	                                               项目第一阶段未付款，不能发定标书
	          </c:if>
	    </c:if>
	    </c:if>
	    <c:if test="${it.status=='tender_in'||it.status=='tender_not'}">
	        ------
	    </c:if>
		</td>
	</tr>
	</c:forEach>
</table>
<c:import url="/inc/pager.jsp"></c:import>