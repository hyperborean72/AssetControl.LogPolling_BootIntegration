package com.assetcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class PollingApp {
    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(PollingApp.class, args);
    }
}
