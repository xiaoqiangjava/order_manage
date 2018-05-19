package cn.bsnt.web.order.util;
/**
 * 该类是OSS参数配置类,方便获取OSS参数
 * @author Administrator
 *
 */
public class OSSConfig {
	//连接区域地址
	private String endPoint;
	//需要存储的bucketName
	private String bucketName;
	//图片保存路径
	private String picLocation;
	//连接OSS客户端的 keyId
	private String accessKeyId;
	//连接OSS客户端密钥
	private String accessKeySecret;
	public OSSConfig(){
		this.endPoint = GetOssConfig.getConfigValue("oss.endPoint");
		this.bucketName = GetOssConfig.getConfigValue("oss.bucketName");
		this.picLocation = GetOssConfig.getConfigValue("oss.picLocation");
		this.accessKeyId = GetOssConfig.getConfigValue("oss.accessKeyId");
		this.accessKeySecret = GetOssConfig.getConfigValue("oss.accessKeySecret");
	}
	public OSSConfig(String endPoint, String bucketName, String picLocation, String accessKeyId,
			String accessKeySecret) {
		super();
		this.endPoint = endPoint;
		this.bucketName = bucketName;
		this.picLocation = picLocation;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
	}
	public String getEndPoint() {
		return endPoint;
	}
	public void setEndPoint(String endPoint) {
		this.endPoint = endPoint;
	}
	public String getBucketName() {
		return bucketName;
	}
	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}
	public String getPicLocation() {
		return picLocation;
	}
	public void setPicLocation(String picLocation) {
		this.picLocation = picLocation;
	}
	public String getAccessKeyId() {
		return accessKeyId;
	}
	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}
	public String getAccessKeySecret() {
		return accessKeySecret;
	}
	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}
	
	
}
