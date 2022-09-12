package com.epicode.dispositivi.auth.users;

import java.util.HashSet;
import java.util.Set;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.epicode.dispositivi.auth.roles.ERole;
import com.epicode.dispositivi.auth.roles.Role;
import com.epicode.dispositivi.auth.roles.RoleRepository;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@AllArgsConstructor
@Component
@Slf4j
public class UsersRunner implements ApplicationRunner {

	RoleRepository roleRepository;	
	UserRepository userRepository;
	PasswordEncoder encoder;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		log.info("user runner started");
		Role admin = new Role();
		admin.setRoleName( ERole.ROLE_ADMIN  );
		roleRepository.save(admin);
		
		
		Role user = new Role();
		user.setRoleName(ERole.ROLE_USER);
		roleRepository.save(user);
		
		
		Set<Role> ruoliAmministratore = new HashSet<Role>();
		ruoliAmministratore.add(admin);
		
		User userAdmin = new User();
		userAdmin.setUsername("admin");
		userAdmin.setPassword(encoder.encode("admin"));
		userAdmin.setRoles(ruoliAmministratore);
		userRepository.save(userAdmin);
		
		Set<Role> ruoliUtente = new HashSet<Role>();
		ruoliUtente.add(user);
		
		User simpleUser = new User();
		
		simpleUser.setUsername("valmai");
		simpleUser.setPassword(encoder.encode("user"));
		simpleUser.setRoles(ruoliUtente);
		userRepository.save(simpleUser);
		 
	}

	
	
	

	

}
