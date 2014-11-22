package com.busiki.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.PrzystanekDaoImpl;
import com.busiki.implDao.TrasaDaoImpl;
import com.busiki.model.Przystanek;
import com.busiki.model.TrasaInfo;

@Service
@Transactional(readOnly=true)
public class TrasaPrzystaneForListingTrasa {

	@Autowired
	private PrzystanekDaoImpl przystanekDaoImpl;

	@Autowired
	private TrasaDaoImpl trasaDaoImpl;

	public List<TrasaInfo> getAllTrasy() {
		for (TrasaInfo t : trasaDaoImpl.getAll()) {
			Hibernate.initialize(t.getPrzystanek());
		}
		return trasaDaoImpl.getAll();
	}

	public Collection<Przystanek> getAllPrzystankiTrasy(TrasaInfo t) {
		return t.getPrzystanek();
	}
	
	public Przystanek getFirstPrzystanek(TrasaInfo t) {
		List<Przystanek> l2 = (ArrayList<Przystanek>) t.getPrzystanek();
		return l2.get(0);
	}
	public Przystanek getLastPrzystanek(TrasaInfo t) {
		List<Przystanek> l2 = (ArrayList<Przystanek>) t.getPrzystanek();
		return l2.get(l2.size()-1);
	}
}
