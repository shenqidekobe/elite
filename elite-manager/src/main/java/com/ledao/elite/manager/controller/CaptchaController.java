package com.ledao.elite.manager.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 验证码生成
 * */
@Controller
@RequestMapping("/captcha")
public class CaptchaController extends BaseController{
	

	private static final String CAPTCHA_UPPER = "upper";
	private static final String CAPTCHA_LOWER = "lower";

	/** 描述  */
	private int height = 30;
	private int width = 100;
	private int length = 4;
	private String upperOrLower = CAPTCHA_UPPER;

	public static final String CAPTCHA_KEY = "captcha";
	
	/**
	 * 生成验证码
	 * */
	@RequestMapping("/produce")
	public void produceCaptcha(HttpServletRequest request,HttpServletResponse response){
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setHeader("Pragma", "no-cache");
		response.setDateHeader("Max-Age", 0);

		BufferedImage image = new BufferedImage(width, height,
				BufferedImage.TYPE_INT_RGB);
		Graphics2D graphics2D = image.createGraphics();
		Random r = new Random(System.currentTimeMillis());
		//String token = Long.toString(Math.abs(r.nextLong()), 36);
		String token = Long.toString(Math.abs(System.currentTimeMillis() + r.nextLong()), 36);
		// 截取长度
		if (length > 12 || length < 1) {
			length = 12;
		}
		String ch = token.substring(0, length);
		// 大小写转换
		if (upperOrLower.equals(CAPTCHA_UPPER)) {
			ch = ch.toUpperCase();
		} else if (upperOrLower.equals(CAPTCHA_LOWER)) {
			ch = ch.toLowerCase();
		}
		Color c = new Color(0.6662f, 0.4569f, 0.3232f);
		GradientPaint gp = new GradientPaint(30, 30, c, 15, 25, Color.lightGray,
				true);
		graphics2D.setPaint(gp);
		Font font = new Font("Verdana", Font.CENTER_BASELINE, 26);
		graphics2D.setFont(font);
		graphics2D.drawString(ch, 2, 20);
		graphics2D.dispose();

		HttpSession session = request.getSession(true);
		session.setAttribute(CAPTCHA_KEY, ch);
		logger.info("captcha = "+ch);

		OutputStream outputStream;
		try {
			outputStream = response.getOutputStream();
			ImageIO.write(image, "jpeg", outputStream);
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
