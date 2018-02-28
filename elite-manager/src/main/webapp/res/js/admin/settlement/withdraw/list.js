$(function() {
	list.init();
});
var list = {
	init : function() {
		list.search();
		$("#search").click(function(){
			//搜索默认第一页
			$("#pager_pageth").val(1);
			list.search()
		});
		list.dateTimePicker();
	},
	search : function() {
		Ajax.post({
			url : ctx + '/settlement/withdraw/listData',
			data : $("#listForm").serialize(),
			success : function(data) {
				$("#listData").html(data);
				$("a[id='viewInvoice']").click(invoicelist.showForm)
				$("a[id='viewLog']").click(list.viewLog)
				$("a[id='viewPay']").click(list.viewPay)
			}
		})
	},
	dateTimePicker : function() {
		$('#startTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true,
		})
		$('#endTimeId').datepicker({
			language : 'zh-CN',
			autoclose : true,
			format : "yyyy-MM-dd",
			todayHighlight : true
		})
	},
	//提现记录
	viewLog:function(){
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/settlement/withdraw/invoicelog/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#showDate").html(data);
				$('#myModel').modal('show');
			}
		})
	},
	//确认提现显示
	viewPay:function(){
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/settlement/withdraw/pay/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#showDate").html(data);
				$('#myModel').modal('show');
				//发票凭证上传
				upload.uploadImg($("#invoiceAttaImgFile"), "settings", function(data) {
					$("#invoiceAttaImg").attr("src", data.url);
					$("#invoiceAttaImg").css({
						width : '230px',
						height : '160px'
					});
					$("#invoiceAttaId").val(data.attaId);
				});
				//打款凭证上传
				upload.uploadImg($("#withdrawAttaFile"), "settings", function(data) {
					$("#withdrawAttaImg").attr("src", data.url);
					$("#withdrawAttaImg").css({
						width : '230px',
						height : '160px'
					});
					$("#withdrawAttaId").val(data.attaId);
				});
				$("#submitPay").click(list.submitPay)
			}
		})
	},submitPay:function(){
		 var id=$("#withdrawId").val();
		 var withdrawAttaId=$("#withdrawAttaId").val();
		 var invoiceAttaId=$("#invoiceAttaId").val();
		 if(withdrawAttaId==null){
			 $.tips({
                 content : "请上传打款凭证"
             });
			 return ;
		 }
        $.confirm({
            title : "提示",
            content : "您确定处理提现吗",
            iconSmall : "icon-warning-sign",
            callback : function() {
                jsonAjax.post({
                    url : ctx + "/settlement/withdraw/pay/save",
                    data : {
                    	id : id,
                    	withdrawAttaId:withdrawAttaId,
                    	invoiceAttaId:invoiceAttaId
                    },
                    success : function(data) {
                        if (BASE.JS_RESULT.SUCCESS == data.result) {
                        	$('#myModel').modal('hide');
                        	list.search();
                        }else{
                            $.tips({
                                content : data.msg
                            });
                        }
                    }
                });
            }
        });
	}
	
};