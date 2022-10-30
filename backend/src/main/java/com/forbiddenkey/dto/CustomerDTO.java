package com.forbiddenkey.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.forbiddenkey.entities.Customer;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Past;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.Instant;
import java.time.LocalDate;

public class CustomerDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    private static final String ERROR_MESSAGE = "Por favor, preencha com dados válidos todos os campos obrigatórios abaixo.";

    private Long id;
    private String firstName;
    private String lastName;
    @NotBlank(message = ERROR_MESSAGE)
    @CPF(message = ERROR_MESSAGE)
    private String cpf;
    @JsonFormat(pattern="dd-MM-yyy")
    @PastOrPresent(message = ERROR_MESSAGE)
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
        this.firstName = entity.getUser().getFirstName();
        this.lastName = entity.getUser().getLastName();
        this.cpf = entity.getCpf();
        this.birthDate = entity.getBirthDate();
        this.phone = entity.getPhone();
        this.user = entity.getUser().getId();
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
