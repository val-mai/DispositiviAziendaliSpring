package com.epicode.dispositivi.device;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class DeviceService {

	private DeviceRepository repo;
	private ObjectProvider<Device> provider;

	public Page<Device> getAll(Pageable pageable) {
		return repo.findAll(pageable);
	}

	public Optional<Device> getById(Long id) {
		if (repo.findById(id).isPresent()) {
			return repo.findById(id);
		}
		throw new EntityNotFoundException();
	}
	
	public Device insert(DeviceDto dto) {
		Device device = provider.getObject();
		BeanUtils.copyProperties(dto, device);
		return repo.save(device);
	}
	
	public Device update(Long id, DeviceDto dto) {
		Optional<Device> device = repo.findById(id);
		if (!device.isPresent()) {
			throw new EntityNotFoundException("No device found");
		}
		
		BeanUtils.copyProperties(dto, device.get());
		return repo.save(device.get());
	}
	
	public void delete(Long id) {
		repo.deleteById(id);
	}

	
	
}
