
package com.qqfall.cloud;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages=("com.qqfall"))
public class Application {

	private final static Logger logger = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {
		logger.info("begin to run");
		SpringApplication.run(Application.class, args);
		logger.info("sucess to run");
	}
	
//	@Bean
//	public EmbeddedServletContainerFactory serveletContainer(){
//		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//		factory.setPort(9981);
//		return factory;
//    }

}
