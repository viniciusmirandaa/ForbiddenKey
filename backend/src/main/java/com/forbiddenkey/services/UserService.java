package com.forbiddenkey.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import com.forbiddenkey.entities.Role;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forbiddenkey.dto.RoleDTO;
import com.forbiddenkey.dto.UserDTO;
import com.forbiddenkey.dto.UserInsertDTO;
import com.forbiddenkey.entities.User;
import com.forbiddenkey.repositories.RoleRepository;
import com.forbiddenkey.repositories.UserRepository;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {
	
	private static Logger logger = LoggerFactory.getLogger(UserService.class);

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Transactional(readOnly = true)
	public Page<UserDTO> findAll(Pageable pageable) {
		Page<User> page = userRepository.findAll(pageable);
		return page.map(user -> new UserDTO(user));
	}

	@Transactional(readOnly = true)
	public UserDTO findById(Long id) {
		Optional<User> obj = userRepository.findById(id);
		var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
		return new UserDTO(entity);
	}

	@Transactional
	public UserDTO update(Long id, UserDTO user) {
		try {
			var entity = userRepository.getReferenceById(id);
			entity = userRepository.save(entity);
			return new UserDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id {" + id + "} not found.");
		}
	}

	@Transactional
	public User insert(UserInsertDTO user, Role role) {
		var entity = new User();
		copyDtoToEntity(user, role, entity);
		entity.setPassword(passwordEncoder.encode(user.getPassword()));
		entity = userRepository.save(entity);
		return entity;
	}

	public void delete(Long id) {
		try {
			userRepository.deleteById(id);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("Id {" + id + "} not found.");
		} catch (DataIntegrityViolationException e) {
			throw new DatabaseException("Database integrity violation.");
		}
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		var user = userRepository.findByEmail(username);
		if (user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException("Email not found");
		}
		logger.info("User found: " + username);
		return user;
	}

	public void copyDtoToEntity(UserDTO dto, Role role, User entity){
		entity.getRoles().add(role);
		entity.setEmail(dto.getEmail());
	}
}
