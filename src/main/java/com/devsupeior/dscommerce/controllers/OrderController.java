    package com.devsupeior.dscommerce.controllers;


import com.devsupeior.dscommerce.dto.OrderDto;
import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.dto.ProductMinDto;
import com.devsupeior.dscommerce.entities.Order;
import com.devsupeior.dscommerce.services.OrderService;
import com.devsupeior.dscommerce.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(path = "/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;


    @PreAuthorize("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')")
    @GetMapping(value = "/{id}")
    public ResponseEntity<OrderDto> findById(@PathVariable Long id){
        OrderDto orderDto = orderService.findById(id);
        return ResponseEntity.ok(orderDto);
    }


    @PreAuthorize(("hasAnyRole('ROLE_ADMIN','ROLE_CLIENT')"))
    @PostMapping
    public ResponseEntity<OrderDto> insert( @RequestBody OrderDto orderDto){
        orderDto = orderService.insert(orderDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(orderDto.getId()).toUri();
        return ResponseEntity.created(uri).body(orderDto);
    }

}
