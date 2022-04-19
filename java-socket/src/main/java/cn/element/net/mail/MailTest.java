package cn.element.net.mail;

import java.io.*;
import java.nio.charset.*;
import java.nio.file.*;
import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * This program shows how to use JavaMail to send mail messages.
 */
public class MailTest {
    
    public static void main(String[] args) throws MessagingException, IOException {
        var props = new Properties();
        String path = "java-socket/src/main/resources/mail.properties";
        try (InputStream in = MailTest.class.getClassLoader().getResourceAsStream("mail.properties")) {
            props.load(in);
        }
        
        List<String> lines = Files.readAllLines(Paths.get(path), StandardCharsets.UTF_8);

        String from = lines.get(0);
        String to = lines.get(1);
        String subject = lines.get(2);

        var builder = new StringBuilder();
        for (int i = 3; i < lines.size(); i++) {
            builder.append(lines.get(i));
            builder.append("\n");
        }

        Console console = System.console();
        var password = new String(console.readPassword("Password: "));

        Session mailSession = Session.getDefaultInstance(props);
        // mailSession.setDebug(true);
        var message = new MimeMessage(mailSession);
        message.setFrom(new InternetAddress(from));
        message.addRecipient(RecipientType.TO, new InternetAddress(to));
        message.setSubject(subject);
        message.setText(builder.toString());
        Transport tr = mailSession.getTransport();
        
        try {
            tr.connect(null, password);
            tr.sendMessage(message, message.getAllRecipients());
        } finally {
            tr.close();
        }
    }
}
