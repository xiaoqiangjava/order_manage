package xiaoqiang.web.spring.gecco.demo;

import java.io.File;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import com.geccocrawler.gecco.pipeline.Pipeline;

@Service("detailImagesPipeline")
public class DetailImagesPipeline implements Pipeline<DetailImages> {
	
	public void process(DetailImages detailImages) {
		String urlContent = detailImages.getUrlContent();
		String regex = "https:\"?(.*?)(\"|>|\\s+)";
		Pattern p = Pattern.compile(regex);
		Matcher m = p.matcher(urlContent);
		int i = 1;
		while(m.find()){
			String detailUrl = m.group();
//			System.out.println(detailUrl);
			detailUrl = detailUrl.substring(0, detailUrl.length()-1);
			if(detailUrl.endsWith(".jpg") || detailUrl.endsWith(".png")) {
				//文件名中不能有空格
				File dic = new File("阿里巴巴商品图片" + File.separator + ProductDetailPipeline.dicName);
				if(!dic.exists()){
					dic.mkdirs();
				}
				File file = new File(dic.getPath() + File.separator + "detailImage" + i + ".jpg");
//				System.out.println("文件夹路径:"+dic.getPath());
				i++;
				ProductDetailPipeline.download(detailUrl, file);
//				System.out.println("商品详情图:"+detailUrl);
			}
		}
//		System.out.println("urlContent="+urlContent);
		System.out.println("抓取完毕!");
	}

}
