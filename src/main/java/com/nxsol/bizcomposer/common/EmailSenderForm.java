package com.nxsol.bizcomposer.common;

import java.io.File;
import java.io.Serializable;

import org.apache.struts.action.ActionForm;
import org.apache.struts.upload.FormFile;
public class EmailSenderForm extends ActionForm {

	private String to;
	private String message;
	private String subject;
	private String cc;
	private String bcc;
	private String from;
	private FormFile attachFile;
	
	public FormFile getAttachFile() {
		return attachFile;
	}
	public void setAttachFile(FormFile attachFile) {
		this.attachFile = attachFile;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getCc() {
		return cc;
	}
	public void setCc(String cc) {
		this.cc = cc;
	}
	public String getBcc() {
		return bcc;
	}
	public void setBcc(String bcc) {
		this.bcc = bcc;
	}
	
}
