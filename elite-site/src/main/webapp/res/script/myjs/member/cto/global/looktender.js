require([ "jquery", "ajax", "jsonAjax","md5","commons"], function($) {

	var projectId=$("#projectId").val();
	var deadlineTime=$("#deadlineTime").val();
	addTimer("deadlineSpan",deadlineTime);
	//去竞标
	$(".go-bid-btn").click(function(){
		window.location.href=ctx+'/project/c/'+projectId+'/bid?'+jsonAjax.random();
	});
	
	$(".go-bid-btn").click(function(){
		window.location.href=ctx+'/project/c/'+projectId+'/bid?'+jsonAjax.random();
	});
	
	$("#myMain, #myProject").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
});