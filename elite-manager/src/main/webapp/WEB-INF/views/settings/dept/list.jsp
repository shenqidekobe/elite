<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
    <head>
        <c:import url="/inc/link.jsp"></c:import>
        <link rel="stylesheet" href="${_PATH}/res/css/ztree/zTreeStyle/zTreeStyle.css" type="text/css">
        <style type="text/css">
			div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
			div#rMenu ul li{
				margin: 1px 0;
				padding: 0 5px;
				cursor: pointer;
				list-style: none outside none;
				background-color: #DFDFDF;
			}
	    </style>
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
                    <h1>   系统设置
                        <small>部门管理</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li class="active">部门管理</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="col-md-12" style="min-width: 950px;">
                        <div class="box">
                            <div class="zTreeDemoBackground left">
								<ul id="deptTree" class="ztree"></ul>
							</div>
                        </div>
                </section>   
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        <script type="text/javascript" src="${_PATH}/res/js/ztree/jquery.ztree.core.js"></script>
        <script type="text/javascript" src="${_PATH}/res/js/admin/settings/dept/list.js"></script>
        
        <div id="rMenu">
			<ul>
				<li id="m_add" onclick="dept.addDept();">新建子部门</li>
				<li id="m_upt" onclick="dept.uptDept();">修改当前部门</li>
				<li id="m_del" onclick="dept.delDept();">删除当前部门</li>
			</ul>
		</div>
		
		<div class="modal fade" id="deptModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document">
			<form action="${_PATH}/settings/dept/save" id="deptForm" method="post">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal"
							aria-label="Close">
							<span aria-hidden="true">×</span>
						</button>
						<input id="deptId" type="hidden" name="id">
						<input id="parentId" type="hidden" name="parentId">
						<input id="organId" type="hidden" name="organId">
					</div>
					<div class="modal-body">
						<div class="form-group">
							<div class="col-xs-6">
								<label for="txt_departmentlevel">名称</label> <input
									type="text" name="name" class="form-control"
									id="name" placeholder="输入部门名称">
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default"
							data-dismiss="modal">
							<span class="glyphicon glyphicon-remove" aria-hidden="true"></span>关闭
						</button>
						<button type="submit" id="btn_submit" class="btn btn-primary">
							<span class="glyphicon glyphicon-floppy-disk"
								aria-hidden="true"></span>保存
						</button>
					</div>
				</div>
			</form>
		</div>
	</div>
    </body>
</html>