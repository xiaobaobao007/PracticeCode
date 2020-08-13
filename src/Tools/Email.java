package Tools;

/**
 * @author xiaobaobao
 * @date 2019/8/23 9:29
 */

import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

	public static final String FROM = "";
	public static final String TO = "";
	public static final String CODE = "";

	public static void main(String[] args) throws Exception {
		long start = System.currentTimeMillis();

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.auth", "true");
		properties.setProperty("mail.transport.protocol", "smtp");
		properties.setProperty("mail.smtp.host", "smtp.sina.com");
		// properties.setProperty("mail.debug", "true");

		int times = 1;
		while (times-- > 0) {
			sendEmail(properties);
			System.out.println("剩余：" + times);
			// TimeUnit.SECONDS.sleep(5);
		}

		System.out.printf("耗时%.3fS", (System.currentTimeMillis() - start) / 1000.0);
	}

	public static void sendEmail(Properties properties) throws Exception {
		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(FROM, CODE);
			}
		});

		Message message = new MimeMessage(session);
		message.setFrom(new InternetAddress(FROM));
		message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(TO));
		message.setSubject("998服出错");

		try {
			doSS();
		} catch (Exception e) {
			message.setText(me(e));
		}
		Transport.send(message);
	}

	public static void doSS() {
		doSS1();
	}

	public static void doSS1() {
		System.out.println(1 / 0);
	}

	public static String me(Exception e) {
		StringBuilder s = new StringBuilder(e.toString());
		for (StackTraceElement element : e.getStackTrace()) {
			s.append("\n");
			s.append(element);
		}
		return s.append("\n\n").toString();
	}

}
