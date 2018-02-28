//任务大厅
function hallDetail(){
	window.location.href=ctx+'/hall?'+jsonAjax.random();
}

function taskDetail(){
	var id = $(this).attr("data");
	window.location.href=ctx+'/project/c/task/'+id+'/index?'+jsonAjax.random();
}


//发布任务
function pushTask(){
	
	$("#releaseTaskDialog").modal({
		backdrop: 'static'
	});
	$('#endTime,#endIcon').datepicker({
		todayBtn : "linked", 
		startDate : getDateStr(1) ,
		autoclose : true,  
        todayHighlight : true,
	}).on("changeDate",function(e){
    	var time=e.date;
    	$("#endTime").val(time.Format("yyyy-MM-dd"));
    });
	$('#startTime,#startIcon').datepicker({
		todayBtn : "linked", 
		startDate : new Date() ,
        autoclose : true,  
        todayHighlight : true,  
	}).on("changeDate",function(e){
    	var time=e.date;
    	$("#startTime").val(time.Format("yyyy-MM-dd"));
    });
	$('#deliveryTime,#deliveryIcon').datepicker({
		todayBtn : "linked", 
		startDate : new Date() ,
        autoclose : true,  
        todayHighlight : true,  
	}).on("changeDate",function(e){
    	var time=e.date;
    	$("#deliveryTime").val(time.Format("yyyy-MM-dd"));
    });
}

//查看提交产物
function viewMaterial(){
	$(".left-menu li:eq(3)").click();
}

function confirmElite(){
	jsonAjax.post({
		url:ctx+'/project/c/viewApplyElite',
		data:{taskId:$(this).attr("data")},
		success:function(html){
			$("#assignTaskDialog").modal({
				backdrop: 'static'
			});
			$('.window-assigned').html(html);
			$('.window-assigned').show();
			$(".window-close").click(function(){
				$('.window-assigned').hide();
			});

			//查看精英详情
			$('.imgDiv').click(eliteDetail);
			//指定精英
			$('.task-btn').click(appointElite)
			
			$(".wab-group").mouseover(function(){
				$(this).find('.task-btn').show();
			});
			$(".wab-group").mouseleave(function(){
				$(this).find('.task-btn').hide();
			});
		}
    });
}

function remindTask(){
	var taskId = $(this).attr("data");
	jsonAjax.post({
		url:ctx+'/project/c/taskRemind',
		data:{taskId:taskId},
		success:function(data){
			console.info("提醒");
			$(".left-menu li:eq(1)").click();
		}
    });

}


function closeTask(){
	var taskId = $(this).attr("data");
	tostConfirm("你确定关闭这条任务?",function(){
		$("#closeDialog").modal({
			backdrop: 'static'
		});
		$(".errortip").text("");
		$("#reason").val("");
		$("#confirm").click(function(){
			var reason=$("#reason").val();
			if($.trim(reason)==''){
				$(".errortip").text("请填写关闭原因");
				return false;
			}
			jsonAjax.post({
				url:ctx+'/project/c/close',
				data:{taskId:taskId,"reason":reason},
				success:function(data){
					$("#closeDialog").modal('hide');
					$("div[class='modal-backdrop fade in']").remove();
					$("body").removeClass('modal-open');
					$(".left-menu li:eq(1)").click();
				}
		    });
		})
		$("#cancel").click(function(){
			$("#closeDialog").modal('hide');
		})
	});
}

function eliteDetail(){
	var id=$(this).attr("data");
	var url=ctx+"/circle/view/"+id+"?"+jsonAjax.random();
	window.open(url);
}

function appointElite(){
	var taskId = $(this).attr("data");
	var memberId = $(this).attr("member");
	tostConfirm("是否确认该精英",function(){
		jsonAjax.post({
			url:ctx+'/project/c/appoint',
			data:{taskId:taskId,memberId:memberId},
			success:function(data){
				$(".left-menu li:eq(1)").click();
			}
	    });
	})
}


function selectType(){
	var id = $(this).attr("data");
	$('#'+id).fadeIn();
	$("#demandList ul li").click(function() {
		$('#demandTypeVal').val($(this).text());
		$('#demandType').val($(this).attr("data"));
		$('#demandTypeList').hide();
	});
	$("#taskTypeList ul li").click(function() {
		$('#taskTypeVal').val($(this).text());
		$('#taskType').val($(this).attr("data"));
		$('#taskTypeList').hide();
	});
	$("body").bind("mousedown", function(event) {
		$(".budget").fadeOut("fast");
		$("body").unbind("mousedown");
	});
}

function saveProjectTask(){
	var name = $('#name').val();
	var demandType = $('#demandType').val();
	var taskType = $('#taskType').val();
	var startTime = $('#startTime').val();
	var deliveryTime = $('#deliveryTime').val();
	var endTime = $('#endTime').val();
	var intro = $('#intro').val();
	var amount = $('#amount').val();
	var intro=UE.getEditor('container').getContentTxt();
	$("#intro").val(UE.getEditor('container').getContent());
	if(name == ''){
		$(".error-tip").text("请填写任务名称");
		return false;
	}else if(demandType == ''){
		$(".error-tip").text("请选择精英角色");
		return false;
	}else if(taskType == ''){
		$(".error-tip").text("请选择任务类型");
		return false;
	}else if(startTime == ''){
		$(".error-tip").text("请填写任务开始时间");
		return false;
	}else if(deliveryTime == ''){
		$(".error-tip").text("请选择任务交付时间");
		return false;
	}else if(deliveryTime == ''){
		$(".error-tip").text("请选择任务截止时间");
		return false;
	}else if(deliveryTime<startTime){
		$(".error-tip").text("任务开始时间必须小于任务交付时间");
		return false;
	}else if(endTime>startTime){
		$(".error-tip").text("任务招募截止时间必须小于任务开始时间");
		return false;
	}else if(amount == ''){
		$(".error-tip").text("请选择金额");
		return false;
	}else if(!validateNum(amount)){
		$(".error-tip").text("金额请填写数字");
		return false;
	}else if(intro == '' || intro>1000){
		$(".error-tip").text("请填写任务要求且字数不能大于1000");
		return false;
	}
	jsonAjax.post({
		url:ctx+'/project/c/pushTask',
		data:$("#releaseTaskForm").serialize(),
		success:function(data){
			if (data.result == "SUCCESS") {
				$("#error-tip").text("保存成功");
				window.setTimeout(function(){ 
					$(".left-menu li:eq(1)").click();
				},2000);
				$("#releaseTaskDialog").modal('hide');
			}else{
				$(".error-tip").text(data.msg);
			}		
		}
    });
}

function hideModal(){
	$("#releaseTaskDialog").modal('hide');
}

function acceptanceTask(){
	var id = $(this).attr("data");
	window.location.href=ctx+'/project/c/'+id+'/acceptance?'+jsonAjax.random();
}


function fileupload(){
	upload.uploadImg($("#taskLogoFile"),"credit",function(data){
		//$(".word-logo").css("background","url("+data.url+")");
		$(".word-logo").show();
		$("#logo").attr("src",data.url);
		//$("#taskLogoImg").attr("src",data.url);
		//$("#taskLogoImg").css({width:'230px',height:'160px'});
		$("#attaId").val(data.attaId);
	});
	
	upload.uploadFile($("#resumeAttaFile"),"elite","resume_atta",function(data){
		$("#resumeAtta_show").show();
		var prd='<button type="button" class="upload-file" id="resumeAtta_btn">'+data.originalName+'<img src='+ctx+'"/res/images/del.png" data="'+data.attaId+'" id="pbimg" class="delete-cross"></button>';
		$("#resumeAtta_show").html(prd);
		$("img[id=pbimg]").unbind('click');
		$("img[id=pbimg]").bind('click',function(e){
            var thiss=$(this);
			var id=thiss.attr("data");
			upload.removeFile("resume_atta",function(url){
				jsonAjax.post({
					url:url,
					data:{attaId:id},
					success:function(drsp){
						if(drsp.result=="SUCCESS"){
							$("#resumeAttaId").val('');
							$("#resumeAtta_show").html('');
						}
					}
				});
			});
		});
		$("#resumeAttaId").val(data.attaId);
	});

}