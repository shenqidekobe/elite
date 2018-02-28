require([ "jquery", "ajax", "jsonAjax", "md5","commons" ], function($) {
	
	//图片首屏功能
	window.onload = function () {
		FullScreenBackground();
	};
	$(window).resize(function() {
		FullScreenBackground();
	});
	function FullScreenBackground(){
		var winHeight=$(window).height();
		$("#companyBg1").css({"height": winHeight});
		$("#li_main1").css({"height": winHeight});
		$("#personBg1").css({"height": winHeight});
		$("#personli_main1").css({"height": winHeight});
	}
	
	
	// 身份选择
	$("#partnerCompanyBtn").click(function() {
		 window.location.href = ctx + '/register/c';
	});
	
     $("#partnerCompanyLoginBtn").click(function() {
    	 window.location.href = ctx + '/introduce/partnerCompany?'+jsonAjax.random();
	 });
     
      $("#partnerEliteBtn").click(function() {
		 window.location.href = ctx + '/register/e';
	  });
      
	 $("#partnerEliteLoginBtn").click(function() {
		window.location.href = ctx + '/introduce/partnerElite?'+jsonAjax.random();
	 });
	 
	//标题切换
	    $('#headNav li').click(function(){
	        $(this).addClass("active_color").siblings().removeClass("active_color");
	        var data=$(this).attr("data");
	        if (data=="company"){
	           $('#company_channel').show();
	           $('#person_channel').hide();
	           offset("li1");
	           ClickScreenBackground1()
	        }
	        else if (data=="person"){
	        	$('#company_channel').hide();
		        $('#person_channel').show();
		        offset("li1_person");
		        ClickScreenBackground5()
	        }
	        else if (data=="help"){
	        	 window.location.href = ctx + '/help?rps=channel';
	        }
	    });
		   var rps=getUrlParams("rp");
		    if(rps!=null&&rps!=""){
		     if (rps=="elite"){
		    		$('#headNav li:eq(1)').click();
			    
			}
		    }
	    //项目渠道首页点击切换
	    $("#pointUl li").bind("click",function(){
	        var data=$(this).attr("data");
	        $(this).addClass("li_active").siblings().removeClass("li_active");
	        if (data=="point1"){
	            $("#headLogin").hide();
	            offset("li1");
	            ClickScreenBackground1()
	        }
	        else if (data=="point2"){
	            $("#headLogin").show();
	            offset("li2");
	            ClickScreenBackground2()
	 
	        }
	        else if (data=="point3"){
	            $("#headLogin").show();
	            offset("li3");
	            ClickScreenBackground3()
	            
	        }
	        else if (data=="point4"){
	            $("#headLogin").show();
	            offset("li4");
	            ClickScreenBackground4()
	           
	        }
	    });
	    
	    
	    //向下按钮1
	    $("#downBtn1").bind("click",function(){
	        $("#headLogin").show();
	        $("#point2").addClass("li_active").siblings().removeClass("li_active");
	        offset("li2");
	        ClickScreenBackground2()
	    });
	    //向下按钮2
	    $("#downBtn2").bind("click",function(){
	        $("#headLogin").show();
	        $("#point3").addClass("li_active").siblings().removeClass("li_active");
	        offset("li3");
	        ClickScreenBackground3()
	    });
	    //向下按钮3
	    $("#downBtn3").bind("click",function(){
	        $("#headLogin").show();
	        $("#point4").addClass("li_active").siblings().removeClass("li_active");
	        offset("li4");
	        ClickScreenBackground4()
	        
	    });

	    //窗口滚动
	    $(window).scroll(function(){
	        var top=$(window).scrollTop();
	        if (top>0&&top<=1000){
	            $("#headLogin").hide();
	            $("#point1").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground1()
	        }
	        else if (top>1000&&top<=2000){
	            $("#headLogin").show();
	            $("#point2").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground2()
	        }
	        else if (top>2000&&top<=3000){
	            $("#headLogin").show();
	            $("#point3").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground3()
	        }
	        else if (top>3000&&top<=4000){
	            $("#headLogin").show();
	            $("#point4").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground4()
	        }
	    });
	    
	    
	    
	    //人才渠道首页点击切换
	    $("#pointUl_person li").bind("click",function(){
	        var data=$(this).attr("data");
	        $(this).addClass("li_active").siblings().removeClass("li_active");
	        if (data=="point1"){
	            $("#headLogin").hide();
	            offset("li1_person");
	            ClickScreenBackground5()
	            
	        }
	        else if (data=="point2"){
	            $("#headLogin").show();
	            offset("li2_person");
	            ClickScreenBackground6()
	           
	        }
	        else if (data=="point3"){
	            $("#headLogin").show();
	            offset("li3_person");
	            ClickScreenBackground7()
	            
	        }
	        else if (data=="point4"){
	            $("#headLogin").show();
	            offset("li4_person");
	            ClickScreenBackground8()
	            
	        }
	    });
	    
	    
	  //点击按钮屏幕滚动
	    function offset(id){
			var top=$("#"+id).offset().top;
			$("body,html").animate({
		       scrollTop: top
		    }, 1000);
		}
	    
	  //点击全屏
	  function ClickScreenBackground1(){
			var winHeight=$(window).height();
			$("#companyBg1").css({"height": winHeight});
			$("#li_main1").css({"height": winHeight});
	  }
	  function ClickScreenBackground2(){
			var winHeight=$(window).height();
			$("#companyBg2").css({"height": winHeight});
			$("#li_main2").css({"height": winHeight});
	  }
	  function ClickScreenBackground3(){
			var winHeight=$(window).height();
			$("#companyBg3").css({"height": winHeight});
			$("#li_main3").css({"height": winHeight});
	  }
	  function ClickScreenBackground4(){
			var winHeight=$(window).height();
			$("#companyBg4").css({"height": winHeight});
			$("#li_main4").css({"height": winHeight});
	  }
	  function ClickScreenBackground5(){
			var winHeight=$(window).height();
			$("#personBg1").css({"height": winHeight});
			$("#personli_main1").css({"height": winHeight});
	  }
	  function ClickScreenBackground6(){
			var winHeight=$(window).height();
			$("#personBg2").css({"height": winHeight});
			$("#personli_main2").css({"height": winHeight});
	  }
	  function ClickScreenBackground7(){
			var winHeight=$(window).height();
			$("#personBg3").css({"height": winHeight});
			$("#personli_main3").css({"height": winHeight});
	  }
	  function ClickScreenBackground8(){
			var winHeight=$(window).height();
			$("#personBg4").css({"height": winHeight});
			$("#personli_main4").css({"height": winHeight});
	  }
	    //向下按钮1
	    $("#downBtn1_person").bind("click",function(){
	        $("#headLogin").show();
	        $("#point2_person").addClass("li_active").siblings().removeClass("li_active");
	        offset("li2_person");
	        ClickScreenBackground6()
	    });
	    //向下按钮2
	    $("#downBtn2_person").bind("click",function(){
	        $("#headLogin").show();
	        $("#point3_person").addClass("li_active").siblings().removeClass("li_active");
	        offset("li3_person");
	        ClickScreenBackground7()
	    });
	    //向下按钮3
	    $("#downBtn3_person").bind("click",function(){
	        $("#headLogin").show();
	        $("#point4_person").addClass("li_active").siblings().removeClass("li_active");
	        offset("li4_person");
	        ClickScreenBackground8()
	    });

	    //窗口滚动
	    $(window).scroll(function(){
	        var top=$(window).scrollTop();
	        if (top>0&&top<=1000){
	            $("#headLogin").hide();
	            $("#point1_person").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground5()
	        }
	        else if (top>1000&&top<=2000){
	            $("#headLogin").show();
	            $("#point2_person").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground6()
	        }
	        else if (top>2000&&top<=3000){
	            $("#headLogin").show();
	            $("#point3_person").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground7()
	        }
	        else if (top>3000&&top<=4000){
	            $("#headLogin").show();
	            $("#point4_person").addClass("li_active").siblings().removeClass("li_active");
	            ClickScreenBackground8()
	        }
	    });
	    
	    //规则入口
	    $("#btnBox3_company").click(function(){
	    	window.open(ctx+'/protocol/companyInviteRule?'+jsonAjax.random());
	    });
	    $("#btnBox3_elite").click(function(){
	    	window.open(ctx+'/protocol/eliteRevenueRule?'+jsonAjax.random());
	    });
	 
});