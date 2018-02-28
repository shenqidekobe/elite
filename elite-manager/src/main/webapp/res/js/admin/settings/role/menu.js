/** 菜单权限分配* */

var treeData = [];
var dic_treeData = {};
var roleId;
var busy = false;
function getAllPowers(callback) {
	$.ajax({
		type : "POST",
		url : ctx+'/settings/role/menus',
		async : false,
		error : function(request) {
		},
		dataType : 'json',
		success : function(data) {
			callback(data);
		}
	});
}
function getPowers(id, callback) {
	var param = {};
	if (id != null)
		param.roleId = id;
	$.ajax({
		type : "POST",
		url : ctx+'/settings/role/roleMenus',
		async : false,
		error : function(request) {
		},
		dataType : 'json',
		data : $.param(param),
		success : function(data) {
			callback(data);
		}
	});
}
function initTree() {
	var jtree = $('#dlg_right').find('div[name="jstree"]');
	getAllPowers(function(data) {
		var rows = data;
		console.info(rows);
		if (rows != null && rows.length > 0) {
			var list = [];
			for (var i = 0; i < rows.length; i++) {
				var item = rows[i];
				item.text = item.name;
				dic_treeData[item.id] = item;
				if (item.parentId == null || parseInt(item.parentId) == 0) {
					list.push(item);
				} else {
					var parents = dic_treeData[item.parentId];
					if (parents != null) {
						var children = parents.children;
						if (children == null) {
							children = parents.children = [];
						}
						children.push(item);
					}
				}
			}
			treeData = [ {
				id : 0,
				parentId : 0,
				text : '全选',
				children : list,
				'state' : {
					'opened' : true,
					'selected' : false
				}
			} ];
			jtree.jstree({
				// "plugins": ["checkbox"],
				"checkbox" : {
					"keep_selected_style" : false,
					'whole_node' : true
				},
				'core' : {
					data : treeData,
					themes : {
						dots : false
					}
				},
				"types" : {
					"#" : {
						"max_children" : 1,
						"max_depth" : 4,
						"valid_children" : [ "root" ]
					},
					"root" : {
						"icon" : ctx+"/res/img/tree_icon.png",
						"valid_children" : [ "default" ]
					},
					"default" : {
						"valid_children" : [ "default", "file" ]
					},
					"file" : {
						"icon" : "glyphicon glyphicon-file",
						"valid_children" : []
					}
				},
				"plugins" : [ "checkbox" ]
			});
			jtree.parent().slimScroll({
				height : jtree.parent().height() - 30,
				railVisible : true
			});
		}
	})
	$('#dlg_right')
			.find('button[name="btn_sub"]')
			.click(
					function() {
						if (busy)
							return;
						var ids = jtree.jstree('get_checked');
						if (ids == null || ids.length == 0) {
							$.tips({content:'您至少要选择一项'});
						} else {
							busy = true;
							var tdic = {};

							function findParentIds(id, tdic) {
								var item = dic_treeData[id];
								tdic[id] = 1;
								if (item != null && item.parentId != null
										&& parseInt(item.parentId) != 0) {
									tdic[item.parentId] = 1;
									findParentIds(item.parentId, tdic);
								}
							}

							for (var k = 0; k < ids.length; k++) {
								if (ids[k] == 0 || isNaN(ids[k]))
									continue;
								findParentIds(ids[k], tdic);
							}
							var ps = [];
							for ( var j in tdic) {
								ps.push(j);
							}
							ps = ps.sort(function(x, y) {
								return parseInt(x) - parseInt(y);
							});
							var param = {
								roleId : roleId,
								menuIds : ps.join(',')
							};
							var maskLayer = $('#dlg_right .maskLayer').show();
							var btn = $(this);
							btn
									.html('<i class="ace-icon fa fa-spinner fa-spin blue bigger-100"></i>保存中');
							btn.addClass('disabled');
							console.info(param);
							jsonAjax.post({
								url : ctx+'/settings/role/saveMenus',
								async : true,
								error : function(request) {
									busy = false;
									$.tips({content:'保存失败'});
									btn.html('<i class="ace-icon fa fa-check"></i>保存');
									btn.removeClass('disabled');
									maskLayer.hide();
								},
								data : $.param(param),
								dataType : 'html',
								success : function(rsp) {
									var data=rsp;
									busy = false;
									maskLayer.hide();
									btn.removeClass('disabled');
									btn.html('<i class="ace-icon fa fa-check"></i>保存');
									if (BASE.JS_RESULT.SUCCESS==data.result) {
										$('#dlg_right').modal('hide');
										$.tips({content:'保存成功'});
									} else {
										$.tips({content:'保存失败'});
									}
								}
							});
						}
					})
}

initTree();
function showRightDialog(id) {
	roleId = id;
	getPowers(roleId, function(data) {
		busy = false;
		var rows = data;
		if (rows != null && rows.length > 0) {
			$.jstree.reference('#dlg_right div[name="jstree"]').deselect_all();
			$.jstree.reference('#dlg_right div[name="jstree"]').close_all();
			var list = [];
			for (var i = 0; i < rows.length; i++) {
				var item = rows[i];
				item = dic_treeData[item.id];
				if (item.children == null || item.children.length == 0) {
					$.jstree.reference('#dlg_right div[name="jstree"]')
							.select_node(item.id, false, false);
				}
			}
		} else {
			var children = treeData[0].children;
			for (var i = 0; i < children.length; i++) {
				var item = children[i];
				$.jstree.reference('#dlg_right div[name="jstree"]').close_node(
						item.id);
			}
			$.jstree.reference('#dlg_right div[name="jstree"]').deselect_all();
		}
	});
	$('#dlg_right').modal({
		backdrop : "static"
	});
}