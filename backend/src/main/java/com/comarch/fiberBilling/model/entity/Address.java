package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ADDRESS")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "STREET", length = 45, nullable = false)
    private String street;
    @Column(name = "HOUSE_NUMBER", length = 6, nullable = false)
    private String houseNumber;
    @Column(name = "APARTMENT_NUMBER", length = 15, nullable = true)
    private String apartmentNumber;
    @Column(name = "ZIP_CODE", length = 45, nullable = false)
    private String zipCode;
    @Column(name = "POST_OFFICE", length = 45, nullable = false)
    private String postOffice;
    @Column(name = "CITY", length = 45, nullable = false)
    private String city;
    @OneToOne(mappedBy = "address")
    private ClientData clientData;
}
