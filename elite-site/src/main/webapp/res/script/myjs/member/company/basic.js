require([ "jquery", "ajax", "jsonAjax","bootstrap-datepicker","customhead","city","commons"], function($) {
	
	initCustomHead();
	
	var kucity=$('#areaName').kuCity();
	$("#cityIcon").click(function(){
		kucity.focus();
	});
	/*$('#birthday').datepicker({
		orientation:"right",
		startDate:new Date(1950,1,1),
		endDate:new Date(),
		startView: 'decade',
        autoclose: true
    });
	$('#birthday_img').datepicker({
		orientation:"right",
		startDate:new Date(1950,1,1),
		endDate:new Date(),
		startView: 'decade',
        autoclose: true
    }).on("changeDate",function(e){
    	var time=e.date;
    	$("#birthday").val(time.Format("yyyy-MM-dd"));
    });*/
	$("#saveBtn").click(function(){
		$("#personFrom  input").removeClass("error-sign");
		//var birthday=$.trim($("#birthday").val());
		var areaName=$.trim($("#areaName").val());
		var email=$.trim($("#email").val());
		var memberSign=$.trim($("#memberSign").val());
		/*if(birthday==""){
			tips("birthday","请选择您的出生年月");
			return false;
		}else */
		if(areaName==""){
			$(this).siblings(".error_div").show();
			tips("areaName","请选择您的常驻城市");
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
			return false;
		}else if(email==""){
			$(this).siblings(".error_div").show();
			tips("email","请填写您的常用邮箱");
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
			return false;
		}else if(!validateEmail(email)){
			$(this).siblings(".error_div").show();
			tips("email","请输入正确的邮箱格式");
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
			return false;
		}else if(memberSign==""){
			$(this).siblings(".error_div").show();
			tips("memberSign","请填写您的头衔/职位");
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
			return false;
		}else if(!validateIllegalChar(memberSign)){
			$(this).siblings(".error_div").show();
			tips("memberSign","头衔/职位不能包含非法字符");
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
			return false;
		}
		jsonAjax.post({
			url:ctx+'/member/basic/save',
			data:$("#personFrom").serialize(),
			success:function(data){
				if(data.result=="SUCCESS"){
					window.location.href=ctx+'/member/company?'+jsonAjax.random();
				}else{
					if(data.code=="3010"){
						$(this).siblings(".error_div").show();
						$("body").bind("mousedown",function(){
							$(".error_div").hide();
							$("body").unbind("mousedown");
						});
						tips("areaName",data.msg);
					}else if(data.code=="3002"){
						$(this).siblings(".error_div").show();
						$("body").bind("mousedown",function(){
							$(".error_div").hide();
							$("body").unbind("mousedown");
						});
						tips("email",data.msg);
					}
				}
			}
		});
	});
	$("#skip").click(function(){
		window.location.href=ctx+'/project/publish?'+jsonAjax.random();
	});
});