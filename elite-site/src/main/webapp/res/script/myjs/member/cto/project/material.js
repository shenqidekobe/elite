function initProjectMaterial(projectId){
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

	
	$("#sendFile").click(function() {
		if($('#selectMember').val()==''){
			$('#tip').text("请输入要发送的人");
			return false;
		}
		$("#selectFile").click();
	});
	 
	 //发送人联想
	 function split( val ) {
		 return val.split( /,\s*/ );
     }
	 
     function extractLast( term ) {
     	return split( term ).pop();
     }
	
	$( "#memberName" )
      // 当选择一个条目时不离开文本域
      .bind( "keydown", function( event ) {
        if ( event.keyCode === $.ui.keyCode.TAB &&
            $( this ).data( "ui-autocomplete" ).menu.active ) {
	          event.preventDefault();
	        }
      	}).autocomplete({
        source: function( request, response ) {
        	var keyWord = extractLast( request.term );
        	if(keyWord.length>1 && keyWord.substring(0,1)=="@"){
        		keyWord=keyWord.substring(1,keyWord.length);
        	}
        	jsonAjax.post({
              url: ctx+"/project/c/sendFileMember",
              dataType: "jsonp",
              data: {
            	  keyWord:keyWord,
                  projectId:$("#projectId").val()
              },
              success: function( msg ) {
                response( $.map( msg.data, function( item ) {
                  return {
                    label: item.label,
                    value: item.value,
                    name: item.name,
                    account: item.account,
                    desc:item.desc
                  }
                }))
              }
          });  
        },
        search: function() {
          // 自定义最小长度
          var term = extractLast( this.value );
        },
        focus: function() {
          // 防止在获得焦点时插入值
          return false;
        },
        select: function( event, ui ) {
          var terms = split( this.value );
          // 移除当前输入
          terms.pop();
          this.value = ui.item.name;
          $("#selectMember").val(ui.item.value);
          $("#memberId").val(ui.item.value);
          $("#role").val(ui.item.desc);
          return false;
        }
      }).data( "ui-autocomplete" )._renderItem = function( ul, item ) {
		  var desc =''
		  if(item.desc=='elite'||item.desc=='cto'){
			  desc="精英";
		  }else if(item.desc=='company'){
			  desc="项目方"
		  }
          return $( "<li>" )
          .append( "<a>"+desc + "&nbsp;&nbsp;"+ item.name + "&nbsp;&nbsp;&nbsp;&nbsp;" + item.account + "</a>" )
          .appendTo( ul );
      };
	
  	// 文件上传
  	upload.uploadFile($("#materialFile"), "project", "material_atta",function(data) {
  		//$("#attaFile_show").show();
  		$(".modal-backdrop").hide();
  		saveProjectMaterial(data.attaId);
  	});

	
	upload.uploadFile($("#selectFile"), "project", "material_atta",function(data) {
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
	var memberId=$("#memberId").val();
	var status = 'wait_audit';
	if($("#role").val()=='elite'){
		status = 'pass';
	}
	var params = {
		projectId : projectId,
		stageId : stageId,
		attaId : attaId,
		receiveId:memberId,
		status : status,
		isDelivery : false
	};
	jsonAjax.post({
		url : ctx + '/project/c/material/save',
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

function downloadMaterial(){
	var id=$(this).attr("data");
	var href=ctx+'/project/c/read/material/'+id;
	window.open(href);
	var selectType=$(".active-tab").attr("data");
	if(selectType=="unread"){
		var unreadCount=$("#unreadCount").text();
		$("#unreadCount").text((parseInt(unreadCount)-1));
	}
	$(".left-menu li:eq(3)").click();
}

function loadMaterial(callback){
	var pid=$("#projectId").val();
	jsonAjax.post({
		url:ctx+'/project/c/material',
		data:{projectId:pid},
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
		url : ctx + '/project/c/material/listData',
		data : $("#materialListForm").serialize(),
		success : function(data) {
			$("#dataSecondList").html(data);
			$("img[id=delMaterial]").click(removeProjectMaterial);
			$("img[id^=download]").click(downloadMaterial);
		}
	});
}

