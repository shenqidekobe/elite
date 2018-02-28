var recommendlist = {
	showForm : function() {
		var id = $(this).attr("data");
         if(id==""){
        	 $.tips({
 				content : "项目Id不能为空"
 			});
 			return;
         }
		jsonAjax.post({
			url : ctx + '/project/pm/recommend/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#data").html(data);
				$("#RecommendProjectId").val(id);
				$('#myModel').modal('show');
				$("#submit").click(recommendlist.submitForm);
				$("#recommendSearch").click(recommendlist.search);
				recommendlist.search();
	
			}
		})
	},
	search:function(){
		var keyword=$("#recommendKeywordId").val();
		var id=$("#RecommendProjectId").val();
		jsonAjax.post({
			url : ctx + '/project/pm/recommend/listData',
			data : {
				keyword : keyword
			},
			success : function(data) {
				$("#ctolistdata").html(data);
				$("#RecommendProjectId").val(id);
				$("div[id='selctCtoItem']").click(recommendlist.selctCtoItem);
				
			}
		})
	},
	selctCtoItem:function(){
		var nickName=$(this).attr("text")
		var creditRealName=$(this).attr("realName");
		if(creditRealName!=null&&creditRealName!=""){
			var realName=nickName+"("+creditRealName+")";
		}else{
			var realName=nickName;
		}
		var ctoId=$(this).attr("data")
		realName="<button type='button' id='selectCtoBtn' class="+ctoId+">"+realName+"</button>"
		var selected=false
		$(".selectCToclass").each(function(){
			if(ctoId==$(this).val()){
				selected=true;
			}
		})
		if(selected){
			$("#selectedCTO").find("."+ctoId+"").remove();
			$("#ctoIds").find("#"+ctoId+"").remove();
		}
		else{
			$("#selectedCTO").append(realName)
			$("#ctoIds").append("<input type='hidden' name='id' class='selectCToclass' id='"+ctoId+"' value='"+ctoId+"'>")
		}
		$("button[id=selectCtoBtn]").click(function(){
			var removeCtoid=$(this).attr("class");
			this.remove();
			$("#ctoIds").find("#"+removeCtoid+"").remove();
		})
    },
	submitForm:function(){
	   var dataMe=$("#submitForm").serialize();
       if(dataMe.indexOf("id")<0){
    	   $.tips({
				content : "请选择CTO"
			});
			return; 
       }
       jsonAjax.post({
			url : ctx + '/project/pm/recomemd/save',
			data : dataMe,
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
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