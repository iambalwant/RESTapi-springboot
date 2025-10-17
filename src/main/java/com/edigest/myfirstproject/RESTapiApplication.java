//this is base package of project
package com.edigest.myfirstproject;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

//this annotation only on main class
//this do three work of annotations
//@Configuration @EnableAutoConfiguration @ComponentScan
@SpringBootApplication
public class RESTapiApplication {
	public static void main(String[] args) {
		SpringApplication.run(RESTapiApplication.class, args);
	}

}
