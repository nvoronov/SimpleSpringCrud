package com.example.simple_crud_with_h2.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "simple_table")
public class SimpleEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "simple_name")
  private String name;

  @Column(name = "simple_number")
  private Integer number;

  public SimpleEntity() {
  }

  public SimpleEntity(String name, Integer number) {
    this.name = name;
    this.number = number;
  }

  public long getId() {
    return id;
  }

  public SimpleEntity setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public SimpleEntity setName(String name) {
    this.name = name;
    return this;
  }

  public Integer getNumber() {
    return number;
  }

  public SimpleEntity setNumber(Integer number) {
    this.number = number;
    return this;
  }
}
