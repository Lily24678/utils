package com.lsy.code;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.lsy.code.utils.FileUtils;

public class TestDemo {

	public static void main(String[] args) throws IOException {
		File file = new File("F:/4s.mp3");
		System.out.println(file.getAbsolutePath());
		System.out.println(file.getCanonicalPath());
		System.out.println(file.getName());
		System.out.println(file.getParent());
		System.out.println(file.getPath());
		
		List<String> pathList = new ArrayList<String>() ;
		pathList.add("F:/莎啦啦.mp3");
		pathList.add("F:/云烟成雨.mp3");
		FileUtils.downFilesToZip(pathList, "F:/test.zip");
	}
}
