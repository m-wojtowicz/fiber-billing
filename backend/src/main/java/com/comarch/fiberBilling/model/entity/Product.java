package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`PRODUCT`")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_PRODUCT`", nullable = false)
    private long id;
    @Column(name = "`PRODUCT_NAME`", length = 45, nullable = false)
    private String productName;
}
