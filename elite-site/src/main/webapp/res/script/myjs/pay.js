require([ "jquery", "ajax", "jsonAjax","commons","pingpp","highcharts"], function($) {
	
	var highcharts = require(['highcharts']);
	console.info(highcharts);
	var pingpp = require('pingpp');
	$("#pay").click(pay);
	$("#withdraw").click(withdraw);
	$("#queryWithdraw").click(queryWithdraw);
	$("#queryCode").click(queryCode);
	$("#queryVisite").click(queryVisite);
	$("#valiCard").click(valiCard);
	$("#valiBank").click(valiBank);
	$("#affirmWithdraw").click(affirmWithdraw);
	function pay(){
		jsonAjax.post({
			url:ctx+'/pingpp/pay',
			success:function(charge){
				console.info(charge);
				callback(charge);
			}
		});
	}
	function withdraw(){
		jsonAjax.post({
			url:ctx+'/pingpp/withdraw',
			success:function(transfer){
				console.info(transfer);
			}
		});
	}
	function queryWithdraw(){
		jsonAjax.post({
			url:ctx+'/pingpp/query/withdraw',
			success:function(transfer){
				console.info(transfer);
			}
		});
	}
	function queryVisite(){
		$.ajax({
			url:ctx+'/pingpp/query/invite',
			type:"POST",
			success:function(code){
				if(code==""||code==null){
					tostHint("查询失败","未查到任何信息");
					$("#invites").text('');
				}else{
					$("#invites").text("你的邀请码为："+code);
				}
			}
		});
	}
	function valiCard(){
		var name=$.trim($("#name").val());
		var idCard=$.trim($("#idCard").val());
		if(name==""||idCard==""){
			return false;
		}
		$.ajax({
			url:ctx+'/pingpp/validate/card',
			type:"POST",
			data:{name:name,idCard:idCard},
			success:function(code){
				tostHint("验证结果",code);
			}
		});
	}
    function valiBank(){
    	var name=$.trim($("#name").val());
		var idCard=$.trim($("#idCard").val());
		var bankCode=$.trim($("#bankCode").val());
		var phone=$.trim($("#phone").val());
		if(name==""||idCard==""||bankCode==""){
			return false;
		}
		$.ajax({
			url:ctx+'/pingpp/validate/bank',
			type:"POST",
			data:{name:name,idCard:idCard,bankCode:bankCode,phone:phone},
			success:function(code){
				tostHint("验证结果",code);
			}
		});
	}
    function affirmWithdraw(){
    	var id=$("#withdrawId").val();
    	if(id==""){
			return false;
		}
    	$.ajax({
			url:ctx+'/pingpp/affirm/withdraw',
			type:"POST",
			data:{id:id},
			success:function(code){
				tostHint("验证结果",code);
			}
		});
    }
	function queryCode(){
		var phone=$.trim($("#phone").val());
		var type=$("#type").val();
		if(phone==""){
			tostHint("查询失败","请输入您要查询的手机号");
			return false;
		}
		$.ajax({
			url:ctx+'/pingpp/query/code',
			type:"POST",
			data:{phone:phone,type:type},
			success:function(code){
				if(code==""||code==null){
					tostHint("查询失败","未查到任何信息");
					$("#codes").text('');
				}else{
					$("#codes").text("您的验证码为："+code);
				}
			}
		});
	}
	function callback(charge){
		if(charge.channel=="wx_pub_qr"){
			var img=charge.credential.wx_pub_qr;
			var cc=ctx+'/pingpp/wx/qr?url='+img;
			$("#wxImg").attr('src',cc);
			return false;
		}
		pingpp.createPayment(charge, function(result, err){
		  if (result == "success") {
		    // 只有微信公众账号 wx_pub 支付成功的结果会在这里返回，其他的支付结果都会跳转到 extra 中对应的 URL。
		  } else if (result == "fail") {
		    // charge 不正确或者微信公众账号支付失败时会在此处返回
		  } else if (result == "cancel") {
		    // 微信公众账号支付取消支付
		  }
		});
	}
});
