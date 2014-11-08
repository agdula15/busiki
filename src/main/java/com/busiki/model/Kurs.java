package com.busiki.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Kurs implements Serializable {

	private static final long serialVersionUID = 1L;

	public Kurs() {
	}

	@Id
	private long id;
	@OneToOne
	private TrasaInfo trasaInfo;
	private String wolneSiedzace;
	private String wolneStojace;
	@OneToMany
	@JoinTable(name = "kurs_srezerwacje", joinColumns = { @JoinColumn(name = "kurs_id") }, inverseJoinColumns = @JoinColumn(name = "rezerwacjas_id"))
	private Collection<Rezerwacja> rezerwacja;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public TrasaInfo getTrasaInfo() {
		return trasaInfo;
	}

	public void setTrasaInfo(TrasaInfo param) {
		this.trasaInfo = param;
	}

	public String getWolneSiedzace() {
		return wolneSiedzace;
	}

	public void setWolneSiedzace(String param) {
		this.wolneSiedzace = param;
	}

	public String getWolneStojace() {
		return wolneStojace;
	}

	public void setWolneStojace(String param) {
		this.wolneStojace = param;
	}

	public Collection<Rezerwacja> getRezerwacja() {
		return rezerwacja;
	}

	public void setRezerwacja(Collection<Rezerwacja> param) {
		this.rezerwacja = param;
	}

}