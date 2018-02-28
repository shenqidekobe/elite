require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	
	$("#hallIndex").click(function(){
		window.location.href=ctx+'/hall?'+jsonAjax.random();
	});
	
	if($("#deadlineSpan").length>0){
		var deadlineTime=$("#deadlineTime").val();
		addTimer("deadlineSpan",deadlineTime);
	}
	
	$("#apply").click(function(){
		var id=$(this).attr("data");
		var oper=$(this).attr("oper");
		if(oper=='N'){
			tostConfirm("您确定要取消报名吗？",function(){
				apply(id,oper);
			});
		}else{
			apply(id,oper);
		}
		
	});
	function apply(id,oper){
		jsonAjax.post({
			url:ctx+'/task/apply?'+jsonAjax.random(),
			data:{taskId:id,oper:oper},
			success:function(data){
				if(data.result=="SUCCESS"){
					if(oper=="Y"){
						$("#apply").attr("oper","N");
						$("#apply").text("取消报名");
						tostHint("报名成功","您已成功报名此任务，请耐心等待");
					}else{
						$("#apply").attr("oper","Y");
						$("#apply").text("我要报名");
						tostHint("报名取消成功","您已成功取消此任务的报名");
					}
					$("#applyCount").text(data.id+"人报名");
				}else{
					tostHint("报名失败",data.msg);
				}
			}
		});
	}
	$(".download-icon").click(function(){
		var href=$(this).attr("data");
		window.open(href);
	});
});