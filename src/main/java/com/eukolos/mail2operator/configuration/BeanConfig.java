package com.eukolos.mail2operator.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class BeanConfig {

  /*  @Bean
    public JavaMailSender getJavaMailSender(){
        return new JavaMailSenderImpl();
    }*/
}
