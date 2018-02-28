require([ "jquery", "ajax", "jsonAjax","bootstrap-datepicker","webupload","commons"], function($) {
	//头部点击事件
	var currentStatus;
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
	   					$(this).attr("data",false);
	   				}else{
	   					$(this).attr("data",true);
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
		if(type=="myTask"){
			$(this).find("img").attr("src",ctx+"/res/images/elite/a_select.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/c.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadTaskList();
			$("#crumbsText").text("我认领的任务");
		}else if(type=="myAttention"){
			$(this).find("img").attr("src",ctx+"/res/images/elite/b_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/c.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadAttentionList();
			$("#crumbsText").text("我的关注");
		}else if(type=="settlement"){
			$(this).find("img").attr("src",ctx+"/res/images/elite/c_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(4)").find("img").attr("src",ctx+"/res/images/elite/d.png");
			loadSettlement();
			$("#crumbsText").text("结算管理");
		}else if(type=="myInfo"){
			$(this).find("img").attr("src",ctx+"/res/images/elite/d_select.png");
			$(".left-menu li:nth-child(1)").find("img").attr("src",ctx+"/res/images/elite/a.png");
			$(".left-menu li:nth-child(2)").find("img").attr("src",ctx+"/res/images/elite/b.png");
			$(".left-menu li:nth-child(3)").find("img").attr("src",ctx+"/res/images/elite/c.png");
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

	
	//任务详情
	$(document).on("click",'#taskDetail',function(){
		var id=$(this).attr("data");
		//推荐和认领中的任务详情跳转到 'hall/task/'+id+'?'+jsonAjax.random();
		window.location.href=ctx+'/task/'+id+'/index?'+jsonAjax.random();
	});
	

	//处理回调
	var rcp=sessionStorage.getItem('rsp_intent_isd');
	if(rcp!=null&&rcp!=""){
		if(rcp=='attention'){
			$(".left-menu li[data=myAttention]").click();
		}else if(rcp=='settlement'){
			$(".left-menu li[data=settlement]").click();
		}else if(rcp=='info'){
			$(".left-menu li[data=myInfo]").click();
		}else{
			$(".left-menu li[data=myTask]").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	}else{
		$(".left-menu li:first").click();
	}

});