/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.deinsoft.efacturador3commons.util;

import com.deinsoft.efacturador3commons.bean.MailBean;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.slf4j.LoggerFactory;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author EDWARD-PC
 */
public class SendMail {

    private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SendMail.class);

    public static boolean validaCorreo(String correo) {
        boolean result = false;
        if (correo.equals("")) {
            result = true;
            return result;
        }
        Pattern pattern = Pattern
                .compile("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
        Matcher mather = pattern.matcher(correo);
        result = mather.find();
        return result;
    }

    public static boolean sendEmail(MailBean mail) {

        boolean result = false;
        Session mailSession = null;
        try {
            Properties props = new Properties();
            props.put("mail.smtp.host", "mail.privateemail.com");
            props.put("mail.smtp.socketFactory.port", "465");
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "465");
            final String username = mail.getCorreoElectronicoFrom();
            final String password = mail.getCorreoElectronicoFromPass();
            mailSession = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
            MimeMessage m = new MimeMessage(mailSession);
            InternetAddress from = new InternetAddress(mail.getCorreoElectronicoFrom());
            InternetAddress[] to = new InternetAddress[]{new InternetAddress(mail.getCorreoElectronicoTo())};
            m.setFrom(from);
            m.setRecipients(Message.RecipientType.TO, to);
            m.setSubject(mail.getAsunto());
            m.setSentDate(new java.util.Date());
//            m.setContent(mail.getContenido(), "text/html");

            Multipart multipart = new MimeMultipart();

            MimeBodyPart attachmentPart = null;

            MimeBodyPart textPart = null;

            try {
                
                textPart = new MimeBodyPart();
                textPart.setText(mail.getContenido());
                multipart.addBodyPart(textPart);
                for (String object : mail.getAdjuntos()) {
                    attachmentPart = new MimeBodyPart();
                    File f = new File(object);
                    attachmentPart.attachFile(f);
                    multipart.addBodyPart(attachmentPart);

                }

            } catch (IOException e) {

                e.printStackTrace();

            }
            m.setContent(multipart);
            Transport.send(m);
            result = true;
            LOG.debug("sendEmail/".concat(mail.toString()).concat("El correo se envio correctamente"));
        } catch (NullPointerException e) {
            LOG.debug("sendEmail/".concat(mail.toString()), e);
            return result;
        } catch (javax.mail.MessagingException e) {
            LOG.debug("sendEmail/".concat(mail.toString()), e);
            return result;
        } catch (Exception e) {
            LOG.debug("sendEmail/".concat(mail.toString()), e);
            return result;
        }
        return result;
    }
}
