require([ "jquery", "ajax", "jsonAjax","md5","commons"], function($) {
	var sessionId=$("#sessionId").val();
	var autoLogin=typeof(localStorage.autoEliteLogin)=='undefined'?'false':localStorage.autoEliteLogin;
	if(sessionId==""&&autoLogin=='true'){
		localStorage.autoEliteLogin=false;
		localStorage.elitePassword='';
	}
	
	 //圖片輪播
    var i=0;
    var size=$(".shuffling_point li").size();

    //向左函数
    function moveL(){
        i++;
        if(i==size){
            i=0;
        }
        $(".shuffling_point li").eq(i).css({"opacity":"1"}).siblings().css({"opacity":"0.5"});
        $(".shuffling_img li").eq(i).show().siblings().hide();
    }
    
    //时间定时
    var timeBegin=setInterval(function(){moveL()},4500);
    function timeStop(){
        clearInterval(timeBegin);
    }
    $(".shuffling_img li").hover(function(){
    	timeStop();
    },function(){
    	timeBegin=setInterval(moveL,4500);
    });
    
    
    //点击切换事件
    $(".shuffling_point li").click(function(){
        timeStop();
        $(this).css({"opacity":"1"}).siblings().css({"opacity":"0.5"});
        var data=$(this).attr("data");
        if(data=="point1") {
            $("#point1_img").show();
            $("#point2_img").hide();
        }else {
            $("#point1_img").hide();
            $("#point2_img").show();
        }
    });

	//看看我们如何工作的？
	$(".int-c").click(function(){
		animateShow()
	});
	$(".close_btn").click(function(){
		animateHide()
	});
	
	
	//how_work出现
	function animateShow(){
		$(".how_work").animate({
		      height:'400px',
		    });
		$(".close_btn").show();
		$(".how_work_box").css({"padding-top":"100px"});
	}
	
	//how_hide出现
	function animateHide(){
		$(".how_work").animate({
		      height:'0px', 
		    });
		$(".how_work_box").css({"padding-top":"0px"});
		$(".close_btn").hide();
	}
	
	//屏幕滚动关闭how_work
	
	$(window).scroll(function(){
        var height=$(".how_work").height();
        if(height==400){
        	animateHide()
        }
    });
	
	
	//更多精英hover动态
	 $("#eliteUl li").hover(function(){
         $(this).find(".create_in_hide").stop().animate({"height":"87px"},function(){
             $(this).parent().find(".logo_box_hide").stop().animate({"height":"173px"},function(){
                 $(this).parent().find(".index_icon").stop().show(function(){
                	 $(this).unbind("click",attention);
                	 $(this).click(attention);
                	 $(this).bind("click",eachElite);
                	 $(this).parent().find(".logo_box_hide,.create_in_hide").bind("click",eachElite);
                 });
             });
         });
     },function(){
    	 $(this).bind("click",eachElite);
         $(this).find(".index_icon").stop().hide();
         $(this).find(".logo_box_hide").stop().animate({"height":"0px"},1,function(){
             $(this).parent().find(".create_in_hide").stop().animate({"height":"0px"},1);
         });
     });
	 
	 
	 //查看每一个精英
	 function eachElite(){
		 $("#eliteUl li").unbind("click");
         $("#eliteUl li").bind("click",function(){
             var id=$(this).attr("data");
             var url=ctx+"/circle/view/"+id+"?"+jsonAjax.random();
             window.open(url);
         });
     }
	 
	 //未登录的精英关注按钮事件
	 $("img[id=nologin_icon]").click(function(){
		 var url=ctx+"/login?"+jsonAjax.random();
	 });
	 
	//关注
	function attention(e){
		e.stopPropagation();

		var thiss=$(this);
		var id=thiss.attr("data");
		var oper=thiss.attr("oper");
		jsonAjax.post({
			url:ctx+'/member/attendtion/addOrRemoveAttention',
			data:{"type":oper,"attentionMemberId":id},
			success:function(data){
				if(data.result=="SUCCESS"){
					var count=$("#fansCount").text();
					if(oper=="yes"){
						thiss.css({"background":"#999"});
						thiss.text("已关注");
						thiss.attr("oper","remove");
						count=parseInt(count)+1;
					}else{
						thiss.css({"background":"#fea600"});
						thiss.text("关注");
						thiss.attr("oper","yes");
						count=parseInt(count)-1;
					}
					$("#fansCount").text(count);
				}else{
					tostHint("关注失败",data.msg);
				}
			}
		});
	}
	 
	 
	//项目方介绍
	$("#me_project").click(function(){
		if(sessionId==''){
			sessionStorage.registerType="company";
			window.location.href=ctx+'/register?'+jsonAjax.random();
		}else{
			window.location.href=ctx+'/member/index?'+jsonAjax.random();
		}
	});
	//进入cto
	$(".me_cto").click(function(){
		if(sessionId==''){
			sessionStorage.registerType="cto";
			window.location.href=ctx+'/register?'+jsonAjax.random();
		}else{
			window.location.href=ctx+'/member/index?'+jsonAjax.random();
		}
	});
	//进入精英
	$(".me_elite").click(function(){
		if(sessionId==''){
			sessionStorage.registerType="elite";
			window.location.href=ctx+'/register?'+jsonAjax.random();
		}else{
			window.location.href=ctx+'/member/index?'+jsonAjax.random();
		}
	});
	
	
	
	//我是项目方，我是精英点击进入主页
	$(".person_project").click(function(){
		window.location.href=ctx+"/introduce/company";
	});
	$(".person_elite").click(function(){
		window.location.href=ctx+"/introduce/elite";
		
	});
	
	
	
	
});
