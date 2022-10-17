package com.scaler.taskmgrspring;

import com.scaler.taskmgrspring.beans.HelloBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class TaskmgrSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskmgrSpringApplication.class, args);
    }

    @Bean
    HelloBean helloBean() {
        return new HelloBean();
    }

}
