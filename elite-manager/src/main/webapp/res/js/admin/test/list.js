$(function(){
	list.init();
});
var list={
	init:function(){
		$("#add").click(list.add);
	},
	add:function(){
		location.href=ctx+'/user/add';
	}
};