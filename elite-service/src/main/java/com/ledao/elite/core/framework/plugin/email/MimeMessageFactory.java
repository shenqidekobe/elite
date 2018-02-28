package com.ledao.elite.core.framework.plugin.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class MimeMessageFactory {
	
	private JavaMailSenderImpl javaMailSender;
	
	private TaskExecutor mailTaskExecutor;
	
	private MailPropertys mailPropertys;
	
	private MimeMessage mimeMessage;
	
	private MimeMessageHelper messageHelper;
	
	public MimeMessageFactory(JavaMailSenderImpl javaMailSender,TaskExecutor mailTaskExecutor,MailPropertys mailPropertys){
		this.javaMailSender=javaMailSender;
		this.mailTaskExecutor=mailTaskExecutor;
		this.mailPropertys=mailPropertys;
		
		mimeMessage=this.javaMailSender.createMimeMessage();
		
		try {
			messageHelper=new MimeMessageHelper(mimeMessage,true,mailPropertys.getMailToEncoding());
		} catch (MessagingException e1) {
			e1.printStackTrace();
		}
		try {
			messageHelper.setTo(this.mailPropertys.getMailToAddress());
			messageHelper.setFrom(this.mailPropertys.getMailFormAddress());
		} catch (MessagingException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 添加邮件发送任务
	 * 
	 * @param mimeMessage
	 *            MimeMessage
	 */
	public void addSendTask(final MimeMessage mimeMessage) {
		try {
			mailTaskExecutor.execute(new Runnable() {
				public void run() {
					javaMailSender.send(mimeMessage);
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void send(MimeMessage mimeMessage){
		this.javaMailSender.send(mimeMessage);
	}

	
	public MimeMessage getMimeMessage() {
		return mimeMessage;
	}

	public void setMimeMessage(MimeMessage mimeMessage) {
		this.mimeMessage = mimeMessage;
	}



	public MimeMessageHelper getMessageHelper() {
		return messageHelper;
	}

	public void setMessageHelper(MimeMessageHelper messageHelper) {
		this.messageHelper = messageHelper;
	}

	public JavaMailSenderImpl getJavaMailSender() {
		return javaMailSender;
	}

	public void setJavaMailSender(JavaMailSenderImpl javaMailSender) {
		this.javaMailSender = javaMailSender;
	}

	public TaskExecutor getMailTaskExecutor() {
		return mailTaskExecutor;
	}

	public void setMailTaskExecutor(TaskExecutor mailTaskExecutor) {
		this.mailTaskExecutor = mailTaskExecutor;
	}

	public MailPropertys getMailPropertys() {
		return mailPropertys;
	}

	public void setMailPropertys(MailPropertys mailPropertys) {
		this.mailPropertys = mailPropertys;
	}
	
	
	
}
