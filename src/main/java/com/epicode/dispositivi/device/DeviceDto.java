package com.epicode.dispositivi.device;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {

	private String description;
	private EnumDeviceStatus status;
	private EnumDeviceType type;
}
