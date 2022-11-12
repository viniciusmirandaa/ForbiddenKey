package com.forbiddenkey.entities;

import org.codehaus.jackson.annotate.JsonIgnore;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "tb_customer")
public class Customer extends DomainEntity{

    private String cpf;
    private LocalDate birthDate;
    private String phone;

    @OneToMany(mappedBy = "customer")
    private List<Cart> carts;

//    @OneToMany(mappedBy = "customerCard")
//    private List<Card> card;

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

//    public List<Card> getCard() {
//        return card;
//    }

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
