package com.devsupeior.dscommerce.dto;

import com.devsupeior.dscommerce.entities.User;

public class ClientDto {

    private Long id;
    private String name;

    public ClientDto(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public ClientDto(User user){
        this.id = user.getId();
        this.name = user.getName();
    }


    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
