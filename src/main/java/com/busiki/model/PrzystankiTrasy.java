package com.busiki.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Entity implementation class for Entity: PrzystankiTrasy
 *
 */
@Entity
@Table(name = "przystanki_trasy")
public class PrzystankiTrasy implements Serializable {

	private static final long serialVersionUID = 1L;

	public PrzystankiTrasy() {
		super();
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private int numerPrzystanku;
	@ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Trasa_Info_ID", referencedColumnName = "ID")
	private TrasaInfo trasaInfo;
	@ManyToOne
	@JoinColumn(name = "Przystanek_ID", referencedColumnName = "ID")
	private Przystanek przystanek;
	public int getNumerPrzystanku() {
		return numerPrzystanku;
	}

	public void setNumerPrzystanku(int param) {
		this.numerPrzystanku = param;
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

}
