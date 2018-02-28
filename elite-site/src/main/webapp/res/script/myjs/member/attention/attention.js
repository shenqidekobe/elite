//我的任务
function loadAttentionList(){
	jsonAjax.post({
		url:ctx+'/member/attendtion/view',
		success:function(data){
			$("#projectList").html(data);
			//项目列表状态事件
			$(".tabs .col-xs-4").click(function(){
				$(".tabs div").removeClass("active-tab");
				$(this).addClass("active-tab");
				var index=parseInt($(this).attr("index"));
				var left=260*index;
				$(".active-line").css({'left':left+'px'});
				var status=$(this).attr("data");
				$("#attentionType").val(status);
				loadAttentionListData();
			});
			$(".tabs div:first").click();
		}
	});
}

function loadAttentionListData(){
	jsonAjax.post({
		url:ctx+'/member/attendtion/listData',
		data:$("#attentionListForm").serialize(),
		success:function(html){
			$("#dataList").html(html);
			$(".find-btn").click(function(){
				window.location.href=ctx+'/circle?'+jsonAjax.random();
			});
			
			//分页组件js
			pagination(loadAttentionListData);
			
			//取消关注
			$("a[id^='remove_']").click(function(){
				var attentionId = $(this).attr("data");
				jsonAjax.post({
					url:ctx+'/member/attendtion/addOrRemoveAttention',
					data:{"type":"remove","attentionMemberId":attentionId},
					success:function(data){
						loadAttentionList();
					}
				});
			})
			
			//添加关注
			$("a[id^='add_']").click(function(){
				var attentionId = $(this).attr("data");
				jsonAjax.post({
					url:ctx+'/member/attendtion/addOrRemoveAttention',
					data:{"attentionMemberId":attentionId},
					success:function(data){
						loadAttentionList();
					}
				});
			})
			
		}
	});
}