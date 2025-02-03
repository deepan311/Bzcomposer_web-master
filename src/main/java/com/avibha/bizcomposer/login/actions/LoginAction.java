package com.avibha.bizcomposer.login.actions;

import javax.crypto.Cipher;  
import javax.crypto.KeyGenerator;   
import javax.crypto.SecretKey;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.avibha.common.City;
import com.avibha.common.Country;
import com.avibha.common.State;
import com.avibha.common.utility.CountryState;
import com.nxsol.bzcomposer.company.ConfigurationDAO;
import com.avibha.bizcomposer.File.dao.CompanyInfo;
import com.avibha.bizcomposer.login.forms.MultiUserForm;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;

import com.avibha.bizcomposer.configuration.forms.configurationForm;
import com.avibha.bizcomposer.login.dao.LoginDAO;
import com.avibha.bizcomposer.login.dao.LoginDAOImpl;
import com.avibha.bizcomposer.login.forms.LoginForm;
import com.avibha.bizcomposer.registration.dao.RegistrationDAO;
import com.avibha.bizcomposer.registration.dao.RegistrationDAOImpl;
import com.avibha.common.log.Loger;
import com.avibha.common.utility.Path;
import com.nxsol.bizcomposer.common.ConstValue;

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

public class LoginAction extends Action 
{
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request,HttpServletResponse response) throws Exception 
	{
		LoginForm muform=(LoginForm)form;
		Loger.log(LoginAction.class);
		HttpSession sess = request.getSession();
		Path p = new Path();
		p.setPathvalue(request.getContextPath());
		request.getSession().setAttribute("path", p);
		String action = request.getParameter("tabid");
		String cID = request.getParameter("selectedCompanyId");
		if(null != cID ) {
			ConstValue.setCompanyId(Integer.parseInt(cID));
		}
		final String SUCCESS = "success";
		String forward = "Success";
		
		if(action.equalsIgnoreCase("register")) {
			request.getSession().setAttribute("path", p);
			CountryState cs = new CountryState();
			request.setAttribute("countryList", cs.getCountryList());
			forward="Success5";
		}
		if(action.equalsIgnoreCase("AdminLogin")) 
		{
			forward="AdminloginSuccess";
		}
		else if(action.equalsIgnoreCase("chkLoginDetails")) 
		{
			System.out.println("chkLoginDetails ---->>>> CCC:");
			String username=muform.getUserName().trim();
			String password=muform.getPassword().trim();
			String remember = request.getParameter("actionType");
			request.getCookies().equals("validateUser");
			//System.out.println("Going to index page with path value:"+p);
			//request.getSession().setAttribute("CID", companyID);
			//request.getSession().setAttribute("username", username);
			LoginDAO loginDAO=new LoginDAOImpl();
			String Role=loginDAO.checkUserRole(username,password,muform.getCompanyid(),request);
			if(Role.toString().equals("SuperAdmin")) {
				boolean status=loginDAO.checkUserLogin(username,password,muform.getCompanyid(),request);
				ArrayList<LoginForm> list = LoginDAOImpl.getAllCompany(request);
				System.out.println(list);
				request.setAttribute("cList", list);
				forward = "admindashboard";
			}else {
				boolean status=loginDAO.checkUserLogin(username,password,muform.getCompanyid(),request);
				forward = "Success";
				if(status == false)
				{
					ActionErrors e=new ActionErrors();
					e.add("loginerror", new ActionMessage("err.user.username.wrong"));
					saveErrors(request,e);
					muform.setUserName("");
					muform.setPassword("");
				
					forward="Success1234";
				}
				if(status == true)
				{
					if(forward=="Failure")
					{
						System.out.println("Inside Failure");
						muform.setUserName("");
						muform.setPassword("");
						muform.setCompanyid("");
					}
					LoginForm cform=(LoginForm)form;
					String companyID = "1";
					request.getCookies().equals("validateUser");
					/*p.setPathvalue(request.getContextPath());
					request.getSession().setAttribute("path", p);*/
					request.getSession().setAttribute("CID", companyID);
					//request.getSession().setAttribute("username", username);	/*commented on 12-06-2019*/
					request.getSession().setAttribute("password", password);   /*Added on 22-05-2019*/
					//request.getSession().setAttribute("remember", remember);   /*Added on 22-05-2019*/
					ArrayList<LoginForm> list = LoginDAOImpl.getCompanyDetails(companyID, request);
					ArrayList<LoginForm> list2 = LoginDAOImpl.getCompanyDetails2(companyID, request);
					
					request.setAttribute("acList", list);
					request.setAttribute("acList2", list2);
					set(request,response,muform,username,password,remember);
					
					System.out.println("Membership Level:"+request.getSession().getAttribute("membershipLevel"));
					
					if (request.getSession().getAttribute("membershipLevel")!= null && request.getSession().getAttribute("membershipLevel").equals("standard"))
					{
						System.out.println("Inside checking membershipLevel session and value is:"+request.getSession().getAttribute("membershipLevel"));
						forward="SuccessDashboard";
					}
					else
					{
						forward="Success";
					}
				}	
			}
			
		}else if(action.equalsIgnoreCase("index"))
		{
			System.out.println("Goes to index page without error");
			forward = "Failure";
		}
		
		else if(action.equalsIgnoreCase("loginPage"))
		{
			System.out.println("goes to loginPage..");
			//request.getSession().setAttribute("path", p);
			String companyID = "1";
			ArrayList<LoginForm> list = LoginDAOImpl.getCompanyDetails(companyID, request);
			request.setAttribute("acList", list);
			forward = "Success1234";
		}
		else if(action.equalsIgnoreCase("DeleteCompany"))
		{
			System.out.println("Inside DeleteCompany action");
			String compID= request.getParameter("CompanyID");
			System.out.println("CompanyID you want to delete is:"+compID);
			boolean status= CompanyInfo.deleteCompany(compID);
			if(status){
				System.out.println(compID + "company deleted");
			}
			forward="Success";
		}
		/*if(action.equalsIgnoreCase("forgotPassweord"))
		{
			System.out.println("Inside forgotPassword condition");
			String userID = request.getParameter("LoginID");
			System.out.println("Entered loginAddress is:"+userID);
			LoginForm cform=(LoginForm)form;
			String companyID = "1";
			Path p = new Path();
			request.getCookies().equals("validateUser");
			//response.addCookie(cookie);
			p.setPathvalue(request.getContextPath());
			request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username",userId);
			ArrayList<LoginForm> list = LoginDAOImpl.getCompanyDetails(companyID, request);
			ArrayList<LoginForm> list2 = LoginDAOImpl.getCompanyDetails2(companyID, request);
			request.setAttribute("acList", list);
			request.setAttribute("acList2", list2);
			set(request,response,muform);
			
			/*boolean status1=LoginDAOImpl.isUserExists(userID);
			
			if(status1)
			{
				System.out.println("User is Exist");
				forward = "Success";
			}
			else
			{
				System.out.println("User doesn't Exist");
				forward = "Failure";
			}
		}*/
		else if(action.equalsIgnoreCase("invoice"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			request.getCookies().equals("validateUser");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username", muform.getUserName());
			forward="invoice11";
		}
		else if(action.equalsIgnoreCase("estimation"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			request.getCookies().equals("validateUser");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username", muform.getUserName());
			forward="estimation11";
		}
		else if(action.equalsIgnoreCase("PurchaseOrder"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			request.getCookies().equals("validateUser");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username", muform.getUserName());
			forward="purchaseOrder11";
		}
		else if(action.equalsIgnoreCase("openCustomer"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			request.getCookies().equals("validateUser");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username", muform.getUserName());
			forward="customer11";
		}
		else if(action.equalsIgnoreCase("VONODO"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			request.getCookies().equals("validateUser");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username", muform.getUserName());
			forward="vendor11";
		}
		else if(action.equalsIgnoreCase("Item"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			request.getCookies().equals("validateUser");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username", muform.getUserName());
			forward="item11";
		}
		else if(action.equalsIgnoreCase("Dashboard"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			request.getCookies().equals("validateUser");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("username", muform.getUserName());
			forward="dashboard11";
		}
		else if(action.equalsIgnoreCase("selectedCompanyHome"))
		{
			String companyID = request.getParameter("selectedCompanyId");
			String companyName = request.getParameter("companyName");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			System.out.println("companyID is:"+companyID);
			request.getSession().setAttribute("user",companyName);
			request.getSession().setAttribute("username", "user");
			request.getSession().setAttribute("userRole", "User");
			forward="Success3";
		}
		
		else if(action.equalsIgnoreCase("selectedCompany"))
		{
			ConfigurationDAO dao = new ConfigurationDAO();
			configurationForm configurationForm = null;
			String companyID = request.getParameter("selectedCompanyId");
			String companyName = request.getParameter("companyName");
			p.setPathvalue(request.getContextPath());
			//request.getSession().setAttribute("path", p);
			request.getSession().setAttribute("CID", companyID);
			request.getSession().setAttribute("companyID", companyID);
			System.out.println("companyID is:"+companyID);
			request.getSession().setAttribute("user",companyName);
			
			
			 int defaultModule = dao.getdefaultModuleName(companyID);
			 System.out.println(defaultModule);
			
			 if(defaultModule == 1) {
				 forward="invoicepage";
			 }else if(defaultModule == 2) {
				 forward="purchaseorderpage";
			 }else if(defaultModule == 3) {
				 forward="customerpage";
			 }else if(defaultModule == 4) {
				 forward="vendorpage";
			 }else if(defaultModule == 5) {
				 forward="inventorypage";
			 }else if(defaultModule == 6) {
				 forward="navigationpage";
			 }/*else if(defaultModule == 7) {
				 forward="estimationpage";
			 }*/
		}
		
		else if(action.equalsIgnoreCase("confirmSubscribe"))
		{
			System.out.println("inside confirmSubscibe method...");
		}
		
		else if(action.equalsIgnoreCase("subscribe"))
		{
			String email = request.getParameter("emailId");
			String to = email;
			/*String from = "support@bzcomposer.com";*/
			String from = "nextbits.jason@gmail.com";
			/*String from = "ravi.bh.java@gmail.com";*/				//Commented on 07-10-2019

		      Properties properties = System.getProperties();
		      properties.setProperty("mail.smtp.host", "localhost");
		      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		      // Get a Properties object
		         Properties props = System.getProperties();
		         props.setProperty("mail.smtp.host", "smtp.gmail.com");
		         props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		         props.setProperty("mail.smtp.socketFactory.fallback", "false");
		         props.setProperty("mail.smtp.port", "465");
		         props.setProperty("mail.smtp.socketFactory.port", "465");
		         props.put("mail.smtp.auth", "true");
		         props.put("mail.debug", "true");
		         props.put("mail.store.protocol", "pop3");
		         props.put("mail.transport.protocol", "smtp");
		         
		         /*final String username = "support@bzcomposer.com";
		         final String password = "nextbitstbptbcp";*/
		         
		         final String username = "nextbits.jason@gmail.com";
		         final String password = "Jason90621#";
		         
		         /*final String username = "ravi.bh.java@gmail.com";				//commented on 07-10-2019
		         final String password = "nxsolteam";*/
		         
		         
		      //Session session = Session.getDefaultInstance(properties);
		         Session session = Session.getDefaultInstance(props, 
                        new Authenticator(){
                           protected PasswordAuthentication getPasswordAuthentication() {
                              return new PasswordAuthentication(username, password);
                           }});

		      try
		      {
		         MimeMessage message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		         message.setSubject("BzComposer - Please confirmation your subscription");
		         message.setText("We're glad you're here,"+to+"\nThank you for subscribing to BzComposer.\n"
		         		+ "If you still cannot subscribe, please copy this link and paste it in your browser :<a href='http://localhost:8035/bzcomposerweb2/Login.do?tabid=confirmSubscribe?address="+to+"'></a>");

		         Transport.send(message);
		         forward = "SuccessContact";
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		         forward = "SuccessContactError";
		      }
		}
		else if(action.equalsIgnoreCase("contactUs"))
		{
			System.out.println("Inside contactUs");
			String name = request.getParameter("hiddenName");
			String email = request.getParameter("hiddenEmail");
			String phone = request.getParameter("hiddenPhone");
			String subject = request.getParameter("hiddenSubject");
			String comments = request.getParameter("hiddenComments");
			
			System.out.println("Name:"+name+"\nEmail:"+email+"\nPhone:"+phone+"\nSubject:"+subject+"\nComments:"+comments);
			
			 String to = email;
			 /*String from = "support@bzcomposer.com";*/
			 String from = "nextbits.jason@gmail.com";
			 //String from = "ravi.bh.java@gmail.com";						/*commented on 07-10-2019*/

		      Properties properties = System.getProperties();
		      properties.setProperty("mail.smtp.host", "localhost");
		      final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
		      // Get a Properties object
		         Properties props = System.getProperties();
		         props.setProperty("mail.smtp.host", "smtp.gmail.com");
		         props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
		         props.setProperty("mail.smtp.socketFactory.fallback", "false");
		         props.setProperty("mail.smtp.port", "465");
		         props.setProperty("mail.smtp.socketFactory.port", "465");
		         props.put("mail.smtp.auth", "true");
		         props.put("mail.debug", "true");
		         props.put("mail.store.protocol", "pop3");
		         props.put("mail.transport.protocol", "smtp");
		         
		         /*final String username = "ravi.bh.java@gmail.com";		//commented on 07-10-2019
		         final String password = "nxsolteam";*/
		         
		         /*final String username = "support@bzcomposer.com";
		         final String password = "nextbitstbptbcp";*/
		         
		         final String username = "nextbits.jason@gmail.com";
		         final String password = "Jason90621#";
		         
		      //Session session = Session.getDefaultInstance(properties);
		         Session session = Session.getDefaultInstance(props, 
                         new Authenticator(){
                            protected PasswordAuthentication getPasswordAuthentication() {
                               return new PasswordAuthentication(username, password);
                            }});

		      try
		      {
		         MimeMessage message = new MimeMessage(session);
		         message.setFrom(new InternetAddress(from));
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
		         message.setSubject("BzComposer - Welcome!");
		         message.setText("We're glad you're here,"+to+"\nWe have received a subscription request from this email address.\n"
		         		+ "If you still cannot subscribe, please copy this link and paste it in your browser :<a href='www.bzcomposer.com'></a>");
		         Transport.send(message);
		         forward = "SuccessContact";
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		         forward = "SuccessContactError";
		      }
		}
		/*else if(status==false)
		{
			ActionErrors e=new ActionErrors();
			e.add("loginerror", new ActionMessage("err.user.username.wrong"));
			saveErrors(request,e);
			muform.setUserName("");
			muform.setPassword("");
			muform.setCompanyid("");
			
			forward="Failure";
		}*/
		return mapping.findForward(forward);
		}

	/*private boolean isCookieSet(HttpServletRequest request) 
	{
		    Cookie [] cookies = request.getCookies();
		    if ( cookies != null ) 
		    {
		      for (int i = 0; i < cookies.length; i++) 
		      {
		        if ( cookies[i].getName().equals(COOKIE_NAME) && cookies[i].getName().equals(COOKIE_NAME1)) 
		        {
		          return true;
		        }
		      }
		    }
		    return false;
	}*/
	
	private void set(HttpServletRequest request,HttpServletResponse response,LoginForm muform, String username2, String password2, String remember) throws Exception
	{
		String value = request.getParameter("remember");
		/*String username=muform.getUserName().trim();
		String password=muform.getPassword().trim();*/
		String username = username2;
		String password = password2;
		boolean rememberMe = remember.equals("on")?true:false;
		Cipher cipher;  

		cipher = Cipher.getInstance("AES"); //SunJCE provider AES algorithm, mode(optional) and padding schema(optional)
		KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128); // block size is 128bits
        SecretKey secretKey = keyGenerator.generateKey();

		
		byte[] plainTextByte = password.getBytes();
	    cipher.init(Cipher.ENCRYPT_MODE, secretKey);
	    byte[] encryptedByte = cipher.doFinal(plainTextByte);
	    Base64.Encoder encoder = Base64.getEncoder();
	    String encryptedText = encoder.encodeToString(encryptedByte);
	    
	    Base64.Decoder decoder = Base64.getDecoder();
        byte[] encryptedTextByte = decoder.decode(encryptedText);
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] decryptedByte = cipher.doFinal(encryptedTextByte);
        String decryptedText = new String(decryptedByte);
		
		if(value != null && value.equalsIgnoreCase("on"))
		{
			muform.setRememberMe("on");
		    rememberMe = true; 
		}
		if (rememberMe) 
		{           
			//If your checkbox value is true
			Cookie cookieUsername = new Cookie("cookieLoginUser", username);
			Cookie cookiePassword = new Cookie("cookieLoginPassword",encryptedText);
			Cookie cookiePassword1 = new Cookie("cookieLoginPassword1",decryptedText);
			Cookie cookieRememberMe = new Cookie("cookieRememberMe", "on");
			
			// Make the cookie 15 days last
			cookieUsername.setMaxAge(60 * 60 * 24 * 15);
			cookiePassword.setMaxAge(60 * 60 * 24 * 15);
			cookiePassword1.setMaxAge(60 * 60 * 24 * 15);
			cookieRememberMe.setMaxAge(60 * 60 * 24 * 15);
			response.addCookie(cookieUsername);
			response.addCookie(cookiePassword);
			response.addCookie(cookiePassword1);
			response.addCookie(cookieRememberMe);
		}
		else
		{
			muform.setRememberMe("off");
			Cookie cookieRememberMe = new Cookie("cookieRememberMe", "off");
		    cookieRememberMe.setMaxAge(60*60*24*15);
		    response.addCookie(cookieRememberMe);
		}
	}
}