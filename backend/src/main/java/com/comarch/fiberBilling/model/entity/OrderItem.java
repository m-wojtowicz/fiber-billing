package com.comarch.fiberBilling.model.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
    @JsonBackReference
    private Order order;
    @Column(name = "`ORDER_ITEM_NAME`", nullable = false)
    private String orderItemName;
    @Column(name = "`ACTIVATION_DATE`", nullable = false)
    private Date activationDate;
    @Column(name = "`STATUS`", nullable = false)
    private String status;
}
