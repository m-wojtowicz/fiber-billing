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
@Table(name = "`CART_ITEM_PARAMETER`")
public class CartItemParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_CART_ITEM_PARAMETER`", nullable = false)
    private long id;
    @JoinColumn(name = "`ID_CART_ITEM`", nullable = false)
    @ManyToOne
    private CartItem cartItem;
    @Column(name = "`NAME`", nullable = false)
    private String name;
    @Column(name = "`COST`", nullable = false)
    private double cost;
}
