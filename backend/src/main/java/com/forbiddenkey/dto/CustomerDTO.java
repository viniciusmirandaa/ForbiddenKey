package com.forbiddenkey.dto;

import com.forbiddenkey.entities.Customer;

import java.io.Serializable;
import java.time.Instant;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String cpf;
    private Instant birthDate;
    private String phone;
    private Long user;

    public CustomerDTO() {
    }

    public CustomerDTO(Long id, String cpf, Instant birthDate, String phone, Long user) {
        this.id = id;
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

    public Instant getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Instant birthDate) {
        this.birthDate = birthDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
