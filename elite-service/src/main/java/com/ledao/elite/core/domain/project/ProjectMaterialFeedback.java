package com.ledao.elite.core.domain.project;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 会员提交的文件记录后的反馈记录对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectMaterialFeedback extends IdentifiedEntity{

	private static final long serialVersionUID = -6660410206199204515L;
	
	//文件记录反馈状态
	public enum MaterialFeedback_Status{
		normal("正常");
		public String label;
		MaterialFeedback_Status(String label){
			this.label=label;
		}
	}
	
	private Long parentId;//上级ID
	private Long materialId;//文件ID
	@Column(length=1000)
	private String content;//反馈内容
	private Long attaId;//反馈提交的附件
	@Enumerated(EnumType.STRING)
	@Column(length=32)
	private MaterialFeedback_Status status;//状态
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "materialId", insertable=false, updatable=false)
    private ProjectMaterial material;//项目文件
	
	
}
