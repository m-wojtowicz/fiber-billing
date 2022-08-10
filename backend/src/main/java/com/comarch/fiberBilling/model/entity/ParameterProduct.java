package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`PARAMETER_X_PRODUCT`")
public class ParameterProduct {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_PARAMETER_X_PRODUCT`", nullable = false)
    private long id;
    @JoinColumn(name = "`ID_PRODUCT`", referencedColumnName = "`ID_PRODUCT`")
    @ManyToOne
    private Product product;
    @JoinColumn(name = "`ID_PARAMETER`", referencedColumnName = "`ID_PARAMETER`")
    @ManyToOne
    private Parameter parameter;
}
