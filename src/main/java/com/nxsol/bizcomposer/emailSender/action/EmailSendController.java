package com.nxsol.bizcomposer.emailSender.action;

import com.avibha.bizcomposer.sales.forms.ItemDto;
import com.nxsol.bizcomposer.common.ConstValue;
import com.nxsol.bizcomposer.common.EmailSenderDto;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.Properties;

/**
 * @author sarfrazmalik
 */
@Controller
public class EmailSendController {

    @PostMapping(value={"/SendMail"}, consumes={"multipart/form-data"})
    public ModelAndView SendMail(@ModelAttribute("emailSenderDto") EmailSenderDto emailSenderDto,
        BindingResult bindingResult, @RequestParam("attachFile") MultipartFile attachFile,
        HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        String forward = "sales/itemDetails";
        String action = request.getParameter("tabid");
        HttpSession sess = request.getSession();
        String companyID = (String) sess.getAttribute("CID");
        ConstValue c = new ConstValue();
        c.setCompanyId(Integer.parseInt(companyID));
        if(action.equalsIgnoreCase("send")) {
            String from = emailSenderDto.getFrom();
            String to = emailSenderDto.getTo();
            String subject = emailSenderDto.getSubject();
            String message = emailSenderDto.getMessage();
            File file = new File(attachFile.getOriginalFilename());
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
        ModelAndView modelAndView =new ModelAndView(forward);
        modelAndView.addObject("itemDto", new ItemDto());
        return modelAndView;
    }
}
