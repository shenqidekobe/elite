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
		<aside class="right-side">
			<!-- Content Header (Page header) -->
			<section class="content-header">
				<h1>500 Error Page</h1>
				<ol class="breadcrumb">
					<li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
					<li><a href="#">Examples</a></li>
					<li class="active">500 error</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">

				<div class="error-page">
					<h2 class="headline">500</h2>
					<div class="error-content">
						<h3>
							<i class="fa fa-warning text-yellow"></i> Oops! Something went
							wrong.
						</h3>
						<p>
							We will work on fixing that right away. Meanwhile, you may <a
								href='${_PATH}/index'>return to dashboard</a> or try using the
							search form.
						</p>
						<form class='search-form'>
							<div class='input-group'>
								<input type="text" name="search" class='form-control'
									placeholder="Search" />
								<div class="input-group-btn">
									<button type="button" name="submit" class="btn btn-info">
										<i class="fa fa-search"></i>
									</button>
								</div>
							</div>
							<!-- /.input-group -->
						</form>
					</div>
				</div>
				<!-- /.error-page -->

			</section>
			<!-- /.content -->
		</aside>
		<!-- /.right-side -->
	</div>
	<!-- ./wrapper -->

</body>
</html>