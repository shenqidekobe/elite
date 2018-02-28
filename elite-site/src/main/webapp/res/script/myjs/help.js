require([ "jquery", "ajax", "jsonAjax", "commons" ], function($) {

	$("#commonIssue li").click(function() {
		$("#commonIssue li").removeClass("active_li");
		$(this).addClass("active_li");
		var data = $(this).attr("data");
		if (data == "common") {
			$("#commonHelp").show();
			$("#projectHelp").hide();
			$("#eliteHelp").hide();
			$("#channelHelp").hide();
		} else if (data == "project") {
			$("#projectHelp").show();
			$("#commonHelp").hide();
			$("#eliteHelp").hide();
			$("#channelHelp").hide();
		} else if (data == "elite") {
			$("#eliteHelp").show();
			$("#commonHelp").hide();
			$("#projectHelp").hide();
			$("#channelHelp").hide();
		} else if (data == "channel") {
			$("#channelHelp").show();
			$("#commonHelp").hide();
			$("#projectHelp").hide();
			$("#eliteHelp").hide();
		}
	});
	
	 //帮助中心点击显示隐藏
    $(".group_show").click(function(){

        var display=$(this).next().css("display");
        $(this).parent().siblings().find(".group_show").children(".show_icon").removeClass("hide_img").addClass("show_img");
        $(this).parent().siblings().find(".group_show").children(".show_text").removeClass("active_color");
        $(this).parent().siblings().children(".group_hide").hide();
        $(this).parent().siblings().removeClass("group_bg");
        if (display=="none"){
            $(this).children(".show_icon").removeClass("show_img").addClass("hide_img");
            $(this).children(".show_text").addClass("active_color");
            $(this).next().show();
            $(this).parent().addClass("group_bg");
        }else {
        	$(this).children(".show_icon").removeClass("hide_img").addClass("show_img");
        	$(this).children(".show_text").removeClass("active_color");
            $(this).next().hide();
            $(this).parent().removeClass("group_bg");
        }
    });
    
    //回调处理
    var rps=getUrlParams("rps");
	if("channel"==rps){
		 $("#headNav li[data=help]").addClass("active_color").siblings().removeClass("active_color");
		 
		$("#commonIssue li:eq(3)").click();
		
		//绑定头部事件
		 $('#headNav li').click(function(){
			 var data=$(this).attr("data");
			 if(data!='help'){
				 window.location.href=ctx+'register/partner?rps='+data;
			 }
		 });
	}
	
});
