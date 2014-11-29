package com.busiki.service;

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
}