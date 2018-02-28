require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	
	$(".choose-title li").click(function(){
		var type=$(this).attr("data");
		$("#pager_pageth").val(1);
		if(type=="project"){
			$(".toPickup").show();
			$(".toDo").hide();
			//绑定项目列表查询选择事件
			bindProjectListEvent();
			
			loadProjectList();
		}else{
			$(".toDo").show();
			$(".toPickup").hide();
			//绑定任务列表查询选择事件
			bindTaskListEvent();
			
			loadTaskList();
		}
	});
	
	function bindProjectListEvent(){
		$("#pSolutionUl li").click(function(){
			var data=$(this).attr("data");
			$("#pSolutionUl li").removeClass("selected-color");
			$(this).addClass("selected-color");
			$("#pSolution").val(data);
			$("#pager_pageth").val(1);
			loadProjectList();
		});
		$("#pStatusUl li").click(function(){
			var data=$(this).attr("data");
			$("#pStatus").val(data);
			$("#pStatusUl li").removeClass("selected-color");
			$(this).addClass("selected-color");
			$("#pager_pageth").val(1);
			loadProjectList();
		});
	}
	function bindTaskListEvent(){
		$("#taskTypeUl li").click(function(){
			var data=$(this).attr("data");
			$("#taskTypeUl li").removeClass("selected-color");
			$(this).addClass("selected-color");
			$("#taskType").val(data);
			$("#pager_pageth").val(1);
			loadTaskList();
		});
		$("#tStatusUl li").click(function(){
			var data=$(this).attr("data");
			$("#tStatus").val(data);
			$("#tStatusUl li").removeClass("selected-color");
			$(this).addClass("selected-color");
			$("#pager_pageth").val(1);
			loadTaskList();
		});
		$("#tDemandUl li").click(function(){
			var data=$(this).attr("data");
			$("#tDemandType").val(data);
			$("#tDemandUl li").removeClass("selected-color");
			$(this).addClass("selected-color");
			$("#pager_pageth").val(1);
			loadTaskList();
		});
	}
	
	function loadProjectList(){
		jsonAjax.post({
			url:ctx+'/hall/project/listData',
			data:$("#projectListForm").serialize(),
			success:function(html){
				$(".pickup-content").html(html);
				//绑定分页事件
				hallPaging();
			}
		});
	}
	function loadTaskList(){
		jsonAjax.post({
			url:ctx+'/hall/task/listData',
			data:$("#stageListForm").serialize(),
			success:function(html){
				$(".todo-content").html(html);
				$("li[id=tl_task],a[id=detail]").click(viewTask);
				//绑定分页事件
				hallPaging();
			}
		});
	}
	
	function viewTask(){
		var id=$(this).attr("data");
		//var status=$(this).attr("status");
		//if(status=="wait_recruit"||status=="recruit_in"){
		window.location.href=ctx+'/hall/task/'+id+'?'+jsonAjax.random();
	}
	
	
	function hallPaging(){
		$(".page-l").click(prev);
		$(".page-r").click(next);
	}
	function prev(){
		var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 <= 1) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 1);
		if($("#pagingType").val()=="project"){
			loadProjectList();
		}else{
			loadTaskList();
		}
	}
    function next(){
    	var currentPageth = $("#pager_pageth").val();
		if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
			return;
		}
		$("#pager_pageth").val(currentPageth - 0 + 1);
		if($("#pagingType").val()=="project"){
			loadProjectList();
		}else{
			loadTaskList();
		}
	}
    
    //$(".choose-title li:eq(0)").click();
    bindTaskListEvent();
	
	loadTaskList();
});
