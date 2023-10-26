package com.seguoer.health;

import com.seguoer.component.CustomComponent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.util.Random;
@Component
public class CustomHealthIndicator extends AbstractHealthIndicator {
    @Autowired
    CustomComponent customComponent;
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        if (customComponent.isEven(new Random().nextInt())) {
            builder.up().withDetail("message", "is even").build();
        } else {
            builder.down().withDetail("message", "is odd").build();
        }
    }
}
