$(function() {
	list.init();
});
var list = {
	init : function() {
		$("#back").click(list.back)
	    $("#addRemarks").click(remarkslist.showForm)
	    $("a[id='viewInvoice']").click(list.showInvoiceForm)
	    $("a[id='addInvoice']").click(list.showAddInvoiceForm)
	    $("a[id='viewInOrder']").click(list.showInOrderForm)
	    $("a[id='viewConfirmReceipt']").click(list.showViewConfirmReceipt)
	 
	  
	},
	search : function() {
		window.history.back(-1); 
	},
	back : function() {
		window.location.href = ctx + '/settlement/receivable'
	},
	showInvoiceForm:function(){
		var id=$(this).attr("data");
		jsonAjax.post({
			url : ctx + '/settlement/receivable/invoice/view',
			data :{id:id},
			success : function(data) {
				$("#showDate").html(data);
				$('#myModel').modal('show');
			}
		})
	},
	showAddInvoiceForm:function(){
		var id=$(this).attr("data");
		jsonAjax.post({
			url : ctx + '/settlement/receivable/addInvoice/view',
			data :{id:id},
			success : function(data) {
				$("#showDate").html(data);
				$('#myModel').modal('show');
				$('#cancel').click(list.receiptCancel);
				$('#submitReceipteBtn').click(list.submitAddInovice);
				upload.uploadImg($("#invoiceAttaFile"), "settings", function(data) {
					$("#invoiceAttaImg").attr("src", data.url);
					$("#invoiceAttaImg").css({
						width : '230px',
						height : '160px'
					});
					$("#invoiceAttaId").val(data.attaId);
				});
				upload.uploadImg($("#expressAttaFile"), "settings", function(data) {
					$("#expressAttaImg").attr("src", data.url);
					$("#expressAttaImg").css({
						width : '230px',
						height : '160px'
					});
					$("#expressAttaId").val(data.attaId);
				});
				//手机号码数字限制
				$("#phoneId").bind("keyup blur",function(){
					var phone = $(this).val();
					$(this).val(phone.replace(/\D/g,''));
				});
			
			  
			}
		})
	},
	showInOrderForm:function(){
		var id=$(this).attr("data");
		jsonAjax.post({
			url : ctx + '/settlement/receivable/inOrder/view',
			                    
			data :{id:id},
			success : function(data) {
				$("#showDate").html(data);
				$('#myModel').modal('show');
			}
		})
	},
	showViewConfirmReceipt:function(){
		var id=$(this).attr("data");
		jsonAjax.post({
			url : ctx + '/settlement/receivable/confirmReceipt/view',
			data :{id:id},
			success : function(data) {
				$("#showDate").html(data);
				$('#myModel').modal('show');
				$('#receiptCancelBtn').click(list.receiptCancel);
				$('#submitReceipteBtn').click(list.submitReceipt);
				$('#receiptTimeId').datepicker({
					language : 'zh-CN',
					autoclose : true,
					format : "yyyy-MM-dd",
					todayHighlight : true,
				})
				//手机号码数字限制
				$("#payAccountId").bind("keyup blur",function(){
					var phone = $(this).val();
					$(this).val(phone.replace(/\D/g,''));
				});
			}
		})
	},
	receiptCancel:function(){
		$('#myModel').modal('hide');
	},
	submitReceipt:function(){
	  var id=$(this).attr("data");
	    var memberName=$("#memberNameId").val().trim();
	    if(memberName==null||memberName==""){
	    	$.tips({
				content :"请输入打款客户名"
			});
	    	return;
	    }
	    var payAccount=$("#payAccountId").val().trim();
	    if(payAccount==null||payAccount==""){
	    	$.tips({
	    		content :"请输入客户账号"
	    	});
	    	return;
	    }
	    var receiptAmount=$("#receiptAmountId").val().trim();
	    if(receiptAmount==null||receiptAmount==""){
	    	$.tips({
	    		content :"请输入实收金额"
	    	});
	    	return;
	    }else if(isNaN(receiptAmount)){
			  $.tips({
					content : "请输入正确实收金额"
				});
			  return;
		  }
 		jsonAjax.post({
			url : ctx + '/settlement/receivable/confirmReceipt/save',
			data :$("#submitForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					$('#myModel').modal('hide');
					window.location.reload();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	}, 
	submitAddInovice:function(){
	    var invoiceResie=$("#invoiceResieId").val();
	    var maxmount=$("#allpayAmountId").val();
	    if(invoiceResie==""){
	    	$.tips({
				content : "请输入发票抬头"
			});
	    	return ;
	    }
	    var amount=$("#amountId").val();
	    if(amount==""){
	    	$.tips({
	    		content : "请输入发票金额"
	    	});
	    	return 
	    }else if(isNaN(amount)){
	    	$.tips({
	    		content : "请输入正确发票金额"
	    	});
	    	return;
	    }else if(maxmount!=null&&amount>maxmount){
	    	$.tips({
	    		content : "发票额度不能高于当前阶段付款额度"
	    	});
	    	return;
	    }
	    var recipients=$("#recipientsId").val();
	    if(recipients==""){
	    	$.tips({
				content : "请输入收件人"
			});
	    	return ;
	    }
	    var phone=$("#phoneId").val();  
	    if(phone==""){
	    	$.tips({
				content : "请输入手机号"
			});
	    	return ;
	    }
	    else{
	    	var mobileReg = /^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\d{8}$/;
			if (!mobileReg.test(phone)) {
				$.tips({
					content : "请输入正确手机号码"
				});
				return;
			}
	    }
	    var expressNum=$("#expressNumId").val();
	    if(expressNum==""){
	    	$.tips({
				content : "请输入运单号码"
			});
	    	return ;
	    }
	    
		jsonAjax.post({
			url : ctx + '/settlement/receivable/addInvoice/save',
			data :$("#submitForm").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					$('#myModel').modal('hide');
					window.location.reload();
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	    
	},
   
	
};