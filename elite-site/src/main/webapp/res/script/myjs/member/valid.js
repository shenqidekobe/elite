function validExists(property,field,callback){
	property=property||'email';
	var validUrl=ctx+'/member/valid/'+property;
	jsonAjax.post({
		url:validUrl,
		data:{property:field},
		success:function(data){
			console.info(data);
			if(data==true){
				
			}else{
				callback();
			}
		}
	});
}