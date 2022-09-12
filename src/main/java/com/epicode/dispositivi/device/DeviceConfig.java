package com.epicode.dispositivi.device;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class DeviceConfig {

	@Bean
	@Scope("prototype")
	public Device newDevice() {
		return new Device(); 
	}
}
