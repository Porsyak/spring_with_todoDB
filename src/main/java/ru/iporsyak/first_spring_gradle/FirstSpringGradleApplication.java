package ru.iporsyak.first_spring_gradle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication//(exclude = {DataSourceAutoConfiguration.class})
public class FirstSpringGradleApplication {

    public static void main(String[] args) {
        SpringApplication.run(FirstSpringGradleApplication.class, args);
    }

}
