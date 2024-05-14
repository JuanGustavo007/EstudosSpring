package com.devsupeior.dscommerce.dto;

import com.devsupeior.dscommerce.entities.OrderItem;

public class OrderItemDto {

    private Long productId;
    private String name;
    private Double price;
    private int quantity;
    private String imgUrl;


    public OrderItemDto(Long productId, String name, Double price, int quantity,String imgUrl) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.imgUrl = imgUrl;
    }

    public OrderItemDto(OrderItem orderItem) {
        productId = orderItem.getProduct().getId();
        name = orderItem.getProduct().getName();
        price = orderItem.getPrice();
        quantity = orderItem.getQuantity();
        imgUrl=orderItem.getProduct().getImgUrl();

    }


    public Long getProductId() {
        return productId;
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

    public String getImgUrl() {
        return imgUrl;
    }

    public Double getSubTotal() {
        return price * quantity;
    }
}
