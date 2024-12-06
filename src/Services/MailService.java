package Services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.mysql.cj.protocol.Message;

public class MailService {
	private final static String APP_MAIL = "dinhvannhan1010@gmail.com";
	private final static String APP_PASS = "waydtfjbgfaaslof";
	
	public static boolean SendLoginMail(String emailTo){
		// Khai bao properties
		Properties props = new Properties();
		
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.port", "587");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		
		//Create Authenticator
		Authenticator auth = new Authenticator() {
			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication(APP_MAIL, APP_PASS);
			}
		};
		
		// Tạo phiên làm việc
		Session session = Session.getInstance(props, auth);
		
		// Gửi mail
		String to = emailTo;
		String title = "Thông báo đăng nhập vào hệ thống BANKING!";
	    String content = 
	    	    "<div style=\"font-family: Arial, sans-serif; text-align: center; padding: 20px;\">"
	    	    + "<h1 style=\"color: #4CAF50;\">Đăng nhập thành công!</h1>"
	    	    + "<p>Chào mừng bạn đã đăng nhập vào hệ thống E-BANKING của chúng tôi.</p>"
	    	    + "<p style=\"color: #555;\">Chúc bạn có trải nghiệm tốt nhất với các dịch vụ của chúng tôi.</p>"
	    	    + "<hr style=\"margin: 20px 0;\"/>"
	    	    + "<p style=\"font-size: 12px; color: #888;\">© 2024 E-BANKING. All rights reserved.</p>"
	    	    + "</div>";


	    MimeMessage msg = new MimeMessage(session);
	    try {
	        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	        msg.setFrom(APP_MAIL);
	        msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(emailTo, false));

	        // Tiêu đề email
	        msg.setSubject(title);

	        // Quy định ngày gửi
	        msg.setSentDate(new Date());

	        // Nội dung (HTML)
	        msg.setContent(content, "text/html; charset=UTF-8");

	        // Gửi email
	        Transport.send(msg);
	        System.out.println("Gửi email thành công");
	        return true;
	    } catch (Exception e) {
	        System.out.println("Gặp lỗi trong quá trình gửi email");
	        e.printStackTrace();
	        return false;
	    }
	}
	public static boolean SendBalanceNotificationMail(String emailTo, long stk, double accountBalance, double previousBalance) {
	    // Khai báo properties
	    Properties props = new Properties();
	    
	    props.put("mail.smtp.host", "smtp.gmail.com");
	    props.put("mail.smtp.port", "587");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    
	    // Tạo Authenticator
	    Authenticator auth = new Authenticator() {
	        @Override
	        protected PasswordAuthentication getPasswordAuthentication() {
	            return new PasswordAuthentication(APP_MAIL, APP_PASS);
	        }
	    };
	    
	    // Tạo phiên làm việc
	    Session session = Session.getInstance(props, auth);
	    
	    // Lấy ngày giờ hiện tại
	    String currentDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new Date());
	    
	    // Tính số tiền bị trừ (chênh lệch giữa số dư hiện tại và số dư trước đó)
	    double amountChanged = accountBalance - previousBalance;
	    
	    // Xác định dấu + hoặc -
	    String amountText = (amountChanged < 0) ? "-" + Math.abs(amountChanged) : "+" + amountChanged;
	    String amountColor = (amountChanged < 0) ? "#e74c3c" : "#27ae60"; // Màu đỏ cho rút tiền và màu xanh cho thêm tiền
	    
	    // Nội dung email
	    String title = "Thông báo biến động số dư tài khoản";
	    String content = "<div style=\"font-family: Arial, sans-serif; text-align: center; padding: 10px;\">"
	        + "<h1 style=\"color: #ff6f61;\">Thông báo biến động số dư</h1>"
	        + "<p style=\"color: #555;\">Tài khoản của bạn vừa có biến động số dư.</p>"
	        + "<table style=\"width: 95%; margin: auto; border-collapse: collapse;\">"
	        + "  <tr style=\"background-color: #f2f2f2;\">"
	        + "    <td style=\"padding: 10px; font-weight: bold;\">Số tài khoản:</td>"
	        + "    <td style=\"padding: 10px;\">" + stk + "</td>"
	        + "  </tr>"
	        + "  <tr style=\"background-color: #ffffff;\">"
	        + "    <td style=\"padding: 10px; font-weight: bold;\">Ngày giờ giao dịch:</td>"
	        + "    <td style=\"padding: 10px;\">" + currentDateTime + "</td>"
	        + "  </tr>"
	        + "  <tr style=\"background-color: #f2f2f2;\">"
	        + "    <td style=\"padding: 10px; font-weight: bold;\">Số tiền biến động:</td>"
	        + "    <td style=\"padding: 10px; color: " + amountColor + ";\">" + amountText + " VND</td>"
	        + "  </tr>"
	        + "  <tr style=\"background-color: #ffffff;\">"
	        + "    <td style=\"padding: 10px; font-weight: bold;\">Số dư hiện tại:</td>"
	        + "    <td style=\"padding: 10px; color: #27ae60;\">" + accountBalance + " VND</td>"
	        + "  </tr>"
	        + "</table>"
	        + "<hr style=\"margin: 20px 0;\"/>"
	        + "<p style=\"font-size: 12px; color: #888;\">© 2024 E-BANKING. All rights reserved.</p>"
	        + "</div>";
	    
	    // Tạo và gửi email
	    MimeMessage msg = new MimeMessage(session);
	    try {
	        msg.addHeader("Content-type", "text/HTML; charset=UTF-8");
	        msg.setFrom(APP_MAIL);
	        msg.setRecipients(javax.mail.Message.RecipientType.TO, InternetAddress.parse(emailTo, false));
	        
	        // Tiêu đề email
	        msg.setSubject(title);
	        
	        // Quy định ngày gửi
	        msg.setSentDate(new Date());
	        
	        // Nội dung email
	        msg.setContent(content, "text/HTML; charset=UTF-8");
	        
	        // Gửi email
	        Transport.send(msg);
	        System.out.println("Gửi email thông báo biến động số dư thành công");
	        return true;
	    } catch (Exception e) {
	        System.out.println("Gặp lỗi trong quá trình gửi email biến động số dư");
	        e.printStackTrace();
	        return false;
	    }
	}

	
	public static void main(String[] args) {
		System.out.println("Mail Sender is running ... !");
//		SendLoginMail("vannhan2409@gmail.com");
	    // Gửi email thông báo biến động số dư
//	    SendBalanceNotificationMail("vannhan2409@gmail.com", "123456", 5000000.0, 150000.0);
	}
}
