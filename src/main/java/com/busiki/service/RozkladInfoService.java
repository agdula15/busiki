package com.busiki.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.busiki.implDao.RozkladInfoDaoImpl;
import com.busiki.model.RozkladInfo;

@Service
@Transactional
public class RozkladInfoService {

	@Autowired
	private RozkladInfoDaoImpl rozkladInfoDaoImpl;

	public void create(RozkladInfo rozkladInfo) {
		rozkladInfoDaoImpl.create(rozkladInfo);
	}
	
	public void update(RozkladInfo rozkladInfo) {
		rozkladInfoDaoImpl.update(rozkladInfo);
	}

	public List<RozkladInfo> getAll() {
		return rozkladInfoDaoImpl.getAll();
	}
	
	public RozkladInfo getById(long id) { 
		return rozkladInfoDaoImpl.getById(id);
	}
}
