package com.busiki.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Trasa_Info")
public class TrasaInfo implements Serializable {

	private static final long serialVersionUID = 1L;

	public TrasaInfo() {
	}

	@Id
	private long id;
	private String numer;
	@ManyToMany(mappedBy = "trasaInfo")
	private Collection<Przystanek> przystanki;
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
	
	public int getCountPrzystanek(){
		return this.przystanki.size();
	}

}