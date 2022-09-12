package com.epicode.dispositivi.employee;

import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class EmployeeService {

	private EmployeeRepository repo;
	private ObjectProvider<Employee> provider;
	
	public Page<Employee> getAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	public Optional<Employee> getById(Long id) {
		if (!repo.findById(id).isPresent()) {
			throw new EntityNotFoundException("No employee found");
		}
		return repo.findById(id);
	}
	
	public Employee getByUsername(String name) {
		if(!repo.existsByUsername(name)) {
			throw new EntityNotFoundException("No employee with username " + name + " found");
		}
		return repo.findByUsername(name);
	}
	
	public Employee insert(EmployeeDto dto) {
		if(repo.existsByUsername(dto.getUsername())) {
			throw new EntityExistsException("Employee with username " + dto.getUsername() + " already in DB");
		} else if (repo.existsByEmail(dto.getEmail())){
			throw new EntityExistsException("Employee with email " + dto.getEmail() + " already in DB");
		}
		Employee employee = provider.getObject();
		BeanUtils.copyProperties(dto, employee);
		return repo.save(employee);
	}
	
	public Employee update(Long id, EmployeeDto dto) {
		Optional<Employee> device = repo.findById(id);
		if (!device.isPresent()) {
			throw new EntityNotFoundException("No employye found");
		}
		BeanUtils.copyProperties(dto, device.get());
		return repo.save(device.get());
	}
	
	public void delete(Long id) {
		if (!repo.findById(id).isPresent()) {
			throw new EntityNotFoundException("No employee found");
		}
		repo.deleteById(id);
	}
}
