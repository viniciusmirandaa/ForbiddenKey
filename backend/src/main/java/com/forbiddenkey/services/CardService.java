package com.forbiddenkey.services;

import com.forbiddenkey.dto.card.CardDTO;
import com.forbiddenkey.entities.Card;
import com.forbiddenkey.entities.Customer;
import com.forbiddenkey.repositories.BannerRepository;
import com.forbiddenkey.repositories.CardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CardService {

    @Autowired
    private CardRepository cardRepository;

    @Autowired
    private BannerRepository bannerRepository;

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
