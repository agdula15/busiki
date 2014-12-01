package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

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
	private String dataKursu;
	@ManyToOne
	private Rozklad rozklad;
/*	@ManyToOne
	private Rezerwacja rezerwacja;*/
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

/*	public Rezerwacja getRezerwacja() {
	    return rezerwacja;
	}

	public void setRezerwacja(Rezerwacja param) {
	    this.rezerwacja = param;
	}*/

}