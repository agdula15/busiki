package com.busiki.implDao;

import org.springframework.stereotype.Repository;

import com.busiki.model.Bus;

@Repository
public class BusDaoImpl extends AbstractDaoImpl<Bus>{

	public Bus getByName(String pojazd) {
		return (Bus)getSession()
				.createQuery("from Bus where nazwa = :nazwa")
				.setParameter("nazwa", pojazd).uniqueResult();
	}


	
}
