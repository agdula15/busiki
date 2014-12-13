package com.busiki.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
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
	private String numer;
	private String nazwa;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="przystanki")
	private Collection<TrasaInfo> trasaInfo;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNumer() {
		return numer;
	}

	public void setNumer(String param) {
		this.numer = param;
	}

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String param) {
		this.nazwa = param;
	}

	public Collection<TrasaInfo> getTrasaInfo() {
		return trasaInfo;
	}

	public void setTrasaInfo(Collection<TrasaInfo> param) {
		this.trasaInfo = param;
	}

}