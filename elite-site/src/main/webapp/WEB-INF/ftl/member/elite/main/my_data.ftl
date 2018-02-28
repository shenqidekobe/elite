<@extend name="completeLayout">
    <@block name="cs">
   
    <style type="text/css">

        .process{
            width:100%;
            height:130px;
            background-color: #F5FBFC;
        }

        .process-box{
            width:1000px;
            margin:0 auto;
            position: relative;
        }

        .process-line{
            width:132px;
            border-bottom: 2px solid #BFBFBF;
            position: absolute;
            top:50px;
            left:210px;
        }

        .content{
            width:1000px;
            margin:0 auto;
            min-height:250px;

            padding-bottom: 60px;
        }
        .process-circle{
            width: 40px;
            height: 40px;
            border:none;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            background-color: #BFBFBF;
            text-align: center;
            line-height: 40px;
            color:white;

            position: absolute;
            top:32px;
            left:160px;
            z-index: 200;
        }

        .active-process{
            background-color: #7DD2E1;
        }

        .process-title{
            position: absolute;
            top:84px;
            left:148px;
        }

        .active-title{
            color:#2CB7C9;
        }

        .complete-circle{
            width:100px;
            height:100px;
            border:1px solid red;
            margin-left:200px;
            margin-top:20px;
            float:left;
        }


        .complete-tips{
            margin-top:30px;
            height:140px;
        }

        .complete-theme{
            margin-left:50px;
            padding-top:40px;
            float:left;
        }

        .tips{
            float:left;
            margin-left:20px;
        }

        .tips img{
            width:20px;
            height:20px;
        }

        .tips p{
            font-size: 12px;
            color:#9B9B9B;
        }

        .split-line{
            height:140px;
            border-left:1px solid #E6E6E6;
            float:left;
            margin-left:40px;
        }

        .form-person{
            display: block;
            padding-left:200px;
            padding-top:30px;
        }

        .form-person label{
            text-align: left!important;
            position: relative;
        }

        .form-tip{
            font-size: 12px;
            color:#FEA600;
        }

        .required{
            color:#FEA600!important;
        }

        .secret-circle{
            width: 20px;
            height: 20px;
            border: 1px solid #4A90E2;
            -moz-border-radius: 50px;
            -webkit-border-radius: 50px;
            border-radius: 50px;
            text-align: center;
            line-height: 20px;
            font-size: 12px;
            color:#4A90E2;
            display: inline-block;

            position: absolute;
            right:10px;
            top:8px;
        }

        .date-select{
            display: block;
            width:100%;
            position: relative;
        }

        .date-icon{
            width:20px;
            height:20px;
            position: absolute;
            top:7px;
            right:16px;
            z-index: 100;
        }


        .birth-box{
            position: relative;
        }

        .year-select{
            position: absolute;
            top:33px;
            left:0px;
            z-index: 100;
            border:1px solid #2CB7C9;

        }

        .year-select{
            display: none;
            height:266px;
            overflow: auto;
        }

        .year-select li{
            padding:0 16px;
            background-color: white;

        }

        .year-select li:hover{
            background-color: #2CB7C9;
            color:white;
            cursor: pointer;
        }
        .birth-year{
            position: absolute;
            top:7px;
            left:16px;
        }


        .form-triangle{
            position: absolute;
            bottom: 6px;
            right:20px;
            cursor: pointer;
        }
        .form-trigger {
            display: inline-block;
            height: 0;
            width: 0;
            overflow: hidden;
            border-style: solid;
            margin-left: 4px;
            margin-right: 4px;
            position: relative;
            top:0;
            border-width:6px 6px 0;
            border-color:#D8D8D8 transparent transparent;
            -webkit-transition:all .4s ease 0s;
            -moz-transition:all .4s ease 0s;
            -o-transition:all .4s ease 0s;
            transition:all .4s ease 0s;
        }

        .trigger-inverse{
            -webkit-transform: rotate(180deg);
            -moz-transform: rotate(180deg);
            -ms-transform: rotate(180deg);
            transform: rotate(180deg);
            animation-fill-mode: forwards;
        }

        .location{
            position: absolute;
            top:8px;
            left:12px;
            width:auto;
            height:20px;
        }

        .split-line2{
            width:100%;
            border-bottom: 1px dashed #E6E6E6;
            position: absolute;
            top:264px;
            left:0;
        }

        .upload-head{
            position: absolute;
            top:72px;
            right:164px;
            text-align: center;
        }

        .upload-head img{
            width:100px;
            height:100px;
        }

        .form-opt{
            margin-top:50px;
            padding-left:136px;
        }

        .btn-ok{
            width:186px;
            height:50px;
            background-color: #FEA600;
            border:none;
            border-radius: 32px;
            color:white;
            vertical-align: bottom;
            margin-right:20px;
        }

        .finish-line{
            border-color: #95D6E2;
        }

        .active-btn{
            background-color: #2CB7C9!important;
            color:white!important;
            border:1px solid #2CB7C9!important;
        }

        .sex button{
            padding-left:30px;
            padding-right:30px;
        }
table{
	width:500px;
}

    </style>
    
   
    </@block>

<#--中间正文部分-->
    <@block name="content">

   

    <div class="content">
       

        <div style="padding-left:10%">
        	基本信息<hr/>
        	
        	<form id="basicinfo">
        	<input type="hidden" id="basicId" value="${obj.basic.id}">
				  <table border="1">
					<tr>
						<td><b>姓名</b></td>
						<td><input type="text" id="realName" value="${obj.basic.realName}"/></td>
					</tr>
					<tr>
						<td><b>地区</b></td>
						<td><input type="text" id="areaName" value="${obj.basic.areaName}"/></td>
					</tr>
					<tr>
						<td><b>签名</b></td>
						<td><input type="text" id="memberSign" value="${obj.basic.memberSign}"/></td>
					</tr>
					
				</table>
         </form>
         <button id="savebasic">保存</button>
       		  <br/><br/>当前状态<hr/>
       		  <table border="1">
			<tr>
				<td><b>职位</b></td>
				<td>
				 <#list obj.eliteJobs as li >
                    	 <div class="work-label">
                            <div class="title-label">
                          		
                           		 <#if li.jobRole=="product" >
                            		 产品&nbsp;&nbsp;&nbsp;
                           		 <#elseif li.jobRole=="test">
                        		  	  测试&nbsp;&nbsp;&nbsp;
                            	 <#elseif li.jobRole=="engineer">
                            		工程师
                            	<#elseif li.jobRole=="designer">
                            		设计师	
                            	
                            </#if>
                               |&nbsp;&nbsp;&nbsp;等级：${li.level}</div>
                        </div>
                    </#list>
				</td>
			</tr>
			<tr>
				<td><b>关注行业<hr/></b></td>
				<td>
				
                   <#if obj.elite.getAttentionIndustryListVal() =="[null]">
                   				暂无
                  	 <#elseif obj.elite.getAttentionIndustryListVal() !="null">
                  				 ${obj.elite.getAttentionIndustryListVal()}
                   		
                   </#if>
                    
				</td>
			</tr>
			<tr>
				<td><b>相关工作年限</b></td>
				<td>
				
				
				${obj.elite.getJobAgeVal()}
				
				
					
				
				</td>
			</tr>
			<tr>
				<td><b>每周可分配时长</b></td>
				<td>
				${obj.elite.getAllotDurationVal()}
				</td>
			</tr>
			<tr>
				<td><b>是否在职</b></td>
				<td>
				<#if obj.elite.onjobed=="true">
							在职
						<#elseif obj.elite.onjobed=="false">
							离职
				</#if>
				
				</td>
			</tr>
		</table>
       		  
       		  
      		<br/><br/>项目经历<hr/>
      		
       <table border="1">
       <#list obj.getProjects() as pro >
			<tr>
				<td><b>项目名称</b></td>
				<td>
				
					${pro.project}
				
				</td>
			</tr>
			<tr>
				<td><b>项目描述</b></td>
				<td>${pro.intro}</td>
			</tr>
			<tr>
				<td><b>时间</b></td>
				<td>${pro.startTime} ~ ${pro.endTime}</td>
			</tr>
			</#list>
		</table>
		<br/><br/>教育经历<hr/>
		  <table border="1">
			<tr>
				<td><b>名称/学历</b></td>
				<td>
				
					<#list obj.getEducations() as edu>
						${edu.organ}/${edu.education}
					</#list>
				</td>
			</tr>
			
			
			
		</table>
      		
      		<br/><br/>征信信息<hr/>
      		 <table border="1">
			<tr>
				<td><b>身份证号</b></td>
				<td><span id="idcard">${obj.credit.idCard}</span></td>
			</tr>
			<tr>
				<td><b>姓名</b></td>
				<td>${obj.basic.realName}</td>
			</tr>
			
			
		</table>
      		
      		</br></br>账号设置<hr/>
      		 <table border="1">
			<tr>
				<td><b>支付宝账号</b></td>
				<td><span id="alipay">${obj.credit.alipayAccount}</span></td>
			</tr>
			<tr>
				<td><b>银行卡绑定</b></td>
				<td>${obj.credit.bankName}/ <span id="card">${obj.credit.bankCard}</span></td>
			</tr>
			<tr>
				<td><b>邮箱</b></td>
				<td>${obj.basic.email}</td>
			</tr>
			<tr>
				<td><b>手机号</b></td>
				<td><span id="mobile">${obj.credit.alipayAccount}</span></td>
			</tr>
			
		</table>
		<hr/>
        </div>

    </div>
   
    </@block>

    <@block name="script">
        <script  src="${_PATH}/res/script/myjs/member/elite/basic.js"></script>
        <script  src="${_PATH}/res/script/myjs/member/elite/main/myinfo.js"></script>
    </@block>
</@extend>
