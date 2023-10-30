package com.seguoer.config;


import com.seguoer.controller.IntroductionController;
import com.seguoer.properties.MessageProperties;
import com.seguoer.service.UserService;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties(MessageProperties.class)
@Import({IntroductionController.class,UserService.class})
public class FirstAutoConfig {

}
