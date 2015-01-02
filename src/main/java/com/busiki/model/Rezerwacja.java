package com.busiki.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MapsId;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;

@Entity
@Table(name = "rezerwacja")
public class Rezerwacja implements Serializable {

	private static final long serialVersionUID = 1L;

	public Rezerwacja() {
	}

	@Id
	@GeneratedValue
	@Column(name = "ID")
	private long id;
	private Date dataRezerwacji;
	@ManyToOne
	@JoinColumn(name = "USERS_ID", referencedColumnName = "ID")
	private User user;
	@ManyToOne
	@JoinColumn(name = "Kurs_id", referencedColumnName = "ID")
	private Kurs kurs;
	@ManyToOne
	@JoinColumn(name = "Kurs_id2", referencedColumnName = "ID")
	private Kurs kurs2;
	@ElementCollection  
	@Column(name="nr_miejsca_zarezewowanego")
	private Set<Integer> miejscaZarezerwowane = new HashSet<Integer>();
	
	public void setMiejscaZarezerwowane(Set<Integer> miejscaZarezerwowane) {
		this.miejscaZarezerwowane = miejscaZarezerwowane;
	}

	public Set<Integer> getMiejscaZarezerwowane() {
		return miejscaZarezerwowane;
	}

	@Column(precision=10, scale=2)
	private float kosztBiletu;
	@Column(columnDefinition = "boolean default false")
	private boolean czyZaplacone;
	private int iloscMiejscZarezerwowanych;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User param) {
		this.user = param;
	}

	public Kurs getKurs() {
		return kurs;
	}

	public void setKurs(Kurs param) {
		this.kurs = param;
	}

	public Kurs getKurs2() {
		return kurs2;
	}

	public void setKurs2(Kurs param) {
		this.kurs2 = param;
	}

	public Date getDataRezerwacji() {
		return dataRezerwacji;
	}

	public void setDataRezerwacji(Date dataRezerwacji) {
		this.dataRezerwacji = dataRezerwacji;
	}
	public float getKosztBiletu() {
		return kosztBiletu;
	}

	public void setKosztBiletu(float param) {
		this.kosztBiletu = param;
	}

	public boolean getCzyZaplacone() {
		return czyZaplacone;
	}

	public void setCzyZaplacone(boolean param) {
		this.czyZaplacone = param;
	}

	public int getIloscMiejscZarezerwowanych() {
		return iloscMiejscZarezerwowanych;
	}

	public void setIloscMiejscZarezerwowanych(int param) {
		this.iloscMiejscZarezerwowanych = param;
	}

}