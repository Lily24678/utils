package com.lsy.code.utils;

import java.math.BigDecimal;
/**
 * <p>1、使用传统的 +、-、*、/ 等算术运算符直接对其对象进行数学运算,存在精度丢失问题</p>
 * <p>2、在使用 BigDecimal 时，使用它的 BigDecimal(String) 构造器创建对象才有意义。其他的如 BigDecimal b = new BigDecimal(1) 这种，还是会发生精度丢失的问题</p>
 */
public class BigDecimalUtil {
	private BigDecimalUtil() {
	}

	public static BigDecimal add(double v1, double v2) {// v1 + v2
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.add(b2);
	}

	public static BigDecimal sub(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.subtract(b2);
	}

	public static BigDecimal mul(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		return b1.multiply(b2);
	}

	public static BigDecimal div(double v1, double v2) {
		BigDecimal b1 = new BigDecimal(Double.toString(v1));
		BigDecimal b2 = new BigDecimal(Double.toString(v2));
		// 2 = 保留小数点后两位 ROUND_HALF_UP = 四舍五入
		return b1.divide(b2, 2, BigDecimal.ROUND_HALF_UP);// 应对除不尽的情况
	}
	
	public static void main(String[] args) {
		System.out.println(0.05 + 0.01);  
		BigDecimal a = new BigDecimal(0.05);
		BigDecimal b = new BigDecimal(0.01);
		System.out.println(a.add(b));
		BigDecimal c = new BigDecimal("0.05");
		BigDecimal d = new BigDecimal("0.01");
		System.out.println(c.add(d));
		System.out.println(add(0.05, 0.01));
		System.out.println(1.0 - 0.42);  
		System.out.println(4.015 * 100);  
		System.out.println(123.3 / 100); 
	}
}
