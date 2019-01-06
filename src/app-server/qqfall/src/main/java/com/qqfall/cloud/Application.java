
package com.qqfall.cloud;

import java.util.concurrent.Executor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@SpringBootApplication(scanBasePackages = ("com.qqfall"))
@EnableAsync
public class Application implements AsyncConfigurer {

  private final static Logger logger = LoggerFactory.getLogger(Application.class);

  public static void main(String[] args) {
    logger.info("begin to run");
    SpringApplication.run(Application.class, args);
    logger.info("sucess to run");
  }

  @Override
  public Executor getAsyncExecutor() {
    ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
    taskExecutor.setMaxPoolSize(10);
    taskExecutor.setThreadNamePrefix("LULExecutor-");
    taskExecutor.initialize();
    return taskExecutor;
  }

  @Override
  public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
    return null;
  }

//	@Bean
//	public EmbeddedServletContainerFactory serveletContainer(){
//		TomcatEmbeddedServletContainerFactory factory = new TomcatEmbeddedServletContainerFactory();
//		factory.setPort(9981);
//		return factory;
//    }

}
