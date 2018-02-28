require([ "jquery", "ajax", "jsonAjax","bootstrap-datepicker","webupload","commons",], function($) {
	 
	 //接活不接活
	 $("#switch_btn").click(function(){
		 	var flag=true;
            if($(this).hasClass("switch_yes")){
                $(".btn_ball").animate({"left":"29px"});
                $(this).removeClass("switch_yes").addClass("switch_no");
                $(".switch_text").text("接活");
                $(".switch_text").css({"color":"#fea600"});
            }else {
                $(".btn_ball").animate({"left":"1px"});
                $(this).removeClass("switch_no").addClass("switch_yes");
                $(".switch_text").text("休息");
                $(".switch_text").css({"color":"#9b9b9b"});
                flag=false;
            }
            jsonAjax.post({
    			url:ctx+'/project/c/changeWork',
    			data:{workFlag:flag},
    			success:function(data){
    				if($(this).attr("data")=='true'){
    					$(this).attr("data",false)
    				}else{
    					$(this).attr("data",true)
    				}
    			}
    		});
        });
	 
	 //申请审核
	 $("#applyAduit").click(function(){
		 jsonAjax.post({
 			url:ctx+'/project/c/applyAudit',
 			success:function(data){
 				if (data.result == "SUCCESS") {
 					tostConfirm("确认申请审核吗?三天内仅只有一次申请机会哦",function(){
 						jsonAjax.post({
 				 			url:ctx+'/project/c/audit',
 				 			success:function(data){
 				 				if (data.result != "SUCCESS") {
 				 					tostHint("申请失败","");
 				 				}else{
 				 					$("#statuscontent").text("审核中");
 				 					$("#aduitbutton").html("");
 				 				}
 				 			}
 				 		});
 					});
 				}else{
 					tostHint("申请失败",data.msg);
 				}
 			}
 		});
     });
	
	//左侧菜单点击事件
	$(".left-menu li").click(function(){
		$(".left-menu li").removeClass("active_color");
		$(this).addClass("active_color");
		$(".left-menu li").find("span").removeClass("active-menu");
		$(this).find("span").addClass("active-menu");
		var type=$(this).attr("data");
		if(type=="myProject"){
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_select.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/team_noselect.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(5)").find("img").attr("src",ctx+"/res/images/elite/c.png");
			$(".left-menu li:nth-child(6)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadProjectList();
			$("#crumbsText").text("我的项目");
		}else if(type=="myTask"){
			
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/a_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_noselect.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/team_noselect.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(5)").find("img").attr("src",ctx+"/res/images/elite/c.png");
			$(".left-menu li:nth-child(6)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadTaskList();
			$("#crumbsText").text("我认领的任务");
		}else if(type=="myTeam"){
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/team_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_noselect.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(5)").find("img").attr("src",ctx+"/res/images/elite/c.png");
			$(".left-menu li:nth-child(6)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadTeam();
			$("#crumbsText").text("我的团队");
		}else if(type=="myAttention"){
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/b_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_noselect.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/team_noselect.png");
			$(".left-menu li:nth-child(5)").find("img").attr("src",ctx+"/res/images/elite/c.png");
			$(".left-menu li:nth-child(6)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadAttentionList();
			$("#crumbsText").text("我的关注");
		}else if(type=="settlement"){
			$(".left-menu li:nth-child(5)").find("img").attr("src",ctx+"/res/images/elite/c_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_noselect.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/team_noselect.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(6)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadSettlement();
			$("#crumbsText").text("结算管理");
		}else if(type=="myInfo"){
			$(".left-menu li:nth-child(6)").find("img").attr("src",ctx+"/res/images/elite/d_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_noselect.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/team_noselect.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(5)").find("img").attr("src",ctx+"/res/images/elite/c.png");
			loadInfo();
		}
	});
	
	//我的资料
	function loadInfo(){
		window.location.href=ctx+'/member/personage?'+jsonAjax.random();
	}
	
	//结算管理
	function loadSettlement(){
		jsonAjax.post({
			url:ctx+'/member/settlement',
			success:function(html){
				$("#projectList").html(html);
				$("a[id=withdraw]").click(withdraw);
				//tab选项点击事件
				$("#allType").click(function(){
					$(".title-record li").css("color","#9B9B9B");
					$(this).css("color","#2CB7C9");
					$("#type").val('');
					loadSettlementData();
				});
				$(".title-record li").click(function(){
					$("#allType,.title-record li").css("color","#9B9B9B");
					$(this).css("color","#2CB7C9");
					var data=$(this).attr("data");
					$("#type").val(data);
					loadSettlementData();
				});
				//选择项目点击事件
				$("#projectName,.select-btn").click(function(){
					$("#procUl").toggle();
					$("#procUl li").unbind("click");
					$("#procUl li").click(function(){
						var data=$(this).attr("data");
						$("#projectId").val(data);
						$("#projectName").val($(this).text());
						$("#procUl").hide();
						loadSettlementData();
					});
				});
				loadSettlementData();
			}
		});
	}
	function loadSettlementData(){
		jsonAjax.post({
			url:ctx+'/member/settlement/listData',
			data:$("#settlementListForm").serialize(),
			success:function(html){
				$("#dataSecondList").html(html);
				$("a[id=detail]").click(viewSettlement);
				//分页组件js
				pagination(loadSettlementData);
			}
		});
	}
	
	//查看结算列表的详情
	function viewSettlement(){
		var id=$(this).attr("data");
		console.info("结算详情ID："+id);
	}
	
	//提现申请
	function withdraw(){
		//验证其余额是否达到提现标准
		var balance=$("#balance").val();
		var isCard=$("#card").val(); 
		if(parseFloat(balance)<100){
			tostHint("余额未达到提现标准","余额还不够哦，目前还不能提前，点击确认可返回查看");
		}else if(!isCard){
			tostHint("提现失败","身份证未认证通过，不能提现");
		}else{
			window.location.href=ctx+'/member/withdraw?'+jsonAjax.random();
		}
	}

	
	//我的项目
	function loadProjectList(){
		jsonAjax.post({
			url:ctx+'/project/c/count',
			success:function(data){
				$("#projectList").html(data);
				//项目列表状态事件
				$(".tabs .col-xs-4").click(function(){
					$(".tabs div").removeClass("active-tab");
					$(this).addClass("active-tab");
					var index=parseInt($(this).attr("index"));
					var left=260*index;
					$(".active-line").css({'left':left+'px'});
					var status=$(this).attr("data");
					$("#projectStatus").val(status);
					$("#pager_pageth").val(1);
					loadProjectListData();
				});
				$(".tabs div:first").click();
			}
		});
	}
	
	
	function loadProjectListData(){
		var type=$("#projectStatus").val();
		$("#type").val(type);
		jsonAjax.post({
			url:ctx+'/project/c/listData',
			data:$("#projectForm").serialize(),
			success:function(html){
				$("#dataList").html(html);
				$("#recommendtab").text($("#recommendCount").val());
				$("#tenderintab").text($("#tenderInCount").val());
				$("#tendertab").text($("#tenderCount").val());
				//分页组件js
				$(".pagination #prev_pager").click(function(){
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 <= 1) {
						return;
					}
					$("#pager_pageth").val(currentPageth - 1);
					loadProjectListData();
				});
				$(".pagination #jump_pager").click(function(){
					var pageth = $(this).attr("data");
					$("#pager_pageth").val(pageth - 0);
					loadProjectListData();
				});
				$(".pagination #next_pager").click(function(){
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
						return;
					}
					console.info($("#pager_pageth").val());
					$("#pager_pageth").val(currentPageth - 0 + 1);
					loadProjectListData();
				});
				
				$("span[id^='deadlineSpan_']").each(function(){
					var deadlineTime=$(this).attr("data");
					addTimer($(this).attr("id"),deadlineTime); 
				})
			}
		});
	}
	
	//处理回调
	var rcp=sessionStorage.getItem('rsp_intent_isd');
	if(rcp!=null&&rcp!=""){
		if(rcp=='tasks'){
			$(".left-menu li[data=myTask]").click();
		}else if(rcp=='team'){
			$(".left-menu li[data=myTeam]").click();
		}else if(rcp=='attention'){
			$(".left-menu li[data=myAttention]").click();
		}else if(rcp=='settlement'){
			$(".left-menu li[data=settlement]").click();
		}else if(rcp=='info'){
			$(".left-menu li[data=myInfo]").click();
		}else{
			$(".left-menu li[data=myProject]").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	}else{
		$(".left-menu li:first").click();
	}

	
	
	//去竞标
	$(document).on("click",'#totender',function(){
		if($("#validStatus").val()!='normal'){
			tostHint("提交失败","您还未审核通未不能执行此操作");
			return false;
		}
		var id = $(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/tender?'+jsonAjax.random();
	});
	
	//查看意向书
	$(document).on("click",'#confirmatta',function(){
		var id = $(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/define?'+jsonAjax.random();
	});
	
	
	//中标阶段查看项目详情
	$(document).on("click",'#projectInfo,#viewReason',function(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/index?'+jsonAjax.random();
	});
	
	
	//分配任务
	$(document).on("click",'#giveTask',function(){
		var id=$(this).attr("data");
		sessionStorage.setItem('rsp_intent_isd','task');
		window.location.href=ctx+'/project/c/'+id+'/index?'+jsonAjax.random();
	});
	
	//查看任务详情
	$(document).on("click",'#taskDetail',function(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/task/'+id+'/index?'+jsonAjax.random();
	});
	
	
	//查看和提交产物
	$(document).on("click",'#viewMaterial',function(){
		sessionStorage.setItem('rsp_intent_isd','material');
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/index?'+jsonAjax.random();
	});
});