package com.ledao.elite.core.utils;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import lombok.extern.java.Log;

import org.springframework.web.multipart.MultipartFile;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 按规定大小缩略图
 * */
@SuppressWarnings("restriction")
@Log
public class Thumbnail {
	
	/**目标文件**/
	private static File destFile;
	
    private static Image img;   
    
    /**
     * 创建缩略图
     * @param sourceFile
     * @param targetFile
     * @param width
     * @param height
     * */
    public static void thumbnailImg(File sourceFile,File targetFile,int width,int height) throws IOException{
 		img = javax.imageio.ImageIO.read(sourceFile); // 构造Image对象
		int w = img.getWidth(null); // 得到源图宽
		int h = img.getHeight(null); // 得到源图长
		log.info("开始创建缩略图，获取原图的宽度width="+w+",高度height="+h+",目标缩略宽高："+width+"-"+height);
		destFile = targetFile;
		resizeFix(w, h,width,height);
    }
    
    
    
    /**
     * 创建缩略图
     * @param sourceFile
     * @param targetFile
     * @param width
     * @param height
     * */
    public static void thumbnailImg1(MultipartFile sourceFile,File targetFile,int width,int height) throws IOException{
 		img = javax.imageio.ImageIO.read(sourceFile.getInputStream()); // 构造Image对象
		int w = img.getWidth(null); // 得到源图宽
		int h = img.getHeight(null); // 得到源图长
		log.info("开始创建缩略图，获取原图的宽度width="+w+",高度height="+h+",目标缩略宽高："+width+"-"+height);
		destFile = targetFile;
		resizeFix(w, h,width,height);
    }
	
	/**
     * 按照规定大小缩略
     * @param w int 原图宽度
     * @param h int 原图高度
     * @param width 目标高度
     * @param height 目标高度
     * @throws IOException
     */
	private static void resizeFix(int w, int h,int width,int height) throws IOException {
		float tagsize = 0;
		if(w>h){
			tagsize=width;
		}else{
			tagsize=height;
		}
		int new_w = 0;
		int new_h = 0;
		if(w>=width){
			float tempdouble;
			tempdouble = w > h ?w / tagsize: h / tagsize;
			new_w = Math.round(w / tempdouble);
			new_h = Math.round(h / tempdouble);
		}else{
			new_w = w;
			new_h = h;
		}
		resize(new_w, new_h);
	}
	
	 /**
     * 压缩/放大图片到固定的大小
     * @param w int 新宽度
     * @param h int 新高度
     * 
     * @throws IOException
     */
	private static void resize(int w, int h) throws IOException {
      BufferedImage _image = new BufferedImage(w, h,
                                               BufferedImage.TYPE_INT_RGB);
      _image.getGraphics().drawImage(img.getScaledInstance(w, h, Image.SCALE_SMOOTH), 0, 0, null); //绘制缩小后的图
      FileOutputStream out = new FileOutputStream(destFile); //输出到文件流
      JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
      encoder.encode(_image); //近JPEG编码
      out.close();
    }   
	
	public static void main(String[] args) throws Exception{
		destFile=new File("E:/a.jpg");
		File targetFile = new File("E:/a_min.jpg");
        int width=450;
        int height=300;
		Thumbnail.thumbnailImg(destFile, targetFile, width, height);
	}
	
}