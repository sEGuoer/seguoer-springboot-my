package com.seguoer.controller;

import com.seguoer.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class IntroductionController {
    @Autowired
    UserService userService;
    @GetMapping("/firstStarter/{username}")
    @ResponseBody
    String firstStarter(@PathVariable String username){
        return userService.sendInformation(username);
    }
}
