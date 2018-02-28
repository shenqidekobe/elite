require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	
	$("#save").click(save);
	function save(){
		jsonAjax.post({
			url:ctx+'',
			data:$("#feedbackForm").serialize(),
			success:function(data){
				
			}
		});
	}
});
