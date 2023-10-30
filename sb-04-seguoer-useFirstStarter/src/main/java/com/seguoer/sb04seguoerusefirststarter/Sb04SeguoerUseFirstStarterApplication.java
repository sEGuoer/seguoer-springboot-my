package com.seguoer.sb04seguoerusefirststarter;

import com.seguoer.config.FirstAutoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(FirstAutoConfig.class)
public class Sb04SeguoerUseFirstStarterApplication {

    public static void main(String[] args) {
        SpringApplication.run(Sb04SeguoerUseFirstStarterApplication.class, args);
    }

}
