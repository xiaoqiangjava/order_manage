package xiaoqiang.web.spring.gecco.demo;

import com.geccocrawler.gecco.annotation.GeccoClass;
import com.geccocrawler.gecco.downloader.AfterDownload;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.response.HttpResponse;
/**
 * 该类继承自AfterDownload接口,用于DetailImages页面下载之后的处理,该类将下载后的js变量转换成
 * Json字符串拱DetailImages渲染时使用
 * @author Administrator
 *
 */
@GeccoClass(DetailImages.class)
public class DetailImagesAfterDownload implements AfterDownload {

	public void process(HttpRequest request, HttpResponse response) {
		
		String content = response.getContent();
//		System.out.println("替换之前:"+content);
		content = content.substring(content.indexOf("={")+1);
//		System.out.println("替换之后"+content);
		response.setContent(content);
	}

}
