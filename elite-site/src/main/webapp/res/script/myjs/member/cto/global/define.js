require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	
	//面包屑事件
	$("#myProject").click(function(){
		sessionStorage.setItem('rsp_intent_isd','projects');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#projectDetail").click(function(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/index?'+jsonAjax.random();
	});
	$("#define_book").click(function(){
		var url=ctx+'/member/index?'+jsonAjax.random();
		window.open(url);
	});
	$(".ok-btn").click(function(){
		var agreeDefine=$("#agreeDefine").is(":checked");
		if(agreeDefine==false){
			tostHint("确认提醒","请先确认立项条款");
			return false;
		}
		tostConfirm("确定要立项嘛？",function(){
			var projectId = $("#project_id").val();
			jsonAjax.post({
				url:ctx+'/project/c/define/save',
				data:{"projectId":projectId},
				success:function(data){
					sessionStorage.setItem('rsp_intent_isd','projects');
					window.location.href=ctx+'/member/index?'+jsonAjax.random();
				}
			});
		});
	});
});