package com.ledao.elite.core.framework.plugin.email.impl;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.ledao.elite.core.framework.plugin.email.EmailService;
import com.ledao.elite.core.framework.plugin.email.MimeMessageFactory;

import lombok.extern.java.Log;

/**
 * 新浪邮箱服务
 * */
@Log
@Service
public class SinaEmailServiceImpl implements EmailService{
	
	@Resource(name="mimeMessageFactory")
	private MimeMessageFactory mimeMessageFactory;
	
	public void send(String toMail,String subject, String text, boolean async){
		Assert.hasText(subject);
		Assert.hasText(text);
		try {
			text=processMailTemplate(subject, text);
			MimeMessageHelper messageHelper=this.mimeMessageFactory.getMessageHelper();
			messageHelper.setSubject(subject);
			messageHelper.setText(text, true);
			/*FileSystemResource attachment=new FileSystemResource(new File("E:\\temp9257.docx"));
            messageHelper.addAttachment(MimeUtility.encodeWord("测试邮件"), attachment);   */
			if(StringUtils.isNotEmpty(toMail)){
				messageHelper.setTo(toMail);
			}
			if (async) {
				mimeMessageFactory.addSendTask(messageHelper.getMimeMessage());
			} else {
				this.mimeMessageFactory.send(messageHelper.getMimeMessage());
			}
			log.info("邮件发送成功.");
		} catch (Exception e) {
			log.info("邮件发送失败."+e);
		}
	}

	public void send(String subject, String text, boolean async) {
		send(null, subject, text, async);
		
	}

	public void send(String subject, String text) {
		send(subject, text, true);
	}
	
	public void sends(String[] toMail,String subject, String text, boolean async){
		Assert.hasText(subject);
		Assert.hasText(text);
		Assert.notEmpty(toMail);
		try {
			text=processMailTemplate(subject, text);
			MimeMessageHelper messageHelper=this.mimeMessageFactory.getMessageHelper();
			messageHelper.setSubject(subject);
			messageHelper.setText(text, true);
			messageHelper.setTo(toMail);
			if (async) {
				mimeMessageFactory.addSendTask(messageHelper.getMimeMessage());
			} else {
				this.mimeMessageFactory.send(messageHelper.getMimeMessage());
			}
			log.info("邮件发送成功.");
		} catch (Exception e) {
			log.info("邮件发送失败."+e);
		}
	}
	
	/**
	 * 邮件模版
	 * */
	private String processMailTemplate(String subject,String text){
		StringBuffer sb=new StringBuffer();
		sb.append("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">");
		sb.append("<html xmlns=\"http://www.w3.org/1999/xhtml\">");
		sb.append("<head>");
		sb.append("<meta http-equiv=\"content-type\" content=\"text/html; charset=utf-8\" />");
		sb.append("<title>"+subject+"</title>");
		sb.append("<meta name=\"author\" content=\"云英汇平台\" />");
		sb.append("<meta name=\"copyright\" content=\"云英汇技术中心\" />");
		sb.append("</head>");
		sb.append("<body>");
		sb.append("<strong>"+subject+"</strong>");
		sb.append("<p>"+text+"</p>");
		sb.append("</body>");
		sb.append("</html>");
		return sb.toString();
	}
}
