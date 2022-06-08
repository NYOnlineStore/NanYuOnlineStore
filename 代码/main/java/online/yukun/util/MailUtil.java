package online.yukun.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

public class MailUtil {
    private static Session session;
    private static MailUtil mailUtil;

    public static MailUtil getMailUtil() {
        //第一次创建mailUtil时进行配置
        if (mailUtil == null) {
            Properties props = new Properties();
            // 发送邮件的服务器的IP和端口
            //设置发件人的SMTP的服务器地址
            props.put("mail.smtp.host", "smtp.qq.com");
            props.put("mail.smtp.port", 465);
            //设置传输协议
            props.setProperty("mail.transport.protocol", "smtp");
            //设置用户的认证方式auth
            props.setProperty("mail.smtp.auth", "true");
            props.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
            props.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
            MailSSLSocketFactory sf = null;
            try {
                sf = new MailSSLSocketFactory();
                sf.setTrustAllHosts(true);
            } catch (GeneralSecurityException e1) {
                e1.printStackTrace();
            }
            props.put("mail.smtp.ssl.socketFactory", sf);
            props.put("mail.smtp.socketFactory.port", 465);
            session = Session.getDefaultInstance(props, new Authenticator() {
                @Override
                public PasswordAuthentication getPasswordAuthentication() {
                    //发件人邮件用户名、密码
                    return new PasswordAuthentication("3538210874@qq.com", "rbyrkyfmxweacijg");
                }
            });
            session.setDebug(true);
            mailUtil = new MailUtil();
        }
        return mailUtil;
    }

    //发送邮件
    public void sendEmail(String email, String subject, String content) {
        try {
            // 设置session,和邮件服务器进行通讯。
            MimeMessage message = new MimeMessage(session);
            // 设置邮件主题
            message.setSubject(subject);
            // 发送 HTML 消息, 可以插入html标签
            message.setContent(content, "text/html;charset=UTF-8");
            // 设置邮件发送日期
            message.setSentDate(new Date());
            //设置发送邮件使用的邮箱地址
            Address address = new InternetAddress("3538210874@qq.com");
            message.setFrom(address);
            // 设置邮件接收方的地址
            Address toAddress = new InternetAddress(email);
            message.addRecipient(Message.RecipientType.TO, toAddress);
            // 发送邮件
            Transport.send(message);
            // System.out.println("send ok!");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    //注册验证
    public void registerUser(String email, String name, String pass) {
        //发送邮件
        MailUtil mailUtil = MailUtil.getMailUtil();
        //内容中使用html
        mailUtil.sendEmail(email,"激活","aaa");
        System.out.println("注册成功,请到邮箱点击链接激活");
}
}

