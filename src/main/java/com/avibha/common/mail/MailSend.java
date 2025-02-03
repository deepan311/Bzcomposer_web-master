/*
 * Author : Avibha IT Solutions Copyright 2006 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 *  
 */

package com.avibha.common.mail;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import com.avibha.common.log.Loger;

/**
 * @author avibha
 * 
 */
public class MailSend {

	private String mailHost;
	private String mailServerProtocol;
	// private String mailUsername;
	// private String mailPassword;
	private String mailFrom;

	public MailSend() {
		try {
			InitialContext ic = new InitialContext();
			this.mailHost = (String) ic.lookup("java:comp/env/MAILHOST");
			this.mailServerProtocol = (String) ic.lookup("java:comp/env/mailProtocol");
			// this.mailUsername = (String) ic.lookup("java:comp/env/MailUsername");
			// this.mailPassword = (String) ic.lookup("java:comp/env/MailPassword");
			this.mailFrom = (String) ic.lookup("java:comp/env/MailFrom");
			
			Loger.log(Loger.DEBUG, mailServerProtocol);
			Loger.log(Loger.DEBUG, mailHost);
		} catch (NamingException e1) {
			System.out.println("Error in Initializing Mail Setting Parameters" + e1);
		} catch (Exception e) {
			System.out.println("Error in Initializing Mail Setting Parameters" + e);
		}
	}

	public boolean sendMail(String txtTo, String txtSubject, String txtMessage) {
		boolean flag = true;
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", mailServerProtocol);
			props.setProperty("mail.smtp.host", mailHost);

			// Must UnComment these 2 parameters if User Name & Password are requied
			// props.setProperty("mail.user", mailUsername);
			// props.setProperty("mail.password", mailPassword);

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(txtSubject);
			message.setFrom(new InternetAddress(mailFrom));
			message.setContent(txtMessage, "text/html");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(txtTo));

			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception e) {
			flag = false;
			Loger.log(Loger.DEBUG, e);
		}
		return flag;
	}

	/**
	 * This methods sends a email message to the given comma separated
	 * receipents list as parameter by using given mail server and the type set
	 * in web.xml
	 */
	public boolean sendMail(String txtFrom, String txtTo, String txtSubject, String txtMessage, String smtpAddress, String mailServerType) {
		boolean flag = true;
		try {
			Properties prop = System.getProperties();
			prop.put(mailServerType, smtpAddress);
			Session mailSession = Session.getInstance(prop, null);
			MimeMessage mimemsg = new MimeMessage(mailSession);

			// mimemsg.setRecipients(Message.RecipientType.TO,TO);
			mimemsg.setRecipients(Message.RecipientType.TO, txtTo);

			// mimemsg.setFrom(new InternetAddress(FROM));
			mimemsg.setFrom(new InternetAddress(txtFrom));

			// mimemsg.setSubject(MSG_SUBJECT);
			mimemsg.setSubject(txtSubject);

			// mimemsg.setText(MSG_TEXT);
			mimemsg.setText(txtMessage);
			javax.mail.Transport.send(mimemsg);
		} catch (Exception e) {
			flag = false;
			Loger.log(Loger.DEBUG, e);
		}
		return flag;
	}

	public boolean sendMail(String txtTo, String txtSubject, String txtMessage, String frommail) {
		boolean flag = true;
		try {
			Properties props = new Properties();
			props.setProperty("mail.transport.protocol", mailServerProtocol);
			props.setProperty("mail.smtp.host", mailHost);

			// Must UnComment these 2 parameters if User Name & Password are requied
			// props.setProperty("mail.user", mailUsername);
			// props.setProperty("mail.password", mailPassword);

			Session mailSession = Session.getDefaultInstance(props, null);
			mailSession.setDebug(true);
			Transport transport = mailSession.getTransport();

			MimeMessage message = new MimeMessage(mailSession);
			message.setSubject(txtSubject);
			message.setFrom(new InternetAddress(frommail));
			message.setContent(txtMessage, "text/html");
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(txtTo));

			transport.connect();
			transport.sendMessage(message, message.getRecipients(Message.RecipientType.TO));
			transport.close();
		} catch (Exception e) {
			flag = false;
			Loger.log(Loger.DEBUG, e);
		}
		return flag;
	}

}