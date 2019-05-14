package br.com.senaigo.fatesg;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.config.EnableHypermediaSupport;
import org.springframework.hateoas.config.EnableHypermediaSupport.HypermediaType;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@EnableWebMvc
@SpringBootApplication
@EnableHypermediaSupport(type = {HypermediaType.HAL})
public class RegistroPontoApplication {

	public static void main(String[] args) {
		SpringApplication.run(RegistroPontoApplication.class, args);
	}

}
