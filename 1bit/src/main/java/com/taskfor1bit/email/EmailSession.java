package com.taskfor1bit.email;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * Created by ivanb on 31.03.2016.
 */



public class EmailSession {
    private static Properties mailServerProperties;
    private static Session getMailSession;
    private static MimeMessage generateMailMessage;

    private EmailSession() {
    }

    public static void generateAndSendEmail(String subjectMessege, String emailBody) throws AddressException, MessagingException {
        //генерируем email из титула и ссылки и отправляем на testmywebservice888@gmail.com

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
        props.put("mail.smtp.socketFactory.port", "465"); //SSL Port
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
        props.put("mail.smtp.auth", "true"); //Enabling SMTP Authentication
        props.put("mail.smtp.port", "465"); //SMTP Port


        getMailSession = Session.getDefaultInstance(props, null);
        generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("testmywebservice888@gmail.com"));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("testmywebservice888@gmail.com"));
        generateMailMessage.setSubject(subjectMessege);
        generateMailMessage.setContent(emailBody, "text/html");


        Transport transport = getMailSession.getTransport("smtp");

        transport.connect("smtp.gmail.com", "testmywebservice888@gmail.com", "q1w2e3r4t");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
    }

}
