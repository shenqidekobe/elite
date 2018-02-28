$(function() {
	
});
var selectDept = {
	init : function() {
		$.fn.zTree.init($("#deptTree"), setting);
	},
    filter:function(treeId, parentNode, childNodes) {
        if (!childNodes) return null;
        for (var i=0, l=childNodes.length; i<l; i++) {
            childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
        }
        return childNodes;
    },
    beforeClick:function(treeId, treeNode) {
		return true;
	},
	onClick:function(e, treeId, treeNode) {
		var zTree = $.fn.zTree.getZTreeObj("deptTree"),
		nodes = zTree.getSelectedNodes(),
		v = "";
		id="";
		nodes.sort(function compare(a,b){return a.id-b.id;});
		for (var i=0, l=nodes.length; i<l; i++) {
			v += nodes[i].name + ",";
			id += nodes[i].id + ",";
		}
		if (v.length > 0 ) v = v.substring(0, v.length-1);
		if (id.length > 0 ) id = id.substring(0, id.length-1);
		$("#deptName").val(v);
		$("#deptId").val(id);
	}
}
var setting = {
	treeNodeKey : "id",
	treeNodeParentKey : "parentId", //设置节点的父节点唯一标识属性名称
	nameCol : "name",
	async : {
		enable : true,
		url : ctx + "/settings/dept/listData",
		autoParam : [ "id=parentId", "organId=organId" ],
		dataFilter: selectDept.filter
	},
	data:{
		simpleData: {
			enable:true,
			idKey: "id",
			pIdKey: "parentId",
			rootPId: 0
		}
	},
	callback: {
		beforeClick: selectDept.beforeClick,
		onClick: selectDept.onClick
	}
};