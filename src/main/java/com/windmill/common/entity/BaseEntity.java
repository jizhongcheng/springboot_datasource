package com.windmill.common.entity;

import javax.persistence.Id;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BaseEntity extends PagedEntity {
    private static final long serialVersionUID = 1L;
    @Id
    private Long id;

    @JsonIgnore
    @Transient
    private Integer limit;

}
