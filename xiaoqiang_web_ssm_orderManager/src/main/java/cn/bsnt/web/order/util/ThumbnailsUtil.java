package cn.bsnt.web.order.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;


/**
 * 封装了java处理图片的API,包括图片压缩和图片拼接(横向拼接和纵向拼接)
 * @author xiaoqiang
 *
 */
public class ThumbnailsUtil {
	/**
	 * 拼接图片
	 * @param files
	 * @param type
	 * @param targetFile
	 */
	 public static synchronized void mergeImage(String[] files, int type, String targetFile) {  
	        int len = files.length;  
	        if (len < 1) {  
	            throw new RuntimeException("图片数量小于1");
	        }
	        File[] src = new File[len];  
	        BufferedImage[] images = new BufferedImage[len];  
	        int[][] ImageArrays = new int[len][];  
	        for (int i = 0; i < len; i++) {  
	            try {  
	                src[i] = new File(files[i]);
	                //使用Toolkit.getDefaultToolkit().getImage(srcPath)读取图片时有缓存,需要flush;
	                images[i] = toBufferedImage(Toolkit.getDefaultToolkit().getImage(src[i].getPath()));
//	                images[i] = ImageIO.read(src[i]);
	            } catch (Exception e) {  
	                throw new RuntimeException(e);  
	            }  
	            int width = images[i].getWidth();  
	            int height = images[i].getHeight();  
	            ImageArrays[i] = new int[width * height];  
	            ImageArrays[i] = images[i].getRGB(0, 0, width, height, ImageArrays[i], 0, width);  
	        }  
	        int newHeight = 0;  
	        int newWidth = 0;  
	        for (int i = 0; i < images.length; i++) {  
	            // 横向  
	            if (type == 1) {  
	                newHeight = newHeight > images[i].getHeight() ? newHeight : images[i].getHeight();  
	                newWidth += images[i].getWidth();  
	            } else if (type == 2) {// 纵向  
	                newWidth = newWidth > images[i].getWidth() ? newWidth : images[i].getWidth();  
	                newHeight += images[i].getHeight();  
	            }  
	        }  
	        if (type == 1 && newWidth < 1) {  
	            return;  
	        }  
	        if (type == 2 && newHeight < 1) {  
	            return;  
	        }  
	  
	        // 生成拼接后的新图片 
	        try {  
	            BufferedImage ImageNew = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);  
	            int height_i = 0;  
	            int width_i = 0;  
	            for (int i = 0; i < images.length; i++) {
	                if (type == 1) {  
	                    ImageNew.setRGB(width_i, 0, images[i].getWidth(), newHeight, ImageArrays[i], 0,  
	                            images[i].getWidth());  
	                    width_i += images[i].getWidth();  
	                } else if (type == 2) {  
	                    ImageNew.setRGB(0, height_i, newWidth, images[i].getHeight(), ImageArrays[i], 0, newWidth);  
	                    height_i += images[i].getHeight();  
	                }  
	            }  
	            //输出想要的图片  
	            ImageIO.write(ImageNew, targetFile.split("\\.")[1], new File(targetFile));  
	  
	        } catch (Exception e) {  
	        	e.printStackTrace();
	            throw new RuntimeException(e);  
	        }  
	    }  
	 /**
	  * 将Image转换成BufferedImage
	  * @param image
	  * @return
	  */
	 public static BufferedImage toBufferedImage(Image image) {  
	        if (image instanceof BufferedImage) {  
	            return (BufferedImage) image;  
	        }  
	        // This code ensures that all the pixels in the image are loaded  
	        image = new ImageIcon(image).getImage();  
	        BufferedImage bimage = null;  
	        GraphicsEnvironment ge = GraphicsEnvironment  
	                .getLocalGraphicsEnvironment();  
	        try {  
	            int transparency = Transparency.OPAQUE;  
	            GraphicsDevice gs = ge.getDefaultScreenDevice();  
	            GraphicsConfiguration gc = gs.getDefaultConfiguration();  
	            bimage = gc.createCompatibleImage(image.getWidth(null),  
	                    image.getHeight(null), transparency);  
	        } catch (HeadlessException e) {  
	        	e.printStackTrace();
	        }  
	        if (bimage == null) {  
	            int type = BufferedImage.TYPE_INT_RGB;  
	            bimage = new BufferedImage(image.getWidth(null),  
	                    image.getHeight(null), type);  
	        }   
	        Graphics g = bimage.createGraphics();   
	        g.drawImage(image, 0, 0, null);  
	        g.dispose(); 
	        image.flush();
	        return bimage;  
	    }
	 /**
	  * 主图留白
	 * @throws IOException 
	  */
	 public static void mergeMain(String srcPath, String destPath) throws IOException{
		 Image image = Toolkit.getDefaultToolkit().getImage(srcPath);
		 Toolkit.getDefaultToolkit().beep();
			image = new ImageIcon(image).getImage();  
	        BufferedImage bimage = null;  
	        GraphicsEnvironment ge = GraphicsEnvironment  
	                .getLocalGraphicsEnvironment();  
	        try {  
	            int transparency = Transparency.OPAQUE;
	            GraphicsDevice gs = ge.getDefaultScreenDevice();  
	            GraphicsConfiguration gc = gs.getDefaultConfiguration();  
	            bimage = gc.createCompatibleImage((int)(1.43*image.getWidth(null)), image.getHeight(null), transparency);  
	        } catch (HeadlessException e) {  
	        	e.printStackTrace();
	        }  
	        if (bimage == null) {  
	            int type = BufferedImage.TYPE_INT_RGB;
	            bimage = new BufferedImage((int)(1.43*image.getWidth(null)), image.getHeight(null), type);  
	        }
	        Graphics g = bimage.createGraphics();
	        g.fillRect(0, 0, (int)(1.43*image.getWidth(null)), image.getHeight(null));
	        g.drawImage(image, (int)(0.2*image.getWidth(null)), 0, image.getWidth(null), image.getHeight(null), Color.WHITE, null);
	        ImageIO.write(bimage, "jpg", new File(destPath));
	        g.dispose();
	        image.flush();
	 }
}
