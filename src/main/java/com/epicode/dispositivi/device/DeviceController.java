package com.epicode.dispositivi.device;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping("device")
public class DeviceController {

	@Autowired
	private DeviceService service;
	
	@GetMapping
	@PreAuthorize("hasRole('USER')")
	@Operation(security = @SecurityRequirement(name = "bearer-authentication"))
	public ResponseEntity<Page<Device>> getAll(Pageable pageable){
		return ResponseEntity.ok(service.getAll(pageable));
	}
	
	@GetMapping("/{id}")
	@PreAuthorize("hasRole('USER')")
	@Operation(security = @SecurityRequirement(name = "bearer-authentication"))
	public ResponseEntity<Optional<Device>> getById(@PathVariable Long id) {
		return ResponseEntity.ok(service.getById(id));
	}
	
	@PostMapping
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(security = @SecurityRequirement(name = "bearer-authentication"))
	public ResponseEntity<Device> insert(@RequestBody DeviceDto dto){
		return ResponseEntity.ok(service.insert(dto));
	}
	
	@PutMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(security = @SecurityRequirement(name = "bearer-authentication"))
	public ResponseEntity<Device> update(@PathVariable Long id,@RequestBody DeviceDto dto){
		return ResponseEntity.ok(service.update(id, dto));
	}
	
	@DeleteMapping("/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	@Operation(security = @SecurityRequirement(name = "bearer-authentication"))
	public ResponseEntity<String> delete(@PathVariable Long id) {
		service.delete(id);
		return ResponseEntity.ok("Device deleted");
	}
}
