require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	var companyed=$("#companyed").val();
	var flag=companyed==""?false:true;
	$("#companyed").val(flag);//默认已注册公司
	$("#have_company").click(
			function() {
				$(".active-line").animate({
					"margin-left" : "0"
				}, 'slow');
				$("#already").show();
				$("#unalready").hide();
				$("#companyed").val(true);
			});
	$("#unhave_company").click(
			function() {
				$(".active-line").animate({
					"margin-left" : "270px"
				}, 'slow');
				$("#unalready").show();
				$("#already").hide();
				$("#companyed").val(false);
			});
	$(".check-custom").click(function(){
		$(".check-custom >img").attr("src",ctx+"/res/images/radio_empty_icon.png");
		var thiss=$(this);
		thiss.find("img").attr("src",ctx+"/res/images/radio_check_icon.png");
		var numKey=thiss.find("span").attr("data");
		if($("#companyed").val()==true||$("#companyed").val()=='true'){
			$("#companyScale").val(numKey);
		}else{
			$("#teamNum").val(numKey);
		}
	});
	//UEditor
	/*UE.delEditor('editor1');
	UE.getEditor('editor1', {
	    toolbars: [
	        ['insertorderedlist', 'insertunorderedlist']
	    ]
	});
	UE.delEditor('editor2');
	UE.getEditor('editor2', {
	    toolbars: [
	        ['insertorderedlist', 'insertunorderedlist']
	    ]
	});*/
	//非法字符限制
	/*$("#companyPosition").bind("keyup blur",function(){
		var companyPosition = $(this).val();
		$(this).val(companyPosition.replace(/[^\a-\z\A-\Z\u4E00-\u9FA5]/g,''));
	});
	$("#companyName").bind("keyup blur",function(){
		var companyName = $(this).val();
		$(this).val(companyName.replace(/[^\a-\z\A-\Z\u4E00-\u9FA5]/g,''));
	});*/
	
	$("#saveBtn").click(function(e){
		var companyPosition=$.trim($("#companyPosition").val());
		var companyName=$.trim($("#companyName").val());
		var companyScale=$.trim($("#companyScale").val());
		var teamNum=$.trim($("#teamNum").val());
		var companyed=$("#companyed").val();
		
		var companyIntro=$.trim($("#companyIntro").val());
		var teamIntro=$.trim($("#teamIntro").val());

		/*var companyIntro = UE.getEditor('editor1').getContentTxt();
		$("#companyIntro").val(UE.getEditor('editor1').getContent());
		var teamIntro = UE.getEditor('editor2').getContentTxt();
		$("#teamIntro").val(UE.getEditor('editor2').getContent());*/
		
		if(companyPosition==""){
			tips("companyPosition","请输入您所担当的职位");
			$(".error_div").show();
			$("body").bind("mousedown",function(){
				$(".error_div").hide();
				$("body").unbind("mousedown");
			});
			return false;
		} 
		if(companyed=='true'||companyed==true){
			if(companyName==""){
				tips("companyName","请输入您的公司名称");
				$(".error_div").show();
				$("body").bind("mousedown",function(){
					$(".error_div").hide();
					$("body").unbind("mousedown");
				});
				return false;
			}else if(companyScale==""){
				tips("companyScale","请选择您的公司规模");
				$(".error_div").show();
				$("body").bind("mousedown",function(){
					$(".error_div").hide();
					$("body").unbind("mousedown");
				});
				return false;
			}else if(companyIntro==""){
				tips("companyIntro","请简要描述一下您的公司");
				$(".error_div").show();
				$("body").bind("mousedown",function(){
					$(".error_div").hide();
					$("body").unbind("mousedown");
				});
				return false;
			}else if(companyIntro.length>2000){
				tips("companyIntro","公司描述请控制在2000字符以内");
				$(".error_div").show();
				$("body").bind("mousedown",function(){
					$(".error_div").hide();
					$("body").unbind("mousedown");
				});
				return false;
			}
			$("#teamNum").val('');
			$("#teamIntro").val('');
		}else{
			if(teamNum==""){
				tips("teamNum","请选择您的团队规模");
				$(".error_div").show();
				$("body").bind("mousedown",function(){
					$(".error_div").hide();
					$("body").unbind("mousedown");
				});
				return false;
			}else if(teamIntro==""){
				tips("teamIntro","请简要描述一下您的团队");
				$(".error_div").show();
				$("body").bind("mousedown",function(){
					$(".error_div").hide();
					$("body").unbind("mousedown");
				});
				return false;
			}else if(teamIntro.length>2000){
				tips("teamIntro","团队描述请控制在2000字符以内");
				$(".error_div").show();
				$("body").bind("mousedown",function(){
					$(".error_div").hide();
					$("body").unbind("mousedown");
				});
				return false;
			}
			$("#companyName").val('');
			$("#companyScale").val('');
			$("#companyIntro").val('');
		}
		jsonAjax.post({
			url:ctx+'/member/company/save',
			data:$("#companyFrom").serialize(),
			success:function(data){
				console.info(data);
				if(data.result=="SUCCESS"){
					window.location.href=ctx+'/member/credit?'+jsonAjax.random();
				}else{
					tips("companyPosition",data.msg);
					$(".error_div").show();
					$("body").bind("mousedown",function(){
						$(".error_div").hide();
						$("body").unbind("mousedown");
					});
				}
			}
		});
	});
	$("#prevBtn").click(function(){
		window.location.href = ctx + '/member/basic?'+jsonAjax.random();
	});
	$("#skip").click(function(){
		window.location.href=ctx+'/project/publish?'+jsonAjax.random();
	});
});