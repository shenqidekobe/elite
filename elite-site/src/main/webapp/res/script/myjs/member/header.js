require([ "jquery", "ajax", "jsonAjax", "md5"],function($) {

	$("#projectLi").hover(function(){
        $(this).find(".down").stop().slideDown(300);
    },function(){
        $(this).find(".down").stop().slideUp(300);
    });
	$("#eliteLi").hover(function(){
        $(this).find(".down").stop().slideDown(300);
    },function(){
        $(this).find(".down").stop().slideUp(300);
    });
	
	$("#message_btn").click(function(){
		window.location.href=ctx+'/member/message?'+jsonAjax.random();
	});
	
	$(".head-portrait").unbind("click");
	$(".head-portrait").click(function(){
		var data=$(this).attr("data");
		if(data=="company"||data=="cto"){
			sessionStorage.setItem('rsp_intent_isd','projects');
		}else if(data=="elite"){
			sessionStorage.setItem('rsp_intent_isd','tasks');
		}
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	
	$("#userHeader").hover(function(){
        $(this).find(".downbox").stop().slideDown(0);
    },function(e){
    	//图标下方位置的偏移量内不处理
    	var y=e.offsetY;
    	if(y>20&&y<55){
    		return false;
    	}
        $(this).find(".downbox").stop().slideUp(100);
    });
	$("body").click(function(){
        $(this).find(".downbox").stop().slideUp(100);
    });
	
	function showMenu(){
		/*$(".downbox").toggle();
		
		$("body").bind("mousedown", function(event) {
			$(".downbox").fadeOut("fast");
			$("body").unbind("mousedown");
		});*/
	}
	
	
	//项目方下拉切换
	$("#downUlCompany li").hover(function(){
		var data=$(this).attr("data")
		$(this).addClass("active_bg").siblings().removeClass("active_bg");
		$(this).siblings().find(".liText").removeClass("active_color");
		$(this).find(".liText").addClass("active_color");
		if(data=="main"){
			$(this).find(".liIcon").removeClass("main_noselect").addClass("main_select");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
		}
		else if(data=="set"){
			$(this).find(".liIcon").removeClass("set_noselect").addClass("set_select");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
			$("#li_mpro").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
		}
		else if(data=="switch"){
			$(this).find(".liIcon").removeClass("switch_noselect").addClass("switch_select");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_mpro").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="exit"){
			$(this).find(".liIcon").removeClass("exit_noselect").addClass("exit_select");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_mpro").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
		}
	});	
	
	
	
	//cto下拉切换
	$("#downUlCto li").hover(function(){
		var data=$(this).attr("data")
		$(this).addClass("active_bg").siblings().removeClass("active_bg");
		$(this).siblings().find(".liText").removeClass("active_color");
		$(this).find(".liText").addClass("active_color");
		if(data=="main"){
			$(this).find(".liIcon").removeClass("main_noselect").addClass("main_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="code"){
			$(this).find(".liIcon").removeClass("code_noselect").addClass("code_select");
			$("#li_mpro").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="set"){
			$(this).find(".liIcon").removeClass("set_noselect").addClass("set_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_mpro").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="switch"){
			$(this).find(".liIcon").removeClass("switch_noselect").addClass("switch_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_mpro").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="exit"){
			$(this).find(".liIcon").removeClass("exit_noselect").addClass("exit_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_mpro").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
		};	
	});	
	
	
	//精英头部下拉
	$("#downUlElite li").hover(function(){
		var data=$(this).attr("data");
		$(this).addClass("active_bg").siblings().removeClass("active_bg");
		$(this).siblings().find(".liText").removeClass("active_color");
		$(this).find(".liText").addClass("active_color");
		if(data=="main"){
			$(this).find(".liIcon").removeClass("main_noselect").addClass("main_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="code"){
			$(this).find(".liIcon").removeClass("code_noselect").addClass("code_select");
			$("#li_mtask").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="set"){
			$(this).find(".liIcon").removeClass("set_noselect").addClass("set_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_mtask").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="switch"){
			$(this).find(".liIcon").removeClass("switch_noselect").addClass("switch_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_mtask").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
		}
		else if(data=="exit"){
			$(this).find(".liIcon").removeClass("exit_noselect").addClass("exit_select");
			$("#li_code").find(".liIcon").removeClass("code_select").addClass("code_noselect");
			$("#li_mtask").find(".liIcon").removeClass("main_select").addClass("main_noselect");
			$("#li_set").find(".liIcon").removeClass("set_select").addClass("set_noselect");
			$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
		};
	});	
	
	
	
	
	
	
	
	//项目推荐方页头部下拉
		$("#downUlPartnerCompany li").hover(function(){
			var data=$(this).attr("data");
			$(this).addClass("active_bg").siblings().removeClass("active_bg");
			$(this).siblings().find(".liText").removeClass("active_color");
			$(this).find(".liText").addClass("active_color");
			if(data=="main"){
				$(this).find(".liIcon").removeClass("main_noselect").addClass("main_select");
				$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
				$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
				$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
				$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
			}
			else if(data=="invite"){
				$(this).find(".liIcon").removeClass("invite_noselect").addClass("invite_select");
				$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
				$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
				$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
				$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
			}
			else if(data=="earnings"){
				$(this).find(".liIcon").removeClass("earnings_noselect").addClass("earnings_select");
				$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
				$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
				$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
				$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
			}
			else if(data=="switch"){
				$(this).find(".liIcon").removeClass("switch_noselect").addClass("switch_select");
				$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
				$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
				$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
				$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
			}
			else if(data=="exit"){
				$(this).find(".liIcon").removeClass("exit_noselect").addClass("exit_select");
				$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
				$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
				$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
				$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
			};
		});	
		
		

		//人才渠道头部下拉
			$("#downUlPartnerElite li").hover(function(){
				var data=$(this).attr("data");
				$(this).addClass("active_bg").siblings().removeClass("active_bg");
				$(this).siblings().find(".liText").removeClass("active_color");
				$(this).find(".liText").addClass("active_color");
				if(data=="main"){
					$(this).find(".liIcon").removeClass("main_noselect").addClass("main_select");
					$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
					$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
					$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
					$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
				}
				else if(data=="invite"){
					$(this).find(".liIcon").removeClass("invite_noselect").addClass("invite_select");
					$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
					$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
					$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
					$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
				}
				else if(data=="earnings"){
					$(this).find(".liIcon").removeClass("earnings_noselect").addClass("earnings_select");
					$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
					$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
					$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
					$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
				}
				else if(data=="switch"){
					$(this).find(".liIcon").removeClass("switch_noselect").addClass("switch_select");
					$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
					$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
					$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
					$("#li_logout").find(".liIcon").removeClass("exit_select").addClass("exit_noselect");
				}
				else if(data=="exit"){
					$(this).find(".liIcon").removeClass("exit_noselect").addClass("exit_select");
					$("#li_inreg").find(".liIcon").removeClass("invite_select").addClass("invite_noselect");
					$("#li_champion").find(".liIcon").removeClass("main_select").addClass("main_noselect");
					$("#li_jsele").find(".liIcon").removeClass("earnings_select").addClass("earnings_noselect");
					$("#li_switch").find(".liIcon").removeClass("switch_select").addClass("switch_noselect");
				};
			});	
			
			
			
		
		
	
	
	$("#li_mpro").click(function(){
		sessionStorage.setItem('rsp_intent_isd','projects');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_mtask").click(function(){
		sessionStorage.setItem('rsp_intent_isd','tasks');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_code").click(function(){
		sessionStorage.setItem('rsp_intent_isd','code');
		window.location.href=ctx+'/invite?'+jsonAjax.random();
	});
	$("#li_set").click(function(){
		sessionStorage.setItem('rsp_intent_isd','settings');
		window.location.href=ctx+'/member/bank/settings?'+jsonAjax.random();
	});
	$("#li_mattention").click(function(){
		sessionStorage.setItem('rsp_intent_isd','attention');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_mteam").click(function(){
		sessionStorage.setItem('rsp_intent_isd','team');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_mattent").click(function(){
		sessionStorage.setItem('rsp_intent_isd','attention');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_jsele").click(function(){
		sessionStorage.setItem('rsp_intent_isd','settlement');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_minfo").click(function(){
		var currentType=sessionStorage.currentType;
		if("company"==currentType||"cto"==currentType||"elite"==currentType){
			window.location.href=ctx+'/member/personage?'+jsonAjax.random();
		}else{
			sessionStorage.setItem('rsp_intent_isd','info');
			window.location.href=ctx+'/member/index?'+jsonAjax.random();
		}
	});
	
	$("#li_champion").click(function(){
		sessionStorage.setItem('rsp_intent_isd','champions');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_inreg").click(function(){
		sessionStorage.setItem('rsp_intent_isd','inviteRegister');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_manager").click(function(){
		sessionStorage.setItem('rsp_intent_isd','manager');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_channel").click(function(){
		sessionStorage.setItem('rsp_intent_isd','channel');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#li_switch").click(function(){
		jsonAjax.post({
			url:ctx+'/member/switch/identity',
			success:function(data){
				$(".headWindow").hide();
				$(".switchWindow").css({"position":"fixed","z-index":"8888","min-height":"1000px"});
				$(".switchWindow").html(data);
				$(".switchWindow").show();
				$("#closeSwitchIdentity").click(function(){
					$(".switchWindow").hide();
				});
				$("#switchImgUl li").click(function(){
					var currentType=$("#switchImgUl").attr("currentType");
					var type=$(this).attr("data");
					if(currentType==type){
						tostHint("提示","当前身份无需切换");
						return false;
					}
					/*var img=$(this).find(".switchBox img");
					img.attr("src",ctx+img.attr("src").split(".png")[0]+"_selected.png");*/
					switchIdentity(type);
					$("#closeSwitchIdentity").click();
				});
			}
		});
	});
	$("#li_logout").click(function(){
		window.location.href=ctx+'/logout';
	});

	function switchIdentity(type){
		window.location.href=ctx+'/member/'+type+'/switch';
	}
});