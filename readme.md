# CheckCodeCeShiUtil-Java登陆滑块验证，多边形模块
第一步，随机获取本地或数据库里面的图片出来，随机，既可以使用随机数，并且规定他在一定的随机范围之内，获取下表拼接路径，即可得到一张图片。	
第二步，为图片赋色并把小图片的背景色弄透明，首先，根据图片的宽高和定位的x、y轴，以及想要的图片大小，获取得到图片的相应位置，并把想要的的图片大小赋值为1，其他区域为0，这样我们就得到一个四方形的一个小图片了，然后新建一个半径
第三步，实现CheckCodeCeShiUtil.java

# FileUtil
- 压缩多个文件到压缩包

# DateUtil
- 获取当前时间的前n天的日期时间
- 获取当前时间的后n天的日期时间
- 求出你来到这个世界多少天
- 将日期对象Date转换成特定的字符串

# StringUtil
- 判断字符串为空
- 判断字符串不为空

#XStreamUtil
- http://x-stream.github.io/
- xStream ：将java对象映射成一段xml，也可以将一段xml映射成一个java对象

#JSONLibUtil
- http://json-lib.sourceforge.net/
- 数组或List集合转成JSON.
- 对象或Map集合转成JSON.
- XML转成JSON.
- JSON转成对象或者XML

#EncryDecryUtil
- MD5加密

#BigDecimalUtil
- 使用传统的 +、-、*、/ 等算术运算符直接对其对象进行数学运算,存在精度丢失问题
- 在使用 BigDecimal 时，使用它的 BigDecimal(String) 构造器创建对象才有意义。其他的如 BigDecimal b = new BigDecimal(1) 这种，还是会发生精度丢失的问题

#其他工具
- 测试工具软件（ https://jmeter.apache.org/）：JMETER