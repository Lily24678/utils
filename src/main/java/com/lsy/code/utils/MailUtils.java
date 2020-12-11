package com.lsy.code.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailUtils {
    public static void sendMail(String to, String code) {
        String host = "smtp.163.com";   //发件人使用发邮件的电子信箱服务器
        String from = "lishanyun_lsy@163.com";
        String authorpassword = "BGDAEEMWDFDGBRVQ";//开启POP3/SMTP服务的授权码

        Properties props = new Properties();
        //javax.mail.MessagingException: Could not connect to SMTP host: localhost, port: 25;
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.auth", "true"); //这样才能通过验证
        // 获得连接:
        Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(from, authorpassword);
            }
        });
        // 构建邮件:
        Message message = new MimeMessage(session);
        try {
            message.setFrom(new InternetAddress(from));
            // 设置收件人:
            // TO:收件人   CC:抄送   BCC:暗送,密送.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            // 主题:
            message.setSubject("来自黑马官方商城的激活邮件!");
            // 正文:
            message.setContent("<h1>来自购物天堂黑马官方商城的激活邮件:请点击下面链接激活!</h1><h3><a href='http://localhost:8080/myStore/UserServlet?method=active&code=" + code + "'>http://localhost:8080/store_v2.0/UserServlet?method=active&code=" + code + "</a></h3>", "text/html;charset=UTF-8");
            // 发送邮件:
            Transport.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
