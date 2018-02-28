package com.ledao.elite.core.framework.plugin.email;

/**
 * 邮箱服务接口
 * 
 * @author kobe.liu
 * @version 1.0
 * */
public interface EmailService {
	
	/**
	 * 发送邮件
	 * @param toMail
	 *           接收者 
	 *   
	 * @param subject
	 *            主题
	 * @param text
	 *            数据
	 * @param async
	 *            是否异步
	 */
	void send(String toMail,String subject, String text, boolean async);
	
	/**
	 * 群发邮件
	 * @param toMail[]
	 *           接收 
	 *   
	 * @param subject
	 *            主题
	 * @param text
	 *            数据
	 * @param async
	 *            是否异步
	 */
	void sends(String[] toMail,String subject, String text, boolean async);

	/**
	 * 发送邮件
	 * 
	 * @param subject
	 *            主题
	 * @param text
	 *            数据
	 * @param async
	 *            是否异步
	 */
	void send(String subject, String text, boolean async);

	/**
	 * 发送邮件(异步)
	 * 
	 * @param subject
	 *            主题
	 * @param text
	 *            数据
	 */
	void send(String subject, String text);

}
