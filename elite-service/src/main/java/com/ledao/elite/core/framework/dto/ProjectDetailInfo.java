package com.ledao.elite.core.framework.dto;

import java.math.BigDecimal;

import org.springframework.beans.BeanUtils;

import com.ledao.elite.core.domain.project.Project;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 项目详细信息
 * 
 * @author kobe.liu
 * @version 1.0
 * */
@Data
@EqualsAndHashCode(callSuper = false,of={})
@AllArgsConstructor
@NoArgsConstructor
public class ProjectDetailInfo extends Project{

	private static final long serialVersionUID = -3073565838684835701L;
	
	private BigDecimal totalTrustAmount;//合计托管费用
	private String auditedMemberCompany;//项目方被审核的状态
	private  String CTOPhone;//项目cto联系方式
	private  Boolean pmNewProjectNotice=false;// pm新分配项目提醒
	private  Boolean newWeeklyNotice=false;// 新周报提醒
	private  Boolean newMaterialNotice=false;// 新文件提醒
	private  Boolean newTenderNotice=false;// 投标提醒
	public ProjectDetailInfo(Project project){
		BeanUtils.copyProperties(project, this);
	}

}
