package com.forbiddenkey.entities;

import javax.persistence.*;
import java.time.Instant;
import java.time.LocalDate;

@Entity
@Table(name = "tb_customer")
public class Customer extends DomainEntity{

    private String firstName;
    private String lastName;
    private String cpf;
    private LocalDate birthDate;
    private String phone;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User customerUser;

    public Customer() {
    }

    public Customer(String firstName, String lastName, String cpf, LocalDate birthDate, String phone, User customerUser) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.phone = phone;
        this.customerUser = customerUser;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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
