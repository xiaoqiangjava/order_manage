package cn.bsnt.web.order.controller;


import java.io.File;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.bsnt.web.order.util.FileZipUtil;
import cn.bsnt.web.order.util.JsonResult;
import xiaoqiang.web.spring.gecco.demo.InitGecco;

@Controller
@RequestMapping("gecco")
public class GeccoController extends AbstractController {

	@Resource
	private InitGecco initGecco;
	
	/**
	 * 通过给定的url抓取该网页上的商品信息
	 * @param url 详情页url
	 * @param session
	 * @param response
	 */
	@RequiresPermissions(value="productInfo:gecco")
	@RequestMapping("productDetail.bsnt")
	@ResponseBody
	public JsonResult geccoDettail(String url) {
		if(url == null || url.trim().isEmpty()){
			return new JsonResult(1, "抓取商品信息失败!商品详情页地址不能为空!");
		}
		initGecco.setUrl(url);
		boolean success = initGecco.init();
		if(success){
			try {
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
			return new JsonResult(0, "商品信息抓取成功!");			
		}
		return new JsonResult(1, "商品信息抓取失败!");
	}
	
	/**
	 * 下载服务器文件到本地
	 * @param session
	 * @param response
	 * @throws IOException 
	 */
	@RequiresPermissions(value="productInfo:download")
	@RequestMapping("download.bsnt")
	public ResponseEntity<byte[]> drawlerImgFromUrl(String title) throws IOException{
		String path = "阿里巴巴商品图片";
		System.out.println("下载时获取的阿里巴巴商品图片的服务器路径=" + path);
		File srcFilePath = new File(path);
		if(!srcFilePath.exists()){
			throw new RuntimeException("您要下载的文件目录不存在!");
		}
		String fileName = URLDecoder.decode(title, "UTF-8");
		System.out.println("title:"+title);
		File srcFile = new File(path + File.separator + title);
		System.out.println(srcFile.getPath());
		FileZipUtil.fileToZip(srcFile.getPath(), path, fileName);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentDispositionFormData("attachment", URLEncoder.encode(fileName,"UTF-8") + ".zip");
		headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
		File file = new File(path + "/" + fileName + ".zip");
		return new ResponseEntity<byte[]>(FileUtils.readFileToByteArray(file), headers, HttpStatus.CREATED);	
	}
	
}
