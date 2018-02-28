require([ "jquery", "ajax", "jsonAjax","commons","pingpp"], function($) {

	var pingpp = require('pingpp');
	//面包屑事件
	$("#myProject").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#projectDetail").click(function(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/'+id+'/index?'+jsonAjax.random();
	});
	var payWay="alipay";
	//转账方式切换
    $(".pay-box .radio-box").click(function(){
    	payWay=$(this).attr("data");
    	$(".pay-box .radio-box").attr("src",ctx+"/res/images/ceo/check_no.png");
    	$(this).attr("src",ctx+"/res/images/ceo/check.png");
    	if(payWay=="offline"){
    		$("#bank_transfer").show();
    	}else{
    		$("#bank_transfer").hide();
    	}
    	$(".two-ways").removeClass("current-way");
    	$(this).next().addClass("current-way");
    });
    $(".two-ways").click(function(){
    	$(".two-ways").removeClass("current-way");
    	$(this).addClass("current-way");
    	payWay=$(this).attr("data");
    	$(".pay-box .radio-box").attr("src",ctx+"/res/images/ceo/check_no.png");
    	$(this).prev().attr("src",ctx+"/res/images/ceo/check.png");
    	if(payWay=="offline"){
    		$("#bank_transfer").show();
    	}else{
    		$("#bank_transfer").hide();
    	}
    });
    //索要发票
    $("#invoice_show").click(function(){
        var display=$(this).next().css("display");
        if (display=="none"){
            $(this).find(".show_img").attr("src",ctx+"/res/images/ceo/hide_img.png");
            $(this).find(".show_txt").addClass("active_color");
            $(this).next().show();
            $(this).parent().css({"background":"#f5fbfc","height":"280px"});
        }else {
            $(this).find(".show_img").attr("src",ctx+"/res/images/ceo/show_img.png");
            $(this).find(".show_txt").removeClass("active_color");
            $(this).next().hide();
            $(this).parent().css({"background":"none","height":"30px"});
        }
    });
    
    
    //索要发票的取消
    $("#cancel_btn").click(function(){
        $(".show_img").attr("src",ctx+"/res/images/ceo/show_img.png");
        $(".show_txt").removeClass("active_color");
        $(".certificate .hide_div").hide();
        $(".certificate").css({"background":"none","height":"30px"});
        
    });
    
    //索要发票的确定
    $("#save_btn").click(function(){
        var value=$("#invoice_value").val();
        if (value==""){
            $(".error_div").show();
            $("#invoice_value").focus(function(){
                $(".error_div").hide();
            });
            return false;
        }else {
            $(".error_div").hide();
        }
        $("#cancel_btn").click();
    });
    
    $(".goto-pay-btn").click(function(){
    	var id=$(this).attr("data");
    	var invoiceRise=$("#invoice_value").val();
    	var href=ctx+'/project/intention/'+id+'/pay';
    	if(payWay=="alipay"){
    		$("#_invoiceRise").val(invoiceRise);
    		$("#payForm").submit();
    		
        	$("#modal-pay").modal({backdrop: 'static', keyboard: false});
        	$("#complete-pay-btn").click(function(){
        		//location.reload();
        		window.location.href= window.location.href;
        	});
    	}else{
    		jsonAjax.post({
    			url:ctx+'/project/pingplus/pay',
    			data:{type:'intention',projectId:id,channel:payWay,invoiceRise:invoiceRise},
    			success:function(data){
    				if (data.result == "SUCCESS") {
    					var charge=data.object;
    					if(charge.channel=="wx_pub_qr"){
    						try{
    							var img=charge.credential.wx_pub_qr;
    							var orderId=charge.orderNo;
        						var cc=ctx+'/common/produce/qr?url='+img+'&size=150';
        						$("#wxpay_img").attr('src',cc);
        						$("#wxpay").fadeIn(500);
        						var queryPayInterval=window.setInterval(function(){
        							$.ajax({
        								url:ctx+'/project/query/pay',
        								type:"POST",
        								data:{orderId:orderId},
        								success:function(data){
        									var result=JSON.parse(data);
        									if(result=="success"){
        										$("#payText").text("支付成功");
        										clearInterval(queryPayInterval);
        										window.setInterval(function(){
        											window.location.href= window.location.href;
        										}, 2000);
        									}else if(result=="failure"){
        										$("#payText").text("支付失败，请重新支付");
        										clearInterval(queryPayInterval);
        										window.setInterval(function(){
        											window.location.href= window.location.href;
        										}, 2000);
        									}
        								}
        							});
        						}, 6000); 
        						$("#wxpay .payClose").click(function(){
        							$("#wxpay").fadeOut(500);
        							clearInterval(queryPayInterval);
        							window.location.href= window.location.href;
        						});
    						}catch(e){
    							tostHint("支付失败",data.msg);
    						}
    					}else{
    						pingpp.createPayment(charge, function(result, err){});
    					}
    				}else{
    					tostHint("支付失败",data.msg);
    				}
    			}
    		});
    	}
    });
});
