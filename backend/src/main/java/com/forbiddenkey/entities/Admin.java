package com.forbiddenkey.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_admin")
public class Admin extends DomainEntity {

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User adminUser;

    public Admin() {
    }

    public Admin(User user) {
        this.adminUser = user;
    }

    public User getUser() {
        return adminUser;
    }

    public void setUser(User user) {
        this.adminUser = user;
    }
}
