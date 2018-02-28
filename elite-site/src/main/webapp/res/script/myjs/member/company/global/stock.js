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
    $(".ok-btn").click(function(){
    	var id=$(this).attr("data");
    	if(!$("#stockCheck").is(":checked")){
    		tostHint("请确认云英汇托管协议书","请确认云英汇托管协议书");
    		return false;
    	}
		jsonAjax.post({
			url:ctx+'/project/stock/save',
			data:{projectId:id},
			success:function(data){
				window.location.href=ctx+'/project/'+id+'/trustcost?'+jsonAjax.random();
			}
		});
    });
});
