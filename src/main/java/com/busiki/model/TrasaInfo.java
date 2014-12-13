package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Trasa_Info")
public class TrasaInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TrasaInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private int numer;
	private String poczatek;
	private String koniec;

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

	public String getPoczatek() {
		return poczatek;
	}

	public void setPoczatek(String param) {
		this.poczatek = param;
	}

	public String getKoniec() {
		return koniec;
	}

	public void setKoniec(String param) {
		this.koniec = param;
	}

}