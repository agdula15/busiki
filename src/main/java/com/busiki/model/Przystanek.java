package com.busiki.model;

import java.io.Serializable;

import javax.persistence.*;

import com.busiki.model.TrasaInfo;

import java.util.Collection;

@Entity
@Table(name = "Przystanek")
public class Przystanek implements Serializable {

	private static final long serialVersionUID = 1L;

	public Przystanek() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	private String numer;
	private String nazwa;
	@ManyToMany
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