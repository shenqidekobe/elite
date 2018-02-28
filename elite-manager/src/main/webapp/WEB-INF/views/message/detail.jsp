<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
  <c:import url="/inc/link.jsp"></c:import>
  <title>云英汇 | 系统首页</title>
</head>
<body class="skin-blue">
<!-- header logo: style can be found in header.less -->
<c:import url="/inc/header.jsp"></c:import>
<div class="wrapper row-offcanvas row-offcanvas-left">
  <!-- Left side column. contains the logo and sidebar -->
  <c:import url="/inc/left.jsp"></c:import>
  <!-- Right side column. Contains the navbar and content of the page -->
  <aside class="right-side">
    <!-- Content Header (Page header) -->
    <section class="content-header">
      <h1>
        消息详情
      </h1>
    </section>

    <!-- Main content -->
    <section class="content">
      <div class="col-md-12" style="min-width: 950px;">
        <div class="panel panel-default">
          <div class="panel-heading">
            <button type="button" class="btn btn-default" id="backMessageList" >返回</button>
            <button type="button" class="btn btn-default" id="remove" value="${message.id}">删除</button>
            <label style="float: right;">
              <c:choose>
                <c:when test="${message.id <= 1}">
                  <c:set var="lastHidden" value="display: none"></c:set>
                  <c:set var="last" value="${message.id}" />
                  <c:set var="next" value="${message.id + 1}" />
                </c:when>
                <c:when test="${message.id >= maxCount}">
                  <c:set var="nextHidden" value="display: none"></c:set>
                  <c:set var="last" value="${message.id - 1}" />
                  <c:set var="next" value="${message.id}" />
                </c:when>
                <c:otherwise>
                  <c:set var="last" value="${message.id - 1}" />
                  <c:set var="next" value="${message.id + 1}" />
                </c:otherwise>
              </c:choose>
              <a href="${_PATH}/message/detail?id=${last}" style="${lastHidden}">上一条</a>
              <a href="${_PATH}/message/detail?id=${next}" style="${nextHidden}">下一条</a>
            </label>
          </div>
          <div class="panel-body">
            <div class="panel panel-default">
                <ul class="list-group">
                  <li class="list-group-item">主题  ：${message.title}</li>
                  <li class="list-group-item">发件人  ：云英汇小秘书</li>
                  <li class="list-group-item">时间 ： <fmt:formatDate value="${message.createTime}" pattern="yyyy-MM-dd HH:mm:ss" /> </li>
                  <li class="list-group-item">内容 ： ${message.content}</li>
                </ul>
              </div>
            </div>
          </div>
        </div>
    </section>
  </aside>
  <!-- /.right-side -->
</div>
<!-- ./wrapper -->
<script src="${_PATH}/res/js/admin/message/detail.js"></script>
</body>
</html>