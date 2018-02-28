require([ "jquery", "ajax", "jsonAjax","commons"], function($) {
	
	$(".attentionImg").click(attention);
	$("#selectUl li").click(function(){
        var type=$(this).attr("data");
        $("#selectUl li").find(".li_box").find(".li_text").removeClass("active_color");
        $(this).find(".li_box").find(".li_text").addClass("active_color");
        if(type=="main"){
            $("#head_img").attr("src",ctx+"/res/images/elite/head_select.png");
            $("#heart_img").attr("src",ctx+"/res/images/elite/heart.png");
            $(this).parent().find(".active_line").animate({"top":"-10px"});
            $("#mainContent").show();
            $("#attentionContent").hide();
        }
        else {
            $("#head_img").attr("src",ctx+"/res/images/elite/head.png");
            $("#heart_img").attr("src",ctx+"/res/images/elite/heart_select.png");
            $(this).parent().find(".active_line").animate({"top":"30px"});
            $("#attentionContent").show();
            $("#mainContent").hide();
            loadAttentionData();
        }
    });
	//关注
	function attention(){
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
	//加载精英的关注数据
	function loadAttentionData(){
		jsonAjax.post({
			url:ctx+'/circle/view/attention',
			data:$("#attentionListForm").serialize(),
			success:function(data){
				$("#attentionMain").html(data);
				//分页组件js
				pagination(loadAttentionData);
				
				//取消关注
				$("button[id^='remove_']").click(function(){
					var attentionId = $(this).attr("data");
					jsonAjax.post({
						url:ctx+'/member/attendtion/addOrRemoveAttention',
						data:{"type":"remove","attentionMemberId":attentionId},
						success:function(data){
							loadAttentionData();
						}
					});
				})
				
				//添加关注
				$("button[id^='add_']").click(function(){
					var attentionId = $(this).attr("data");
					jsonAjax.post({
						url:ctx+'/member/attendtion/addOrRemoveAttention',
						data:{"attentionMemberId":attentionId},
						success:function(data){
							loadAttentionData();
						}
					});
				})
			}
		});
	}
});