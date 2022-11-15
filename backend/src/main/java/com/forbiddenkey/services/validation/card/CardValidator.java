package com.forbiddenkey.services.validation.card;

import com.forbiddenkey.dto.card.CardDTO;
import com.forbiddenkey.repositories.CardRepository;
import com.forbiddenkey.resources.exceptions.FieldMessage;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.ArrayList;
import java.util.List;

public class CardValidator implements ConstraintValidator<CardValid, CardDTO> {

    @Autowired
    private CardRepository repository;

    @Override
    public void initialize(CardValid ann) {
    }

    @Override
    public boolean isValid(CardDTO dto, ConstraintValidatorContext context) {

        List<FieldMessage> list = new ArrayList<>();

        var card = repository.findByNumber(dto.getNumber());

        if (dto.getId() != null) {
            if (card != null && !card.getId().equals(dto.getId())) {
                list.add(new FieldMessage("number", "o número do cartão inserido já é existente."));
            }
        } else {
            if (card != null) {
                list.add(new FieldMessage("number", "o número do cartão inserido já é existente."));
            }
        }
        for (FieldMessage e : list) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
                    .addConstraintViolation();
        }
        return list.isEmpty();
    }
}
