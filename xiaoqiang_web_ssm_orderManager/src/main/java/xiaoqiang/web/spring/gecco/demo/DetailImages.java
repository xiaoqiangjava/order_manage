package xiaoqiang.web.spring.gecco.demo;

import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.JSONPath;
import com.geccocrawler.gecco.spider.JsonBean;

/**
 * 该类用于抓取阿里巴巴网上详情图
 * @author xiaoqiang
 *
 */
@Gecco(matchUrl="https://img.alicdn.com/tfscom/{code}",pipelines="detailImagesPipeline")
public class DetailImages implements JsonBean {

	private static final long serialVersionUID = -4844999285804510348L;
	
	@JSONPath("$.content")
	private String urlContent;


	public String getUrlContent() {
		return urlContent;
	}

	public void setUrlContent(String urlContent) {
		this.urlContent = urlContent;
	}

	@Override
	public String toString() {
		return "DetailImages [urlContent=" + urlContent + "]";
	}
	
	
}
