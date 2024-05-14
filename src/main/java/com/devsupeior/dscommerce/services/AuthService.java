package com.devsupeior.dscommerce.services;

import com.devsupeior.dscommerce.entities.User;
import com.devsupeior.dscommerce.services.exceptions.ForbiddenException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    @Autowired
    private UserService userService;


    public void validadeSelfOrAdmin(long userId) {


        User me = userService.authenticate();
        if(!me.equals("ROLE_ADMIN") && !me.equals(userId)) {
                throw  new ForbiddenException("Acesso negado");
        }

    }



}
