package com.avibha.bizcomposer.forgetpassword.util;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class MailSender {

	

	public static void main(String sfdf[]) {

		String subject = "Testing Subject";
		String email = "tulsi0789@gmail.com";
		String message = "Hi, Below is your login info for the BZC";
		message += "<b>Your User Id:</b>&nbsp; Test" ;
		message += "<br/><b>Password:</b>&nbsp; Test" ;
		message += "Thank you,<br><br> BZcomposer";


		new MailSender().sendMail(subject, email, message, "html");

	}

	public void sendMail(String subject, String email, String strBody, String strMailType) {

		final String authAddress = "support@bzcomposer.com";
		final String authPassword = "nextbitstbptbcp";
		String smtpServer = "mail.bzcomposer.com";
		String smtpPort = "2525";

		try {
			Properties props = new Properties();
			props.put("mail.smtp.host", smtpServer);
			props.put("mail.smtp.port", smtpPort);
			props.put("mail.smtp.auth", true);

			// create some properties and get the default Session
			Session sessionMail = Session.getInstance(props, new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(authAddress, authPassword);
				}
			});

			sessionMail.setDebug(true);

			// create a message
			Message msg = new MimeMessage(sessionMail);

			if (strMailType.equalsIgnoreCase("text"))
				{
					msg.setContent(strBody, "text/plain");
				}
			else if (strMailType.equalsIgnoreCase("html"))
				{
					msg.setContent(strBody, "text/html");
				}

			// set the from and to address
			InternetAddress addressFrom = new InternetAddress(authAddress);
			msg.setFrom(addressFrom);

			InternetAddress[] addressTo = new InternetAddress[1];
			addressTo[0] = new InternetAddress(email);
			msg.setRecipients(Message.RecipientType.TO, addressTo);

			msg.setSubject(subject);
			
			Transport.send(msg);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
	/*
	 * public MailSender() {
	 * 
	 * }
	 * 
	 * public MailSender(String sender, String password, String recipients, String
	 * subject, String body, String mailtype) {
	 * 
	 * strSender = sender; strPassword = password; strRecipients = recipients;
	 * strSubject = subject; strBody = body; strMailType = mailtype; strHost =
	 * "mail.nextbits.com";
	 * 
	 * }
	 * 
	 * public MailSender(String provider, String sender, String password, String
	 * recipients, String subject, String body, String mailtype, boolean auth) {
	 * 
	 * strSender = sender; strPassword = password; strRecipients = recipients;
	 * strSubject = subject; strBody = body; strMailType = mailtype; strHost =
	 * provider; strRecipients = provider; strSender = sender; strPassword =
	 * password; strHost = recipients; strSubject = subject; strBody = body;
	 * strMailType = mailtype;
	 * 
	 * }
	 */


// public void sendMail() {
//
// Properties objProps = System.getProperties();
// objProps.put("mail.smtp.host", strHost);
// objProps.put("mail.user", strSender);
// objProps.put("mail.password", strPassword);
//
// objSession = Session.getDefaultInstance(objProps, null);
// objSession.setDebug(true);
//
// try {
// Message message = new MimeMessage(objSession);
//
// InternetAddress inetFrom = new InternetAddress(strSender);
// InternetAddress[] inetTo = InternetAddress.parse(strRecipients);
// for (int i = 0; i < inetTo.length; i++) {
// message.setFrom(inetFrom);
// InternetAddress inetReceiver = new
// InternetAddress(inetTo[i].getAddress());
//
// message.setRecipient(Message.RecipientType.TO, inetReceiver);
// message.setSubject(strSubject);
// if (strMailType.equalsIgnoreCase("text"))
// message.setContent(strBody, "text/plain");
// else if (strMailType.equalsIgnoreCase("html"))
// message.setContent(strBody, "text/html");
//
// Transport.send(message);
// }
//
//
// } catch (MessagingException me) {
// me.printStackTrace();
// }
// }

/*
 * public void sendMail() { Properties objProps = System.getProperties();
 * objProps.put("mail.smtp.starttls.enable", "true");
 * objProps.put("mail.smtp.port", "465"); objProps.put("mail.smtps.auth",
 * "true"); objProps.put("mail.smtp.host", "mail.bzcomposer.com");
 * 
 * // String host = "smtp.gmail.com";
 * 
 * objProps.put("mail.smtp.starttls.enable", "true");
 * 
 * // objProps.put("mail.smtp.ssl.enable", false);
 * 
 * objProps.put("mail.smtp.starttls.enable", false);
 * 
 * objProps.put("mail.smtp.ssl.trust", strHost); objProps.put("mail.smtp.user",
 * strSender); objProps.put("mail.smtp.password", strPassword);
 * objProps.put("mail.smtp.port", "587"); objProps.put("mail.smtp.auth",
 * strAuth);
 * 
 * 
 * if (strMailType == null || ("").equals(strMailType)) { strMailType = "html";
 * } if (strSubject == "" || ("").equals(strSubject)) { strSubject =
 * "Message From BZcomposer"; } if (strSender == "" || ("").equals(strSender)) {
 * strSender = "bzcomposer-support"; } // objProps.put("mail.user",
 * "cdi2go@yahoo.com"); // objProps.put("mail.password", "jason8572");
 * 
 * 
 * objProps.put("mail.smtp.host", "smtp.gmail.com");
 * objProps.put("mail.smtp.socketFactory.port", "465");
 * objProps.put("mail.smtp.socketFactory.class",
 * "javax.net.ssl.SSLSocketFactory"); objProps.put("mail.smtp.auth", "true");
 * objProps.put("mail.smtp.port", "805");
 * 
 * 
 * Session session = Session.getDefaultInstance(objProps, new
 * javax.mail.Authenticator() {
 * 
 * @Override protected PasswordAuthentication getPasswordAuthentication() {
 * return new PasswordAuthentication(strSender, strPassword); } });
 * 
 * try {
 * 
 * SMTPMessage message = new SMTPMessage(session); // Message message = new
 * MimeMessage(session); message.setFrom(new InternetAddress(strSender));
 * message.setRecipients(Message.RecipientType.TO,
 * InternetAddress.parse(strRecipients));
 * 
 * // String messageBody =
 * "Hello"+"\n"+"This is Test mail from local."+"\n"+"Have // a Great day.";
 * 
 * message.setSubject(strSubject); //
 * System.out.println("MessageBoby:"+strBody); message.setText(strBody); //
 * message.setText(""); //
 * message.setContent("This Is my First Mail Through Java"); //
 * message.setContent(message, "text/plain"); // message.setContent(mp);
 * message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS); int returnOption =
 * message.getReturnOption(); System.out.println(returnOption);
 * Transport.send(message); // System.out.println("Successfully Sent");
 * 
 * } catch (MessagingException e) { throw new RuntimeException(e); }
 * 
 * 
 * try { Message message = new MimeMessage(objSession); InternetAddress inetFrom
 * = new InternetAddress(strRecipients); InternetAddress[] inetTo =
 * InternetAddress.parse(strHost); for (int i = 0; i < inetTo.length; i++) {
 * message.setFrom(inetFrom); InternetAddress inetReceiver = new
 * InternetAddress( inetTo[i].getAddress());
 * 
 * message.setRecipient(Message.RecipientType.TO, inetReceiver);
 * message.setSubject(strSubject); if (strMailType.equalsIgnoreCase("text"))
 * message.setContent(strBody, "text/plain"); else if
 * (strMailType.equalsIgnoreCase("html")) message.setContent(strBody,
 * "text/html");
 * 
 * Transport.send(message); // this.sendFromGMail(); }
 * 
 * } catch (MessagingException me) { me.printStackTrace(); }
 * 
 * } }
 */
/*
 * Properties props = new Properties(); props.put("mail.smtp.host",
 * "smtp.gmail.com"); props.put("mail.smtp.socketFactory.port", "465");
 * props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
 * props.put("mail.smtp.auth", "true"); props.put("mail.smtp.port", "805");
 * 
 * Session session = Session.getInstance(props,new javax.mail.Authenticator() {
 * 
 * @Override protected PasswordAuthentication getPasswordAuthentication() {
 * return new PasswordAuthentication("ravi.bh.java@gmail.com","nxsolteam"); }
 * });
 * 
 * try {
 * 
 * SMTPMessage message = new SMTPMessage(objSession); message.setFrom(new
 * InternetAddress("ravi.bh.java@gmail.com"));
 * message.setRecipients(Message.RecipientType.TO,
 * InternetAddress.parse(strRecipients));
 * 
 * message.setSubject(strSubject); message.setText(strBody);
 * //message.setContent("This Is my First Mail Through Java");
 * message.setNotifyOptions(SMTPMessage.NOTIFY_SUCCESS); int returnOption =
 * message.getReturnOption(); System.out.println(returnOption);
 * Transport.send(message); System.out.println("Successfully Sent");
 * 
 * } catch (MessagingException e) { throw new RuntimeException(e); } }
 * 
 * /*public void sendFromGMail() { String host = "smtp.gmail.com"; String userid
 * = "designmaxx.reg"; String password = "designmaxx.reg1"; strSender =
 * "admin@designmaxxx.com"; if (strMailType == null || ("").equals(strMailType))
 * { strMailType = "html"; } if (strSubject == "" || ("").equals(strSubject)) {
 * strSubject = "Message From Designmaxx"; } if (strSender == "" ||
 * ("").equals(strSender)) { strSender = "designmaxx-support"; }
 * 
 * try { Properties props = System.getProperties();
 * props.put("mail.smtp.starttls.enable", "true"); props.put("mail.smtp.host",
 * host); props.setProperty("mail.transport.protocol", "smtps");
 * props.put("mail.smtp.user", userid); props.put("mail.smtp.password",
 * password); props.put("mail.smtp.port", "465"); props.put("mail.smtps.auth",
 * "true"); Session session = Session.getDefaultInstance(props, null);
 * MimeMessage message = new MimeMessage(session); InternetAddress fromAddress =
 * null; InternetAddress[] inetTo = null;
 * 
 * try { fromAddress = new InternetAddress(strSender); inetTo =
 * InternetAddress.parse(strRecipients); } catch (AddressException e) {
 * 
 * e.printStackTrace(); } for (int i = 0; i < inetTo.length; i++) {
 * message.setFrom(fromAddress); InternetAddress inetReceiver = new
 * InternetAddress( inetTo[i].getAddress());
 * 
 * message.setRecipient(Message.RecipientType.TO, inetReceiver);
 * message.setSubject(strSubject); if (strMailType.equalsIgnoreCase("text"))
 * message.setContent(strBody, "text/plain"); else if
 * (strMailType.equalsIgnoreCase("html")) message.setContent(strBody,
 * "text/html");
 * 
 * Transport transport = session.getTransport("smtps"); transport.connect(host,
 * userid, password); transport.sendMessage(message,
 * message.getAllRecipients()); transport.close(); // Transport.send(message); }
 * // SMTPSSLTransport transport //
 * =(SMTPSSLTransport)session.getTransport("smtps");
 * 
 * } catch (MessagingException e) { e.printStackTrace(); } }
 */
