package com.busiki.service;

import java.util.List;
import java.util.Set;

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
	public Rezerwacja getById(long id) {
		return rezerwacjaDaoImpl.getById(id);
	}
	public void update(Rezerwacja r) {
		rezerwacjaDaoImpl.update(r);
	}

	public List<Rezerwacja> getReservationsByUserId(long id) {
		return rezerwacjaDaoImpl.getReservationsByUserId(id);
	}
	
	public List<Integer> getSeatNumbersByReservationId(long id) {
		return rezerwacjaDaoImpl.getSeatNumbersByReservationId(id);
	}

	public Set<Rezerwacja> getReservationsByKursId(long id) {
		return rezerwacjaDaoImpl.getReservationsByKursId( id);
	}
	
}
