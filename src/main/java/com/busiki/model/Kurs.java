package com.busiki.model;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

@Entity
public class Kurs implements Serializable {

	private static final long serialVersionUID = 1L;

	public Kurs() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private String wolneSiedzace;
	private String wolneStojace;
	@OneToMany
	@JoinTable(name = "kurs_rezerwacje", joinColumns = { @JoinColumn(name = "kurs_id") }, inverseJoinColumns = @JoinColumn(name = "rezerwacjas_id"))
	private Collection<Rezerwacja> rezerwacja;
	private String dataKursu;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getDataKursu() {
		return dataKursu;
	}

	public void setDataKursu(String data) {
		this.dataKursu = data;
	}

}