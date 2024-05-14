package com.devsupeior.dscommerce.services;

import com.devsupeior.dscommerce.dto.OrderDto;
import com.devsupeior.dscommerce.dto.OrderItemDto;
import com.devsupeior.dscommerce.dto.ProductDto;
import com.devsupeior.dscommerce.entities.*;
import com.devsupeior.dscommerce.repositories.OrderItemRepository;
import com.devsupeior.dscommerce.repositories.OrderRepository;
import com.devsupeior.dscommerce.repositories.ProductRepository;
import com.devsupeior.dscommerce.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private AuthService authService;

    @Transactional(readOnly = true)
    public OrderDto findById(Long id) {
        Order result = orderRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Recurso nao encontrado"));
        authService.validadeSelfOrAdmin(result.getClient().getId());
        return new OrderDto(result);
    }


    @Transactional
    public OrderDto insert(OrderDto orderDto) {
        Order order = new Order();

        order.setMoment(Instant.now());
        order.setStatus(OrderStatus.WAITING_PAYMENT);

        User user = userService.authenticate();
        order.setClient(user);

        for (OrderItemDto orderItemDto : orderDto.getItems()) {
            Product product = productRepository.getReferenceById(orderItemDto.getProductId());
            OrderItem orderItem = new OrderItem(order, product, orderItemDto.getQuantity(), product.getPrice());
            order.getItems().add(orderItem);
        }
        orderRepository.save(order);
        orderItemRepository.saveAll(order.getItems());


        return new OrderDto(order);
    }
}
