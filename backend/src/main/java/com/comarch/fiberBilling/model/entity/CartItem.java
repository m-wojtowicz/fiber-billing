package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Builder
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`CART_ITEM`")
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_CART_ITEM`", nullable = false)
    private long id;
    @JoinColumn(name = "`ID_ORDER`", nullable = false)
    @ManyToOne
    private Order order;
    @JoinColumn(name = "`ID_PRODUCT`", nullable = false)
    @ManyToOne
    private Product product;
}
