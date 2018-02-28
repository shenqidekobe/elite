require([ "jquery", "ajax", "jsonAjax","commons","bootstrap-datepicker","webupload","city"], function($) {
	
	var kucity=$('#areaName').kuCity();
	$("#cityIcon").click(function(){
		kucity.focus();
	});
	//项目描述编辑器
	UE.delEditor('editor');
	var ue=UE.getEditor('editor', {
	    toolbars: [
	        ['insertorderedlist', 'insertunorderedlist']
	    ],
	    wordCount:true,
	    maximumWords:1000,
	});
	ue.addListener("selectionchange", function () {
		var count=ue.getContentTxt().length;
		if(count>1000){
			$("#edui1_wordcount").text("当前已输入"+count+"个字符, 已经超过1000个字符");
			return false;
		}
		$("#edui1_wordcount").text("当前已输入"+count+"个字符, 您还可以输入"+(1000-parseInt(count))+"个字符。");
		$("#introError").hide();
    });
	//初始化表单
	var solutionVal=$("#projectSolution").val();
	var pindustry=$("#productIndustry").val();
	var pfunction=$("#productFunction").val();
	var plan=$("#planed").val();
	var solutionText=""
	if(solutionVal!=""){
		$("#solution_div").show();
		var arrs=solutionVal.split(",");
		for(var i in arrs){
			$(".solution-btn").each(function(){
				if($(this).attr("data")==arrs[i]){
					$(this).addClass("active-btn");
					var orders=$(this).attr("orders");
					solutionText=addStr(solutionText,$(this).text(), "+",orders);
				}
			});
		}
		$(".selected-solution").html(solutionText);
	}
	if(pindustry!=""){
		var arrs=pindustry.split(",");
		for(var i in arrs){
			$(".industry").each(function(){
				if($(this).attr("data")==arrs[i]){
					$(this).addClass("active-btn");
				}
			});
		}
	}
	if(pfunction!=""){
		var arrs=pfunction.split(",");
		for(var i in arrs){
			$(".function-btn").each(function(){
				if($(this).attr("data")==arrs[i]){
					$(this).addClass("active-btn");
				}
			});
		}
	}
	if(plan!=""){
		$(".btn-group button").each(function(){
			if($(this).attr("data")==plan){
			   $(this).addClass("active-btn");
			}
		});
	}
	//选择解决方案类型
	$(".solution-btn").click(function(event){
		var thiss=$(this);
		var orders=thiss.attr("orders");
		if(thiss.hasClass("active-btn")){
			thiss.removeClass("active-btn");
			solutionText=subStr(solutionText,thiss.text(), "+",orders);
			solutionVal=subStr(solutionVal,thiss.attr("data"), ",",orders);
		}else{
			thiss.addClass("active-btn");
			solutionText=addStr(solutionText,thiss.text(), "+",orders);
			solutionVal=addStr(solutionVal,thiss.attr("data"), ",",orders);
		}
		$("#solution_div").show();
		if(solutionText==""){
			$("#solution_div").hide();
		}
		$(".selected-solution").html(solutionText);
		$("#projectSolution").val(solutionVal);
		
		$("#solutionError").hide();
		return false;
	});
	//项目预算点击事件
	$("#projectBudget,.form-triangle-project").click(function(){
		$(".budget").fadeIn();
		var pts=$("#projectBudget");
		$(".budget li").click(function(){
			var thiss=$(this);
			pts.val(thiss.text());
			$(".budget").fadeOut("fast");
		});
		$("body").bind("mousedown", function(event) {
			$(".budget").fadeOut("fast");
			$("body").unbind("mousedown");
		});
		
		$("#budgetError").hide();
	});
	//切换详细信息
	$("#more_btn").click(function(){
		$(".release-more").toggle();
		var display=$(".release-more").css("display");
		if (display=="block"){
            $(this).addClass("more_btn").removeClass("moreUp");
        }else {
        	$(this).addClass("moreUp").removeClass("more_btn");
        }
	});
	$(".expand-icon").click(function(){
		$(this).next().toggle();
		var display=$(this).next().css("display");
		if (display=="block"){
            $(this).attr("src","/res/images/project/arrowDown.png");
        }else {
            $(this).attr("src","/res/images/project/arrowRight.png");
        }
	});
	//交付时间选择
	var startTime=$("#serverDate").val();
	var expectTime=0;
	$('#startTime').datepicker({
		startDate:new Date(),
		todayHighlight:true,
        autoclose: true
    }).on("changeDate",function(e){
    	startTime=e.date.getTime();
    	if(expectTime>0){
    		var interval=expectTime-startTime;
        	var day=interval/(24 * 60 * 60 * 1000);
        	day=parseInt(day)+1;
        	$("#deliveryDay").val(day);
    	}
    	$("#timeError").hide();
    });
	$('#startTime_icon').datepicker({
		orientation:"right",
		startDate:new Date(),
        autoclose: true
    }).on("changeDate",function(e){
    	var time=e.date;
    	$("#startTime").val(time.Format("yyyy-MM-dd"));
    	startTime=e.date.getTime();
    	if(expectTime>0){
    		var interval=expectTime-startTime;
        	var day=interval/(24 * 60 * 60 * 1000);
        	day=parseInt(day)+1;
        	$("#deliveryDay").val(day);
    	}
    	
    	if(expectTime!=""&&startTime>expectTime){
			projectTips("timeError", "交付日期不能低于开始日期");
    	}else{
    		$("#timeError").hide();
    	}
    });

	
	$('#expectTime').datepicker({
		startDate:new Date(),
        autoclose: true,
    }).on("changeDate",function(e){
    	expectTime=e.date.getTime();
    	var interval=expectTime-startTime;
    	var day=interval/(24 * 60 * 60 * 1000);
    	day=parseInt(day)+1;
    	$("#deliveryDay").val(day);
    	$("#timeError").hide();
    });
	$('#endTime_icon').datepicker({
		orientation:"right",
		startDate:new Date(),
        autoclose: true
    }).on("changeDate",function(e){
    	var time=e.date;
    	$("#expectTime").val(time.Format("yyyy-MM-dd"));
    	expectTime=e.date.getTime();
    	var interval=expectTime-startTime;
    	var day=interval/(24 * 60 * 60 * 1000);
    	day=parseInt(day)+1;
    	$("#deliveryDay").val(day);
    	
    	if(startTime!=""&&startTime>expectTime){
			projectTips("timeError", "交付日期不能低于开始日期");
    	}else{
    		$("#timeError").hide();
    	}
    });
	//选择行业分类
	var industryVal="";
	$(".industry").click(function(){
		var thiss=$(this);
		if(industryVal.split(",").length < 3){
			if(thiss.hasClass("active-btn")){
				thiss.removeClass("active-btn");
				industryVal=subStr(industryVal,thiss.attr("data"), ",");
			}else{
				thiss.addClass("active-btn");
				industryVal=addStr(industryVal,thiss.attr("data"), ",");
			}
		}else{
			if(thiss.hasClass("active-btn")){
				thiss.removeClass("active-btn");
				industryVal=subStr(industryVal,thiss.attr("data"), ",");
			}else{
				tostHint("提示信息","行业分类最多选择三个");
			}
		}
		$("#productIndustry").val(industryVal);
		
		$("#indusError").hide();
	});
	//选择功能分类
	var functionVal="";
	$(".function-btn").click(function(){
		var thiss=$(this);
		if(thiss.hasClass("active-btn")){
			thiss.removeClass("active-btn");
			functionVal=subStr(functionVal,thiss.attr("data"), ",");
		}else{
			thiss.addClass("active-btn");
			functionVal=addStr(functionVal,thiss.attr("data"), ",");
		}
		$("#productFunction").val(functionVal);
	});
	//是否参与计划按钮切换
	$(".btn-group button").click(function(){
		$(".btn-group button").removeClass("active-btn");
		$(this).addClass("active-btn");
		if($(this).attr("data")=='Y'){
			$("#planed").val(true);
		}else{
			$("#planed").val(false);
		}
	});
	//项目附件上传
	var attaIds=$("#attaIds").val();
	removeAttaFile();
	upload.uploadFile($("#attaFile"),"project","project_atta",function(data){
		$("#attaFile_show").show();
		var prd='<div class="fl ptre"><button type="button" class="upload-file">'+data.originalName+'</button><span class="close-a"  data="'+data.attaId+'"></span></div>';
		$("#attaFile_show").append(prd);
		$(".close-a").unbind('click');
		removeAttaFile();
		attaIds=addStr(attaIds,data.attaId, ",",100);
		$("#attaIds").val(attaIds);
	});
	function removeAttaFile(){
		$(".close-a").bind('click',function(e){
            var thiss=$(this);
            var bthis=thiss.parent();
			var id=thiss.attr("data");
			upload.removeFile("project",function(url){
				jsonAjax.post({
					url:url,
					data:{attaId:id},
					success:function(drsp){
						if(drsp.result=="SUCCESS"){
							attaIds=subStr(attaIds,id, ",",100);
							bthis.remove();
							$("#attaIds").val(attaIds);
						}
					}
				});
			});
		});
	}
	//商业计划书上传
	removePlanBookFile();
	upload.uploadFile($("#planBookFile"),"project","project_planbook",function(data){
		$("#planBook_show").show();
		var prd='<div class="fl ptre"><button type="button" class="upload-file">'+data.originalName+'</button><span class="close-a"  data="'+data.attaId+'" id="pbimg"></span></div>';
		$("#planBook_show").html(prd);
		$("span[id=pbimg]").unbind('click');
		removePlanBookFile();
		$("#planBook_val").val(data.attaId);
	});
	function removePlanBookFile(){
		$("span[id=pbimg]").bind('click',function(e){
            var thiss=$(this);
            var bthis=thiss.parent();
			var id=thiss.attr("data");
			upload.removeFile("project",function(url){
				jsonAjax.post({
					url:url,
					data:{attaId:id},
					success:function(drsp){
						if(drsp.result=="SUCCESS"){
							$("#planBook_val").val('');
							bthis.remove();
						}
					}
				});
			});
		});
	}
	
	$("#saveBtn").click(function(){
		var projectSolution=$("#projectSolution").val();
		var name=$.trim($("#name").val());
		//var intro=$.trim($("#intro").val());
		var intro=UE.getEditor('editor').getContentTxt();
		$("#intro").val(UE.getEditor('editor').getContent());
		
		var recommendUser=$.trim($("#recommendUser").val());
		var startTime=$.trim($("#startTime").val());
		var expectTime=$.trim($("#expectTime").val());
		var contactName=$("#contactName").val();
		var contactPhone=$("#contactPhone").val();
		var referProject=$("#referProject").val();
		if(projectSolution==""){
			projectTips("solutionError", "请选择您需要的解决方案类型");
			offsetMove();
			return false;
		}else if(projectBudget==""){
			projectTips("budgetError", "请选择您项目的预算");
			offsetMove(200);
			return false;
		}else if(name==""){
			$("#name").focus(function(){$("#nameError").hide();});
			projectTips("nameError", "请填写您的项目名称");
			offsetMove(250);
			return false;
		}else if(!validateIllegalChar(name)){
			$("#name").focus(function(){$("#nameError").hide();});
			projectTips("nameError", "项目名称不能包含非法字符");
			offsetMove(250);
			return false;
		}else if(intro==""){
			projectTips("introError", "请输入项目的需求简介信息,字符控制在1000以内");
			offsetMove(400);
			return false;
		}else if(intro.length>1000){
			projectTips("introError", "请用1000个以内字符描述你的项目需求");
			offsetMove(400);
			return false;
		}else if(recommendUser!=""&&validateChinese(recommendUser)){
			$("#recommendUser").focus(function(){$("#remdError").hide();});
			projectTips("remdError", "请填写正确的推荐人手机号或邀请码");
			return false;
		}else if(startTime!=""&&expectTime!=""&&startTime>expectTime){
			projectTips("timeError", "交付日期不能低于开始日期");
			return false;
		}else if(contactName!=""&&!validateIllegalChar(contactName)){
			$("#contactName").focus(function(){$("#contactError").hide();});
			projectTips("contactError", "请填写正确的紧急联系人姓名");
			return false;
		}else if(contactPhone!=""&&!validateIllegalChar(contactPhone)){
			$("#contactPhone").focus(function(){$("#contactError").hide();});
			projectTips("contactError", "请填写正确的紧急联系方式");
			return false;
		}else if(referProject!=""&&!validateIllegalChar(referProject)){
			$("#referProject").focus(function(){$("#referError").hide();});
			projectTips("referError", "请填写正确的参考项目");
			return false;
		}else if(industryVal!=""&&industryVal.split(",").length>3){
			projectTips("indusError", "行业选择不能超过三个");
			return false;
		}
		jsonAjax.post({
			url:ctx+'/project/company/update',
			data:$("#releaseFrom").serialize(),
			success:function(data){
				if(data.result=="SUCCESS"){
					$(".complete-success").show();
					var i=3;
					$("#down_time").text(i+"s");
					setInterval(function(){
						i--;
						$("#down_time").text(i+"s");
						if(i==0){
							$("#enter_index").click();
						}
					}, 1000);
					$("#enter_index,.resu-btn").click(function(){
						window.location.href=ctx+'/member/index?'+jsonAjax.random();
					});
				}else{
					tostHint("项目发布失败",data.msg);
				}
			},
			error:function(data){
				$(this).removeAttr("disabled");
			}
		});
	});
	$("#canBtn").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	function projectTips(targetId,content){
		var target=$("#"+targetId);
		target.text(content);
		target.show();
	}
});