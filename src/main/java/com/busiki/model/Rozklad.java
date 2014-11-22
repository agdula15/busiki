package com.busiki.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import com.busiki.model.RozkladInfo;

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

	@OneToMany
	private Collection<DniKursu> dniKursu;

	@OneToOne
	@MapsId("id")
	@JoinColumn(name = "RozkladInfo_ID", referencedColumnName = "ID")
	private RozkladInfo rozkladInfo;

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

	public Collection<DniKursu> getDniKursu() {
	    return dniKursu;
	}

	public void setDniKursu(Collection<DniKursu> param) {
	    this.dniKursu = param;
	}

	public RozkladInfo getRozkladInfo() {
	    return rozkladInfo;
	}

	public void setRozkladInfo(RozkladInfo param) {
	    this.rozkladInfo = param;
	}

}