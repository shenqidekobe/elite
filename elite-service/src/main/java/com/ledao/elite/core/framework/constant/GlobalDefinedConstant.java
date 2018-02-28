package com.ledao.elite.core.framework.constant;

/**
 * 全局系统常量定义
 */
public class GlobalDefinedConstant {

	// 全局参数定义
	public final static String SUCCESS = "SUCCESS";
	public final static String FAILURE = "FAILURE";
	public final static String EXCEPTION = "EXCEPTION";
	public final static String UNAUTHORIZED = "UNAUTHORIZED";
	public final static String NOLOGIN = "NOLOGIN";
	
	
	/**
	 * 系统使用符号定义
	 * */
	public final static class System_Symbol{
		public static final String JOINT_SEPARATOR=",";   //字符串分隔符
		public static final String LINE_SYMBOL="\r\n";    //换行符
		public static final String SPACING_SYMBOL="\t";  //间隔符
		public static final String SPACING_SPACING=" ";  //空格
	}

	/**
	 * 系统常量定义
	 */
	public final static class System_Constant {
		public final static Integer SMS_EXPIRE_TIME = 10;// 短信过期时间，分钟
	}

	/**
	 * 数据操作类型定义 {上移、下移、置顶,推送}
	 */
	public enum Data_Operate_Type {
		up, down, stick, push
	}

	/**
	 * 系统公用状态定义 {正常、被禁用、已删除}
	 */
	public enum System_Status {
		normal, disabled, deleted
	}
	
	/**
	 * 编码类型
	 * */
	public enum Encoding_Type{
		iso("ISO-8859-1"),
		gbk("GBK"),
		utf8("UTF-8");
		public String val;
		Encoding_Type(String val){
			this.val=val;
		}
		public String getVal(){
			return this.val;
		}
	}
	
	/**
	 * 系统时间格式
	 * */
	public enum System_Date_Patten{
		TIMESTAMP("yyyy-MM-dd HH:mm:ss:SSS","时间戳"),
		DATETIME("yyyy-MM-dd HH:mm:ss","时间"),
		DATE("yyyy-MM-dd","某天"),
		TIME("HH:mm:ss","某时");
		public String patten;
		public String desc;
		System_Date_Patten(String patten,String desc){
			this.patten=patten;
			this.desc=desc;
		}
		
	}

	/**
	 * 平台合作类型 {企业、个人}
	 */
	public enum Cooperate_Type {
		salcompany("销售公司"), 
		incubator("孵化器"), 
		other("其他");
		public String label;

		Cooperate_Type(String label) {
			this.label = label;
		}
		public String getLabel() {
			return label;
		}
	}
	
	//支付渠道
	public enum Pay_Channel{
		offline("线下转账"),
		alipay("支付宝 "),
		cp_b2b("银联企业网银"),
		upacp("银联APP支付"),
		upacp_pc("银联网页支付"),
		upacp_wap("银联手机网页支付"),
		wx("微信APP支付"),
		wx_pub_qr("微信扫码");
		public String label;
		Pay_Channel(String label){
			this.label=label;
		}
		public String getLabel(){
			return label;
		}
	}
	
	//支付方式
	public enum Pay_Way{
		online("线上转账"),
		offline("线下转账");
		public String label;
		Pay_Way(String label){
			this.label=label;
		}
		public String getLabel(){
			return this.label;
		}
	}
	
	//支付状态
	public enum Pay_Status{
		paying("支付中"),
		success("支付成功"),
		failure("支付失败"),
		expire("支付过期");
		public String label;
		Pay_Status(String label){
			this.label=label;
		}
	}
	
	//数据操作类型
	public enum Data_Oper{
		sum("累加"),
		sub("相减"),
		mulit("乘法"),
		divi("除法");
		public String label;
		Data_Oper(String label){
			this.label=label;
		}
	}

	/**
	 * 是否定义
	 */
	public final static class TRUE_FALSE_DEFINE {
		public final static Integer STATE_YES = 1;
		public final static Integer STATE_NO = 0;
	}

	/**
	 * JS常量定义
	 */
	public final static class JS_DEFINED {
		public final static String FLAG_ASYNC = "is_async";// 异步标识
		public final static String RESULTID = "RESULT_ID";// 异步请求头resultId

		public final static class JS_RESULT {
			public final static String SUCCESS = "SUCCESS";
			public final static String ERROR = "ERROR";
			public final static String FAIL = "FAIL";
		}
	}
}
