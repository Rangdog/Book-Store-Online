package poly.bean;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import net.bytebuddy.utility.RandomString;
import poly.dao.DAOInterface.KhachHangInterface;
import poly.entity.KhachHang;

@Service("mailer")
public class Mailer {
	@Autowired
	private KhachHangInterface DAOKhachHang;
	@Autowired
	private JavaMailSender mailer;

	public String getSiteURL(HttpServletRequest request) {
		String siteURL = request.getRequestURL().toString();
		return siteURL.replace(request.getServletPath(), "");
	}
	
	public void register(KhachHang khachHang, String siteURL) throws UnsupportedEncodingException, MessagingException {
		Calendar verificationTime = Calendar.getInstance();
		verificationTime.add(Calendar.DAY_OF_MONTH, 1);

		khachHang.setVerificationCode(RandomString.make(64));
		khachHang.setVerificationTime(verificationTime.getTime());
		DAOKhachHang.insertKhachHang(khachHang);

		sendVerificationEmail(khachHang, siteURL);
	}

	public void sendVerificationEmail(KhachHang khachHang, String siteURL)
			throws MessagingException, UnsupportedEncodingException {
		String content = "Dear our Customer,<br>" + "Please click the link below to verify your email-registration:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">VERIFY</a></h3>" + "Thank you,<br>" + "3T-BOOKSTORE.";
		String verifyURL = siteURL + "/verify.htm?code=" + khachHang.getVerificationCode();
		content = content.replace("[[URL]]", verifyURL);

		MimeMessage mail = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
		helper.setFrom("phuduthang300502@gmail.com", "3T-CCS");
		helper.setTo(khachHang.getEmail());
		helper.setSubject("Please verify your email-registeration");
		helper.setText(content, true);

		mailer.send(mail);
	}
	
	public void sendConfirmationPassword(String email, String siteURL) throws MessagingException, UnsupportedEncodingException {
		Calendar limitTime = Calendar.getInstance();
		limitTime.add(Calendar.DAY_OF_MONTH, 1);
		
		String content = "Dear our Customer,<br>" + "Please click the link below to renew your password:<br>"
				+ "<h3><a href=\"[[URL]]\" target=\"_self\">COFIRM</a></h3>" + "Thank you,<br>" + "3T-BOOKSTORE.";
		String verifyURL = siteURL + String.format("/confirm.htm?email=%s&limit=%s", email, 
				new SimpleDateFormat("dd/MM/yyyy").format(limitTime.getTime()));
		content = content.replace("[[URL]]", verifyURL);

		MimeMessage mail = mailer.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true, "UTF-8");
		helper.setFrom("phuduthang300502@gmail.com", "3T-CCS");
		helper.setTo(email);
		helper.setSubject("Please confirm your password-confirmation");
		helper.setText(content, true);

		mailer.send(mail);
	}

	public boolean verify(String verificationCode) {
		KhachHang khachHang = DAOKhachHang.findByVerificationCode(verificationCode);
		if (khachHang == null || khachHang.getTrangThaiEmail()
				|| khachHang.getVerificationTime().before(Calendar.getInstance().getTime())) return false;
		return DAOKhachHang.confirmVerification(khachHang.getMaKhachHang());
	}
	
	public void renewVerificationEmail(int maKhachHang) {
		Calendar verificationTime = Calendar.getInstance();
		verificationTime.add(Calendar.DAY_OF_MONTH, 1);
		
		DAOKhachHang.renewVerificationEmail(maKhachHang, RandomString.make(64), verificationTime.getTime());
	}
}
