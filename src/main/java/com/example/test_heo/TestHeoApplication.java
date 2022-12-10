package com.example.test_heo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

@SpringBootApplication
public class TestHeoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestHeoApplication.class, args);
    }

}




