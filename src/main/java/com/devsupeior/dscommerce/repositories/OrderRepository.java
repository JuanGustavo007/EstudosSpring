package com.devsupeior.dscommerce.repositories;

import com.devsupeior.dscommerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
