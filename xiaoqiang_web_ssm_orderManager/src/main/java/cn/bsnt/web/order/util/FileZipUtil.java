package cn.bsnt.web.order.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;


/**
 * 该类用于将文件夹压缩成.zip文件
 * @author xiaoqiang
 *
 */
public class FileZipUtil {
	
	/**
	 * 该方法将放在srcFilePath目录下的源文件,打包成fileName名字的.zip文件,并放到ZipFilePath下面
	 * @param srcFilePath
	 * @param zipFilePath
	 * @param fileName
	 * @return
	 */
	public static boolean fileToZip(String srcFilePath, String zipFilePath, String fileName){
		boolean flag = false;
		File srcFile = new File(srcFilePath);
		
		if(!srcFile.exists()){
			throw new RuntimeException("待压缩的文件目录:"+srcFilePath+"不存在!");
		}
		File zipFile = new File(zipFilePath+"/" + fileName + ".zip");
//		if(zipFile.exists()){
//			throw new RuntimeException(zipFilePath + "目录下存在名为:" + fileName + "的压缩文件!");
//		}
		File[] srcFiles = srcFile.listFiles();
		if(srcFiles == null || srcFile.length() < 1){
			throw new RuntimeException(srcFilePath + "目录下没有文件,不需要压缩!");
		}
		FileOutputStream fos = null;
		ZipOutputStream zos = null;
		BufferedInputStream bis = null;
		try {
			fos = new FileOutputStream(zipFile);
			zos = new ZipOutputStream(fos);
			//缓冲数组
			byte[] buf = new byte[10*1024];
//			System.out.println(srcFiles.length);
			for(int i=0;i<srcFiles.length;i++){
				//创建Zip实体(新的进入点),并添加进压缩包
				ZipEntry zipEntry = new ZipEntry(srcFiles[i].getName());
				zos.putNextEntry(zipEntry);
				//读取待压缩的文件并写进压缩包里对应的Zip实体中
				bis = new BufferedInputStream(new FileInputStream(srcFiles[i]));
				int read = -1;
				while((read = bis.read(buf))!=-1){
					zos.write(buf, 0, read);
				}
				zos.flush();
				zos.closeEntry();
			}
			flag = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if(zos != null){
				try {
					zos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
			if(bis != null){
				try {
					bis.close();
				} catch (IOException e) {
					e.printStackTrace();
					throw new RuntimeException(e);
				}
			}
		}
		return flag;
	}
	
	public static void main(String[] args) {
//		String srcFilePath = "F:\\公司文件备份\\2017-04-11";
//		String zipFilePath = "F:\\";
//		String fileName = "测试压缩文件";
//		fileToZip(srcFilePath, zipFilePath, fileName);
	}
}
