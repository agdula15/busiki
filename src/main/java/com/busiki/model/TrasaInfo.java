package com.busiki.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Trasa_Info")
public class TrasaInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TrasaInfo() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	private String numer;
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "przystanki_trasy", joinColumns = { @JoinColumn(name = "TRASA_INFO_ID", referencedColumnName = "ID") }, inverseJoinColumns = { @JoinColumn(name = "PRZYSTANEK_ID", referencedColumnName = "ID") })
	private Collection<Przystanek> przystanki = new ArrayList <Przystanek>();

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

	public Collection<Przystanek> getPrzystanek() {
		return przystanki;
	}

	public void setPrzystanek(Collection<Przystanek> param) {
		this.przystanki = param;
	}

	public int getCountPrzystanek() {
		return this.przystanki.size();
	}

}