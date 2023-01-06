package br.com.lucasdev3.financesystemapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class FinanceSystemApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(FinanceSystemApiApplication.class, args);

        System.out.println(new BCryptPasswordEncoder().encode("123456"));

    }

}
