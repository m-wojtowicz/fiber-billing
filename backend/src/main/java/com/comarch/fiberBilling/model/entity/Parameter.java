package com.comarch.fiberBilling.model.entity;

import lombok.*;
import java.util.Date;

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
    @Column(name = "`ACTIVE_FROM`", nullable = false)
    private Date activeFrom;
    @Column(name = "`ACTIVE_TO`", nullable = false)
    private Date activeTo;
}
