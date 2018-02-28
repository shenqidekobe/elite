require([ "jquery", "ajax", "jsonAjax","commons","autocomplete","bootstrap-datepicker","webupload"], function($) {
	
	var projectId=$("#projectId").val();
	var taskId=$("#taskId").val();
	//面包屑处理
	$("#crumbs_mytask").click(taskList);
	//左侧菜单点击事件
	$(".left-menu li").click(function(){
		$(".left-menu li").find(".text_span").removeClass("active_color");
		$(this).find(".text_span").addClass("active_color");
		$(".left-menu li").find(".line_span").removeClass("active_line");
		$(this).find(".line_span").addClass("active_line");
		var type=$(this).attr("data");
		$("#crumbs_detail").text($(this).text());
		var liclass=$(this).attr("class");
		$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
		if(type=="task_detail"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			loadTaskDetail();//任务详情
		}else if(type=="project_weekly"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			initProjectWeekly(taskId);//项目周报
		}else if(type=="project_material"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			initProjectMaterial(taskId);//项目文件
		}else if(type=="project_log"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			
			initProjectLog(taskId);
		}
	});
	
	//处理回调
	var rcp=sessionStorage.getItem('rsp_intent_isd');
	var rp=getUrlParams("rp");//处理链接返回的回调
	if(rp!=""){
		rcp=rp;
	}
	if(rcp!=null&&rcp!=""){
		if(rcp=='task'){
			$(".left-menu li[data=task_detail]").click();
		}else if(rcp=='weekly'){
			$(".left-menu li[data=project_weekly]").click();
		}else if(rcp=='material'){
			$(".left-menu li[data=project_material]").click();
		}else if(rcp=='projectlog'){
			$(".left-menu li[data=project_log]").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	}else{
		$(".left-menu li:first").click();
	}

	
	function loadTaskDetail(){
		console.info(taskId);
		jsonAjax.post({
			url:ctx+'/task/viewTask',
			data:{taskId:taskId},
			success:function(html){
				$("#dataList").html(html);
				var deadlineTime=$("#deadlineTime").val();
				addTimer("deadlineSpan",deadlineTime);
				var deadlineTime=$("#guaranteeTime").val();
				addTimer("guaranteeSpan",deadlineTime);
				$("#apply").click(toapply);
				$("#submitMaterial").click(submitMaterial);
			}
	    });
	}
	
	//提交产物
	function submitMaterial(){
		$(".left-menu li[data=project_material]").click();
	}
	
	function toapply(){
		var id=$(this).attr("data");
		var oper=$(this).attr("oper");
		if(oper=='N'){
			tostConfirm("您确定要取消报名吗？",function(){
				apply(id,oper);
			});
		}else{
			apply(id,oper);
		}
	}
	
	function apply(id,oper){
		jsonAjax.post({
			url:ctx+'/task/apply?'+jsonAjax.random(),
			data:{taskId:id,oper:oper},
			success:function(data){
				console.info(data);
				if(data.result=="SUCCESS"){
					if(oper=="Y"){
						$("#apply").attr("oper","N");
						$("#apply").text("取消报名");
						tostHint("报名成功","您已成功报名此任务，请耐心等待");
					}else{
						$("#apply").attr("oper","Y");
						$("#apply").text("认领任务");
					}
					$("#applyCount").text(data.id);
				}else{
					tostHint("报名失败",data.msg);
				}
			}
		});
	}

});