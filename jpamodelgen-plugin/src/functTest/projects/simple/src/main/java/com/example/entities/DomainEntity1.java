package com.example.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DomainEntity1 {

    @Id
    private Long id;

    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

}
