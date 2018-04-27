package com.example.domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DomainObject1 {

    @Id
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

}
