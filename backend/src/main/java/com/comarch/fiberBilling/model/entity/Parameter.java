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
@Table(name = "`PARAMETER`")
public class Parameter {
    @Id
    @Column(name = "`ID_PARAMETER`", length = 45, nullable = false)
    private long id;
    @Column(name = "`NAME`", length = 45, nullable = false)
    private String name;
}