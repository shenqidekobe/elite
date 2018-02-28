<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>

<!-- 入驻项目 -->
<table class="details-table">
 <br>
 <p><fmt:formatDate value="${month}" pattern="yyyy年MM月" />周报</p>
 <input type="hidden" value="${projectId}" id="projectId" />
 <div class="report-box">
   <c:forEach items="${weeklyMap.keySet()}" var="wk">   
    <hr>
        <c:if test="${wk==null}">
            <div class="report" style="margin-top:0;">
	            <div class="column1">${wk.startTime}-${wk.endTime} &nbsp;&nbsp;
	                <c:if test="${wk.currentWeek=='true'}">
	                     <span style="color:#FEA600;">当前周</span>
	                </c:if>
	                 <c:if test="${wk.currentWeek=='false'}">
	                                                             周报
	                </c:if>
	            </div>
	            <div class="column2">未提交周报</div>
	            <div class="report-opt">
	            		<img src="/res/images/upload_row_icon.png" class="opt-icon" flag="${wk.endTime}" data="${wk.endTime}" id="upload_weekly">
		            	<input type="file" name="file" id="weeklyFile_${wk.endTime}" style="display:none;">
	            </div>
	        </div>
	    </c:if>
	     <c:if test="${wk!=null}">
	     <div class="column1"><fmt:formatDate value="${wk.startTime}" pattern="MM/dd" />-<fmt:formatDate value="${wk.endTime}" pattern="MM/dd" />&nbsp;
			            <c:if test="${wk.currentWeek=='true'}">
	                     <span style="color:#FEA600;">当前周</span>
	                   </c:if>
	                    <c:if test="${wk.currentWeek=='false'}">
	                                                             周报
	                </c:if>
	         <c:if test="${weeklyMap.get(wk).size()!=0 }">
            <c:forEach items="${weeklyMap.get(wk)}" var="wkcontext">
		        <div class="report">
			      &nbsp; &nbsp;  <span style="font-size: 14px;">${wkcontext.atta.fileName}</span>
			        <fmt:formatDate value="${wkcontext.createTime}" pattern="MM/dd HH:mm" /></span>
			                     <a href="${wkcontext.atta.downPath}" target="_blank">点击下载</a>
			       
			        &nbsp; &nbsp;   <c:if test="${type=='cto'}">
			       
			           <c:if test="${wkcontext.status=='wait_audit'}"> 
			           <button type="button" id="weekPassAudit" data="${wkcontext.id}" time="<fmt:formatDate value="${wk.startTime}" pattern="MM/dd" />-<fmt:formatDate value="${wk.endTime}" pattern="MM/dd" />">审核通过</button>
			            <button type="button" id="weekunPassAudit"  data="${wkcontext.id}" time="<fmt:formatDate value="${wk.startTime}" pattern="MM/dd" />-<fmt:formatDate value="${wk.endTime}" pattern="MM/dd" />">审核不通过</button>
			            </c:if>
			            <c:if test="${wkcontext.status=='normal'}">
			                                          已经通过审核
			            </c:if>
			            <c:if test="${wkcontext.status=='unaudit'}">
			                                          审核未通过
			            </c:if>
	                   </c:if> 
	                     </div>
			    </div>
	        </c:forEach>
	        </c:if>
	         <c:if test="${weeklyMap.get(wk).size()==0 }">
	           <br>
			            &nbsp; &nbsp;             未提交周报&nbsp;
			            
			            <c:if test="${type=='company'}">
			            <button type="button" id="uploadWeekly" data="company">上传周报</button>
			             <input type="file" name="file"
						id="uploadWeekFile" style="display: none;"> 
						<span></span>
						<input type="hidden" name="attaId"
						id="attaId" />
						<button type="button" id="submitWeek"  style="display: none;">确认上传</button>
						<button type="button" id="cancleWeek"  style="display: none;">取消</button>
						<input type="hidden"  value='<fmt:formatDate value="${wk.endTime}" pattern="yyyy-MM-dd" />'/>
	                   </c:if>
	                   <c:if test="${type=='cto'}">
	                   <button type="button" id="askWeek" data="<fmt:formatDate value="${wk.startTime}" pattern="MM/dd" />-<fmt:formatDate value="${wk.endTime}" pattern="MM/dd" />">索要周报</button>
	                   </c:if>
	        </c:if>
        </c:if>
    </c:forEach>
</div>





</table>



