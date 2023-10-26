package com.seguoer.component;

import org.springframework.stereotype.Component;

@Component
public class CustomComponent {
    public boolean isEven(int random) {
        return random % 2 == 0;
    }
}
