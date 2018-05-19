package xiaoqiang.web.spring.gecco.demo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.geccocrawler.gecco.pipeline.Pipeline;
import com.geccocrawler.gecco.scheduler.SchedulerContext;
/**
 * 该类用于商品详情抓取后续处理
 * @author xiaoqiang
 *
 */
@Service("productDetailPipeline")
public class ProductDetailPipeline implements Pipeline<ProductDetail> {
	
	public static String dicName;

	public void process(ProductDetail detail) {
		StringBuilder builder = new StringBuilder();
		String title = detail.getTitle();
		builder.append("商品名称:" + title + "; \r\n");
		dicName = title;
//		System.out.println("商品名称:"+title);
		File file = new File("阿里巴巴商品图片" + File.separator + dicName);
		if(!file.exists()){
			file.mkdirs();
		}
		List<String> colors = detail.getColors();
//		System.out.println("商品颜色:"+colors);
		builder.append("商品颜色:" + colors.toString() + "; \r\n");
		
		List<String> sizes = detail.getSizes();
//		System.out.println("商品尺寸:"+sizes);
		builder.append("商品尺寸:" + sizes.toString() + ";\r\n");
		
		List<String> prices = detail.getPrices();
//		System.out.println("商品价格:"+prices);
		builder.append("商品价格:" + prices.toString() + "; \r\n");
		
		List<String> storeCount = detail.getStoreCount();
//		System.out.println("商品库存:"+storeCount);
		builder.append("商品库存:" + storeCount.toString() + "; '\r\n");
		
		List<String> mainParams = detail.getMainParams();
		Map<String,String> params = new HashMap<String,String>();
		for(int i=0;i<mainParams.size();i++){
			if(i<mainParams.size()/2){
				int key = 2*i;
				int value = 2*i+1;
				params.put(mainParams.get(key), mainParams.get(value));				
			}
		}
		builder.append(params.toString().replaceAll("=", " : "));
		
		File info = new File(file.getPath() + File.separator + "info.txt");
//		System.out.println("tet文件路径:" + info.getPath());
		PrintWriter writer = null;
		try {
			writer = new PrintWriter(new OutputStreamWriter(new FileOutputStream(info)),true);
			writer.write(builder.toString());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(writer != null){
				writer.close();
			}
		}
		
		List<String> mainImages = detail.getMainImages();
		int number = 1;
		for(String mainImage : mainImages){
			String[] mainImgArry = mainImage.split(",");
			for(int i=0;i<mainImgArry.length;i++){
				String[] urlArry = mainImgArry[i].split(":", 2);
				if(i==0){
					String preUrl = urlArry[1];
					preUrl = preUrl.substring(1, preUrl.length()-1);
					List<String> preUrlList = new ArrayList<String>();
					preUrlList.add(preUrl);
//					System.out.println("商品主图:"+preUrl);	
					File fileName = new File(file.getPath() + File.separator + "mainImage" + number + ".jpg");
					download(preUrl,fileName);
					number++;
				}else{
					String origUrl = urlArry[1];
					origUrl = origUrl.substring(1, origUrl.length()-2);
//					System.out.println("商品主图原图:"+origUrl);
					File fileName  = new File(file.getPath() + File.separator + "originalImage" + number + ".jpg");
//					System.out.println("===================");
//					System.out.println(file.getPath());
					download(origUrl,fileName);
					number++;
				}
			}
		}
		
//		System.out.println("商品主要参数:"+params);
//		System.out.println("商品详情:"+detail);
		String url = detail.getDataUrl();
		SchedulerContext.into(detail.getRequest().subRequest(url));
		
	}
	
	public static void download(String url, File file){
		InputStream input = null;
		FileOutputStream output = null;
		try {
				//构造URL
				URL imgURL = new URL(url);
				//打开链接
				URLConnection conn = imgURL.openConnection();
				input = conn.getInputStream();
				byte[] buff = new byte[10*1024];
				int len;
				output = new FileOutputStream(file);
				while((len=input.read(buff))!=-1){
					output.write(buff, 0, len);
				}
				output.flush();
//				System.out.println(file.length());
//				if(file.length()<50000 && file.getName().contains("商品详情图")){
//					file.delete();
//					System.out.println("删除成功!");
//				}
				
		} catch (MalformedURLException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(output != null){
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
	}

}
