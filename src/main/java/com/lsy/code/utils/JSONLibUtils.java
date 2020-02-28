package com.lsy.code.utils;

import java.util.List;
import java.util.Map;

import com.lsy.code.assist.CreateDataUtils;
import com.lsy.code.bean.Person;

import net.sf.json.JSON;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import net.sf.json.xml.XMLSerializer;

/**
 * 工具：JSONLIB
 */
public class JSONLibUtils {

	public static void main(String[] args) {
		List<Person> list = CreateDataUtils.createListPerson();
		Map<String, Object> map = CreateDataUtils.createMap();
		String xml = CreateDataUtils.createXML();
		
		//LIST convert to JSON
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray.toString());
		
		//OBJECT covert to JSON
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject.toString());
		
		//XML covert to JSON ：使用JSONLIB的XMLSerializer需添加依赖 xom
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		System.out.println(json.toString());
	}
	

}
