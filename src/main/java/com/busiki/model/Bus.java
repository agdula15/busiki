package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "bus")
public class Bus implements Serializable {

	private static final long serialVersionUID = 1L;

	public Bus() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private String nazwa;

	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	private int miejscaSiedzace;
	private int miejscaStojace;
	private String opis;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getMiejscaSiedzace() {
		return miejscaSiedzace;
	}

	public void setMiejscaSiedzace(int param) {
		this.miejscaSiedzace = param;
	}

	public int getMiejscaStojace() {
		return miejscaStojace;
	}

	public void setMiejscaStojace(int param) {
		this.miejscaStojace = param;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String param) {
		this.opis = param;
	}
}
