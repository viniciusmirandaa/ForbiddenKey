package com.forbiddenkey.dto.banner;

import com.forbiddenkey.entities.Banner;

import java.io.Serializable;

public class BannerDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    private String name;

    public BannerDTO() {
    }

    public BannerDTO(String name) {
        this.name = name;
    }

    public BannerDTO(Banner entity) {
        this.id = entity.getId();
        this.name = entity.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
