package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "`ADDRESS`")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_ADDRESS`", nullable = false)
    private long id;
    @Column(name = "`COUNTRY`", length = 45, nullable = false)
    private String country;
    @Column(name = "`CITY`", length = 45, nullable = false)
    private String city;
    @Column(name = "`STREET`", length = 45, nullable = false)
    private String street;
    @Column(name = "`STREET_NUMBER`", length = 6, nullable = false)
    private String streetNumber;
    @Column(name = "`HOUSE_NUMBER`", length = 15, nullable = true)
    private String houseNumber;
    @Column(name = "`ZIP_CODE`", length = 45, nullable = false)
    private String zipCode;
    @Column(name = "`POST_OFFICE`", length = 45, nullable = false)
    private String postOffice;
//    @OneToOne(mappedBy = "address")
//    private ClientData clientData;
}
