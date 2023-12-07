package fr.lde.apitest;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import springfox.documentation.swagger2.annotations.EnableSwagger2;

// @EnableSwagger2
@SpringBootApplication
public class ApitestApplication
		implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(ApitestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("App is running... Does it work?");
	}
}
