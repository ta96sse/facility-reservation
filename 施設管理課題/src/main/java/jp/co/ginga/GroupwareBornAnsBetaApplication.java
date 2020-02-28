package jp.co.ginga;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;

import jp.co.ginga.application.handler.GrobalExceptionHandler;

@Configuration
@SpringBootApplication
@ComponentScan
public class GroupwareBornAnsBetaApplication {

	public static void main(String[] args) {
		SpringApplication.run(GroupwareBornAnsBetaApplication.class, args);
	}

	@Bean
	public MessageSource messageSource() {
		ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
		messageSource.setBasename("message");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public GrobalExceptionHandler exceptionHandler() {
		return new GrobalExceptionHandler();
	}

}
