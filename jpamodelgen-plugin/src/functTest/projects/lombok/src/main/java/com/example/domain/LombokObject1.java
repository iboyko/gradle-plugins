package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.ToString;

@Entity
@ToString
public class LombokObject1 {

    @Id
    private Long id;

}
