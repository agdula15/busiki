package com.busiki.service;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.KursDaoImpl;
import com.busiki.model.Kurs;

@Service
@Transactional
public class KursService {
	
	@Autowired
	KursDaoImpl kursDaoImpl;
	
	public void create (Kurs kurs){
		kursDaoImpl.create(kurs);
	}

	public List<Kurs> getKursByCriteriaSearch(String dzien,
			long r_id) {
		return kursDaoImpl.getKursByCriteriaSearch( dzien,
				 r_id);
	}

	public Kurs getById(long kursId) {
		return kursDaoImpl.getById(kursId);
	}

	public Set<Integer> getZajeteMiejsca(Kurs k, Kurs k2) {
		// TODO Auto-generated method stub
		return kursDaoImpl.getZajeteMiejsca(k, k2);
	}
}