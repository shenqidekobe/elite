require([ "jquery", "ajax", "jsonAjax"], function($) {
	
	var i=6;
	$("#down_time").text(i+"s");
	setInterval(function(){
		i--;
		$("#down_time").text(i+"s");
		if(i==0){
			$("#promptly_login").click();
		}
	}, 1000);
	$("#promptly_login").click(function(){
		window.location.href=ctx+'/login?'+jsonAjax.random();
	});
});