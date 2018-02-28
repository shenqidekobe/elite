require([ "jquery", "ajax", "jsonAjax","commons","bootstrap-datepicker","webupload"], function($) {
	//选择行业分类
	var industryVal=$("#attentionIndustry").val();
	var projectCount=0;
	$(".select-box button").click(function(){
		var thiss=$(this);
		//不得多于三个
		if($.trim($("#attentionIndustry").val()).split(",").length < 3){
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
			}
		}
		$("#attentionIndustry").val(industryVal);
	});
	//关注行业回显

	if($("#attentionIndustry").val()!=""){
		$(".select-box button").each(function(){
			var strs = $("#attentionIndustry").val().split(",");
			for(var i=0;i<strs.length;i++){
				if($(this).attr("data") == strs[i]){
					$(this).addClass("active-btn");
				}
			}
		});
	}
	
	//项目经历添加和修改
	$("#add_project").click(function(){
		$("#addProjectForm")[0].reset();
		$("#addProjectForm #project_id").val('');
		$("#addProjectDialog").modal({backdrop: 'static', keyboard: false});
		//$('body').css({"overflow":"hidden"});
		//$("body").css('padding-right','0px');
		$('#startTime,#startTimeIcon').datepicker({
			format:"yyyy-mm",
			endDate:new Date(),
	        autoclose: true,
	        startView: 'year',
	        minViewMode:'months'
		}).on("changeDate",function(e){
	    	var time=e.date;
	    	$("#startTime").val(time.Format("yyyy-MM"));
	    });
		$('#endTime,#endIcon').datepicker({
			format:"yyyy-mm",
			endDate:new Date(),
	        autoclose: true,
	        startView: 'year',
	        minViewMode:'months'
	    }).on("changeDate",function(e){
	    	var time=e.date;
	    	$("#endTime").val(time.Format("yyyy-MM"));
	    });
		$("#save_project_btn").unbind('click');
		$("#save_project_btn").click(saveProject);
	});
	//项目经历回显
	jsonAjax.post({
		url:ctx+'/member/project/list',
		success:function(rsp){
			var arrs=rsp.data;
			for(var i in rsp.data){
				var obj=arrs[i];
				var id=obj.id;
				var project=obj.project;
				var position=obj.position;
				var tm=obj.startTime.substring(0,7)+' ~ '+obj.endTime.substring(0,7);
				
				if(project.length>8){
					project=project.substring(0,8);
				}
				if(position.length>6){
					position=position.substring(0,6);
				}
				var oper='<div class="oper-new" style="line-height: 30px;margin-top:15px;" data="'+id+'"><img src='+ctx+'"/res/images/edit_icon.png" class="opt-icon" id="edit_project"><img src='+ctx+'"/res/images/delete_icon.png" class="opt-icon" id="del_project"></div>';
				var info='<div class="append-tr-new" id="apt_'+id+'"><span>'+project+'</span><span style="color:#2cb7c9">|</span><span>'+position+'</span><span class="work-period">'+tm+'</span>'+oper+'</div>';
				$("#add_project_info").before(info);
				//$("#add_project_oper").append(oper);
				$("img[id=edit_project]").click(editProject);//编辑项目经验
				$("img[id=del_project]").click(delProject);//删除项目经验
			}
		}
	});
	function saveProject(){
		
		var project=$.trim($("#addProjectForm #project").val());
		var position=$.trim($("#addProjectForm #position").val());
		var startTime=$.trim($("#addProjectForm #startTime").val());
		var endTime=$.trim($("#addProjectForm #endTime").val());
		var intro=$.trim($("#addProjectForm #intro").val());
		var tips=$("#addProjectForm .error-tip");
	
		tips.text('');
		if(project==""){
			tips.text("请填写项目名称");
			return false;
		}else if(position==""){
			tips.text("请填写你所担任的职位");
			return false;
		}else if(startTime==""){
			tips.text("请填写项目开始时间");
			return false;
		}else if(endTime==""){
			tips.text("请填写项目结束时间");
			return false;
		}else if(endTime<startTime){
			tips.text("项目结束时间必须大于项目开始事件");
			return false;
		}else if(new Date() < Date.parse(new Date(endTime.replace(/-/g, "/")))){
			tips.text("项目结束时间必须小于当前时间");
			return false;
		}else if(new Date() < Date.parse(new Date(startTime.replace(/-/g, "/")))){
			tips.text("项目开始时间必须小于当前时间");
			return false;
		}else if(intro==""){
			tips.text("请填写项目描述");
			return false;
		}else if(intro!=""&&intro.length>500){
			tips.text("项目描述请控制在500字符以内");
			return false;
		}
		jsonAjax.post({
			url:ctx+'/member/project/save',
			data:$("#addProjectForm").serialize(),
			success:function(data){
				if(data!=null&&data.id!=null){
					var tm=startTime.substring(0,7)+' ~ '+endTime.substring(0,7);
					if(project.length>8){
						project=project.substring(0,8);
					}
					if(position.length>6){
						position=position.substring(0,6);
					}
					var oper='<div class="oper-new" style="line-height: 30px;margin-top:15px;" data="'+data.id+'"><img src='+ctx+'"/res/images/edit_icon.png" class="opt-icon" id="edit_project"><img src='+ctx+'"/res/images/delete_icon.png" class="opt-icon" id="del_project"></div>';
					var info='<div class="append-tr-new" id="apt_'+data.id+'"><span>'+project+'</span><span style="color:#2cb7c9">|</span><span>'+position+'</span><span class="work-period">'+tm+'</span>'+oper+'</div>';
				    
					if($("#addProjectForm #project_id").val()==""){
				    	$("#add_project_info").before(info);
					   // $("#add_project_oper").append(oper);
					    projectCount++;
					    var val = $("#project_count").val();
					    $("#project_count").val(++val);
					   
				    }else{
				    	var aptId="apt_"+data.id;
				    	$("#"+aptId+" >span:eq(0)").text(project);
				    	$("#"+aptId+" >span:eq(2)").text(position);
				    	$("#"+aptId+" >span:eq(3)").text(tm);
				    }
				    $("#addProjectDialog").modal('hide');
				    $("img[id=edit_project]").unbind('click');
				    $("img[id=del_project]").unbind('click');
				    $("img[id=edit_project]").click(editProject);//编辑项目经验
				    $("img[id=del_project]").click(delProject);//删除项目经验
				}
			}
		});
	}
	function editProject(){
		var id=$(this).parent().attr("data");
		if(id==""){
			return false;
		}
//		var project=$.trim($("#addProjectForm #project").val());
//		var position=$.trim($("#addProjectForm #position").val());
//		var startTime=$.trim($("#addProjectForm #startTime").val());
//		var endTime=$.trim($("#addProjectForm #endTime").val());
//		var intro=$.trim($("#addProjectForm #intro").val());
//		var tips=$("#addProjectForm .error-tip");
//		tips.text('');
//		if(project==""){
//			tips.text("请填写项目名称");
//			return false;
//		}else if(position==""){
//			tips.text("请填写你所担任的职位");
//			return false;
//		}else if(startTime==""){
//			tips.text("请填写项目开始时间");
//			return false;
//		}else if(endTime==""){
//			tips.text("请填写项目结束时间");
//			return false;
//		}else if(endTime<startTime){
//			tips.text("项目结束时间必须大于项目开始事件");
//			return false;
//		}else if(new Date() < Date.parse(new Date(endTime.replace(/-/g, "/")))){
//			tips.text("项目结束时间必须小于当前时间");
//			return false;
//		}else if(new Date() < Date.parse(new Date(startTime.replace(/-/g, "/")))){
//			tips.text("项目开始时间必须小于当前时间");
//			return false;
//		}else if(intro==""){
//			tips.text("请填写项目描述");
//			return false;
//		}else if(intro!=""&&intro.length>500){
//			tips.text("项目描述请控制在500字符以内");
//			return false;
//		}
		jsonAjax.post({
			url:ctx+'/member/project/'+id,
			data:$("#addProjectForm").serialize(),
			success:function(data){
				$("#addProjectForm")[0].reset();
				$("#addProjectDialog").modal({backdrop: 'static', keyboard: false});
				$("#addProjectForm #project").val(data.project);
				$("#addProjectForm #position").val(data.position);
				$("#addProjectForm #startTime").val(data.startTime.substring(0,7));
				$("#addProjectForm #endTime").val(data.endTime.substring(0,7));
				$("#addProjectForm #intro").val(data.intro);
				$("#addProjectForm #project_id").val(data.id);
				$("#save_project_btn").unbind('click');
				$("#save_project_btn").click(saveProject);
			}
		});
	}
	function delProject(){
		
		var thiss=$(this);
		var id=thiss.parent().attr("data");
		if(id==""){
			return false;
		}
		tostConfirm("确定要删除吗？",function(){
			jsonAjax.post({
				url:ctx+'/member/project/remove',
				data:{id:id},
				success:function(data){
					if(data.result=="SUCCESS"){
						$("#apt_"+id).remove();
						thiss.siblings("img").remove();
						thiss.remove();
					}
					$("#optDialog").modal('hide');
					projectCount--;
					 var val = $("#project_count").val();
					  $("#project_count").val(--val);
				}
			});
		});
	}
	//上传简历
	
	upload.uploadFile($("#resumeAttaFile"),"elite","resume_atta",function(data){
		$("#resumeAtta_show").show();
		var prd='<button type="button" class="upload-file">'+data.originalName+'</button><img src='+ctx+'"/res/images/del.png" data="'+data.attaId+'" id="pbimg" class="delete-cross">';
		$("#resumeAtta_show").html(prd);
		$("img[id=pbimg]").unbind('click');
		$("img[id=pbimg]").bind('click',function(e){
            var thiss=$(this);
            var bthis=thiss.prev();
			var id=thiss.attr("data");
			upload.removeFile("resume_atta",function(url){
				jsonAjax.post({
					url:url,
					data:{attaId:id},
					success:function(drsp){
						if(drsp.result=="SUCCESS"){
							$("#resumeAttaId").val('');
							thiss.remove();
							bthis.remove();
						}
					}
				});
			});
		});
		$("#resumeAttaId").val(data.attaId);
	});
	//简历回显
	
	
	if($("#resumeAttaId").val()!=""){
		var strs = $("#atta").val().split(",");
		var prd='<button type="button" class="upload-file">'+strs[1]+'</button><img src='+ctx+'"/res/images/del.png" data="'+strs[0]+'" id="pbimg" class="delete-cross">';
		$("#resumeAtta_show").html(prd).show();
		$("img[id=pbimg]").unbind('click');
		$("img[id=pbimg]").bind('click',function(e){
            var thiss=$(this);
            var bthis=thiss.prev();
			var id=thiss.attr("data");
			upload.removeFile("resume_atta",function(url){
				jsonAjax.post({
					url:url,
					data:{attaId:id},
					success:function(drsp){
						if(drsp.result=="SUCCESS"){
							$("#resumeAttaId").val('');
							thiss.remove();
							bthis.remove();
						}
					}
				});
			});
		});
	}
	
	
	
	//下一步
	$("#saveBtn").click(function(){
		var attentionIndustry=$.trim($("#attentionIndustry").val());
		var intro=$.trim($("#intro").val());
		if(attentionIndustry==""){
			tips("attentionIndustry","选择关注行业");
			return false;
		}else if($("#project_count").val()<1){
			tips("project","请添加项目经历");
			return false;
		}else if(intro!=""&&intro.length>2000){
			tips("intro","自我描述请控制在2000字符以内");
			return false;
		}
		jsonAjax.post({
			url:ctx+'/member/elite/save',
			data:$("#personFrom").serialize(),
			success:function(data){
				if(data.result=="SUCCESS"){
					window.location.href=ctx+'/member/credit?'+jsonAjax.random();
				}else{
					tips("project",data.msg);
				}
			}
		});
	});
	$("#skip").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#prevBtn").click(function(){
		window.location.href=ctx+'/member/basic?'+jsonAjax.random();
	});
});