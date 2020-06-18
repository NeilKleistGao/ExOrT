package com.exort;

import com.exort.dao.CharacterDAO;
import com.exort.dao.CharacterDAOImpl;
import com.exort.entity.Character;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

@SpringBootApplication
public class ExortApplication {

	public static void main(String[] args) {
		SpringApplication.run(ExortApplication.class, args);
	}
}
