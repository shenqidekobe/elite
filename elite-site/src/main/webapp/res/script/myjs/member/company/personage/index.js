require([ "jquery", "ajax", "jsonAjax","md5","bootstrap-datepicker","customhead","city","commons"], function($) {
	
	$(window).scroll(function () {
		var obj=$("#companyInfoDiv");
		var top = $(window).scrollTop() + 200;
	    var left = $(window).scrollLeft() + 320;
	    obj.animate({ "top": top ,"left":left}, 30); //方式一 效果比较理想
	});
	
	//返回个人主页
	$("#myMain").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	})
	
	//加载面包屑和左侧菜单栏事件
	var leftFlag=false;
	var boxNextClass="icon-a";
	$(".ceoInfo-l-a-content li").click(function(){
		var thiss=$(this);
		var data=thiss.attr("data");
		$(".ceoInfo-l-a-content li").removeClass("select");
		thiss.addClass("select");
		
		var boxClass="";
		if(data=="basic"){
			boxClass="icon-a";
			offset("anchor1");
		}else if(data=="company"){
			boxClass="icon-b";
			offset("anchor2");
		}else if(data=="credit"){
			boxClass="icon-e";
			offset("anchor3");
		}
		$("."+boxNextClass).addClass(boxNextClass+"-s");
		$("."+boxNextClass).removeClass(boxNextClass);
		$(".txt-box").removeClass("txt_box_select");
		thiss.find(".icon-box").removeClass(boxClass+"-s");
		thiss.find(".icon-box").addClass(boxClass);
		thiss.find(".txt-box").addClass("txt_box_select");
		boxNextClass=boxClass;
		
		//左侧点击显示
		if(leftFlag){
			loadAllInfo();
		}
		leftFlag=false;
	});
	loadAllInfo();
	
	function loadAllInfo(callback){
		jsonAjax.post({
			url:ctx+'/member/personage/frag',
			success:function(html){
				$("#dataList").html(html);
				initCustomHead();
				//编辑事件绑定
				$("#editBasic").click(function(){
					leftFlag=true;
					$(".editCls").hide();
					loadBasic();
				});
				$("#editCompany").click(function(){
					leftFlag=true;
					$(".editCls").hide();
					loadCompany();
				});
				$("#editCredit").click(function(){
					leftFlag=true;
					$(".editCls").hide();
					loadCredit();
				});
				if(callback){
					callback();
				}
			}
		});
	}
	//加载基本信息
	function loadBasic(){
		jsonAjax.post({
			url:ctx+'/member/personage/basic',
			success:function(html){
				$("#basicBox").hide();
				$("#editBasicBox").html(html);
				var kucity=$('#areaName').kuCity();
				$(".city-icon").click(function(){
					kucity.focus();
				});
				$('#birthday').datepicker({
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
			    });
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
				$(".save").click(function(){
					var nickName=$.trim($("#nickName").val());
					var birthday=$.trim($("#birthday").val());
					var areaName=$.trim($("#areaName").val());
					var email=$.trim($("#email").val());
					var memberSign=$.trim($("#memberSign").val());
					$(".sex button").each(function(){
						if($(this).hasClass("active-btn")){
						   $("#sex").val($(this).attr("data"));
						}
					});
					if(nickName==""){
						tips("nickName","请填写您的昵称");
						return false;
					}else if(birthday==""){
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
						url:ctx+'/member/personage/basic/save',
						data:$("#basicFrom").serialize(),
						success:function(data){
							if(data.result=="SUCCESS"){
								loadAllInfo();
							}else{
								tips("email",data.msg);
							}
						}
					});
				});
				$(".cancel").click(function(){
					loadAllInfo();
				});
			}
		});
	}
	//加载创业信息
	function loadCompany(){
		jsonAjax.post({
			url:ctx+'/member/personage/company',
			success:function(html){
				$("#infoBox").hide();
				$("#editInfoBox").html(html).css({"height":"770px"});
				var fromId='';
				$(".login-title li").click(function(){
					var data=$(this).attr("data");
					$(".login-title li").removeClass("yet");
					$(this).addClass("yet");
					if(data=='Y'){
						$(".not-box").hide();	
						$(".yed-box").show();
						$("input[id=companyed]").val(true);
						fromId='personForm1';
					}else{
						$(".yed-box").hide();
					    $(".not-box").show();
					    $("input[id=companyed]").val(false);
					    fromId='personForm2';
					}
					$(".company_error").hide();
				});
				var companyedFlag=$("#companyedFlag").val();
				var flag=companyedFlag==""?false:true;
				if(companyedFlag){
					$(".login-title li:eq(0)").click();
				}else{
					$(".login-title li:eq(1)").click();
				}
				//UEditor
				UE.delEditor('editor1');
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
				});
				$("button[class=save]").click(function(){
					var companyPosition=$.trim($("#"+fromId+" #companyPosition").val());
					var companyName=$.trim($("#companyName").val());
					var companyScale=$("input[name='companyScale']:checked").val();
					
					var companyIntro = UE.getEditor('editor1').getContentTxt();
					$("#companyIntro").val(UE.getEditor('editor1').getContent());
					var teamIntro = UE.getEditor('editor2').getContentTxt();
					$("#teamIntro").val(UE.getEditor('editor2').getContent());
					
					var teamNum=$("input[name='teamNum']:checked").val();
					
					var companyed= $("input[id=companyed]").val();
					if(companyPosition==""){
						tips("companyPosition","请输入您所担当的职位");
						return false;
					} 
					//非法字符限制
					/*$("#companyPosition").bind("keyup blur",function(){
						var companyPosition = $(this).val();
						$(this).val(companyPosition.replace(/[^\a-\z\A-\Z\u4E00-\u9FA5]/g,''));
					});
					$("#companyName").bind("keyup blur",function(){
						var companyName = $(this).val();
						$(this).val(companyName.replace(/[^\a-\z\A-\Z\u4E00-\u9FA5]/g,''));
					});*/
					if(companyed=='true'||companyed==true){
						if(companyName==""){
							tips("companyName","请输入您的公司名称");
							return false;
						}else if(companyScale==""||companyScale==undefined){
							tips("companyScale","请选择您的公司规模");
							return false;
						}else if(companyIntro==""){
							tips("companyIntro","请简要描述一下您的公司");
							return false;
						}else if(companyIntro.length>2000){
							tips("companyIntro","公司描述请控制在2000字符以内");
							return false;
						}
						$("#teamNum").val('');
						$("#teamIntro").val('');
					}else{
						if(teamNum==""||teamNum==undefined){
							tips("teamNum","请选择您的团队规模");
							return false;
						}else if(teamIntro==""){
							tips("teamIntro","请简要描述一下您的团队");
							return false;
						}else if(teamIntro.length>2000){
							tips("teamIntro","团队描述请控制在2000字符以内");
							return false;
						}
						$("#companyName").val('');
						$("#companyScale").val('');
						$("#companyIntro").val('');
					}
					jsonAjax.post({
						url:ctx+'/member/personage/company/save',
						data:$("#"+fromId).serialize(),
						success:function(data){
							if(data.result=="SUCCESS"){
								loadAllInfo();
							}else{
								tips("companyPosition",data.msg);
							}
						}
					});
				});
				$("button[class=cancel]").click(function(){
					loadAllInfo();
				});
			}
		});
	}
	//加载征信信息
	function loadCredit(){
		jsonAjax.post({
			url:ctx+'/member/personage/credit',
			success:function(html){
				$("#creditBox").hide();
				$("#editCreditBox").html(html);
				
				upload.uploadImg($("#cardJustFile"),"credit",function(data){
					$("#cardJustImg").attr("src",data.url);
					//$("#cardJustImg").css({width:'160px',height:'100px'});
					$("#cardJust").val(data.attaId);
				});
				upload.uploadImg($("#cardReverseFile"),"credit",function(data){
					$("#cardReverseImg").attr("src",data.url);
					//$("#cardReverseImg").css({width:'160px',height:'100px'});
					$("#cardReverse").val(data.attaId);
				});
				upload.uploadImg( $("#businessCertFile"),"credit",function(data){
					$("#businessCertImg").attr("src",data.url);
					//$("#businessCertImg").css({width:'160px',height:'100px'});
					$("#businessCert").val(data.attaId);
				});
				upload.uploadImg($("#jobCertFile"),"credit",function(data){
					$("#jobCertImg").attr("src",data.url);
					//$("#jobCertImg").css({width:'160px',height:'100px'});
					$("#jobCert").val(data.attaId);
				});
				upload.uploadImg($("#visitingCertFile"),"credit",function(data){
					$("#visitingCertImg").attr("src",data.url);
					//$("#visitingCertImg").css({width:'160px',height:'100px'});
					$("#visitingCert").val(data.attaId);
				});
				/*$("#realName").bind("keyup blur",function(e){
					//真实姓名只能为英文和汉字
					var realName = $(this).val();
					$(this).val(realName.replace(/[^\a-\z\A-\Z\u4E00-\u9FA5]/g,''));
				});*/
				$("button[class=cancel]").click(function(){
					loadAllInfo();
				});
				$("button[class=save]").click(function() {
					var realName=$.trim($("#realName").val());
					var idCard=$.trim($("#idCard").val());
					var cardJust=$.trim($("#cardJust").val());
					var cardReverse=$.trim($("#cardReverse").val());
					if(realName==""){
						tips("realName","请输入您的真实姓名");
						return false;
					}else if(!validateIllegalChar(realName)){
						tips("realName","真实姓名不能包含非法字符");
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
							if(data.result=="SUCCESS"){
								loadAllInfo();
							}else{
								tips("idCard",data.msg);
							}
						}
					});
				});
			}
		});
	}
	function offset(id){
		var top=$("#"+id).offset().top;
		$("body,html").animate({
	       scrollTop: top-100
	    }, 1000);
	}
	function tips(target,text){
		$(".error_div").show();
		$(".error_text").text(text);
		var thiss=$("#"+target);
		thiss.focus(function(){
			$(".error_div").hide();
		});
	}

});