$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#create").click(list.create);
		$("#search").click(function() {
			// 搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		$("a[id=showByType]").click(function() {
			$("#pager_pageth").val(1);
			list.searchByType($(this).attr("data"))
		});
	},
	search : function() {
		
		jsonAjax.post({
			url : ctx + '/marketing/ad/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id=remove]").click(list.remove);
				$("a[id=update]").click(list.updateview);
				$("a[id=upmove]").click(list.upmove);
				$("a[id=downmove]").click(list.downmove);
				$("a[id='detail']").click(list.viewdetail);
				var type = $("#TypeId").val();
				if (null == type || "" == type) {
					$("#TypeId").val("launch");
				}
			}
		})
	},
	searchByType : function(type) {
		$("li[name=listTypeLiName]").removeClass();
		$(this).parent().addClass("active");
		$("#typeId").val(type);
		$("#dictTypeId").val(type);
		list.search();
	},
	remove : function() {
		var id = $(this).parent().attr("data");
		$.confirm({
			title : "提示",
			content : "你确定要删除",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/marketing/ad/remove',
					data : {
						id : id
					},
					success : function(data) {
						if ("SUCCESS" == data.result) {
							list.search();
						} else {
							$.tips({
								content : data.msg
							});
						}
					}
				});
			}
		});
	},
	updateview : function() {
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/marketing/ad/updateview',
			data : {
				id : id
			},
			success : function(data) {
				$("#showData").html(data);
				$('#myModel').modal('show');
				// 百度编辑器
				UE.delEditor('content');
				ueEditor = UE.getEditor('content', {
					initialFrameWidth : "100%",// 初始化选项
					toolbars : [ [ 'fullscreen', 'source', 'undo', 'redo', 'bold',
									'insertorderedlist', 'fontfamily', 'fontsize', 'simpleupload',
									'insertimage', 'selectall', 'cleardoc' ] ],
					initialFrameHeight : 100,
					initialFrameHeight : 400,
				})
				$("a[id='selectTypeId']").click(list.selectType);
				$("a[id='selectPlatTypeId']").click(list.selectPlatType);
				$("#btn_update_ad").click(list.updateSubmit);
				upload.uploadImg($("#logoIdFile"), "platformarketing", function(data) {
					$("#logoIdImgId").attr("src", data.url);
					$("#logoId").val(data.attaId);
				});
			}
		});
	},
	// 营销类型
	selectType : function() {
		var data = $(this).attr("data");
		var indata = $(this).attr("value");
		$("#selectTypeShowId").html(data);
		$("#selectTypeValueId").val(indata);
	},
	// 平台类型
	selectPlatType : function() {
		var data = $(this).attr("value");
		$("#selectPlatTypeShowId").html(data);
		$("#selectPlatTypeValueId").val(data);
	},
	// 角色类型
	selectRoleType : function() {
		var data = $(this).attr("data");
		var indata = $(this).attr("value");
		$("#selectRoleTypeShowId").html(data);
		$("#selectRoleTypeValueId").val(indata);
	},
	create : function() {
		var type=$("#typeId").val();
		jsonAjax.post({
			url : ctx + '/marketing/ad/addview',
			success : function(data) {
				$("#showData").html(data);
				$('#myModel').modal('show');
				// 百度编辑器
				UE.delEditor('content');
				ueEditor = UE.getEditor('content', {
					initialFrameWidth : "100%",// 初始化选项
					toolbars : [ [ 'fullscreen', 'source', 'undo', 'redo', 'bold',
									'insertorderedlist', 'fontfamily', 'fontsize', 'simpleupload',
									'insertimage', 'selectall', 'cleardoc' ] ],
					initialFrameHeight : 100,
					initialFrameHeight : 400,
				})
				
				var typelist=$("a[id='selectTypeId']");
				for(var i=0;i<typelist.length;i++){
					var selectVal=$(typelist[i]).attr("value");
					if(type==selectVal){
						$("#selectTypeShowId").html($(typelist[i]).attr("data"));
						break;
					}
				}
				$("a[id='selectTypeId']").click(list.selectType);
				$("a[id='selectPlatTypeId']").click(list.selectPlatType);
				$("a[id='selectRoleTypeId']").click(list.selectRoleType);
				$("#btn_submit_ad").click(list.createSubmit);
				$("#selectTypeValueId").val(type);
				upload.uploadImg($("#logoIdFile"), "platformarketing", function(data) {
					$("#logoIdImgId").attr("src", data.url);
					$("#logoId").val(data.attaId);
				});
			}
		})
	},
	viewdetail : function() {
		var id=$(this).attr("data");
		jsonAjax.post({
			url : ctx + '/marketing/ad/detail',
			data:{id:id},
			success : function(data) {
				$("#showData").html(data);
				$('#myModel').modal('show');
				// 百度编辑器
				UE.delEditor('content');
				ueEditor = UE.getEditor('content', {
					initialFrameWidth : "100%",// 初始化选项
					toolbars : [ [ 'fullscreen', 'source', 'undo', 'redo', 'bold',
									'insertorderedlist', 'fontfamily', 'fontsize', 'simpleupload',
									'insertimage', 'selectall', 'cleardoc' ] ],
					initialFrameHeight : 100,
					initialFrameHeight : 400,
				})
				
			}
		})
	},
	createSubmit : function() {
		var title=$("#titleId").val();
		var type=$("#selectTypeValueId").val();
		var ue=UE.getEditor('content');
		var content=ue.getContent();//s就是编辑器的带格式的内容
		if(title==null||title==""){
			$.tips({
				content : "请输入标题"
			});
			return;
		}else if(type==null||type==""){
			$.tips({
				content : "请选择类型"
			});
			return;
		}else if(content==null||content==""){
			$.tips({
				content : "请输入内容"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/marketing/ad/add',
			data : $("#createForm").serialize(),
			success : function(data) {
				if ("SUCCESS" == data.result) {
					$('#myModel').modal('hide');
					list.search();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	},
	updateSubmit : function() {
		var title=$("#titleId").val();
		var type=$("#selectTypeValueId").val();
		var ue=UE.getEditor('content');
		var content=ue.getContent();//s就是编辑器的带格式的内容
		if(title==null||title==""){
			$.tips({
				content : "请输入标题"
			});
			return;
		}else if(type==null||type==""){
			$.tips({
				content : "请选择类型"
			});
			return;
		}else if(content==null||content==""){
			$.tips({
				content : "请输入内容"
			});
			return;
		}
		jsonAjax.post({
			url : ctx + '/marketing/ad/update',
			data : $("#createForm").serialize(),
			success : function(data) {
				if ("SUCCESS" == data.result) {
					$('#myModel').modal('hide');
					list.search();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	},
	upmove : function() {
		list.move("up", $(this).parent().attr("data"))
	},
	downmove : function(moveType) {
		list.move("down", $(this).parent().attr("data"))
	},
	move : function(moveType, id) {
		jsonAjax.post({
			url : ctx + '/marketing/ad/move',
			data : {
				id : id,
				moveType : moveType
			},
			success : function(data) {
				if ("SUCCESS" == data.result) {
					list.search();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	}

};