package com.seguoer.config;

import com.seguoer.properties.MessageProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableConfigurationProperties(MessageProperties.class)
public class FirstAutoConfig {
}
