package cn.itcast.demo3;

import java.io.IOException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class DigestUtils {
	//md5加密算法,单向加密，没有解密的
	
	public static String md5(String str){
		byte[] bytes = null;
		try {
			bytes = MessageDigest.getInstance("md5").digest(str.getBytes("utf-8"));//得到MD5的实例，再将字符串加密
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return new BigInteger(1,bytes).toString(16);//把字节数组转化为正的bigInteger,然后变成16进制表示
	}
	
	//base64算法,解密，加密
	public static String base64Encode(String str){
		BASE64Encoder baseEncode = new BASE64Encoder();
		return baseEncode.encode(str.getBytes());//加密
	}
	public static String base64Decode(String str){
		BASE64Decoder baseDecoder = new BASE64Decoder();
		try {
			return new String(baseDecoder.decodeBuffer(str));//解密
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
