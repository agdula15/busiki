package com.busiki.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.RezerwacjaDaoImpl;
import com.busiki.model.Kurs;
import com.busiki.model.Rezerwacja;
import com.busiki.model.User;

@Service
@Transactional
public class RezerwacjaService {

	@Autowired
	private RezerwacjaDaoImpl rezerwacjaDaoImpl;

	public void zatwierdzRezerwacje(Kurs k1, Kurs k2,
			String[] doRezerwacji, User user) {
		rezerwacjaDaoImpl.zatwierdzRezerwacje(k1,k2,doRezerwacji,user);
	}

	public void create(Rezerwacja r) {
		rezerwacjaDaoImpl.create(r);
	}
	
	
}