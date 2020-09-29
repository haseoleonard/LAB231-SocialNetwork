/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nghiadh.utils;

import java.io.UnsupportedEncodingException;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author haseo
 */
public final class MailHelpers {

    private static String MAIL_USER = "";
    private static String MAIL_PASSWORD = "";
    private static final int SMTP_PORT = 465;
    private static final String HOST = "smtp.gmail.com";
    private static final boolean SSL_ENABLE = true;
    private static final boolean SMTP_AUTH = true;
    private static final boolean DEBUG = false;

    public static void setMAIL_USER(String MAIL_USER) {
        MailHelpers.MAIL_USER = MAIL_USER;
    }

    public static void setMAIL_PASSWORD(String MAIL_PASSWORD) {
        MailHelpers.MAIL_PASSWORD = MAIL_PASSWORD;
    }
    //
    public static void sendVerificationEmail(String receiverEmail, String verificationCode, Timestamp timeOutDate) throws MessagingException, UnsupportedEncodingException {
//        if(MAIL_USER.trim().isEmpty()||MAIL_PASSWORD.trim().isEmpty())return;
        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", HOST);
        properties.setProperty("mail.smtp.port", String.valueOf(SMTP_PORT));
        properties.setProperty("mail.smtp.ssl.enable", String.valueOf(SSL_ENABLE));
        properties.setProperty("mail.smtp.auth", String.valueOf(SMTP_AUTH));
        //
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(MAIL_USER, MAIL_PASSWORD);
            }
        });

        session.setDebug(DEBUG);

        MimeMessage message = new MimeMessage(session);

        message.setFrom(new InternetAddress(MAIL_USER, "DHN Social Network"));
        message.setRecipient(Message.RecipientType.TO, new InternetAddress(receiverEmail));
        message.setSubject("Social Network Account Verification Code[LAB231]");
        message.setContent("<h1 style='style=color:Blue'>Congratulations</h1>"
                + "<br/>You have successfully create a new account<br/>"
                + "Your verification code is " + verificationCode + "<br/>"
                + "The code provided will expired at " + DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.DEFAULT).format(timeOutDate) + "<br/>"
                + "Please Enter your code to complete the registration process.<br/>"
                + "If it's not you who Sign-up on my website please ignore this message", "text/html");
        Transport.send(message);
    }
}
