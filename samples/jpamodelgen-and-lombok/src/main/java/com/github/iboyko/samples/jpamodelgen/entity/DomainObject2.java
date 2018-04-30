package com.github.iboyko.samples.jpamodelgen.entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;

@Entity
public class DomainObject2 {

  @Id
  @GeneratedValue
  private Long id;

  public Long getId(){
    return id;
  }

  public void setId(Long id){
    this.id = id;
  }

}
