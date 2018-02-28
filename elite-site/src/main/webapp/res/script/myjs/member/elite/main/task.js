	//我的任务
	function taskList(){
		sessionStorage.setItem('rsp_intent_isd','tasks');
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	}

	//我的任务列表
	function loadTaskList(){
		jsonAjax.post({
			url:ctx+'/task/count',
			success:function(data){
				$("#projectList").html(data);
				//项目列表状态事件
				$(".tabs .col-xs-4").click(function(){
					$(".tabs div").removeClass("active-tab");
					$(this).addClass("active-tab");
					var index=parseInt($(this).attr("index"));
					var left=260*index;
					$(".active-line").css({'left':left+'px'});
					var type=$(this).attr("data");
					$("#taskType").val(type);
					$("#pager_pageth").val(1);
					loadTaskListData();
				});
				$(".tabs div:first").click();
			}
		});
	}
	
	function loadTaskListData(){
		jsonAjax.post({
			url:ctx+'/task/task_list',
			data:$("#taskForm").serialize(),
			success:function(html){
				$("#dataList").html(html);
				$("#recomtab").text($("#recomCount").val());
				$("#recruitintab").text($("#recruitInCount").val());
				$("#recruitwintab").text($("#recruitWinCount").val());
				$("span[id^='guaranteeSpan_']").each(function(){
					var guaranteeTime=$(this).attr("data");
					addTimer($(this).attr("id"),guaranteeTime); 
				})
				$("button[id^=sendMaterial_]").click(sendMaterial)
				//报名
				$("button[id=apply]").click(toapply);
				$("button[id=signup]").click(applyForRecomm);
				
				//分页组件js
				$(".pagination #prev_pager").click(function(){
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 <= 1) {
						return;
					}
					$("#pager_pageth").val(currentPageth - 1);
					loadTaskListData();
				});
				$(".pagination #jump_pager").click(function(){
					var pageth = $(this).attr("data");
					$("#pager_pageth").val(pageth - 0);
					loadTaskListData();
				});
				$(".pagination #next_pager").click(function(){
					var currentPageth = $("#pager_pageth").val();
					if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
						return;
					}
					console.info($("#pager_pageth").val());
					$("#pager_pageth").val(currentPageth - 0 + 1);
					loadTaskListData();
				});
			}
		});
	}
	
	//提交产出物
	function sendMaterial(){
		var taskId=$(this).attr("data");
		sessionStorage.setItem('rsp_intent_isd','material');
		window.location.href=ctx+'/task/'+taskId+'/index?'+jsonAjax.random();
	}
	
	//推荐列表中报名
	function applyForRecomm(){
		var id=$(this).attr("data");
		jsonAjax.post({
			url:ctx+'/task/apply?'+jsonAjax.random(),
			data:{taskId:id,oper:"Y"},
			success:function(data){
				if(data.result=="SUCCESS"){
					loadTaskList();
				}
			}
		})
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
						$(".tabs div:eq(2)").click();//取消刷新数据
					}
					$("#applyCount").text(data.id+"人报名");
				}else{
					tostHint("报名失败",data.msg);
				}
			}
		});
	}
