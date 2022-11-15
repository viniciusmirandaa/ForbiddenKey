package com.forbiddenkey.dto.card;

import com.forbiddenkey.dto.banner.BannerDTO;
import com.forbiddenkey.entities.Card;
import com.forbiddenkey.services.validation.card.CardValid;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@CardValid
public class CardDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ERROR_MESSAGE = "Por favor, preencha com dados válidos todos os campos obrigatórios abaixo.";

    private Long id;

    @NotNull(message = ERROR_MESSAGE)
    private String number;

    @NotNull(message = ERROR_MESSAGE)
    private String holder;

    @NotNull(message = ERROR_MESSAGE)
    @Size(min = 2, max = 2, message = ERROR_MESSAGE)
    private String expirationDateMonth;

    @NotNull(message = ERROR_MESSAGE)
    @Size(min = 4, max = 4, message = ERROR_MESSAGE)
    private String expirationDateYear;

    @NotNull(message = ERROR_MESSAGE)
    @Size(min = 3, max = 3, message = ERROR_MESSAGE)
    private String securityNumber;

    @CPF(message = ERROR_MESSAGE)
    private String holderCpf;

    @NotNull(message = ERROR_MESSAGE)
    private BannerDTO bannerDTO;

    public CardDTO(Card entity) {
        this.id = entity.getId();
        this.number = entity.getNumber();
        this.holder = entity.getHolder();
        this.expirationDateMonth = entity.getExpirationDateMonth();
        this.expirationDateYear = entity.getExpirationDateYear();
        this.securityNumber = entity.getSecurity();
        this.holderCpf = entity.getHolderCpf();
        this.bannerDTO = new BannerDTO(entity.getBanner());
    }

    public CardDTO() {
    }

    public CardDTO(Long id, String number, String holder, String expirationDateMonth, String expirationDateYear, String securityNumber, String holderCpf, BannerDTO bannerDTO) {
        this.id = id;
        this.number = number;
        this.holder = holder;
        this.expirationDateMonth = expirationDateMonth;
        this.expirationDateYear = expirationDateYear;
        this.securityNumber = securityNumber;
        this.holderCpf = holderCpf;
        this.bannerDTO = bannerDTO;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getHolder() {
        return holder;
    }

    public void setHolder(String holder) {
        this.holder = holder;
    }

    public String getExpirationDateMonth() {
        return expirationDateMonth;
    }

    public void setExpirationDateMonth(String expirationDateMonth) {
        this.expirationDateMonth = expirationDateMonth;
    }

    public String getExpirationDateYear() {
        return expirationDateYear;
    }

    public void setExpirationDateYear(String expirationDateYear) {
        this.expirationDateYear = expirationDateYear;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public String getHolderCpf() {
        return holderCpf;
    }

    public void setHolderCpf(String holderCpf) {
        this.holderCpf = holderCpf;
    }

    public BannerDTO getBannerDTO() {
        return bannerDTO;
    }

    public void setBannerDTO(BannerDTO bannerDTO) {
        this.bannerDTO = bannerDTO;
    }
}
