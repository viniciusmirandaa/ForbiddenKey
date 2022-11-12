package com.forbiddenkey.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.forbiddenkey.dto.developer.DeveloperDTO;
import com.forbiddenkey.entities.Developer;
import com.forbiddenkey.repositories.DeveloperRepository;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@Service
public class DeveloperService {

    @Autowired
    private DeveloperRepository developerRepository;

    @Transactional(readOnly = true)
    public List<DeveloperDTO> findAll() {
        List<Developer> list = developerRepository.findAll();
        return list.stream().map(Developer -> new DeveloperDTO(Developer, Developer.getDeveloperProduct())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<DeveloperDTO> findAllPaged(Pageable pageable) {
        Page<Developer> page = developerRepository.findAll(pageable);
        return page.map(Developer -> new DeveloperDTO(Developer, Developer.getDeveloperProduct()));
    }

    @Transactional(readOnly = true)
    public DeveloperDTO findById(Long id) {
        Optional<Developer> obj = developerRepository.findById(id);
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
        return new DeveloperDTO(entity, entity.getDeveloperProduct());
    }

    @Transactional
    public DeveloperDTO insert(DeveloperDTO developer) {
        var entity = new Developer();
        entity.setName(developer.getName());
        entity = developerRepository.save(entity);
        return new DeveloperDTO(entity);
    }

    @Transactional
    public DeveloperDTO update(Long id, DeveloperDTO developer) {
        try {
            Optional<Developer> obj = developerRepository.findById(id);
            var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
            entity.setName(developer.getName());
            entity = developerRepository.save(entity);
            return new DeveloperDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        }
    }

    public void delete(Long id) {
        try {
            developerRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Id {" + id + "} violates integrity.");
        }
    }
}