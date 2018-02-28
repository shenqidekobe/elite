require([ "jquery", "ajax", "jsonAjax","bootstrap-datepicker","webupload","commons"], function($) {
	var currentStatus;
    //左侧菜单点击事件
	$(".left-menu li").click(function(){

		$(".left-menu li").removeClass("active_color");
		$(this).addClass("active_color");
		$(".left-menu li").find("span").removeClass("active-menu");
		$(this).find("span").addClass("active-menu");
		var type=$(this).attr("data");
		
		if(type=="myProject"){
			$(this).find("img").attr("src",ctx+"/res/images/ceo/myProject_select.png");
			loadProjectList();
			$("#crumbsText").text("我的项目");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/ceo/settlement_noselect.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/ceo/myInfo_noselect.png");
		}else if(type=="settlement"){
			$(this).find("img").attr("src",ctx+"/res/images/ceo/settlement_select.png");
			loadSettlement();
			$("#crumbsText").text("结算管理");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_noselect.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/ceo/myInfo_noselect.png");
		}else if(type=="myInfo"){
			loadInfo();
			$(this).find("img").attr("src",ctx+"/res/images/ceo/myInfo_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/ceo/myProject_noselect.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/ceo/myInfo_noselect.png");
		}
	});
	//我的项目
	function loadProjectList(){
		jsonAjax.post({
			url:ctx+'/project/company/list',
			success:function(data){
				$("#dataList").html(data);
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
	//项目数据查询
	function loadProjectListData(){
		currentStatus=status;
		jsonAjax.post({
			url:ctx+'/project/company/listData',
			data:$("#projectListForm").serialize(),
			success:function(html){
				$("#dataSecondList").html(html);
				
				$("#waittab").text($("#waitCount").val());
				$("#auditintab").text($("#auditInCount").val());
				$("#auditedtab").text($("#auditedCount").val());
				
				$("img[id=delete]").click(deleteProject);//删除
				$("img[id=edit]").click(editProject);//编辑
				$("a[id=detail]").click(viewProject);//查看详情
				$("button[id=submit_intention]").click(submitIntention);//提交意向金
				$("button[id=affirm_projectdefine]").click(affirmProjectdefine);//确认项目立项书
				$("button[id=trust_amount]").click(trustAmount);//托管费用
				$("button[id=go_accept]").click(goAccept);//去验收
				$("button[id=go_evaluate]").click(goEvaluate);//去评价
				$("button[id=view_cause]").click(viewCause);//查看不通过原因
				$("button[id=view_material]").click(viewMaterial);//查看新文件
				//分页组件js
				pagination(loadProjectListData);
			}
		});
	}
	//结算管理
	function loadSettlement(){
		jsonAjax.post({
			url:ctx+'/member/settlement',
			success:function(html){
				$("#dataList").html(html);
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
						var name=$(this).text();
						if(name.length>5){
							name=name.substring(0,5);
						}
						$("#projectName").val(name);
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
	//我的资料
	function loadInfo(){
		window.location.href=ctx+'/member/personage?'+jsonAjax.random();
	}
	//查看原因
	function viewCause(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/'+id+'/cause?'+jsonAjax.random();
	}
	//删除项目
	function deleteProject(){
		var id=$(this).attr("data");
		$("#optDialog").modal('show');
		$("#optDialog #ok-btn").unbind('click');
		$("#optDialog #ok-btn").click(function(){
			jsonAjax.post({
				url:ctx+'/project/company/remove',
				data:{projectId:id},
				success:function(data){
					$("#optDialog").modal('hide');
					
					$("#projectStatus").val(currentStatus);
					loadProjectList();
				}
			});
		});
	}
	//处理回调
	var rcp=sessionStorage.getItem('rsp_intent_isd');
	if(rcp!=null&&rcp!=""){
	    if(rcp=='settlement'){
			$(".left-menu li:eq(1)").click();
		}else if(rcp=='info'){
			$(".left-menu li:eq(2)").click();
		}else{
			$(".left-menu li:first").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	}else if(getUrlParams("ts")=="settlement"){
		$(".left-menu li:eq(1)").click();
	}else{
		$(".left-menu li:first").click();
	}
});