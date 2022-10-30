package com.forbiddenkey.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.forbiddenkey.entities.Customer;

import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private String cpf;
    @JsonFormat(pattern="dd-MM-yyy")
    private LocalDate birthDate;
    private String phone;
    private Long user;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String firstName, String lastName, String cpf, LocalDate birthDate, String phone, Long user) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.cpf = cpf;
        this.birthDate = birthDate;
        this.phone = phone;
        this.user = user;
    }

    public CustomerDTO(Customer entity) {
        this.id = entity.getId();
        this.cpf = entity.getCpf();
        this.birthDate = entity.getBirthDate();
        this.phone = entity.getPhone();
        this.user = entity.getUser().getId();
        this.firstName = entity.getFirstName();
        this.lastName = entity.getLastName();
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUser() {
        return user;
    }

    public void setUser(Long user) {
        this.user = user;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
