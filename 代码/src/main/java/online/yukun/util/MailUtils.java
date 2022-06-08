package online.yukun.util;


import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;

public class MailUtils {
    /**
     *
     * @param email     接收者邮箱
     * @param subject   邮件主题
     * @param emailMsg  邮件内容
     * @throws AddressException
     * @throws MessagingException
     */
    public static void sendMail(String email, String subject,String emailMsg)
            throws AddressException, MessagingException {
        Properties properties = new Properties();
        properties.put("mail.transport.protocol", "smtp");// 连接协议
        properties.put("mail.smtp.host", "smtp.qq.com");// 主机名
        properties.put("mail.smtp.port", 465);// 端口号
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.ssl.enable", "true");// 设置是否使用ssl安全连接 ---一般都使用
        properties.put("mail.debug", "true");// 设置是否显示debug信息 true 会在控制台显示相关信息
        // 得到回话对象
        Session session = Session.getInstance(properties);
        // 获取邮件对象
        Message message = new MimeMessage(session);
        // 设置邮件主题
        message.setSubject(subject);
        // 发送 HTML 消息, 可以插入html标签
        message.setContent(emailMsg, "text/html;charset=UTF-8");
        // 设置邮件发送日期
        message.setSentDate(new Date());
        //设置发送邮件使用的邮箱地址
        Address address = new InternetAddress("3538210874@qq.com");
        message.setFrom(address);
        // 设置邮件接收方的地址
        Address toAddress = new InternetAddress(email);
        message.addRecipient(Message.RecipientType.TO, toAddress);

        // 得到邮差对象
        Transport transport = session.getTransport();
        // 连接自己的邮箱账户
        transport.connect("3538210874@qq.com", "rbyrkyfmxweacijg");// 密码为QQ邮箱开通的smtp服务后得到的客户端授权码
        // 发送邮件
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}


