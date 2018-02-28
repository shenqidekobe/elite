package com.ledao.elite.core.framework.constant;

/**
 * 附件常量定义
 * */
public class AttachmentsConstant {
	
	//附件服务器地址
	public static String ATTA_SERVER_PATH;
	
	//会员个人附件
	public enum MEMBER_ATTA_TYPE{
		MEMBER_PHOTO("会员头像");//会员头像
		private String label;
		MEMBER_ATTA_TYPE(String label){
            this.label = label;
        }
        public String getLabel(){
            return this.label;
        }
	}
	
	//项目相关附件
	public enum PROJECT_ATTA_TYPE{
		
	}
	
	//系统附件
    public enum SYSTEM_ATTA_TYPE{
		
	}

}
