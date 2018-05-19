package xiaoqiang.web.spring.gecco.demo;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.geccocrawler.gecco.GeccoEngine;
import com.geccocrawler.gecco.pipeline.PipelineFactory;
import com.geccocrawler.gecco.request.HttpGetRequest;
import com.geccocrawler.gecco.request.HttpRequest;

@Service("initGecco")
public class InitGecco {
	
	@Resource(name="springPipelineFactory")
	private PipelineFactory springPipelineFactory;
	
	private String url;
	
	/**
	 * 设置访问的url地址
	 * @param url
	 * @return 
	 */
	public void setUrl(String url){
		if(url!=null && !url.trim().isEmpty()){
			this.url = url;			
		}else{
			throw new RuntimeException("抓取页面的地址不能为空!");
		}
	}
	
	/**
	 * 启动爬虫引擎
	 * @return
	 */
	public boolean init() {
		HttpRequest start = new HttpGetRequest(url);
		if(url!=null){
			System.out.println("url:" + url);
			GeccoEngine.create().pipelineFactory(springPipelineFactory).classpath("xiaoqiang.web.spring.gecco.demo")
			.start(start).interval(2000).start();
//			System.out.println("循环!");
			return true;			
		}else{
			return false;			
		}
	}

}
