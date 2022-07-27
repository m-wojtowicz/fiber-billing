package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT_DATA")
public class ClientData {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "NAME", length = 45, nullable = true)
    private String name;
    @Column(name = "SURNAME", length = 45, nullable = true)
    private String surname;
    @Column(name = "PESEL", length = 11, nullable = true)
    private int pesel;
    @Column(name = "COMPANY_NAME", length = 45, nullable = true)
    private String companyName;
    @Column(name = "TAX_ID", length = 10, nullable = true)
    private int taxId;
    @Column(name = "EMAIL_ADDRESS", length = 45, nullable = false)
    private String emailAddress;
    @Column(name = "PHONE_NUMBER", nullable = false)
    private int phoneNumber;
    @JoinColumn(name = "ID_ADDRESS", referencedColumnName = "ID", nullable = false)
    @OneToOne(cascade = CascadeType.MERGE)
    private Address address;
    @JoinColumn(name = "ID_USER", referencedColumnName = "ID", nullable = false)
    @OneToOne(cascade = CascadeType.MERGE)
    private User user;
}
