package cn.bsnt.web.order.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/**
 * 该类用于获取配置文件中的值
 * @author Administrator
 *
 */
public class GetOssConfig {
	/**
	 * 根据config文件的key读取到其对应的value
	 * @param key key值
	 * @return 对应的value
	 */
	public static String getConfigValue(String key){
		InputStream input = null;
		String value = null;
			try {
				input = GetOssConfig.class.getClassLoader().getResourceAsStream("config/ossClient.properties");
				Properties ps = new Properties();
				ps.load(input);
				value = ps.getProperty(key);
			} catch (Exception e) {
				e.printStackTrace();
			} finally{
				if(input!=null){
					try {
						input.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		return value;
	}
	
	public static void main(String[] args) {
		String value = getConfigValue("oss.accessKeyId");
		System.out.println(value);
	}
}
