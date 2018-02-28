package com.ledao.elite.core.domain.platform;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.domain.sys.Attas;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 平台营销策略数据
 * 
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class PlatforMarketing extends IdentifiedEntity {

	private static final long serialVersionUID = 6628042455821711983L;
	
	//营销数据的使用平台
	public enum Marketing_Platform{
		pc,app,wechat
	}
	
	//营销数据的类型
	public enum Marketing_Type{
		launch("启动页"),
		guide("引导页"),
		banner("首页旗帜"),
		activity("活动");
		public String label;
		Marketing_Type(String label){
			this.label=label;
		}
		public String getLabel() {
			return label;
		}
	}
	
	public enum Role_Channel{
		elite("精英方"),
		company("项目方");
		public String label;
		Role_Channel(String label){
			this.label=label;
		}
		public String getLabel() {
			return label;
		}
	}
	
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private Marketing_Platform usePlatform;//使用的平台
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private Marketing_Type type;//营销类型
	@Enumerated(EnumType.STRING)
	@Column(length=16)
	private Role_Channel channel;//角色频道
	@Column(length=64)
	private String title;//标题
	private Long logoId;//图片ID
	@Column(length=256)
	private String href;//链接地址
	@Lob
	private String content;//内容
	private int orders;//排序号
	private Long createId;//创建者
	private Long updateId;//修改者
	
	
	@Transient
	private String createName;//创建者用户名
	@Transient
	private String updateName;//更新者用户名
	@ManyToOne
   	@JoinColumn(name = "logoId", insertable=false, updatable=false)
    private Attas atta;//附件

}
