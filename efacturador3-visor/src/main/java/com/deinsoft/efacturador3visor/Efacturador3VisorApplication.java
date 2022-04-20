package com.deinsoft.efacturador3visor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
@ComponentScan(basePackages = { "com.deinsoft" })
public class Efacturador3VisorApplication //extends SpringBootServletInitializer 
        implements CommandLineRunner{

        @Autowired
	private BCryptPasswordEncoder passwordEncoder;
    
//        @Override
//        protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
//           return application.sources(Efacturador3VisorApplication.class);
//        }
   
	public static void main(String[] args) {
		SpringApplication.run(Efacturador3VisorApplication.class, args);
	}
        @Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
//		uploadFileService.deleteAll();
//		uploadFileService.init();
		
		String password = "123456";
		
		for(int i=0; i<2; i++) {
			String bcryptPassword = passwordEncoder.encode(password);
			System.out.println(bcryptPassword);
		}
	}
}
