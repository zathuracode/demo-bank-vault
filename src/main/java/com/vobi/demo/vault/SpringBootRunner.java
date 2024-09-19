package com.vobi.demo.vault;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;

/**
 * @author Zathura Code Generator Version 23.10 http://zathuracode.org/
 * @generationDate 2024-09-19T17:02:34.813843
 */
@SpringBootApplication
public class SpringBootRunner {

  public static void main(String[] args) {
    SpringApplication.run(SpringBootRunner.class, args);
  }

  @Bean
  OpenAPI customOpenAPI() {
    return new OpenAPI()
        .info(
            new Info()
                .title("demo-bank-vault")
                .version("0.0.1")
                .description("Spring Boot Project generated by Zathuracode 23.10"));
  }
}
