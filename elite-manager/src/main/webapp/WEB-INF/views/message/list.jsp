<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<script type="text/javascript">
            //邮箱未读数量
            var unreadCount= '${unreadCount}';
            sessionStorage.setItem('unreadCount',unreadCount);
          </script>
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
                我的消息箱
            </h1>
        </section>

        <!-- Main content -->
        <section class="content">
            <div class="col-md-12" style="min-width: 950px;">
                <form id="listForm" action="${_PATH}/message/listData">
                    <div class="panel panel-default">
                            <div class="panel-heading panel-message">
                                <button type="button" class="btn btn-default" id="remove">删除</button>
                                <button type="button" class="btn btn-default" id="markRead">标为已读</button>
                                <button type="button" class="btn btn-default" id="allUnread">全部未读</button>
                                <button type="button" class="btn btn-default" id="allread">全部已读</button>
                            </div>
                            <div id="listData"></div>
                    </div>
                </form>
            </div>
        </section>
    </aside>
    <!-- /.right-side -->
    <form id="detailForm">
    <input type="hidden" id="inputId" name="id"/>
    </form>
</div>
<!-- ./wrapper -->
<script src="${_PATH}/res/js/admin/message/list.js"></script>
</body>
</html>