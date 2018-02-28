require([ "jquery", "ajax", "jsonAjax","commons","bootstrap-datepicker","webupload"], function($) {
	
	
	var projectId=$("#projectId").val();
	//面包屑处理
	$("#crumbs_myproject").click(projectList);
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	//左侧菜单点击事件
	$("#project_ul li").click(function(){
		$("#project_ul li").removeClass("active_color");
		$("#project_ul li").find(".line").removeClass("active_line");
		$(this).find(".line").addClass("active_line");
		$(this).addClass("active_color");
		var type=$(this).attr("data");
		$("#crumbs_detail").text($(this).text());
		if(type=="project_detail"){
			$("#project_ul li:nth-child(1)").find(".icon_span").removeClass("detail_noselect").addClass("detail_select");
			//$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$("#project_ul li:nth-child(3)").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$("#project_ul li:nth-child(4)").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			loadProjectDetail();//项目详情
		}
		//else if(type=="project_demand"){
		//	$("#project_ul li:nth-child(1)").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
		//	$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("demand_noselect").addClass("demand_select");
		//	$("#project_ul li:nth-child(3)").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
		//	$("#project_ul li:nth-child(4)").find(".icon_span").removeClass("material_select").addClass("material_noselect");
		//	$("#project_ul li:nth-child(5)").find(".icon_span").removeClass("log_select").addClass("log_noselect");
		//	loadProjectDetail();//需求修改
		//}

		else if(type=="project_weekly"){
			$("#project_ul li:nth-child(1)").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			//$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("weekly_noselect").addClass("weekly_select");
			$("#project_ul li:nth-child(3)").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$("#project_ul li:nth-child(4)").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			initProjectWeekly(projectId);//项目周报
		}else if(type=="project_material"){
			$("#project_ul li:nth-child(1)").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			//$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$("#project_ul li:nth-child(3)").find(".icon_span").removeClass("material_noselect").addClass("material_select");
			$("#project_ul li:nth-child(4)").find(".icon_span").removeClass("log_select").addClass("log_noselect");
			initProjectMaterial(projectId);//项目文件
		}else if(type=="project_log"){
			$("#project_ul li:nth-child(1)").find(".icon_span").removeClass("detail_select").addClass("detail_noselect");
			//$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("demand_select").addClass("demand_noselect");
			$("#project_ul li:nth-child(2)").find(".icon_span").removeClass("weekly_select").addClass("weekly_noselect");
			$("#project_ul li:nth-child(3)").find(".icon_span").removeClass("material_select").addClass("material_noselect");
			$("#project_ul li:nth-child(4)").find(".icon_span").removeClass("log_noselect").addClass("log_select");
			initProjectLog(projectId);
		}
	});
	
	function loadProjectDetail(){
		jsonAjax.post({
			url:ctx+'/project/company/detail',
			data:{projectId:projectId},
			success:function(html){
				$("#dataList").html(html);
				if($("#status").val()=="unpass"){
					window.location.href=ctx+'/project/'+projectId+'/cause?'+jsonAjax.random();
				}
				$("img[id=edit]").click(editProject);//编辑
				$("button[id=submit_intention]").click(submitIntention);//提交意向金
				$("button[id=affirm_define]").click(affirmProjectdefine);//确认项目立项书
				$("button[id=trust_amount]").click(trustAmount);//托管费用
				$("button[id=go_accept]").click(goAccept);//去验收
			}
	    });
	}
	
	//处理回调
	var rcp=sessionStorage.getItem('rsp_intent_isd');
	var rp=getUrlParams("rp");//处理链接返回的回调
	if(rp!=null&&rp!=""){
		rcp=rp;
	}
	if(rcp!=null&&rcp!=""){
		if(rcp=='weekly'){
			$(".left-menu li:eq(1)").click();
		}else if(rcp=='material'){
			$(".left-menu li:eq(2)").click();
		}else if(rcp=='logs'){
			$(".left-menu li:eq(3)").click();
		}
		sessionStorage.removeItem('rsp_intent_isd');
	}else{
		$(".left-menu li:first").click();
	}
});