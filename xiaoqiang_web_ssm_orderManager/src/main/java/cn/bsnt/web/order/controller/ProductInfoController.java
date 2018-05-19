package cn.bsnt.web.order.controller;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import cn.bsnt.web.order.service.ProductInfoService;
import cn.bsnt.web.order.util.JsonResult;
import cn.bsnt.web.order.util.OSSUploadUtil;
/**
 * 产品操作的控制器,包括产品下架和新品上架;
 * 该类使用slf4j(Simple Logging Facade) 接口记录文件上传的时间;
 * @author xiaoqiang
 *
 */
@Controller
@RequestMapping("productInfo")
public class ProductInfoController extends AbstractController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductInfoController.class);
	@Resource
	private ProductInfoService productInfoService;
	
	/**
	 * 批量下架
	 * @param ids 要下架的商品id
	 * @return 下架的商品数目
	 */
	@RequiresPermissions(value="productInfo:delete")
	@RequestMapping("deleteProducts.bsnt")
	@ResponseBody
	public JsonResult deleteProducts(String[] ids){
		//System.out.println(ids);
		int n = productInfoService.deleteProduts(ids);
		return new JsonResult(0,n);
	}
	
	/**
	 * 新品上架--文件上传到oss并获取图片的url地址
	 * @param productName 商品名称
	 * @param brand 品牌
	 * @param artNumber 型号
	 * @param channelPrice 渠道价
	 * @param farePrice 运费
	 * @param price 单件售价
	 * @param oldPrice 单件原件
	 * @param size 规格尺寸
	 * @param color 颜色样式
	 * @param venderUrl 厂家地址
	 * @param taobaoUrl 淘宝地址
	 * @param kingUrl 代金券地址
	 * @param picture0 主图
	 * @param picture1 副图1
	 * @param picture2 副图2
	 * @param picture3 副图3
	 * @param picture4 副图4
	 * @param picture5 详情图
	 * @return
	 */
	@RequiresPermissions("productInfo:create")
	@RequestMapping("saveProductInfo.bsnt")
	@ResponseBody
	public JsonResult saveProductInfo(String productName,String lable,String productClass,String brand,String artNumber,String firstPrice,String price,
			String channelPrice,String farePrice, String oldPrice,String size,String color,String venderUrl,String taobaoUrl,String kingUrl,
						HttpServletRequest request){
		//利用servlet上下文初始化多部分处理器
		long start = System.currentTimeMillis();
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		if(multipartResolver.isMultipart(request)){
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest)request;
			//获取request中的所有文件名
			Map<String, List<MultipartFile>> map = multiRequest.getMultiFileMap();
//			System.out.println(map);
			if(map.get("picture0") == null){
				throw new RuntimeException("第一张主图不存在!");
			}
			if(map.get("picture1") == null){
				throw new RuntimeException("第二张主图不存在!");
			}
			if(map.get("picture2") == null){
				throw new RuntimeException("第三张主图不存在!");
			}
			if(map.get("picture3") == null){
				throw new RuntimeException("第四张主图不存在!");
			}
			if(map.get("picture4") == null){
				throw new RuntimeException("第五张主图不存在!");
			}
			if(map.get("picture5") == null){
				throw new RuntimeException("详情图不存在!");
			}
			long upStart = System.currentTimeMillis();
			int num = 1;
			int number = 1;
			String path = "upload/";
			File dest = new File(path);
			if(!dest.exists()){
				dest.mkdirs();
			}
			for(Entry<String, List<MultipartFile>> entrys : map.entrySet()){
				String key = entrys.getKey();
				String fileName = "";
				if("picture5".equals(key)){
					for(MultipartFile file : entrys.getValue()){
						fileName = "detailImage" + num + ".jpg";
						num ++;
						try {
							file.transferTo(new File(path + fileName));
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else{
					for(MultipartFile file : entrys.getValue()){
						fileName = "mainImage" + number + ".jpg";
						number ++;
						try {
							file.transferTo(new File(path + fileName));
						} catch (IllegalStateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}
			}
			long upEnd = System.currentTimeMillis();
//			System.out.println("多文件上传耗时:" + (upEnd - upStart) + "ms");
			LOG.debug("文件上传耗时:" + (upEnd-upStart) + "ms");
			
			File file0 = new File("upload/mainImage1.jpg");
			File file1 = new File("upload/mainImage2.jpg");
			File file2 = new File("upload/mainImage3.jpg");
			File file3 = new File("upload/mainImage4.jpg");
			File file4 = new File("upload/mainImage5.jpg");
			File[] file5 = new File[map.get("picture5").size()];
			for(int i=0;i<map.get("picture5").size();i++){
				file5[i] = new File("upload/detailImage" + (i+1) + ".jpg");
			}
			LOG.debug("详情图的个数:" + file5.length);
			String fileType0 = file0.getName().split("\\.")[file0.getName().split("\\.").length-1];
			String fileType1 = file1.getName().split("\\.")[file1.getName().split("\\.").length-1];
			String fileType2 = file2.getName().split("\\.")[file2.getName().split("\\.").length-1];
			String fileType3 = file3.getName().split("\\.")[file3.getName().split("\\.").length-1];
			String fileType4 = file4.getName().split("\\.")[file4.getName().split("\\.").length-1];
			String[] fileType5 = new String[file5.length];
			for(int i=0;i<file5.length;i++){
				fileType5[i] = file5[i].getName().split("\\.")[file5[i].getName().split("\\.").length-1];
			}
			//System.out.println(fileType0);
			InputStream input0 = null; InputStream input1 = null; InputStream input2 = null;
			InputStream input3 = null; InputStream input4 = null; 
			List<InputStream> input5 = new ArrayList<InputStream>();
			String contentType = "image/jpeg";
			try {
				input0 = new BufferedInputStream(new FileInputStream(file0),100*1024);
				input1 = new BufferedInputStream(new FileInputStream(file1),100*1024);
				input2 = new BufferedInputStream(new FileInputStream(file2),100*1024);
				input3 = new BufferedInputStream(new FileInputStream(file3),100*1024);
				input4 = new BufferedInputStream(new FileInputStream(file4),100*1024);
				for(int i=0;i<file5.length;i++){
					input5.add(new BufferedInputStream(new FileInputStream(file5[i]),100*1024));
				}
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			long file2oss = System.currentTimeMillis();
			String picture0 = OSSUploadUtil.uploadFile2Oss(input0, fileType0,contentType);
			String picture1 = OSSUploadUtil.uploadFile2Oss(input1, fileType1,contentType);
			String picture2 = OSSUploadUtil.uploadFile2Oss(input2, fileType2,contentType);
			String picture3 = OSSUploadUtil.uploadFile2Oss(input3, fileType3,contentType);
			String picture4 = OSSUploadUtil.uploadFile2Oss(input4, fileType4,contentType);
			long file2ossEnd = System.currentTimeMillis();
//			System.out.println("主图上传到OSS耗时:" + (file2ossEnd - file2oss) + "ms");
			LOG.debug("主图上传到OSS耗时:" + (file2ossEnd - file2oss) + "ms");
			String detailUrls = "";
			StringBuilder builder = new StringBuilder();
			
			long detail2oss = System.currentTimeMillis();
			
			for(int i=0;i<file5.length;i++){
				detailUrls = OSSUploadUtil.uploadFile2Oss(input5.get(i), fileType5[i], contentType);
				builder.append(detailUrls);
				builder.append(";");				
			}
			String picture5 = builder.toString();
			long detail2OssEnd = System.currentTimeMillis();
//			System.out.println("详情图上传到oss耗时:" + (detail2OssEnd-detail2oss) + "ms");
			LOG.debug("详情图上传到oss耗时:" + (detail2OssEnd - detail2oss) + "ms");
			long end = System.currentTimeMillis();
//			System.out.println("上传总耗时:" + (end-start) + "ms");
			LOG.debug("文件处理总耗时:" + (end - start) + "ms");
			boolean success = productInfoService.saveProductInfo(productName,lable, productClass, brand, artNumber, firstPrice, price, channelPrice, farePrice,
					oldPrice, size, color, venderUrl, taobaoUrl, kingUrl, picture0, picture1, picture2, picture3, picture4, picture5);
			if(success){
				return new JsonResult(0,success);
			}else{
				return new JsonResult(1,success);
			}
			
		}else{
			return new JsonResult(2,new Exception());
		}
	}
}