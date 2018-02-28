require(
		[ "jquery", "ajax", "jsonAjax", "md5", "customhead", "city",
				"bootstrap-datepicker", "commons" ],
		function($) {
			
			//上传头像
			initCustomHead();
			
			var kucity = $('#areaName').kuCity();
			$("#cityIcon").click(function() {
				kucity.focus();
			});
            //type选择显示
			$("#selectTypeId,#selectTypeIconId").click(function(){
				$("#selectTypeUlId").fadeIn();
				$("body").bind("mousedown", function(event) {
					$("#selectTypeUlId").fadeOut("fast");
					$("body").unbind("mousedown");
				});
			})
			//type选择
			$("#selectTypeUlId li").click(function(){
				var title=$(this).html();
				var data=$(this).attr("data");
				$("#selectTypeId").val(title)
				$("#selectTypeValueId").val(data)
				$("#selectTypeUlId").fadeOut("fast");
			})
					   //性别选择
		      $("#selectSexPan li").click(function(){
		    	  var data=$(this).attr("data");
		    	  if(data=="M"){
		    		  $("#manselectColorId").attr("class","select_color");
		    		  $("#womanselectColorId").attr("class","noselect_color");
		    		  $("#manPigId").attr("src",ctx+"/res/images/partner/man_select.png");
		    		  $("#womanPigId").attr("src",ctx+"/res/images/partner/woman_noselect.png");
		    	  }
		    	  else if(data=="F"){
		    		  $("#manselectColorId").attr("class","noselect_color");
		    		  $("#womanselectColorId").attr("class","select_color");
		    		  $("#manPigId").attr("src",ctx+"/res/images/partner/man_noselect.png");
		    		  $("#womanPigId").attr("src",ctx+"/res/images/partner/woman_select.png");
		    	  }
		    	  $(this).removeClass("noselect_bg").addClass("select_bg");
		    	  $(this).siblings().removeClass("select_bg").addClass("noselect_bg");
		    	  
		    	  $("#sexId").val(data);
		      })
					// 基本信息提交
			$("#saveBasicBtn").click(function() {
										$(".error_text").text("");
										var email = $("#emailId").val()
										if (email == "") {
											$(".error_div").show();
											$(".error_text").text("请输入邮箱");
											return;
										} else {
											var pattern = /^([\.a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
											if (!pattern.test(email)) {
												$(".error_div").show();
												$(".error_text").text(
														"请输入正确邮箱地址");
												return;
											}
										}
										jsonAjax.post({
													url : ctx+ '/partner/partnerElite/basicInfo/save',
													data : $("#personFrom").serialize(),
													success : function(data) {
														if (data.result == "SUCCESS") {
															window.location.href = ctx
																	+ "/partner/partnerElite/industry/register/view";
														} else {
															$(".error_div").show();
															$(".error_text").text(data.msg);
														}
													}
												});
									})
		});
