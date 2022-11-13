package com.forbiddenkey.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "tb_customer")
public class Customer extends DomainEntity{

    private String cpf;
    private LocalDate birthDate;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private Set<Cart> carts;

    @OneToMany(mappedBy = "customerCard", fetch = FetchType.EAGER)
    private Set<Card> cards;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User customerUser;

    public Customer() {
    }

    public Customer(String cpf, LocalDate birthDate, String phone, User customerUser) {
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.phone = phone;
        this.customerUser = customerUser;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public Set<Card> getCards() {
        return cards;
    }

    public User getCustomerUser() {
        return customerUser;
    }

    public void setCustomerUser(User customerUser) {
        this.customerUser = customerUser;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public User getUser() {
        return customerUser;
    }

    public void setUser(User user) {
        this.customerUser = user;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }
}
