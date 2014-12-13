package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Przystanek")
public class Przystanek implements Serializable {

	private static final long serialVersionUID = 1L;

	public Przystanek() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private int numer;
	private String nazwa;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int param) {
		this.numer = param;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String param) {
		this.nazwa = param;
	}

}