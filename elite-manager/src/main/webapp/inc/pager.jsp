<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- 分页JS代码 -->
<script type="text/javascript">
	//上一页
	function back() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 <= 1) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 1);
		pageSubmit();
	}
	//下一页
	function next() {
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 0 + 1);
		pageSubmit();
	}
	//跳转页
	function jump(pageNum) {
		$("#pager_pageth").val(pageNum - 0);
		pageSubmit();
	}
	//提交页面
	function pageSubmit() {
		list.search();
		//返回页面顶部
		window.scrollBy(0, -10000);
	}
	function pagethSelect(index){
		$("#pager_pageth").val(index);
		list.search();
	}
	$("#pagethSelect").change(function(){
		$("#pager_pageth").val(this.options[this.options.selectedIndex].value);
		list.search();
	})
</script>
<div class="box-footer clearfix">
	<ul class="pagination pagination-sm no-margin pull-right">
		<c:if test="${pager.pageCount!=0}">
		<c:if test="${pager.pageCount!=1}">
			<li><a class="disable">第
			<select id="pagethSelect" >
						<c:forEach var="x" begin="1" end="${pager.pageCount}">
                            <option value="${x}"
                            <c:if test="${pager.pageth==x}">
                              selected
                             </c:if> 
                            >${x}</option>
                         </c:forEach>
				</select> 页
			</a></li>
			</c:if>
			<li><a href="###" class="disable">共${pager.pageCount}页</a></li>
		</c:if>
		<li><a href="###" class="disable">共${pager.rowCount}条</a></li>

		<c:if test="${pager.pageCount > 1}">
			<!-- 计算上一页按钮样式 -->
			<c:choose>
				<c:when test="${pager.pageth == 1}">
					<li><a href="###" class="disable">&lt;上一页</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:back()">&lt;上一页</a></li>
				</c:otherwise>
			</c:choose>
			<!-- 计算分页样式 -->
			<!-- 只有一页时 -->
			<c:choose>
				<c:when test="${pager.pageCount < 2}">
					<li class="active"><a href="javascript:jump('1')">1</a></li>
				</c:when>
				<c:otherwise>
					<!-- 包含多页时 -->
					<!-- 首页 -->
					<c:choose>
						<c:when test="${pager.pageth == 1}">
							<li class="active"><a href="javascript:jump('1')">1</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:jump('1')">1</a></li>
						</c:otherwise>
					</c:choose>
					<!-- 是否显示首页分割 -->
					<c:if test="${pager.headView eq true}">
						<li><a>...</a></li>
					</c:if>
					<!-- 显示中间页 -->
					<c:forEach items="${pager.pageths}" var="temp">
						<c:choose>
							<c:when test="${temp == pager.pageth}">
								<li class="active"><a href="javascript:jump('${temp}')">${temp}</a></li>
							</c:when>
							<c:otherwise>
								<li><a href="javascript:jump('${temp}')">${temp}</a></li>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					<!-- 是否显示尾页分割 -->
					<c:if test="${pager.footView eq true}">
						<li><a>...</a></li>
					</c:if>
					<!-- 尾页 -->
					<c:choose>
						<c:when test="${pager.pageth == pager.pageCount}">
							<li class="active"><a
								href="javascript:jump('${pager.pageCount}')">${pager.pageCount}</a></li>
						</c:when>
						<c:otherwise>
							<li><a href="javascript:jump('${pager.pageCount}')">${pager.pageCount}</a></li>
						</c:otherwise>
					</c:choose>
				</c:otherwise>
			</c:choose>
			<!-- 计算下一页按钮样式 -->
			<c:choose>
				<c:when test="${pager.pageth == pager.pageCount}">
					<li><a href="###" class="disable">下一页&gt;</a></li>
				</c:when>
				<c:otherwise>
					<li><a href="javascript:next()">下一页&gt;</a></li>
				</c:otherwise>
			</c:choose>
		</c:if>
	</ul>
</div>
<input type="hidden" id="pager_pageCount" name="pageCount"
	value="${pager.pageCount}" />
<input type="hidden" id="pager_pageth" name="pageth"
	value="${pager.pageth}" />
<input type="hidden" id="pager_rowCount" name="rowCount"
	value="${pager.rowCount}" />
<input type="hidden" id="pager_pageSize" name="pageSize"
	value="${pager.pageSize}" />