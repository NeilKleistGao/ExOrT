package com.exort;

import com.exort.util.GitUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class ExortApplication {

	public static void main(String[] args) {
		GitUtil.checkUpdate();
		SpringApplication.run(ExortApplication.class, args);
	}
}
