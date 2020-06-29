package com.exort;

import com.exort.util.GitUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * This class is the main class
 * @author SpringBoot
 * @version 1.0.0
 */
@SpringBootApplication
public class ExortApplication {

	/**
	 * This method is the main method
	 * @param args The application arguments
	 */
	public static void main(String[] args) {
		GitUtil.checkUpdate();
		SpringApplication.run(ExortApplication.class, args);
	}
}
