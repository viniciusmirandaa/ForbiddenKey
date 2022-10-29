package com.forbiddenkey.dto;

import com.forbiddenkey.entities.Admin;
import com.forbiddenkey.entities.User;

import java.io.Serializable;

public class AdminDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private Long user;

    public AdminDTO() {
    }

    public AdminDTO(Long id, Long user) {
        this.id = id;
        this.user = user;
    }

    public AdminDTO(Admin entity) {
        this.id = entity.getId();
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
}
