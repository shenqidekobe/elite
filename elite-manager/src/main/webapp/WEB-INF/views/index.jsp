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
                    <h1>   云英汇管理中心
                        <small>系统首页</small>
                    </h1>
                    <ol class="breadcrumb">
                        <li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
                        <li class="active">管理中心</li>
                    </ol>
                </section>

                <!-- Main content -->
                <section class="content">
                    <div class="col-md-12" style="min-width: 950px;">
                        <div class="box">
                            <div style="margin-top:30px;margin-left:5px;">
                                <span style="font-size:30px;">${user.userName} 欢迎进入云英汇后端支撑平台。&nbsp;&nbsp;&nbsp;&nbsp;<a href="https://www.yunyinghui.com" target="_blank">进入平台</a></span>
                            </div>
                        </div>
                </section>   
            </aside><!-- /.right-side -->
        </div><!-- ./wrapper -->
        
        <script src="${_PATH}/res/js/plugins/validate/jquery.validate.min.js"></script>
		<script src="${_PATH}/res/js/plugins/validate/jquery.validate.messages_cn.js"></script>
		<script src="${_PATH}/res/js/crypto/core-min.js"></script>
		<script src="${_PATH}/res/js/crypto/cipher-core-min.js"></script>
		<script src="${_PATH}/res/js/crypto/enc-base64-min.js"></script>
		<script src="${_PATH}/res/js/crypto/mode-ecb-min.js"></script>
		<script src="${_PATH}/res/js/crypto/tripledes-min.js"></script>
		<script src="${_PATH}/res/js/common/encrypt.js"></script>
		<script src="${_PATH}/res/js/admin/index.js"></script>
		<script type="text/javascript">
            //邮箱未读数量
            var unreadCount= '${unreadCount}';
            sessionStorage.setItem('unreadCount',unreadCount);
          </script>
		<div class="modal fade" id="epModel" tabindex="-1" role="dialog"
			aria-labelledby="myModalLabel">
			<div class="modal-dialog" role="document">
				<form  id="epForm" method="post">
					<div class="modal-content">
						<div class="modal-header">
							<button type="button" class="close" data-dismiss="modal"
								aria-label="Close">
								<span aria-hidden="true">×</span>
							</button>
							<h4 class="modal-title" id="myModalLabel">修改密码</h4>
						</div>
						<div class="modal-body">
						    <div class="col-xs-12">
						       <div class="form-group">
						           <label for="txt_departmentlevel">旧密码</label>
						           <input type="password" name="oldPass" class="form-control" id="oldPass" placeholder="输入旧密码">
						       </div>
							</div>
							<div class="col-xs-12">
						       <div class="form-group">
						           <label for="txt_departmentlevel">新密码</label>
						           <input type="password" name="newPass" class="form-control" id="newPass" placeholder="输入新密码">
						       </div>
							</div>
							<div class="col-xs-12">
						       <div class="form-group">
						           <label for="txt_departmentlevel">确认密码</label>
						           <input type="password" name="affirmPass" class="form-control" id="affirmPass" placeholder="输入确认密码">
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