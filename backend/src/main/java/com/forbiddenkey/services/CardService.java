package com.forbiddenkey.services;

import com.forbiddenkey.dto.card.CardDTO;
import com.forbiddenkey.entities.Card;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.repositories.BannerRepository;
import com.forbiddenkey.repositories.CardRepository;
import com.forbiddenkey.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BannerRepository bannerRepository;

    @Transactional(readOnly = true)
    public List<CardDTO> findAll(Customer customer) {
        List<Card> list = cardRepository.findByCard(customer.getId());
        return list.stream().map(CardDTO::new).collect(Collectors.toList());
    }

    @Transactional
    public CardDTO insert(CardDTO cardDTO, Customer customer) {
        var entity = new Card();
        copyEntityToDto(entity, cardDTO, customer);
        entity = cardRepository.save(entity);
        return new CardDTO(entity);
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
