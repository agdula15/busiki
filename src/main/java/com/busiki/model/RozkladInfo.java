package com.busiki.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "RozkladInfo")
public class RozkladInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	
	
	public RozkladInfo() {
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	private Date dataOd;
	private Date dataDo;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getDataOd() {
		return dataOd;
	}

	public void setDataOd(Date dataPoczatku) {
		
		this.dataOd = dataPoczatku;
	}

	public Date getDataDo() {
		return dataDo;
	}

	public void setDataDo(Date param) {
		this.dataDo = param;
	}

}