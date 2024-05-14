package com.devsupeior.dscommerce.controllers;



import com.devsupeior.dscommerce.dto.UserDto;

import com.devsupeior.dscommerce.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping(value = "/me")
    public ResponseEntity<UserDto> getMe(){
        UserDto userDto = userService.getMe();
        return ResponseEntity.ok(userDto);
    }

}
