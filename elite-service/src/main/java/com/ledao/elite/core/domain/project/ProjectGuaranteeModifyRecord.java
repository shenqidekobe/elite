package com.ledao.elite.core.domain.project;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 质保期bug修改的记录对象
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class ProjectGuaranteeModifyRecord extends IdentifiedEntity{

	private static final long serialVersionUID = 5277074111879508133L;
	
	private Long parendId;//上级ID
	
	private Long pgmId;//质保期修改ID
	
	@Column(length=256)
	private String intro;//描述
	
	private Long attaId;//附件ID
	
	@Column(length=32)
	private String processResult;//处理结果
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date processTime;//处理时间
	
	private Long processId;//处理者ID
	
	/************************hibernate many one config**************************/
    @ManyToOne
	@JoinColumn(name = "pgmId", insertable=false, updatable=false)
    private ProjectGuaranteeModify guaranteeModify;//质保期修改记录
	

}
