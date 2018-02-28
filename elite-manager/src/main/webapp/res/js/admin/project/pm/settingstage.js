var stagelist = {

	showForm : function() {
		var id = $(this).attr("data");
		jsonAjax.post({
			url : ctx + '/project/stage/setting/view',
			data : {
				id : id
			},
			success : function(data) {
				$("#showData").html(data);
				$('#myModel').modal('show');
				$('#input_projectId').val(id);
				$('button[id=deleteStage]').click(stagelist.remove);
				$('#addStage').click(stagelist.addStage);
				$('#setting_submitBtn').click(stagelist.submit);
			}
		})
	},
	remove : function() {
		$(this).parent().parent().html("");
	},
	addStage : function() {

		var key = $("#stage_select").val();
		var text = $("#stage_select").find("option:selected").text();
		var order = $("#stage_select").find("option:selected").attr("data");
		var existed = false;
		$('[id=stageItem]').each(function(i) {
			var val = $(this).attr("data");
			if (key == val) {
				$.tips({
					content : text + "已经存在，不能重复添加！"
				});
				existed = true;
			}
		})
		if (existed) {
			return;
		}

		var addtext = "<tr data='"+order+"' ><td style='width: 150px;'><span class='custom-bg circle' id='stageItem' data='" + key + "' value='" + key + "'>" + text + "</span></td>"
				+ "<input type='hidden' name='dicts' value='" + key + "'/>" + "<td><button type='button' class='btn btn-default' id='deleteStage'><span>×</span></button></td></tr>";
		var insertIndex=-1
		var addindex=false;
		$("#stageTable tr").each(function(i){
	    	if(order<$(this).attr("data")){
	    		if(addindex==false){
	    		insertIndex=i;
	    		addindex=true;
	    		}
	    	}
	    })
	    if(insertIndex>=0){
	    $("#stageTable tr").eq(insertIndex).before(addtext);
	    }
	  var trsize=  $("#stageTable tr").length
	  if(trsize==0||insertIndex==-1){
		$("#stageTable").append(addtext);
	  }
		$('button[id=deleteStage]').click(stagelist.remove);
		
	},
	submit : function() {
		jsonAjax.post({
			url : ctx + '/project/stage/save',
			data : $("#form").serialize(),
			success : function(data) {
				if (BASE.JS_RESULT.SUCCESS == data.result) {
					$('#myModel').modal('hide');
					list.search();
				}
			}
		})
	}

};