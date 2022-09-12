package com.epicode.dispositivi.employee;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {

	private String username;
	private String firstName;
	private String lastName;
	private String email;
}
