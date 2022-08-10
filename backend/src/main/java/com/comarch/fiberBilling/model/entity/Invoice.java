package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.sql.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`INVOICE`")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_INVOICE`", length = 20, nullable = false)
    private long id;
    @Column(name = "`PAYMENT_DATE`", nullable = false)
    private Date paymentDate;
    @Column(name = "`INVOICE_DATE`", nullable = false)
    private Timestamp invoiceDate;
    @Column(name = "`DUE_DATE`", nullable = false)
    private Date dueDate;
    @JoinColumn(name = "`ID_ORDER`", nullable = false)
    @ManyToOne
    private Order order;
    @JoinColumn(name = "`ID_PROVIDER_DATA`", nullable = false)
    @ManyToOne
    private ProviderData dataProvider;
}
