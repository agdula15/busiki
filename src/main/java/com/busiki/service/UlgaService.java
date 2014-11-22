package com.busiki.service;

import java.util.List;

import com.busiki.implDao.UlgaDaoImpl;
import com.busiki.model.Ulga;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UlgaService {
	
	@Autowired
	private UlgaDaoImpl ulgaDaoImpl;

	public List<Ulga> getAll(){
		return ulgaDaoImpl.getAll();
	}
	
	public void create(String opis, double wartosc){
		Ulga ulga = new Ulga(); 
		ulga.setOpis(opis);
		ulga.setWartoscUlgi(wartosc);
		
		ulgaDaoImpl.create(ulga);
	}
	
	public Ulga getById(long id){
		return ulgaDaoImpl.getById(id);
	}
	
	public void edit(Ulga ulga){
		ulgaDaoImpl.update(ulga);
	}
	
	public void delete(long id){
		ulgaDaoImpl.deleteById(id);
	}
}
