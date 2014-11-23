package com.busiki.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.DniKursuDaoImpl;
import com.busiki.model.DniKursu;

@Service
@Transactional
public class DniKursuService {
	
	protected static Logger logger = Logger.getLogger(DniKursuService.class);
	
	@Autowired
	private DniKursuDaoImpl dniKursuDaoImpl;

	public List<DniKursu> getAll() {
		return dniKursuDaoImpl.getAll();
	}

	/*public Bus getById(long id) {
		return busDaoImpl.getById(id);
	}*/

}
