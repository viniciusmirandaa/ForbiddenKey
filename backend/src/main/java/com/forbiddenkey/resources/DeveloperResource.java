package com.forbiddenkey.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.forbiddenkey.dto.developer.DeveloperDTO;
import com.forbiddenkey.services.DeveloperService;

@RestController
@RequestMapping(value = "/developers")
public class DeveloperResource {

	@Autowired
	private DeveloperService developerService;

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<DeveloperDTO>> findAll() {
		List<DeveloperDTO> list = developerService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<DeveloperDTO>> findAllPageable(Pageable pageable) {
		Page<DeveloperDTO> list = developerService.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DeveloperDTO> findById(@PathVariable Long id) {
		DeveloperDTO dto = developerService.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<DeveloperDTO> insert(@Valid @RequestBody DeveloperDTO dto) {
		dto = developerService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<DeveloperDTO> update(@Valid @PathVariable Long id, @RequestBody DeveloperDTO dto) {
		dto = developerService.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DeveloperDTO> delete(@PathVariable Long id) {
		developerService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
