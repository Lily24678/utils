package com.lsy.code.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class FileUtil {

	/**
	 * 压缩多个文件到压缩包
	 * @param pathList 被压缩文件的文件全路径名
	 * @param destpath 压缩包名的全路径
	 */
	public static void downFilesToZip(List<String> pathList,String destpath){
		OutputStream out=null;//输出流
		ZipOutputStream zos=null;
		try {
			out = new FileOutputStream(destpath);
			zos=new ZipOutputStream(out);
			for (String path : pathList) {
				File file = new File(path);
				String name = file.getName();
				if (file.exists()&&file.isFile()) {
					InputStream input = new FileInputStream(file);//获取文件流
					zos.putNextEntry(new ZipEntry(name));// 压缩文件名称 设置ZipEntry对象
					zos.setComment("压缩包");//设置注释
					int temp = 0;
					while ((temp = input.read()) != -1) { // 读取内容
						zos.write(temp); // 压缩输出
					}
					input.close(); // 关闭输入流					
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if (null!=zos) {
					zos.flush();
					zos.close();
				}
				if(null!=out)out.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		//压缩多个文件到压缩包
		List<String> pathList = new ArrayList<String>() ;
		pathList.add("F:/莎啦啦.mp3");
		pathList.add("F:/云烟成雨.mp3");
		FileUtil.downFilesToZip(pathList, "F:/test.zip");		
	}
}
