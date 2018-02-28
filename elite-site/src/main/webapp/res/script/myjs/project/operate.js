//我的项目列表
function projectList(){
	window.location.href=ctx+'/member/index?'+jsonAjax.random();
}
//编辑项目
function editProject(){
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/company/'+id+'/edit?'+jsonAjax.random();
}
//查看项目详情
function viewProject(){
	var id=$(this).attr("data");
	var status=$(this).attr("status");
	if(status=='unpass'){
		window.location.href=ctx+'/project/'+id+'/cause?'+jsonAjax.random();
	}else{
		window.location.href=ctx+'/project/'+id+'/index?'+jsonAjax.random();
	}
}
//去提交意向金
function submitIntention(){
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/intention?'+jsonAjax.random();
}
//确认项目立项书
function affirmProjectdefine(){
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/define?'+jsonAjax.random();
}
//托管阶段费用
function trustAmount(){
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/trustcost?'+jsonAjax.random();
}
//去验收
function goAccept(){
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/accept?'+jsonAjax.random();
}
//去评价
function goEvaluate(){
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/evaluate?'+jsonAjax.random();
}
//查看审核不通过原因
function viewCause(){
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/cause?'+jsonAjax.random();
}
//查看周报
function viewWeekly(){
	sessionStorage.setItem('rsp_intent_isd','weekly');
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/index?'+jsonAjax.random();
}
//查看文件
function viewMaterial(){
	sessionStorage.setItem('rsp_intent_isd','material');
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/index?'+jsonAjax.random();
}
//查看文件
function viewLogs(){
	sessionStorage.setItem('rsp_intent_isd','logs');
	var id=$(this).attr("data");
	window.location.href=ctx+'/project/'+id+'/index?'+jsonAjax.random();
}