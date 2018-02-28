package com.ledao.elite.core.domain.project;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;
import com.ledao.elite.core.utils.DateTimeUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 项目立项书对象
 * 
 */
@Data
@Entity
@EqualsAndHashCode(callSuper = false, of = {})
@ToString(callSuper = true, of = {})
public class ProjectDefine extends IdentifiedEntity {

	private static final long serialVersionUID = -3648926544588870174L;

	// 立项书状态值定义
	public enum ProjectDefine_Status {
		wait("待确认"), normal("已确认"), cancel("已取消");
		public String label;

		ProjectDefine_Status(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	// 立项书类型
	public enum ProjectDefine_Type {
		company("给项目方的立项书"), cto("给CTO的立项书");
		public String label;

		ProjectDefine_Type(String label) {
			this.label = label;
		}

		public String getLabel() {
			return label;
		}
	}

	private Long projectId;// 项目ID
	private Long memberId;// 立项书的会员ID
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private ProjectDefine_Type defineType;// 立项书类型
	@Lob
	private String clause;// 立项条款
	private BigDecimal totalAmount;// 研发总费用
	private BigDecimal stock=new BigDecimal(0);// 股权
	@Enumerated(EnumType.STRING)
	@Column(length = 32)
	private ProjectDefine_Status status;// 状态
	@Column(length = 512)
	private String otherDesc;// 其他说明
	@Temporal(TemporalType.DATE)
	private Date deliveryTime;// 确定交付时间
	private double downTime = 0;// 立项书确认时间限制{24小时、2天}，单位：小时
	@Temporal(TemporalType.TIMESTAMP)
	private Date affirmTime;// 立项书确认时间
	@Temporal(TemporalType.TIMESTAMP)
	private Date recallTime;// 立项书撤回时间
	private Long attaId;// 附件ID
	
	
	//是否有股权
	public Boolean getIsStock() {
		return stock.compareTo(BigDecimal.ZERO)==1;
	}
	//股权百分比,两位小数
	public String getStockPercent(){
	    NumberFormat percent = NumberFormat.getPercentInstance();
        percent.setMaximumFractionDigits(2);
        return percent.format(stock.doubleValue());
	}

	// 立项书确认时间倒计时{精确到秒}
	public long getDefineCountdown() {
		long currentTime = System.currentTimeMillis();
		if (new BigDecimal(downTime).compareTo(BigDecimal.ZERO) == 0) {
			return 0l;
		}
		long createTime = this.getCreateTime().getTime();
		long offtime = currentTime - createTime;
		long cd = ((long) downTime * 3600) - (offtime / 1000);
		return cd;
	}


	/************************* hibernate many one config**************************/
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "projectId", insertable = false, updatable = false)
	private Project project;// 项目

	@OrderBy("orders asc")
	@OneToMany(mappedBy = "projectDefine", fetch = FetchType.LAZY)
	private List<ProjectDefineStage> stages;// 立项书阶段
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "attaId", insertable = false, updatable = false)
	private Attas atta; //附件
	
	//期望交付天数
	public Integer getDeliveryDay() {
		return DateTimeUtils.dateSub(deliveryTime, project.getStartTime());
	}
	/*
	 * @OrderBy("createTime desc")
	 * 
	 * @Where(clause="type='project_define_atta'")
	 * 
	 * @OneToMany(mappedBy = "project") private List<ProjectAtta> attas;//立项书附件
	 */
}
