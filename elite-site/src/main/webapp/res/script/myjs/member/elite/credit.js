require([ "jquery", "ajax", "jsonAjax","webupload","commons"], function($) {
	
	$("#bankCard").bind("keyup blur",function(){
		$(this).val($(this).val().replace(/\D/g,''));
	}); 
	
	$("#otherOper").click(function(){
		$("#otherDiv").toggle();
	});
	

	upload.uploadImg($("#cardJustFile"),"credit",function(data){
		$("#cardJustImg").attr("src",data.url);
		$("#cardJustImg").css({width:'230px',height:'160px'});
		$("#cardJust").val(data.attaId);
	});
	upload.uploadImg($("#cardReverseFile"),"credit",function(data){
		$("#cardReverseImg").attr("src",data.url);
		$("#cardReverseImg").css({width:'230px',height:'160px'});
		$("#cardReverse").val(data.attaId);
	});
	upload.uploadImg($("#businessCertFile"),"credit",function(data){
		$("#businessCertImg").attr("src",data.url);
		$("#businessCertImg").css({width:'230px',height:'160px'});
		$("#businessCert").val(data.attaId);
	});
	upload.uploadImg($("#jobCertFile"),"credit",function(data){
		$("#jobCertImg").attr("src",data.url);
		$("#jobCertImg").css({width:'230px',height:'160px'});
		$("#jobCert").val(data.attaId);
	});
	upload.uploadImg($("#visitingCertFile"),"credit",function(data){
		$("#visitingCertImg").attr("src",data.url);
		$("#visitingCertImg").css({width:'230px',height:'160px'});
		$("#visitingCert").val(data.attaId);
	});

	$("#saveBtn").click(function() {
		var realName=$.trim($("#realName").val());
		var idCard=$.trim($("#idCard").val());
		var cardJust=$.trim($("#cardJust").val());
		var cardReverse=$.trim($("#cardReverse").val());
		//var businessCert=$.trim($("#businessCert").val());
		//var jobCert=$.trim($("#jobCert").val());
		//var visitingCert=$.trim($("#visitingCert").val());
		/*var alipayAccount=$.trim($("#alipayAccount").val());
		var bankCard=$.trim($("#bankCard").val());*/
		if(realName==""){
			tips("realName","请输入您的真实姓名");
			return false;
		}else if(idCard==""){
			tips("idCard","请输入您的身份证号码");
			return false;
		}else if(!validateCard(trimMiddle(idCard))){
			tips("idCard","请输入正确的身份证号码");
			return false;
		}else if(cardJust==""){
			tips("cardJust","请上传您的身份证正面照");
			return false;
		}else if(cardReverse==""){
			tips("cardReverse","请上传您的身份证反面照");
			return false;
		}
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
							$(".backButton").click();
						}
					}, 1000);
					$(".backButton").click(function(){
						window.location.href=ctx+'/member/index?'+jsonAjax.random();
					});
				}else{
					tips("idCard",data.msg);
				}
			}
		});
	});
	$("#skip").click(function() {
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#prevBtn").click(function(){
		window.location.href=ctx+'/member/elite?'+jsonAjax.random();
	});
});