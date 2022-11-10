package com.forbiddenkey.dto;

import com.forbiddenkey.entities.Card;
import com.forbiddenkey.entities.Customer;

import java.io.Serializable;

public class CardDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String number;

    private String holder;

    private String expirationDateMonth;

    private String expirationDateYear;

    private String securityNumber;

    private String holderCpf;

    private String flag;

    public CardDTO(Card entity){
        this.number = entity.getNumber();
        this.holder = entity.getHolder();
        this.expirationDateMonth = entity.getExpirationDateMonth();
        this.expirationDateYear = entity.getExpirationDateYear();
        this.securityNumber = entity.getSecurity();
        this.holderCpf = entity.getHolderCpf();
        this.flag = entity.getFlag();
    }

    public CardDTO() {
    }

    public CardDTO(String number, String holder, String expirationDateMonth, String expirationDateYear, String securityNumber, String holderCpf, String flag) {
        this.number = number;
        this.holder = holder;
        this.expirationDateMonth = expirationDateMonth;
        this.expirationDateYear = expirationDateYear;
        this.securityNumber = securityNumber;
        this.holderCpf = holderCpf;
        this.flag = flag;
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

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
