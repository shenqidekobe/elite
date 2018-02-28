require([ "jquery", "ajax", "jsonAjax","bootstrap-datepicker","customhead","city","commons"], function($) {
	
	initCustomHead();
	
	var kucity=$('#areaName').kuCity();
	$("#cityIcon").click(function(){
		kucity.focus();
	});
	$('#birthday').datepicker({
		orientation:"right",
		startDate:new Date(1950,1,1),
		endDate:new Date(),
		startView: 'decade',
        autoclose: true
    });
	$('.date-icon').datepicker({
		orientation:"right",
		startDate:new Date(1950,1,1),
		endDate:new Date(),
		startView: 'decade',
        autoclose: true
    }).on("changeDate",function(e){
    	var time=e.date;
    	$("#birthday").val(time.Format("yyyy-MM-dd"));
    });
	//头像回显
	if($("#head").val()!=""){
		var strs= $("#head").val().split(".");
		var src = $("#headImg").attr("src");
		$("#headImg").attr("src",uploadPath+"/preview/img/head/"+src+"/"+strs[1]+"/"+$("#head").val());
		
	}
	//性别切换
	$(".sex button").click(function(){
		$(".sex button").removeClass("active-btn");
		$(this).addClass("active-btn");
		$("#sex").val($(this).attr("data"));
	});
	//性别回显
	if($("#sex").val()!=""){
		$(".sex button").each(function(){
			if($(this).attr("data") == $("#sex").val()){
				$(this).addClass("active-btn");
			}else{
				$(this).removeClass("active-btn");
			}
			
		});
	}
	$("#saveBtn").click(function(){
		var birthday=$.trim($("#birthday").val());
		var areaName=$.trim($("#areaName").val());
		var email=$.trim($("#email").val());
		var memberSign=$.trim($("#memberSign").val());
		if(birthday==""){
			tips("birthday","请选择您的出生年月");
			return false;
		}else if(areaName==""){
			tips("areaName","请选择您的常驻城市");
			return false;
		}else if(email==""){
			tips("email","请填写您的常用邮箱");
			return false;
		}else if(!validateEmail(email)){
			tips("email","请输入正确的邮箱格式");
			return false;
		}else if(memberSign==""){
			tips("memberSign","请填写您的头衔/职位");
			return false;
		}else if(!validateIllegalChar(memberSign)){
			tips("memberSign","头衔/职位不能包含非法字符");
			return false;
		}
		
		jsonAjax.post({
			url:ctx+'/member/basic/save',
			data:$("#personFrom").serialize(),
			success:function(data){
				if(data.result=="SUCCESS"){
					window.location.href=ctx+'/member/elite?'+jsonAjax.random();
				}else {
					if(data.code=="3010"){
						tips("areaName",data.msg);
					}else if(data.code=="3002"){
						tips("email",data.msg);
					}
				}
			}
		});
	});
	$("#skip").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#prevBtn").click(function(){
		window.location.href=ctx+'/member/current?'+jsonAjax.random();
	});
});