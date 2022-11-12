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

import com.forbiddenkey.dto.distributor.DistributorDTO;
import com.forbiddenkey.services.DistributorService;

@RestController
@RequestMapping(value = "/distributors")
public class DistributorResource {
	
	@Autowired
	private DistributorService distributorService;

	@GetMapping(value = "/getAll")
	public ResponseEntity<List<DistributorDTO>> findAll() {
		List<DistributorDTO> list = distributorService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping
	public ResponseEntity<Page<DistributorDTO>> findAllPaged(Pageable pageable) {
		Page<DistributorDTO> list = distributorService.findAllPaged(pageable);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<DistributorDTO> findById(@PathVariable Long id) {
		DistributorDTO dto = distributorService.findById(id);
		return ResponseEntity.ok().body(dto);
	}

	@PostMapping
	public ResponseEntity<DistributorDTO> insert(@Valid @RequestBody DistributorDTO dto) {
		dto = distributorService.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<DistributorDTO> update(@Valid @PathVariable Long id, @RequestBody DistributorDTO dto) {
		dto = distributorService.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DistributorDTO> delete(@PathVariable Long id) {
		distributorService.delete(id);
		return ResponseEntity.noContent().build();
	}

}
