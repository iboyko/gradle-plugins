package com.example.domain;

import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@ToString
public class LombokObject1 {

    @Id
    private Long id;

}
