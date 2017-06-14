package com.skb.course.common.util;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;

import org.apache.commons.codec.binary.Base64;

public class DESCode {

	private String charSet = "utf-8";

	private String key = "7KB86855YJ2FJSD823KX8QB8";

	public static DESCode des = null;

	public static DESCode getInstance() {
		if (des == null) {
			synchronized (DESCode.class) {
				if (des == null) {
					des = new DESCode();
				}
			}
		}
		return des;
	}

	public String encryptThreeDESECB(String src) throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(charSet));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey securekey = keyFactory.generateSecret(dks);

		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.ENCRYPT_MODE, securekey);
		byte[] b = cipher.doFinal(src.getBytes(charSet));
		return new String(Base64.encodeBase64(b), charSet);

	}

	public String decryptThreeDESECB(String src) throws Exception {
		DESedeKeySpec dks = new DESedeKeySpec(key.getBytes(charSet));
		SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		SecretKey securekey = keyFactory.generateSecret(dks);
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, securekey);
		byte[] retByte = cipher.doFinal(Base64.decodeBase64(src.getBytes(charSet)));
		return new String(retByte, charSet);
	}

	public static String videoEncrypt(String src) throws Exception {
		return DESCode.getInstance().encryptThreeDESECB(src);
	}

	public static void main(String[] args) {
		try {
			DESCode des = new DESCode();
			// http://v1.jiyoutang.com/source/video/gaokaozhenti/2014/lishu/beijingjuan/2.flv"
			// http://www.daydays.com/source/1.flv.flv

			// z78VYRKFVT1MHMm2tbKXBH8ieHyFEumxd5YCGS7PMGIVAaZMNDfJIEPPfuj2A=
			// http://www.jiyoutang.com/source/teacherMain/pptVideo/video/tanjie1.mp4
			String OldStr = "liKG6YGKA+0=";
			String encode = des.decryptThreeDESECB(OldStr);
			System.out.println(encode);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
