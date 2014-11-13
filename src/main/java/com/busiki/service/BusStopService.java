package com.busiki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.BusStopDaoImpl;
import com.busiki.model.Przystanek;

@Service
@Transactional
public class BusStopService {
	@Autowired
	private BusStopDaoImpl busStopDaoImpl;
	
	public List<Przystanek> getAll(){
		return busStopDaoImpl.getAll();
	}
}
