<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/inc/taglibs.jsp"%>
<!DOCTYPE html>
<html>
<head>
<c:import url="/inc/link.jsp"></c:import>
<title>云英汇 | 系统首页</title>
</head>
<script>
  //招标结束时间
  var tenderoverTime='${lastTender.tenderoverTime}'
</script>
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
					项目中心 <small>我的股权项目</small>
				</h1>
				<ol class="breadcrumb">
					<li><a href="${_PATH}/index"><i class="fa fa-dashboard"></i> 首页</a></li>
					<li class="active">项目中心</li>
				</ol>
			</section>

			<!-- Main content -->
			<section class="content">
			<form id="listForm">
                    <div class="col-md-12" style="min-width: 950px;">
                        <div class="box" style="padding: 10px 10px; width: 95%; min-width: 950px;">
                        	<button type="button" class="btn btn-primary"
							style="margin: 10px 0 10px 10px;" id="back">返回列表</button>
                            <div class="title-h4" style="border-bottom: 1px solid #C1C1C1;">
                                <h4>标书列表:</h4>
                            </div>
                            <input type="hidden" value="${id}" name="id" id="projectId"/>
                            <input type="hidden" value="${lastTender.id}" name="tenderId" id="tenderId"/>
                            <div class="sort-condation">
                                <div class="form-group">
                                    <c:if test="${lastTender.status!='tender_cancel'&&lastTender.status!='tender_stop'}">
                                    <span for="ck1" id="countdownId"></span>
                                    </c:if>
                                    <c:if test="${lastTender.status=='tender_cancel'}">
                                    <span for="ck1">本次招标于<fmt:formatDate value="${lastTender.closeTime}" pattern="yyyy-MM-dd HH:mm:ss"/>取消</span>
                                    </c:if>
                                    <c:if test="${lastTender.status=='tender_stop'}">
                                    <span for="ck1">本次招标已经结束</span>
                                    </c:if>
                                </div>
                             
                                <div>
                                   <a href="javascript:void(0)" data="${it.memberId}" id="show_tenderdetail">查看招标书</a>
                                </div>
                               <c:if test="${lastTender.status=='tender_in'}">
                                 <div>
                                   <a href="javascript:void(0)" data="${id}" id="show_recommend">推荐给CTO</a>
                                </div>
                                <div>
                                    <a href="javascript:void(0)" id="remove_tender" data="${lastTender.id}">取消目前招标</a>
                                </div>
                                <div>
                                </div>
                                 </c:if>
                                 <c:if test="${lastTender.status=='tender_cancel'}">
                                 <div>
                                 <a href="javascript:void(0)"  id="send_tender" data="${id}">重新发招标书</a>
                                 </div>
                                 </c:if>
                              <!--   <div>
                                    <a href="#">取消目前招标并重新发招标书</a>
                                </div> -->
                            </div>
                            	<div id="listData">
                           
                             </div>
                        </div>
                        </form>
                </section> 
		</aside>
		<form id="detailForm" action=""
			hidden="true" method="post">
			<input type="text" name="id" id="detailFormInputId" />
			<input type="text" name="projectTenderRecordId" id="projectTenderRecordId" />
		</form>
	</div>
	<!-- ./wrapper -->
<script src="${_PATH}/res/js/admin/project/tender/recommend.js"></script> 
<script src="${_PATH}/res/js/admin/project/tender/calibration.js"></script> 
<script src="${_PATH}/res/js/admin/project/tender/list.js" >
</script> 
<div class="modal fade" id="myModel" tabindex="-1" role="dialog"
		aria-labelledby="myModalLabel">
		<div class="modal-dialog" role="document" id="data"
			style="width: 800px;"></div>
	</div>
</body>
</html>