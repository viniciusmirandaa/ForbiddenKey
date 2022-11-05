package com.forbiddenkey.resources;

import com.forbiddenkey.dto.*;
import com.forbiddenkey.repositories.RoleRepository;
import com.forbiddenkey.services.AdminService;
import com.forbiddenkey.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.forbiddenkey.services.UserService;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "/admins")
public class AdminResource {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;

	@Autowired
	private RoleRepository roleRepository;

	@GetMapping(value = "/details")
	public ResponseEntity<AdminDTO> findById(){
		var adminDTO = new AdminDTO(adminService.currentAdminLogged());
		return ResponseEntity.ok().body(adminDTO);
	}

	@PostMapping
	public ResponseEntity<AdminDTO> insert(@Valid @RequestBody UserInsertDTO dto) {
		var role = roleRepository.findByRole("ROLE_ADMIN");
		var adminDTO = adminService.insert(userService.insert(dto, role));
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(adminDTO.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/password")
	public ResponseEntity<UserDTO> updatePassword(@Valid @RequestBody UserUpdateDTO dto){
		var entity = userService.update(adminService.currentAdminLogged().getUser().getId(), dto);
		return ResponseEntity.ok().body(entity);
	}
}
