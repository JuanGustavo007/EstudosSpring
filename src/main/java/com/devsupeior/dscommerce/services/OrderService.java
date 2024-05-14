package com.devsupeior.dscommerce.services;

import com.devsupeior.dscommerce.dto.OrderDto;
import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.entities.Order;
import com.devsupeior.dscommerce.entities.Product;
import com.devsupeior.dscommerce.repositories.OrderRepository;
import com.devsupeior.dscommerce.repositories.ProductRepository;
import com.devsupeior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        Order result = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso nao encontrado"));
        return new OrderDto(result);
    }
}
