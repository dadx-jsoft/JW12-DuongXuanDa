package com.devpro.shopdoda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Autowired
    private JavaMailSender javaMailSender;
	
	public void sendEmail(String...mails) {

        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(mails);

        msg.setSubject("Đặt hàng thành công");
        msg.setText("Chúc mừng bạn đã đặt hàng thành công");

        javaMailSender.send(msg);

    }
}
