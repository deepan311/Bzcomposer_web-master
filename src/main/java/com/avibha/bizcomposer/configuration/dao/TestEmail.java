/*
 * Author : Avibha IT Solutions Copyright 2007 Avibha IT Solutions. All rights
 * reserved. AVIBHA PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * www.avibha.com
 */
package com.avibha.bizcomposer.configuration.dao;

import java.util.Properties;

import javax.mail.*;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class TestEmail {
	
	/*		The method tests the connecion with mail 
	 * server. If connected then it return true
	 * else return false.
	 */
    public boolean Test(String host, String authType, String userEmail, String password)
    {
    	boolean result = false;
    	Transport transport = null;
    	Properties props = null;
		Session mailSession = null;
    	String mailServerProtocol = "";
        try {
        	InitialContext ic = new InitialContext();
        	mailServerProtocol = (String) ic.lookup("java:comp/env/mailProtocol");
        }
        catch(NamingException nex) {
        	nex.printStackTrace();
        }
        try{
           	if(host==null || host.equals("")) {
				return result;
			}else if(mailServerProtocol==null || mailServerProtocol.equals("")) {
				return result;
			}else {
           		props = new Properties();
        		props.setProperty("mail.smtp.host", host);
        		props.setProperty("mail.transport.protocol", mailServerProtocol);
				props.put("mail.smtp.port", "587");
				if(authType.equals("true")){
					props.put("mail.smtp.auth", "true");
					props.put("mail.smtp.starttls.enable", "true");
					mailSession = Session.getInstance(props,
						new Authenticator() {
							protected PasswordAuthentication getPasswordAuthentication() {
								return new PasswordAuthentication(userEmail, password);
							}
						});
				}else{
					mailSession = Session.getInstance(props);
				}
				transport = mailSession.getTransport();
        		transport.connect();
        		if(transport.isConnected()){
        			result = true;
        			transport.close();
        			props.clear();
        		}
        	}
        }
        catch (MessagingException mex){
        	mex.printStackTrace();
        	result = false;
        }
        return result;
    }
 }
