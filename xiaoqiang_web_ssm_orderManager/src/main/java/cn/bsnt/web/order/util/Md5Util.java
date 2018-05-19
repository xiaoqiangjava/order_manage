package cn.bsnt.web.order.util;

import org.apache.commons.codec.digest.DigestUtils;

public class Md5Util {
	public final static String SALT = "百事农通后台管理密码加盐";
	
	public static String md5Salt(String data){
		return DigestUtils.md5Hex(data);
	}
}
