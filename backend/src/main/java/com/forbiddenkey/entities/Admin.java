package com.forbiddenkey.entities;

import javax.persistence.*;

@Entity
@Table(name = "tb_admin")
public class Admin extends DomainEntity {

    @OneToOne
    @JoinColumn(name = "user_id")
    private User adminUser;

    public Admin() {
    }

    public Admin(User adminUser) {
        this.adminUser = adminUser;
    }

    public User getAdminUser() {
        return adminUser;
    }

    public void setAdminUser(User adminUser) {
        this.adminUser = adminUser;
    }

    public User getUser() {
        return adminUser;
    }

    public void setUser(User user) {
        this.adminUser = user;
    }
}
