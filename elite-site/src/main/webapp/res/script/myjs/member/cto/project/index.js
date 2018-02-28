require(["jquery", "ajax", "jsonAjax","commons","bootstrap-datepicker","webupload","autocomplete"], function($) {
	
	var projectId=$("#projectId").val();
	//面包屑处理
	$("#crumbs_myproject").click(projectList);
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
		if(type=="project_detail"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			loadProjectDetail();//项目详情
		}else if(type=="task_assign"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			loadProjectTaskDetail(projectId);//任务详情
		}else if(type=="project_demand"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			loadProjectDetail();//需求修改
		}else if(type=="project_weekly"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			initProjectWeekly(projectId);//项目周报
		}else if(type=="project_material"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			$(".log").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			
			
			initProjectMaterial(projectId);//项目文件
		}else if(type=="project_log"){
			$(this).find(".icon_span").removeClass(liclass+"_noselect").addClass(liclass+"_select");
			$(".task").find(".icon_span").removeClass("task_select").addClass("task_noselect");
			$(".demand").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$(".weekly").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$(".material").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$(".detail").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			
			
			initProjectLog(projectId);
		}
		
		
		
	});
	
	//处理回调
	var rcp=sessionStorage.getItem('rsp_intent_isd');
	var rp=getUrlParams("rp");//处理链接返回的回调
	if(rp!=""){
		rcp=rp;
	}
	if(rcp!=null&&rcp!=""){
		if(rcp=='project'){
			$(".left-menu li:eq(0)").click();
		}else if(rcp=='task'){
			$(".left-menu li:eq(1)").click();
		}else if(rcp=='weekly'){
			$(".left-menu li:eq(2)").click();
		}else if(rcp=='material'){
			$(".left-menu li:eq(3)").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	}else{
		$(".left-menu li:first").click();
	}


	
	function loadProjectDetail(){
		jsonAjax.post({
			url:ctx+'/project/c/viewProject',
			data:{projectId:projectId},
			success:function(html){
				$("#dataList").html(html);
				var deadlineTime=$("#deadlineTime").val();
				addTimer("deadlineSpan",deadlineTime);
				//查看产物
				$("#viewMaterial").click(function(){
					$(".left-menu li:eq(3)").click();
				});
				$("#signTask").click(function(){
					$(".left-menu li:eq(1)").click();
				});
				$("#totender").click(function(){
					//如果还未审核过
					if($("#validStatus").val()!='normal'){
						tostHint("提交失败","您还未审核通未不能执行此操作");
						console.info("您还未审核通未不能执行此操作");
						return false;
					}
					var id = $(this).attr("data");
					window.location.href=ctx+'/project/c/'+id+'/tender?'+jsonAjax.random();
				});
				//查看定标书
				$("#confirmatta").click(function(){
					var id = $(this).attr("data");
					window.location.href=ctx+'/project/c/'+id+'/define?'+jsonAjax.random();
				});
			}
	    });
	}
	
	function loadProjectTaskDetail(projectId){
		var data;
		if($("#taskForm").length>0){
			data=$("#taskForm").serialize();
		}else{
			data={projectId:projectId};
		}
		jsonAjax.post({
			url:ctx+'/project/c/viewProjectTask',
			data:data,
			success:function(html){
				$("#dataList").html(html);
				//发布任务
				UE.delEditor('container');
				UE.getEditor('container', {
				    toolbars: [
				        ['insertorderedlist', 'insertunorderedlist']
				    ]
				});
				$(".release-btn").click(pushTask);
				$("#publicBtn").click(saveProjectTask);
				fileupload();
				$("#demandTypeVal,#demandTypeIcon,#taskTypeVal,#taskTypeIcon").click(selectType);
				
				$("button[id^='confirmElite_']").click(confirmElite);
				//任务提醒
				$("button[id^='remind_']").click(remindTask);
				//查看/提交产物
				$("#viewMaterial").click(viewMaterial);
				//关闭任务
				$("div[id^='closeTask_']").click(closeTask);
				//验收
				$("button[id^='acceptance_']").click(acceptanceTask);
				
				//任务大厅
				$("#toHall").click(hallDetail);
				//精英详情
				$("div[id='showEliteInfo']").click(eliteDetail);
				
				//任务详情
				$("a[id^='taskDetail_']").click(taskDetail);
				
				$("span[id^='deadlineSpan_']").each(function(){
					var deadlineTime=$(this).attr("data");
					addTimer($(this).attr("id"),deadlineTime); 
				})
				
				//分页组件js
				$(".pagination #prev_pager").click(function(){
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 <= 1) {
						return;
					}
					$("#pager_pageth").val(currentPageth - 1);
					loadProjectTaskDetail(projectId);
				});
				$(".pagination #jump_pager").click(function(){
					var pageth = $(this).attr("data");
					$("#pager_pageth").val(pageth - 0);
					loadProjectTaskDetail(projectId);
				});
				$(".pagination #next_pager").click(function(){
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
						return;
					}
					console.info($("#pager_pageth").val());
					$("#pager_pageth").val(currentPageth - 0 + 1);
					loadProjectTaskDetail(projectId);
				});
			}
	    });
	}

	
	//我的项目列表
	function projectList(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	}
	
	//去竞标
	$(document).on("click",'#totender',function(){
		var id = $(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/tender?'+jsonAjax.random();
	});
	
	//查看意向书
	$(document).on("click",'#confirmatta',function(){
		var id = $(this).attr("data");
		window.location.href=ctx+'/project/c/'+id+'/define?'+jsonAjax.random();
	});
	

});