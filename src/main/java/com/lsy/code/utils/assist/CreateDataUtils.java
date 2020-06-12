package com.lsy.code.utils.assist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.lsy.code.utils.bean.Person;
import com.lsy.code.utils.bean.PhoneNumber;
import com.thoughtworks.xstream.XStream;
	
public class CreateDataUtils {

	public static List<Person> createListPerson() {
		List<Person> list = new ArrayList<Person>();
		for (int i = 1; i <=2; i++) {
			Person person = new Person("小明_"+i, "李");
			person.setPhone(new PhoneNumber(i, i+"_0123456789"));
			person.setFax(new PhoneNumber(i, i+"_9876543210"));
			list.add(person);
		}
		return list;
	}
	
	
	public static Map<String, Object> createMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", "小明");
		map.put("age", 23);
		return map;
		
	}
	
	public static String createXML() {
		List<Person> list = CreateDataUtils.createListPerson();
		
		XStream xStream = new XStream();
		
		//将java对象映射成一段xml
		// 修改标签名:
		xStream.alias("person", Person.class);
		// 将类中属性作为 标签的属性
		xStream.useAttributeFor(Person.class, "firstname");//属性
		xStream.useAttributeFor(Person.class, "lastname");//属性
		String xml = xStream.toXML(list);
		System.out.println(xml);
		return xml;
	}
}
