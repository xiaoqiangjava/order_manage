package cn.bsnt.web.order.util;

import java.math.BigDecimal;

/**
 * 该类用于计算商品的成本价
 * FIRST_COST (单件成本价)=渠道价（新增加）+运费（新增加）+PRICE (单件平台售价)*3%+PRICE (单件平台售价)*0.55%
 * @author Administrator
 *
 */
public class FirstPriceUtil {
	public static Double calFirstPrice(String channelPrice,String farePrice,String price){
		BigDecimal bd1 = new BigDecimal(channelPrice);
		BigDecimal bd2 = new BigDecimal(farePrice);
		BigDecimal bd3 = new BigDecimal(price);
		BigDecimal bd4 = new BigDecimal("0.0355");
		BigDecimal bdPrice = (bd1.add(bd2)).add(bd3.multiply(bd4));
		return bdPrice.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
	}
	public static void main(String[] args) {
		String channelPrice = "39.99";
		String farePrice = "10.06";
		String price = "78.98";
		Double firstPrice = calFirstPrice(channelPrice, farePrice, price);
		System.out.println(firstPrice);
	}
}
