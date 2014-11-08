package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="bus")
public class Bus implements Serializable {

	private static final long serialVersionUID = 1L;

	public Bus() {
	}

	@Id
	private long id;
	private String nr;
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