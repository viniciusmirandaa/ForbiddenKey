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

import com.forbiddenkey.dto.distributor.DistributorDTO;
import com.forbiddenkey.entities.Distributor;
import com.forbiddenkey.repositories.DistributorRepository;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;

@Service
public class DistributorService {

    @Autowired
    private DistributorRepository distributorRepository;

    @Transactional(readOnly = true)
    public List<DistributorDTO> findAll() {
        List<Distributor> list = distributorRepository.findAll();
        return list.stream().map(distributor -> new DistributorDTO(distributor, distributor.getDistributorProducts())).collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<DistributorDTO> findAllPaged(Pageable pageable) {
        Page<Distributor> page = distributorRepository.findAll(pageable);
        return page.map(distributor -> new DistributorDTO(distributor, distributor.getDistributorProducts()));
    }

    @Transactional(readOnly = true)
    public DistributorDTO findById(Long id) {
        Optional<Distributor> obj = distributorRepository.findById(id);
        var entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
        return new DistributorDTO(entity, entity.getDistributorProducts());
    }

    @Transactional
    public DistributorDTO insert(DistributorDTO distributor) {
        var entity = new Distributor();
        entity.setName(distributor.getName());
        entity = distributorRepository.save(entity);
        return new DistributorDTO(entity);
    }

    @Transactional
    public DistributorDTO update(Long id, DistributorDTO distributor) {
        try {
            Optional<Distributor> obj = distributorRepository.findById(id);
            Distributor entity = obj.orElseThrow(() -> new ResourceNotFoundException("Id {" + id + "} not found."));
            entity.setName(distributor.getName());
            entity = distributorRepository.save(entity);
            return new DistributorDTO(entity);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        }
    }

    public void delete(Long id) {
        try {
            distributorRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Id {" + id + "} violates integrity.");
        }
    }
}
