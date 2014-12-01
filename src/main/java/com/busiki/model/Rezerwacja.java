package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "rezerwacja")
public class Rezerwacja implements Serializable {

	private static final long serialVersionUID = 1L;

	public Rezerwacja() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private String data_rezerwacji;
	@ManyToOne
	private User user;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getData_rezerwacji() {
		return data_rezerwacji;
	}

	public void setData_rezerwacji(String param) {
		this.data_rezerwacji = param;
	}

}