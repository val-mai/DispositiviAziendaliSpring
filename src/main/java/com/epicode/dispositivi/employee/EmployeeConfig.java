package com.epicode.dispositivi.employee;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class EmployeeConfig {

	@Bean
	@Scope("prototype")
	public Employee newEmployee() {
		return new Employee();
	}
}
