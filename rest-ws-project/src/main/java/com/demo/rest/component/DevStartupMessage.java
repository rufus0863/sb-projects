package com.demo.rest.component;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("dev")
public class DevStartupMessage {
    public DevStartupMessage() {
        System.out.println(">>> Running in DEV mode with in-memory H2 database <<<");
    }
}
