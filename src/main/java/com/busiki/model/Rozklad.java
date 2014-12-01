package com.busiki.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Rozklad")
public class Rozklad implements Serializable {

	private static final long serialVersionUID = 1L;

	public Rozklad() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;

	private String godzina;

	@ManyToOne
	// @MapsId("id")
	@JoinColumn(name = "DniKursu_ID", referencedColumnName = "ID")
	private DniKursu dniKursu;

	@ManyToOne
	// @MapsId("id")
	@JoinColumn(name = "RozkladInfo_ID", referencedColumnName = "ID")
	private RozkladInfo rozkladInfo;

	@ManyToOne
	// @MapsId("id")
	@JoinColumn(name = "Trasa_Info_ID", referencedColumnName = "ID")
	private TrasaInfo trasaInfo;

	@ManyToOne
	// @MapsId("id")
	@JoinColumn(name = "Przystanek_ID", referencedColumnName = "ID")
	private Przystanek przystanek;

	private int numer;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getGodzina() {
		return godzina;
	}

	public void setGodzina(String param) {
		this.godzina = param;
	}

	public DniKursu getDniKursu() {
		return dniKursu;
	}

	public void setDniKursu(DniKursu param) {
		this.dniKursu = param;
	}

	public RozkladInfo getRozkladInfo() {
		return rozkladInfo;
	}

	public void setRozkladInfo(RozkladInfo param) {
		this.rozkladInfo = param;
	}

	public TrasaInfo getTrasaInfo() {
		return trasaInfo;
	}

	public void setTrasaInfo(TrasaInfo param) {
		this.trasaInfo = param;
	}

	public Przystanek getPrzystanek() {
		return przystanek;
	}

	public void setPrzystanek(Przystanek param) {
		this.przystanek = param;
	}

	public int getNumer() {
		return numer;
	}

	public void setNumer(int param) {
		this.numer = param;
	}

}