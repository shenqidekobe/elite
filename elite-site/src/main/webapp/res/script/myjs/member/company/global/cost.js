require([ "jquery", "ajax", "jsonAjax","commons","pingpp"], function($) {

	var pingpp = require('pingpp');
	//面包屑事件
	$("#myProject").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#crumbs_main").click(function(){
		window.location.href=ctx+'/member/index?'+jsonAjax.random();
	});
	$("#projectDetail,#projectDetail1").click(function(){
		var id=$(this).attr("data");
		window.location.href=ctx+'/project/'+id+'/index?'+jsonAjax.random();
	});
	//转账方式切换
	var payWay="alipay";
    $(".pay-box .radio-box").click(function(){
    	payWay=$(this).attr("data");
    	$(".pay-box .radio-box").attr("src",ctx+"/res/images/ceo/check_no.png");
    	$(this).attr("src",ctx+"/res/images/ceo/check.png");
    	if(payWay=="offline"){
    		$(".goto-pay-btn").text("保存");
    		$("#bank_transfer").show();
    		//显示影藏发票和银行卡信息
    		$("#gatheringDiv").show();
    		//$("#invoice_ask").hide();
    		//$("#invoiceDiv").hide();
    	}else{
    		$(".goto-pay-btn").text("去支付");
    		$("#bank_transfer").hide();
    		//显示发票和银行卡信息
    		$("#gatheringDiv").hide();
    		//$("#invoice_ask").show();
    		//$("#invoiceDiv").hide();
    	}
    	$(".two-ways").removeClass("current-way");
    	$(this).next().addClass("current-way");
    	$("#cancelBtn").click();
    });
    $(".two-ways").click(function(){
    	$(".two-ways").removeClass("current-way");
    	$(this).addClass("current-way");
    	payWay=$(this).attr("data");
    	$(".pay-box .radio-box").attr("src",ctx+"/res/images/ceo/check_no.png");
    	$(this).prev().attr("src",ctx+"/res/images/ceo/check.png");
    	if(payWay=="offline"){
            $(".goto-pay-btn").text("保存");
    		
    		$("#bank_transfer").show();
    		$("#gatheringDiv").show();
    		//$("#invoice_ask").hide();
    		//$("#invoiceDiv").hide();
    	}else{
    		$(".goto-pay-btn").text("去支付");
    		$("#bank_transfer").hide();
    		//显示发票和银行卡信息
    		$("#gatheringDiv").hide();
    		//$("#invoice_ask").show();
    		//$("#invoiceDiv").hide();
    	}
    	$("#cancelBtn").click();
    });
    
    //索要发票
    $("#invoice_ask").click(function(){
        var display=$(this).next().css("display");
        if (display=="none"){
            $(this).find(".show_img").attr("src",ctx+"/res/images/ceo/hide_img.png");
            $(this).find(".show_txt").addClass("active_color");
            $(this).next().show();
            $(this).parent().css({"background":"#f5fbfc","height":"430px"});
        }else {
            $(this).find(".show_img").attr("src",ctx+"/res/images/ceo/show_img.png");
            $(this).find(".show_txt").removeClass("active_color");
            $(this).next().hide();
            $(this).parent().css({"background":"none","height":"30px"});
        }
    });
    
    
    //索要发票的取消
    $("#cancelBtn").click(function(){
        $("#invoice_ask .show_img").attr("src",ctx+"/res/images/ceo/show_img.png");
        $("#invoice_ask .show_txt").removeClass("active_color");
        $(".invoiceBox .hide_div").hide();
        $(".invoiceBox").css({"background":"none","height":"30px"});
        
    });
    
    //托管费用索要发票的确定
    $("#saveBtn").click(function(){
        var value1=$("#invoiceRise").val();
        var value2=$("#invoiceAddress").val();
        var value3=$("#invoiceName").val();
        var value4=$("#invoicePhone").val();

        if (value1==""){
            $(".error_div").show();
            $(".error_text").text("请输入发票抬头");
            $("#invoiceRise").focus(function(){
                $(".error_div").hide();
            });
            return false;
        }else if(value2==""){
            $(".error_div").show();
            $(".error_text").text("请输入邮件地址");
            $("#invoiceAddress").focus(function(){
                $(".error_div").hide();
            });
            return false;
        }else if(value3==""){
            $(".error_div").show();
            $(".error_text").text("请输入收件人姓名");
            $("#invoiceName").focus(function(){
                $(".error_div").hide();
            });
            return false;
        }else if(value4==""){
            $(".error_div").show();
            $(".error_text").text("请输入联系方式");
            $("#invoicePhone").focus(function(){
                $(".error_div").hide();
            });
            return false;
        }
        $("#cancelBtn").click();
    });
    //修改发票信息
    $(".change-company").click(function(){
    	$("#modal-invoice").modal('show');
    });
    $("#save-invoice-btn").click(function(){
		var invoiceRise=$.trim($("#invoiceRise").val());
		if(invoiceRise==""){
			$("#invoiceRise-error").show();
			$("#invoiceRise-error").text("请输入发票抬头");
			return false;
		}
		$("#invoiceRise_face").text(invoiceRise);
		$("#modal-invoice").modal('hide');
	});
    
    $(".goto-pay-btn").click(function(){
    	var id=$(this).attr("data");
    	var invoiceRise=$("#invoiceRise").val();
        var invoiceAddress=$("#invoiceAddress").val();
        var invoiceName=$("#invoiceName").val();
        var invoicePhone=$("#invoicePhone").val();
    	if(payWay=="alipay"){
    		$("#_invoiceRise").val(invoiceRise);
            $("#_invoiceAddress").val(invoiceAddress);
            $("#_invoiceName").val(invoiceName);
            $("#_invoicePhone").val(invoicePhone);
    		$("#payForm").submit();
    		
    		$("#modal-pay").modal({backdrop: 'static', keyboard: false});
        	$("#complete-pay-btn").click(function(){
        		location.reload();
        	});
    	}else if(payWay=="offline"){
    		//线下支付
    		jsonAjax.post({
    			url:ctx+'/project/company/prostage/payWay',
    			data:{projectId:id,invoiceRise:invoiceRise,invoiceAddress:invoiceAddress,invoiceName:invoiceName,invoicePhone:invoicePhone},
    			success:function(data){
    				$(".payBank").show();
    				var i=6;
    				$("#down_time").text(i+"s");
					setInterval(function(){
						i--;
						$("#down_time").text(i+"s");
						if(i==0){
							$(".box-c-content").click();
						}
					}, 1000);
					$(".box-c-content").click(function(){
						window.location.href=ctx+'/member/index?'+jsonAjax.random();
					});
    			}
    		});
    	}else{
    		jsonAjax.post({
    			url:ctx+'/project/pingplus/pay',
    			data:{type:'prostage',projectId:id,channel:payWay,
    				  invoiceRise:invoiceRise,invoiceAddress:invoiceAddress,invoiceName:invoiceName,invoicePhone:invoicePhone},
    			success:function(data){
    				if (data.result == "SUCCESS") {
    					var charge=data.object;
    					if(charge.channel=="wx_pub_qr"){
    						try{
    							var img=charge.credential.wx_pub_qr;
        						var cc=ctx+'/common/produce/qr?url='+img+'&size=150';
        						$("#wxpay_img").attr('src',cc);
        						$("#wxpay").fadeIn(500);
        						$("#wxpay .payClose").click(function(){
        							$("#wxpay").fadeOut(500);
        							window.location.href= window.location.href;
        						});
    						}catch(e){
    							tostHint("支付失败",data.msg);
    						}
    						return false;
    					}
    					pingpp.createPayment(charge, function(result, err){
    						
    					});
    				}else{
    					tostHint("支付失败",data.msg);
    				}
    			}
    		});
    	}
    });
});
