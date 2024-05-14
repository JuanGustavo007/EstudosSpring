package com.devsupeior.dscommerce.dto;

import com.devsupeior.dscommerce.entities.User;
import jakarta.persistence.Column;
import org.springframework.security.core.GrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class UserDto {

    private long id;
    private String name;
    private String email;
    private String phone;
    private LocalDate birth_date;
    private String password;

    private List<String> roles = new ArrayList<>();

    public UserDto(User entity) {
        id = entity.getId();
        name = entity.getName();
        email = entity.getEmail();
        phone = entity.getPhone();
        birth_date = entity.getBirthdate();
        password = entity.getPassword();
        for (GrantedAuthority authority : entity.getAuthorities()) {
            roles.add(authority.getAuthority());
        }
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirth_date() {
        return birth_date;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getRoles() {
        return roles;
    }
}