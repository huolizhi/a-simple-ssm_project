package lz;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {
	/**
	 * 使用md5的算法进行加密
	 */
	public static String md5(String plainText) {
		byte[] secretBytes = null;
		try {
			secretBytes = MessageDigest.getInstance("md5").digest(
					plainText.getBytes());
		} catch (NoSuchAlgorithmException e) {
			throw new RuntimeException("没有md5这个算法！");
		}
		String md5code = new BigInteger(1, secretBytes).toString(16);// 16进制数字
		// 如果生成数字未满32位，需要前面补0
		for (int i = 0; i < 32 - md5code.length(); i++) {
			md5code = "0" + md5code;
		}
		return md5code;
	}

	public static void main(String[] args) {
		//md5
		//System.out.println(md5("1234"));

		/*//md5Hash shiro
		Md5Hash md5Hash=new Md5Hash("123","sss11",55);
		System.out.println(md5Hash);*/

		//spring-security加密
		/*
		* $2a$10$ttMAbB8Cs0xAbWONJIG99ufhSgzHxpVZ3ovENsnnzmV6dYhfFMjlO
		*$2a$10$1l3jd7LIoXQIoiGA7HTyZeaeE3gtLh18cyVniRrYDUZliQRVrWfkG
		* */
		BCryptPasswordEncoder bc=new BCryptPasswordEncoder();
		String encode = bc.encode("123");
		System.out.println(encode);
		boolean bool = bc.matches("123", "$2a$10$ttMAbB8Cs0xAbWONJIG99ufhSgzHxpVZ3ovENsnnzmV6dYhfFMjlO");
		System.out.println(bool);

	}

}