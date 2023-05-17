package com.forbiddenkey.services;

import com.forbiddenkey.dto.card.CardDTO;
import com.forbiddenkey.entities.Card;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.repositories.BannerRepository;
import com.forbiddenkey.repositories.CardRepository;
import com.forbiddenkey.repositories.CustomerRepository;
import com.forbiddenkey.services.exceptions.DatabaseException;
import com.forbiddenkey.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public List<CardDTO> findAll(Customer customer) {
        Optional<List<Card>> list = cardRepository.findByCard(customer.getId());
        var entityList = list.orElseThrow(() -> new ResourceNotFoundException("O cliente não possui cartão vinculado a ele."));
        return entityList.stream().map(CardDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CardDTO insert(CardDTO cardDTO, Customer customer) {
        var entity = new Card();
        copyEntityToDto(entity, cardDTO, customer);
        entity = cardRepository.save(entity);
        return new CardDTO(entity);
    }

    public void delete(Long id) {
        try {
            cardRepository.deleteById(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Id {" + id + "} not found.");
        } catch (DataIntegrityViolationException e) {
            throw new DatabaseException("Id {" + id + "} violates integrity.");
        }
    }

    public void copyEntityToDto(Card entity, CardDTO cardDTO, Customer customer) {
        entity.setCustomer(customer);
        entity.setExpirationDateMonth(cardDTO.getExpirationDateMonth());
        entity.setExpirationDateYear(cardDTO.getExpirationDateYear());
        entity.setBanner(bannerRepository.findById(cardDTO.getBannerDTO().getId()).get());
        entity.setHolder(cardDTO.getHolder());
        entity.setNumber(cardDTO.getNumber());
        entity.setHolderCpf(cardDTO.getHolderCpf());
        entity.setSecurity(cardDTO.getSecurityNumber());
    }
}
