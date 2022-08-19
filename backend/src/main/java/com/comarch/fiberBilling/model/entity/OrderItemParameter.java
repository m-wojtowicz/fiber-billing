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
@Table(name = "`ORDER_ITEM_PARAMETER`")
public class OrderItemParameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_ORDER_ITEM_PARAMETER`", nullable = false)
    private long id;
    @JoinColumn(name = "`ID_ORDER_ITEM`")
    @ManyToOne
    private OrderItem orderItem;
    @JoinColumn(name = "`ID_PARAMETER_DETAIL`")
    @ManyToOne
    private ParameterDetail parameterDetail;
}
