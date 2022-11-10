package com.forbiddenkey.entities;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_card")
public class Card extends DomainEntity {

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customerCard;

    private String number;

    private String holder;

    private String expirationDateMonth;

    private String expirationDateYear;

    private String securityNumber;

    private String holderCpf;

    private String flag;

    public Customer getCustomer() {
        return customerCard;
    }

    public void setCustomer(Customer customer) {
        this.customerCard = customer;
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

    public String getSecurity() {
        return securityNumber;
    }

    public void setSecurity(String security) {
        this.securityNumber = security;
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
