package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Entity;
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
	private long id;
	private String data_rezerwacji;
	@ManyToOne
	private Kurs kurs;

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

	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs param) {
		this.kurs = param;
	}

}