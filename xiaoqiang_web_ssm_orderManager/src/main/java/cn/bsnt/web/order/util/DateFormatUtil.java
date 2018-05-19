package cn.bsnt.web.order.util;

import java.util.Date;
import java.text.SimpleDateFormat;
/**
 * 该类是一个日期格式化器
 * @author Administrator
 *
 */
public class DateFormatUtil {
	public static String dateFormat(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	public static String dataFormat1(Date date){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
}
