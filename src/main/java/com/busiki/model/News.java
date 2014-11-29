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
@Table(name="News")
public class News implements Serializable{
	private static final long serialVersionUID = 3436273783458080590L;
	
	public News(){}
	
	public News(String tresc){
		this.tresc = tresc;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private long id;
	
	@Column(name = "data")
	private Date data;
	
	@Column(name = "tytul")
	private String tytul;
	
	@Column(name = "tresc", columnDefinition="text")
	private String tresc;
	
	public void setId(long id){
		this.id = id;
	}
	
	public long getId(){
		return id;
	}
	
	public void setTytul(String tytul){
		this.tytul = tytul;
	}
	
	public String getTytul(){
		return tytul;
	}
	
	public void setTresc(String tresc){
		this.tresc = tresc;
	}
	
	public String getTresc(){
		return tresc;
	}
	
	public void setDateCreated(Date data){
		this.data = data;
	}
	
	public Date getDate(){
		return data;
	}
}
