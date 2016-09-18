package com.concretepage;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class User implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
	private String name;
	private Integer age;

    public User() {}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
}
