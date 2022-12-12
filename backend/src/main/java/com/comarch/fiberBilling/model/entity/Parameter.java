package com.comarch.fiberBilling.model.entity;

import lombok.*;

import java.util.List;

import javax.persistence.*;

@Getter
@Setter
@With
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "`PARAMETER`")
public class Parameter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "`ID_PARAMETER`", length = 45, nullable = false)
    private long id;
    @Column(name = "`NAME`", length = 45, nullable = false)
    private String name;
    @OneToMany(mappedBy = "parameter")
    private List<ParameterDetail> parameterDetail;
}
