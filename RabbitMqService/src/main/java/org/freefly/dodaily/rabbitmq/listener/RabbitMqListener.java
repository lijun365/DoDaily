package org.freefly.dodaily.rabbitmq.listener;

import org.freefly.dodaily.sugarmark.entity.User;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.mail.MailProperties;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.util.Date;

@Component
public class RabbitMqListener {

    @Autowired
    private JavaMailSender javaMailSender;
    @Autowired
    private MailProperties mailProperties;
    @Autowired
    private TemplateEngine templateEngine;

    @RabbitListener(queues = {"mail.queue"})
    public void doMail(User user) {
        System.out.println("In RabbitMqListener: user is: " + user);
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage);
        try {
            helper.setFrom(mailProperties.getUsername());
            helper.setTo(user.getEmail());
            helper.setSubject("Sugarmark marked!");
            Context context = new Context();
            context.setVariable("name", user.getName());
            String process = templateEngine.process("insert_sugarmark", context);
            helper.setText(process, true);
            helper.setSentDate(new Date());
            javaMailSender.send(mimeMessage);
            System.out.println("In RabbitMqListener: email had been sent to " + user.getEmail());
        } catch (Exception e) {
            System.out.println("In RabbitMqListener: Something error when send an email...");
            System.out.println(e);
        }
    }
}
