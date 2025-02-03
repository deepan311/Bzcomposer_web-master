package com.nxsol.bizcomposer.emailSender.action;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.EmailSenderForm;

/*import net.codejava.mail.Date;*/

public class EmailSendAction extends Action{
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {

		String forward = "";
		String action = request.getParameter("tabid");
		HttpSession sess = request.getSession();
		String companyID = (String) sess.getAttribute("CID");
		ConstValue c = new ConstValue();
		c.setCompanyId(Integer.parseInt(companyID));
		if(action.equalsIgnoreCase("send"))
		{
			EmailSenderForm esForm = (EmailSenderForm)form;
			String from = esForm.getFrom();
			String to = esForm.getTo();
			String subject = esForm.getSubject();
			String message = esForm.getMessage();
			FormFile attachFile = esForm.getAttachFile();
			File file = new File(attachFile.getFileName());
			System.out.println(file);
			OutputStream os = new FileOutputStream(file);
			InputStream is = new BufferedInputStream(attachFile.getInputStream());
			int count;
			byte buf[] = new byte[4096];
			while ((count = is.read(buf)) > -1) {
				os.write(buf, 0, count);  
				}
				is.close(); 
				os.close();
			Properties properties = new Properties();
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.user","pritesh.d.java@gmail.com");
			/*properties.put("mail.password","PRI*16189");*/
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("pritesh.d.java@gmail.com","PRI*16189");
				}
			};
			Session session = Session.getInstance(properties, auth);
			Message msg = new MimeMessage(session);
			try {
				msg.setFrom(new InternetAddress("pritesh.d.java@gmail.com"));
			
				InternetAddress[] toAddresses = { new InternetAddress(to) };
				msg.setRecipients(Message.RecipientType.TO, toAddresses);
				msg.setSubject(subject);
				
				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(message, "text/html");
				
				Multipart multipart = new MimeMultipart();
				multipart.addBodyPart(messageBodyPart);
				
				MimeBodyPart attachPart = new MimeBodyPart();
				attachPart.attachFile(file);
				multipart.addBodyPart(attachPart);
				msg.setContent(multipart);
				Transport.send(msg);
				System.out.println("Mail send successfully");
			}catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			System.out.println(attachFile);
		}
		return mapping.findForward(forward);
	}
	
}
