package com.ledao.elite.core.framework.dto.attachment;

import java.io.Serializable;

import com.ledao.elite.core.framework.base.ResponseBase;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = false)
public class UploadResult extends ResponseBase implements Serializable{
	
	private static final long serialVersionUID = -2684643572231774839L;
	
	protected Long attaId;//附件ID
	protected String attaType;//附件类型
	protected String fileName;//上传后的文件名
	protected String originalName;//原始名称
	protected String url;//上传后的文件的http可访问的绝对路径

}
