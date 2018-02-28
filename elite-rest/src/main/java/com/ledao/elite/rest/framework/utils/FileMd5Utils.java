package com.ledao.elite.rest.framework.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


public class FileMd5Utils {  
    
    public final static String[] hexDigits = { "0", "1", "2", "3", "4", "5",  
        "6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };  
      
    /** 
     * 获取文件的MD5值 
     * @param file 
     * @return 
     */  
    public static String getFileMD5(File file){  
        String md5 = null;  
        FileInputStream fis = null;  
        FileChannel fileChannel = null;  
        try {  
            fis = new FileInputStream(file);  
            fileChannel = fis.getChannel();  
            MappedByteBuffer byteBuffer = fileChannel.map(FileChannel.MapMode.READ_ONLY, 0, file.length());  
              
            try {  
                MessageDigest md = MessageDigest.getInstance("MD5");  
                md.update(byteBuffer);  
                md5 = byteArrayToHexString(md.digest());  
            } catch (NoSuchAlgorithmException e) {  
                  
                e.printStackTrace();  
            }  
        } catch (FileNotFoundException e) {  
              
            e.printStackTrace();  
        } catch (IOException e) {  
              
            e.printStackTrace();  
        }finally{  
            try {  
                fileChannel.close();  
                fis.close();  
            } catch (IOException e) {  
                  
                e.printStackTrace();  
            }  
        }  
          
        return md5;  
    }  
    
    private static String byteArrayToHexString(byte[] digest) {  
        
        StringBuffer buffer = new StringBuffer();  
        for(int i=0; i<digest.length; i++){  
            buffer.append(byteToHexString(digest[i]));  
        }  
        return buffer.toString();  
    }  
    private static String byteToHexString(byte b) {  
         int d1 = (b&0xf0)>>4;  
         int d2 = b&0xf;  
         return hexDigits[d1] + hexDigits[d2];  
    } 
   
}
