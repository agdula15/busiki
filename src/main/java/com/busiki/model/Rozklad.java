package com.busiki.model;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Rozklad")
public class Rozklad implements Serializable {

	private static final long serialVersionUID = 1L;

	public Rozklad() {
	}

	@Id
	private long id;

	@OneToOne
	@MapsId("id")
	@JoinColumn(name = "Przystanek_id", referencedColumnName = "id")
	private Przystanek przystanek;
	@OneToOne
	@MapsId("id")
	@JoinColumn(name = "Trasa_Info_id", referencedColumnName = "id")
	private TrasaInfo trasaInfo;

	@ElementCollection
	private Map<Integer, String> godziny;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Przystanek getPrzystanek() {
		return przystanek;
	}

	public void setPrzystanek(Przystanek param) {
		this.przystanek = param;
	}

	public TrasaInfo getTrasaInfo() {
		return trasaInfo;
	}

	public void setTrasaInfo(TrasaInfo param) {
		this.trasaInfo = param;
	}

	public Map<Integer,String> getGodziny() {
		return godziny;
	}

	public void setGodziny(Map<Integer,String> param) {
		this.godziny = param;
	}

}