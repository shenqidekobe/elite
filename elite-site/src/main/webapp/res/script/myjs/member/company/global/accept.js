require([ "jquery", "ajax", "jsonAjax","md5","commons"], function($) {
	//面包屑事件
	$("#myProject").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#projectDetail,#projectDetail1").click(function(){
		var id=$(this).attr("data");
		window.open(ctx+'/project/'+id+'/index?rp=material&'+jsonAjax.random());
	});
	$(".bac-pay-confirm").click(function(){
		var id=$(this).attr("data");
		var password=$.trim($("#password").val());
		if(password==""){
			tostHint("提示信息","请输入您的帐号密码");
			return false;
		}
		password = $.md5(password);
		jsonAjax.post({
			url:ctx+'/project/accept/save',
			data:{
				projectId:id,
				password:password
			},
			success:function(data){
				if(data.result=="SUCCESS"){
					$(".bac-window").show();
					$(".window-close").click(function(){
						location.reload();
						//$(".bac-window").hide();
					});
					$("#trustNext").click(function(){
						//托管下一阶段费用
						window.location.href=ctx+'/project/'+id+'/trustcost?'+jsonAjax.random();
					});
					$("#evaluate").click(function(){
						//先去评价
						window.location.href=ctx+'/member/index?'+jsonAjax.random();
					});
				}else{
					console.info(data);
					tostHint("提交失败",data.msg);
				}
			}
		});
		
	});
});