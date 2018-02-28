function initProjectMaterial(taskId){
	loadMaterial(function(){
		bindEvent(0);
	});
}
function bindEvent(i){
	//项目阶段选择点击事件
	var stageId = '';
	$("#stageBudget,.triangle").click(function() {
		$(".budget").fadeIn();
		$(".budget li").click(function() {
			var thiss = $(this);
			var pts = thiss.text();
			$(".budget").fadeOut("fast");
			stageId = thiss.attr("data");
			$("#stageId").val(stageId);
			$("#stageBudget").val(pts);
			loadMaterialList();
		});
		$("body").bind("mousedown", function(event) {
			$(".budget").fadeOut("fast");
			$("body").unbind("mousedown");
		});
	});
	
	// 状态切换
	$("#material_list_option .tab").click(function() {
		$("#material_list_option .tab").removeClass("active-tab");
		$(this).addClass("active-tab");
		var qt = $(this).attr("data");
		$("#queryType").val(qt);
		loadMaterialList();
	});

	
	// 文件上传
	upload.uploadFile($("#materialFile"), "project", "material_atta",function(data) {
		//$("#attaFile_show").show();
		$(".modal-backdrop").hide();
		saveProjectMaterial(data.attaId);
	});
	$("#material_list_option .tab:eq("+i+")").click();
}
// 保存上传的文件
function saveProjectMaterial(attaId) {
	var projectId=$("#projectId").val();
	var stageId=$("#stage").val();
	var receiveId=$("#receiveId").val();
	var taskId=$("#taskId").val();
	var params = {
		projectId : projectId,
		stageId : stageId,
		attaId : attaId,
		receiveId:receiveId,
		taskId:taskId,
		status : 'pass',
		isDelivery : false
	};
	console.info(params);
	jsonAjax.post({
		url : ctx + '/task/material/save',
		data : params,
		success : function(data) {
			loadMaterial(function(){
				bindEvent(1);
			});
		}
	});
}
// 删除上传的文件
function removeProjectMaterial(e) {
	var id=$(this).attr("data");
	var attaId=$(this).attr("attaId");
	tostConfirm("确定要删除吗？",function(){
		jsonAjax.post({
			url : ctx + '/project/r/material/remove',
			data : {id : id},
			success : function(data) {
				if(data.result=="SUCCESS"){
					upload.removeFile("project",function(url){
						jsonAjax.post({
							url:url,
							data:{attaId:attaId},
							success:function(drsp){
								if(drsp.result=="SUCCESS"){
									loadMaterial(function(){
										bindEvent(1);
									});
								}
							}
						});
					});
				}else{
					tostHint("删除失败",data.msg);
				}
			}
		});
		e.stopPropagation();
	});
}
function loadMaterial(callback){
	var tid=$("#taskId").val();
	jsonAjax.post({
		url:ctx+'/task/material',
		data:{taskId:tid},
		success:function(data){
			$("#dataList").html(data);
			if(callback){
				callback();
			}
		}
	});
}
//加载文件列表
function loadMaterialList() {
	jsonAjax.post({
		url : ctx + '/task/material/listData',
		data : $("#materialListForm").serialize(),
		success : function(data) {
			$("#dataSecondList").html(data);
			$("img[id^=download]").click(downloadMaterial);
			$("img[id=delMaterial]").click(removeProjectMaterial);
		}
	});
}
//下载
function downloadMaterial(){
	var id=$(this).attr("data");
	var href=ctx+'/project/c/read/material/'+id;
	window.open(href);
	var selectType=$(".active-tab").attr("data");
	if(selectType=="unread"){
		var unreadCount=$("#unreadCount").text();
		$("#unreadCount").text((parseInt(unreadCount)-1));
	}
	$(".left-menu li:eq(2)").click();
}
