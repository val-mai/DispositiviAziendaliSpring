package com.epicode.dispositivi.employee;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	public Employee findByUsername(String username);
	
	public boolean existsByUsername(String username);
	
	public Employee findByEmail(String email);
	
	public boolean existsByEmail(String email);
	
}
