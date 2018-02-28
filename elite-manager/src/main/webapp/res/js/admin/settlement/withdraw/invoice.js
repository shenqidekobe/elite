var invoicelist = {
	showForm : function() {
		var id = $(this).parent().attr("data");
		jsonAjax.post({
			url : ctx + '/settlement/withdraw/invoice/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#showDate").html(data);
				$('#myModel').modal('show');
				$("#submitInvoice").click(invoicelist.submitForm)
				upload.uploadImg($("#invoiceAttaFile"), "settings", function(data) {
					$("#invoiceAttaImg").attr("src", data.url);
					$("#invoiceAttaImg").css({
						width : '230px',
						height : '160px'
					});
					$("#invoiceAttaId").val(data.attaId);
				});
			}
		})
	},
    //提交发票
	submitForm : function(status) {
		var invoiceAmount=$("#invoiceAmount").val();
		if(invoiceAmount==""){
			$.tips({
				content : "请输入发票额度"
			});
			return;
		}else {
			var a=/^[0-9]*(\.[0-9]{1,2})?$/;
			if(!a.test(invoiceAmount))
			{
				$.tips({
					content : "发票额度格式不对"
				});
			return;
			}
		}
		var receiptAmount=$("#receiptAmount").val();
		if(receiptAmount==""){
			$.tips({
				content : "请输入打款额度"
			});
			return;
		}else {
			var a=/^[0-9]*(\.[0-9]{1,2})?$/;
			if(!a.test(receiptAmount))
			{
				$.tips({
					content : "打款额度格式不对"
				});
				return;
			}
		}
		var selectRadio = $('input[name="receive"]:checked ').val();
		if(typeof(selectRadio) == "undefined"||selectRadio==""||selectRadio=="NO"){
			{
				$.tips({
					content : "请确认 选择是"
				});
				return;
			}
		}
		var id=$("#withdrawId").val();
		var invoiceAttaId=$("#invoiceAttaId").val();
		jsonAjax.post({
			url : ctx + '/settlement/withdraw/invoice/save',
			data :{
				id:id,
				invoiceAmount:invoiceAmount,
				receiptAmount:receiptAmount,
				invoiceAttaId:invoiceAttaId
				
			},
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					list.search();
					$('#myModel').modal('hide');
				} else {
					$.tips({
						content : data.msg
					});
				}
			}
		})
	}
};