package com.comarch.fiberBilling.model.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "`SYSTEM`")
public class System {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID`", nullable = false)
    private long id;
    @Column(name = "`IsFiberExist`")
    private boolean isFiberExist;
    @Column(name = "`OntExist`")
    private boolean ontExist;
    @Column(name = "`MinimalActivationDate`")
    private Date minimalActivationDate;
}
