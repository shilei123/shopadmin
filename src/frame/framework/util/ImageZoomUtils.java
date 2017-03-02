package framework.util;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.log4j.Logger;

import com.sun.image.codec.jpeg.ImageFormatException;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ImageZoomUtils {
	private final static Logger log = Logger.getLogger(ImageZoomUtils.class);
	private static final double WIDTH = 140; // 缩略图宽度
	private static final double HEIGHT = 120;// 缩略图高度
	
	public static void main(String[] args) throws Exception {
		zoom("C:\\aaa.jpg","C:\\bbb.jpg");
	}
	
	public static void zoom(File file, File fileto) throws IOException{
		BufferedImage sourceImage = ImageIO.read(file); 
        BufferedImage dstImage = null;   
        AffineTransform transform = AffineTransform.getScaleInstance(WIDTH/sourceImage.getWidth(), HEIGHT/sourceImage.getHeight());// 返回表示缩放变换的变换   
        AffineTransformOp op = new AffineTransformOp(transform, AffineTransformOp.TYPE_BILINEAR);   
        dstImage = op.filter(sourceImage, null);   
        try{
        	String formatName = file.getName().split("\\.")[1].toLowerCase();
        	if(formatName.equals("gif") || formatName.equals("png")){
        		ImageIO.write(dstImage, "png", fileto);
        	}else{
        		zoom2(file.getPath(), fileto.getPath());
        	}
        } catch (IOException e) {
           log.error("生成缩略图异常", e);
        }
        sourceImage = null;
        dstImage = null;
	}
	
	public static void zoom(String file, String fileto) throws IOException{
		zoom(new File(file), new File(fileto));
	}
	
	/*
	/**
	 * 在指定地址生成缩略图
	 * @param srcFileName
	 * @param tofilename
	 * @throws ImageFormatException
	 * @throws IOException
	 */
	public static void zoom2(String srcFileName, String tofilename) throws ImageFormatException, IOException {
		BufferedImage buffImg = ImageZoomUtils.zoom2(srcFileName);
		FileOutputStream fos = new FileOutputStream(tofilename);
        JPEGImageEncoder jpgEncoder = JPEGCodec.createJPEGEncoder(fos); //创建JPEG图像编码器，用于编码内存中的图像数据到JPEG数据输出流。    
        jpgEncoder.encode(buffImg); //编码BufferedImage对象到JPEG数据输出流。   
        fos.close();
	}
	
	/**
	 * 生成缩略图
	 * @param srcFileName
	 * @return
	 */
	public static BufferedImage zoom2(String srcFileName) {
		return zoom2(new ImageIcon(srcFileName));
	}
	
	/**
	 * 生成缩略图
	 * @param imgIcon
	 * @return
	 */
	public static BufferedImage zoom2(ImageIcon imgIcon) {
		BufferedImage buffImg = new BufferedImage((int)WIDTH, (int)HEIGHT, BufferedImage.TYPE_INT_RGB);// 构造一个预定义的图像类型的BufferedImage对象。
		Graphics2D g = buffImg.createGraphics();// 创建Graphics2D对象，用于在BufferedImage对象上绘图。

		g.setColor(Color.WHITE);// 设置图形上下文的当前颜色为白色。
		g.fillRect(0, 0, (int)WIDTH, (int)HEIGHT);// 用图形上下文的当前颜色填充指定的矩形区域。
		Image srcImage = imgIcon.getImage();
		g.drawImage(srcImage, 0, 0, (int)WIDTH, (int)HEIGHT, null);// 按照缩放的大小在BufferedImage对象上绘制原始图像。
		g.dispose();// 释放图形上下文使用的系统资源。
		srcImage.flush();// 刷新此 Image 对象正在使用的所有可重构的资源.
		return buffImg;
	}
}
