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
@Builder
@Table(name = "`ORDER`")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "`ID_ORDER`", nullable = false)
    private long id;
    @Column(name = "`ORDER_STATUS`", length = 20, nullable = false)
    private String orderStatus;
    @Column(name = "`ORDER_START_DATE`", nullable = false)
    private Date orderStartDate;
    @Column(name = "`ORDER_END_DATE`", nullable = false)
    private Date orderEndDate;
    @JoinColumn(name = "`ID_CLIENT_DATA`", nullable = false)
    @ManyToOne
    private ClientData clientData;
}
