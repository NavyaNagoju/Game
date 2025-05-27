package com.nri_project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class NriPropertyApplication {
	public static void main(String[] args) {
		SpringApplication.run(NriPropertyApplication.class, args);

	}

}























/*package com.nri.main;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
@ComponentScan("com.nri") // make sure this scans all your components
public class NriPropertyApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(NriPropertyApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(NriPropertyApplication.class);
    }
}*/

