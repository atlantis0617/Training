package com.cn.mail;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 邮件发送工具类
 * 
 * @author 袁华洋
 *
 */
public class MailUtils {
	public static void sendMail(String protocol,String userName,String psw,String to,String fileUrl) throws Exception{
		Properties prop = new Properties();
		prop.setProperty("mail.host", protocol);
		prop.setProperty("mail.transport.protocol", "smtp");
		prop.setProperty("mail.smtp.auth", "true");
		// 1、创建session
		Session session = Session.getInstance(prop);
		// 开启Session的debug模式，这样就可以查看到程序发送Email的运行状态
		// session.setDebug(true);
		// 2、通过session得到transport对象
		Transport ts = session.getTransport();
		// 3、连上邮件服务器
		ts.connect(protocol, userName, psw);
		// 4、创建邮件
		Message message = createMixedMail(session,to,fileUrl);
		// 5、发送邮件
		ts.sendMessage(message, message.getAllRecipients());
		System.out.println("邮件发送成功！！！");
		ts.close();
	}
	/**
	 * @param session
	 * @return 生成一封带附件和带图片的邮件
	 * @throws MessagingException
	 * @throws AddressException
	 * @throws UnsupportedEncodingException
	 */
	private static Message createMixedMail(Session session,String to,String fileUrl) throws Exception {
		MimeMessage message = new MimeMessage(session);

		// 设置邮件的基本信息
		// 发件人
		message.setFrom(new InternetAddress("yangzijk2008@163.com"));
		// 收件人
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		// 邮件标题
		message.setSubject("JavaMail邮件发送测试");

		// 创建邮件正文，为了避免邮件正文中文乱码问题，需要使用charset=UTF-8指明字符编码
		MimeBodyPart text = new MimeBodyPart();
		text.setContent("使用JavaMail创建的带附件的邮件", "text/html;charset=UTF-8");

		// 创建邮件附件
		MimeBodyPart attach = new MimeBodyPart();
		DataHandler dh = new DataHandler(new FileDataSource(fileUrl));
		attach.setDataHandler(dh);
		attach.setFileName(dh.getName()); //

		// 创建容器描述数据关系
		MimeMultipart mp = new MimeMultipart();
		mp.addBodyPart(text);
		mp.addBodyPart(attach);
		mp.setSubType("mixed");

		message.setContent(mp);
		message.saveChanges();
		// 将创建的Email写入到E盘存储
//		message.writeTo(new FileOutputStream("E:\\attachMail.eml"));
		// 返回生成的邮件
		return message;
	}
	public static void main(String[] args) throws Exception {
		String protocol = "smtp.163.com";
		String userName = "yangzijk2008@163.com";
		String psw = "king2005251018";
		String to = "973549268@qq.com";
		String fileUrl = "src\\test.jpg";
		sendMail(protocol, userName, psw, to, fileUrl);
	}
}
