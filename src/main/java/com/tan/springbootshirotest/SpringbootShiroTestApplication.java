package com.tan.springbootshirotest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(value = "com.tan.springbootshirotest.mapper")
@SpringBootApplication
public class SpringbootShiroTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootShiroTestApplication.class, args);
	}

}
