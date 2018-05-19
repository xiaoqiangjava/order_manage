package xiaoqiang.web.spring.gecco.demo;

import java.util.List;

import com.geccocrawler.gecco.annotation.Attr;
import com.geccocrawler.gecco.annotation.Gecco;
import com.geccocrawler.gecco.annotation.HtmlField;
import com.geccocrawler.gecco.annotation.Image;
import com.geccocrawler.gecco.annotation.Request;
import com.geccocrawler.gecco.annotation.RequestParameter;
import com.geccocrawler.gecco.annotation.Text;
import com.geccocrawler.gecco.request.HttpRequest;
import com.geccocrawler.gecco.spider.HtmlBean;

/**
 * 该类用于封装抓取1688网上商品的详细信息
 * @author xiaoqiang
 *
 */
@Gecco(matchUrl="https://detail.1688.com/offer/{code}.html",pipelines="productDetailPipeline")
public class ProductDetail implements HtmlBean {

	private static final long serialVersionUID = 2454868814085254504L;

	@Request
	private HttpRequest request;
	
	@RequestParameter
	private String code;
	
	@Text
	@HtmlField(cssPath="#mod-detail-title > h1")
	private String title;
	
	@Text
	@HtmlField(cssPath="td.price em.value")
	private List<String> prices;
	
	@Attr("title")
	@HtmlField(cssPath="#mod-detail-bd .region-vertical .widget-custom .obj-leading > div.obj-content > ul > li > div > a")
	private List<String> colors;
	
	@HtmlField(cssPath="#mod-detail-bd .region-vertical .offerdetail_ditto_purchasing div.obj-sku .name > span")
	private List<String> sizes;

	@Image
	@HtmlField(cssPath="#mod-detail-bd .region-vertical .tab-pane a > img")
	private String mainImg;
	
	@Text
	@HtmlField(cssPath="#mod-detail-attributes tr td")
	private List<String> mainParams;
	
	@Text
	@HtmlField(cssPath="#mod-detail-bd .region-vertical .widget-custom div.obj-sku tr .count > span > em.value")
	private List<String> storeCount;
	
	@Attr("data-imgs")
	@HtmlField(cssPath="#dt-tab > div > ul > li.tab-trigger")
	private List<String> mainImages;
	
	@Attr("data-tfs-url")
	@HtmlField(cssPath="#desc-lazyload-container")
	private String dataUrl;

	public String getDataUrl() {
		return dataUrl;
	}

	public void setDataUrl(String dataUrl) {
		this.dataUrl = dataUrl;
	}

	@Override
	public String toString() {
		return "ProductDetail [request=" + request + ", code=" + code + ", title=" + title + ", prices=" + prices
				+ ", colors=" + colors + ", sizes=" + sizes + ", mainImg=" + mainImg + ", mainParams=" + mainParams
				+ ", storeCount=" + storeCount + ", mainImages=" + mainImages
				+ ", dataUrl=" + dataUrl + "]";
	}




	public List<String> getMainImages() {
		return mainImages;
	}


	public void setMainImages(List<String> mainImages) {
		this.mainImages = mainImages;
	}

	public List<String> getStoreCount() {
		return storeCount;
	}

	public void setStoreCount(List<String> storeCount) {
		this.storeCount = storeCount;
	}
	
	public HttpRequest getRequest() {
		return request;
	}

	public void setRequest(HttpRequest request) {
		this.request = request;
	}



	public String getCode() {
		return code;
	}



	public void setCode(String code) {
		this.code = code;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}



	public List<String> getPrices() {
		return prices;
	}



	public void setPrices(List<String> prices) {
		this.prices = prices;
	}



	public List<String> getColors() {
		return colors;
	}



	public void setColors(List<String> colors) {
		this.colors = colors;
	}



	public List<String> getSizes() {
		return sizes;
	}



	public void setSizes(List<String> sizes) {
		this.sizes = sizes;
	}



	public String getMainImg() {
		return mainImg;
	}



	public void setMainImg(String mainImg) {
		this.mainImg = mainImg;
	}



	public List<String> getMainParams() {
		return mainParams;
	}



	public void setMainParams(List<String> mainParams) {
		this.mainParams = mainParams;
	}

//	详图地址:https://img.alicdn.com/tfscom/TB1bduIPpXXXXbBapXXXXXXXXXX
//
//	public static void main(String[] args) {
////		HtmlUnitDownloder downloader = new HtmlUnitDownloder();
//		HttpRequest start = new HttpGetRequest("https://detail.1688.com/offer/44242860983.html?spm=a262r.8314602.izwhfl6u.56.t2uRlN");
//		GeccoEngine.create().classpath("xiaoqiang.web.spring.gecco.demo")
//		.start(start).thread(1).interval(3000).start();
//	}
}
