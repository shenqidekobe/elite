$(function(){
	edit.init();
});
var edit={
	init:function(){
		$("#save").click(edit.save);
	},
	save:function(){
		var phone=$("#phone").val();
		if(phone==""){
			alert('请输入电话');
			return false;
		}
		$.ajax({
			url:ctx+'/user/save',
			type:'POST',
			data:$('#form1').serialize(),
			success:function(rsp){
				console.info(rsp);
				location.href=ctx+'/user/list';
			}
		});
	}
};