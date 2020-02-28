package com.lsy.code.utils;

import java.util.ArrayList;
import java.util.List;

import com.lsy.code.bean.Person;
import com.lsy.code.bean.PhoneNumber;
import com.thoughtworks.xstream.XStream;

/**
 * 将java对象映射成一段xml，也可以将一段xml映射成一个java对象
 */
public class XStreamUtils {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		XStream xStream = new XStream();
		List<Person> list = new ArrayList<Person>();
		for (int i = 1; i <=2; i++) {
			Person person = new Person("小明_"+i, "李");
			person.setPhone(new PhoneNumber(i, i+"_0123456789"));
			person.setFax(new PhoneNumber(i, i+"_9876543210"));
			list.add(person);
		}

		//将java对象映射成一段xml
		// 修改标签名:
		xStream.alias("person", Person.class);
		// 将类中属性作为 标签的属性
		xStream.useAttributeFor(Person.class, "firstname");//属性
		xStream.useAttributeFor(Person.class, "lastname");//属性
		String xml = xStream.toXML(list);
		System.out.println(xml);
		System.out.println("------------------------------------------------------------------");
		//将一段xml映射成一个java对象
		List<Person> list2 = (List<Person>) xStream.fromXML(xml);
		for (Person person : list2) {
			System.out.println(person);
		}
		
		
	}
}
