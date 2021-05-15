package com.eoptech.shopdoda.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.eoptech.shopdoda.entities.Saleorder;

@Service
public class MailService {
	@Autowired
	private JavaMailSender javaMailSender;

	public void sendMailAfterOrderSuccess(String email, Saleorder saleorder) {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(email);
		
		msg.setSubject("Đặt hàng thành công");
		msg.setText(
				"Chúc mừng bạn đã đặt hàng thành công, "
				+ "chúng tôi sẽ giao hàng trong vòng 7 ngày kể từ khi gửi mail này.");

		javaMailSender.send(msg);
	}

	public void sendEmailResetPass(String emailResetPass, String newPass) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(emailResetPass);

		msg.setSubject("Reset password");
		msg.setText("Mật khẩu tạm cho email " + emailResetPass + " là " + newPass
				+ "\n\nVui lòng đổi mật khẩu của mình để đảm bảo an toàn");

		javaMailSender.send(msg);

	}

	public void sendEmail(String... mails) {

		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setTo(mails);

		msg.setSubject("Đặt hàng thành công");
		msg.setText("Chúc mừng bạn đã đặt hàng thành công");

		javaMailSender.send(msg);

	}
}
