package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Ulga")
public class Ulga implements Serializable{
	private static final long serialVersionUID = -3149646836274244208L;
	
	public Ulga(){}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "opis")
	private String opis;
	
	@Column(name = "wartoscUlgi")
	private double wartoscUlgi;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public double getWartoscUlgi() {
		return wartoscUlgi;
	}

	public void setWartoscUlgi(double wartoscUlgi) {
		this.wartoscUlgi = wartoscUlgi;
	}

}