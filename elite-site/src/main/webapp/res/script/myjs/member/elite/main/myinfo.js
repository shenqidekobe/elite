require([ "jquery", "ajax", "jsonAjax","bootstrap-datepicker","webupload"], function($) {

	var idcardreg = /^(\d{4})\d+(\d{4})$/;//身份证
	$("#idcard").text($("#idcard").text().replace(idcardreg, "$1**********$2"));
	
	
	var mobilereg = /^(\d{3})\d+(\d{4})$/;//手机号
	$("#alipay").text($("#alipay").text().replace(mobilereg, "$1****$2"));
	$("#mobile").text($("#mobile").text().replace(mobilereg, "$1****$2"));
	
	var cardreg = /^(\d{6})\d+(\d{8})$/;//银行卡
	$("#card").text($("#card").text().replace(cardreg, "$1*****$2"));

	//保存信息
	$("#savebasic").click(function(){
		
		jsonAjax.post({
			url:ctx+'/memberInfo/basic/edit',
			data:{
				"realName":$("#realName").val(),
				"areaName":$("#areaName").val(),
				"memberSign":$("#memberSign").val()
			},
			success:function(data){
				$("#dataList").html(data);
				console.info(data);
			}
		});
		
	});
	
	
	/**jsonAjax.post({
		url:ctx+'/task/task_list',
		data:{"type":"recomd_me"
		},
		success:function(data){
			$("#dataList").html(data);
			$("#recomd_me").text($("#recommendSize").val());
			$("#under_way").text($("#fragSize").val());
			$("#under_finish").text($("#claimSize").val());
			console.info(data);
		}
	});
	
	*/
		
	
	
});