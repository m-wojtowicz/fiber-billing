package com.comarch.fiberBilling.repository;

import com.comarch.fiberBilling.model.entity.Order;
import com.comarch.fiberBilling.model.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderItemRepository extends JpaRepository <OrderItem, Long>{

    @Query(
        value = "Select \"ORDER_ITEM\".\"ID_ORDER_ITEM\", \"ORDER_ITEM\".\"ACTIVATION_DATE\", \"ORDER_ITEM\".\"MONTHLY\", \"ORDER_ITEM\".\"ORDER_ITEM_NAME\", \"ORDER_ITEM\".\"ID_ORDER\", \"ORDER_ITEM\".\"STATUS\" " +
                "FROM \"ORDER_ITEM\" " +
                "JOIN \"ORDER\" USING(\"ID_ORDER\") " +
                "JOIN \"CLIENT_DATA\" USING(\"ID_CLIENT_DATA\") " +
                "WHERE \"CLIENT_DATA\".\"ID_CLIENT_DATA\" = ?1 AND \"ORDER_ITEM\".\"MONTHLY\" = true",
            nativeQuery = true
    )
    Optional<List<OrderItem>> getAllUserProducts(Long id);
    List<OrderItem> findByOrder(Order order);
}
