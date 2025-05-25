package com.example.demo.Utility;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import jakarta.mail.internet.MimeMessage;

@Component
public class EmailUtility {

    @Autowired
    private JavaMailSender javaMailSender;

    // Generic method to send an email with an attachment
    private boolean sendEmailWithAttachment(String subject, String body, String to, File file, String attachmentName) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true);

            helper.setSubject(subject);
            helper.setText(body, true); // 'true' enables HTML formatting
            helper.setTo(to);
            helper.addAttachment(attachmentName, file);

            javaMailSender.send(mimeMessage);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Sends all pending recharge email
    public boolean sendAllPendingRechargePDFMail(String subject, String body, String to, File file) {
        return sendEmailWithAttachment(subject, body, to, file, "All_Pending_Recharges.pdf");
    }

    // Sends all admission details email
    public boolean sendAllAddmissionPDFMail(String subject, String body, String to, File file) {
        return sendEmailWithAttachment(subject, body, to, file, "All_Admissions.pdf");
    }
}
