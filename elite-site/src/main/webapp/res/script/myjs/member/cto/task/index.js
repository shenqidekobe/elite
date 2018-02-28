require([ "jquery", "ajax", "jsonAjax","commons","autocomplete","bootstrap-datepicker","webupload"], function($) {
	
	var projectId=$("#projectId").val();
	var taskId=$("#taskId").val();
	
	//任务管理
	$("#crumbs_mytask").click(function(){
		sessionStorage.setItem('rsp_intent_isd','task');
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/index?'+jsonAjax.random();
	})
	
	//首页
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	})

	//左侧菜单点击事件
	$(".left-menu li").click(function(){
		$(".left-menu li").find(".text_span").removeClass("active_color");
		$(this).find(".text_span").addClass("active_color");
		$(".left-menu li").find(".line_span").removeClass("active_line");
		$(this).find(".line_span").addClass("active_line");
		var type=$(this).attr("data");
		$("#crumbs_detail").text($(this).text());
		if(type=="task_detail"){
			$(".left-menu li:eq(0)").find(".icon_span").removeClass("detail_noselect").addClass("detail_select");
			$(".left-menu li:eq(1)").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".left-menu li:eq(2)").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			loadTaskDetail();//任务详情
		}else if(type=="project_material"){
			$(".left-menu li:eq(0)").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".left-menu li:eq(1)").find(".icon_span").removeClass("material_noselect").addClass("material_select");
			$(".left-menu li:eq(2)").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			initProjectMaterial(projectId);//项目文件
		}else if(type=="project_log"){
			$(".left-menu li:eq(0)").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".left-menu li:eq(1)").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".left-menu li:eq(2)").find(".icon_span").removeClass("log_noselect").addClass("log_select");
			initProjectLog(taskId);
		}
	});
	
	//处理回调
	var rcp=sessionStorage.getItem('rsp_intent_isd');
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
			url:ctx+'/project/c/viewTask',
			data:{taskId:taskId},
			success:function(html){
				$("#dataList").html(html);
				var deadlineTime=$("#deadlineTime").val();
				addTimer("deadlineSpan",deadlineTime);
				var deadlineTime=$("#guaranteeTime").val();
				addTimer("guaranteeSpan",deadlineTime);
				//任务提醒
				$("#remind").click(remindTask);
				//验收
				$("#acceptance").click(acceptanceTask);
			}
	    });
	}
	
	//任务提醒
	function remindTask(){
		var taskId = $(this).attr("data");
		jsonAjax.post({
			url:ctx+'/project/c/taskRemind',
			data:{taskId:taskId},
			success:function(data){
				console.info("提醒");
				$(".left-menu li:eq(0)").click();
			}
	    });

	}
	
	//验收
	function acceptanceTask(){
		var id = $(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/acceptance?'+jsonAjax.random();
	}


	
});