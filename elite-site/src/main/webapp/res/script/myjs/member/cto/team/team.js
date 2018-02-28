//我的团队
function loadTeam(){
	jsonAjax.post({
		url:ctx+'/member/team/list',
		success:function(html){
			$("#projectList").html(html);
			$(".tabs .group").click(function(){
				var keyWord = $("#content").val();
				$(".tabs div").removeClass("active-tab");
				$(this).addClass("active-tab");
				var index=parseInt($(this).attr("index"));
				var left=260*index;
				$(".active-line").css({'left':left+'px'});
				var status=$(this).attr("data");
				loadTeamListData(status,keyWord);
			});
			$(".tabs div:first").click();
			$("#search").click(function(){
				$(".tabs div:first").click();
			})
		}	
	});
}


function loadTeamListData(status,keyWord){
	var url="";
	if(status=='memberTeam'){
		url=ctx+'/member/team/listData';
	}else{
		$("#dataList").html("");
		return false;
	}
	jsonAjax.post({
		url:url,
		data:{"keyWord":keyWord},
		success:function(html){
			$("#dataList").html(html);
			$(".find-btn").click(function(){
				window.location.href=ctx+"/circle?"+jsonAjax.random();
			})
			$(".opt-btn").click(attenttion);//关注
			//分页组件
			$(".pagination #prev_pager").click(function(){
				var currentPageth = $("#pager_pageth").val();
				if (currentPageth - 0 <= 1) {
					return;
				}
				$("#pager_pageth").val(currentPageth - 1);
				loadProjectListData();
			});
			$(".pagination #jump_pager").click(function(){
				var pageth = $(this).attr("data");
				$("#pager_pageth").val(pageth - 0);
				loadProjectListData();
			});
			$(".pagination #next_pager").click(function(){
				var currentPageth = $("#pager_pageth").val();
				if (currentPageth - 0 >= $("#pager_pageCount").val() - 0) {
					return;
				}
				console.info($("#pager_pageth").val());
				$("#pager_pageth").val(currentPageth - 0 + 1);
				loadProjectListData();
			});

		}
	});	
}

function attenttion(){
	var memberId = $(this).attr("data");
	jsonAjax.post({
		url:ctx+'/member/attendtion/addOrRemoveAttention',
		data:{"type":"add","attentionMemberId":memberId},
		success:function(data){
			if(data.result=="SUCCESS"){
				$(".tabs div:first").click();
			}else{
				tostHint("关注失败",data.msg);
			}
		}
	});

}


