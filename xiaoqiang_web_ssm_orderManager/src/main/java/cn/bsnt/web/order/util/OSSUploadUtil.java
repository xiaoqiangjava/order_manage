package cn.bsnt.web.order.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.UUID;


import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectRequest;

/**
 * 该类是OSS上传文件的工具类,提供上传文件的方法
 * @author Administrator
 *
 */
public class OSSUploadUtil {
	public static OSSConfig ossConfig = null;
	
	
	/**
	 * 该方法用于上传文件到OSS(支持单文件上传)
	 * @param file 需要上传的文件
	 * @param fileType 上传文件的类型
	 * @return String 返回上传后图片的url
	 */
	public static String uploadFile2Oss(InputStream input,String fileType,String contentType){
		ossConfig =  ossConfig ==null?new OSSConfig():ossConfig;
		//上传之后文件的名称要唯一,否则会覆盖已有名称的文件
		String fileName = ossConfig.getPicLocation()+DateFormatUtil.dataFormat1(new Date())+"/"
							+UUID.randomUUID().toString().replaceAll("-", "") + "."+fileType;
		String picUrl = putObject(input,fileName,contentType);
		return picUrl;
	}
	
	public static String putObject(InputStream input, String fileName, String contentType) {
		ossConfig = ossConfig == null?new OSSConfig():ossConfig;
		
		OSSClient ossClient = null;
		String url = "";
		try {
			ossClient = new OSSClient(ossConfig.getEndPoint(),ossConfig.getAccessKeyId(),ossConfig.getAccessKeySecret());
			ObjectMetadata meta = new ObjectMetadata();//创建上传Object的metadate
			meta.setCacheControl("no-cache");//被下载时网页的缓存行为
			meta.setContentDisposition("inline");
			meta.setContentType(contentType);
			PutObjectRequest request = new PutObjectRequest(ossConfig.getBucketName(), fileName, input,meta);
			ossClient.putObject(request);
			//上传成功返回的文件路径
			fileName = fileName.replaceAll("/", "%2F");
			url = ossConfig.getEndPoint().replaceFirst("http://", "http://"+ossConfig.getBucketName()+".")+"/"+fileName;
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			ossClient.shutdown();
			try {
				input.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return url;
		
	}
	public static void main(String[] args) {
		
//		File file = new File("F:\\公司文件备份\\2017-03-22\\20170322\\31\\1.jpg");
//		System.out.println(file.getName());
//		String[] nameArry = file.getName().split("\\.");
//		//System.out.println(Arrays.toString(nameArry));
//		System.out.println(nameArry[nameArry.length-1]);
//		String picUrl = uploadFile2Oss(file,nameArry[nameArry.length-1]);
//		System.out.println(picUrl);
		
	}
}
