package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bus")
public class Bus implements Serializable {

	private static final long serialVersionUID = 1L;

	public Bus() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private String nr;
	private String nazwa;
	public String getNazwa() {
		return nazwa;
	}

	public void setNazwa(String nazwa) {
		this.nazwa = nazwa;
	}

	private String miejscaSiedzace;
	private String miejscaStojace;
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNr() {
		return nr;
	}

	public void setNr(String param) {
		this.nr = param;
	}

	public String getMiejscaSiedzace() {
		return miejscaSiedzace;
	}

	public void setMiejscaSiedzace(String param) {
		this.miejscaSiedzace = param;
	}

	public String getMiejscaStojace() {
		return miejscaStojace;
	}

	public void setMiejscaStojace(String param) {
		this.miejscaStojace = param;
	}
}
