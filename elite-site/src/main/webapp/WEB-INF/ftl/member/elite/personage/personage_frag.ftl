<div class="data relative">
	<div class="data-bg">
		<img src="${_PATH}/res/images/c-box-a.png" alt="">
	</div>
	<form id="personFrom" name="personFrom">
		<input type="hidden" value="" id="photoId" />
            <div class="logo_img_box" id="anchor0" title="支持jpg/jpeg/png/bim/bmp格式，文件小于2M">
	            <#if obj.basic.photoId??>
			       <img src="${obj.basic.memberPhoto.path}" id="headImg" alt=""/>
			    <#else>
			       <img  src="${_PATH}/res/images/default.jpg" id="headImg" alt=""/>
			    </#if>   
           </div>
		<input type="file" name="file" id="headFile" style="display:none;">
		<#--上传头像弹窗-->
        <div class="headWindow" style="display:none;" save="Y"></div>
	</form> 
	
	<div class="logo_icon_box">
	    <#if obj.elite.status=='normal'&&obj.elite.ctoed>
	    	<div class="cto_logo">CTO</div> 
		</#if>
		<#if obj.elite.status=='normal'><img src="${_PATH}/res/images/elite/badge.png" alt="" class="badge_img"></#if>
		<#--<img src="/res/images/elite/star.png" alt="" class="star_img">-->
	</div>
	<div class="c-box-b">
        <div class="box-b-content">
            <div class="b-content-a" id="anchor">
        	<div class="b-content-total">
        			<div class="elite_name" id="basicBox" style="display:block;">
    					<div class="editor_box" id="editBasic"><span class="editor editr"></span>编辑</div>
    					
    					<div class="name_a_box">
	    					<div class="name_a">${obj.nickName}</div>
	    					<#if obj.basic.sex??>
		    					<#if obj.basic.sex=='M'>
		    					    <img src="${_PATH}/res/images/ceo/man.png"  class="name_a_img">
								<#else>
								    <img src="${_PATH}/res/images/ceo/woman_icon.png"  class="name_a_img">
								</#if>
							</#if>	
    					</div>
    					
    					<div class="name_b">
    						<span class="name_b_work">${obj.basic.memberSign}</span>
        					<img src="${_PATH}/res/images/ceo/elite_city.png"  class="name_b_icon">
        					<span class="name_b_city">${obj.basic.areaName}</span>
    					</div>
    					
        			</div>
        			<div class="edit_content" id="basicContent" style="display:none"></div>
        	</div>
            <div class="b-content-a" id="anchor1">
                <div class="line-box">
                    <span class="line"></span>
                    <span class="line-btn">当前状态</span>
                    <span class="line"></span>
                    <p class="c-box-b-rt c-box-rt" id="editCurrent"><span class="editor editr"></span>编辑</p>
                </div>
                <div id="currentInfo"></div>
                <div class="box-txt" id="currentBox" style="display:block;">
                  	<div class="box-txt-left">
			                <span class="person-name title_name">擅长领域</span>
			                <#list obj.elite.eliteJobs as job>
			                	<div class="work-label">
				                    <div class="title-label">
				                    	<div class="title-text">${job.jobRoleVal}</div>
				                    
				                    	<div class="title-grade">L${job.level}</div>
			                    	</div>
				                </div>
			                </#list>
			                <p style="margin:15px 0 15px 66px;">
			                   <#list obj.elite.eliteJobs as job>
			                   		<#list job.jobAdeptValList as adept>
			                   			<span class="value">${adept.value}</span>
			                   		</#list>
			                   </#list>
			                </p>
	              		<div style="margin-top:14px;">
	                   	 	<p style="margin-bottom: 15px;">
	                            <span class="title_name">关注行业</span>
	                            	<#list obj.elite.attentionIndustryListVal as indus>
	                            		<span class="value">${indus}</span>
	                            	</#list>
	                            </span>
	                   		</p>
	                    	<p style="margin-bottom: 6px;" class="year_p">
	                            <span class="title_name">相关年限</span><span class="year_span">${obj.elite.jobAgeVal}</span>
	                    	</p>
	              		</div>
               		</div>
	               	<div class="box-txt-right">
	               		<p class="title_name">
	               			<#if obj.elite.onjobed>
	               				在职
	               			<#else>
	               				不在职	
	               			</#if>
						</p>
	               		<p class="time_p" style="margin-top:20px;"><span class="title_name">每周可分配时长 </span><span class="time_span">${obj.elite.allotDurationVal}</span></p>
	               	</div>
            	</div>
            </div>
            <div class="b-content-b" id="anchor2">
                <div class="line-box">
                    <span class="line"></span>
                    <span class="line-btn">自我描述</span>
                    <span class="line"></span>
                    <p class="c-box-b-rt c-box-rt" style="cursor:pointer" id="selfDescrip"><span class="editor editr"></span>编辑</p>
                </div>
                <div id="selfDescripInfo"></div>
                <div class="box-txt" id="selfDescripBox">
                	<p class="pbtn"><span>${obj.elite.intro}</span></p>
               	</div>	
            </div>
            <div class="b-content-b" id="anchor3">
                <div class="line-box" >
                    <span class="line"></span>
                    <span class="line-btn">项目经历</span>
                    <span class="line"></span>
                    <p class="c-box-b-rt c-box-rt" id="addProject"><span class="editor editrr"></span>添加</p>
                </div>
                <div id="projectBox">
                	<#assign n=1>
	                <#list obj.projects as project>
		                <div class="box-txt experience_div">
	                		<div class="pbtn company">${project.project}</div>
				            <div class="pbtn position">${project.position}</div>
				            <div class="pbtn time"><span>${project.startTime?string("yyyy-MM")}</span>-<span>${project.endTime?string("yyyy-MM")}</span></div>
	                		<div class="pbtn edit" style="cursor:pointer" id="editProject_${n}" data="${project.id}"><span class="editor editr"></span>编辑</div>
		            	</div>
		            	
		            	<div class="bot-txte experience_text">
		            		<div class="experience_text_content">
		            			${project.intro}
		            		</div>
		            	</div>
		            	<#assign n=n+1>
	            	</#list>
            	</div>
            	<div id="projectInfo"></div>
            </div>
            <div class="b-content-b" id="anchor4">
                <div class="line-box">
                    <span class="line"></span>
                    <span class="line-btn">教育经历</span>
                    <span class="line"></span>
                    <p class="c-box-b-rt c-box-rt" id="addEducation"><span class="editor editrr"></span>添加</p>
                </div>
                <div id="educationInfo" style="display:none"></div>
                <div class="box-txt" id="educationBox">
                	<#assign n=1>
                	<#list obj.educations as edu>
                	<div class="education_div">
	                  	<div class="pbtn education">${edu.organ}</div>
	                  	<div class="degree">${edu.educationVal}</div> 
	                  	<div class="pbtn time"><span>${edu.graduateTime?string("yyyy-MM")}</span>毕业</div>
	               		<div class="pbtn edit" id="editEducation_${n}" data="${edu.id}"><span class="editor editr"></span>编辑</div>
		             </div>
		               	<#assign n=n+1>
	               	</#list>
            </div>
            <div id="education"></div>
            <div class="b-content-b" id="anchor5">
                <div class="line-box clearfloat marginbottom50">
                    <span class="line"></span>
                    <span class="line-btn">征信信息</span>
                    <span class="line"></span>
                    <p class="c-box-b-rt c-box-rt" id="editCredit"><span class="editor editr"></span>编辑</p>
                </div>
                <div id="creditInfo"></div>
                <div  style="display:block;" id="creditBox">
                    <#if obj.credit.realName??>
                        <div class="pbtn"><span class="marginright20 title_name">身份证号:</span><span class="marginright20">${obj.credit.formatCard}</span><span>${obj.basic.formatName}</span></div>
                    </#if>
                    <div class="certificate">
	                    <#if obj.credit.realName??>
	                        <p class="pbtn title_name">证件上传</p>
	                    </#if>
                        <ul>
                            <#if obj.credit.cardJust??>
	                            <li>
	                            	<div class="idbox">
			                               <img src="${obj.credit.cardJustPhoto.path}" style="width:160px;height:100px;">
									</div>
								</li>
							</#if>
                            <#if obj.credit.cardReverse??>
	                            <li>
		                            <div class="idbox">
			                               <img src="${obj.credit.cardReversePhoto.path}" style="width:160px;height:100px;">
		                            </div>
	                            </li>
                            </#if>
                            <#if obj.credit.businessCert??>
	                            <li>
		                            <div class="idbox">
			                               <img src="${obj.credit.businessCertPhoto.path}" style="width:160px;height:100px;">
		                            </div>
	                            </li>
                            </#if>
                            <#if obj.credit.visitingCert??>
	                            <li>
		                            <div class="idbox">
			                               <img src="${obj.credit.visitingCertPhoto.path}" style="width:160px;height:100px;">
		                            </div>
	                            </li>
                            </#if>
                        </ul>
                    </div>
                </div>
            </div>
</div>
