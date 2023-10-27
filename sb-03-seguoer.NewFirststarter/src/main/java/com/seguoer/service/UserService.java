package com.seguoer.service;

import com.seguoer.properties.MessageProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    MessageProperties messageProperties;
    public String sendInformation(String userName) {
        return messageProperties.getPrefix() + "," + userName + "," + messageProperties.getSuffix();
    }

}
