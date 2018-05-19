package cn.bsnt.web.order.util;
/**
 * 该类提供对字符串的全角-->半角
 * @author Administrator
 *
 */
public class BCConvert {
	/**
	 * ASCII 表中可见字符从!开始,偏移位值为33(Decimal)
	 */
	public static final char DBC_CHAR_START = 33; //半角!
	/**
	 * ASCII 表中可见字符到~结束,偏移位值为126(Decimal)
	 */
	public static final char DBC_CHAR_END = 126; //半角~
	/**
	 * 全角对应于ASCII 表中字符从!开始,偏移值为65281
	 */
	public static final char SBC_CHAR_START = 65281; //全角!
	/**
	 * 全角对应于ASCII 表中字符到~结束,偏移位值为65374
	 */
	public static final char SBC_CHAR_END = 65374; //全角~
	/**
	 * ASCII 表中除空格外的可见字符与对应的全角字符的相对偏移量为65248
	 */
	public static final char CONVERT_STEP = 65248;
	
	/**
	 * 全角字符--->半角字符
	 * 只处理全角!到全角~之间的字符,忽略其他
	 */
	public static String qj2bj(String str){
		if(str==null){
			return str;
		}
		StringBuffer buf = new StringBuffer();
		char[] ch = str.toCharArray();
		for(int i=0;i<ch.length;i++){
			if(ch[i]>=SBC_CHAR_START && ch[i]<=SBC_CHAR_END){
				buf.append((char)(ch[i]-CONVERT_STEP));
			}else{
				buf.append(ch[i]);
			}
		}
		return buf.toString();
	}
}
