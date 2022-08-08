package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Builder
@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`ORDER_ITEM`")
public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_ORDER_ITEM`", nullable = false)
    private long id;
    @JoinColumn(name = "`ID_ORDER`", nullable = false)
    @ManyToOne
    private Order order;
    @JoinColumn(name = "`ID_PRODUCT`", nullable = false)
    @ManyToOne
    private Product product;
    @Column(name = "`MONTHLY`", nullable = false)
    private boolean monthly;
    @Column(name = "`ACTIVATION_DATE`", nullable = false)
    private Date activationDate;
}
