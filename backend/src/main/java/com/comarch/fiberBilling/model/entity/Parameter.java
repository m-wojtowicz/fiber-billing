package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`PARAMERTER`")
public class Parameter {
    @Id
    @Column(name = "`ID_PARAMETER`", length = 45, nullable = false)
    private String id;
    @Column(name = "`VALUES`", length = 45, nullable = false)
    private String values;
    @JoinColumn(name = "`ID_PRICE`", nullable = false)
    @ManyToOne
    private Price price;
}
