package com.forbiddenkey.entities;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "tb_customer")
public class Customer extends DomainEntity{

    private String cpf;
    private Instant birthDate;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customerUser;

    public Customer() {
    }

    public Customer(String cpf, Instant birthDate, String phone, User user) {
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.phone = phone;
        this.customerUser = user;
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

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }
}
