$(function() {
	dept.init();
});
var zTree, rMenu;
var dept = {
	edit : false,
	index : 0,
	init : function() {
		zTree = $.fn.zTree.init($("#deptTree"), setting);
		rMenu = $("#rMenu");
	},
	filter : function(treeId, parentNode, childNodes) {
		if (!childNodes)
			return null;
		for (var i = 0, l = childNodes.length; i < l; i++) {
			childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
		}
		return childNodes;
	},
	OnRightClick : function(event, treeId, treeNode) {
		if (!treeNode && event.target.tagName.toLowerCase() != "button"
				&& $(event.target).parents("a").length == 0) {
			zTree.cancelSelectedNode();
			dept.showRMenu("root", event.clientX, event.clientY);
		} else if (treeNode && !treeNode.noR) {
			zTree.selectNode(treeNode);
			dept.showRMenu("node", event.clientX, event.clientY);
		}
	},
	showRMenu : function(type, x, y) {
		$("#rMenu ul").show();
		if (type == "root") {
			$("#m_del").hide();
			$("#m_upt").hide();
			$("#m_add").hide();
		} else {
			$("#m_del").show();
			$("#m_upt").show();
			$("#m_add").show();
		}
		rMenu.css({
			"top" : y + "px",
			"left" : x + "px",
			"visibility" : "visible"
		});
		$("body").bind(
				"mousedown",
				function(e) {
					if (!(e.target.id == "rMenu" || $(e.target)
							.parents("#rMenu").length > 0)) {
						rMenu.css({
							"visibility" : "hidden"
						});
					}
				});
	},
	addDept : function() {
		dept.edit = false;
		var node = zTree.getSelectedNodes()[0];
		index = node.getIndex();
		var organId = node.organId;
		var parentId = node.id;
		if (node.type == "organ") {
			parentId = "0";
		}
		if (parentId == "0")
			parentId = null;
		$("#organId").val(organId);
		$("#parentId").val(parentId);
		$("#deptId").val('');
		$("#name").val('');
		$("#deptModal").modal();
		dept.validate();
	},
	uptDept : function() {
		dept.edit = true;
		var node = zTree.getSelectedNodes()[0];
		index = node.getIndex();
		var organId = node.organId;
		var parentId = node.id;
		if (node.type == "organ") {
			parentId = "0";
		}
		if (parentId == "0")
			parentId = null;
		var name = node.name;
		var id = node.id;
		$("#deptId").val(id);
		$("#name").val(name);
		$("#organId").val(organId);
		$("#parentId").val(parentId);
		$("#deptModal").modal();
		dept.validate();
	},
	delDept : function() {
		var node = zTree.getSelectedNodes()[0];
		var id = node.id;
		if (node.type == "organ") {
			return false;
		}
		$.confirm({
			title : "提示",
			content : "你确定要删除此部门嘛",
			iconSmall : "icon-warning-sign",
			callback : function() {
				jsonAjax.post({
					url : ctx + '/settings/dept/remove',
					data : {
						deptId : id
					},
					success : function(data) {
						if (BASE.JS_RESULT.SUCCESS == data.result) {
							var nodes = zTree.getSelectedNodes();
							zTree.removeNode(nodes[0]);
						} else {
							$.tips({
								content : data.msg
							});
						}
						$("#rMenu").css({ "visibility" : "hidden"});
					}
				});
			}
		});
	},
	validate : function() {
		$("#deptForm").validate({
			debug : false,
			rules : {
				name : {
					required : true,
					minlength : 2,
					maxlength : 16,
					remote : {
						url : ctx + "/settings/dept/check/deptName",
						type : "Post",
						data : {
							id : function() {
								return $("#deptId").val();
							},
							organId : function() {
								return $("#organId").val();
							}
						}
					}
				},
			},
			messages : {
				name : {
					required : '请输入部门名称',
					minlength : '部门名称只允许4~16个字符',
					maxlength : '部门名称只允许4~16个字符',
					remote : '部门名称已存在',
				},
			},
			submitHandler : function(form) {
				dept.formSumbit();
			}
		});
	},
	formSumbit : function() {
		jsonAjax.post({
			url : ctx + '/settings/dept/save',
			data : $("#deptForm").serialize(),
			success : function(data) {
				if (data.id) {
					$('#deptModal').modal('hide');
					if (dept.edit) {
						// UPDATE SELECT NODES
						var nodes = zTree.getSelectedNodes();
						if (nodes.length > 0) {
							var node = nodes[0];
							node.name = data.name;
							zTree.updateNode(node);
						}
					} else {
						var newNode = {
							id : data.id,
							isParent : false,
							level : 2,
							name : data.name,
							open : false,
							organId : data.organId,
							parentId : data.parentId,
							type : "dept"
						};
						zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
					}
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		});
	}
}
var setting = {
	treeNodeKey : "id",
	treeNodeParentKey : "parentId", // 设置节点的父节点唯一标识属性名称
	nameCol : "name",
	async : {
		enable : true,
		url : ctx + "/settings/dept/listData",
		autoParam : [ "id=parentId", "organId=organId" ],
		dataFilter : dept.filter
	},
	data : {
		simpleData : {
			enable : true,
			idKey : "id",
			pIdKey : "parentId",
			rootPId : 0
		}
	},
	callback : {
		onRightClick : dept.OnRightClick
	}
};