package com.forbiddenkey.entities;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "tb_banner")
public class Banner extends DomainEntity {

    private String name;

    public Banner() {
    }

    public Banner(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
