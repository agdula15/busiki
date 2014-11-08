package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;



@Entity
@Table(name = "ROLE")
@NamedQuery(name = "findRoleByName", query = "SELECT r FROM Role r WHERE r.name= :name")  //?
public class Role implements Serializable{

	private static final long serialVersionUID = -5472275492866495332L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	@Column(name = "NAME")
	private String name;
	public long getId() {
		return id;
	}
	
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	

}
