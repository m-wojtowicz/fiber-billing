package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`ORDER`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_ORDER`", nullable = false)
    private long id;
    @Column(name = "`ORDER_STATUS`", length = 20, nullable = false)
    private String orderStatus;
    @Column(name = "`ORDER_DATE`", nullable = false)
    private Date orderDate;
    @Column(name = "`PRODUCT_QUANTITY`", nullable = false)
    private int productQuantity;
    @JoinColumn(name = "`ID_OFFER`", nullable = false)
    @ManyToOne
    private Offer offer;
    @JoinColumn(name = "`ID_CLIENT_DATA`", nullable = false)
    @ManyToOne
    private ClientData clientData;
}
