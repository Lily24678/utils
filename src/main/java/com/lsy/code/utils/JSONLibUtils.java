package com.lsy.code.utils;

import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.PropertyUtils;

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

	public static void main(String[] args) throws Exception {
		List<Person> list = CreateDataUtils.createListPerson();
		Map<String, Object> map = CreateDataUtils.createMap();
		String xml = CreateDataUtils.createXML();
		String jsonstr = "{name=\"json\",bool:true,int:1,double:2.2,func:function(a){ return a; },array:[1,2]}";
		
		//LIST convert to JSON
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(">>>\t"+jsonArray.toString());
		jsonArray = JSONArray.fromObject( "['json','is','easy']" );  
		System.out.println(">>>\t"+jsonArray.toString());
		
		//OBJECT covert to JSON
		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(">>>\t"+jsonObject.toString());
		
		//XML covert to JSON ：使用JSONLIB的XMLSerializer需添加依赖 xom
		XMLSerializer xmlSerializer = new XMLSerializer();
		JSON json = xmlSerializer.read(xml);
		System.out.println(">>>\t"+json.toString());		
		
		//JSON convert to OBJECT
		JSONObject jsonObject2 = JSONObject.fromObject(jsonstr);
		Object bean = JSONObject.toBean(jsonObject2);
		System.out.println(">>>\t"+PropertyUtils.getProperty(bean, "name"));
		List<?> list2 = (List<?>) JSONArray.toCollection(jsonObject2.getJSONArray("array"));
		System.out.println(">>>\t"+list2);
		
		//JSON convert to XML
//		JSONObject json1 = JSONObject.fromObject("{\"name\":\"json\",\"bool\":true,\"int\":1}");  
		JSONArray json1 = JSONArray.fromObject("[1,2,3]");  
		String xml2 = xmlSerializer.write(json1);
		System.out.println(xml2);
	}

}
