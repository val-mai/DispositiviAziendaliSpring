package com.epicode.dispositivi.employee;

import org.springframework.data.repository.PagingAndSortingRepository;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {

	public Employee findByUsername(String username);
}
