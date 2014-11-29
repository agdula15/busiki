package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "DniKursu")
public class DniKursu implements Serializable {

	private static final long serialVersionUID = 1L;

	public DniKursu() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private String dzien;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDzien() {
		return dzien;
	}

	public void setDzien(String param) {
		this.dzien = param;
	}

}