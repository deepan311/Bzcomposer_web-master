package com.avibha.bizcomposer.forgetpassword.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.avibha.bizcomposer.forgetpassword.forms.RecoverForm;
import com.avibha.bizcomposer.forgetpassword.util.MailSender;
import com.avibha.bizcomposer.login.actions.LoginAction;
import com.avibha.bizcomposer.login.forms.MultiUserForm;
import com.avibha.common.db.SQLExecutor;
import com.avibha.common.log.Loger;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/*import net.sf.json.JSONObject;*/ //Commented on 05-05-2020 for solving error

public class ForgetPasswordDAO {

	public boolean isRecoverPassword(RecoverForm reform) {
		Loger.log(LoginAction.class + ".isRecoverPassword()");
		boolean recoverStatus = false;
		Statement stmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		Connection c = null;
		c = db.getConnection();
		String loginid = "";
		String password = "";
		String email_address = "";
		String sql = " SELECT LoginID,Password,PasswordHint,PasswordAns,Email_Address" + " FROM  bca_user" + " WHERE"
				+ " LoginID='" + reform.getUserName() + "'";
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				loginid = rs.getString("LoginID").trim();
				password = rs.getString("Password").trim();
				email_address = rs.getString("Email_Address").trim();
				if (!reform.getUserName().trim().equals(rs.getString("LoginId").trim())) {
					return recoverStatus;
				} else if (!reform.getPasswordHint().trim().equals(rs.getString("PasswordHint").trim())) {
					return recoverStatus;
				} else if (!reform.getPasswordAnswer().trim().equals(rs.getString("PasswordAns").trim())) {
					return recoverStatus;
				}
				recoverStatus = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					db.close(rs);
				}
				if (stmt != null) {
					db.close(stmt);
				}
				if (c != null) {
					db.close(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (recoverStatus) {
			String messageBody = "Hello, <br/>Below is your login info for the BZC";
			messageBody += "<br/><b>Your User Id:</b>&nbsp; " + loginid;
			messageBody += "<br/><b>Password:</b>&nbsp; " + password;
			messageBody += "<br/><br/> Thank you,<br/>Support<br/> BZcomposer";

			/*
			 * com.avibha.bizcomposer.forgetpassword.util.MailSender ms = new
			 * com.avibha.bizcomposer.forgetpassword.util.MailSender( email_address,
			 * "support@bzcomposer.com", "nextbitstbptbcp", getMailServiceProvider(),
			 * "Password Information from BZC", messageBody, "html", true);
			 */

			//MailSender ms1 = new MailSender(email_address, "letsbuyappliances2@gmail.com", "kenny4kenny",
			//		getMailServiceProvider(), "Password Information from BZC", messageBody, "html", true);
			//ms.sendMail(); // Commented on 09-04-2019.
			// ms.sendFromGMail();

			new MailSender().sendMail("Password Information from BZC", email_address, messageBody, "html");

			Loger.log(ForgetPasswordDAO.class + "Successfully Send mail");
		}
		return recoverStatus;
	}

	public static String getMailServiceProvider() {
		// return "mail.nextbits.net";
		return "smtp.gmail.com";
	}

	public boolean isRecoverPassword1(MultiUserForm reform, int otp) {
		Loger.log(LoginAction.class + ".isRecoverPassword1()");
		boolean recoverStatus = false;
		Statement stmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		Connection c = db.getConnection();

		String loginid = "";
		String password = "";
		String email_address = "";
		String phone = "";

		int sentotp = otp;
		String sql = " SELECT LoginID,Password,Phone,Email_Address" + " FROM  bca_user" + " WHERE" + " LoginID='"
				+ reform.getUserName() + "'";
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				loginid = rs.getString("LoginID").trim();
				password = rs.getString("Password").trim();
				phone = rs.getString("phone").trim();
				email_address = rs.getString("Email_Address").trim();
				recoverStatus = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					db.close(rs);
				}
				if (stmt != null) {
					db.close(stmt);
				}
				if (c != null) {
					db.close(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (recoverStatus) {
			String messageBody = "Hi," + "\n" + "Below is verification code form  BZC " + "\n"
					+ "Your Verification code is: " + sentotp + "\n" + "Thank you," + "\n" + " BZcomposer";

			if (email_address !=null && !email_address.contentEquals(""))
			{
				try {
					new MailSender().sendMail("Password Information from BZC", email_address, messageBody, "html");
				}
				catch(Exception e) {
					sendSMS(messageBody, phone);
				}

			}
			else if(phone !=null && !phone.contentEquals(""))
			{
				sendSMS(messageBody, phone);
			}
			else {
				recoverStatus = false;
			}

			Loger.log(ForgetPasswordDAO.class + "Successfully Send mail");
		}
		return recoverStatus;
	}

	//This method is only for testing purpose
	private boolean sendSMS(String message,String mobile) {
		try {
			final String ACCOUNT_SID = "AC2076aeab121ff243d3c4b8566e702466";
			final String AUTH_TOKEN = "7ba4fae964529624acd4198eda5eebbd";
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			System.out.println("Message is sending.....");

			Message.creator(new PhoneNumber(mobile), // to
					new PhoneNumber("+19495183644"), // from
					message).create();

			System.out.println("Messeage Sent successfully");

		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error:" + e.getMessage());
		}
		return true;

	}

	//This method is only for testing purpose
	public static void main (String fdsf[]) {
		try {
			final String ACCOUNT_SID = "AC2076aeab121ff243d3c4b8566e702466";
			final String AUTH_TOKEN = "7ba4fae964529624acd4198eda5eebbd";
			String messageSent = "Hi," + "\n" + "Below is verification code form  BZC " + "\n"
					+ "Your Verification code is: " + 4546544 + "\n" + "Thank you," + "\n" + "BZcomposer";
			Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

			System.out.println("Message is sending.....");

			Message.creator(new PhoneNumber("+1222"), // to
					new PhoneNumber("+19495183644"), // from
					messageSent).create();

			System.out.println("Messeage Sent successfully");

		} catch (Exception e) {
			e.getMessage();
			System.out.println("Error:" + e.getMessage());
		}

	}

	public boolean sendSms(MultiUserForm reform, String number, int otp) {
		boolean status = false;
		Statement stmt = null;
		ResultSet rs = null;
		SQLExecutor db = new SQLExecutor();
		Connection c = db.getConnection();

		String loginid = "";
		String password = "";
		String email_address = "";
		String phone = "";

		int sentotp = otp;
		String sql = " SELECT LoginID,Password,Phone,Email_Address" + " FROM  bca_user" + " WHERE" + " LoginID='"
				+ reform.getUserName() + "'";
		try {
			stmt = c.createStatement();
			rs = stmt.executeQuery(sql);

			if (rs.next()) {
				loginid = rs.getString("LoginID").trim();
				password = rs.getString("Password").trim();
				phone = rs.getString("phone").trim();
				email_address = rs.getString("Email_Address").trim();
				status = true;
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} finally {
			try {
				if (rs != null) {
					db.close(rs);
				}
				if (stmt != null) {
					db.close(stmt);
				}
				if (c != null) {
					db.close(c);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		if (status) {
			try {
				final String ACCOUNT_SID = "AC2076aeab121ff243d3c4b8566e702466";
				final String AUTH_TOKEN = "7ba4fae964529624acd4198eda5eebbd";
				String messageSent = "Hi," + "\n" + "Below is verification code form  BZC " + "\n"
						+ "Your Verification code is: " + otp + "\n" + "Thank you," + "\n" + "BZcomposer";
				Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

				System.out.println("Message is sending.....");

				Message message = Message.creator(new PhoneNumber(number), // to
						new PhoneNumber("+19495183644"), // from
						messageSent).create();

				/*
				 * Message message = Message .creator(new PhoneNumber("+14159352345"), // to new
				 * PhoneNumber("+14158141829"), // from "Where's Wallace?") .create();
				 */

				System.out.println(message.getSid());

				System.out.println("Messeage Sent successfully");

				// System.out.println(message.getSid());
				// return message.getSid();
				return status;
			} catch (Exception e) {
				e.getMessage();
				System.out.println("Error:" + e.getMessage());
				// return "Error"+e;
				return status;
			}
		}
		return status;
	}

	// String apiKey = "apikey=" + " h2O125ZnO+s-D1tHi3SHDb5IgsopwjjxeGtpcvx35x";
	/*
	 * try { // Construct data String apiKey = "apikey=" +
	 * "h2O125ZnO+s-vTSjJlw3jvE7JDRHTkViQCMgxmydj3"; String messageSent =
	 * "&message=" + "Hi,"+"\n"+"Below is verification code form  BZC "+
	 * "\n"+"Your Verification code is: " + otp+ "\n"+"Thank you,"+
	 * "\n"+"BZcomposer"; String message = "&message=" + messageSent; String sender
	 * = "&sender=" + "TXTLCL"; String numbers = "&numbers=" + number;
	 *
	 * // Send data HttpURLConnection con = (HttpURLConnection) new
	 * URL("https://api.textlocal.in/send/").openConnection(); String data = apiKey
	 * + numbers + message + sender; con.setDoOutput(true);
	 * con.setRequestMethod("POST"); con.setRequestProperty("Content-Length",
	 * Integer.toString(data.length()));
	 * con.getOutputStream().write(data.getBytes("UTF-8")); final BufferedReader rd
	 * = new BufferedReader(new InputStreamReader(con.getInputStream())); final
	 * StringBuffer stringBuffer = new StringBuffer(); String line; while ((line =
	 * rd.readLine()) != null) { stringBuffer.append(line); } rd.close();
	 *
	 * return stringBuffer.toString(); } catch (Exception e) {
	 * System.out.println("Error SMS "+e); return "Error "+e; }
	 */

	// HttpURLConnection con = (HttpURLConnection) new
	// URL("https://api.twilio.com/2010-04-01/Accounts/AC2076aeab121ff243d3c4b8566e702466/Messages.json").openConnection();

}
