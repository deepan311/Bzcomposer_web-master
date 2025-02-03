package com.nxsol.bizcomposer.common;

public class EmailSenderDto {

    private static final long serialVersionUID = 0;
    private String from;
    private String to;
    private String cc;
    private String bcc;
    private String subject;
    private String message;

    private String mailServer;
    private String mailUsername;
    private String mailPassword;
    private String mailSenderEmail;

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
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public String getMailServer() { return mailServer; }
    public void setMailServer(String mailServer) { this.mailServer = mailServer; }

    public String getMailUsername() { return mailUsername; }
    public void setMailUsername(String mailUsername) { this.mailUsername = mailUsername; }

    public String getMailPassword() { return mailPassword; }
    public void setMailPassword(String mailPassword) { this.mailPassword = mailPassword; }

    public String getMailSenderEmail() { return mailSenderEmail; }
    public void setMailSenderEmail(String mailSenderEmail) { this.mailSenderEmail = mailSenderEmail; }
}
