require([ "jquery", "ajax", "jsonAjax","commons"], function($) {

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
	$("#define_book").click(function(){
		var url=ctx+'/member/index?'+jsonAjax.random();
		window.open(url);
	});
	$(".ok-btn").click(function(){
		var agreeDefine=$("#agreeDefine").is(":checked");
		var projectId=$("#projectId").val();
		if(agreeDefine==false){
			tostHint("确认提醒","请先确认立项条款");
			return false;
		}
		tostConfirm("确定要立项嘛？",function(){
			jsonAjax.post({
				url:ctx+'/project/define/save',
				data:{projectId:projectId},
				success:function(data){
					console.info(data);
					if(data.result=="SUCCESS"){
						$(".complete-success").show();
						var i=6;
						setInterval(function(){
							$("#down_time").text(i+"s");
							if(i==0){
								$("#enter_index").click();
							}
							i--;
						}, 1000);
						$("#enter_index,.resu-btn").click(function(){
							window.location.href=ctx+'/member/index?'+jsonAjax.random();
						});
					}else{
						tostHint("提交失败",data.msg);
					}
				}
			});
		});
	});
});