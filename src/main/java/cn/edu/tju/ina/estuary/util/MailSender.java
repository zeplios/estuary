package cn.edu.tju.ina.estuary.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {
	private String to;
	private String subject;
	private String text;
	
	/**
	 * toAddress, subject and text is needed, please call setter to set them
	 */
	public void send() {
		String from = "ina@tju.edu.cn";

        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.put("mail.smtp.host", "smtp.tju.edu.cn");
        properties.setProperty("mail.smtp.auth", "true");
        Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ina@tju.edu.cn", "203@ina");
			}
		};
        Session session = Session.getDefaultInstance(properties, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, 
                    new InternetAddress(to));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	/**
	 * toAddress, subject and text is needed, please call setter to set them
	 */
	public void sendHtml() {
		String from = "ina@tju.edu.cn";

        Properties properties = new Properties();//创建Properties对象
        properties.setProperty("mail.transport.protocol", "smtp");//设置传输协议
        properties.put("mail.smtp.host", "smtp.tju.edu.cn");//设置发信邮箱的smtp地址
        properties.setProperty("mail.smtp.auth", "true"); //验证
        Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("ina@tju.edu.cn", "203@ina");
			}
		};
        Session session = Session.getDefaultInstance(properties, auth);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipient(Message.RecipientType.TO, 
                    new InternetAddress(to));
            message.setSubject(subject);
            Multipart mainPart = new MimeMultipart();    
  	      	BodyPart html = new MimeBodyPart();    
  	      	html.setContent(text, "text/html; charset=utf-8");    
  	      	mainPart.addBodyPart(html);    
  	      	message.setContent(mainPart); 
            Transport.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
	}
	
	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
