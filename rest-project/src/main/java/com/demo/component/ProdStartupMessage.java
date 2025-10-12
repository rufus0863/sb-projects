package com.demo.component;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("prod")
public class ProdStartupMessage {
    public ProdStartupMessage() {
        System.out.println(">>> Running in PROD mode with file-based H2 database <<<");
    }
}
