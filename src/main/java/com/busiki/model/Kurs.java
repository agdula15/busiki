package com.busiki.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Kurs implements Serializable {

	private static final long serialVersionUID = 1L;

	public Kurs() {
	}

	@Id
	@GeneratedValue
	private long id;
	
	private Date dataKursu;
	@ManyToOne
	private Rozklad rozklad;
	@ManyToOne
	private Bus bus;

	private String szczegoly;
	
	@ElementCollection
	@Column(name="nr_miejsca_zajetego")
	private Set<Integer> miejscaZajete = new HashSet<Integer>();

	public Set<Integer> getMiejscaZajete() {
		return miejscaZajete;
	}

	public void setMiejscaZajete(Set<Integer> miejscaZajete) {
		this.miejscaZajete = miejscaZajete;
	}

	public String getSzczegoly() {
		return szczegoly;
	}

	public void setSzczegoly(String szczegoly) {
		this.szczegoly = szczegoly;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	/*
	 * public int getWolneStojace() { return wolneStojace; }
	 * 
	 * public void setWolneStojace(int param) { this.wolneStojace = param; }
	 */

	public Date getDataKursu() {
		return dataKursu;
	}

	public void setDataKursu(Date data) {
		this.dataKursu = data;
	}

	public Rozklad getRozklad() {
		return rozklad;
	}

	public void setRozklad(Rozklad param) {
		this.rozklad = param;
	}

	public Bus getBus() {
		return bus;
	}

	public void setBus(Bus param) {
		this.bus = param;
	}

	/*
	 * public Rezerwacja getRezerwacja() { return rezerwacja; }
	 * 
	 * public void setRezerwacja(Rezerwacja param) { this.rezerwacja = param; }
	 */

}