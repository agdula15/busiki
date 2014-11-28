package com.busiki.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.BusDaoImpl;
import com.busiki.model.Bus;

@Service
@Transactional
public class BusService {

	protected static Logger logger = Logger.getLogger(BusService.class);

	@Autowired
	private BusDaoImpl busDaoImpl;

	public List<Bus> getAll() {
		return busDaoImpl.getAll();
	}

	public Bus getById(long id) {
		return busDaoImpl.getById(id);
	}
	
	public void create(Bus t) {
		busDaoImpl.create(t);
	}

	public void delete(Bus t) {
		busDaoImpl.delete(t);
	}

	public void updateBus(Bus t) {
		busDaoImpl.update(t);
	}

}
