package com.forbiddenkey.dto.admin;

import com.forbiddenkey.entities.Admin;
import com.forbiddenkey.entities.User;

import java.io.Serializable;

public class AdminDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String firstName;
    private String lastName;
    private Long user;
    private String email;

    public AdminDTO() {
    }

    public AdminDTO(Long id, String firstName, String lastName, Long user, String email) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.user = user;
        this.email = email;
    }

    public AdminDTO(Admin entity) {
        this.id = entity.getId();
        this.firstName = entity.getUser().getFirstName();
        this.lastName = entity.getUser().getLastName();
        this.user = entity.getUser().getId();
        this.email = entity.getUser().getEmail();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}