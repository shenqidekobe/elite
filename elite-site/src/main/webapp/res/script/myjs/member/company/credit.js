require([ "jquery", "ajax", "jsonAjax","webupload","commons"], function($) {

	/*$("#idCard").keyup(function(e) {
		//禁止回退键
		var ev = e || window.event;
		if(ev.keyCode==8){
			return;
		}
		var val = $("#idCard").val();
		if (val.length == 6) {
			$("#idCard").val(val + " ");
		} else if (val.length == 15) {
			$("#idCard").val(val + " ");
		}
	});*/
	upload.uploadImg($("#cardJustFile"),"credit",function(data){
		$("#cardJustImg").attr("src",data.url);
		$("#cardJustImg").css({width:'230px',height:'144px'});
		$("#cardJust").val(data.attaId);
	});
	upload.uploadImg($("#cardReverseFile"),"credit",function(data){
		$("#cardReverseImg").attr("src",data.url);
		$("#cardReverseImg").css({width:'230px',height:'144px'});
		$("#cardReverse").val(data.attaId);
	});
	upload.uploadImg( $("#businessCertFile"),"credit",function(data){
		$("#businessCertImg").attr("src",data.url);
		$("#businessCertImg").css({width:'230px',height:'144px'});
		$("#businessCert").val(data.attaId);
	});
	upload.uploadImg($("#jobCertFile"),"credit",function(data){
		$("#jobCertImg").attr("src",data.url);
		$("#jobCertImg").css({width:'230px',height:'144px'});
		$("#jobCert").val(data.attaId);
	});
	upload.uploadImg($("#visitingCertFile"),"credit",function(data){
		$("#visitingCertImg").attr("src",data.url);
		$("#visitingCertImg").css({width:'230px',height:'144px'});
		$("#visitingCert").val(data.attaId);
	});

	$("#saveBtn").click(function() {
		var realName=$.trim($("#realName").val());
		var idCard=$.trim($("#idCard").val());
		var cardJust=$.trim($("#cardJust").val());
		var cardReverse=$.trim($("#cardReverse").val());
		var businessCert=$.trim($("#businessCert").val());
		var jobCert=$.trim($("#jobCert").val());
		var visitingCert=$.trim($("#visitingCert").val());
		/*var alipayAccount=$.trim($("#alipayAccount").val());
		var bankCard=$.trim($("#bankCard").val());*/
		if(realName==""){
			tips("realName","请输入您的真实姓名");
			 errorShow()
			return false;
		}else if(!validateIllegalChar(realName)){
			tips("realName","真实姓名不能包含非法字符");
			 errorShow()
			return false;
		}else if(idCard==""){
			tips("idCard","请输入您的身份证号码");
			 errorShow()
			return false;
		}else if(!validateCard(trimMiddle(idCard))){
			tips("idCard","请输入正确的身份证号码");
			 errorShow()
			return false;
		}else if(cardJust==""){
			tips("cardJust","请上传您的身份证正面照");
			 errorShow()
			return false;
		}else if(cardReverse==""){
			tips("cardReverse","请上传您的身份证反面照");
			 errorShow()
			return false;
		}
		
		function errorShow(){
			$(".error_div").show();
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
		}
		
		
		/*else if(businessCert==""){
			tips("businessCert","请上传您的营业执照");
			return false;
		}else if(jobCert==""){
			tips("jobCert","请上传您的工作证正面");
			return false;
		}else if(visitingCert==""){
			tips("visitingCert","请上传您的名片正面照");
			return false;
		}else if(alipayAccount==""){
			tips("alipayAccount","请输入您的支付宝帐号");
			return false;
		}else if(bankCard==""){
			tips("bankCard","请输入您常用的银行卡号");
			return false;
		}else if(bankCard!=""&&!validateBankNum(bankCard)){
			tips("bankCard","请输入正确的银行卡号");
			return false;
		}*/
		$("#idCard").val(trimMiddle(idCard))
		jsonAjax.post({
			url : ctx + '/member/credit/save',
			data : $("#companyFrom").serialize(),
			success : function(data) {
				if (data.result == "SUCCESS") {
					$(".window-confirm").show();
					var i=6;
					$("#down_time").text(i+"s");
					setInterval(function(){
						i--;
						$("#down_time").text(i+"s");
						if(i==0){
							window.location.href=ctx+'/member/index?'+jsonAjax.random();
						}
					}, 1000);
					$(".backButton").click(function(){
						window.location.href = ctx + '/project/publish?'+jsonAjax.random();
					});
				}else{
					tips("idCard",data.msg);
				}
			}
		});
	});
	$("#prevBtn").click(function(){
		window.location.href = ctx + '/member/company?'+jsonAjax.random();
	});
	$("#skip").click(function() {
		window.location.href = ctx + '/project/publish?'+jsonAjax.random();
	});
});