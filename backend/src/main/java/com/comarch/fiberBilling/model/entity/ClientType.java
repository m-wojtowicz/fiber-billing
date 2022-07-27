package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "CLIENT_TYPE")
public class ClientType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    @Column(name = "TYPE", length = 20, nullable = false)
    private String type;
    @OneToMany(mappedBy = "clientType")
    private Set<User> users;
}
