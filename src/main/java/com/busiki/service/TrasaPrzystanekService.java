package com.busiki.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.PrzystanekDaoImpl;
import com.busiki.implDao.TrasaDaoImpl;
import com.busiki.model.Przystanek;
import com.busiki.model.TrasaInfo;

@Service
@Transactional
public class TrasaPrzystanekService {

	protected static Logger logger = Logger
			.getLogger(TrasaPrzystanekService.class);

	@Autowired
	private PrzystanekDaoImpl przystanekDaoImpl;

	@Autowired
	private TrasaDaoImpl trasaDaoImpl;

	public List<Przystanek> getAllPrzystanek() {
		return przystanekDaoImpl.getAll();
	}

	public Przystanek getByIdPrzystanek(long id) {
		return przystanekDaoImpl.getById(id);
	}

	public void deletePrzystanek(long id) {
		przystanekDaoImpl.delete(przystanekDaoImpl.getById(id));
	}

	public void updatePrzystanek(Przystanek t) {
		przystanekDaoImpl.update(t);
	}

	public void createPrzystanek(Przystanek t) {
		przystanekDaoImpl.create(t);
	}

	public Przystanek getPrzystanekByName(String nazwa) {
		return przystanekDaoImpl.getPrzystanekByName(nazwa);
	}

	public void dodajTrase(String numer, String string) {
		TrasaInfo newTrasa = new TrasaInfo();
		List<Przystanek> lista = new ArrayList<Przystanek>();
		String[] nazwy = string.split("-");
		for (String s : nazwy) {
			lista.add(przystanekDaoImpl.getPrzystanekByName(s));
			logger.debug("Znaleziono i dodano przystanek do trasy"
					+ przystanekDaoImpl.getPrzystanekByName(s).getNazwa());
		}
		newTrasa.setPrzystanek(lista);
		newTrasa.setNumer(numer);
		trasaDaoImpl.create(newTrasa);
	}

	public List<TrasaInfo> getAllTrasy() {
		for (TrasaInfo t : trasaDaoImpl.getAll()) {
			Hibernate.initialize(t.getPrzystanek());
		}
		return trasaDaoImpl.getAll();
	}
	
	public List<Przystanek> getAllPrzystankiTrasy(TrasaInfo t) {
		return (List<Przystanek>) t.getPrzystanek();
	}
	

	public TrasaInfo getByIdTrasaInfo(long tid) {
		return trasaDaoImpl.getById(tid);
	}

	public Object getByNumerTrasaInfo(long tid) {
		return trasaDaoImpl.getByNumer(tid);
	}
	
}
