package com.easyApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import tk.mybatis.spring.annotation.MapperScan;

@EnableDiscoveryClient
@SpringBootApplication
@MapperScan(basePackages = "com.easyApp.dao")
public class EasyAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyAppApplication.class, args);
	}

}
