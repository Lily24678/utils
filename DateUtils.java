package utils;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtils {
	/**
	 * 
	 * @param currentDate
	 *            当前日期对象
	 * @param n
	 *            要计算的前n天数
	 * @return
	 */
	// 获取当前时间的前n天的日期时间
	public static Date getBeforeCurrentDate(Date currentDate, int n) {
		// 将当前日期转换成毫秒值n1
		long n1 = currentDate.getTime();
		// 计算n天的等于多少毫秒值n2
		long n2 = Math.abs(n) * 24 * 60 * 60 * 1000L;
		// 前n的毫秒值n3=n1-n2
		long n3 = n1 - n2;
		// 返回Date对象
		return new Date(n3);
	}

	/**
	 * 
	 * @param currentDate
	 *            当前日期对象
	 * @param n
	 *            要计算的后n天数
	 * @return
	 */
	// 获取当前时间的后n天的日期时间
	public static Date getAfterCurrentDate(Date currentDate, int n) {
		// 将当前日期转换成毫秒值n1
		long n1 = currentDate.getTime();
		// 计算n天的等于多少毫秒值n2
		long n2 = Math.abs(n) * 24 * 60 * 60 * 1000L;
		// 前n的毫秒值n3=n1+n2
		long n3 = n1 + n2;
		// 返回Date对象
		return new Date(n3);
	}

	/**
	 * 
	 * @param date
	 *            要被格式化的日期对象
	 * @param format
	 *            格式化形式
	 * @return
	 */
	// 将日期对象Date转换成特定的字符串
	public static String getDateFormatStr(Date date, String format) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.format(date);
	}

	/**
	 * 
	 * @param dateStr
	 *            日期字符串
	 * @param format
	 *            格式化形式
	 * @return
	 * @throws ParseException
	 */
	// 将日期字符串转换成日期对象Date
	public static Date getDate(String dateStr, String format) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
		return simpleDateFormat.parse(dateStr);
	}

	/**
	 * 
	 * @param birthDateStr
	 *            出生日期字符串形式，必须写成如2000-01-23的形式
	 * @param format
	 * @return
	 * @throws ParseException
	 */
	// 案例：求出你来到这个世界多少天
	public static long demo1(String birthDateStr) {
		// 出生日期
		// String dateStr="1993-02-28";
		long birthTime;
		try {
			birthTime = getDate(birthDateStr, "yyyy-MM-dd").getTime();
			long currTime = new Date().getTime();
			long time = currTime - birthTime;
			return time / 1000 / 60 / 60 / 24;
		} catch (Exception e) {
			e = new Exception("应传入字符串的格式yyyy-MM-dd");
			e.printStackTrace();
			return -1;
		}

	}

	public static void main(String[] args) throws ParseException {
		Date beforeCurrentDate = getBeforeCurrentDate(new Date(), 31);
		System.out.println(getDateFormatStr(beforeCurrentDate, "yyyy-MM-dd hh:mm:ss"));
		Date afterCurrentDate = getAfterCurrentDate(new Date(), 3);
		System.out.println(getDateFormatStr(afterCurrentDate, "yyyy-MM-dd"));
		System.out.println(demo1("1993-02-28"));
	}
}
