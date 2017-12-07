package br.com.cleartech.pgmc.mgu.integration;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan( "br.com.cleartech.pgmc.mgu" )
public class MguIntegrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(MguIntegrationApplication.class, args);
    }
}