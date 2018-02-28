require([ "jquery", "ajax", "jsonAjax"], function($) {
	//面包屑事件
	$("#myProject").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#projectDetail,#projectDetail1").click(function(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/'+id+'/index?'+jsonAjax.random();
	});
	$(".goto-pay-btn").click(function(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/company/'+id+'/edit?'+jsonAjax.random();
	});
});