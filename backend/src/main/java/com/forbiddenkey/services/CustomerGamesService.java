package com.forbiddenkey.services;

import com.forbiddenkey.dto.customerGames.CustomerGamesDTO;
import com.forbiddenkey.entities.CustomerGames;
import com.forbiddenkey.repositories.CustomerGamesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerGamesService {

    @Autowired
    private CustomerGamesRepository customerGamesRepository;

    @Transactional(readOnly = true)
    public List<CustomerGamesDTO> findAll() {
        List<CustomerGames> list = customerGamesRepository.findAll();
        return list.stream().map(CustomerGamesDTO::new).collect(Collectors.toList());
    }
}
