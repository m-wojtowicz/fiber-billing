package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "CLIENT_DATA")
public class ClientData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "NAME", length = 45, nullable = true)
    private String name;
    @Column(name = "SURNAME", length = 45, nullable = true)
    private String surname;
    @Column(name = "EMAIL_ADDRESS", length = 45, nullable = false, unique = true)
    private String emailAddress;
    @JoinColumn(name = "ID_ADDRESS", referencedColumnName = "ID", nullable = false)
    @OneToOne(cascade = CascadeType.MERGE)
    private Address address;
    @Column(name = "LOGIN", length = 45, nullable = false, unique = true)
    private String login;
    @JoinColumn(name = "ID_CLIENT_TYPE", nullable = false)
    @ManyToOne
    private ClientType clientType;
}
