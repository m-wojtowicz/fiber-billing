package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "\"USER\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "LOGIN", length = 45, nullable = false)
    private String login;
    @JoinColumn(name = "ID_CLIENT_TYPE", nullable = false)
    @ManyToOne
    private ClientType clientType;
    @OneToOne(mappedBy = "user")
    private ClientData clientData;
}
