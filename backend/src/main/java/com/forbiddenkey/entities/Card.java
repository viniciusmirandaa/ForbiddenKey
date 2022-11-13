package com.forbiddenkey.entities;

import javax.persistence.*;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "banner_id")
    private Banner banner;

    public Card() {
    }

    public Card(Customer customerCard, String number, String holder, String expirationDateMonth, String expirationDateYear, String securityNumber, String holderCpf, Banner banner) {
        this.customerCard = customerCard;
        this.number = number;
        this.holder = holder;
        this.expirationDateMonth = expirationDateMonth;
        this.expirationDateYear = expirationDateYear;
        this.securityNumber = securityNumber;
        this.holderCpf = holderCpf;
        this.banner = banner;
    }

    public Customer getCustomerCard() {
        return customerCard;
    }

    public void setCustomerCard(Customer customerCard) {
        this.customerCard = customerCard;
    }

    public String getSecurityNumber() {
        return securityNumber;
    }

    public void setSecurityNumber(String securityNumber) {
        this.securityNumber = securityNumber;
    }

    public Banner getBanner() {
        return banner;
    }

    public void setBanner(Banner banner) {
        this.banner = banner;
    }

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

}
