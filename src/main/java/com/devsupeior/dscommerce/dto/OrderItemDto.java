package com.devsupeior.dscommerce.dto;

import com.devsupeior.dscommerce.entities.OrderItem;

public class OrderItemDto {

    private Long id;
    private String name;
    private Double price;
    private int quantity;


    public OrderItemDto(Long id, String name, Double price, int quantity) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    public OrderItemDto(OrderItem orderItem) {
        id = orderItem.getProduct().getId();
        name = orderItem.getProduct().getName();
        price = orderItem.getPrice();
        quantity = orderItem.getQuantity();

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }


    public Double getSubTotal() {
        return price * quantity;
    }
}
