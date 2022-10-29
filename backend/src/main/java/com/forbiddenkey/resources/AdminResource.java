package com.forbiddenkey.resources;

import com.forbiddenkey.dto.CustomerDTO;
import com.forbiddenkey.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.forbiddenkey.dto.UserDTO;
import com.forbiddenkey.services.UserService;

//@RestController
//@RequestMapping(value = "/admin")
//public class AdminResource {
//
//	@Autowired
//	private UserService service;
//
//	@Autowired
//	private AdminService adminService;
//
//	@Autowired
//	private CustomerService customerService;
//
//	@GetMapping(value = "/{id}")
//	public ResponseEntity<UserDTO> findById(@PathVariable Long id){
//		var dto = service.findById(id);
//		return ResponseEntity.ok().body(dto);
//	}
//}
