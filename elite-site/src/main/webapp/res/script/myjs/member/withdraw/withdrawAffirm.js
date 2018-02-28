require([ "jquery", "ajax", "jsonAjax","md5","commons"], function($) {
	//面包屑处理
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#seles_manage").click(function(){
		sessionStorage.setItem('rsp_intent_isd','settlement');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	
	/*var withdrawAmount=sessionStorage.withdrawAmount;
	console.info(withdrawAmount);
	$("#wamount").text("￥"+withdrawAmount);*/
	//确认提现
	$(".confirm-btn").click(function(){
		var password=$.trim($("#password").val());
		var bankId=$("#bankId").val();
		if(password==""){
			$(".error_text").text("请输入您的登录密码");
			$(".error_div").show();
			$("#password").focus(function(){
				$(".error_div").hide();
			});
			return false;
		}
		password = $.md5(password);
		var withdrawAmount=$("#withdrawAmount").val();
		var receiptAmount=$("#receiptAmount").val();
		var invoiceWay=$("#invoiceWay").val();
		var params={
			withdrawAmount:withdrawAmount,
			receiptAmount:receiptAmount,
			bankId:bankId,
			password:password,
			invoiceWay:invoiceWay
		}
		jsonAjax.post({
			url:ctx+'/member/withdraw/save',
			data:params,
			success:function(data){
				if(data.result=="SUCCESS"){
					$(".window-confirm").show();
					var i=6;
					$("#down_time").text(i+"s");
					setInterval(function(){
						i--;
						$("#down_time").text(i+"s");
						if(i==0){
							$(".backButton").click();
						}
					}, 1000);
					$(".backButton").click(function(){
						sessionStorage.setItem('rsp_intent_isd','settlement');
						window.location.href=ctx+'/member/index?'+jsonAjax.random();
					});
				}else{
					$(".error_text").text(data.msg);
					$(".error_div").show();
					$("#password").focus(function(){
						$(".error_div").hide();
					});
				}
			}
		});
	});
});