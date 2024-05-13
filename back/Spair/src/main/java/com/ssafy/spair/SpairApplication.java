package com.ssafy.spair;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.ssafy.spair.model.dao")
public class SpairApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpairApplication.class, args);
	}

}
