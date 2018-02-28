<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<aside class="left-side sidebar-offcanvas">
	<!-- sidebar: style can be found in sidebar.less -->
	<section class="sidebar">
		<!-- Sidebar user panel -->
		<div class="user-panel">
			<div class="pull-left image">
				   <img src="${user.photo.path}" class="img-circle" alt=""  onerror="this.src='${_PATH}/res/img/avatar3.png'"/> 
			</div>
			<div class="pull-left info">
				<p>你好, ${user.userName}!</p>
				<a href="#"><i class="fa fa-circle text-success"></i> 在线</a>
			</div>
		</div>
		<!-- sidebar menu: : style can be found in sidebar.less -->
		<ul class="sidebar-menu">
			<c:forEach items="${menus}" var="parent" varStatus="st">
			    <c:set var="urls" value=""/>
			    <c:set var="join" value=","/>
			    <c:set var="menuIcon" value="fa-bar-chart-o"/>
			    <c:if test="${parent.flag eq 'elite:project'}">
			        <c:set var="menuIcon" value="fa-th"/>
			    </c:if>
			    <c:if test="${parent.flag eq 'elite:member'}">
			        <c:set var="menuIcon" value="fa-laptop"/>
			    </c:if>
			    <c:if test="${parent.flag eq 'elite:settlement'}">
			        <c:set var="menuIcon" value="fa-table"/>
			    </c:if>
			    <c:if test="${parent.flag eq 'elite:channel'}">
			        <c:set var="menuIcon" value="fa-calendar"/>
			    </c:if>
			    <c:if test="${parent.flag eq 'elite:setting'}">
			        <c:set var="menuIcon" value="fa-envelope"/>
			    </c:if>
			    <c:if test="${parent.flag eq 'elite:logs'}">
			        <c:set var="menuIcon" value="fa-folder"/>
			    </c:if>
				<c:forEach items="${parent.haveChilds}" var="c">
				    <c:set var="urls" value="${_PATH}${c.paths}${join}${urls}"/>
				</c:forEach>
			    <c:set var="active" value=""/>
				<c:if test="${fn:contains(urls, _URL)}">
				   <c:set var="active" value="active"/>
	            </c:if>
				<li class="treeview ${active}">
			        <a href="javascript:void(0);">
				        <i class="fa ${menuIcon}"></i>
						<span>${parent.name}</span>
						<i class="fa fa-angle-left pull-right"></i>
					</a>
					<ul class="treeview-menu">
						<c:forEach items="${parent.haveChilds}" var="child" varStatus="ct">
						    <c:set var="active" value=""/>
						    <c:set var="url" value="${_PATH}${child.paths}"/>
						    <c:if test="${url eq _URL}"><c:set var="active" value="active"/></c:if>
							<li data="${child.paths}" class='${active}'>
								<a href="${_PATH}${child.paths}"><i class="fa fa-angle-double-right"></i>${child.name}</a>
							</li>
						</c:forEach>
					</ul>
			</c:forEach>
		</ul>
	</section>
	<!-- /.sidebar -->
</aside>
<script type="text/javascript">
$(function(){
	var url=GetUrlRelativePath();
	//$(".treeview-menu li").removeClass("active");
	//$(".treeview").removeClass("active");
	$(".treeview-menu li").each(function(){
		/* var thiss=$(this);
		if(thiss.attr("data")==url){
			thiss.parent().parent().addClass("active");
			thiss.addClass("active");
			return false;
		} */
	});
	function GetUrlRelativePath(){
		var url = document.location.toString();
		var arrUrl = url.split("//");
		var start = arrUrl[1].indexOf("/");
		var relUrl = arrUrl[1].substring(start);//stop省略，截取从start开始到结尾的所有字符
		if(relUrl.indexOf("?") != -1){relUrl = relUrl.split("?")[0];}return relUrl;}
});
</script>
