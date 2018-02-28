<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<header class="header">
	<a href="${_PATH}/index" class="logo"> <!-- Add the class icon to your logo image or logo icon to add the margining -->
		yunyinghui
	</a>
	<!-- Header Navbar: style can be found in header.less -->
	<nav class="navbar navbar-static-top" role="navigation">
		<!-- Sidebar toggle button-->
		<div class="navbar-header">
			<a class="navbar-brand" style="color: white;" href="#">云英汇运营中心</a>
		</div>
		<div class="navbar-right">
			<ul class="nav navbar-nav">
				<!-- Messages: style can be found in dropdown.less-->
				<li><a href="${_PATH}/message/list"> 消息箱 <i class="fa fa-envelope"></i>
					<span class="label label-success" id="unreadCountId">
					<script>
					if(sessionStorage.getItem("unreadCount")==null){
						document.write('${unreadCount}');
					}else{
						document.write(sessionStorage.getItem("unreadCount"));	
					}
					</script>
					</span>
				</a></li>
				<!-- User Account: style can be found in dropdown.less -->
				<li class="dropdown user user-menu">
					<a href="#" class="dropdown-toggle" data-toggle="dropdown">
						<i class="glyphicon glyphicon-user">&nbsp;${user.userName}</i> 
						<span><i class="caret"></i></span>
					</a>
					<ul class="dropdown-menu">
						<!-- User image -->
						<li class="user-header bg-light-blue" style="height:150px;">
						     <img src="${user.photo.path}" class="img-circle" alt=""  onerror="this.src='${_PATH}/res/img/avatar3.png'"/> 
							<p>
								 欢迎您,${userRoles}
								 ${user.userName}!
								<!--<small>Member since Nov. 2012</small>-->
							</p></li>
						<!-- Menu Footer-->
						<li class="user-footer">
							<div class="pull-left">
								<a href="javascript:void(0);" class="btn btn-default btn-flat" id="ep_btn">修改密码</a>
							</div>
							<div class="pull-right">
								<a href="javascript:void(0);" class="btn btn-default btn-flat" id="logout">退出</a>
							</div>
						</li>
					</ul></li>
			</ul>
		</div>
	</nav>
</header>
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