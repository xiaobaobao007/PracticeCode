package per.bmy.Tools;

/**
 * @author xiaobaobao
 * @date 2019/8/23 9:29
 */

import com.sun.mail.util.MailSSLSocketFactory;
import java.io.UnsupportedEncodingException;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import javax.mail.*;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {

    public static final String FROM = "";
    public static final String[] email = {""};
    public static final InternetAddress[] TO = new InternetAddress[email.length];
    public static final String CODE = "";

    static {
        try {
            for (int i = email.length - 1; i >= 0; i--) {
                TO[i] = new InternetAddress(email[i]);
            }
        } catch (AddressException e) {
            e.printStackTrace();
        }
    }

    public static int THREAD_NUM = 1;
    public static final ExecutorService pool = Executors.newFixedThreadPool(THREAD_NUM);
    public static final Semaphore semaphore = new Semaphore(THREAD_NUM);
    public static final CountDownLatch down = new CountDownLatch(THREAD_NUM);

    public static void main(String[] args) throws Exception {
        Properties properties = new Properties();
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.transport.protocol", "smtp");
        // properties.setProperty("mail.debug", "true");
        // properties.setProperty("mail.smtp.host", "smtp.sina.com");
        properties.setProperty("mail.smtp.host", "smtp.qq.com");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.port", "465");
        properties.setProperty("mail.smtp.ssl.enable", "smtp");
        properties.put("Mail.smtp.ssl.socketFactory", new MailSSLSocketFactory());

        int times = THREAD_NUM;
        while (--times >= 0) {
            pool.execute(() -> {
                try {
                    sendEmail(properties);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    semaphore.release();
                    down.countDown();
                }
            });
        }

        down.await();
        pool.shutdown();
    }

    public static void sendEmail(Properties properties) throws Exception {
        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(FROM, CODE);
            }
        });

        Message message = new MimeMessage(session);
        String nick = "";
        try {
            nick = javax.mail.internet.MimeUtility.encodeText("燃烧内网服务器");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        message.setFrom(new InternetAddress(nick + " <" + FROM + ">"));
        message.addRecipients(MimeMessage.RecipientType.TO, TO);
        message.setSubject((Thread.currentThread().getId() % 100) + "服,出错警告");

        try {
            doSS();
        } catch (Exception e) {
            message.setText(me(e));
        }
        semaphore.acquire();
        long start = System.currentTimeMillis();
        Transport.send(message);
        System.out.println(System.currentTimeMillis() - start);
        System.out.println(Thread.currentThread().getId() + " is over");
    }

    public static void doSS() {
        doSS1();
    }

    public static void doSS1() {
        System.out.println(1 / 0);
    }

    public static String me(Exception e) {
        StringBuilder s = new StringBuilder(per.bmy.util.DateUtil.formatDate()).append("\n").append(e.toString());
        for (StackTraceElement element : e.getStackTrace()) {
            s.append("\n");
            s.append(element);
        }
        return s.append("\n\n").toString();
    }

}
