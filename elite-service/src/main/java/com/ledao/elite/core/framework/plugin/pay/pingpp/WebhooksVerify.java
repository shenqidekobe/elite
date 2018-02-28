package com.ledao.elite.core.framework.plugin.pay.pingpp;

import java.io.*;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.X509EncodedKeySpec;

import javax.annotation.Resource;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Component;

import com.ledao.elite.core.framework.config.PingPlusPlusConfig;

/**
 * webhooks签名验证
 * 
 * @author kobe.liu
 */
@Component("webhooksVerify")
public class WebhooksVerify {
	
	@Resource
	private PingPlusPlusConfig pingPlusPlusConfig;

	
	/**
	 * 验证签名
	 * 
	 * @param dataString
	 * @param signatureString
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public boolean verifyData(String dataString, String signatureString)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException,Exception {
		PublicKey publicKey=getPubKey();
		byte[] signatureBytes = Base64.decodeBase64(signatureString);
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(dataString.getBytes("UTF-8"));
		return signature.verify(signatureBytes);
	}
	
	/**
	 * 生成签名
	 * 
	 * @param dataString
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws InvalidKeyException
	 * @throws SignatureException
	 */
	public String createSignature(String dataString)
			throws NoSuchAlgorithmException, InvalidKeyException, SignatureException, UnsupportedEncodingException,Exception {
		PublicKey publicKey=getPubKey();
		Signature signature = Signature.getInstance("SHA256withRSA");
		signature.initVerify(publicKey);
		signature.update(dataString.getBytes("UTF-8"));
		return Base64.encodeBase64String(signature.sign());
	}
	

	/**
	 * 读取文件, 部署 web 程序的时候, 签名和验签内容需要从 request 中获得
	 * 
	 * @param filePath
	 * @return
	 * @throws Exception
	 */
	public static String getStringFromFile(String filePath) throws Exception {
		FileInputStream in = new FileInputStream(filePath);
		InputStreamReader inReader = new InputStreamReader(in, "UTF-8");
		BufferedReader bf = new BufferedReader(inReader);
		StringBuilder sb = new StringBuilder();
		String line;
		do {
			line = bf.readLine();
			if (line != null) {
				if (sb.length() != 0) {
					sb.append("\n");
				}
				sb.append(line);
			}
		} while (line != null);
		bf.close();
		return sb.toString();
	}

	/**
	 * 获得公钥
	 * 
	 * @return
	 * @throws Exception
	 */
	public PublicKey getPubKey() throws Exception {
		//String pubKeyString = getStringFromFile(pubKeyPath);
		String pubKeyString=pingPlusPlusConfig.getPingPublicKey();
		pubKeyString = pubKeyString.replaceAll("(-+BEGIN PUBLIC KEY-+\\r?\\n|-+END PUBLIC KEY-+\\r?\\n?)", "");
		byte[] keyBytes = Base64.decodeBase64(pubKeyString);

		// generate public key
		X509EncodedKeySpec spec = new X509EncodedKeySpec(keyBytes);
		KeyFactory keyFactory = KeyFactory.getInstance("RSA");
		PublicKey publicKey = keyFactory.generatePublic(spec);
		return publicKey;
	}

}
