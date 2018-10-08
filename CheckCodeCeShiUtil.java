package com.gxzt.platform.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.awt.image.BufferedImageOp;
import java.awt.image.ColorModel;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageInputStream;
import javax.swing.JButton;

//import Decoder.BASE64Decoder;
//import Decoder.BASE64Encoder;

import Decoder.BASE64Decoder;
import Decoder.BASE64Encoder;

import com.gxzt.base.core.base.SEContextInit;
import com.gxzt.base.core.exception.ServiceException;
import com.gxzt.base.core.util.PropUtil;
import com.gxzt.base.core.util.StringUtil;
import com.gxzt.vo.system.ExcelConfigVO;

public class CheckCodeCeShiUtil {
/**
 * 全部方法集合成工具类
 * 
 * 这里是测试方法，根据自己的需求调整。
 * */

	public static void main(String args[]){

			CheckCodeCeShiUtiltest= new CheckCodeCeShiUtil();
			Random rand = new Random(); //定义随机数 
		    int min = 90;
		    int max = 230;
		    int min2 = 10;
		    int max2 = 100;
		    Integer rx = rand.nextInt(max-min)+min;//130~210的x轴   
		    Integer ry = rand.nextInt(max2-min2)+min2;//10 ~ 100的y轴 
			try {
				BufferedImage image = test.Images();//调用链接取本地图片的方法
				BufferedImage checkImgMin = test.getMarkImage(image , rx ,ry , 55, 55);//这张原图的长宽+抠图区的位置+抠出来的图长宽  这里是 抠出来的小图片
			    BufferedImage checkImg = test.cutByTemplate(image ,test.getCutAreaData(310,172, rx ,ry, 55, 55));
			    
				BufferedImage frameMin = test.imagesFrameMin(checkImgMin);//为小图片添加红色的小边框
				BufferedImage frame = test.imagesFrame(checkImg, rx, ry, 55, 55);
				
				BufferedImage dest = simpleBlur(frame, null);//图片经过高斯模糊
				BufferedImage destMin = simpleBlur(frameMin, null);//图片经过高斯模糊
				
				byte[] ImageWriter = test.compressPictures(dest);//压缩图片转jpg
				byte[] ImageWriterMin = test.compressPictures(destMin);//压缩图片转jpg
				
				ByteArrayInputStream in = new ByteArrayInputStream(ImageWriter);    //将b作为输入流；
				ByteArrayInputStream InMin = new ByteArrayInputStream(ImageWriterMin);    //将b作为输入流；
				
				BufferedImage dd = ImageIO.read(in);   //转换类型
				BufferedImage ddMin = ImageIO.read(InMin);   //转换类型
				
				//String comp = test.imageToBase64(dd,"jpg");//调用 图片转64位字符串的方法			返回给前端用
				//String compMin = test.imageToBase64(ddMin,"png");//调用 图片转64位字符串的方法		返回给前端用
				
				//输出图片到本地D盘下
				ImageIO.write(dd, "jpg", new File("d:\\test.png"));
				ImageIO.write(ddMin, "jpg", new File("d:\\test1.png"));
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
/**
 * 第一步，随机读写一张用于做验证码的图片
 * 原图片一定是png格式，后经转站可转为jpg
 * */

private BufferedImage Images()throws Exception {
	
		String path = "d:/check";// 图片路径
		Random rand = new Random(); //定义随机数
		Integer r= rand.nextInt(Integer.valueOf("6")); //获得随机下标,如果改位置的图片总数为6，为了方便随机取图片，图片为名字1~6.png
		path=path+"/"+r.toString()+".png"; //拼接路径
		BufferedImage  image = ImageIO.read(new FileInputStream(path));
		return image;

	}

/**
 * 第二步
 * 抠图方块裁剪，得到一个用来滑动的小方块
 * 对图片裁剪，并把裁剪后的图片返回 。
 */
private BufferedImage getMarkImage(BufferedImage image,int x,int y,int length,int width)throws IOException {

			InputStream is =  null ;
			ImageInputStream iis = null ;
			
			try {
			
			    ByteArrayOutputStream os = new ByteArrayOutputStream();
			    ImageIO.write(image, "jpg", os);
			    is = new ByteArrayInputStream(os.toByteArray());
			
			/*
			* 返回包含所有当前已注册 ImageReader 的 Iterator，这些 ImageReader
			* 声称能够解码指定格式。 参数：formatName - 包含非正式格式名称 .
			*（例如 "jpeg" 或 "tiff"）等 。
			*/
			    Iterator<ImageReader> it= ImageIO.getImageReadersByFormatName("jpg");
			    ImageReader reader = it.next();
			    // 获取图片流
			    iis = ImageIO.createImageInputStream(is);
			
			/*
			* <p>iis:读取源.true:只向前搜索 </p>.将它标记为 ‘只向前搜索'。
			* 此设置意味着包含在输入源中的图像将只按顺序读取，可能允许 reader
			* 避免缓存包含与以前已经读取的图像关联的数据的那些输入部分。
			*/
			    reader.setInput(iis, true ) ;
			
			/*
			* <p>描述如何对流进行解码的类<p>.用于指定如何在输入时从 Java Image I/O
			* 框架的上下文中的流转换一幅图像或一组图像。用于特定图像格式的插件
			* 将从其 ImageReader 实现的 getDefaultReadParam 方法中返回
			* ImageReadParam 的实例。
			*/
			    ImageReadParam param = reader.getDefaultReadParam();
			
			/*
			* 图片裁剪区域。Rectangle 指定了坐标空间中的一个区域，通过 Rectangle 对象
			* 的左上顶点的坐标（x，y）、宽度和高度可以定义这个区域。
			*/
			    Rectangle rect =  new Rectangle(x, y, length, width);
			
			
			    // 提供一个 BufferedImage，将其用作解码像素数据的目标。
			    param.setSourceRegion(rect);
			
			/*
			* 使用所提供的 ImageReadParam 读取通过索引 imageIndex 指定的对象，并将
			* 它作为一个完整的 BufferedImage 返回。
			 */
			    BufferedImage bi=reader.read(0,param);
			    return bi;
			
			} finally {
			    if (is != null )
			        is.close() ;
			    if (iis != null )
			        iis.close();
			}
			
			
			
			}

/**
 * 第三步
 * @param targetLength 原图的长度
 * @param targetWidth  原图的宽度
 * @param x            裁剪区域的x坐标
 * @param y            裁剪区域的y坐标
 * @param length        抠图的长度
 * @param width        抠图的宽度
 * @return
 * 获取扣图区域坐标，原理是用二维数组表示原图的所有像素坐标，通过抠图坐标和长宽确定抠图区域坐标，并将抠图区域坐标的值赋值为1，其他区域0
 */
			private int [][] getCutAreaData(int targetLength,int targetWidth,int x,int y ,int length,int width){
				
			    int[][] data = new int[targetLength][targetWidth];
			    
			    for (int i=0;i<targetLength;i++){
			    	
			        for(int j=0;j<targetWidth;j++){
			        	
			            if(i<x+length&&i>=x&&j<y+width&&j>y){
			            	
			                data[i][j]=1;
			            }else {
			                data[i][j]=0;
			            }
			        }
			    }
			    return data;
			}

/**
 * 第四步
* 经过以上两个步骤，我们已经获取到原图和扣下来的方块图了，下面通过二维数组坐标给原图的抠图区域做色彩处理，就可以得到带有阴影的原图。
* **/
			public BufferedImage cutByTemplate(BufferedImage oriImage,int[][] templateImage) throws  Exception{
				 
		        for (int i = 0; i < oriImage.getWidth(); i++) {
		            for (int j = 0; j < oriImage.getHeight(); j++) {
		                int rgb = templateImage[i][j];
		                // 原图中对应位置变色处理
		                int rgb_ori = oriImage.getRGB(i,  j);
		                if (rgb == 1) {
		                    int r = (0xff & rgb_ori);
		                    int g = (0xff & (rgb_ori >> 8));
		                    int b = (0xff & (rgb_ori >> 16));
		                    int Gray = (r*2 + g*5 + b*1) >> 3;
		                    oriImage.setRGB( i, j, Gray);
		                }
		            }
		        }
				return oriImage;
		    }

			
			/**
			 * 第五步，高斯模糊图片
			 * **/
					public static ConvolveOp getGaussianBlurFilter(int radius,
					        boolean horizontal) {
					    if (radius < 1) {
					        throw new IllegalArgumentException("Radius must be >= 1");
					    }
					    
					    int size = radius * 2 + 1;
					    float[] data = new float[size];
					    
					    float sigma = radius / 3.0f;
					    float twoSigmaSquare = 2.0f * sigma * sigma;
					    float sigmaRoot = (float) Math.sqrt(twoSigmaSquare * Math.PI);
					    float total = 0.0f;
					    
					    for (int i = -radius; i <= radius; i++) {
					        float distance = i * i;
					        int index = i + radius;
					        data[index] = (float) Math.exp(-distance / twoSigmaSquare) / sigmaRoot;
					        total += data[index];
					    }
					    
					    for (int i = 0; i < data.length; i++) {
					        data[i] /= total;
					    }        
					    
					    Kernel kernel = null;
					    if (horizontal) {
					        kernel = new Kernel(size, 1, data);
					    } else {
					        kernel = new Kernel(1, size, data);
					    }
					    return new ConvolveOp(kernel, ConvolveOp.EDGE_NO_OP, null);
					}
					
					public static BufferedImage simpleBlur(BufferedImage src,BufferedImage dest) {
					        BufferedImageOp op = getGaussianBlurFilter(2,false);
					       return op.filter(src, dest);
					}


			
			
//为图片添加边框  第六步
		    public BufferedImage imagesFrame(BufferedImage image,int x,int y,int width,int height)throws Exception {
		    	
		        Graphics g = image.getGraphics();
		        g.setColor(Color.RED);//画笔颜色
		        g.drawRect(x, y, width, height);//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
		        
				return image;
		    }
		    public BufferedImage imagesFrameMin(BufferedImage image)throws Exception {
		    	
		    	int height = 54;
		        int width = 54;
		        Graphics g = image.getGraphics();
		        Color c1 = new Color(173,216,230);
		        g.setColor(c1);//画笔颜色
		        g.drawRect(0, 0, width, height);//矩形框(原点x坐标，原点y坐标，矩形的长，矩形的宽)
				return image;
		    }
		    
		    
		    /**
		     * 第七步
		     * 图片压缩处理
		     * @param bufferedImage   图片对象
		     * @param imagType  图片类型
		     * @return byte[]
		     * @throws IOException
		     */
		    public static byte[] compressPictures(BufferedImage bufferedImage) throws IOException {
		        //bos.reset();
		        // 得到指定Format图片的writer
		        Iterator<ImageWriter> iter = ImageIO .getImageWritersByFormatName("jpg");// 得到迭代器  
		        ImageWriter writer = (ImageWriter) iter.next();    
		        // 获取指定writer的输出参数设置(ImageWriteParam )
		        
		        ImageWriteParam imageWriteParam = writer.getDefaultWriteParam();
		        imageWriteParam.setCompressionMode(ImageWriteParam.MODE_EXPLICIT); // 设置可否压缩
		        imageWriteParam.setCompressionQuality(1f); // 设置压缩质量参数    
		        imageWriteParam.setProgressiveMode(ImageWriteParam.MODE_DISABLED);  
		        ColorModel colorModel = ColorModel.getRGBdefault();
		        
		        // 指定压缩时使用的色彩模式
		        imageWriteParam.setDestinationType(new javax.imageio.ImageTypeSpecifier(colorModel,colorModel.createCompatibleSampleModel(16, 16))); 
		        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(); // 取得内存输出流  
		        
		        writer.setOutput(ImageIO.createImageOutputStream(byteArrayOutputStream));
		        IIOImage iIamge = new IIOImage(bufferedImage, null, null);
		        writer.write(null, iIamge, imageWriteParam);
		        
		        return  byteArrayOutputStream.toByteArray();
		    }      
		   
		    
	/**
	 * 
	 * 第八步
	 * base64图片转字符串
	 * **/	
	private String imageToBase64(BufferedImage image) throws  Exception{
		
		        byte[] imagedata = null;
		        ByteArrayOutputStream bao=new ByteArrayOutputStream();
		        ImageIO.write(image,"jpg",bao);//更换图片格式，png、jpg
		        imagedata=bao.toByteArray();
		        BASE64Encoder encoder = new BASE64Encoder();
		        
		        String BASE64IMAGE=encoder.encodeBuffer(imagedata).trim();
		        
		        BASE64IMAGE = BASE64IMAGE.replaceAll("\n", "").replaceAll("\r", "");//删除 \r\n
		        
		        return BASE64IMAGE;
		    }
	/**
	 * 
	 * base64字符串转图片
	 * **/
		    private BufferedImage base64StringToImage(String base64String) {
		        try {
		        	BASE64Decoder decoder=new BASE64Decoder();
		        	
		            byte[] bytes1 = decoder.decodeBuffer(base64String);
		            
		            ByteArrayInputStream bais = new ByteArrayInputStream(bytes1);
		            
		            return ImageIO.read(bais);
		        } catch (IOException e) {
		            e.printStackTrace();
		        }
		        return null;
		    }

}

