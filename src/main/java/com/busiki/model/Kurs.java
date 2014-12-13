package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import com.busiki.model.Bus;
import com.busiki.model.Rezerwacja;

@Entity
public class Kurs implements Serializable {

	private static final long serialVersionUID = 1L;

	public Kurs() {
	}

	@Id
	@GeneratedValue
	private long id;
	private int wolneSiedzace;
	private int wolneStojace;
	private String dataKursu;
	@ManyToOne
	private Rozklad rozklad;
	@ManyToOne
	private Bus bus;
	@ManyToOne
	private Rezerwacja rezerwacja;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getWolneSiedzace() {
		return wolneSiedzace;
	}

	public void setWolneSiedzace(int param) {
		this.wolneSiedzace = param;
	}

	public int getWolneStojace() {
		return wolneStojace;
	}

	public void setWolneStojace(int param) {
		this.wolneStojace = param;
	}

	public String getDataKursu() {
		return dataKursu;
	}

	public void setDataKursu(String data) {
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

	public Rezerwacja getRezerwacja() {
	    return rezerwacja;
	}

	public void setRezerwacja(Rezerwacja param) {
	    this.rezerwacja = param;
	}

/*	public Rezerwacja getRezerwacja() {
	    return rezerwacja;
	}

	public void setRezerwacja(Rezerwacja param) {
	    this.rezerwacja = param;
	}*/

}