require(["jquery", "ajax", "jsonAjax","commons","md5"], function($) {
	
	$(".backButton ,#myProject").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	})
	
	$("#closeWindow").click(function(){
		$(".bac-window").hide();
		var id = $(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/acceptance?'+jsonAjax.random();
	})
	
	$("#forgetPassport").click(function(){
		window.location.href=ctx+'/forget/p?'+jsonAjax.random();
	})
	
	$("#payConfirm").click(function(){
		var passpord = $.trim($("#password").val());
		if(password==''){
			console.info("请输入密码");
			return false;
		}jsonAjax.post({
			url : ctx + '/project/c/acceptanceTask',
			data:{taskId:$(this).attr("data"),password:$.md5(passpord)} ,
			success : function(data) {
				if (data.result == "SUCCESS") {
					$("#errortip").html("");
					$(".bac-window").show();
					var i=6;
					setInterval(function(){
						$(".colorfe").text(i+"s");
						if(i==0){
							window.location.href=ctx+'/member/index?'+jsonAjax.random();
						}
						i--;
					}, 1000);
				}else{
					$("#errortip").html("<div class='errortip'><span class='error_icon'></span><span class='error_text'>密码不对</span></div>");
				}	
			}
		});
	})
	
});	
