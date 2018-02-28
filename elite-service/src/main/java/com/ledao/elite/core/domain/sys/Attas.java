package com.ledao.elite.core.domain.sys;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;

import com.ledao.elite.core.domain.IdentifiedEntity;
import com.ledao.elite.core.framework.constant.AttachmentsConstant;
import com.ledao.elite.core.utils.CommonUtils;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


/**
 * 附件实体
 * 
 * @author kobe.liu
 * */
@Data
@Entity
@EqualsAndHashCode(callSuper = false,of={})
@ToString(callSuper = true, of = {})
public class Attas extends IdentifiedEntity{
	
	private static final long serialVersionUID = -629594925886253862L;
	
	public static final String UPLOAD_ROOT_PATH="/elite-upload";//文件上传的存储根目录定义
	//上传附件的用户类型定义
	public enum UPLOAD_USER_TYPE{
		CEO,CTO,ELITE,HEADHUNT,SYSTEM
	}
	
	@Column(length=128)
	private String attaName;//附件文件名称
	@Column(length=64)
	private String fileName;//附件原始文件名称
	@Column(length=32)
	private String attaType;//附件类型(文件后缀名)
	private Long attaLength;//附件大小
	@Column(length=64)
	private String attaPath;//附件存储相对路径
	//@Enumerated(EnumType.STRING)
	@Column(length=32)
	private String status;//状态
	@Column(length=32)
	private String userType;//上传附件的用户类型
	@Type(type = "yes_no")
	private boolean reviewed=false;//是否可在线预览
	@Type(type = "yes_no")
	private boolean downloaded=false;//是否可下载
	private Long createId;//上传者用户ID
	private Long updateId;//修改者用户ID
	@Temporal(TemporalType.TIMESTAMP)
	private Date removeTime;//删除时间
	private Long removeId;//删除者用户ID
	
	//文件大小
	@Transient
	public String getFileSize(){
		return CommonUtils.convertFileSize(attaLength);
	}
	
	//文件路径
	public String getPath(){
		return AttachmentsConstant.ATTA_SERVER_PATH+"/"+UPLOAD_ROOT_PATH+"/"+attaPath+"/"+attaName;
	}
	//文件路径
	public String getDownPath(){
		return AttachmentsConstant.ATTA_SERVER_PATH+"/download/"+getId();
	}

}
