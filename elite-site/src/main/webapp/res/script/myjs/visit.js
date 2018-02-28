$(function(){
	$("#startBtn").click(startVisit);
	$("#visitCode").focus(function(){
		$(".errormsg").hide();
	});
	$('body').keydown(function(event) {
		if (event.keyCode == 13){
			startVisit();
			return false;
		}
	});
	var sessionId=$("#sessionId").val();
	var autoLogin=typeof(localStorage.autoEliteLogin)=='undefined'?'false':localStorage.autoEliteLogin;
	if(sessionId==""&&autoLogin=='true'){
		localStorage.autoEliteLogin=false;
		localStorage.elitePassword='';
	}
});
function startVisit(){
	//var visitName=$.trim($("#visitName").val());
	//var visitPhone=$.trim($("#visitPhone").val());
	var visitCode=$.trim($("#visitCode").val());
	if(visitCode==""){
		return false;
	}
	jsonAjax.post({
		url:ctx+'/visit/start',
		data:$("#visitForm").serialize(),
		success:function(data){
			if(data.result=="SUCCESS"){
				window.location.href=ctx+'/?'+jsonAjax.random();
			}else{
				$(".errormsg").show();
				$(".errormsg").text('火星上都未找到此邀请码...');
				$("#visitCode").val('');
				//$(".errormsg").text('sorry，您的邀请码未找到，请联系BOSS');
			}
		}
	});
}
function localVisit(visitName){
	localStorage.visitName=visitName;
	//localStorage.visitPhone=visitPhone;
	//localStorage.visitCode=visitCode;
}