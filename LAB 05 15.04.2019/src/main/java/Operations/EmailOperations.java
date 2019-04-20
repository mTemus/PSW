package Operations;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailOperations {

    private String usernameEmail;
    private String passwordEmail;

    public EmailOperations() {
        usernameEmail = "utptest69@gmail.com";
        passwordEmail = "ZP()PS\\/\\/Utp@";
    }

    public EmailOperations(String usernameEmail, String passwordEmail) {
        this.usernameEmail = usernameEmail;
        this.passwordEmail = passwordEmail;
    }

    public void sendEmailAfterRegistering(String userEmail, String userName, String userSurname) {

        final String username = usernameEmail;
        final String password = passwordEmail;

        Properties props = new Properties();
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });
        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("TemusOrigami@gmail.com"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(userEmail));
            message.setSubject("Registration Complete.");
            message.setText("Dear " + userName + " " + userSurname + ","
                    + "\n\n Registration wet successfully!");

            Transport.send(message);

            System.out.println("Message sent.");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }

}
