package com.busiki.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.RozkladDaoImpl;
import com.busiki.model.Rozklad;

@Service
@Transactional
public class RozkladService {

	protected static Logger logger = Logger.getLogger(RozkladService.class);

	@Autowired
	private RozkladDaoImpl rozkladDaoImpl;

	public void create(Rozklad r) {
		rozkladDaoImpl.create(r);
	}

	public List<Rozklad> getAllByRozkladInfoID(long riid) {
		return rozkladDaoImpl.getAllByRozkladInfoID(riid);
	}

}
